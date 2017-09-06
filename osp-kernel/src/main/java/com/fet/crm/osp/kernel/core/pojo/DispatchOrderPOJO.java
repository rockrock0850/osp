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
 * 分派案件 操作用POJO物件. <br>
 * 
 * @author RichardHuang
 */
public class DispatchOrderPOJO {
    
    private String poolKey;
    private String orderMId; // 主要進件單號
    private String msisdn; // 門號
    
    /**
     * @return the poolKey
     */
    public String getPoolKey() {
        return poolKey;
    }
    
    /**
     * @param poolKey the poolKey to set
     */
    public void setPoolKey(String poolKey) {
        this.poolKey = poolKey;
    }
    
    /**
     * @return the orderMId
     */
    public String getOrderMId() {
        return orderMId;
    }
    
    /**
     * @param orderMId the orderMId to set
     */
    public void setOrderMId(String orderMId) {
        this.orderMId = orderMId;
    }
    
    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }
    
    /**
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
    
}
