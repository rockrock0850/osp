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

package com.fet.crm.osp.platform.webapp.action.setting;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.facade.dispatchinfo.ShiftManageFacade;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.ShiftMaintainVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.ShiftTypeSettingVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.ShiftTypeSkillMapVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.component.FileVO;
import com.fet.crm.osp.platform.webapp.action.AbstractOSPAction;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;

/**
 * [班表匯入/維護作業] 導頁控制器
 * 
 * @author LawrenceLai, Adam Yeh
 */
@Controller
@RequestMapping("/setting/shift")
public class ShiftMaintainAction extends AbstractOSPAction{
	
    @Autowired
    private ShiftManageFacade shiftManageFacade;

	@Override
	@RequestMapping(value="/fileUpload", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String fileUpload(MultipartFile file) {
		return super.fileUpload(file);
	}
	
	/**
	 * 「班表匯入」服務頁面
	 * 
	 * @return String
	 */
	@RequestMapping(value="/service-shift-content", method = RequestMethod.GET)
	public String serviceShiftContent() {
		return ForwardUtil.OSPLV2008.getView();
	}
	
	/**
	 * 「班表匯入 」
	 * 
	 * @return String
	 */
	@RequestMapping(value="/import-shift-content", method = RequestMethod.POST)
	public @ResponseBody String importShiftContent() {
		boolean result = false;

		try {
			String userId = HttpSessionHandler.getSessionInfo().getUserId();
			
			Map<String, Object> paramMap = HttpRequestHandler.getJsonToMap();
			String dtYear = MapUtils.getString(paramMap, "year", "");
			String dtMonth = MapUtils.getString(paramMap, "month", "");
			String fileName = MapUtils.getString(paramMap, "fileName", "");
			String filePath = MapUtils.getString(paramMap, "filePath", "");
			String fileExtension = MapUtils.getString(paramMap, "fileExtension", "");
			
			File file = new File(filePath);
			byte[] fileBytes =IOUtils.toByteArray(new FileInputStream(file));
			
			FileVO fileVO = new FileVO();
			fileVO.setName(fileName);
			fileVO.setFile(file);
			fileVO.setFileBinary(fileBytes);
			fileVO.setExtension(fileExtension);
			
			ShiftMaintainVO shiftMaintainVO = new ShiftMaintainVO();
			shiftMaintainVO.setDtYear(dtYear);
			shiftMaintainVO.setDtMonth(dtMonth);
			shiftMaintainVO.setShiftAttachment(fileVO);
			shiftMaintainVO.setCreateUser(userId);
			
			result = shiftManageFacade.createShiftAttachment(shiftMaintainVO);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return JsonUtil.toJson(result);
	}
	
	/**
	 * 「班表匯出」
	 * 
	 * @return String
	 */
	@RequestMapping(value="/export-shift-content", method = RequestMethod.GET)
	public String exportShiftContent(HttpServletResponse response) {
		Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();
		String year = requestData.get("year").toString();
		String month = requestData.get("month").toString();
		FileVO fileVO = shiftManageFacade.queryShiftAttachment(year, month);
		
		try {
			InputStream inputStream = new ByteArrayInputStream(fileVO.getFileBinary());
			String fileName = URLEncoder.encode(fileVO.getName(), "UTF-8");// 解決中文檔名亂碼問題
			
	        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
	        
			OutputStream outputStream = response.getOutputStream();
			FileCopyUtils.copy(inputStream, outputStream);
			
			return "";
		} catch (Exception ex) {
			// 不知道要怎麼handle
		}
		
		return ForwardUtil.JSON.getView();
	}

	/**
	 * 「班別維護 」服務頁面
	 * 
	 * @return String
	 */
	@RequestMapping(value="/service-shift-type", method = RequestMethod.GET)
	public String ServiceShiftType() {
		return ForwardUtil.OSPLV2009.getView();
	}
	
	/**
	 * 「班別查詢」
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-shift-type-info", method = RequestMethod.POST)
	public String getShiftTypeInfo() {
		Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();
		List<ShiftTypeSettingVO> voList = new ArrayList<>();
		
		if(requestData.isEmpty()) {
			voList = shiftManageFacade.getShiftTypeInfo(); 
		}else{
			String shiftTypeName = MapUtils.getString(requestData, "shiftTypeName");
			voList = shiftManageFacade.getShiftTypeInfoByshiftTypeName(shiftTypeName); 
		}
		
		String responseData = JsonUtil.toJson(voList);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 「班別新增」
	 * 
	 * @return String
	 */
	@RequestMapping(value="/create-shift-type", method = RequestMethod.POST)
	public String createShiftType() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();
		
		ShiftTypeSettingVO vo = JsonUtil.fromJson(requestData, ShiftTypeSettingVO.class);
		vo.setCreateUser(userId);
		vo.setUpdateUser(userId);
		
		boolean dataMap = shiftManageFacade.createShiftType(vo);
		
		String responseData = JsonUtil.toJson(dataMap);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 「班別修改」
	 * 
	 * @return String
	 */
	@RequestMapping(value="/modify-shift-type", method = RequestMethod.POST)
	public String modifyShiftType() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();

		ShiftTypeSettingVO vo = JsonUtil.fromJson(requestData, ShiftTypeSettingVO.class);
		vo.setCreateUser(userId);
		vo.setUpdateUser(userId);
		
		boolean dataMap = shiftManageFacade.modifyShiftType(vo);

		String responseData = JsonUtil.toJson(dataMap);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 「查詢對應技能關係」
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-skill-mapping-info", method = RequestMethod.POST)
	public String getSkillMappingInfo() {
		Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();
		
		String shiftTypeId = MapUtils.getString(requestData, "shiftTypeId");
		
		Map<String, Object> map = new HashMap<>();
		List<ShiftTypeSkillMapVO> has = shiftManageFacade.getSkillMappingInfoByShiftTypeId(shiftTypeId);
		List<ShiftTypeSkillMapVO> no = shiftManageFacade.getSkillMappingInfoNotInShiftTypeId(shiftTypeId);
		map.put("has", has);
		map.put("no", no);
		
		String responseData = JsonUtil.toJson(map);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 「修改對應技能關係」
	 * 
	 * @return String
	 */
	@RequestMapping(value="/modify-skill-mapping", method = RequestMethod.POST)
	public String modifySkillMapping() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();

		JSONObject jsonObject = new JSONObject(requestData);
		String data = jsonObject.optString("collectionRight");
		List<ShiftTypeSkillMapVO> voList = JsonUtil.fromJsonToList(data, ShiftTypeSkillMapVO.class);
		for (ShiftTypeSkillMapVO vo : voList) {
			vo.setCreateUser(userId);
			vo.setUpdateUser(userId);
		}
		
		boolean dataMap = shiftManageFacade.modifySkillMapping(voList);
		
		String responseData = JsonUtil.toJson(dataMap);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}

}
