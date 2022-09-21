package com.mf.dispatch.common.base.os;

import com.mf.dispatch.common.base.BaseBean;
import lombok.Data;

@Data
public class SystemFile  extends BaseBean {

    /**
     * 盘符路径
     */
    private String dirName;

    /**
     * 盘符类型
     */
    private String sysTypeName;

    /**
     * 文件类型
     */
    private String typeName;

    /**
     * 总大小 单位mb
     */
    private double total;

    /**
     * 剩余大小 单位mb
     */
    private double free;

    /**
     * 已经使用量 单位mb
     */
    private double used;

    /**
     * 资源的使用率
     */
    private double usage;
}
