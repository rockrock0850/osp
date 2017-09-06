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
import com.fet.crm.osp.platform.core.vo.systeminfo.MemberInfoVO;

/**
 * [人力資源管理] 總體服務窗口 測試單元
 * 
 * @author LawrenceLai
 */
public class HRManageFacadeTest extends SpringTest {
	
	@Autowired 
	private HRManageFacade facade;

	@Test
	public void testGetMemberInfoBy7331Team() {
		List<MemberInfoVO> dataList = facade.getMemberInfoBy7331Team();
		
		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}

	@Test
	public void testGetMemberInfoBy7331TeamWithoutBeSettedRoleMember() {
		String roleId = "R-20170321-4346ef";
		List<MemberInfoVO> dataList = facade.getMemberInfoBy7331TeamWithoutBeSettedRoleMember(roleId);

		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println(dataList.size());
		System.out.println("==================================================");
	}

	@Test
	public void testGetShiftTypeByUserIdAndSysDate() {
		String userId = "75047";
		
		String shiftType = facade.getShiftTypeByUserIdAndSysDate(userId);
		
		System.out.println("==================================================");
		System.out.println("shiftType = " + shiftType);
		System.out.println("==================================================");
	}
	
	@Test
	public void testGetMemberInfoByLeaderEmpNo() {
		String userId = "60564";
		
		List<MemberInfoVO> dataList = facade.getMemberInfoByLeaderEmpNo(userId);
		
		System.out.println("==================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("==================================================");
	}

}
