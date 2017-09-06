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

package com.fet.crm.osp.kernel.mware.client.billling;

import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustBillingInfoRtnVO;

/**
 * 核資服務 SOAP Client 介面
 * 
 * @author RichardHuang
 */
public interface IDecisionDataServiceClient {
    
    public final String DECISION_DATA_SERVICE_ENDPOINT_URL = "DECISION_DATA_SERVICE_ENDPOINT_URL";
    public final String DECISION_DATA_SERVICE_ENCRYPT_KEY = "DECISION_DATA_SERVICE_ENCRYPT_KEY";
    public final int CYCLE_COUNT = 3; // 查詢帳單期數
    public final String SUB_BE = "A"; // For F1 必填；T = 電信帳單；M = 小額帳單；A = 兩者都需要回傳
    
    /**
     * 呼叫 DecisionDataService API 查詢客戶的帳單資訊. <br>
     * 
     * @param clientIP
     * @param accountId
     * @param subscriberId
     * 
     * @return CustBillingInfoRtnVO
     */
    CustBillingInfoRtnVO queryCustomerBillingInfo(String clientIP, String accountId, String subscriberId);
    
}
