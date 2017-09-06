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

package com.fet.crm.osp.platform.webapp.action.system;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fet.crm.osp.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;

/**
 * 
 * @author LawrenceLai
 */
@Controller
@RequestMapping("/file")
public class FileAction{
	
	@Value("${temp.store.path}")
	private String storePath;
	
	/**
	 * 「上傳檔案 」
	 * 
	 * @return String
	 */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String upload(@RequestParam("file") MultipartFile multipartFile) {
    	if (multipartFile != null) {
    		try {
				String virtualName = IdentifierIdUtil.getUuid();
				String realName = multipartFile.getOriginalFilename();
				String extension = getFileExtension(realName);
				long fileSize = multipartFile.getSize();
				byte[] fileByte = multipartFile.getBytes();

				File file = null;
				
				if (isExitStorePath()) {
					file = new File(storePath, virtualName);
					FileUtils.writeByteArrayToFile(file, fileByte);
				}
				
				Map<String, String> fileMap = new HashMap<>();
				fileMap.put("FILE_NAME", realName);
				fileMap.put("FILE_PATH", file.getAbsolutePath());
				fileMap.put("FILE_SIZE", String.valueOf(fileSize));
				fileMap.put("FILE_EXTENSION", extension);
				
				return JsonUtil.toJson(fileMap);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
		
		return "";
	}

    private boolean isExitStorePath() {
    	File file = new File(storePath);
    	
    	if (!file.exists()) {
    		file.mkdirs();
    	}
    	
    	return true; 
    }

    private String getFileExtension(String name) {
    	if (StringUtils.isNotBlank(name)) {
    		int begin = name.lastIndexOf(".");
    		
    		return name.substring(begin + 1);
    	}
    	
    	return "";
    }
    
}
