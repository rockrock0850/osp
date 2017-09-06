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
package com.fet.crm.osp.common.util;

/**
 * OSP RESTFul Web Services 公用程式
 * 
 * @author RichardHuang
 */
public enum OSPRESTFulWSUtil {
	// OSPToolMainRESTFulWS : 根據證號取得客資歷史資訊的 RESTful WS Name
	GET_IDVIEW_INFO_BY_ROCID("getIdViewInfoByRocId"),
	// OSPToolMainRESTFulWS : 根據 IVR Code 取得業務人員 ID 的 RESTful WS Name
    GET_USERID_BY_IVRCODE("getUserIdByIvrCode"),
    // OSPToolMainRESTFulWS : 取得 Cache Subscriber Info 相關資訊 RESTful WS Name
    GET_CACHE_SUBSCRIBER_INFO_BY_MSISDN("getCacheSubscriberInfoByMsisdn"),
    // OSPToolMainRESTFulWS : 新增 CIE 資訊 RESTful WS Name
    ADD_CIE_MASTER("addCIEMaster"),
    // OSPToolMainRESTFulWS : 將各OSP系統建檔抛轉進來的工單寫入TicketPool 的 RESTful WS Name
    SYNC_ORDER_TO_TICKET_POOL_FROM_OSP("syncOrder2TicketPoolFromOSP"),
    // OSPToolMainRESTFulWS : 取得促代相關資訊 RESTful WS Name
    GET_SPECIAL_OFFER_BY_ROCID("getSpecialOfferByRocId"),
    // OSPToolMainRESTFulWS : 取得促代相關資訊 RESTful WS Name
    GET_PROMOTION_DETAIL("getPromotionDetail"),
    // OSPToolMainRESTFulWS : 取得 NE Info RESTful WS Name
    GET_NE_INFO("getNEInfo"),
    // OSPToolMainRESTFulWS : 取得簽核層級與人員資訊 RESTful WS Name
    GET_AUTH_LEVEL_INFO("getAuthLevelInfo"),
    // OSPToolMainRESTFulWS : 從 ITT 取得備註資訊 RESTful WS Name
    GET_COMMENT_FROM_ITT("getCommentFromITT"),
    // OSPToolMainRESTFulWS : 取得業務人員相關資訊 RESTful WS Name
    QUERY_SALES_INFO("querySalesInfo"),
    // OSPToolMainRESTFulWS : 取得 OSP 核資相關資訊 RESTful WS Name
    GET_CUST_INFO_FOR_OSP("getCustInfoForOSP"),
    // OSPToolMainRESTFulWS : 取得 SIM 卡號 RESTful WS Name
    GET_SIMID_BY_MSISDN("getSimIdByMsisdn"),
    // OSPToolMainRESTFulWS : 取得帳務相關資訊 RESTful WS Name
    GET_CUST_BILLING_INFO("getCustBillingInfo"),
    // OSPToolMainRESTFulWS : 取得 AppPart 啟動所需客資 RESTful WS Name
    GET_CUST_INFO_FOR_APP_PART("getCustInfoForAppPart"),
    // OSPToolMainRESTFulWS : 取得使用者登入相關資訊 for AppPart RESTful WS Name
    GET_AGENT_INFO("getAgentInfo"),
    // OSPToolMainRESTFulWS : 取得 IdView 相關資訊 RESTful WS Name
    GET_ID_VIEW_INFO("getIdViewInfo"),
    // OSPPoolMainRESTFulWS : 案件分派 RESTful WS Name
    DISPATCH("dispatch"),
    // TicketPoolMainRESTFulWS : 更新工單資訊(狀態/處理人員)至 TicketPool RESTful WS Name
    UPDATE_ORDER_STATUS_TO_TICKETPOOL("updateOrderStatus2TicketPool"),
    // TicketPoolMainRESTFulWS : 取得 SOA 進件詳細資訊 RESTful WS Name
    GET_SOA_TICKET_DETAIL("getSOATicketDetail");
        
    private final String ospKernelHostUri = PropertiesUtil.getProperty("osp.kernel.domain.restful");
    private final String ospRESTfulPrefix = "/rest";
    private String serviceUri;

    private OSPRESTFulWSUtil(String serviceName) {
        this.serviceUri = ospKernelHostUri + ospRESTfulPrefix + "/" + serviceName;
    }
    
    public String getServiceUri() {
        return serviceUri;
    }
    
}
