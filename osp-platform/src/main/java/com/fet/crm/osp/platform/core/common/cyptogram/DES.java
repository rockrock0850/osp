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

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES資料加解密. <br>
 * 程式取自「NSP」系統「com.fet.crm.nsp.generic.util」
 * 
 * @author FET
 */
public class DES {

	protected String Algorithm = "DES";
	protected KeyGenerator keygen;
	protected SecretKey deskey;
	protected Cipher cipher;

	/**
	 * 初始化DES實例
	 * 
	 * @throws Exception
	 */
	public DES(String strKey) throws Exception {
		init(strKey);
	}

	/**
	 * DES初始
	 * 
	 * @param strKey
	 *            DES Key
	 * @throws Exception
	 */
	public void init(String strKey) throws Exception {
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
	 * 對String進行加密
	 * 
	 * @param str
	 *            要加密的數據
	 * @return 返回加密後的byte數組
	 * @throws Exception
	 */
	public byte[] createEncryptor(String str) throws Exception {
		byte[] cipherByte;

		cipher.init(Cipher.ENCRYPT_MODE, deskey);
		cipherByte = cipher.doFinal(str.getBytes());
		return cipherByte;
	}

	/**
	 * 對Byte數組進行解密
	 * 
	 * @param buff
	 *            要解密的數據
	 * @return 返回加密後的String
	 * @throws Exception
	 */
	public String createDecryptor(byte[] buff) throws Exception {
		byte[] cipherByte;

		cipher.init(Cipher.DECRYPT_MODE, deskey);
		cipherByte = cipher.doFinal(buff);

		return (new String(cipherByte));
	}

}
