/**
 * Copyright (c) 2014 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */

package com.fet.crm.osp.platform.core.common.cyptogram;

import org.apache.commons.codec.binary.Base64;

/**
 * 透過Base64進行加解密<br>
 * 
 * @author VJChou
 */
public class Base64Handler {

	/**
	 * Encode the string by base64
	 * 
	 * @param str
	 * @return string
	 */
	public static String encode(String str) {
		return Base64.encodeBase64String(str.getBytes());
	}

	/**
	 * Decode the string by base64
	 * 
	 * @param str
	 * @return string
	 */
	public static String decode(String str) {
		byte by[] = Base64.decodeBase64(str);
		return new String(by);
	}

}
