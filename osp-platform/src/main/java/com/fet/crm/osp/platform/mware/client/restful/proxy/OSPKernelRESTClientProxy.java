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

package com.fet.crm.osp.platform.mware.client.restful.proxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.common.vo.kernel.result.SOATicketDetailRtnVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.agent.AgentInfoVO;
import com.fet.crm.osp.platform.mware.client.restful.service.OSPKernelRESTClient;
import com.fet.crm.osp.platform.mware.client.vo.AddCIEMasterReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.AddCIEMasterVO;
import com.fet.crm.osp.platform.mware.client.vo.AuthLevelInfoReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.AuthLevelInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.CacheSubscriberInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.CustBillingInfoReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.CustBillingInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.CustInfoForAppPartReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.CustInfoForOSPReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.IdViewInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.NEInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.OrderLoadReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.OrderLoadVO;
import com.fet.crm.osp.platform.mware.client.vo.PromotionDetailReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.SpecialOfferVO;

/**
 * [OSP Kerne RESTful]用戶端服務 代理控制器. <br>
 * 
 * @author Lawrence.Lai
 */
@Service
public class OSPKernelRESTClientProxy {

    @Autowired
    private OSPKernelRESTClient ospKernelRESTClient;

    /**
     * 類型: 資料異動. <br>
     * 新增 CIE Master 資料. <br>
     * 
     * @param addCIEMasterParamVO POST 請求參數
     *          
     * @return AddCIEMasterOutputVO 回傳結果資訊封裝物件
     */
    public AddCIEMasterReturnVO addCIEMaster(AddCIEMasterVO paramVO, String ntAccount) {
        return ospKernelRESTClient.addCIEMaster(paramVO, ntAccount);
    }


    /**
     * 類型: 資料異動. <br>
     * 將各OSP系統建檔抛轉進來的工單再次處理，並寫入TicketPool[ORDER_MAIN_METADATA]. <br>
     * 
     * @param OrderLoadVO 
     *          POST 請求參數
     *          
     * @return OrderLoadReturnVO 回傳結果資訊封裝物件
     */
	public OrderLoadReturnVO syncOrder2TicketPoolFromOSP(OrderLoadVO paramVO) {
        return ospKernelRESTClient.syncOrder2TicketPoolFromOSP(paramVO);
	}
    
    /**
     * 類型: 查詢. <br>
     * 
     * [從ITT取得備註資訊]<br>
     * 當前台(osp-platform)進行結案動作(有效件/無效件)時，且系統來源(from sourceSysId = 'ITT' )，
     * 觸發前端呼叫此服務。透過此服務再次呼叫 ITT 系統所提供的API，將回傳的欄位組成字串，回傳前端。
     * <br>
     * 做為前端存入OSP的工單備註欄位資訊. <br>
     * 
     * @param sourceOrderId POST 請求參數 - 原始系統工單號
     * @return StringOutputVO 回傳結果資訊封裝物件
     */
    public String getCommentFromITT(String sourceOrderId) {
        return ospKernelRESTClient.getCommentFromITT(sourceOrderId);
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得 NE Info. <br>
     * 
     * @param promotionId POST 請求參數 - 原始系統工單號
     * @return StringOutputVO 回傳結果資訊封裝物件
     */
    public PromotionDetailReturnVO getPromotionDetail(String promotionId) {
        return ospKernelRESTClient.getPromotionDetail(promotionId);
    }

    /**
     * 類型: 查詢. <br>
     * 查詢/異動條件. <br>
     * 
     * @param paramVO
     *              POST 請求參數
     * @return String
     */
    public String getNEInfo(NEInfoVO neInfoParamVO, String ntAccount) {
        return ospKernelRESTClient.getNEInfo(neInfoParamVO, ntAccount);
    }

    /**
     * 類型: 查詢. <br>
     * 取得簽核層級與人員資訊. <br>
     * 
     * @param authLevelInfoParamVO
     *              POST 請求參數
     * @return AuthLevelOutputVO
     *              回傳結果資訊封裝物件
     */
    public AuthLevelInfoReturnVO getAuthLevelInfo(AuthLevelInfoVO authLevelInfoParamVO, String ntAccount) {
        return ospKernelRESTClient.getAuthLevelInfo(authLevelInfoParamVO, ntAccount);
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得 SOA 進件詳細資訊. <br>
     * 
     * @param msisdns
     *              POST 請求參數 - 門號
     * @return SOATicketDetailOutputVO
     *              回傳結果資訊封裝物件
     */
    public List<SOATicketDetailRtnVO> getSOATicketDetail(List<String> msisdns, String ntAccount) {
        return ospKernelRESTClient.getSOATicketDetail(msisdns, ntAccount);
    }

    public boolean updateOrderStatus2TicketPool(String poolKey, String status, String processUser, String ntAccount) {
        return ospKernelRESTClient.updateOrderStatus2TicketPool(poolKey, status, processUser, ntAccount);
    }

    public String getSalesInfo(String empNo) {
        return ospKernelRESTClient.getSalesInfo(empNo);
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得 AppPart 啟動所需客資. <br>
     * 
     * @param custInfoForAppPartParamVO
     *              POST 請求參數 - 資訊封裝物件
     * @return CustInfoForAppPartOutputVO
     *              回傳結果資訊封裝物件
     */
    public CustInfoForAppPartReturnVO getCustInfoForAppPart(String ownId, String rocId, String idType, String msisdn) {
    	return ospKernelRESTClient.getCustInfoForAppPart(ownId, rocId, idType, msisdn);
    }

    public CustInfoForOSPReturnVO getCustInfoForOSP(String subscriberId, String ntAccount) {
        return ospKernelRESTClient.getCustInfoForOSP(subscriberId, ntAccount);
    }
    
    public CustBillingInfoReturnVO getCustBillingInfo(CustBillingInfoVO custBillingInfoVO, String ntAccount) {
        return ospKernelRESTClient.getCustBillingInfo(custBillingInfoVO, ntAccount);
    }   
    
    /**
     * 類型：查詢. <br>
     * 根據使用者 ntAccountId，取得 使用者登入相關資訊. <br>
     * 
     * @param ntAccountId
     * @return AgentInfoVO
     */
    public AgentInfoVO getAgentInfo(String ntAccount) {
        return ospKernelRESTClient.getAgentInfo(ntAccount);
    }

    public List<SpecialOfferVO> getSpecialOfferByRocId(String rocId, String ntAccount) {
    	return ospKernelRESTClient.getSpecialOfferByRocId(rocId, ntAccount);
    }

    /**
     * 類型: 查詢. <br>
     * 根據門號取得 Cache Subscriber Info 資訊. <br>
     * 
     * @param msisdn 
     *          POST 請求參數 - 門號
     *          
     * @return CacheSubscriberInfoVO
     */
    public CacheSubscriberInfoVO getCacheSubscriberInfoByMsisdn(String msisdn, String ntAccount) {
    	return ospKernelRESTClient.getCacheSubscriberInfoByMsisdn(msisdn, ntAccount);
    }
    
    /**
     * 類型: 查詢. <br>
     * 根據 IVR Code 取得業務人員 ID. <br>
     * 
     * @param ivrCode
     * @return String
     */
    public String getUserIdByIvrCode(String ivrCode) {
    	return ospKernelRESTClient.getUserIdByIvrCode(ivrCode);
    }

    /**
     * 類型: 查詢. <br>
     * 根據 paymentCategory 與 msisdn 取得sim. <br>
     * 
     * @param paymentCategory
     * @param msisdn
     * @return String
     */
    public String getSimIdByMsisdn(String paymentCategory, String msisdn, String ntAccount) {
        return ospKernelRESTClient.getSimIdByMsisdn(paymentCategory, msisdn, ntAccount);
    }

    /**
     * 類型: 查詢. <br>
     * 根據  rocId 查詢證號歷史. <br>
     * 
     * @param rocId
     * @return List<IdViewInfoVO>
     */
    public List<IdViewInfoVO> getIdViewInfoByRocId(String rocId, String ntAccount) {
    	return ospKernelRESTClient.getIdViewInfoByRocId(rocId, ntAccount);
    }
    
}