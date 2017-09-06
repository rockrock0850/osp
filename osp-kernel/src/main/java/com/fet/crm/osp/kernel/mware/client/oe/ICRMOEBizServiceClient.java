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

package com.fet.crm.osp.kernel.mware.client.oe;

/**
 * CRMOEBizService SOAP Client 介面
 * 
 * @author RichardHuang
 */
public interface ICRMOEBizServiceClient {
    
    public final String CRM_OE_BIZ_SERVICE_ENDPOINT_URL = "CRM_OE_BIZ_SERVICE_ENDPOINT_URL";
    public final String IS_WITH_HISTORY = "false";
    public final String RESOURCE_TYPE_MSISDN = "C";
    public final String RESOURCE_TYPE_SIM = "S";
    
    /**
     * 呼叫 CRMOEBizService API 查詢 SIM 資訊. <br>
     * 
     * @param msisdn
     * @param paymentCategory
     * @param userId 使用者 NT Account
     * 
     * @return String
     */
    String querySimByMsisdn(String msisdn, String paymentCategory, String userId);
    
}
