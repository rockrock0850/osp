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

import java.util.Date;

/**
 * 本期不同帳單類型 資訊封裝物件
 * 
 * @author AllenChen
 */
public class InvoiceInfoVO {
    
    private String invoiceType; // 本期帳單類別
    private String totalInvoiceAmount; // 本期電信&小額新增費用
    private Date statementDate; // 本期帳單結算日
    private Date dueDay; // 繳費截止日
    private String subBE; // 帳單類型
    private String totalAmount; // 本期新增費用
    private String invoiceBalance; // 本期未繳餘額
    private String billingInvoiceNumber; // 本期帳單編號
    
    /**
     * 取得「本期帳單結算日」
     * 
     * @return
     */
    public Date getStatementDate() {
        return statementDate;
    }
    
    /**
     * 設定「本期帳單結算日」
     * 
     * @param statementDate
     */
    public void setStatementDate(Date statementDate) {
        this.statementDate = statementDate;
    }
    
    /**
     * 取得「繳費截止日」
     * 
     * @return
     */
    public Date getDueDay() {
        return dueDay;
    }
    
    /**
     * 設定「繳費截止日」
     * 
     * @param dueDay
     */
    public void setDueDay(Date dueDay) {
        this.dueDay = dueDay;
    }
    
    /**
     * 取得「帳單類型」
     * 
     * @return
     */
    public String getSubBE() {
        return subBE;
    }
    
    /**
     * 設定「帳單類型」
     * 
     * @param subBE
     */
    public void setSubBE(String subBE) {
        this.subBE = subBE;
    }
    
    /**
     * 取得「本期新增費用」
     * 
     * @return
     */
    public String getTotalAmount() {
        return totalAmount;
    }
    
    /**
     * 設定「本期新增費用」
     * 
     * @param totalAmount
     */
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    /**
     * 取得「本期未繳餘額」
     * 
     * @return
     */
    public String getInvoiceBalance() {
        return invoiceBalance;
    }
    
    /**
     * 設定「本期未繳餘額」
     * 
     * @param invoiceBalance
     */
    public void setInvoiceBalance(String invoiceBalance) {
        this.invoiceBalance = invoiceBalance;
    }
    
    /**
     * 取得「本期帳單編號」
     * 
     * @return
     */
    public String getBillingInvoiceNumber() {
        return billingInvoiceNumber;
    }
    
    /**
     * 設定「本期帳單編號」
     * 
     * @param billingInvoiceNumber
     */
    public void setBillingInvoiceNumber(String billingInvoiceNumber) {
        this.billingInvoiceNumber = billingInvoiceNumber;
    }
    
    /**
     * @return the invoiceType
     */
    public String getInvoiceType() {
        return invoiceType;
    }

    /**
     * @param invoiceType the invoiceType to set
     */
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }
    
    /**
     * @return the totalInvoiceAmount
     */
    public String getTotalInvoiceAmount() {
        return totalInvoiceAmount;
    }

    /**
     * @param totalInvoiceAmount the totalInvoiceAmount to set
     */
    public void setTotalInvoiceAmount(String totalInvoiceAmount) {
        this.totalInvoiceAmount = totalInvoiceAmount;
    }

    @Override
    public String toString() {
        return "InvoiceInfoVO [invoiceType=" + invoiceType + ", totalInvoiceAmount=" + totalInvoiceAmount
                + ", statementDate=" + statementDate + ", dueDay=" + dueDay + ", subBE=" + subBE + ", totalAmount="
                + totalAmount + ", invoiceBalance=" + invoiceBalance + ", billingInvoiceNumber=" + billingInvoiceNumber
                + "]";
    }
    
}
