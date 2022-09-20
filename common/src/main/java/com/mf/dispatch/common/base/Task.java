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
}
