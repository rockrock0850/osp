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

package com.fet.crm.osp.common.vo.kernel.input.param;

/**
 * 更新工單資訊(狀態/處理人員)至 TicketPool 的 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 * 
 * @author RichardHuang
 */
public class OrderParamVO extends AbstractESBParamVO {
	
	private String poolKey; // 工單池所產生的鍵值
    private String status; // 工單狀態
    private String processUser; // 處理人員

	/**
	 * 取得工單池所產生的鍵值
	 * 
	 * @return the poolKey
	 */
	public String getPoolKey() {
		return poolKey;
	}

	/**
	 * 設定工單池所產生的鍵值
	 * 
	 * @param poolKey the poolKey to set
	 */
	public void setPoolKey(String poolKey) {
		this.poolKey = poolKey;
	}

	/**
	 * 取得工單狀態
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 設定工單狀態
	 * 
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 取得處理人員
	 * 
	 * @return the processUser
	 */
	public String getProcessUser() {
		return processUser;
	}

	/**
	 * 設定處理人員
	 * 
	 * @param processUser the processUser to set
	 */
	public void setProcessUser(String processUser) {
		this.processUser = processUser;
	}

}
