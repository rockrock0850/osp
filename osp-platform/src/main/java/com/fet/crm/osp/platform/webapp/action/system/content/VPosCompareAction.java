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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fet.crm.osp.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.common.util.PropertiesUtil;
import com.fet.crm.osp.platform.core.facade.systeminfo.BuzVPosCompareFacade;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.content.compare.BuzVPOSCompareResultVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.content.compare.BuzVPOSCompareVO;
import com.fet.crm.osp.platform.webapp.action.AbstractOSPAction;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;
import com.fet.crm.osp.platform.webapp.vo.session.SessionVO;

/**
 * [工作單頁簽內容] CONT-0070 前端控制器
 *
 * @author AndrewLee
 */
@Controller
@RequestMapping("/flow/content")
public class VPosCompareAction extends AbstractOSPAction {
	
	@Value("${temp.store.path.xlsx}")
	private String storePath;

	private static final String vopsFileNM = "VPOS";
	private static final String aimsFileNM = "AIMS";
	
    @Autowired
    private BuzVPosCompareFacade buzVPoscompareFacade;

    @RequestMapping(value = "/cont-0070", method = { RequestMethod.POST, RequestMethod.GET })
    public String getContent0070() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String ntAccount = sVO.getUserInfoVO().getNtAccount();
    	
    	HttpRequestHandler.put("ntAccount", ntAccount);
    	
    	return ForwardUtil.CONT0070.getView();
    }

    /**
     * 將上傳的Excel檔解析後.將其資料insert至DB
     * 
     * @param file
     */
    @RequestMapping(value = "/upload-vpos-excel", method = RequestMethod.POST)
    public @ResponseBody String uploadVposExcel(@RequestParam("file") List<MultipartFile> multipartFileList) {
        if (CollectionUtils.isNotEmpty(multipartFileList)) {
    		String virtualName = vopsFileNM;
    		
    		List<Map<String, String>> fileList = new ArrayList<>();
    		 
        	for (int index = 0; index < multipartFileList.size(); index++) {
        		MultipartFile multipartFile = multipartFileList.get(index);
        		
        		if (index > 0) {
        			virtualName = aimsFileNM;
        		}
        		
        		if (multipartFile != null) {
            		try {
        				String realName = multipartFile.getOriginalFilename();
        				String extension = getFileExtension(realName);
        				long fileSize = multipartFile.getSize();
        				byte[] fileByte = multipartFile.getBytes();

        				File file = null;
        				
        				if (isExitStorePath()) {
        					file = new File(storePath, virtualName.concat("." + extension));
        					FileUtils.writeByteArrayToFile(file, fileByte);
        				}
        				
        				Map<String, String> fileMap = new HashMap<>();
        				fileMap.put("PREFIX_NAME", virtualName);
        				fileMap.put("FILE_NAME", realName);
        				fileMap.put("FILE_PATH", file.getAbsolutePath());
        				fileMap.put("FILE_SIZE", String.valueOf(fileSize));
        				fileMap.put("FILE_EXTENSION", extension);
        				
        				fileList.add(fileMap);
        			} catch (IOException e) {
        				e.printStackTrace();
        			}
            	}
        	}
        	
        	return JsonUtil.toJson(fileList);
        }
        
        return "";
    }

    /**
     * 對已經暫存的Excel檔案進行對比.
     * 
     * @return String
     */
    @RequestMapping(value = "/begin-vpos-compare", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody String beginVPosCompare() {
    	String userId = HttpSessionHandler.getSessionInfo().getUserId();
    	
    	Map<String, Object> paramMap = HttpRequestHandler.getJsonToMap();
    	String vposPath = MapUtils.getString(paramMap, "filePath_".concat(vopsFileNM));
    	String aimsPath = MapUtils.getString(paramMap, "filePath_".concat(aimsFileNM));

        File vPosFile = new File(vposPath);
        File aimsFile = new File(aimsPath);
        
        BuzVPOSCompareVO buzVPOSCompareVO = new BuzVPOSCompareVO();
        buzVPOSCompareVO.setvPosfile(vPosFile);
        buzVPOSCompareVO.setAimsFile(aimsFile);
        buzVPOSCompareVO.setUserId(userId);
        
        buzVPoscompareFacade.doExcelCompare(buzVPOSCompareVO);

        return "";
    }

    /**
     * 取得對比結果
     * 
     * @return String
     */
    @RequestMapping(value = "/get-compare-result", method = { RequestMethod.POST, RequestMethod.GET })
    public String getCompareResult() {
        
        List<BuzVPOSCompareResultVO> dataList = buzVPoscompareFacade.getCompareResult();
        String responseData = JsonUtil.toJson(dataList);
        
        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.JSON.getView();
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
