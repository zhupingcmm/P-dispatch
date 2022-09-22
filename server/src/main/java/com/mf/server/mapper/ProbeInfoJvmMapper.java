package com.mf.server.mapper;

import com.mf.server.model.JvmDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProbeInfoJvmMapper{

    int addProbeJvmInfo(JvmDo data);

    int updateProbeJvmInfo(JvmDo data);

    JvmDo getProbeJvmInfoByProbeInfoId(long id);

}
