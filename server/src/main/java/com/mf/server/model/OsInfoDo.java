package com.mf.server.model;

import com.mf.dispatch.common.base.os.OsInfo;
import lombok.Data;

@Data
public class OsInfoDo extends OsInfo {

    private long id;

    private long probeInfoId;
}
