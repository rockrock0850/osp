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

package com.fet.crm.osp.kernel.core.service.system;

/**
 * 系統全域參數 服務介面. <br>
 * 
 * @author RichardHuang
 */
public interface ISysGlobalConfigService {

	/**
	 * 根據系統參數編號，取得參數設定值. <br>
	 * 
	 * @param confId
	 * 			系統參數編號
	 * @return String
	 */
	String getSysConfValue(String confId);
	
}
