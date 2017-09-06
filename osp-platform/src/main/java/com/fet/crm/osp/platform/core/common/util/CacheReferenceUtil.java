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

package com.fet.crm.osp.platform.core.common.util;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.facade.cache.CacheReferenceFacade;

/**
 * 
 * @author LawrenceLai, Adam Yeh
 */
@Component
public class CacheReferenceUtil {
	
	@Autowired
	private CacheReferenceFacade cacheRefrenceFacade;
	
	private static List<String> cDayCalList; 		// 日曆日
	private static List<String> wDayCalList; 		// 工作日
	private static Map<String, String> gbConfigMap; // 系統全域參數參照
	
	public List<String> getCDayCalList() {
		initDayCalList();
		
		return wDayCalList;
	}
	
	public List<String> getWDayCalList() {
		initDayCalList();
		
		return cDayCalList;
	}
	
	public String getGlobalConfigValueById(String confId) {
		initGbConfigMap();
		
		return gbConfigMap.get(confId);
	}
	
	private void initDayCalList() {
		if (wDayCalList == null || wDayCalList.isEmpty()) {
			wDayCalList = cacheRefrenceFacade.getWorkDayCalList();
		}
		if (cDayCalList == null || cDayCalList.isEmpty()) {
			cDayCalList = cacheRefrenceFacade.getCalendarDayCalList();
		}
	}
	
	private void initGbConfigMap() {
		if (gbConfigMap == null || gbConfigMap.isEmpty()) {
			gbConfigMap = cacheRefrenceFacade.getGlobalConfig();
		}
	}
	
}
