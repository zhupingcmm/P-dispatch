package com.mf.probe.mapper;

import com.mf.probe.model.TaskDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {
    int addTask(TaskDo taskDo);

    int updateTaskStatus(TaskDo taskDo);

    List<TaskDo> getTasks();
}
