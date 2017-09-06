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

package com.fet.crm.osp.platform.core.service.systeminfo;

import java.util.List;

import com.fet.crm.osp.platform.core.vo.systeminfo.component.DisplayVO;

/**
 * [系統內存外顯資訊] 服務介面
 * 
 * @author LawrenceLai
 */
public interface IDisplayManageService {

	/**
	 * 查詢 進件系統 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	List<DisplayVO> querySourceSystemDisplay();
	
	/**
	 * 查詢 產品類別 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	List<DisplayVO> queryProductTypeDisplay();
	
	/**
	 * 查詢 案件類別 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	List<DisplayVO> queryOrderTypeDisplay();
	
	/**
	 * 查詢 案件類別 內存外顯值資訊
	 * 查詢條件 [進件系統代號_SOURCE_SYS_ID] 
	 * 
	 * @return List<DisplayVO>
	 */
	List<DisplayVO> queryOrderTypeDisplayBySourceSysId(String sourceSysId);
	
	/**
	 * 查詢 交易類型 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	List<DisplayVO> queryOperateTypeDisplay();
	
	/**
	 * 查詢 所有案件狀態 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	List<DisplayVO> queryAllOrderStatusDisplay();
	
	/**
	 * 查詢 未結案類 案件狀態 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	List<DisplayVO> queryInvalidOrderStatusDisplay();
	
	/**
	 * 查詢 技能 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	List<DisplayVO> querySkillDisplay();
	
	/**
	 * 查詢 角色 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	List<DisplayVO> queryRoleTypeDisplay();

	/**
	 * 查詢 處理原因 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	List<DisplayVO> queryProcessReasonDisplay(String flowId, String orderStatus);
	
	/**
	 * 查詢 處理結果 內存外顯值資訊
	 * 
	 * @return List<DisplayVO>
	 */
	List<DisplayVO> queryProcessResultDisplay(String flowId, String orderStatus);
	
	   
    /**
     * 查詢  KPI設定_日期類別 內存外顯值資訊
     * 
     * @return List<DisplayVO>
     */
    List<DisplayVO> queryOptionKpiDate();
    
    /**
     * 查詢 判斷進件時間 內存外顯值資訊
     * 
     * @return List<DisplayVO>
     */
    List<DisplayVO> queryOptionBeforeCreateDate(String dayTimeType);
    
    /**
     * 查詢 KPI設定_起算時間 內存外顯值資訊
     * 
     * @return List<DisplayVO>
     */
    List<DisplayVO> queryOptionStartCountTime();

    /**
     * 查詢 建檔頁面的進件系統 內存外顯值資訊
     * 
     * @author Adam
     * @create date: May 4, 2017
     *
     * @return
     */
	List<DisplayVO> queryCreationSourceSystemDisplay();

	/**
	 * 查詢 證號類型 內存外顯值資訊
	 * 
	 * @author Adam
	 * @create date: May 4, 2017
	 *
	 * @return
	 */
	List<DisplayVO> queryCustTypeDisplay();

	/**
	 * 查詢  通知業務 內存外顯值資訊
	 * 
	 * @author Adam
	 * @create date: May 4, 2017
	 *
	 * @return
	 */
	List<DisplayVO> queryOptionComboTypeDisplay(String type);
	
}
