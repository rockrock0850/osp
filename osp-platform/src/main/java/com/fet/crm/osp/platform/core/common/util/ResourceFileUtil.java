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

import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

/**
 * 資源管理器
 * 
 * @author V.J CHOU
 */
public enum ResourceFileUtil {
	
	 JSON("json", ""), 				// json檔案
	 SQL("sql", null),
	 SQL_ORDER("sql", "order"),    // SQL下的order
	 SQL_SHIFT("sql","shift"),    // SQL下的Shift
	 SQL_SYSTEM("sql","system"),    // SQL下的Shift
     SQL_SKILL("sql","skill");     // SQL下的skill
    

	 private String target; // 指定資料夾
	 private String module; // 模組資料夾

	 private static String rootDir = "template";

	 /** separator */
	 public static String separator = "/";

	 private ResourceFileUtil(String target, String module) {
	     this.target = target;
	     this.module = module;
	 }

	 /**
	  * 根據指定的[路徑/檔案名稱]回傳[檔案]路徑
	  * 
	  * @param params
	  * @return String
	  */
	 public final String getResourcePath(String... params) {
	     if (params != null && params.length != 0) {
	         StringBuilder sb = new StringBuilder();
	         sb.append(separator);
	         sb.append(rootDir);
	         sb.append(separator);
	         sb.append(target);
	         sb.append(separator);

	         if (StringUtils.isNotEmpty(module)) {
	             sb.append(module);
	             sb.append(separator);
	         }

	         int len = params.length;
	         int count = 0;

	         for (String index : params) {
	             sb.append(index);
	             count++;
	             if (count < len) {
	                 sb.append(separator);
	             }
	         }

	         sb.append("." + target);

	         return sb.toString();
	     }

	     return "";
	 }

	 /**
	  * 根據指定的[路徑/檔案名稱]回傳[檔案]內容(文字). <br>
	  * 
	  * @param params
	  * @return String
	  */
	 public final String getResource(String... params) {
	     InputStream inStr = getResourceAsInputStream(params);

	     if (inStr != null) {
	         return ReadFileUtil.readTextFile(inStr);
	     }

	     return "";
	 }

	 /**
	  * 根據指定的[路徑/檔案名稱]回傳[檔案]內容(InputStream). <br>
	  * 
	  * @param params
	  * @return InputStream
	  */
	 public final InputStream getResourceAsInputStream(String... params) {
	     String path = getResourcePath(params);
	     if (StringUtils.isNotBlank(path)) {
	         InputStream inStr = ResourceFileUtil.class.getResourceAsStream(path);

	         if (inStr == null) {
	             inStr = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
	         }

	         return inStr;
	     }

	     return null;
	 }

	 public String getTarget() {
	     return target;
	 }

	 public String getModule() {
	     return module;
	 }

}
