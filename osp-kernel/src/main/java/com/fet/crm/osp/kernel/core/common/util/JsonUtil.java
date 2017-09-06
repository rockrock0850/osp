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

package com.fet.crm.osp.kernel.core.common.util;

import java.awt.image.BufferedImage;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * JSON格式處理工具<br>
 * 將JSON的處理統一在此實現(GSON)
 * 
 * @author VJChou
 */
public class JsonUtil {
	
	/**
	 * 將傳入物件轉成JSON字串
	 * 
	 * @param src
	 * @return String
	 * @throws ParseException
	 */
	public static String toJson(Object src) {
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat(DateUtil.DATE_PATTERN);
		builder.setExclusionStrategies(new Exclusion());
		
		Gson gsonObj = builder.create();
		return gsonObj.toJson(src);
	}

	/**
	 * 將傳JSON字串入轉成物件
	 * 
	 * @param json
	 * @param classOfT
	 * @return <T>
	 */
	public static <T> T fromJson(String json, Class<T> classOfT) {
		GsonBuilder builder = new GsonBuilder();
		builder.setExclusionStrategies(new Exclusion());
		builder.registerTypeAdapter(BigDecimal.class, new BigDecimalDeserializer());
		builder.registerTypeAdapter(Date.class, new DateDeserializer());
		
		Gson gsonObj = builder.create();
		return gsonObj.fromJson(json, classOfT);
	}
	
	/**
	 * 日期解析器
	 * 
	 * @author PaulChen
	 */
	private static class DateDeserializer implements JsonDeserializer<Date> {
		
		private SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_PATTERN);
		
		@Override
		public Date deserialize(JsonElement jsElm, Type type,JsonDeserializationContext context) throws JsonParseException {
			String str = jsElm.getAsString();
			
			if(StringUtils.isNotBlank(str)) {
				try {
					return sdf.parse(str);
				} catch (Exception ignore) {
					return null;
				}
			}
			
			return null;
		}
		
	}
	
	/**
	 * BigDecimal 解析器
	 * 
	 * @author PaulChen
	 */
	private static class BigDecimalDeserializer implements JsonDeserializer<BigDecimal> {

		@Override
		public BigDecimal deserialize(JsonElement jsElm, Type type, JsonDeserializationContext context)
				throws JsonParseException {
			String str = jsElm.getAsString();
			
			if(StringUtils.isNotBlank(str)) {
				try {
					Double d = Double.parseDouble(str);
					return BigDecimal.valueOf(d);
				} catch (Exception ignore) {
					return null;
				}
			}
			
			return null;
		}
	}
	
	/**
	 * JSON 排除（例外）解析器
	 * 
	 * @author PaulChen
	 */
	private static class Exclusion implements ExclusionStrategy {
		
		private static Set<Class<?>> clazzSt = new HashSet<>();
		{
			clazzSt.add(BufferedImage.class);
		}
		
		@Override
		public boolean shouldSkipField(FieldAttributes f) {
			return false;
		}

		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			return clazzSt.contains(clazz);
		}
		
	}
	
}
