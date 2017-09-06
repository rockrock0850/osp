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
package com.fet.crm.osp.common.vo.kernel.result.agentinfo;

/**
 * @author RichardHuang
 */
public class InternaluseraccountVO {
    
    String userid;
    String name;
    String englishname;
    String password;
    String email;
    String status;
    String isPasswordLocked;
    String position;
    String agentcode;
    
    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }
    
    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
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
     * @return the englishname
     */
    public String getEnglishname() {
        return englishname;
    }
    
    /**
     * @param englishname the englishname to set
     */
    public void setEnglishname(String englishname) {
        this.englishname = englishname;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * @return the isPasswordLocked
     */
    public String getIsPasswordLocked() {
        return isPasswordLocked;
    }
    
    /**
     * @param isPasswordLocked the isPasswordLocked to set
     */
    public void setIsPasswordLocked(String isPasswordLocked) {
        this.isPasswordLocked = isPasswordLocked;
    }
    
    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }
    
    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }
    
    /**
     * @return the agentcode
     */
    public String getAgentcode() {
        return agentcode;
    }
    
    /**
     * @param agentcode the agentcode to set
     */
    public void setAgentcode(String agentcode) {
        this.agentcode = agentcode;
    }

    @Override
    public String toString() {
        return "InternaluseraccountVO [userid=" + userid + ", name=" + name + ", englishname=" + englishname
                + ", password=" + password + ", email=" + email + ", status=" + status + ", isPasswordLocked="
                + isPasswordLocked + ", position=" + position + ", agentcode=" + agentcode + "]";
    }
    
}
