package com.mf.probe.service.impl;

import com.mf.dispatch.common.base.ResponseEnum;
import com.mf.dispatch.common.base.Task;
import com.mf.dispatch.common.exception.DispatchException;
import com.mf.dispatch.common.utils.Asset;
import com.mf.dispatch.common.utils.ObjectTransform;
import com.mf.probe.mapper.TaskMapper;
import com.mf.probe.model.TaskDo;
import com.mf.probe.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        TaskDo taskDo = new TaskDo();
        taskDo.setTaskName(t.getTaskName());
        try {
            Future<Integer> result = poolExecutor.submit(() -> {
                // 模拟执行task任务，并且返回执行结果
                log.info("Executor {} task", taskDo.getTaskName());
                Thread.sleep(1000);
                return 1;
            });
            log.info("Run {} with result: {}", t.getTaskName(), result.get());

            taskDo.setStatus(result.get());
            // 更新 db 中 tb_task_queue task的状态
            taskMapper.updateTaskStatus(taskDo);

        } catch (InterruptedException | ExecutionException e) {
            // 把 task 状态设置成为失败
            taskDo.setStatus(3);
            // 更新 db 中 tb_task_queue task的状态
            taskMapper.updateTaskStatus(taskDo);
            throw new DispatchException(ResponseEnum.RUN_TASK_FAILED);
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
    public List<Task> getTasks() {
        return ObjectTransform.transform(taskMapper.getTasks(), Task.class);
    }
}
