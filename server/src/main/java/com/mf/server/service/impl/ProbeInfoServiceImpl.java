package com.mf.server.service.impl;

import com.mf.dispatch.common.base.ProbeInfo;
import com.mf.server.mapper.ProbeInfoMapper;
import com.mf.server.model.ProbeInfoDo;
import com.mf.server.service.ProbeInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    @Override
    public void checkProbeStatus() {
        // 获取 tb_probe_info 表中 所有的信息， 在一般生产环境上需要分页
        List<ProbeInfoDo> probeList = probeInfoMapper.getProbeList();

        // 如果 probe 最后更新时间 和当前的系统时间比较小于 10 并且 probe的status是活着的（0） 那就把status字段修改为失联的（1）
        probeList = probeList.stream().filter(x -> (System.currentTimeMillis() - x.getUpdateTime().getTime() > 10 * 1000) && x.getStatus() == 0)
                .collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        probeList.forEach(x -> sb.append(x.getProbeId()));
        if (!probeList.isEmpty()){
            log.info("probe: {} is disconnect", sb);
            probeInfoMapper.updateStatus(probeList);
        }

    }
}
