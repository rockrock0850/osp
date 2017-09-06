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

package com.fet.crm.osp.platform.webapp.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.common.util.PropertiesUtil;
import com.fet.crm.osp.platform.core.vo.systeminfo.component.FileVO;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;

/**
 * 
 * @author LawrenceLai, Adam Yeh
 */
@Controller
public abstract class AbstractOSPAction {
	
	/**
	 * 「上傳檔案 」
	 * 
	 * @return String
	 */
	public @ResponseBody String fileUpload(@RequestParam("file") MultipartFile file) {
		FileVO fileVO = toFileVO(file);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("fileVO", fileVO);
		
		String responseData = JsonUtil.toJson(responseMap);
		HttpRequestHandler.put("responseData", responseData);
		
		return responseData;
	}

    /**
     * 
     * @author Adam Yeh
     * @create date: Mar 3, 2017
     *
     * @param MultipartFile
     *            file
     * @return FileVO
     */
    public FileVO toFileVO(MultipartFile multipartFile) {
        FileVO vo = new FileVO();

        try {
            String[] retval = multipartFile.getOriginalFilename().split("\\.");
            String name = retval[0];
            String extension = retval[1];
            byte[] bytes = multipartFile.getBytes();
            String root = PropertiesUtil.getProperty("application.temp.file");

            /* 注意: bytes不會為null, 因為前端一定會要求選擇一個Excel檔案 */
            String filePath = root + File.pathSeparator + extension + File.pathSeparator + name + "." + extension;
            File file = new File(filePath);
            FileUtils.writeByteArrayToFile(file, bytes);

            vo.setName(name);
            vo.setFileBinary(bytes);
            vo.setExtension(extension);
            vo.setFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vo;
    }

}
