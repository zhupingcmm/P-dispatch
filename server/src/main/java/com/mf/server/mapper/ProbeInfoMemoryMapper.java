package com.mf.server.mapper;

import com.mf.server.model.MemoryDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProbeInfoMemoryMapper {

    int addProbeMemoryInfo(MemoryDo memoryDo);

    int updateProbeMemoryInfo(MemoryDo memoryDo);

    MemoryDo getProbeMemoryInfo(long id);
}
