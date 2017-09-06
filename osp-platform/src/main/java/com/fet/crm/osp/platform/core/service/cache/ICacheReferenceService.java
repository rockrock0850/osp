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

package com.fet.crm.osp.platform.core.service.cache;

import java.util.List;
import java.util.Map;

/**
 * [系統記憶體快取] 服務介面
 * 
 * @author LawrenceLai
 */
public interface ICacheReferenceService {
	
	/**
	 * 取得 日曆日資訊
	 * 
	 * @return List<String>
	 */
	List<String> queryCalendarDayCalList();
	
	/**
	 * 取得 工作日資訊
	 * 
	 * @return List<String>
	 */
	List<String> queryWorkDayCalList();
	
	/**
	 * 取得 系統全域參數
	 * map.key[CONF_ID], map.value[CONF_VALUE]
	 * 
	 * @return Map<String, String>
	 */
	Map<String, String> queryGlobalConfig();

}
