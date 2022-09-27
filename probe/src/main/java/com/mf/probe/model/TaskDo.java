package com.mf.probe.model;

import com.mf.dispatch.common.base.Task;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDo extends Task {
    /**
     * 主键
     */
    private long id;

    /**
     * probe cpu 的创建时间
     */
    private Date createTime;

    /**
     * 最后一次的更新事假
     */
    private Date updateTime;
}
