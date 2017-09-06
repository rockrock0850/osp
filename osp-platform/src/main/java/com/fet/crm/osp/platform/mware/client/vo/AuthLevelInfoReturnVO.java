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
package com.fet.crm.osp.platform.mware.client.vo;

/**
 * 查詢簽核層級與人員相關資訊的 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 *  
 * @author Adam Yeh
 */
public class AuthLevelInfoReturnVO {
    
    private String authLevel; // 特授層級
    private String empId; // 簽核者的員工代碼
    private String email; // 簽核者的email
    private String name; // 簽核者的name
    private String sms; // 簽核者的sms
    private String level; // 層級
    private String levelDesc; // 職稱
    
    /**
     * @return the authLevel
     */
    public String getAuthLevel() {
        return authLevel;
    }
    
    /**
     * @param authLevel the authLevel to set
     */
    public void setAuthLevel(String authLevel) {
        this.authLevel = authLevel;
    }
    
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
        return "AuthLevelInfoRtnVO [authLevel=" + authLevel + ", empId=" + empId + ", email=" + email + ", name=" + name
                + ", sms=" + sms + ", level=" + level + ", levelDesc=" + levelDesc + "]";
    }
    
}
