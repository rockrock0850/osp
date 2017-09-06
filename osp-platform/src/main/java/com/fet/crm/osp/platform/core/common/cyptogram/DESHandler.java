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

import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 實作DES 加解密. <br>
 * 程式取自「NSP」系統「com.fet.crm.nsp.generic.util」
 * 
 * @author FET
 */
@SuppressWarnings( {"rawtypes", "unchecked"} )
public class DESHandler {
	
	// Encrypt type
    public static final String ENCRYPT_DES = "DES";

	/**
	 * 將字串加密
	 * 
	 * @param value
	 *            要加密的字串
	 * @param key
	 *            DES 金鑰
	 * 
	 * @throws Exception
	 */
	public static String getEncrypt(String value, String key) throws Exception {
		DES des = new DES(key);
		String str = new sun.misc.BASE64Encoder().encode(des.createEncryptor(value));
		
		return str.replaceAll("\\r\\n|\\r|\\n", "");
	}

	public static String getDecrypt(String value, String key) throws Exception {
		DES des = new DES(key);

		return des.createDecryptor(new sun.misc.BASE64Decoder().decodeBuffer(value));
	}

	/**
	 * 將字串轉為byte array方式加密
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getEncryptByteArray(String value, String key)
			throws Exception {
		DES des = new DES(key);
		byte[] arrEncrypted = des.createEncryptor(value);
		String str = byteArrayToByteValueString(arrEncrypted);

		return str;
	}

	/**
	 * 將byteArray轉為字串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteArrayToByteValueString(byte[] byteArray) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			sb.append(Byte.toString(byteArray[i]) + "|");
		}
		return sb.toString();
	}

	/**
	 * 將字串轉為byteArray
	 * 
	 * @param byteValueString
	 * @return
	 */
	@SuppressWarnings("unused")
	private static byte[] byteValueStringToByteArray(String byteValueString) {
		StringTokenizer strTokenizer = new StringTokenizer(byteValueString, "|");

		Vector vByteArray = new Vector();
		while (strTokenizer.hasMoreElements()) {
			vByteArray.add((String) strTokenizer.nextElement());
		}
		int arraySize = vByteArray.size();
		byte byteArray[] = new byte[arraySize];
		for (int i = 0; i < arraySize; i++) {
			byteArray[i] = Byte.parseByte((String) vByteArray.get(i));
		}
		return byteArray;
	}

}
