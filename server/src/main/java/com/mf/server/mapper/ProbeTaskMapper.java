package com.mf.server.mapper;

import com.mf.server.model.ProbeTaskDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProbeTaskMapper {

    int addProbeTaskInfo(List<ProbeTaskDo> probeTaskDo);

    int updateTaskInfo(List<ProbeTaskDo> probeTaskDo);

    List<ProbeTaskDo> getProbeTasksByProbeId(long id);
}
