package com.fet.crm.osp.platform.core.db.condition.api;

import java.util.HashMap;
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
		
		cBuilder.setSqlRestriction(sqlRestriction, new HashMap<String, Object>());
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
