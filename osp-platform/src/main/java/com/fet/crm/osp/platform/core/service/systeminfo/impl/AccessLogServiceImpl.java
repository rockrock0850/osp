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

package com.fet.crm.osp.platform.core.service.systeminfo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.db.warehouse.SurroundingLogWarehouse;
import com.fet.crm.osp.platform.core.db.warehouse.SysActionLogWarehouse;
import com.fet.crm.osp.platform.core.pojo.ActionLogPOJO;
import com.fet.crm.osp.platform.core.pojo.SurroundingDetailLogPOJO;
import com.fet.crm.osp.platform.core.pojo.SurroundingMainLogPOJO;
import com.fet.crm.osp.platform.core.service.systeminfo.IAccessLogService;
import com.fet.crm.osp.platform.core.vo.systeminfo.LogInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.SurroundingDetailLogVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.SurroundingMainLogVO;

/**
 * 
 * @author LawrenceLai
 */
@Service
public class AccessLogServiceImpl implements IAccessLogService {
	
	@Autowired
	private SysActionLogWarehouse sysActionLogWarehouse;
	
	@Autowired
	private SurroundingLogWarehouse surroundingLogWarehouse;

	@Override
	public boolean createAccessLog(LogInfoVO logInfo) {
		if (logInfo != null) {
			String threadId = logInfo.getThreadId();
			String lodId = IdentifierIdUtil.getUuid();
			String userId = logInfo.getUserId();                 
			String classify = logInfo.getClassify();               
			String operation = logInfo.getOperation();              
			Date logDate = logInfo.getLogDate();                  
			String ipAddress = logInfo.getIpAddress();              
			String duration = String.valueOf(logInfo.getDuration());               
			String requestContent = logInfo.getRequestContent();
			
			ActionLogPOJO pojo = new ActionLogPOJO();
			
			pojo.setRequestId(threadId);
			pojo.setLogId(lodId);
			pojo.setUserId(userId);
			pojo.setClassify(classify);
			pojo.setOperation(operation);
			pojo.setLogDate(logDate);
			pojo.setIpAddress(ipAddress);
			pojo.setDuration(duration);
			pojo.setRequestContent(requestContent);
			
			sysActionLogWarehouse.save(pojo);
		}
		
		return true;
	}

	@Override
	public boolean createSurroundingLog(SurroundingMainLogVO mainLogVO) {
		if (mainLogVO != null) {
			SurroundingMainLogPOJO mainLogPOJO = new SurroundingMainLogPOJO();
			
			BeanUtils.copyProperties(mainLogVO, mainLogPOJO);
			
			List<SurroundingDetailLogVO> detailVOList = mainLogVO.getDetailLogList();
			
			if (CollectionUtils.isNotEmpty(detailVOList)) {
				List<SurroundingDetailLogPOJO> detailPOJOList = new ArrayList<>();
				
				for (SurroundingDetailLogVO detailLogVO : detailVOList) {
					SurroundingDetailLogPOJO detailLogPOJO = new SurroundingDetailLogPOJO();
					
					BeanUtils.copyProperties(detailLogVO, detailLogPOJO);
					
					detailPOJOList.add(detailLogPOJO);
				}
				
				mainLogPOJO.setDetailLogList(detailPOJOList);
				
				surroundingLogWarehouse.save(mainLogPOJO);
			}
		}
		
		return true;
	}

}
