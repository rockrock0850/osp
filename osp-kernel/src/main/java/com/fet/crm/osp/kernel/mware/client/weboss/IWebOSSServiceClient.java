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
package com.fet.crm.osp.kernel.mware.client.weboss;

/**
 * WebOSSService SOAP Client 介面
 * 
 * @author RichardHuang
 */
public interface IWebOSSServiceClient {
    
    public final String WEB_OSS_SERVICE_ENDPOINT_URL = "WEB_OSS_SERVICE_ENDPOINT_URL";
    public final String APNID = "APNID";
    public final String APN_RESULT_END = "END";
    
    /**
     * 呼叫 WebOSSService API 查詢 NE Info
     * 
     * @param paymentCategory
     * @param msisdn
     * @param accountName
     * @param subType
     * @param userId 使用者 NT Account
     * 
     * @return String
     */
    String getNEInfo(String paymentCategory, String msisdn, String accountName, String subType, String userId);
    
}
