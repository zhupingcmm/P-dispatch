package com.mf.probe.service;

import com.mf.dispatch.common.base.Task;

public interface TaskService<T extends Task> {

    void runTask(T t);

    void addTask(T t);

    int updateTaskStatus();

}
