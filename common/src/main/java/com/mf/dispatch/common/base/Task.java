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
     * customer id
     */
    private long customerId;


    /**
     * task info (run task)
     */
    private String taskName;


    /**
     * task status 0-pending 1-running 2-success 3-failed
     */
    private int status;

}
