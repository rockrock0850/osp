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

package com.fet.crm.osp.kernel.mware.client.ticketpool;

/**
 * TicketPoolService SOAP Client 介面
 * 
 * @author RichardHuang
 */
public interface ITicketPoolServiceClient {
    
    public final String TICKET_POOL_SERVICE_ENDPOINT_URL = "TICKET_POOL_SERVICE_ENDPOINT_URL";
    
    /**
     * 類型: 資料異動. <br>
	 * 呼叫 TicketPoolService API 更新工單資訊(狀態/處理人員)至 TicketPool [ORDER_MAIN_METADATA]. <br>
	 * 
	 * @param userId
     * @param poolKey
     * @param status
     * @param processUser
     * @param ntAccount
     * 
     * @return boolean
     */
    boolean updateTicketStatus(String userId, String poolKey, String status, String processUser, String ntAccount);
    
}
