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

package com.fet.crm.osp.platform.core.service.capacityinfo;

import java.util.List;
import java.util.Map;

/**
 *  [產能運算管理] 服務介面
 * 
 * @author LawrenceLai
 */
public interface ICapacityManagerService {
	
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
	List<Map<String, Object>> queryAgentCapacityInfo(List<String> agentList, String startDate, String endDate);
	
}
