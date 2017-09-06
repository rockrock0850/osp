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

package com.fet.crm.osp.platform.core.facade.capacityinfo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.platform.SpringTest;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;

/**
 * 
 * @author LawrenceLai
 */
public class CapacityManagerFacadeTest extends SpringTest {
	
	@Autowired
	private CapacityManagerFacade capacityManagerFacade;

	@Test
	public void testGetAgentCapacityInfo() {
		List<String> agentList = new ArrayList<>();
		agentList.add("65196");
		
		String startDate = "2017-05-01";
		String endDate = "2017-05-10";
		
		List<Map<String, Object>> dataList = capacityManagerFacade.getAgentCapacityInfo(agentList, startDate, endDate);
		
		System.out.println("=======================================================================================");
		System.out.println(JsonUtil.toJson(dataList));
		System.out.println("=======================================================================================");
	}

}
