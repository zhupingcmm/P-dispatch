package com.mf.probe.kafka.producer;

import com.mf.dispatch.common.base.ProbeInfo;

public interface SyncProbeInfo <T extends ProbeInfo>{
    /**
     * 把 probe 的信息同步给server
     * @param t
     */
    void syncToServer(T t);
}
