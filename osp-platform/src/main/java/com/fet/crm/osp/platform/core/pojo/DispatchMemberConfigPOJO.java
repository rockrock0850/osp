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

package com.fet.crm.osp.platform.core.pojo;

import java.util.Date;

/**
 * [暫停分派] POJO物件
 *
 * @author AndrewLee
 */
public class DispatchMemberConfigPOJO {

    private String dispMemberId;
    private String empno;
    private String empname;
    private Date pauseStartTime;
    private Date pauseEndTime;
    private String active;
    private Date createDate;
    private String createUser;
    private Date updateDate;
    private String updateUser;

    public String getDispMemberId() {
        return dispMemberId;
    }

    public void setDispMemberId(String dispMemberId) {
        this.dispMemberId = dispMemberId;
    }

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public Date getPauseStartTime() {
        return pauseStartTime;
    }

    public void setPauseStartTime(Date pauseStartTime) {
        this.pauseStartTime = pauseStartTime;
    }

    public Date getPauseEndTime() {
        return pauseEndTime;
    }

    public void setPauseEndTime(Date pauseEndTime) {
        this.pauseEndTime = pauseEndTime;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

}
