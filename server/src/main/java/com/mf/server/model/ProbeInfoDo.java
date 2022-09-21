package com.mf.server.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProbeInfoDo {

    private long id;

    /**
     * probe customer id
     */
    private long customerId;

    /**
     * probe id
     */
    private long probeId;



    /**
     *  the probe extra info
     */

//    private String extra;


    /**
     * the probe status 0 active 1 disconnect
     */

    private int status;


    private Date createTime;

    private Date updateTime;
}
