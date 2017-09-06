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

package com.fet.crm.osp.platform.core.common.util;

import java.util.Map;
import java.util.Map.Entry;

/**
 * 將樣板依照 Map的key和value做置換
 * 
 * @author Paul
 */
public class TemplateReplacementUtil {

	private static final String PATTERN = "$P{%s}";
	
	private TemplateReplacementUtil() {
		
	}
	
	/**
	 * 將樣板依照 Map的key和value做置換
	 *
	 * @param template 
	 * @param paramMp
	 * @return the string
	 */
	public static String replacement(String template, Map<String, String> paramMp) {
		if (template == null || template.length() == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer(template);

		for (Entry<String, String> entry : paramMp.entrySet()) {
			String token = String.format(PATTERN, entry.getKey()); // 置換對象關鍵字
			String value = entry.getValue(); // 置換內容

			int start = sb.indexOf(token);
			
			while(start != -1) {
				int end = token.length() + start;
				if (value == null) {
					value = "";
				}

				sb.replace(start, end, value);
				
				// 再次搜找是否有相同的token
				start = sb.indexOf(token);
			}
		}

		return sb.toString();
	}

}
