/**
 * Copyright (c) 2017 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */

package com.fet.crm.osp.platform.core.vo.orderinfo;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;

/**
 * [分派人員維護] 查詢結果 資料物件
 * 
 * @author LawrenceLai,AndrewLee
 */
public class OrderDispatchVO extends AbstractOspBaseVO {

    private String dispMemberId;
    private String empNo;
    private String empName;
    private String pauseStartTime;
    private String pauseEndTime;
    private String active;

    public String getDispMemberId() {
        return dispMemberId;
    }

    public void setDispMemberId(String dispMemberId) {
        this.dispMemberId = dispMemberId;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPauseStartTime() {
        return pauseStartTime;
    }

    public void setPauseStartTime(String pauseStartTime) {
        this.pauseStartTime = pauseStartTime;
    }

    public String getPauseEndTime() {
        return pauseEndTime;
    }

    public void setPauseEndTime(String pauseEndTime) {
        this.pauseEndTime = pauseEndTime;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

}
