package com.mf.server.model;

import com.mf.dispatch.common.base.Task;
import lombok.Data;

import java.util.Date;

@Data
public class ProbeTaskDo extends Task {
    /**
     * 主键
     */
    private long id;
    /**
     * 外键 tb_probe_info id 字段
     */
    private long probeInfoId;

    /**
     * probe cpu 的创建时间
     */
    private Date createTime;

    /**
     * 最后一次的更新事假
     */
    private Date updateTime;
}
