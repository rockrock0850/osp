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

package com.fet.crm.osp.kernel.mware.client.cie;

import com.fet.crm.osp.common.vo.kernel.input.param.AddCIEMasterParamVO;

import tw.com.fet.cie.services.www.bobj.schema.CieMasterBObj;

/**
 * CRMCIEBizService 服務 SOAP Client 介面
 * 
 * @author RichardHuang
 */
public interface ICRMCIEBizServiceClient {
    
    public final String CRM_CIE_BIZ_SERVICE_ENDPOINT_URL = "CRM_CIE_BIZ_SERVICE_ENDPOINT_URL";
    
    /**
     * 呼叫 CRMCIEBizService API 新增 CIE Master 資料. <br>
     * 
     * @param requestParam
     * 
     * @return CieMasterBObj
     */
    CieMasterBObj addCIEMaster(AddCIEMasterParamVO requestParam);
    
}
