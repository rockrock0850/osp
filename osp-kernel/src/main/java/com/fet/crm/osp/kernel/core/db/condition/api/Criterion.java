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
public interface Criterion {

	/**
	 * To sql string.
	 *
	 * @param cBuilder the c builder
	 */
	public void processCriterion(CriterionBuilder cBuilder);
	
}
