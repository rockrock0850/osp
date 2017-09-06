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

/**
 * 核資 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 * 
 * @author AllenChen
 */
public class CustBillingInfoVO {
    
    private String accountId;
    private String subscriberId;
    
    public String getAccountId() {
        return accountId;
    }
    
    public void setAccountId(String accountID) {
        this.accountId = accountID;
    }
    
    public String getSubscriberId() {
        return subscriberId;
    }
    
    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }
    
}
