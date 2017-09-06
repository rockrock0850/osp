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
package com.fet.crm.osp.kernel.mware.client.pojo;

/**
 * @author RichardHuang
 */
public class ApproverInfoPOJO {
    
    private String empId;
    private String email;
    private String name;
    private String sms;
    private String level;
    private String levelDesc;
    
    /**
     * @return the empId
     */
    public String getEmpId() {
        return empId;
    }
    
    /**
     * @param empId the empId to set
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the sms
     */
    public String getSms() {
        return sms;
    }
    
    /**
     * @param sms the sms to set
     */
    public void setSms(String sms) {
        this.sms = sms;
    }
    
    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }
    
    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }
    
    /**
     * @return the levelDesc
     */
    public String getLevelDesc() {
        return levelDesc;
    }
    
    /**
     * @param levelDesc the levelDesc to set
     */
    public void setLevelDesc(String levelDesc) {
        this.levelDesc = levelDesc;
    }

    @Override
    public String toString() {
        return "ApproverInfoPOJO [empId=" + empId + ", email=" + email + ", name=" + name + ", sms=" + sms + ", level="
                + level + ", levelDesc=" + levelDesc + "]";
    }
    
}
