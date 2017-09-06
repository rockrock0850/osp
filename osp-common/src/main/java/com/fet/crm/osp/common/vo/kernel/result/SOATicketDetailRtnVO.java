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

package com.fet.crm.osp.common.vo.kernel.result;

/**
 * 查詢 SOA 進件詳細資訊後所回傳的封裝物件. <br>
 * 
 * @author RichardHuang
 */
public class SOATicketDetailRtnVO {
    
    String ticketNo; // Ticket ID
    String msisdn; // 門號
    String recipientID; // 移入業者
    String dornorID; // 移出業者
    String custID; // 證號
    String fID; // 通知初審結果時間
    String contractDate; // NP 生效日
    String state; // Current State
    
    /**
     * @return the ticketNo
     */
    public String getTicketNo() {
        return ticketNo;
    }
    
    /**
     * @param ticketNo the ticketNo to set
     */
    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
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
    
    /**
     * @return the recipientID
     */
    public String getRecipientID() {
        return recipientID;
    }
    
    /**
     * @param recipientID the recipientID to set
     */
    public void setRecipientID(String recipientID) {
        this.recipientID = recipientID;
    }
    
    /**
     * @return the dornorID
     */
    public String getDornorID() {
        return dornorID;
    }
    
    /**
     * @param dornorID the dornorID to set
     */
    public void setDornorID(String dornorID) {
        this.dornorID = dornorID;
    }
    
    /**
     * @return the custID
     */
    public String getCustID() {
        return custID;
    }
    
    /**
     * @param custID the custID to set
     */
    public void setCustID(String custID) {
        this.custID = custID;
    }
    
    /**
     * @return the fID
     */
    public String getFID() {
        return fID;
    }
    
    /**
     * @param fID the fID to set
     */
    public void setFID(String fID) {
        this.fID = fID;
    }
    
    /**
     * @return the contractDate
     */
    public String getContractDate() {
        return contractDate;
    }
    
    /**
     * @param contractDate the contractDate to set
     */
    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }
    
    /**
     * @return the state
     */
    public String getState() {
        return state;
    }
    
    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

}
