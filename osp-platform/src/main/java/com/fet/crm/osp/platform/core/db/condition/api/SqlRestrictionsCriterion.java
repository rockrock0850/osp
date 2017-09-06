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
package com.fet.crm.osp.platform.core.db.condition.api;

import java.util.Map;

/**
 * The Class SqlRestrictionsCriterion.
 */
public class SqlRestrictionsCriterion implements Criterion {

	private String sqlRestrictions;
	private Map<String, Object> params;

	/**
	 * Instantiates a new sql restrictions criterion.
	 * 
	 * @param sqlRestrictions
	 *            the sql restrictions
	 * @param params
	 *            the params
	 */
	public SqlRestrictionsCriterion(String sqlRestrictions, Map<String, Object> params) {
		this.sqlRestrictions = sqlRestrictions;
		this.params = params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fet.crm.sss.core.db.condition.api.Criterion#processCriterion(com.
	 * fet.crm.sams.core.db.condition.api.CriterionBuilder)
	 */
	@Override
	public void processCriterion(CriterionBuilder cBuilder) {
		cBuilder.setSqlRestriction(sqlRestrictions, params);
	}

}
