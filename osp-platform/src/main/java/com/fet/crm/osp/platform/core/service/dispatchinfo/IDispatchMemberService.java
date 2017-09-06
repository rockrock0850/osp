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

package com.fet.crm.osp.platform.core.service.dispatchinfo;

import java.util.List;

import com.fet.crm.osp.platform.core.vo.orderinfo.OrderDispatchVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.PauseDispatchVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.OrderDispatchCVO;

/**
 * [分派人員維護] 服務介面
 * 
 * @author LawrenceLai
 */
public interface IDispatchMemberService {
	
	/**
	 * 查詢 分派資訊<br>
	 * 取得條件：
	 * 
	 * @param dispatchCVO
	 * @return OrderDispatchVO
	 */
	List<OrderDispatchVO> queryDispatchInfo(OrderDispatchCVO dispatchCVO);
	
	/**
	 * 執行 案件指派<br>
	 * boolean
	 * 
	 * @param assignVO
	 * @return boolean
	 */
	boolean excutePauseDispatch(List<PauseDispatchVO> pauseDispatchVOLs);

}
