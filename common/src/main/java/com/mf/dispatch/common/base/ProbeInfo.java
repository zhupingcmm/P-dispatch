package com.mf.dispatch.common.base;


import com.mf.dispatch.common.base.os.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProbeInfo extends BaseBean {

    /**
     * probe customer id
     */
    private long customerId;

    /**
     * probe id
     */
    private long probeId;

    /**
     * the probe system info
     */

    /**
     * cpu info
     */
    private Cpu cpu;

    /**
     * jvm info
     */
    private Jvm jvm;

    /**
     * memory info
     */

    private Memory memory;

    /**
     * os os info
     */
    private OsInfo osInfo;

    /**
     * the probe status 0 active 1 disconnect
     */

    private int status;


    /**
     * probe task queue
     */
    private List<Task> taskQueue = new ArrayList<>();

}
