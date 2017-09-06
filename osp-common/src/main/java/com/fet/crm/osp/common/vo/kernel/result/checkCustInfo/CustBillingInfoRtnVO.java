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
package com.fet.crm.osp.common.vo.kernel.result.checkCustInfo;

import java.util.List;

/**
 * 查詢 Billing 相關資訊回傳的封裝物件. <br>
 * 
 * @author RichardHuang
 */
public class CustBillingInfoRtnVO {
    
    private String cycleDate; // 帳單週期起算日
    private String arpb; // 過去半年平均帳單金額
    private String customerScore; // VIP客戶等級
    private String paymentMethod; // 付款方式
    private List<InvoiceInfoVO> invoiceInfoList; // 帳單列表
    
    /**
     * @return the cycleDate
     */
    public String getCycleDate() {
        return cycleDate;
    }
    
    /**
     * @param cycleDate the cycleDate to set
     */
    public void setCycleDate(String cycleDate) {
        this.cycleDate = cycleDate;
    }
    
    /**
     * @return the arpb
     */
    public String getArpb() {
        return arpb;
    }
    
    /**
     * @param arpb the arpb to set
     */
    public void setArpb(String arpb) {
        this.arpb = arpb;
    }
    
    /**
     * @return the customerScore
     */
    public String getCustomerScore() {
        return customerScore;
    }
    
    /**
     * @param customerScore the customerScore to set
     */
    public void setCustomerScore(String customerScore) {
        this.customerScore = customerScore;
    }
    
    /**
     * @return the paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    /**
     * @param paymentMethod the paymentMethod to set
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    /**
     * @return the invoiceInfoList
     */
    public List<InvoiceInfoVO> getInvoiceInfoList() {
        return invoiceInfoList;
    }
    
    /**
     * @param invoiceInfoList the invoiceInfoList to set
     */
    public void setInvoiceInfoList(List<InvoiceInfoVO> invoiceInfoList) {
        this.invoiceInfoList = invoiceInfoList;
    }
    
}
