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

import com.fet.crm.osp.platform.core.service.systeminfo.IBuzFlowService;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.UserInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.BuzFlowVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzHierarchyEstablishmentVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzRecordContentVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzRecordRoutineVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzStepPageVO;

/**
 * 「頁面資訊」總體服務窗口
 * 
 * @author PaulChen, AllenChen
 */
@Service
public class BuzFlowManageFacade {

	@Autowired
	private IBuzFlowService buzFlowService;
	
	/**
	 * 取得「作業流程服務頁」
	 * 
	 * @param flowId
	 *            流程代碼
	 * @return BuzFlowVO
	 */
	@Transactional(readOnly = true)
	public BuzFlowVO getServiceBuzFlow(String flowId) {
		return buzFlowService.getServiceBuzFlow(flowId);
	}

	/**
	 * 取得「流程內容服務頁」
	 * 
	 * @param stepId
	 *            頁籤代碼
	 * @return List<BuzStepPageVO>
	 */
	@Transactional(readOnly = true)
	public List<BuzStepPageVO> getServiceBuzStep(String flowId, String stepId, OrderInfoVO orderInfoVO, 
			UserInfoVO userInfoVO, String subscriberId, String accountId) {
		return buzFlowService.getServiceBuzStep(flowId, stepId, orderInfoVO, userInfoVO, subscriberId, accountId);
	}

	/**
	 * 取得「流程內容服務資訊」
	 * 使用於功能業務流程，非案件業務流程
	 * 
	 * @param menuId
	 * @param userInfoVO
	 * 
	 * @return List<BuzStepPageVO>
	 */
	@Transactional(readOnly = true)
	public List<BuzStepPageVO> getServiceBuzStepForFunction(String menuId, UserInfoVO userInfoVO) {
		return buzFlowService.getServiceBuzStepForFunction(menuId, userInfoVO);
	}
	
	/**
	 * 取得「工單內容記錄」
	 * 
	 * @param contentId
	 * @param orderMId
	 * @return List<BuzRecordContentVO>
	 */
	@Transactional(readOnly = true)
	public List<BuzRecordContentVO> getServiceBuzRecordContent(String contentId, String orderMId) {
		return buzFlowService.getServiceBuzRecordContent(contentId, orderMId);
	}
	
	/**
	 * 取得「日常作業記錄」
	 * 
	 * @param contentId
	 * @param userId
	 * @return List<BuzRecordRoutineVO>
	 */
	@Transactional(readOnly = true)
	public List<BuzRecordRoutineVO> getServiceBuzRecordRoutine(String contentId, String userId) {
		return buzFlowService.getServiceBuzRecordRoutine(contentId, userId);
	}

	/**
	 * 取得「階層建立資訊」
	 * 
	 * @param contentId
	 * @param empNo
	 * @return List<BuzHierarchyEstablishmentVO>
	 */
	@Transactional(readOnly = true)
	public List<BuzHierarchyEstablishmentVO> getServiceCreateIaLevel(String contentId, String empNo) {
		return buzFlowService.getServiceCreateIaLevel(contentId, empNo);
	}

}