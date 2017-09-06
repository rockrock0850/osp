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

package com.fet.crm.osp.platform.core.service.cache.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.service.cache.ICacheReferenceService;

/**
 * [系統記憶體快取] 服務實作
 * 
 * @author LawrenceLai, Adam Yeh
 */
@Service
public class CacheReferenceServiceImpl implements ICacheReferenceService {

	@Autowired
	private JdbcDAO jdbcDAO;

	@Override
	public List<String> queryCalendarDayCalList() {
		List<Map<String, Object>> dataList = queryHrmCalendar();
		
		if (CollectionUtils.isNotEmpty(dataList)) {
			List<String> wDayCalList = new ArrayList<String>(); // 工作日
			
			for (Map<String, Object> indexMp : dataList) {
				String dtDateStr = MapUtils.getString(indexMp, "DT_DATE_STR");
				String isHollday = MapUtils.getString(indexMp, "IS_HOLLDAY");

				if ("N".equals(isHollday)) { // 非假日
					wDayCalList.add(dtDateStr);
				}
			}
			
			return wDayCalList;
		}
		
		return Collections.emptyList();
	}

	@Override
	public List<String> queryWorkDayCalList() {
		List<Map<String, Object>> dataList = queryHrmCalendar();
		
		if (CollectionUtils.isNotEmpty(dataList)) {
			List<String> cDayCalList = new ArrayList<String>(); // 日曆日
			
			for (Map<String, Object> indexMp : dataList) {
				String dtDateStr = MapUtils.getString(indexMp, "DT_DATE_STR");
				cDayCalList.add(dtDateStr);
			}
			
			return cDayCalList;
		}
		
		return Collections.emptyList();
	}

	@Override
	public Map<String, String> queryGlobalConfig() {
		String sqlText = "SELECT CONF_ID, CONF_VALUE FROM SYS_GLOBAL_CONFIG";
        
        List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqlText);
        
        if (CollectionUtils.isNotEmpty(dataList)) {
        	Map<String, String> gbConfigMap = new HashMap<>();
        	
        	for (Map<String, Object> dataMap : dataList) {
        		String key = MapUtils.getString(dataMap, "CONF_ID", "");
        		String val = MapUtils.getString(dataMap, "CONF_VALUE", "");
        		
        		gbConfigMap.put(key, val);
        	}
        	
        	return gbConfigMap;
        }
        
		return Collections.emptyMap();
	}
	
	private List<Map<String, Object>> queryHrmCalendar() {
		String sqlText = ResourceFileUtil.SQL.getResource("cache", "QUERY_HRM_CALENDAR");
		List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqlText);

		if (CollectionUtils.isNotEmpty(dataList)) {
			return dataList;
		}
		
		return Collections.emptyList();
	}
	
}
