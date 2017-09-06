/**
 * Copyright (c) 2017 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */

package com.fet.crm.osp.common.util;

import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import com.fet.crm.osp.common.Constant;

/**
 * 屬性檔(properties)讀取工具
 * 
 * @author V.J CHOU
 */
public class PropertiesUtil {

	/**
	 * 根據傳入的屬性鍵值(prop_key)，從回設定檔(configuration)回傳相對應的內容值.
	 * 
	 * @param prop_key
	 * @return String
	 */
	public static String getProperty(String prop_key) {
		return getPropertys(prop_key, Constant.CONFIG_FILE);
	}

	/**
	 * 
	 * 
	 * @param prop_key
	 * @return boolean
	 */
	public static boolean getBooleanProp(String prop_key) {
		String propValue = getProperty(prop_key);
		return StringUtils.equalsIgnoreCase("TRUE", propValue);
	}
	
	/**
	 * 根據傳入的屬性鍵值(prop_key)，從指定檔案(fileName)回傳相對應的內容值.
	 * 
	 * @param prop_key
	 * @param fileName
	 * @return String
	 */
	public static String getPropertys(String prop_key, String fileName) {
		ResourceBundle res = ResourceBundle.getBundle(fileName);
		String prop_val = res.getString(prop_key);

		return prop_val;
	}

}
