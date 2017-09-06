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

import com.fet.crm.osp.platform.core.vo.orderinfo.OrderInfoVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderProcessVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.UserInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.BuzFlowVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzHierarchyEstablishmentVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzRecordContentVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzRecordRoutineVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzStepPageVO;

/**
 * 「頁面資訊」服務介面
 * 
 * @author PaulChen
 */
public interface IBuzFlowService {
	
	/**
	 * 取得「作業流程服務頁」
	 * 
	 * @param flowId
	 *            流程代碼
	 * @return BuzFlowVO
	 */
	public BuzFlowVO getServiceBuzFlow(String flowId);
	
	/**
	 * 取得「流程內容服務頁」
	 * 
	 * @param stepId
	 *            頁籤代碼
	 * @return List<BuzStepPageVO>
	 */
	List<BuzStepPageVO> getServiceBuzStep(String flowId, String stepId, OrderInfoVO orderInfoVO, UserInfoVO userInfoVO, String subscriberId, String accountId);
	
	/**
	 * 取得「流程內容服務資訊」
	 * 使用於功能業務流程，非案件業務流程
	 * 
	 * @param menuId
	 * @return List<BuzStepPageVO>
	 */
	List<BuzStepPageVO> getServiceBuzStepForFunction(String menuId, UserInfoVO userInfoVO);
		
	/**
	 * 取得「工單內容記錄」
	 * 
	 * @param contentId
	 * @param orderMId
	 * @return List<BuzRecordContentVO>
	 */
	List<BuzRecordContentVO> getServiceBuzRecordContent(String contentId, String orderMId);
	
	/**
	 * 取得「日常作業記錄」
	 * 
	 * @param contentId
	 * @param userId
	 * @return List<BuzRecordRoutineVO>
	 */
	List<BuzRecordRoutineVO> getServiceBuzRecordRoutine(String contentId, String userId);

	/**
	 * 取得「階層建立」
	 * 
	 * @param contentId
	 * @param empNo
	 * @return List<BuzHierarchyEstablishmentVO>
	 */
	List<BuzHierarchyEstablishmentVO> getServiceCreateIaLevel(String contentId, String empNo);

	/**
	 * 取得FlowId
	 * 
	 * @author Adam
	 * @create date: May 19, 2017
	 *
	 * @param orderProcessVO
	 * @param infoVO
	 * @return
	 */
	public String getFlowId(OrderProcessVO orderProcessVO, OrderInfoVO infoVO);
	
}
