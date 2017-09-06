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

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.platform.SpringTest;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.UserInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.BuzFlowVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzHierarchyEstablishmentVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzRecordContentVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzRecordRoutineVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzStepPageVO;
import com.fet.crm.osp.platform.webapp.vo.session.SessionVO;

/**
 * 頁面資訊」總體服務窗口 測試類別
 * 
 * @author PaulChen
 */
public class BuzFlowManageFacadeTest extends SpringTest {

	@Autowired
	private BuzFlowManageFacade facade;
	
	@Test
	public void testGetServiceBuzFlow() {
		String flowId = "FL0103";
		BuzFlowVO buzFlowVO = facade.getServiceBuzFlow(flowId);
		
		System.out.println(buzFlowVO);
	}

	@Test
	public void testGetServiceBuzContent() {
		String flowId = "FL0040";
		String pageStepId = "OSPL4015T01";
		String subscriberId = "";
		String accountId = "";

		OrderInfoVO orderInfoVO = new OrderInfoVO();
		orderInfoVO.setCustId("1111");
		orderInfoVO.setMsisdn("222222");
		orderInfoVO.setPromotionId("333333");
		orderInfoVO.setIvrCode("444444");
		orderInfoVO.setSourceOrderId("555555");
		
		UserInfoVO userInfoVO = new UserInfoVO();
		userInfoVO.setUserId("999999");
		userInfoVO.setNtAccount("000000");
		
		List<BuzStepPageVO> buzStepPageLs = facade.getServiceBuzStep(flowId, pageStepId, orderInfoVO, userInfoVO, subscriberId, accountId);
		
		System.out.println(buzStepPageLs);
	}
	
	@Test
	public void testGetServiceBuzStepForFunction() {
		String menuId = "OSPL4054";//OSPL4054

		UserInfoVO userInfoVO = new UserInfoVO();
		userInfoVO.setUserId("999999");
		userInfoVO.setNtAccount("000000");

		List<BuzStepPageVO> buzStepPageLs = facade.getServiceBuzStepForFunction(menuId, userInfoVO);

		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(buzStepPageLs));
		System.out.println("==================================================");
	}

	@Test
	public void testGetServiceBuzRecordContent() {
		String contentId = "CONT0025A";
		String orderMId = "";
		
		List<BuzRecordContentVO> dataList = facade.getServiceBuzRecordContent(contentId, orderMId);
		
		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}
	
	@Test
	public void testGetServiceBuzRecordRoutine() {
		String contentId = "CONT0013";
		String userId = "";
		
		List<BuzRecordRoutineVO> dataList = facade.getServiceBuzRecordRoutine(contentId, userId);
		
		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}
	
	@Test
	public void testGetServiceCreateIaLevel() {
		String contentId = "CONT0027C";
		String empNo = "65196";

		List<BuzHierarchyEstablishmentVO> dataList = facade.getServiceCreateIaLevel(contentId, empNo);

		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}

}
