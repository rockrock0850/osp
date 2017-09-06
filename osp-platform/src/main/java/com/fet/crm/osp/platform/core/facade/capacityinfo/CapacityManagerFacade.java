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

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.platform.core.service.capacityinfo.ICapacityManagerService;

/**
 * [產能運算管理] 總體服務窗口
 * 
 * @author LawrenceLai
 */
@Service
public class CapacityManagerFacade {

	@Autowired
	private ICapacityManagerService capacityManagerService;
	
	/**
	 * 產能查詢
	 * 依據查詢條件取得期間KPI產能資訊
	 * 
	 * @param agentList
	 * @param startDate
	 * @param endDate
	 * 
	 * @return List<Map<String, Object>>
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getAgentCapacityInfo(List<String> agentList, String startDate, String endDate) {
		return capacityManagerService.queryAgentCapacityInfo(agentList, startDate, endDate);
	}
	
}