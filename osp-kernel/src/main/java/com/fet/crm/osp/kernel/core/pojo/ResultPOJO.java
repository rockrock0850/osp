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

/**
 * @author RichardHuang
 */
public class ResultPOJO {
    
    MessagePOJO message;
    DatasPOJO datas;
    String status;
    
    /**
     * @return the message
     */
    public MessagePOJO getMessage() {
        return message;
    }
    
    /**
     * @param message the message to set
     */
    public void setMessage(MessagePOJO message) {
        this.message = message;
    }
    
    /**
     * @return the datas
     */
    public DatasPOJO getDatas() {
        return datas;
    }
    
    /**
     * @param datas the datas to set
     */
    public void setDatas(DatasPOJO datas) {
        this.datas = datas;
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
}
