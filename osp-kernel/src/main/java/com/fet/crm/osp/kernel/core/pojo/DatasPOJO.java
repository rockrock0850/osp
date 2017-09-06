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
package com.fet.crm.osp.kernel.core.pojo;

import java.util.List;

/**
 * @author RichardHuang
 */
public class DatasPOJO {
    
    List confirmMsgs;
    String errorMsg;
    List promptMsgs;
    
    /**
     * @return the confirmMsgs
     */
    public List getConfirmMsgs() {
        return confirmMsgs;
    }
    
    /**
     * @param confirmMsgs the confirmMsgs to set
     */
    public void setConfirmMsgs(List confirmMsgs) {
        this.confirmMsgs = confirmMsgs;
    }
    
    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }
    
    /**
     * @param errorMsg the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
    /**
     * @return the promptMsgs
     */
    public List getPromptMsgs() {
        return promptMsgs;
    }
    
    /**
     * @param promptMsgs the promptMsgs to set
     */
    public void setPromptMsgs(List promptMsgs) {
        this.promptMsgs = promptMsgs;
    }
    
}
