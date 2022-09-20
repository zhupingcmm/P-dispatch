package com.mf.server.dispatch.impl;

import com.mf.dispatch.common.base.ProbeInfo;
import com.mf.server.dispatch.Dispatch;

public class AbstractDispatch<T extends ProbeInfo> implements Dispatch {

    @Override
    public long dispatch() {
        return 0;
    }
}
