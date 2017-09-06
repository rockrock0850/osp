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

package com.fet.crm.osp.platform.core.facade.cache;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.platform.core.service.cache.ICacheReferenceService;

/**
 * 
 * @author LawrenceLai, Adam Yeh
 */
@Service
public class CacheReferenceFacade {
	
	@Autowired
	private ICacheReferenceService cacheRefrenceService;
	
	/**
	 * 取得 日曆日資訊
	 * 
	 * @return List<String>
	 */
	@Transactional(readOnly = true)
	public List<String> getWorkDayCalList() {
		return cacheRefrenceService.queryWorkDayCalList();
	}
	
	/**
	 * 取得 工作日資訊
	 * 
	 * @return List<String>
	 */
	@Transactional(readOnly = true)
	public List<String> getCalendarDayCalList() {
		return cacheRefrenceService.queryCalendarDayCalList();
	}
	
	/**
	 * 取得 系統全域參數
	 * map.key[CONF_ID], map.value[CONF_VALUE]
	 * 
	 * @return Map<String, String>
	 */
	@Transactional(readOnly = true)
	public Map<String, String> getGlobalConfig() {
		return cacheRefrenceService.queryGlobalConfig();
	}


}
