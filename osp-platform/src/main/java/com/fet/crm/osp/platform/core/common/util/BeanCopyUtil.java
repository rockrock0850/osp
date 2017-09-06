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

package com.fet.crm.osp.platform.core.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.fet.crm.osp.platform.core.logger.CoreLoggerFactory;

/**
 * [資料封裝物件 物件複製] 工具類別
 * 
 * @author LawrenceLai, Adam yeh
 */
public class BeanCopyUtil {
	
	private static Logger logger = CoreLoggerFactory.getLogger(BeanCopyUtil.class);

	/**
	 * 提取NULL資料，略過複製行為。
	 * 
	 * @param object
	 * @return String[]
	 */
	public static String[] null2Ignore(Object object) {
		List<String> properties = new ArrayList<String>();

		try {
			Class clazz = object.getClass();
			Field[] declaredFields = clazz.getDeclaredFields();

			for (Field declaredField : declaredFields) {
				declaredField.setAccessible(true);

				if (declaredField.get(object) == null) {
					String name = declaredField.getName();
					properties.add(name);
				}
			}

			return properties.toArray(new String[properties.size()]);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return new String[0];
	}

	/**
	 * 檢查傳入物件的所有以String型態宣告的欄位, 若為null則轉為空字串
	 * 
	 * @author Adam
	 * @create date: May 19, 2017
	 *
	 * @param object
	 */
	public static void nullToEmptyString(Object object) {
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				if (field.getType().equals(String.class)) {
					String value;
					value = (String) field.get(object);
					if (value == null) {
						field.set(object, "");
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// 抓到Exception則停止置換空白
				StackTraceElement[] stack = e.getStackTrace();
				
				logger.info(JsonUtil.toJson(stack));
			}
		}
	}

}
