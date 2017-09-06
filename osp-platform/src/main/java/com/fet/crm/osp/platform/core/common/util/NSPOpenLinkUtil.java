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

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.google.gson.Gson;

/**
 * 請使用「NSPCyptogramHandler」
 * 
 * @author LawrenceLai
 */
@Deprecated
public class NSPOpenLinkUtil {

	protected String Algorithm = "DES";
	protected KeyGenerator keygen;
	protected SecretKey deskey;
	protected Cipher cipher;
	
	/**
	 * @param strKey(
	 *            NSPOpenLink 加密的 Key )
	 * @throws Exception
	 */
	@SuppressWarnings("restriction")
	public NSPOpenLinkUtil(String strKey) throws Exception {
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
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		String link = "http://nsp22sit06.fareastone.com.tw/NSP/nspOpenLinkInterface.do?para=";
		String key = "07b719cd-a81b-4f96-811d-d974310023e3";           
		String token = "jv498Gcdze";         
		String date = "20170522";          
		String ivrcode = null;       
		String account = "65196";       
		String rocIdType = null;         
		String rocId = null;             
		String corpPicTaxId = null;       
		String msisdn = null;     
		String ntAccount = "pcyang";     
		String linkId  = "199531";        
		String system = "BSOM";        
		
		Map<String, String> funcPara = new HashMap<>();
		funcPara.put("fjobId", "圖檔代碼");
		funcPara.put("account", "登入帳號");
		
		NSPOpenLinkBean linkBean = new NSPOpenLinkBean();
		linkBean.setKey(key);
		linkBean.setToken(token);
		linkBean.setDate(date);
		linkBean.setIvrcode(ivrcode);
		linkBean.setAccount(account);
		linkBean.setRocIdType(rocIdType);
		linkBean.setRocId(rocId);
		linkBean.setCorpPicTaxId(corpPicTaxId);
		linkBean.setMsisdn(msisdn);
		linkBean.setNtAccount(ntAccount);
		linkBean.setLinkId(linkId);
		linkBean.setSystem(system);
		linkBean.setFuncPara(funcPara);
		
		String json = new Gson().toJson(linkBean);
//		String encryptString = CyptogramHandler.decrypt(json, "DES", "rVx5qZ48l0");
//		String encryptString = CyptogramHandler.encrypt(json, "DES", "rVx5qZ48l0");
		
		// System.out.println("json = \" " + json);
		
		NSPOpenLinkUtil linkUtil = new NSPOpenLinkUtil("rVx5qZ48l0");
		
//		String encryptSting = linkUtil.desEncrypt(json);
		String encryptString = "IGlNjUFits5K6RyJgJuYwm0D0uKnE4UJOTzuLmIPPves7uifU9qa%2BwV0CZmDm6bk57OeVVPuQ2SX%0DbCwlwL1o3dmAWImz%2BfAjYY%2BUHddLzDHF54U8yQ6qUIWRKnubRco12V%2B5djTUl78x2p8wyW4ZXygs%0Dm%2FUlHcS45qg25TkfOlcfO4SvGcOv1cbvzUlJ5kJ2mVewRlFhKJQ%3D";
		String decrypSting = linkUtil.desDecrypt(URLDecoder.decode(encryptString, "UTF-8"));
				
//		System.out.println("加密前：\n" + json + "\n");
//        System.out.println("加密後：\n" + encryptSting + "\n");
        System.out.println("解密後：\n" + decrypSting + "\n");
        
//        System.out.println(link + encryptSting);
	}
	
	/**
	 * NSP 開啟資訊封裝類別
	 * 
	 * @author PaulChen
	 */
	private static class NSPOpenLinkBean {
		private String key;

		private String token;

		private String date;

		private String ivrcode;

		private String account;

		private String rocIdType;

		private String rocId;

		private String corpPicTaxId;

		private String msisdn;

		private String ntAccount;

		private String linkId;

		private String system;

		private Map<String, String> funcPara;

		public NSPOpenLinkBean() {
			super();
		}

		public NSPOpenLinkBean(String key, String token, String date, String account, String ntAccount, String linkId,
				String system) {
			super();
			this.key = key;
			this.token = token;
			this.date = date;
			this.account = account;
			this.ntAccount = ntAccount;
			this.linkId = linkId;
			this.system = system;
		}

		/**
		 * 
		 * @param key
		 *            BSOM 回寫 Key 值
		 * @param token
		 *            兩邊需相同的的驗證 ， BSOM 請填jv498Gcdze
		 * @param date
		 *            日期 YYYYMMDD
		 * @param ivrcode
		 *            店組代碼
		 * @param account
		 *            登入帳號
		 * @param rocIdType
		 *            證照類型
		 * @param rocId
		 *            證照號碼 / 統編
		 * @param corpPicTaxId
		 *            公司負責人證照號碼
		 * @param msisdn
		 *            行動電話 / 代表號
		 * @param ntAccount
		 * @param linkId
		 *            功能別 ID
		 * @param system
		 *            請帶BSOM
		 * @param funcPara
		 *            功能參數
		 */
		public NSPOpenLinkBean(String key, String token, String date, String ivrcode, String account, String rocIdType,
				String rocId, String corpPicTaxId, String msisdn, String ntAccount, String linkId, String system,
				Map<String, String> funcPara) {
			super();
			this.key = key;
			this.token = token;
			this.date = date;
			this.ivrcode = ivrcode;
			this.account = account;
			this.rocIdType = rocIdType;
			this.rocId = rocId;
			this.corpPicTaxId = corpPicTaxId;
			this.msisdn = msisdn;
			this.ntAccount = ntAccount;
			this.linkId = linkId;
			this.system = system;
			this.funcPara = funcPara;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getIvrcode() {
			return ivrcode;
		}

		public void setIvrcode(String ivrcode) {
			this.ivrcode = ivrcode;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getRocIdType() {
			return rocIdType;
		}

		public void setRocIdType(String rocIdType) {
			this.rocIdType = rocIdType;
		}

		public String getRocId() {
			return rocId;
		}

		public void setRocId(String rocId) {
			this.rocId = rocId;
		}

		public String getCorpPicTaxId() {
			return corpPicTaxId;
		}

		public void setCorpPicTaxId(String corpPicTaxId) {
			this.corpPicTaxId = corpPicTaxId;
		}

		public String getMsisdn() {
			return msisdn;
		}

		public void setMsisdn(String msisdn) {
			this.msisdn = msisdn;
		}

		public String getNtAccount() {
			return ntAccount;
		}

		public void setNtAccount(String ntAccount) {
			this.ntAccount = ntAccount;
		}

		public String getLinkId() {
			return linkId;
		}

		public void setLinkId(String linkId) {
			this.linkId = linkId;
		}

		public String getSystem() {
			return system;
		}

		public void setSystem(String system) {
			this.system = system;
		}

		public Map<String, String> getFuncPara() {
			return funcPara;
		}

		public void setFuncPara(Map<String, String> funcPara) {
			this.funcPara = funcPara;
		}
	}

}
