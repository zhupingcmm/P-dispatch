package com.mf.probe.mapper;

import com.mf.probe.model.TaskDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.Mapping;

@Mapper
public interface TaskMapper {
    int addTask(TaskDo taskDo);

    int updateTaskStatus(TaskDo taskDo);
}
