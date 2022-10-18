package com.mf.server.mapper;

import com.mf.server.model.MetricDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MetricMapper {
    /**
     * 添加 metric 信息
     * @param metricDos
     * @return
     */
    int addMetrics(List<MetricDo> metricDos);

    List<MetricDo> getMetricsByProbeId(long id);

    /**
     * 更新 metric 信息
     * @param metricDos
     * @return
     */
    int updateMetrics(List<MetricDo> metricDos);
}
