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

package com.fet.crm.osp.kernel.mware.client.getcommentfromitt;

/**
 * 查詢 ITT 備註資訊服務 SOAP Client 介面
 * 
 * @author RichardHuang
 */
public interface IQueryTicketDataServiceClient {
    
    public final String QUERY_TICKET_DATA_SERVICE_ENDPOINT_URL = "QUERY_TICKET_DATA_SERVICE_ENDPOINT_URL";
    public final long NOTE_TYPE = 1;
    
    public final String TROUBLE_REASON_ONE_NAME = "troubleReasonOneName"; // 問題原因 第一層
    public final String TROUBLE_REASON_TWO_NAME = "troubleReasonTwoName"; // 問題原因 第二層
    public final String TROUBLE_REASON_THREE_NAME = "troubleReasonThreeName"; // 問題原因 第三層
    public final String PROC_RESULT_NAME = "procResultName"; // 處理結果
    
    /**
     * 呼叫 QueryTicketData API 查詢 ITT 備註資訊. <br>
     * 
     * @param sourceOrderId 原始系統工單號
     * 
     * @return ContractAndBillingInfo
     */
    String queryTicketData(String sourceOrderId);
    
}
