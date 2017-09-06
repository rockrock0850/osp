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

package com.fet.crm.osp.kernel.mware.client.crmcust;

import java.rmi.RemoteException;

import com.fet.crm.osp.common.vo.kernel.result.CacheSubscriberInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustInfoForOSPRtnVO;

/**
 * CRMCustMDMBizService SOAP Client 介面
 * 
 * @author RichardHuang
 */
public interface ICRMCustMDMBizServiceClient {
    
    public final String CRM_CUST_MDM_BIZ_SERVICE_ENDPOINT_URL = "CRM_CUST_MDM_BIZ_SERVICE_ENDPOINT_URL";
    
    /**
     * 呼叫 CRMCustMDMBizService API 查詢合約資訊. <br>
     * 
     * @param subscriberID
     * @param userId 使用者 NT Account
     * 
     * @return CustInfoForOSPRtnVO
     * @throws ProcessingFault 
     * @throws RemoteException 
     */
    CustInfoForOSPRtnVO getContractBySubscriberId(String subscriberID, String userId);
    
    /**
     * 呼叫 CRMCustMDMBizService API 查詢 Cache Subscriber Info 資訊. <br>
     * 
     * @param key
     * @param userId 使用者 NT Account
     * 
     * @return CacheSubscriberInfoRtnVO
     */
    CacheSubscriberInfoRtnVO getCacheSubscriberInfoByKey(String key, String userId);
    
}
