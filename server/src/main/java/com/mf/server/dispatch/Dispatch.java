package com.mf.server.dispatch;

import com.mf.dispatch.common.base.ProbeInfo;

public interface Dispatch <T extends ProbeInfo>{

    long dispatch();
}
