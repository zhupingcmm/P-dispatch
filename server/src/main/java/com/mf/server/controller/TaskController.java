package com.mf.server.controller;

import com.mf.dispatch.common.base.BaseResponse;
import com.mf.dispatch.common.base.Task;
import com.mf.server.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/task")
    public BaseResponse<?> addTask() {

        Task task = mockTask();
        taskService.addTask(task);
        return BaseResponse.success("zp");
    }

    private Task mockTask(){
        return Task.builder()
                .probeId(2l)
                .taskName("run discovery job")
                .build();

    }
}
