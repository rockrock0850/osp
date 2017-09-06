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

import java.net.URLEncoder;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * NSP 加 / 解密工具 
 * 
 * @author PaulChen
 */
public class NSPCyptogramHandler {

	private String Algorithm = "DES";
	// private KeyGenerator keygen;
	private SecretKey deskey;
	private Cipher cipher;
	
	/**
	 * 
	 * @param strKey(
	 *            NSPOpenLink 加密的 Key )
	 * @throws Exception
	 */
	@SuppressWarnings("restriction")
	public NSPCyptogramHandler(String strKey) throws Exception {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());

		byte[] key = strKey.getBytes();

		DESKeySpec spec = null;
		spec = new DESKeySpec(key);

		SecretKeyFactory factory = null;
		factory = SecretKeyFactory.getInstance(Algorithm);
		deskey = factory.generateSecret(spec);
		cipher = Cipher.getInstance(Algorithm);
	}

	/**
	 * 
	 * 1. DES 加密 2. BASE64 Encode 3. URL Encoder
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("restriction")
	public String desEncrypt(String str) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] encryptedByte = cipher.doFinal(str.getBytes());

		String encryptedString = new sun.misc.BASE64Encoder().encode(encryptedByte).replaceAll("\n", "");

		String urlEncodedStr = URLEncoder.encode(encryptedString, "UTF-8");

		return urlEncodedStr;
	}

	/**
	 * 
	 * HYT 用 1. BASE64 decode 2. DES 解密
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("restriction")
	public String desDecrypt(String str) throws Exception {
		byte[] decryptByte = new sun.misc.BASE64Decoder().decodeBuffer(str);

		cipher.init(Cipher.DECRYPT_MODE, deskey);
		byte[] decryptString = cipher.doFinal(decryptByte);

		return new String(decryptString);
	}
	
}
