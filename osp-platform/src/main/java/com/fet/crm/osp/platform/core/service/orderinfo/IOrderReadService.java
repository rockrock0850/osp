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

package com.fet.crm.osp.platform.core.service.orderinfo;

import java.util.List;
import java.util.Map;

import com.fet.crm.osp.platform.core.vo.orderinfo.condition.TodoOrderCVO;

/**
 * 「訂單資訊檢視讀取」服務介面.
 * 
 * @author LawrenceLai
 */
public interface IOrderReadService {
	
	/**
	 *  查詢 部門待處理 訂單資訊<br>
	 * 
	 * @param todoOrderCVO 頁面查詢條件
	 * @param userId 功能查詢必要條件
	 * 
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryDeptTodoOrderInfo(TodoOrderCVO todoOrderCVO, String userId);
	
	/**
	 *  查詢 部門待處理 急件件數 資訊<br>
	 * 
	 * @param todoOrderCVO
	 * @return int
	 */
	int queryDeptCriticalOrderCount(TodoOrderCVO todoOrderCVO, String userId);

	/**
	 * 查詢 部門待處理 逾期件件數 資訊<br>
	 * 
	 * @param todoOrderCVO
	 * @return int
	 */
	int queryDeptOverdueOrderCount(TodoOrderCVO todoOrderCVO, String userId);
	
}
