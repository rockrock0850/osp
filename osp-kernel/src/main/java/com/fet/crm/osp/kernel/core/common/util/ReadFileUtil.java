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

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author VJChou
 */
public class ReadFileUtil {

	// add at 2013-12-04
	private static final String encoding = "UTF-8";
	// private static Logger logger = SystemLoggerFactory.getLogger();
	
	/**
	 * 回傳文字格式內容
	 * 
	 * @param inStream
	 * @return String
	 */
	public static String readTextFile(InputStream inStream) {
		String content = "";

		try {
			int ca = inStream.available();
			byte[] by = new byte[ca];
			
			int isSuccess = inStream.read(by);
			if (isSuccess != -1) {
				content = new String(by, encoding);
			}
			
			inStream.close();

		} catch (IOException e) {
			// logger.error(" ReadFileUtil.readTextFile : ", e);
		}

		return content;
	}
	
}
