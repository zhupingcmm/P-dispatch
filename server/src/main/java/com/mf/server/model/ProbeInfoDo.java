package com.mf.server.model;

import com.mf.dispatch.common.base.BaseBean;
import com.mf.dispatch.common.base.ProbeInfo;
import com.mf.dispatch.common.base.Task;
import com.mf.dispatch.common.base.os.Cpu;
import com.mf.dispatch.common.base.os.Jvm;
import com.mf.dispatch.common.base.os.Memory;
import com.mf.dispatch.common.base.os.OsInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.LinkedList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProbeInfoDo extends BaseBean {

    private long id;

    /**
     * probe customer id
     */
    private long customerId;

    /**
     * probe id
     */
    private long probeId;

    /**
     * the probe system info
     */

    private CpuDo cpu;

    private JvmDo jvm;

    private MemoryDo memory;

    private OsInfoDo osInfo;

    /**
     * the probe status 0 active 1 disconnect
     */

    private int status;


    /**
     * probe task queue
     */
    private LinkedList<ProbeTaskDo> taskQueue = new LinkedList<>();

    private Date createTime;

    private Date updateTime;
}
