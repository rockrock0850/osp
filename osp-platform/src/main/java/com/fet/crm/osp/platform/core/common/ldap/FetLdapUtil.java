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

package com.fet.crm.osp.platform.core.common.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.slf4j.Logger;

import com.fet.crm.osp.platform.core.logger.CoreLoggerFactory;
import com.fet.crm.osp.platform.core.vo.systeminfo.UserInfoVO;

/**
 * 遠傳 LDAP 工具類別
 * 
 * @author PaulChen
 */
public class FetLdapUtil {
	
	/**
	 * 認證網域
	 */
	private static final String DOMAIN_NAME = "fareastone.com.tw";

	/**
	 * 認證網址
	 */
	private static final String LDAP_URL = "ldap://" + DOMAIN_NAME + "/DC=fareastone,DC=com,DC=tw";
	
	private static Logger logger = CoreLoggerFactory.getLogger(FetLdapUtil.class);
	
	/**
	 * 經由NT-ACCOUNT LDAP 認証，取得使用者資訊
	 * 
	 * @param ntAccount
	 * @param passWd
	 * @return UserInfoVO
	 */
	public static UserInfoVO getAuthUser(String ntAccount, String passWd) {
		logger.info("FetLdapUtil.getAuthUser ntAccount = " + ntAccount);
		
		Hashtable<String, Object> env = new Hashtable<String, Object>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, LDAP_URL);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, ntAccount + "@" + DOMAIN_NAME);
		env.put(Context.SECURITY_CREDENTIALS, passWd);
		
		UserInfoVO userInfoVO = new UserInfoVO();
		LdapContext ctx = null;
		
		try{
			ctx = new InitialLdapContext(env, null);

			String base = "OU=FET-ARC";
			String filter = "mailNickname=" + ntAccount;

			SearchControls sc = new SearchControls();
			sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
			NamingEnumeration<SearchResult> results = ctx.search(base, filter, sc);

			while (results.hasMore()) {
				SearchResult sr = (SearchResult) results.next();
				Attributes attrs = sr.getAttributes();
				
				// 只取得員工編號
				String name = attrs.get("name").get().toString();
				String userId = getUserId(name);
				String mail = attrs.get("mail").get().toString();

				userInfoVO.setUserNm(name);
				userInfoVO.setUserId(userId);
				userInfoVO.setNtAccount(ntAccount);
				userInfoVO.setPass(true);
				userInfoVO.setMail(mail);
			}
		} catch(Exception e) {
			logger.error("LDAP ERROR:", e);
		} finally {
			if(ctx != null) {
				try {
					ctx.close();
				} catch (NamingException ignore) {}
			}
		}
		
		return userInfoVO;
	}
	
	/**
	 * 從 ntAccount 中 name 資訊取得員工編號
	 * 範例：yunchshen(82192)
	 * 
	 * @param name
	 * @return
	 */
	private static String getUserId(String name) {
		StringBuffer sb = new StringBuffer(name);
		int start = sb.lastIndexOf("(");
		int end   = sb.indexOf(")");
		
		return sb.substring(start + 1, end);
	}

}
