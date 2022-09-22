package com.mf.server.service.impl;

import com.mf.dispatch.common.base.ProbeInfo;
import com.mf.dispatch.common.utils.ObjectTransform;
import com.mf.server.mapper.*;
import com.mf.server.model.*;
import com.mf.server.service.ProbeInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProbeInfoServiceImpl implements ProbeInfoService {

    private final ProbeInfoMapper probeInfoMapper;

    private final ProbeInfoCpuMapper cpuMapper;

    private final ProbeInfoMemoryMapper memoryMapper;

    private final ProbeInfoOsMapper osMapper;

    private final ProbeInfoJvmMapper jvmMapper;

    private final ProbeTaskMapper taskMapper;

    @Override
    @Transactional
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
           cpuMapper.addProbeCpuInfo(cpuDo);

           // 3.插入 数据到 tb_probe_memory
           MemoryDo memoryDo = ObjectTransform.transform(probeInfo.getSystemInfo().getMemory(), MemoryDo.class);
           memoryDo.setProbeInfoId(probeInfoDo.getId());
           memoryMapper.addProbeMemoryInfo(memoryDo);

           // 4. 插入 数据到 tb_probe_os
           OsInfoDo osInfoDo = ObjectTransform.transform(probeInfo.getSystemInfo().getOsInfo(), OsInfoDo.class);
           osInfoDo.setProbeInfoId(probeInfoDo.getProbeId());
           osMapper.addProbeOsInfo(osInfoDo);

           // 5. 插入 数据到 tb_probe_jvm
           JvmDo jvmDo = ObjectTransform.transform(probeInfo.getSystemInfo().getJvm(), JvmDo.class);
           jvmDo.setProbeInfoId(probeInfoDo.getProbeId());
           jvmMapper.addProbeJvmInfo(jvmDo);

           // 6. 插入到 tb_probe_task_queue
           List<ProbeTaskDo> taskDos = ObjectTransform.transform(probeInfo.getTaskQueue(), ProbeTaskDo.class);
           if (!taskDos.isEmpty()) {
               taskMapper.addProbeTaskInfo(taskDos);
           }



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
           cpuMapper.updateProbeCpuInfo(cpuDo);

           //3. 更新数据到 tb_probe_memory
           MemoryDo memoryDo = ObjectTransform.transform(probeInfo.getSystemInfo().getMemory(), MemoryDo.class);
           memoryDo.setProbeInfoId(probeInfoDo.getId());
           memoryMapper.updateProbeMemoryInfo(memoryDo);

           //4. 更新数据到 tb_probe_os
           OsInfoDo osInfoDo = ObjectTransform.transform(probeInfo.getSystemInfo().getOsInfo(), OsInfoDo.class);
           osInfoDo.setProbeInfoId(probeInfoDo.getProbeId());
           osMapper.updateProbeOsInfo(osInfoDo);

           // 5. 更新数据到 tb_probe_jvm
           JvmDo jvmDo = ObjectTransform.transform(probeInfo.getSystemInfo().getJvm(), JvmDo.class);
           jvmDo.setProbeInfoId(probeInfoDo.getProbeId());
           jvmMapper.updateProbeJvmInfo(jvmDo);

           //6. 更新数据到 tb_probe_task_queue
           List<ProbeTaskDo> taskDos = ObjectTransform.transform(probeInfo.getTaskQueue(), ProbeTaskDo.class);
           if (!taskDos.isEmpty()){
               taskMapper.updateTaskInfo(taskDos);
           }


       }

    }

    @Override
    @Transactional
    public void checkProbeStatus() {
        // 获取 tb_probe_info 表中 所有的信息， 在一般生产环境上需要分页
        List<ProbeInfoDo> probeList = probeInfoMapper.getProbeList();

        // 如果 probe 最后更新时间 和当前的系统时间比较小于 10 并且 probe的status是活着的（0） 那就把status字段修改为失联的（1）
        // TODO 这里还可以把 长时间不活动的 probe 直接从数据库中删除
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
