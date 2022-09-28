package com.mf.server.kafka.producer;

import com.mf.dispatch.common.base.Task;

public interface TaskMessage<T extends Task> {

    /**
     * 把task 任务信息发送出去
     * @param task
     */
    void sendTask(T task);
}
