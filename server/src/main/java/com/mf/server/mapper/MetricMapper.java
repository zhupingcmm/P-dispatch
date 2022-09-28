package com.mf.server.mapper;

import com.mf.server.model.MetricDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MetricMapper {
    int addMetrics(List<MetricDo> metricDos);

    List<MetricDo> getMetricsByProbeId(long id);

    int updateMetrics(List<MetricDo> metricDos);
}
