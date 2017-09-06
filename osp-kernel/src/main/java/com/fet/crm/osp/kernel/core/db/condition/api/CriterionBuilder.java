/**
 * Copyright (c) 2013 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */
package com.fet.crm.osp.kernel.core.db.condition.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.fet.crm.osp.kernel.core.common.util.JsonUtil;
import com.fet.crm.osp.kernel.core.common.util.TemplateReplacementUtil;
import com.fet.crm.osp.kernel.core.logger.CoreLoggerFactory;

/**
 * 
 * @author Paul
 */
public class CriterionBuilder {
		
	private Logger logger = CoreLoggerFactory.getLogger();
	
	private StringBuffer sql = new StringBuffer("( 1 = 1 )");
	private Map<String, Object> sqlParamMp = new HashMap<String, Object>();
	private String space = " ";
	
	private String conditionToken = "CONDITION";

	protected CriterionBuilder() {
		
	}
	
	
	// ====================== getter / setter
	public void setConditionToken(String conditionToken) {
		this.conditionToken = conditionToken;
	}

	// ====================== tools method
	/**
	 * 組出SQL，如： NAME = NAME_B
	 */
	protected void setSqlStringAndParams(String colA, String colB, String logicOp) {
		sql.append(space);
		sql.append(colA.trim());
		sql.append(space);
		sql.append(logicOp);
		sql.append(space);
		sql.append(colB.trim());
	}
	
	/**
	 * 組出SQL，如： NAME = :NAME
	 */
	protected void setSqlStringAndParams(String col, Object value, String logicOp) {
		String key = col;
		// 應該只會有一個參數是一樣的吧！！！
		if(sqlParamMp.containsKey(key)) {
			key += "_1";
		}
		
		sql.append(space);
		sql.append(col);
		sql.append(space);
		sql.append(logicOp);
		sql.append(space);
		sql.append(":" + key);
		
		sqlParamMp.put(key, value);
	}
	
	/**
	 * 自定SQL查詢子句
	 */
	protected void setSqlRestriction(String sqlRestriction, Map<String, Object> sqlParams) {
		sql.append(space);
		sql.append(sqlRestriction);
		
		sqlParamMp.putAll(sqlParams);
	}
	
	/**
	 * 定義 AND 或 OR
	 */
	protected void setRelationalOp(String rOp) {
		sql.append(rOp);
	}
	
	/**
	 * 取得完整SQL
	 */
	public String getCompleteSQL(String fragmentSQL) {
		Map<String, String> templateMp = new HashMap<String, String>();
		templateMp.put(conditionToken, sql.toString());
		
		String completeSQL = TemplateReplacementUtil.replacement(fragmentSQL, templateMp);
		
		logger.info(" completeSQL = " + completeSQL);
		return completeSQL;
	}
	
	/**
	 * 取得 PreparedStatment 所須的參數
	 */
	public Map<String, Object> getParams() {
		logger.info(" SQL params = " + JsonUtil.toJson(sqlParamMp));
		
		return sqlParamMp;
	}
	
	public void addGroupBy(String... groupCol) {
		sql.append(" GROUP BY ");
		for(String col : groupCol) {
			sql.append(col);
			sql.append(" ,");
		}
		
		// 移除掉最後一個「,」
		sql.replace(sql.length() - 1, sql.length(), "");
	}
	
	/**
	 * 加入排序欄位
	 * 
	 * @param orderCol
	 */
	public void addOrder(String orderCol) {
		addOrder(orderCol, Order.DESC);
	}
	
	/**
	 * 加入排序欄位以及升冪或降冪
	 * 
	 * @param orderCol
	 */
	public void addOrder(String orderCol, Order order) {
		sql.append(space);
		sql.append("ORDER BY");
		sql.append(space);
		sql.append(orderCol);
		sql.append(space);
		sql.append(order.getValue());
	}
	
}
