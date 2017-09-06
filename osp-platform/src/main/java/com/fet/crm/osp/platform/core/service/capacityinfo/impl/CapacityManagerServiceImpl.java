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

package com.fet.crm.osp.platform.core.service.capacityinfo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.db.util.SqlTextUtil;
import com.fet.crm.osp.platform.core.service.capacityinfo.ICapacityManagerService;

/**
 *  [產能運算管理] 服務介面實作
 * 
 * @author LawrenceLai
 */
@Service
public class CapacityManagerServiceImpl implements ICapacityManagerService {
	
	private static final String sqlStartTime = "00:00:00";
	private static final String sqlEndTime = "23:59:59";
	private static final String space = " ";
	
	@Autowired
	private JdbcDAO jdbcDAO;

	@Override
	public List<Map<String, Object>> queryAgentCapacityInfo(List<String> agentList, String startDate, String endDate) {
		List<Map<String, Object>> capacityInfoList = new ArrayList<>();
		
		// STEP.0  取得產能樣板
		List<Map<String, Object>> agentRefence = queryAgentRefrence(agentList);
		List<Map<String, Object>> dateRangeList = getDateRangeForCapacity(startDate, endDate);
		
		for (Map<String, Object> dateRange : dateRangeList) {
			String dtDate = MapUtils.getString(dateRange, "DT_DATE_STR");
			
			for (String userId : agentList) {
				Map<String, Object> capacityTmpMap = new HashMap<>();
				capacityTmpMap.put("USER_ID", userId);
				capacityTmpMap.put("DT_DATE", dtDate);
				
				for (Map<String, Object> refMap : agentRefence) {
					String empNo = MapUtils.getString(refMap, "EMPNO");
					String empName = MapUtils.getString(refMap, "EMPNAME");
					
					if (userId.equals(empNo)) {
						capacityTmpMap.put("USER_NAME", empName);
					}
				}
				
				capacityInfoList.add(capacityTmpMap);
			}
		}
		
		// STEP.1 取得產能運算資料
		List<Map<String, Object>> routineCapacity = queryAgentRoutineCapacity(agentList, startDate, endDate);
		List<Map<String, Object>> orderCapacity = queryAgentOrderCapacity(agentList, startDate, endDate);
		List<Map<String, Object>> processCapacity = queryAgentProcessOrderCapacity(agentList);
		List<Map<String, Object>> dispatchCapacity = queryAgentDispatchOrderCapacity(agentList);
		
		for (Map<String, Object> capacityProcessMap : capacityInfoList) {
			String userId = MapUtils.getString(capacityProcessMap, "USER_ID");
			String dtDate = MapUtils.getString(capacityProcessMap, "DT_DATE");
			
			int realKPI = 0;		// COLUMN.1 實際完成產能
			int processKPI = 0; 	// COLUMN.2 個人待處理產程
			int KPISum = 0;			// COLUMN.3 前兩者加總產能
			int dispatchKPI = 0;	// COLUMN.4 當日已派件的預計處理產能
			
			for (Map<String, Object> routineMap : routineCapacity) {
				String routineUser = MapUtils.getString(routineMap, "PROCESS_USER_ID");
				String routineDate = MapUtils.getString(routineMap, "KPI_DATE");
				int routineKPI = MapUtils.getIntValue(routineMap, "KPI_SUM");
				
				if (userId.equals(routineUser) && dtDate.equals(routineDate)) {
					realKPI = realKPI + routineKPI;
				}
			}
			
			for (Map<String, Object> orderMap : orderCapacity) {
				String orderUser = MapUtils.getString(orderMap, "PROCESS_USER_ID");
				String orderDate = MapUtils.getString(orderMap, "KPI_DATE");
				int orderKPI = MapUtils.getIntValue(orderMap, "KPI_SUM");
				
				if (userId.equals(orderUser) && dtDate.equals(orderDate)) {
					realKPI = realKPI + orderKPI;
				}
			}
			
			for (Map<String, Object> processMap : processCapacity) {
				String processUser = MapUtils.getString(processMap, "PROCESS_USER_ID");
				String processDate = MapUtils.getString(processMap, "KPI_DATE");
				int tmpProcessKPI = MapUtils.getIntValue(processMap, "KPI_SUM");
				
				if (userId.equals(processUser) && dtDate.equals(processDate)) {
					processKPI = processKPI + tmpProcessKPI;
				}
			}
			
			for (Map<String, Object> dispatchMap : dispatchCapacity) {
				String dispatchser = MapUtils.getString(dispatchMap, "PROCESS_USER_ID");
				String dispatchDate = MapUtils.getString(dispatchMap, "KPI_DATE");
				int tmpDrocessKPI = MapUtils.getIntValue(dispatchMap, "KPI_SUM");
				
				if (userId.equals(dispatchser) && dtDate.equals(dispatchDate)) {
					dispatchKPI = dispatchKPI + tmpDrocessKPI;
				}
			}
			
			KPISum = realKPI + processKPI;
			
			capacityProcessMap.put("REAL_KPI", realKPI);
			capacityProcessMap.put("PROCESS_KPI", processKPI);
			capacityProcessMap.put("KPI_SUM", KPISum);
			capacityProcessMap.put("DISPATCH_KPI", dispatchKPI);
		}
		
		return capacityInfoList;
	}
	
	private List<Map<String, Object>> queryAgentRoutineCapacity(List<String> agentList, String startDate, String endDate) {
		String sqlText = ResourceFileUtil.SQL.getResource("capacity", "QUERY_AGENT_ROUTINE_CAPACITY");
		
		String agentIn = SqlTextUtil.getConditionInStr(agentList);
		sqlText = sqlText.replace("$P{AGENT_IN}", agentIn);
		
		Map<String, Object> params = new HashMap<>();
		params.put("START_DATE", startDate.concat(space + sqlStartTime));
		params.put("END_DATE", endDate.concat(space + sqlEndTime));
		
		List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqlText, params);
		
		if (!CollectionUtils.isEmpty(dataList)) {
			return dataList;
		}
		
		return Collections.emptyList();
	}
	
	private List<Map<String, Object>> queryAgentOrderCapacity(List<String> agentList, String startDate, String endDate) {
		String sqlText = ResourceFileUtil.SQL.getResource("capacity", "QUERY_AGENT_ORDER_CAPACITY");
		
		String agentIn = SqlTextUtil.getConditionInStr(agentList);
		sqlText = sqlText.replace("$P{AGENT_IN}", agentIn);
		
		Map<String, Object> params = new HashMap<>();
		params.put("START_DATE", startDate.concat(space + sqlStartTime));
		params.put("END_DATE", endDate.concat(space + sqlEndTime));
		
		List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqlText, params);
		
		if (!CollectionUtils.isEmpty(dataList)) {
			return dataList;
		}
		
		return Collections.emptyList();
	}
	
	private List<Map<String, Object>> queryAgentProcessOrderCapacity(List<String> agentList) {
		String sqlText = ResourceFileUtil.SQL.getResource("capacity", "QUERY_AGENT_PROCESS_ORDER_CAPACITY");
		
		String agentIn = SqlTextUtil.getConditionInStr(agentList);
		sqlText = sqlText.replace("$P{AGENT_IN}", agentIn);
		
		List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqlText);
		
		if (!CollectionUtils.isEmpty(dataList)) {
			return dataList;
		}
		
		return Collections.emptyList();
	}
	
	private List<Map<String, Object>> queryAgentDispatchOrderCapacity(List<String> agentList) {
		String sqlText = ResourceFileUtil.SQL.getResource("capacity", "QUERY_AGENT_DISPATCH_ORDER_CAPACITY");
		
		String agentIn = SqlTextUtil.getConditionInStr(agentList);
		sqlText = sqlText.replace("$P{AGENT_IN}", agentIn);
		
		List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqlText);
		
		if (!CollectionUtils.isEmpty(dataList)) {
			return dataList;
		}
		
		return Collections.emptyList();
	}
	
	private List<Map<String, Object>> getDateRangeForCapacity(String startDate, String endDate) {
		String sqlText = ResourceFileUtil.SQL.getResource("capacity", "QUERY_DATE_RANGE_FOR_CAPACITY");
		
		Map<String, Object> params = new HashMap<>();
		params.put("START_DATE", startDate);
		params.put("END_DATE", endDate);
		
		List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqlText, params);
		
		if (!CollectionUtils.isEmpty(dataList)) {
			return dataList;
		}
		
		return Collections.emptyList();
	}
	
	private List<Map<String, Object>> queryAgentRefrence(List<String> agentList) {
		String sqlText = "SELECT * FROM HRM_MEMBER WHERE EMPNO IN ( $P{AGENT_IN} )";
		
		String agentIn = SqlTextUtil.getConditionInStr(agentList);
		sqlText = sqlText.replace("$P{AGENT_IN}", agentIn);
		
		List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqlText);
		
		if (!CollectionUtils.isEmpty(dataList)) {
			return dataList;
		}
		
		return Collections.emptyList();
	}
	
}
