package com.mf.server.model;

import com.mf.dispatch.common.base.os.Memory;
import lombok.Data;

import java.util.Date;

@Data
public class MemoryDo extends Memory {
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
