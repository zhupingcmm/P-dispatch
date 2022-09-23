package com.mf.server.quartz;

import com.mf.server.mapper.StudentMapper;
import com.mf.server.service.ProbeInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ScanProbeStatusJob extends QuartzJobBean {

    private final ProbeInfoService probeInfoService;
    private final StudentMapper studentMapper;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        studentMapper.selectStudentById(1);
        // 扫描 tb_probe_info 这张表的数据，并且根据规则修改probe的status
        probeInfoService.checkProbeStatus();
    }
}
