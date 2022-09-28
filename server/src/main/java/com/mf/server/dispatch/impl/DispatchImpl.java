package com.mf.server.dispatch.impl;

import com.alibaba.fastjson.JSON;
import com.mf.dispatch.common.base.ResponseEnum;
import com.mf.dispatch.common.base.Task;
import com.mf.dispatch.common.constants.Constants;
import com.mf.dispatch.common.exception.DispatchException;
import com.mf.server.aware.CalculatorRouter;
import com.mf.server.calculate.Calculator;
import com.mf.server.common.CalcResult;
import com.mf.server.common.Metric;
import com.mf.server.dispatch.Dispatch;
import com.mf.server.mapper.ProbeInfoMapper;
import com.mf.server.model.ProbeInfoDo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
@RequiredArgsConstructor
public class DispatchImpl<T extends Task> implements Dispatch<T> {
    private final ProbeInfoMapper probeInfoMapper;

    private final CalculatorRouter router;

    @Override
    public void dispatch(T task) {
        List<ProbeInfoDo> probeList = probeInfoMapper.getProbeList();

//        List<Metric> metrics = wrap(probeList);
//        System.out.println(probeList);
//
//        for (Metric metric : metrics) {
//            CalcResult calcResult = new CalcResult();
//            Calculator service = router.getService(metric.getName());
//            service.calculate();
//        }

    }

//    private List<Metric> wrap(ProbeInfoDo probeInfoDo){
//        List<Metric> metrics = new ArrayList<>();
//        Metric jvmMetric = new Metric();
//        jvmMetric.setName("jvm");
//        jvmMetric.setUsage(probeInfoDo.getJvm().getTotal() / probeInfoDo.getJvm().getMax());
//        metrics.add(jvmMetric);
//
//        return metrics;
//    }
}
