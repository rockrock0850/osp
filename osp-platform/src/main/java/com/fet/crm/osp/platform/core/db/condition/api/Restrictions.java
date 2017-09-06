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

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Paul
 */
public abstract class Restrictions {
	
	/**
	 * ex: IN ('PAUL1', 'PAUL2')
	 */
	public static Criterion in(String col, List<String> value) {
		return new InCriterion(" IN ", col, value);
	}
	
	/**
	 * ex: NAME LIKE '%PAUL%'
	 */
	public static Criterion like(String col, String value) {
		return new LikeCriterion(" LIKE ", col, value, LikeCriterion.AROUND);
	}
	
	/**
	 * ex: NAME LIKE '%PAUL'
	 */
	public static Criterion likeStart(String col, String value) {
		return new LikeCriterion(" LIKE ", col, value, LikeCriterion.START);
	}
	
	/**
	 * ex: NAME LIKE 'PAUL%'
	 */
	public static Criterion likeEnd(String col, String value) {
		return new LikeCriterion(" LIKE ", col, value, LikeCriterion.END);
	}
	
	/**
	 * ex: NAME NOT LIKE '%PAUL%'
	 */
	public static Criterion notLike(String col, String value) {
		return new LikeCriterion(" NOT LIKE ", col, value, LikeCriterion.AROUND);
	}
	
	/**
	 *  ex: NAME NOT LIKE '%PAUL'
	 */
	public static Criterion notLikeStart(String col, String value) {
		return new LikeCriterion(" NOT LIKE ", col, value, LikeCriterion.START);
	}
	
	/**
	 *  ex: NAME NOT LIKE 'PAUL%'
	 */
	public static Criterion notLikeEnd(String col, String value) {
		return new LikeCriterion(" NOT LIKE ", col, value, LikeCriterion.END);
	}
	
	/**
	 *  ex: NAME <> 'PAUL'
	 */
	public static Criterion ne(String col, Object value) {
		return new SimpleCriterion("<>", col, value);
	}
	
	/**
	 * ex: NAME = 'PAUL'
	 */
	public static Criterion eq(String col, Object value) {
		return new SimpleCriterion("=", col, value);
	}
	
	/**
	 * ex: NAME == NAME_B
	 */
	public static Criterion eqPropery(String colA, String colB) {
		return new PropertyCriterion("=", colA, colB);
	}
	
	/**
	 * ex: NAME != NAME_B
	 */
	public static Criterion nePropery(String colA, String colB) {
		return new PropertyCriterion("<>", colA, colB);
	}
	
	/**
	 * ex: NAME > 123
	 */
	public static Criterion le(String col, Object value) {
		return new SimpleCriterion("<=", col, value);
	}
	
	/**
	 * ex: NAME > NAME_B
	 */
	public static Criterion lePropery(String colA, String colB) {
		return new PropertyCriterion("<=", colA, colB);
	}
	
	/**
	 * ex: NAME < 123
	 */
	public static Criterion lt(String col, Object value) {
		return new SimpleCriterion("<", col, value);
	}
	
	/**
	 * ex: NAME < NAME_B
	 */
	public static Criterion ltPropery(String colA, String colB) {
		return new PropertyCriterion("<", colA, colB);
	}
	
	/**
	 * ex: NAME > 123
	 */
	public static Criterion gt(String col, Object value) {
		return new SimpleCriterion(">", col, value);
	}
	
	/**
	 * ex: NAME > NAME_B
	 */
	public static Criterion gtPropery(String colA, String colB) {
		return new PropertyCriterion(">", colA, colB);
	}
	
	/**
	 * ex: NAME >= 123
	 */
	public static Criterion ge(String col, Object value){
		return new SimpleCriterion(">=", col, value);
	}
	
	/**
	 * ex: NAME >= NAME_B
	 */
	public static Criterion gePropery(String colA, String colB) {
		return new PropertyCriterion(">=", colA, colB);
	}
	
	/**
	 * 可自行定義 SQL 
	 */
	public static Criterion sqlRestrictions(String sqlRestrictions) {
		return new SqlRestrictionsCriterion(sqlRestrictions, Collections.<String, Object>emptyMap());
	}
	
	/**
	 * 可自行定義 SQL 
	 */
	public static Criterion sqlRestrictions(String sqlRestrictions, Map<String, Object> params) {
		return new SqlRestrictionsCriterion(sqlRestrictions, params);
	}
	
}
