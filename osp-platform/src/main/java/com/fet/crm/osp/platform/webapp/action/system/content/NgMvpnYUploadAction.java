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

package com.fet.crm.osp.platform.webapp.action.system.content;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fet.crm.osp.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.handler.FTPHandler;
import com.fet.crm.osp.platform.core.vo.systeminfo.component.FileVO;
import com.fet.crm.osp.platform.webapp.action.AbstractOSPAction;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;

/**
 * [上傳NG MVPN(Y槽) 檔案] CONT-0069 前端控制器
 *
 * @author AndrewLee
 */
@Controller
@RequestMapping("/flow/content")
public class NgMvpnYUploadAction extends AbstractOSPAction {
	
	@Value("${temp.store.path}")
	private String storePath;
    
    @Autowired
    private FTPHandler ftpHandler;

    @RequestMapping(value = "/cont-0069", method = { RequestMethod.POST, RequestMethod.GET })
    public String getContent0069() {
        return ForwardUtil.CONT0069.getView();
    }

    /**
     * 上傳NG MVPN 檔案至網路磁碟機上
     * 
     * @param file
     */
    @RequestMapping(value = "/upload-ng-mvpn", method = RequestMethod.POST)
    public @ResponseBody String uploadNGMVPN(@RequestParam("file") MultipartFile multipartFile) {
    	if (multipartFile != null) {
    		try {
    			String virtualName = IdentifierIdUtil.getUuid();
    			String realName = multipartFile.getOriginalFilename();
    			byte[] fileByte = multipartFile.getBytes();
			
    			File file = null;
			
    			if (isExitStorePath()) {
    				file = new File(storePath, virtualName);
    				FileUtils.writeByteArrayToFile(file, fileByte);
    			}
			
    			ftpHandler.uploadNGMVPN(file, realName);
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
    
}
