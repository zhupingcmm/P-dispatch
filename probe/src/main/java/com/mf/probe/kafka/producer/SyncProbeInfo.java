package com.mf.probe.kafka.producer;

import com.mf.dispatch.common.base.ProbeInfo;

public interface SyncProbeInfo <T extends ProbeInfo>{
    /**
     *
     * @param t
     */
    void syncToServer(T t);
}
