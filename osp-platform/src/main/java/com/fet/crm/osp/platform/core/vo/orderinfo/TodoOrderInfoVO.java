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

package com.fet.crm.osp.platform.core.vo.orderinfo;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author LawrenceLai
 */
public class TodoOrderInfoVO {

	private List<Map<String, Object>> todoList; // 待處理資料清單
	private int criticalOrderCount; 			// 急件件數
	private int overdueOrderCount; 				// 逾期件件數

	public List<Map<String, Object>> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<Map<String, Object>> todoList) {
		this.todoList = todoList;
	}

	public int getCriticalOrderCount() {
		return criticalOrderCount;
	}

	public void setCriticalOrderCount(int criticalOrderCount) {
		this.criticalOrderCount = criticalOrderCount;
	}

	public int getOverdueOrderCount() {
		return overdueOrderCount;
	}

	public void setOverdueOrderCount(int overdueOrderCount) {
		this.overdueOrderCount = overdueOrderCount;
	}

}
