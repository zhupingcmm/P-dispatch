package com.mf.probe.service.impl;

import com.mf.dispatch.common.base.Task;
import com.mf.probe.service.TaskService;

public class TaskServiceImpl<T extends Task> implements TaskService<T> {

    @Override
    public void runTask(T t) {

    }

    @Override
    public int addTask() {
        return 0;
    }

    @Override
    public int updateTaskStatus() {
        return 0;
    }
}
