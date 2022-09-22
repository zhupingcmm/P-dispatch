package com.mf.dispatch.common.base;


import com.mf.dispatch.common.base.os.SystemHardwareInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    private SystemHardwareInfo systemInfo;

    /**
     * the probe status 0 active 1 disconnect
     */

    private int status;


    /**
     * probe task queue
     */
    private LinkedList<Task> taskQueue = new LinkedList<>();

//    /**
//     * the extra info from probe
//     */
//    private Map<String, Object> extra = new ConcurrentHashMap<>();
}
