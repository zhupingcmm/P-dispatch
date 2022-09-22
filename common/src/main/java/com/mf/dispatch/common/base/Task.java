package com.mf.dispatch.common.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
     * task status  0 active 1 running 3 success 4 failed 5 pending
     */
    private int status;

}
