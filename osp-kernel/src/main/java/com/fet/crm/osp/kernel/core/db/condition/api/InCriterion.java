/**
 * Copyright (c) 2016 Far Eastone Telecommunications Co., Ltd.
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

import java.util.Collections;
import java.util.List;

public class InCriterion implements Criterion {
	
	private String logicOp;
	private String column;
	private List<String> params;
	
	public InCriterion(String logicOp, String column, List<String> params) {
		this.logicOp = logicOp;
		this.column = column;
		this.params = params;
	}

	@Override
	public void processCriterion(CriterionBuilder cBuilder) {
		StringBuilder sb = new StringBuilder();
		sb.append(column);
		sb.append(logicOp);
		sb.append("(");
		sb.append(getConditionInStr(params));
		sb.append(")");
		
		String sqlRestriction = sb.toString();
		
		cBuilder.setSqlRestriction(sqlRestriction, Collections.<String,Object>emptyMap());
	}

	/**
	 * 產生[查詢條件 IN]語法
	 * 
	 * @param columns
	 * @return String
	 */
	public static String getConditionInStr(List<String> columns) {
		StringBuffer sqltext = new StringBuffer();

		for (int i = 0; i < columns.size(); i++) {
			if (i > 0) {
				sqltext.append(", ");
			}
			
			sqltext.append("'");
			sqltext.append(columns.get(i)); // 字串
			sqltext.append("'");
		}

		return sqltext.toString();
	}
	
}
