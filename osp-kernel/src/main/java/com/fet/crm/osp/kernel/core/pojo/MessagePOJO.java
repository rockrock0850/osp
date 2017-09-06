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
public class MessagePOJO {
    
    String level;
    String msg;
    DatasPOJO datas;
    String status;
    
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
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }
    
    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
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

    @Override
    public String toString() {
        return "MessagePOJO [level=" + level + ", msg=" + msg + ", datas=" + datas + ", status=" + status + "]";
    }
    
}
