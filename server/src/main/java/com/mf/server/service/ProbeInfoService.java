package com.mf.server.service;

import com.mf.dispatch.common.base.ProbeInfo;

public interface ProbeInfoService {

    /**
     * 更新probe的信息
     * @param probeInfo
     */
    void updateProbeInfo(ProbeInfo probeInfo);

    /**
     * 检查 probe的状态
     */
    void checkProbeStatus();
}
