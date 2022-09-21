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
    private List<SystemFile> systemFiles = new ArrayList<>();


    public SystemHardwareInfo () {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();

        updateCpuInfo(hardware.getProcessor());
        updateMemory(hardware.getMemory());
        updateOsInfo();
        updateJvmInfo();
        updateSystemFiles(systemInfo.getOperatingSystem());
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
     * 设置服务器信息
     */
    private void updateOsInfo() {
        Properties props = System.getProperties();
        osInfo.setComputerIp(NetUtil.getLocalhostStr());
        osInfo.setOsName(props.getProperty("os.name"));
        osInfo.setOsArch(props.getProperty("os.arch"));
        osInfo.setUserDir(props.getProperty("user.dir"));
    }

    /**
     * 设置Java虚拟机
     */
    private void updateJvmInfo() {
        Properties props = System.getProperties();
        jvm.setTotal(Runtime.getRuntime().totalMemory());
        jvm.setMax(Runtime.getRuntime().maxMemory());
        jvm.setFree(Runtime.getRuntime().freeMemory());
        jvm.setVersion(props.getProperty("java.version"));
        jvm.setHome(props.getProperty("java.home"));
    }

    /**
     * 设置磁盘信息
     */
    private void updateSystemFiles(OperatingSystem os) {
        FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fsArray = fileSystem.getFileStores();
        for (OSFileStore fs : fsArray) {
            SystemFile systemFile = new SystemFile();
            long free = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used = total - free;
            systemFile.setDirName(fs.getMount());
            systemFile.setSysTypeName(fs.getType());
            systemFile.setTypeName(fs.getName());
            systemFile.setTotal(convertFileSize(total));
            systemFile.setFree(convertFileSize(free));
            systemFile.setUsed(convertFileSize(used));
            systemFile.setUsage(NumberUtil.round(NumberUtil.mul(used, total, 4), 100).doubleValue());
            systemFiles.add(systemFile);
        }
    }

    /**
     * 字节转换
     *
     * @param size 字节大小
     * @return 转换后值
     */
    private String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else {
            return String.format("%d B", size);
        }
    }

}
