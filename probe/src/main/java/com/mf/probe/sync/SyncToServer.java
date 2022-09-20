package com.mf.probe.sync;

import com.mf.dispatch.common.base.ProbeInfo;

public interface SyncToServer <T extends ProbeInfo>{

    /**
     *
     * @param probeInfo
     */
    void syncToServer(T probeInfo);
}
