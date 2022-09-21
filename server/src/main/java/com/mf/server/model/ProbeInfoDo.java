package com.mf.server.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Builder
public class ProbeInfoDo {

    private long id;

    /**
     * probe customer id
     */
    private long customerId;

    /**
     * probe id
     */
    private long probeId;

    /**
     *  the probe extra info
     */

//    private String extra;
}
