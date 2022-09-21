package com.mf.server.model;

import com.mf.dispatch.common.base.os.Memory;
import lombok.Data;

@Data
public class MemoryDo extends Memory {
    private long id;
    private long probeInfoId;
}
