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
 * ESB CRMOEBizService 網路服務 回傳代碼與訊息
 * 
 * @author RichardHuang
 */
public enum CRMOEBizServiceReturnCode {

	SUCCESS("ESB-006-029-01000", "Success"), // 執行成功
	SUCCESS_WITH_WARNING("ESB-006-029-01002", "Success with warnings"),
	EMPTY_INPUT_ERROR("ESB-006-029-01010", "Error empty input"); // 輸入參數為空值
		
    private String code;
    private String message;

	private CRMOEBizServiceReturnCode(String returnCode, String returnMessage) {
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
