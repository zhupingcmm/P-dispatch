package com.mf.dispatch.common.base;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * probe ip address
     */
    private String ip;
    /**
     * probe hostname
     */
    private String hostname;

    /**
     * probe cpu
     */
    private double cpu;

    /**
     *
     * probe io
     */
    private String io;
    /**
     * probe memory
     */
    private String memory;

    /**
     * the extra info from probe
     */
    private Map<String, Object> extra = new ConcurrentHashMap<>();
}
