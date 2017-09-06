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

package com.fet.crm.osp.common.exception.code;

/**
 * 取得錯誤訊息代碼、文字 介面
 * 
 * @author PaulChen
 */
public interface IExceptionCode {

	/**
	 * 取得系統錯誤代碼
	 * 
	 * @return String
	 */
	public String getErrorCode();
	
	/**
	 * 取得系統錯誤訊息
	 * 
	 * @return String
	 */
	public String getErrorMessage();
	
}
