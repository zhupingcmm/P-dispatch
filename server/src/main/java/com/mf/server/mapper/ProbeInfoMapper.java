package com.mf.server.mapper;

import com.mf.server.model.ProbeInfoDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProbeInfoMapper {

    ProbeInfoDo selectProbeInfoByProbeId(long probeId);

    int updateProbeInfo(ProbeInfoDo probeInfoDo);

    int insertProbeInfo(ProbeInfoDo probeInfoDo);

    List<ProbeInfoDo> getProbeList ();

    int updateStatus(List<ProbeInfoDo> probeList);


}
