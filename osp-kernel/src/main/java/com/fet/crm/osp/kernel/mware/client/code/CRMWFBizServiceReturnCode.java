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
 * ESB CRMWFBizService 網路服務 回傳代碼與訊息
 * 
 * @author RichardHuang
 */
public enum CRMWFBizServiceReturnCode {

	SUCCESS("ESB-006-030-01000", "Success"), // 執行成功
	AUTH_ERROR("ESB-006-030-01150", "未有符合授權規則"),
	EMPTY_INPUT_ERROR("ESB-006-030-01010", "Error input value"), // 輸入參數錯誤
	FLOW_TYPE_ERROR("ESB-006-030-01060", "簽核等級及類型錯誤"),
	NO_EMPLOYEE_DATA("ESB-006-030-03060", "查詢員工資料不存在");
		
    private String code;
    private String message;

	private CRMWFBizServiceReturnCode(String returnCode, String returnMessage) {
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
