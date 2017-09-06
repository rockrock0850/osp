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
public class PropertyCriterion implements Criterion {

	private String columnA;
	private String columnB;
	private String logicOp;
	
	/**
	 * Instantiates a new property criterion.
	 *
	 * @param logicOp the logic op
	 * @param columnA the column a
	 * @param columnB the column b
	 */
	PropertyCriterion(String logicOp, String columnA, String columnB) {
		this.columnA = columnA;
		this.columnB = columnB;
		this.logicOp = logicOp;
	}
	
	@Override
	public void processCriterion(CriterionBuilder cBuilder) {
		cBuilder.setSqlStringAndParams(columnA, columnB, logicOp);;
	}

}
