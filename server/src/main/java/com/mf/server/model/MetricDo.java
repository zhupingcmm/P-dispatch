package com.mf.server.model;

import com.mf.server.common.Metric;
import lombok.Data;

import java.util.Date;

@Data
public class MetricDo extends Metric {
    /**
     * 主键
     */
    private long id;
    /**
     * 外键
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
