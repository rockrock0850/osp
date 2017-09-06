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

package com.fet.crm.osp.kernel.core.service.system.impl;

import org.springframework.stereotype.Service;

import com.fet.crm.osp.kernel.core.service.system.IAccessLogService;
//TODO - Gary: [待處理] System Application Log ...
//import com.fet.arap.system.core.db.sys.dao.SysApLogRepository;
//import com.fet.arap.system.core.db.sys.model.SysApLog;
//import com.fet.arap.system.core.db.sys.model.SysApLogId;
import com.fet.crm.osp.kernel.core.vo.LogInfoVO;

/**
 * 日誌管理 實作類別
 * 
 * @author PaulChen
 */
@Service
public class AccessLogServiceImpl implements IAccessLogService {

    // TODO - Gary: [待處理] System Application Log ...
    
//	@Autowired
//	private SysApLogRepository sysApLogRepo;
//	
//	@Override
	public void createAccessLog(LogInfoVO logInfo) {
//		List<SysApLog> modelLs = new ArrayList<SysApLog>();
//		
//		String classify = logInfo.getClassify(); // category
//		// String operation = logInfo.getOperation();
//		Date eventDt = logInfo.getEventDt();
//		String ip = logInfo.getIp();
//		String userId = logInfo.getUserId();
//		String threadId = logInfo.getThreadId();
//		String duraction = String.valueOf(logInfo.getDuration()); 
//		
//		List<String> eventDesc = logInfo.getLogMessage();
//		int i = 0;
//		for(String s : eventDesc) {
//			SysApLogId id = new SysApLogId(threadId, UuidUtil.getUuid());
//			SysApLog model = new SysApLog();
//			model.setId(id);
//			model.setClassify(classify);
//			model.setEventDesc(s);
//			model.setIpAddress(ip);
//			model.setLogDate(eventDt);
//			model.setUserId(userId);
//			model.setStepSequence(BigDecimal.valueOf(i++));
//			model.setDuration(duraction);
//			
//			modelLs.add(model);
//		}
//		
//		sysApLogRepo.save(modelLs);
	}

}
