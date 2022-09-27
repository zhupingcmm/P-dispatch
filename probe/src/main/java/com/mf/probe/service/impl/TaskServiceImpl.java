package com.mf.probe.service.impl;

import com.mf.dispatch.common.base.Task;
import com.mf.dispatch.common.utils.Asset;
import com.mf.dispatch.common.utils.ObjectTransform;
import com.mf.probe.mapper.TaskMapper;
import com.mf.probe.model.TaskDo;
import com.mf.probe.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl<T extends Task> implements TaskService<T> {

    private final TaskMapper taskMapper;

    private final ThreadPoolExecutor poolExecutor;
    @Override
    public void runTask(T t) {
        try {
            Future<Integer> result = poolExecutor.submit(() -> {
                log.info(t.toString());
                Thread.sleep(1000);
                return 1;
            });
            log.info("result: {}", result.get());
//            taskMapper.updateTaskStatus();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void addTask(T t) {
        TaskDo taskDo = ObjectTransform.transform(t, TaskDo.class);
        int result = taskMapper.addTask(taskDo);
        Asset.singleRowAffected(result);
    }


    @Override
    @Transactional
    public int updateTaskStatus() {
        return 0;
    }
}
