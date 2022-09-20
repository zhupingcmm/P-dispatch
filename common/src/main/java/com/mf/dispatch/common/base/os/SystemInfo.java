package com.mf.dispatch.common.base.os;

import com.mf.dispatch.common.base.BaseBean;
import lombok.Data;

@Data
public class SystemInfo extends BaseBean {
    private Cpu cpu;
    private Jvm jvm;
    private Memory memory;
    private OsInfo osInfo;
    private SystemFile systemFile;
}
