package com.mf.dispatch.common.base.os;


import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.NumberUtil;
import com.mf.dispatch.common.base.BaseBean;
import lombok.Data;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Data
public class SystemHardwareInfo extends BaseBean {

    private static final int OSHI_WAIT_SECOND = 1000;
    private Cpu cpu = new Cpu();
    private Jvm jvm = new Jvm();
    private Memory memory = new Memory();
    private OsInfo osInfo = new OsInfo();


    public SystemHardwareInfo () {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();

        updateCpuInfo(hardware.getProcessor());
        updateMemory(hardware.getMemory());
        updateOsInfo();
        updateJvmInfo();
    }

    /**
     * update cpu info
     * @param processor
     */
    private void updateCpuInfo(CentralProcessor processor){
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Util.sleep(OSHI_WAIT_SECOND);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        cpu.setCupNum(processor.getLogicalProcessorCount());
        cpu.setTotal(totalCpu);
        cpu.setSys(cSys);
        cpu.setUsed(user);
        cpu.setWait(iowait);
        cpu.setFree(idle);
    }


    private void updateMemory(GlobalMemory globalMemory) {
        memory.setTotal(globalMemory.getTotal());
        memory.setUsed(globalMemory.getTotal() - globalMemory.getAvailable());
        memory.setFree(globalMemory.getAvailable());
    }

    /**
     * ?????????????????????
     */
    private void updateOsInfo() {
        Properties props = System.getProperties();
        osInfo.setComputerIp(NetUtil.getLocalhostStr());
        osInfo.setOsName(props.getProperty("os.name"));
        osInfo.setOsArch(props.getProperty("os.arch"));
        osInfo.setUserDir(props.getProperty("user.dir"));
    }

    /**
     * ??????Java?????????
     */
    private void updateJvmInfo() {
        Properties props = System.getProperties();
        jvm.setTotal(Runtime.getRuntime().totalMemory());
        jvm.setMax(Runtime.getRuntime().maxMemory());
        jvm.setFree(Runtime.getRuntime().freeMemory());
        jvm.setVersion(props.getProperty("java.version"));
        jvm.setHome(props.getProperty("java.home"));
    }


}
