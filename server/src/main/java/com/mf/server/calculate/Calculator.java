package com.mf.server.calculate;


import com.mf.server.common.ProbeItem;

public interface Calculator<T extends ProbeItem> {

    /**
     * 更加 metric 计算 probe 的 score
     * @param t
     */
    void calculate(T t);
}
