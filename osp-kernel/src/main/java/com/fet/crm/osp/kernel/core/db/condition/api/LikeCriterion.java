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
public class LikeCriterion implements Criterion {

	private String column;
	private String value;
	private String logicOp;
	private int position;
	
	static final int AROUND = 0;
	static final int START  = 1;
	static final int END    = 2;
	
	/**
	 * Instantiates a new like criterion.
	 *
	 * @param logicOp the logic op
	 * @param column the column
	 * @param value the value
	 * @param position the position
	 */
	LikeCriterion(String logicOp, String column, String value, int position) {
		this.column = column;
		this.value = value;
		this.logicOp = logicOp;
		this.position = position;
	}
	
	@Override
	public void processCriterion(CriterionBuilder cBuilder) {
		StringBuffer tValue = new StringBuffer(value);
		
		if((position == AROUND || position == START) && !value.startsWith("%")) {
			tValue.insert(0, "%");
		}
		
		if((position == AROUND || position == END) && !value.endsWith("%")) {
			tValue.insert(tValue.length(), "%");
		}
		
		cBuilder.setSqlStringAndParams(column, (Object) tValue.toString(), logicOp);
	}

}
