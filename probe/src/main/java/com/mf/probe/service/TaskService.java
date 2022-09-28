package com.mf.probe.service;

import com.mf.dispatch.common.base.Task;

import java.util.List;

public interface TaskService<T extends Task> {

    /**
     * 执行任务
     * @param t
     */
    void runTask(T t);

    /**
     * 添加任务到db
     * @param t
     */
    void addTask(T t);

    /**
     * 更新任务的状态
     * @return
     */
    int updateTaskStatus();

    /**
     * 获取任务队列
     * @return
     */
    List<Task> getTasks();

}
