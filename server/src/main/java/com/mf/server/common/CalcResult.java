package com.mf.server.common;

import com.mf.dispatch.common.base.BaseBean;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalcResult extends BaseBean {

    private long score;
}
