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

package com.fet.crm.osp.platform.core.common.comparator;

import java.util.Comparator;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

/**
 * 
 * @author Lawrence.Lai
 */
public class TodoSortComparator implements Comparator<Map<String, Object>> {

	@Override
	public int compare(Map<String, Object> dataMap1, Map<String, Object> dataMap2) {
		String criticalFlag1 = MapUtils.getString(dataMap1, "CRITICAL_FLAG");
		String criticalFlag2 = MapUtils.getString(dataMap2, "CRITICAL_FLAG");
		
		if ("Y".equals(criticalFlag1) && "N".equals(criticalFlag2)) {
			return -1;
		}
		
		if ("N".equals(criticalFlag1) && "Y".equals(criticalFlag2)) {
			return 1;
		}
		
		String overdueFlag1 = MapUtils.getString(dataMap1, "OVERDUE_FLAG");
		String overdueFlag2 = MapUtils.getString(dataMap2, "OVERDUE_FLAG");
		
		if ("Y".equals(overdueFlag1) && "N".equals(overdueFlag2)) {
			return -1;
		}
		
		if ("N".equals(overdueFlag1) && "Y".equals(overdueFlag2)) {
			return 1;
		}
		
		double overdueTime1 = Double.valueOf(MapUtils.getString(dataMap1, "OVERDUE_TIME"));
		double overdueTime2 = Double.valueOf(MapUtils.getString(dataMap2, "OVERDUE_TIME"));
		
		if (overdueTime1 < overdueTime2) {
			return -1;
		}
		
		return 0;
	}

}

