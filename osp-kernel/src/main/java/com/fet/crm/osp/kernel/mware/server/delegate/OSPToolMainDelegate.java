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

package com.fet.crm.osp.kernel.mware.server.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.kernel.mware.server.proxy.OSPToolMainProxy;
import com.fet.crm.osp.kernel.mware.server.vo.process.ExContext;

/**
 * 面向OSP相關服務 資源管理/處理主要入口. <br>
 * 主要是生成後端唯一服務實例(singleton)，避免每一次連線連入的時候都生成一個服務實例. <br>
 * 
 * @author VJChou
 */
@Component
public class OSPToolMainDelegate {

    private static OSPToolMainProxy ospToolProxy;

    /**
     * 類型: 查詢. <br>
     * 透過業務人員代碼取得業務人員相關資訊(E-Mail). <br>
     * 查詢CEDS(eHR). <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext querySalesInfo(ExContext exContext) {
        return ospToolProxy.querySalesInfo(exContext);
    }
    
    /**
     * 類型: 查詢. <br>
     * 根據 IVR Code 取得業務人員 ID. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext getUserIdByIvrCode(ExContext exContext) {
        return ospToolProxy.getUserIdByIvrCode(exContext);
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
     * @param exContext
     * @return ExContext
     */
    public static ExContext getCommentFromITT(ExContext exContext) {
        return ospToolProxy.getCommentFromITT(exContext);
    }

    /**
     * 類型: 查詢. <br>
     * 取得 AppPart 啟動所需客資. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext getCustInfoForAppPart(ExContext exContext) {
        return ospToolProxy.getCustInfoForAppPart(exContext);
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得核資, 付款人與使用人相關資訊. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext getCustInfoForOSP(ExContext exContext) {
        return ospToolProxy.getCustInfoForOSP(exContext);
    }
    
    /**
     * 類型: 查詢. <br>
     * 
     * 取得帳務相關資訊. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext getCustBillingInfo(ExContext exContext) {
        return ospToolProxy.getCustBillingInfo(exContext);
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得 AppPart 所需使用者登入相關資訊. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext getAgentInfo(ExContext exContext) {
        return ospToolProxy.getAgentInfo(exContext);
    }

    /**
     * 類型: 查詢. <br>
     * 根據門號清單，取得 SOA 進件詳細資訊. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext getSOATicketDetail(ExContext exContext) {
        return ospToolProxy.getSOATicketDetail(exContext);
    }

    /**
     * 類型: 查詢. <br>
     * 取得簽核層級與人員資訊. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext getAuthLevelInfo(ExContext exContext) {
        return ospToolProxy.getAuthLevelInfo(exContext);
    }

    /**
     * 類型: 查詢. <br>
     * 取得學生溫暖長青申請方案相關資訊. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext getSpecialOfferByRocId(ExContext exContext) {
        return ospToolProxy.getSpecialOfferByRocId(exContext);
    }

    /**
     * 類型: 查詢. <br>
     * 取得 SPV 促代資訊. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext getPromotionDetail(ExContext exContext) {
        return ospToolProxy.getPromotionDetail(exContext);
    }

    /**
     * 類型: 查詢. <br>
     * 取得 NE Info. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext getNEInfo(ExContext exContext) {
        return ospToolProxy.getNEInfo(exContext);
    }

    /**
     * 類型: 查詢. <br>
     * 取得 SIM 卡號. <br>
     * 
     * @param exContext
     * 
     * @return ExContext
     */
    public static ExContext getSimIdByMsisdn(ExContext exContext) {
        return ospToolProxy.getSimIdByMsisdn(exContext);
    }

    /**
     * 類型: 查詢. <br>
     * 根據門號取得 Cache Subscriber Info 資訊. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext getCacheSubscriberInfoByMsisdn(ExContext exContext) {
        return ospToolProxy.getCacheSubscriberInfoByMsisdn(exContext);
    }
    
    /**
     * 類型: 查詢. <br>
     * 根據證號取得客資歷史資訊. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext getIdViewInfoByRocId(ExContext exContext) {
    	return ospToolProxy.getIdViewInfoByRocId(exContext);
    }

    /**
     * 類型: 資料異動. <br>
     * 新增 CIE Master 資料. <br>
     * 
     * @param exContext
     * 
     * @return ExContext
     */
    public static ExContext addCIEMaster(ExContext exContext) {
        return ospToolProxy.addCIEMaster(exContext);
    }

    /**
     * 類型: 資料異動. <br>
     * 傳入OSP之前呼叫服務所代入的參數「ospKey」，將TxId更新回OSP. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext updateTxIdByIdentifyId(ExContext exContext) {
        return ospToolProxy.updateTxIdByIdentifyId(exContext);
    }
    
    @Autowired
    public void setOspToolProxy(OSPToolMainProxy ospToolProxy) {
        OSPToolMainDelegate.ospToolProxy = ospToolProxy;
    }

}
