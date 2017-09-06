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

package com.fet.crm.osp.platform.core.handler;

import java.io.File;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.common.ftp.FTPUtil;
import com.fet.crm.osp.platform.core.common.util.CacheReferenceUtil;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;

/**
 * 
 * @author LawrenceLai
 */
@Component
public class FTPHandler {

	@Autowired
	private CacheReferenceUtil cacheReferenceUtil;
	
	@SuppressWarnings("unchecked")
	public boolean uploadIPConfig(File file, String fileName) {
		String settingJson = cacheReferenceUtil.getGlobalConfigValueById("IP_UPLOAD_PATH");
		
		if (StringUtils.isNotBlank(settingJson)) {
			Map<String, Object> settingMap = JsonUtil.fromJson(settingJson, Map.class);
			
			String server = MapUtils.getString(settingMap, "server");  
			int port = MapUtils.getIntValue(settingMap, "port");       
			String user = MapUtils.getString(settingMap, "user");    
			String password = MapUtils.getString(settingMap, "password");
			String dir = MapUtils.getString(settingMap, "dir");    
			
			FTPUtil ftpUtil = new FTPUtil(server, port, user, password, dir);
			
			ftpUtil.upload(file, fileName);
			
			return true;
		}
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean uploadNGMVPN(File file, String fileName) {
		String settingJson = cacheReferenceUtil.getGlobalConfigValueById("NGMVPN_UPLOAD_PATH");
		
		if (StringUtils.isNotBlank(settingJson)) {
			Map<String, Object> settingMap = JsonUtil.fromJson(settingJson, Map.class);
			
			String server = MapUtils.getString(settingMap, "server");  
			int port = MapUtils.getIntValue(settingMap, "port");       
			String user = MapUtils.getString(settingMap, "user");    
			String password = MapUtils.getString(settingMap, "password");
			String dir = MapUtils.getString(settingMap, "dir");    
			
			FTPUtil ftpUtil = new FTPUtil(server, port, user, password, dir);
			
			ftpUtil.upload(file, fileName);
			
			return true;
		}
		
		return false;
	}
	
}
