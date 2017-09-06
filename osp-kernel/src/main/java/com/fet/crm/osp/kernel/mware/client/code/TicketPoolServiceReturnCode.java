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

package com.fet.crm.osp.kernel.mware.client.code;

/**
 * ESB TicketPoolService 網路服務 回傳代碼與訊息
 * 
 * @author RichardHuang
 */
public enum TicketPoolServiceReturnCode {

	SUCCESS("ESB-006-901-03000", "SUCCESS"); // 執行成功
		
	private String code;
	private String message;

	private TicketPoolServiceReturnCode(String returnCode, String returnMessage) {
		this.code = returnCode;
		this.message = returnMessage;
	}
    
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
