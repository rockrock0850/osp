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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.platform.core.service.systeminfo.IAccessLogService;
import com.fet.crm.osp.platform.core.vo.systeminfo.LogInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.SurroundingMainLogVO;

/**
 * 
 * @author LawrenceLai
 */
@Service
public class AccessLogFacade {
	
	@Autowired
	private IAccessLogService accessLogService;
	
	/**
	 * 寫入ACCESS LOG 至資料庫
	 * 
	 * @param logInfo
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean accessLog(LogInfoVO logInfo) {
		return accessLogService.createAccessLog(logInfo);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean createSurroundingLog(SurroundingMainLogVO mainLogVO) {
		return accessLogService.createSurroundingLog(mainLogVO);
	}

}
