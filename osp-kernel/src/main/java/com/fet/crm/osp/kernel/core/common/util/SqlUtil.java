/**
 * Copyright (c) 2015 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */

package com.fet.crm.osp.kernel.core.common.util;

import java.util.List;

/**
 * Native SQL 工具類別
 * 
 * @author PaulChen
 */
public class SqlUtil {

	/**
	 * 產生 SQL IN 的樣式，如 'A', 'B', 'C' 等。
	 * 
	 * @param values
	 * @return String
	 */
	public static String getInCondition(List<String> values) {
		StringBuffer in = new StringBuffer();
		
		for(String s : values) {
			if(in.length() != 0) {
				in.append(", ");
			}
			
			in.append("'");
			in.append(s);
			in.append("'");
		}
		
		return in.toString();
	}
	
}
