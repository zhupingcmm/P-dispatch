package com.mf.server.service.impl;

import com.mf.dispatch.common.base.ProbeInfo;
import com.mf.server.mapper.ProbeInfoMapper;
import com.mf.server.model.ProbeInfoDo;
import com.mf.server.service.ProbeInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProbeInfoServiceImpl implements ProbeInfoService {

    private final ProbeInfoMapper probeInfoMapper;
    @Override
    public void updateProbeInfo(ProbeInfo probeInfo) {

       ProbeInfoDo probeInfoDo =  probeInfoMapper.selectProbeInfoByProbeId(probeInfo.getProbeId());
       if (probeInfoDo == null) {
           probeInfoMapper.insertProbeInfo(ProbeInfoDo.builder()
                   .customerId(probeInfo.getCustomerId())
                   .probeId(probeInfo.getProbeId())
                   .build());
       } else {
           probeInfoDo.setCustomerId(probeInfo.getCustomerId());
           probeInfoDo.setProbeId(probeInfo.getProbeId());
           probeInfoDo.setUpdateTime(new Date(System.currentTimeMillis()));
           probeInfoMapper.updateProbeInfo(probeInfoDo);
       }

    }
}
