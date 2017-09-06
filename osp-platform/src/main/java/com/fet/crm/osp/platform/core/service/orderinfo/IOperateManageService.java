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

import com.fet.crm.osp.platform.core.vo.systeminfo.OperateManageVO;

/**
 * 
 * @author Adam Yeh
 */
public interface IOperateManageService {
	
	public boolean createContent(OperateManageVO vo);
	
}
