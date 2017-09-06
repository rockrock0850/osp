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

package com.fet.crm.osp.platform.core.facade.systeminfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.platform.core.service.systeminfo.IDisplayManageService;
import com.fet.crm.osp.platform.core.vo.systeminfo.component.DisplayVO;

/**
 * 「系統內存外顯資訊」總體服務窗口
 * 
 * @author LawrenceLai
 */
@Service
@Transactional(readOnly = true)
public class DisplayManageFacade {
	
	@Autowired
	private IDisplayManageService displayManageService;

	/**
	 * 查詢 證號類型 內存外顯值資訊
	 * 
	 * @author Adam
	 * @create date: May 4, 2017
	 *
	 * @return
	 */
	public List<DisplayVO> getCustTypeDisplay() {
		return displayManageService.queryCustTypeDisplay();
	}

	/**
	 * 查詢 建檔頁面的進件系統 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	public List<DisplayVO> getCreationSourceSystemDisplay() {
		return displayManageService.queryCreationSourceSystemDisplay();
	}

	/**
	 * 查詢 進件系統 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	public List<DisplayVO> getSourceSystemDisplay() {
		return displayManageService.querySourceSystemDisplay();
	}
	
	/**
	 * 查詢 產品類別 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	public List<DisplayVO> getProductTypeDisplay() {
		return displayManageService.queryProductTypeDisplay();
	}
	
	/**
	 * 查詢 案件類別 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	public List<DisplayVO> getOrderTypeDisplay() {
		return displayManageService.queryOrderTypeDisplay();
	}
	
	/**
	 * 查詢 案件類別 內存外顯值資訊
	 * 查詢條件 [進件系統代號_SOURCE_SYS_ID] 
	 * 
	 * @return List<DisplayVO>
	 */
	public List<DisplayVO> getOrderTypeDisplayBySourceSysId(String sourceSysId) {
		return displayManageService.queryOrderTypeDisplayBySourceSysId(sourceSysId);
	}
	
	/**
	 * 查詢 交易類型 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	public List<DisplayVO> getOperateTypeDisplay() {
		return displayManageService.queryOperateTypeDisplay();
	};
	
	/**
	 * 查詢 所有案件狀態 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	public List<DisplayVO> getOrderStatusDisplay() {
		return displayManageService.queryAllOrderStatusDisplay();
	}
	
	/**
	 * 查詢 未結案類別 案件狀態 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	public List<DisplayVO> getInvalidOrderStatusDisplay() {
		return displayManageService.queryInvalidOrderStatusDisplay();
	}
	
	/**
	 * 查詢 技能 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	public List<DisplayVO> getSkillDisplay() {
		return displayManageService.querySkillDisplay();
	}
	
	/**
	 * 查詢 角色 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	public List<DisplayVO> getRoleTypeDisplay() {
		return displayManageService.queryRoleTypeDisplay();
	}
	
	/**
	 * 查詢 處理原因 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	public List<DisplayVO> getProcessReasonDisplay(String flowId, String orderStatus) {
		return displayManageService.queryProcessReasonDisplay(flowId, orderStatus);
	}
	
	/**
	 * 查詢 處理結果 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	public List<DisplayVO> getProcessResultDisplay(String flowId, String orderStatus) {
		return displayManageService.queryProcessResultDisplay(flowId, orderStatus);
	}
	
	   
    /**
     * 查詢 KPI設定_日期類別 內存外顯值資訊
     * 
     * @return List<DisplayVO>
     */
    public List<DisplayVO> getOptionKpiDate() {
        return displayManageService.queryOptionKpiDate();
    }
    
    /**
     * 查詢判斷進件時間 內存外顯值資訊
     * 
     * @return List<DisplayVO>
     */
    public List<DisplayVO> getOptionBeforeCreateDate(String dayTimeType) {
        return displayManageService.queryOptionBeforeCreateDate(dayTimeType);
    }
    
    /**
     * 查詢 KPI設定_起算時間 內存外顯值資訊
     * 
     * @return List<DisplayVO>
     */
    public List<DisplayVO> getOptionStartCountTime() {
        return displayManageService.queryOptionStartCountTime();
    }
    
    /**
     * 查詢 通知業務 內存外顯值資訊
     * 
     * @return List<DisplayVO>
     */
	public List<DisplayVO> getComboTypeDisplay(String type) {
        return displayManageService.queryOptionComboTypeDisplay(type);
	}
	
}
