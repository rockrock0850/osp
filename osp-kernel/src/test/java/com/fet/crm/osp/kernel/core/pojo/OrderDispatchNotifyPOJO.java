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

import java.util.Date;

/**
 * @author RichardHuang
 */
public class OrderDispatchNotifyPOJO {
    
    private String batchNo;
    private String orderMId;
    private String processUserId;
    private Date createDate;
    
    /**
     * @return the batchNo
     */
    public String getBatchNo() {
        return batchNo;
    }
    
    /**
     * @param batchNo the batchNo to set
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
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
     * @return the processUserId
     */
    public String getProcessUserId() {
        return processUserId;
    }
    
    /**
     * @param processUserId the processUserId to set
     */
    public void setProcessUserId(String processUserId) {
        this.processUserId = processUserId;
    }
    
    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }
    
    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "OrderDispatchNotifyPOJO [batchNo=" + batchNo + ", orderMId=" + orderMId + ", processUserId="
                + processUserId + ", createDate=" + createDate + "]";
    }
    
}
