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

import java.util.List;

import com.fet.crm.osp.common.vo.kernel.result.SCVAccountInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.SpecialOfferRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.idviewinfo.IdViewInfoRtnVO;

/**
 * CRMCustBizService SOAP Client 介面
 * 
 * @author RichardHuang
 */
public interface ICRMCustBizServiceClient {
    
    public final String CRM_CUST_BIZ_SERVICE_ENDPOINT_URL = "CRM_CUST_BIZ_SERVICE_ENDPOINT_URL";
    public final int QUERY_TYPE = 1;
    public final String COUNT_FLAG = "Y";
    
    /**
     * 呼叫 CRMCustBizService API 查詢合約資訊. <br>
     * 
     * @param rocId 身分證號碼
     * @param userId 使用者 NT Account
     * 
     * @return List<SpecialOfferRtnVO>
     */
    List<SpecialOfferRtnVO> getSpecialOfferByRocId(String rocId, String userId);
    
    /**
     * 呼叫 CRMCustBizService API 查詢用戶 Profile. <br>
     * 
     * @param subscriberId
     * @param userId 使用者 NT Account
     * 
     * @return List<SCVAccountInfoRtnVO>
     */
    List<SCVAccountInfoRtnVO> getRecordBySubscrId(String subscriberId, String userId);
    
    /**
     * 呼叫 CRMCustBizService API 查詢歷史客資. <br>
     * 
     * @param rocId 身分證號碼
     * @param userId 使用者 NT Account
     * 
     * @return IdViewInfoRtnVO
     */
    IdViewInfoRtnVO getIdViewInfoByRocId(String rocId, String userId);
    
}
