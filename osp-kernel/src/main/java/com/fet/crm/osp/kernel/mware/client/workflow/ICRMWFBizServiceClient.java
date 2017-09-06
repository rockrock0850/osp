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
package com.fet.crm.osp.kernel.mware.client.workflow;

import com.fet.crm.osp.kernel.mware.client.pojo.AuthLevelInfoInputPOJO;
import com.fet.crm.osp.kernel.mware.client.pojo.AuthLevelInfoPOJO;

/**
 * 查詢此流程要簽核的層級及要簽核的人員資訊服務 SOAP Client 介面
 * 
 * @author RichardHuang
 */
public interface ICRMWFBizServiceClient {
    
    public final String CRM_WF_BIZ_SERVICE_ENDPOINT_URL = "CRM_WF_BIZ_SERVICE_ENDPOINT_URL";
    public final String FLOW_TYPE = "NSP_GA_AUTH";
    public final String IS_NEW = "Y"; // 是否為啟用
    public final String BIZ_ENTITY = "FET"; // FET or KGT
    public final String SYSTEM_TYPE = "IA";
    public final String FUNCTION_ID = "3NT4G";
    
    /**
     * 呼叫 CRMWFBizService API 查詢此流程要簽核的層級及要簽核的人員資訊. <br>
     * 
     * @param inputParam
     * 
     * @return AuthLevelInfoPOJO
     */
    AuthLevelInfoPOJO getAuthLevelInfo(AuthLevelInfoInputPOJO inputParam);
    
}
