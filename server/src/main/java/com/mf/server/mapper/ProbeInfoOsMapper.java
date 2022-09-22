package com.mf.server.mapper;

import com.mf.server.model.OsInfoDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProbeInfoOsMapper{

    int addProbeOsInfo(OsInfoDo data);

    int updateProbeOsInfo(OsInfoDo data);

    OsInfoDo getProbeOsInfoByProbeInfoId(long id);
}
