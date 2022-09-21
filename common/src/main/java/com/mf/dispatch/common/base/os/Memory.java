package com.mf.dispatch.common.base.os;

import com.mf.dispatch.common.base.BaseBean;
import lombok.Data;

@Data
public class Memory extends BaseBean {


    /**
     * 内存总量
     */
    private double total;

    /**
     * 已用内存
     */
    private double used;

    /**
     * 剩余内存
     */
    private double free;
}
