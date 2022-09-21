package com.mf.dispatch.common.base.os;

import com.mf.dispatch.common.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cpu extends BaseBean {

    /**
     * core cpu number
     */
    private int cupNum;

    /**
     * the total used of  cpu ratio
     */
    private double total;


    /**
     * the system used of cpu ratio
     */
    private double sys;

    /**
     * the user used of cpu ratio
     */
    private double used;

    /**
     * the ratio of wait for cpu
     */

    private double wait;

    /**
     * the free ratio of cpu
     */
    private double free;
}
