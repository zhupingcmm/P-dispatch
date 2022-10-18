package com.mf.server.controller;

import com.mf.dispatch.common.base.BaseResponse;
import com.mf.dispatch.common.base.Task;
import com.mf.server.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TaskController {

    private final TaskService<Task> taskService;

    @GetMapping("/task")
    public BaseResponse<?> addTask() {

        // mock task
        Task task = mockTask();
        // 添加 task
        taskService.addTask(task);

        return BaseResponse.success("success");
    }

    private Task mockTask(){
        return Task.builder()
                .probeId(1)
                .customerId(1)
                .taskName("run discovery task")
                .build();
    }
}
