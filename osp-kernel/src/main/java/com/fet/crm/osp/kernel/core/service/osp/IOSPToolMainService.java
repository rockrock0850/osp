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

package com.fet.crm.osp.kernel.core.service.osp;

import com.fet.crm.osp.common.vo.kernel.result.CustInfoForAppPartRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.SalesInfoVO;
import com.fet.crm.osp.common.vo.kernel.result.agentinfo.AgentInfoRtnVO;

/**
 * 面向OSP相關服務 介面. <br>
 * 
 * @author VJChou, RichardHuang
 */
public interface IOSPToolMainService {
    
    /**
     * 類型: 查詢. <br>
     * 透過業務人員代碼取得業務人員相關資訊(E-Mail). <br>
     * 查詢CEDS(eHR). <br>
     * 
     * @param empNo
     *            業務人員員工代碼
     * @return SalesInfoVO
     */
    SalesInfoVO querySalesInfo(String empNo);
    
    /**
     * 類型: 查詢. <br>
     * 根據 IVR Code 取得業務人員 ID. <br>
     * 
     * @param ivrCode
     * 
     * @return String
     */
    String getUserIdByIvrCode(String ivrCode);

    /**
     * 類型: 查詢. <br>
     * 
     * 取得 AppPart 啟動所需客資. <br>
     * 
     * @param ownId
     *              cache 識別編號
     * @param msisdn
     *              門號
     * @param idType
     *              證號類別
     * @param rocId
     *              證號
     * 
     * @return CustInfoForAppPartRtnVO
     */
    public CustInfoForAppPartRtnVO getCustInfoForAppPart(String ownId, String msisdn, String idType, String rocId);
    
    /**
     * 類型：查詢. <br>
     * 根據使用者 NT Account ID，取得 AppPart 所需使用者登入相關資訊. <br>
     * 
     * @param accountId
     * @return AgentInfoRtnVO
     */
    AgentInfoRtnVO getAgentInfo(String accountId);
    
    /**
     * 類型: 資料異動. <br>
     * 傳入OSP之前呼叫服務所代入的參數「ospKey」，將TxId更新回OSP. <br>
     * 
     * @return boolean
     */
    boolean updateTxIdByIdentifyId(String ospKey, String txId);
    
}
