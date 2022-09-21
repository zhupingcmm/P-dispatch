package com.mf.dispatch.common.base;

import lombok.Data;

@Data
public class Task extends BaseBean {

    /**
     * probe id
     */
    private long probeId;


    /**
     * task info (run job)
     */
    private String jobName;


    /**
     * task status 0 active 1 success 3 failed
     */
    private int status;

}
