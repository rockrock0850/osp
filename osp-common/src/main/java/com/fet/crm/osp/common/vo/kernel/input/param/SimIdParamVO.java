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
package com.fet.crm.osp.common.vo.kernel.input.param;

/**
 * 查詢 SIM 卡號 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 * 
 * @author RichardHuang
 */
public class SimIdParamVO extends AbstractESBParamVO {
    
    private String paymentCategory; // 付款種類
    private String msisdn; // 門號
    
    /**
     * @return the paymentCategory
     */
    public String getPaymentCategory() {
        return paymentCategory;
    }
    
    /**
     * @param paymentCategory the paymentCategory to set
     */
    public void setPaymentCategory(String paymentCategory) {
        this.paymentCategory = paymentCategory;
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
