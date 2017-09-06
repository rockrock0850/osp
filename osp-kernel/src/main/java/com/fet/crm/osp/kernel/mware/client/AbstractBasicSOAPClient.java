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

package com.fet.crm.osp.kernel.mware.client;

import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.core.service.system.ISysGlobalConfigService;

/**
 * 基本 SOAP Client 抽象類別. <br>
 * 
 * @author RichardHuang
 */
public abstract class AbstractBasicSOAPClient {
    
	@Autowired
	private ISysGlobalConfigService configService;
	
	/**
	 * 根據系統參數 ID, 取得 SOAP WS Endpoint URL. <br>
	 * 
	 * @param confId
	 * 			系統參數 ID
	 * @return String
	 */
	protected String getSysConfValueByConfId(String confId) {
		return configService.getSysConfValue(confId);
	}
	
	/**
	 * 取得呼叫 ESB API 所需傳入的 password
	 * 
	 * @return String
	 */
	protected String getAPIPassword() {
		return getSysConfValueByConfId(Constant.ESB_API_PASSWORD);
	}
	
	/**
	 * 取得呼叫 ESB API 所需傳入的 userID
	 * 
	 * @return String
	 */
//	protected String getAPIUserId() {
//		return getSysConfValueByConfId(Constant.ESB_API_USER_ID);
//	}
	
}
