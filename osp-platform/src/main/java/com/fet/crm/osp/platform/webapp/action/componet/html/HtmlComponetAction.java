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

package com.fet.crm.osp.platform.webapp.action.componet.html;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fet.crm.osp.platform.core.facade.systeminfo.DisplayManageFacade;
import com.fet.crm.osp.platform.core.vo.systeminfo.component.DisplayVO;
import com.fet.crm.osp.platform.webapp.action.AbstractOSPAction;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;

/**
 * Jsp下拉選單內容產生器
 * 
 * @author Adam Yeh
 */
@Controller
@RequestMapping("/componet/html")
public class HtmlComponetAction extends AbstractOSPAction{
	
	@Autowired
	private DisplayManageFacade displayManageFacade;
	
	/**
	 * 查詢 通知它業者 內存外顯值資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-option-combo-type", method = RequestMethod.GET)
	public String getOptionComboType() {
		List<DisplayVO> voList = displayManageFacade.getComboTypeDisplay("MAIL_TELECOM");
		
		HttpRequestHandler.put("responseData", voList);
		
		return ForwardUtil.OPTION_SELECTION.getView();
	}

	/**
	 * 查詢 證號類型 內存外顯值資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-option-cust-type", method = RequestMethod.GET)
	public String getOptionCustType() {
		List<DisplayVO> voList = displayManageFacade.getCustTypeDisplay();
		
		HttpRequestHandler.put("responseData", voList);
		
		return ForwardUtil.OPTION_SELECTION.getView();
	}

	/**
	 * 查詢 建檔頁面的進件系統 內存外顯值資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-creation-option-source-system", method = RequestMethod.GET)
	public String getCreationOptionSourceSystem() {
		List<DisplayVO> voList = displayManageFacade.getCreationSourceSystemDisplay();
		
		HttpRequestHandler.put("responseData", voList);
		
		return ForwardUtil.OPTION_SELECTION.getView();
	}

	/**
	 * 查詢 進件系統 內存外顯值資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-option-source-system", method = RequestMethod.GET)
	public String getOptionSourceSystem() {
		List<DisplayVO> voList = displayManageFacade.getSourceSystemDisplay();
		
		HttpRequestHandler.put("responseData", voList);
		
		return ForwardUtil.OPTION_SELECTION.getView();
	}
	
	/**
	 * 查詢 產品類別 內存外顯值資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-option-product-type", method = RequestMethod.GET)
	public String getOpertionProductType() {
		List<DisplayVO> voList = displayManageFacade.getProductTypeDisplay();
		
		HttpRequestHandler.put("responseData", voList);
		
		return ForwardUtil.OPTION_SELECTION.getView();
	}
	
	/**
	 * 查詢 案件類別 內存外顯值資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-option-order-type", method = RequestMethod.GET)
	public String getOptionOrderType() {
		List<DisplayVO> voList = displayManageFacade.getOrderTypeDisplay();
		
		HttpRequestHandler.put("responseData", voList);
		
		return ForwardUtil.OPTION_SELECTION.getView();
	}
	
	/**
	 * 查詢共用按鈕 [ 更改案件類別 ] 的案件類別內存外顯值資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-option-order-type-by-source-sys-id", method = RequestMethod.GET)
	public String getOrderTypeDisplayBySourceSysId(@RequestParam String sourceSysId) {
		List<DisplayVO> voList = displayManageFacade.getOrderTypeDisplayBySourceSysId(sourceSysId);
		
		HttpRequestHandler.put("responseData", voList);
		
		return ForwardUtil.OPTION_SELECTION.getView();
	}
	
	/**
	 * 查詢 交易類型 內存外顯值資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-option-operater-type", method = RequestMethod.GET)
	public String getOptionOperaterType() {
		List<DisplayVO> voList = displayManageFacade.getOperateTypeDisplay();
		
		HttpRequestHandler.put("responseData", voList);
		
		return ForwardUtil.OPTION_SELECTION.getView();
	}
	
	/**
	 * 查詢 案件狀態 內存外顯值資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-option-order-status", method = RequestMethod.GET)
	public String getOptionOrderStatus() {
		List<DisplayVO> voList = displayManageFacade.getOrderStatusDisplay();
		
		HttpRequestHandler.put("responseData", voList);
		
		return ForwardUtil.OPTION_SELECTION.getView();
	}
	
	/**
	 * 查詢 未結案類別 案件狀態 內存外顯值資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-invalid-option-order-status", method = RequestMethod.GET)
	public String getInvalidOptionOrderStatus() {
		List<DisplayVO> voList = displayManageFacade.getInvalidOrderStatusDisplay();
		
		HttpRequestHandler.put("responseData", voList);
		
		return ForwardUtil.OPTION_SELECTION.getView();
	}
	
	/**
	 * 查詢 技能 內存外顯值資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-option-skill", method = RequestMethod.GET)
	public String getOptionSkill() {
		List<DisplayVO> voList = displayManageFacade.getSkillDisplay();
		
		HttpRequestHandler.put("responseData", voList);
		
		return ForwardUtil.OPTION_SELECTION.getView();
	}
	
	/**
	 * 查詢 角色 內存外顯值資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-option-role", method = RequestMethod.GET)
	public String getOptionRole() {
		List<DisplayVO> voList = displayManageFacade.getRoleTypeDisplay();
		
		HttpRequestHandler.put("responseData", voList);
		
		return ForwardUtil.OPTION_SELECTION.getView();
	}
	
	/**
	 * 查詢 處理原因 內存外顯值資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-option-process-reason", method = RequestMethod.GET)
	public String getOptionProcessReason(@RequestParam String flowId, @RequestParam String orderStatus) {
		List<DisplayVO> voList = displayManageFacade.getProcessReasonDisplay(flowId, orderStatus);
		
		HttpRequestHandler.put("responseData", voList);
		
		return ForwardUtil.OPTION_SELECTION.getView();
	}
	
	/**
	 * 查詢 處理結果 內存外顯值資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-option-process-result", method = RequestMethod.GET)
	public String getOptionProcessResult(@RequestParam String flowId, @RequestParam String orderStatus) {
		List<DisplayVO> voList = displayManageFacade.getProcessResultDisplay(flowId, orderStatus);
		
		HttpRequestHandler.put("responseData", voList);
		
		return ForwardUtil.OPTION_SELECTION.getView();
	}
	
	   
    /**
     * 查詢 KPI設定_日期類別 內存外顯值資訊
     * 
     * @return String
     */
    @RequestMapping(value="/get-option-kpi-date", method = RequestMethod.GET)
    public String getOptionKpiDate() {
        List<DisplayVO> voList = displayManageFacade.getOptionKpiDate();
        
        HttpRequestHandler.put("responseData", voList);
        
        return ForwardUtil.OPTION_SELECTION.getView();
    }
    
    /**
     * 查詢 判斷進件時間 內存外顯值資訊
     * 
     * @return String
     */
    @RequestMapping(value="/get-option-before-create-date", method = RequestMethod.GET)
    public String getOptionBeforeCreateDate(@RequestParam String dayTimeType) {
        List<DisplayVO> voList = displayManageFacade.getOptionBeforeCreateDate(dayTimeType);
        
        HttpRequestHandler.put("responseData", voList);
        
        return ForwardUtil.OPTION_SELECTION.getView();
    }
    
    /**
     * 查詢 KPI設定_起算時間 內存外顯值資訊
     * 
     * @return String
     */
    @RequestMapping(value="/get-option-start-count-time", method = RequestMethod.GET)
    public String getOptionStartCountTime() {
        List<DisplayVO> voList = displayManageFacade.getOptionStartCountTime();
        
        HttpRequestHandler.put("responseData", voList);
        
        return ForwardUtil.OPTION_SELECTION.getView();
    }

}
