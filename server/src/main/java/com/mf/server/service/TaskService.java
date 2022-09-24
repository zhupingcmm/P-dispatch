package com.mf.server.service;

import com.mf.dispatch.common.base.Task;

public interface TaskService<T extends Task> {
    void addTask(T t);
}
