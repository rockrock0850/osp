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
package com.fet.crm.osp.platform.core.db.condition;

import com.fet.crm.osp.platform.core.db.condition.api.Criterion;
import com.fet.crm.osp.platform.core.db.condition.api.CriterionBuilder;

/**
 *
 * @author Paul
 */
public class Condition extends CriterionBuilder {
	
	/**
	 * And.
	 *
	 * @param criterion the criterion
	 * @return the criteria
	 */
	public Condition and(Criterion criterion) {
		setRelationalOp(" AND ");
		criterion.processCriterion(this);
		
		return this;
	}
	
	/**
	 * AND+左括弧
	 * 
	 * @param criterion the criterion
	 * @return the criteria
	 * @author Adam Yeh
	 */
	public Condition addAndWithOpenParenthesis(Criterion criterion) {
		setRelationalOp(" AND ( ");
		criterion.processCriterion(this);
		
		return this;
	}
	
	/**
	 * Or.
	 *
	 * @param criterion the criterion
	 * @return the criteria
	 */
	public Condition or(Criterion criterion) {
		setRelationalOp(" OR ");
		criterion.processCriterion(this);
		
		return this;
	}
	
	/**
	 * OR+左括弧
	 * 
	 * @param criterion the criterion
	 * @return the criteria
	 * @author Adam Yeh
	 */
	public Condition addOrWithOpenParenthesis(Criterion criterion) {
		setRelationalOp(" OR ( ");
		criterion.processCriterion(this);
		
		return this;
	}
	
	/**
	 * 增加右括弧
	 * 
	 * @author Adam Yeh
	 */
	public void addCloseParenthesis(){
		setRelationalOp(" ) ");
	}
	
}
