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


/**
 *	
 * @author Paul
 */
public class SimpleCriterion implements Criterion {

	private String logicOp;
	private String column;
	private Object value;
	
	/**
	 * 建構子.
	 *
	 * @param logicOp the logic op
	 * @param column the column
	 * @param value the value
	 */
	SimpleCriterion(String logicOp, String column, Object value) {
		this.logicOp = logicOp;
		this.column = column;
		this.value = value;
	}

	@Override
	public void processCriterion(CriterionBuilder cBuilder) {
		cBuilder.setSqlStringAndParams(column, value, logicOp);
	}

}
