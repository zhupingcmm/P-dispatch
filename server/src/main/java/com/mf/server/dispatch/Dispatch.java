package com.mf.server.dispatch;

import com.mf.dispatch.common.base.ProbeInfo;
import com.mf.dispatch.common.base.Task;

public interface Dispatch <T extends Task>{

    void dispatch(T task);
}
