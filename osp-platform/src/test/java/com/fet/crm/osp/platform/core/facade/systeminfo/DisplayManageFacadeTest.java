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
import com.fet.crm.osp.platform.core.vo.systeminfo.component.DisplayVO;

/**
 * [系統內存外顯資訊] 總體服務窗口 測試單元
 * 
 * @author LawrenceLai
 */
public class DisplayManageFacadeTest extends SpringTest {
	
	@Autowired
	private DisplayManageFacade facade;

	@Test
	public void testGetSourceSystemDisplay() {
		List<DisplayVO> dataList = facade.getSourceSystemDisplay();
		
		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}

	@Test
	public void testGetProductTypeDisplay() {
		List<DisplayVO> dataList = facade.getProductTypeDisplay();
		
		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}

	@Test
	public void testGetOrderTypeDisplay() {
		List<DisplayVO> dataList = facade.getOrderTypeDisplay();
		
		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}

	@Test
	public void testGetOperateTypeDisplay() {
		List<DisplayVO> dataList = facade.getOperateTypeDisplay();
		
		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}

	@Test
	public void testGetInvalidOrderStatusDisplay() {
		List<DisplayVO> dataList = facade.getInvalidOrderStatusDisplay();
		
		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}
	
	@Test
	public void testGetOrderStatusDisplay() {
		List<DisplayVO> dataList = facade.getOrderStatusDisplay();
		
		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}
	
	@Test
	public void testGetSkillDisplay() {
		List<DisplayVO> dataList = facade.getSkillDisplay();
		
		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}
	
	@Test
	public void testGetRoleDisplay() {
		List<DisplayVO> dataList = facade.getRoleTypeDisplay();
		
		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}
	
	@Test
	public void testGetProcessReasonDisplay() {
		List<DisplayVO> dataList = facade.getProcessReasonDisplay("FL0007", "080");
		
		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}
	
	@Test
	public void testGetProcessResultDisplay() {
		List<DisplayVO> dataList = facade.getProcessResultDisplay("FL0001", "070");
		
		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}
	
	@Test
	public void testGetOrderTypeDisplayBySourceSysId() {
		String sourceSysId = "AIMS";		
		
		List<DisplayVO> dataList = facade.getOrderTypeDisplayBySourceSysId(sourceSysId);
		
		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}

}
