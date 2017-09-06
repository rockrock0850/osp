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
 * ESB CRMCustMDMBizService 網路服務 回傳代碼與訊息
 * 
 * @author RichardHuang
 */
public enum WebOSSServiceReturnCode {

	SUCCESS("ESB-007-001-03000", "SUCCESS"), // 執行成功
	SUCCESS_WITH_WARNING("ESB-007-001-03001", "Success with warnings"),
	INPUT_VALUE_ERROR("ESB-007-001-03011", "Error input value"); // 無效的輸入參數
		
	private String code;
	private String message;

	private WebOSSServiceReturnCode(String returnCode, String returnMessage) {
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
