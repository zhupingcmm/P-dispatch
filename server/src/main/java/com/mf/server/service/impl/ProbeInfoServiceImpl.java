package com.mf.server.service.impl;

import com.mf.dispatch.common.base.ProbeInfo;
import com.mf.dispatch.common.utils.ObjectTransform;
import com.mf.server.mapper.ProbeInfoCpuMapper;
import com.mf.server.mapper.ProbeInfoMapper;
import com.mf.server.model.CpuDo;
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

    private final ProbeInfoCpuMapper probeInfoCpuMapper;
    @Override
    public void updateProbeInfo(ProbeInfo probeInfo) {
        // 通过probe id 获取 probe 信息
       ProbeInfoDo probeInfoDo =  probeInfoMapper.selectProbeInfoByProbeId(probeInfo.getProbeId());

       // 如果没有获取到 probe 的信息那就进行插入操作， 如果获取到了 就进行更新操作
       if (probeInfoDo == null) {
           // 插入

           // 1. 插入 数据到 tb_probe_info
           probeInfoMapper.insertProbeInfo(ProbeInfoDo.builder()
                   .customerId(probeInfo.getCustomerId())
                   .probeId(probeInfo.getProbeId())
                   .build());
           probeInfoDo = probeInfoMapper.selectProbeInfoByProbeId(probeInfo.getProbeId());

           // 2. 插入 数据到 tb_probe_cpu
           CpuDo cpuDo = ObjectTransform.transform(probeInfo.getSystemInfo().getCpu(), CpuDo.class);
           cpuDo.setProbeInfoId(probeInfoDo.getId());
           probeInfoCpuMapper.addProbeCpuInfo(cpuDo);

       } else {
           // 更新
           // 1. 更新 tb_probe_info 数据
           probeInfoDo.setCustomerId(probeInfo.getCustomerId());
           probeInfoDo.setProbeId(probeInfo.getProbeId());
           // update time 与 系统时间同步，这个是为了对tb_probe_info status 扫描做准备
           probeInfoDo.setUpdateTime(new Date(System.currentTimeMillis()));
           probeInfoMapper.updateProbeInfo(probeInfoDo);

           //2. 更新 tb_probe_cpu 数据
           CpuDo cpuDo = ObjectTransform.transform(probeInfo.getSystemInfo().getCpu(), CpuDo.class);
           cpuDo.setProbeInfoId(probeInfoDo.getId());
           probeInfoCpuMapper.updateProbeCpuInfo(cpuDo);

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
