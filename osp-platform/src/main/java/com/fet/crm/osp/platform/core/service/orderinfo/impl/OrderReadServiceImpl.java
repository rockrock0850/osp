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

package com.fet.crm.osp.platform.core.service.orderinfo.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.common.comparator.TodoSortComparator;
import com.fet.crm.osp.platform.core.common.util.DateUtil;
import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.condition.Condition;
import com.fet.crm.osp.platform.core.db.condition.api.Restrictions;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.service.orderinfo.IOrderReadService;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.TodoOrderCVO;

/**
 * 訂單資訊檢視讀取」服務實作
 * 
 * @author LawrenceLai
 */
@Service
public class OrderReadServiceImpl implements IOrderReadService {
	
    @Autowired
    private JdbcDAO jdbcDAO;

	@Override
	public List<Map<String, Object>> queryDeptTodoOrderInfo(TodoOrderCVO todoOrderCVO, String userId) {
		if (todoOrderCVO != null && StringUtils.isNotBlank(userId)) {
			String orderMId				= todoOrderCVO.getOrderMId(); 			// OSP主要進件單號
			String orderTypeId		 	= todoOrderCVO.getOrderTypeId(); 		// 案件類型代號
			String sourceProdTypeId 	= todoOrderCVO.getSourceProdTypeId(); 	// 進件來源產品代號
			String msisdn				= todoOrderCVO.getMsisdn();				// 門號
			String custName				= todoOrderCVO.getCustName(); 			// 客戶名稱
			String sourceOrderId		= todoOrderCVO.getSourceOrderId(); 		// 來源系統單號
			
			Condition condition = new Condition();
			
			if (StringUtils.isNotBlank(orderMId)) {
				condition.and(Restrictions.eq("T1.ORDER_M_ID", orderMId));
			}
			if (StringUtils.isNotBlank(orderTypeId)) {
				condition.and(Restrictions.eq("T1.ORDER_TYPE_ID", orderTypeId));
			}
			if (StringUtils.isNotBlank(sourceProdTypeId)) {
				condition.and(Restrictions.eq("T1.SOURCE_PROD_TYPE_ID", sourceProdTypeId));
			}
			if (StringUtils.isNotBlank(msisdn)) {
				condition.and(Restrictions.eq("T1.MSISDN", msisdn));
			}
			if (StringUtils.isNotBlank(custName)) {
				condition.and(Restrictions.eq("T1.CUST_NAME", custName));
			}
			if (StringUtils.isNotBlank(sourceOrderId)) {
				condition.and(Restrictions.eq("T1.SOURCE_ORDER_ID", sourceOrderId));
			}
			
			String sqltext = ResourceFileUtil.SQL_ORDER.getResource("QUERY_DEPT_TODO_ORDER_INFO");
			
			sqltext = sqltext.replace("$P{USER_ID}", userId);
			
			sqltext = condition.getCompleteSQL(sqltext);
	    	Map<String, Object> params = condition.getParams();
	    	
	    	List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqltext, params);
	    	
	    	if (dataList != null && !dataList.isEmpty()) {
	    	   	for(Map<String, Object> data : dataList) {
	    	   		Date cspDate = (Date) data.get("CUST_SPECIFY_DATE");
		    		String cspDateStr = DateUtil.fromDate(cspDate, "yyyy-MM-dd");
		    		
		    		data.put("CUST_SPECIFY_DATE", cspDateStr);
		    	}
	    		
	    		dataList = setNullValueToEmptyString(dataList);
	    		
	    		Collections.sort(dataList, new TodoSortComparator());
	    		
	    		return dataList;
	    	}
		}
		
		return Collections.emptyList();
	}

	@Override
	public int queryDeptCriticalOrderCount(TodoOrderCVO todoOrderCVO, String userId) {
		if (todoOrderCVO != null && StringUtils.isNotBlank(userId)) {
			String orderMId				= todoOrderCVO.getOrderMId(); 			// OSP主要進件單號
			String orderTypeId		 	= todoOrderCVO.getOrderTypeId(); 		// 案件類型代號
			String sourceProdTypeId 	= todoOrderCVO.getSourceProdTypeId(); 	// 進件來源產品代號
			String msisdn				= todoOrderCVO.getMsisdn();				// 門號
			String custName				= todoOrderCVO.getCustName(); 			// 客戶名稱
			String sourceOrderId		= todoOrderCVO.getSourceOrderId(); 		// 來源系統單號
			
			Condition condition = new Condition();
			
			if (StringUtils.isNotBlank(orderMId)) {
				condition.and(Restrictions.eq("T1.ORDER_M_ID", orderMId));
			}
			if (StringUtils.isNotBlank(orderTypeId)) {
				condition.and(Restrictions.eq("T1.ORDER_TYPE_ID", orderTypeId));
			}
			if (StringUtils.isNotBlank(sourceProdTypeId)) {
				condition.and(Restrictions.eq("T1.SOURCE_PROD_TYPE_ID", sourceProdTypeId));
			}
			if (StringUtils.isNotBlank(msisdn)) {
				condition.and(Restrictions.eq("T1.MSISDN", msisdn));
			}
			if (StringUtils.isNotBlank(custName)) {
				condition.and(Restrictions.eq("T1.CUST_NAME", custName));
			}
			if (StringUtils.isNotBlank(sourceOrderId)) {
				condition.and(Restrictions.eq("T1.SOURCE_ORDER_ID", sourceOrderId));
			}
			
			String sqltext = ResourceFileUtil.SQL_ORDER.getResource("QUERY_DEPT_CRITICAL_ORDER_COUNT");
			
			sqltext = sqltext.replace("$P{USER_ID}", userId);
			
			sqltext = condition.getCompleteSQL(sqltext);
			Map<String, Object> params = condition.getParams();
			
			List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqltext, params);
			
			if (CollectionUtils.isNotEmpty(dataList)) {
				BigDecimal count = (BigDecimal) dataList.get(0).get("COUNT");
				
		    	return count.intValue();
			}
		}
		
		return 0;
	}

	@Override
	public int queryDeptOverdueOrderCount(TodoOrderCVO todoOrderCVO, String userId) {
		if (todoOrderCVO != null && StringUtils.isNotBlank(userId)) {
			String orderMId				= todoOrderCVO.getOrderMId(); 			// OSP主要進件單號
			String orderTypeId		 	= todoOrderCVO.getOrderTypeId(); 		// 案件類型代號
			String sourceProdTypeId 	= todoOrderCVO.getSourceProdTypeId(); 	// 進件來源產品代號
			String msisdn				= todoOrderCVO.getMsisdn();				// 門號
			String custName				= todoOrderCVO.getCustName(); 			// 客戶名稱
			String sourceOrderId		= todoOrderCVO.getSourceOrderId(); 		// 來源系統單號
			String processUserId		= todoOrderCVO.getProcessUserId();		// 處理人員編號
			
			Condition condition = new Condition();
			
			if (StringUtils.isNotBlank(orderMId)) {
				condition.and(Restrictions.eq("T1.ORDER_M_ID", orderMId));
			}
			if (StringUtils.isNotBlank(orderTypeId)) {
				condition.and(Restrictions.eq("T1.ORDER_TYPE_ID", orderTypeId));
			}
			if (StringUtils.isNotBlank(sourceProdTypeId)) {
				condition.and(Restrictions.eq("T1.SOURCE_PROD_TYPE_ID", sourceProdTypeId));
			}
			if (StringUtils.isNotBlank(msisdn)) {
				condition.and(Restrictions.eq("T1.MSISDN", msisdn));
			}
			if (StringUtils.isNotBlank(custName)) {
				condition.and(Restrictions.eq("T1.CUST_NAME", custName));
			}
			if (StringUtils.isNotBlank(sourceOrderId)) {
				condition.and(Restrictions.eq("T1.SOURCE_ORDER_ID", sourceOrderId));
			}
			if (StringUtils.isNotBlank(processUserId)) {
				condition.and(Restrictions.eq("T1.PROCESS_USER_ID", processUserId));
			}
			
			String sqltext = ResourceFileUtil.SQL_ORDER.getResource("QUERY_DEPT_OVERDUE_ORDER_COUNT");
			
			sqltext = sqltext.replace("$P{USER_ID}", userId);
			
			sqltext = condition.getCompleteSQL(sqltext);
			Map<String, Object> params = condition.getParams();
			
			List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqltext, params);
			
			if (CollectionUtils.isNotEmpty(dataList)) {
				BigDecimal count = (BigDecimal) dataList.get(0).get("COUNT");
				
		    	return count.intValue();
			}
		}
		
		return 0;
	}
	
	private List<Map<String, Object>> setNullValueToEmptyString(List<Map<String, Object>> dataList) {
		for (Map<String, Object> map : dataList) {
			for (String key : map.keySet()) {
				Object o = map.get(key);
				
				if (o == null) {
					map.put(key, "");
				}
			}
		}
		
		return dataList;
	}
	
}