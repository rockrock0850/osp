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

import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.fet.crm.osp.platform.core.logger.CoreLoggerFactory;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * FreeMarker 工具類別
 * 
 * @author PaulChen
 */
public class FreeMarkerUtil {

	private static Logger logger = CoreLoggerFactory.getLogger(FreeMarkerUtil.class);
	
	private static Configuration configure;
	static {
		// 初使化 FreeMarker 所需要的各項參數
		configure = new Configuration(Configuration.VERSION_2_3_22);
		// 固定讀取「/template/ftl」目錄
		ClassTemplateLoader clazzLoader = new ClassTemplateLoader(FreeMarkerUtil.class, "/template/ftl");
		configure.setTemplateLoader(clazzLoader);
		configure.setEncoding(Locale.TAIWAN, "UTF-8");
	}
	
	/**
	 * 依據傳入參數，使用 FreeMarker 做 Template 轉換
	 * 
	 * @param params
	 * @param resources
	 * @return String
	 */
	public static String merge(Map<String, Object> params, String... resources) {
		StringBuffer path = new StringBuffer();
		for(String s : resources) {
			if(path.length() != 0) {
				path.append("/");
			}
			
			path.append(s);
		}
		path.append(".ftl"); // 加上副檔名
		
		try {
			Template t = configure.getTemplate(path.toString());
			return FreeMarkerTemplateUtils.processTemplateIntoString(t, params);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		return StringUtils.EMPTY;
	}
	
}
