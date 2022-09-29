package com.mf.server.dispatch.impl;

import com.alibaba.fastjson.JSON;
import com.mf.dispatch.common.base.ResponseEnum;
import com.mf.dispatch.common.base.Task;
import com.mf.dispatch.common.constants.Constants;
import com.mf.dispatch.common.exception.DispatchException;
import com.mf.dispatch.common.utils.ObjectTransform;
import com.mf.server.aware.CalculatorRouter;
import com.mf.server.calculate.Calculator;
import com.mf.server.common.CalcResult;
import com.mf.server.common.Metric;
import com.mf.server.common.ProbeItem;
import com.mf.server.dispatch.Dispatch;
import com.mf.server.kafka.producer.TaskMessage;
import com.mf.server.mapper.ProbeInfoMapper;
import com.mf.server.model.ProbeInfoDo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DispatchImpl<T extends Task> implements Dispatch<T> {
    private final ProbeInfoMapper probeInfoMapper;

    private final CalculatorRouter router;

    private final TaskMessage<T> taskMessage;

    @Override
    public void dispatch(T task) {

        List<ProbeItem> probes = wrap(probeInfoMapper.getProbeList());
        for (ProbeItem probe : probes) {

            for (Metric metric : probe.getMetrics()) {
                // 根据 metric 的name 进行路由
                Calculator<ProbeItem> calculator = router.getService(metric.getName());
                if (calculator == null) {
                    throw new DispatchException(ResponseEnum.METRIC_IMPLEMENT_DO_NOT_EXIT);
                }

                calculator.calculate(probe);
            }

            // 根据 taskQueue 来计算 score
            // 这里算的就比较简单，task queue 每个task 都会让 score 减 1
            probe.setScore(probe.getScore() - probe.getTaskQueue().size());
        }

        // 排序 得到 score 最高的probe
        probes.sort((o1, o2) -> (int) (o1.getScore() - o2.getScore()));

        ProbeItem probeItem = probes.stream()
                .findFirst()
                .orElse(null);

        // 如果计算出来的结果 没有 probe 复合 要求就抛出异常
        // todo 这里可以加上必须得分要达到多少的判断 比如 60 ，才能 发送 task 信息到 probe 端
        // todo 这里也有另外一种做法 我们可以加缓存，既然计算出来没有可以执行 task 的 probe 我们就把task 缓存起来，然后 通过一个定时任务，隔一段时间去计算一下，有没有可以使用的 probe
        // 在实际的生产上，使用的这两种做法的结合体，即使用了缓存，也使用score的阈值
        if (probeItem == null) {
            throw new DispatchException(ResponseEnum.NO_AVAILABLE_PROBE);
        }
        task.setProbeId(probeItem.getProbeId());
        taskMessage.sendTask(task);
    }

    private List<ProbeItem> wrap(List<ProbeInfoDo> probeList){
        List<ProbeItem> probes = new ArrayList<>();
        probeList.stream()
                // 过滤出 active 的 probe
                .filter(x -> x.getStatus() == 0)
                .forEach(x -> {
                    ProbeItem probeItem = ProbeItem.builder()
                            .metrics(ObjectTransform.transform(x.getMetrics(), Metric.class))
                            .taskQueue(ObjectTransform.transform(x.getTaskQueue(), Task.class)
                                    .stream()
                                    //过滤出pending 或者 active的job
                                    .filter(t -> t.getStatus() == 1 || t.getStatus() == 0)
                                    .collect(Collectors.toList()))
                            .score(0)
                            .build();
                    probes.add(probeItem);
                });
        return probes;
    }
}
