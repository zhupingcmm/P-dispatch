package com.mf.server.mapper;

import com.mf.server.model.CpuDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProbeInfoCpuMapper {

    int addProbeCpuInfo(CpuDo cpuDo);

    int updateProbeCpuInfo(CpuDo cpuDo);

    CpuDo getProbeCpuByProbeInfoId(long id);
}
