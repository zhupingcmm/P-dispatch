package com.mf.server.common;

import com.mf.dispatch.common.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Metric extends BaseBean {

    private String name;

    private double usage;
}
