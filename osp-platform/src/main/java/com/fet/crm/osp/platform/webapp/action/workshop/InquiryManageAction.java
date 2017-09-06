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

package com.fet.crm.osp.platform.webapp.action.workshop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fet.crm.osp.platform.core.common.util.DateUtil;
import com.fet.crm.osp.platform.core.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.common.util.PropertiesUtil;
import com.fet.crm.osp.platform.core.facade.capacityinfo.CapacityManagerFacade;
import com.fet.crm.osp.platform.core.facade.systeminfo.HRManageFacade;
import com.fet.crm.osp.platform.core.vo.systeminfo.MemberInfoVO;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;

/**
 * [查詢管理] 共用控制器
 * 
 * @author LawrenceLai
 */
@Controller
@RequestMapping("/workshop/inquiry")
public class InquiryManageAction {
	
	@Value("${temp.store.path.csv}")
	private String storePathCSV;
	
	@Autowired
	private HRManageFacade hrManageFacade;
	
	@Autowired
	private CapacityManagerFacade capacityManagerFacade;
	
	@RequestMapping(value = "/service-agent-capacity-inquiry", method = {RequestMethod.POST, RequestMethod.GET})
	public String ServicePersonalCapacityInquiry() {
		String userId = HttpSessionHandler.getSessionInfo().getUserId();
		
		List<MemberInfoVO> memberList = hrManageFacade.getMemberInfoByLeaderEmpNo(userId);
		
		HttpRequestHandler.put("memberList", memberList);
		
		return ForwardUtil.OSPLV2005.getView();
	}

	@RequestMapping(value = "/get-agent-capacity", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String getPersonalCapacity() {
		// STEP1. Get Session Info 
		String userId = HttpSessionHandler.getSessionInfo().getUserId();
		
		// STEP2. Get Request Info 
		Map<String, Object> requestMap = HttpRequestHandler.getJsonToMap();
		String empNo = MapUtils.getString(requestMap, "empNo");
		String startDate = MapUtils.getString(requestMap, "startDate");
		String endDate = MapUtils.getString(requestMap, "endDate");
		
		// STEP3. 取得產能查詢對象
		// 預設為頁面拉選對象帳號，若是頁面未拉選查詢對象，則取得登入使用者，組織階級對映下所有Member成員
		List<String> agentList = new ArrayList<>();
		agentList.add(empNo);
		
		if (StringUtils.isBlank(empNo)) {
			agentList.clear();
			
			List<MemberInfoVO> memberList = hrManageFacade.getMemberInfoByLeaderEmpNo(userId);
			
			for (MemberInfoVO memberVO : memberList) {
				String agentEmpNo = memberVO.getEmpNo();
				
				agentList.add(agentEmpNo);
			}
		} 
		
		// STEP4. 取得查詢成員對象之產能資訊
		List<Map<String, Object>> capacityList = capacityManagerFacade.getAgentCapacityInfo(agentList, startDate, endDate);
		
		return JsonUtil.toJson(capacityList);
	}
	
	@RequestMapping(value = "/export-agent-capacity", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String exportAgentCapacity(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> requestMap = HttpRequestHandler.getJsonToMap();
		String empNo = MapUtils.getString(requestMap, "empNo");
		String startDate = MapUtils.getString(requestMap, "startDate");
		String endDate = MapUtils.getString(requestMap, "endDate");
		
		List<String> agentList = new ArrayList<>();
		agentList.add(empNo);
		
		if (StringUtils.isBlank(empNo)) {
			String userId = HttpSessionHandler.getSessionInfo().getUserId();
			
			List<MemberInfoVO> memberList = hrManageFacade.getMemberInfoByLeaderEmpNo(userId);
			
			for (MemberInfoVO memberVO : memberList) {
				String agentEmpNo = memberVO.getEmpNo();
				
				agentList.add(agentEmpNo);
			}
		}
		
		List<Map<String, Object>> capacityList = capacityManagerFacade.getAgentCapacityInfo(agentList, startDate, endDate);
		
		String fileKey = "CSV_" + IdentifierIdUtil.getUuid();
	    String fileName = fileKey + ".csv";
		
	    OutputStream outputStream = null;
	    
	    File csvDir = new File(storePathCSV);
	    
	    if (!csvDir.exists()) {
	    	csvDir.mkdirs();
	    }
	    
		try {
			byte[] BOM_UTF8 = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
			
			File csvFile = new File(storePathCSV, fileName);
			
			outputStream = new FileOutputStream(csvFile);
			outputStream.write(BOM_UTF8);
			
			if (CollectionUtils.isNotEmpty(capacityList)) {
				StringBuffer titleSB = new StringBuffer();
				titleSB.append("處理人員");
				titleSB.append(", ");
				titleSB.append("日期");
				titleSB.append(", ");
				titleSB.append("實際完成產能");
				titleSB.append(", ");
				titleSB.append("個人待處理預計完成產能");
				titleSB.append(", ");
				titleSB.append("前兩者加總產能");
				titleSB.append(", ");
				titleSB.append("當日已派件的預計處裡產能");
			    titleSB.append("\n");
				
			    outputStream.write(titleSB.toString().getBytes("UTF-8"));
				
				for (Map<String, Object> rowMap : capacityList) {
					StringBuffer dateSB = new StringBuffer();
					
					String userName = MapUtils.getString(rowMap, "USER_NAME");
					String dtDate = MapUtils.getString(rowMap, "DT_DATE");
					String realKPI = MapUtils.getString(rowMap, "REAL_KPI");
					String processKPI = MapUtils.getString(rowMap, "PROCESS_KPI");
					String kpiSum = MapUtils.getString(rowMap, "KPI_SUM");
					String dispatchKPI = MapUtils.getString(rowMap, "DISPATCH_KPI");
					
					dateSB.append(userName);
					dateSB.append(", ");
					dateSB.append(dtDate);
					dateSB.append(", ");
					dateSB.append(realKPI);
					dateSB.append(", ");
					dateSB.append(processKPI);
					dateSB.append(", ");
					dateSB.append(kpiSum);
					dateSB.append(", ");
					dateSB.append(dispatchKPI);
				    dateSB.append("\n");
					
				    outputStream.write(dateSB.toString().getBytes("UTF-8"));
				}
			}
			
			outputStream.flush();
			
			HttpSessionHandler.put(fileKey, csvFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
				outputStream = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return fileKey;
	}
	
	@RequestMapping(value = "/download-agent-capacity", method = {RequestMethod.GET})
	public @ResponseBody void downloadAgentCapacity(HttpServletRequest request, HttpServletResponse response, @RequestParam String fileKey) {
		try {
			HttpSession session = HttpSessionHandler.getHttpSession();
			String userId = HttpSessionHandler.getSessionInfo().getUserId();
			
			String filePath = (String) session.getAttribute(fileKey);
			
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			
			String fileName = "個人產能狀態查詢_" + userId + "_" + DateUtil.fromDate(new Date(), "yyyyMMddHHmmss") + ".csv";
		    response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));//指定下載檔案的名稱為fileName的宣告變數
		    
		    ServletOutputStream outputStream = response.getOutputStream();
		        
		    FileCopyUtils.copy(fis, outputStream);
		    
		    session.removeAttribute(fileKey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
