package com.mf.server.dispatch;


import com.mf.dispatch.common.base.Task;

public interface Dispatch <T extends Task>{

    /**
     * 把task 派发给 probe
     * @param task
     */
    void dispatch(T task);
}
