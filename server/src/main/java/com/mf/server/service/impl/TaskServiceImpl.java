package com.mf.server.service.impl;

import com.mf.dispatch.common.base.Task;
import com.mf.server.dispatch.Dispatch;
import com.mf.server.kafka.producer.TaskMessage;
import com.mf.server.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl<T extends Task> implements TaskService<T> {
    private final Dispatch<T> dispatchService;

    private final TaskMessage<T> taskMessage;
    @Override
    public void addTask(T task) {
        // 根据probe 的信息，计算出一台最优的probe机器

        dispatchService.dispatch(task);

        // 把计算出来的结果发送出去
        taskMessage.sendTask(task);
    }
}
