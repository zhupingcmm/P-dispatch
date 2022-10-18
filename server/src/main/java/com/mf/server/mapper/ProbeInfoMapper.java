package com.mf.server.mapper;

import com.mf.server.model.ProbeInfoDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProbeInfoMapper {

    /**
     *
     * @param probeId
     * @return 返回 probe的信息
     * @des 根据 probe id 查询到 probe的信息
     */
    ProbeInfoDo selectProbeInfoByProbeId(long probeId);

    /**
     *
     * @param probeInfoDo
     * @return
     * @Des 更新 probe 的信息, 现在只更新只更新customer_id
     */
    int updateProbeInfo(ProbeInfoDo probeInfoDo);

    /**
     * 插入 probe 信息
     * @param probeInfoDo
     * @return
     */
    int insertProbeInfo(ProbeInfoDo probeInfoDo);

    /**
     *  通过 customer id 获取 有关customer 相关的probe的信息
     * @return
     */
    List<ProbeInfoDo> getProbeListByCustomerId (long customerId);


    /**
     * 返回 所有 probe 信息
     * @return
     */
    List<ProbeInfoDo> getProbeList();

    /**
     *
     * @param probeList
     * @return
     * 更新 probe 的 转态信息
     */
    int updateStatus(List<ProbeInfoDo> probeList);


}
