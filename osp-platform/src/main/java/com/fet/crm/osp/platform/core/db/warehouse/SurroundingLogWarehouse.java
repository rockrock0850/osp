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

package com.fet.crm.osp.platform.core.db.warehouse;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.db.model.SysSurroundingDetailLog;
import com.fet.crm.osp.platform.core.db.model.SysSurroundingMainLog;
import com.fet.crm.osp.platform.core.db.repository.SysSurroundingDetailLogRepository;
import com.fet.crm.osp.platform.core.db.repository.SysSurroundingMainLogRepository;
import com.fet.crm.osp.platform.core.pojo.SurroundingDetailLogPOJO;
import com.fet.crm.osp.platform.core.pojo.SurroundingMainLogPOJO;

/**
 * 
 * @author LawrenceLai
 */
@Component
public class SurroundingLogWarehouse {
	
	@Autowired
	private SysSurroundingMainLogRepository mainLogRepository;
	@Autowired
	private SysSurroundingDetailLogRepository detailLogRepository;
	
	public boolean save(SurroundingMainLogPOJO mainLogPOJO) {
		if (mainLogPOJO != null) {
			saveMain(mainLogPOJO);
			
			List<SurroundingDetailLogPOJO> detailList = mainLogPOJO.getDetailLogList();
			
			for(SurroundingDetailLogPOJO detailLogPOJO : detailList) {
				saveDetail(detailLogPOJO);
			}
		}
		
		return true;
	}
	
	private boolean saveMain(SurroundingMainLogPOJO mainLogPOJO) {
		if (mainLogPOJO != null) {
			SysSurroundingMainLog entity = new SysSurroundingMainLog();
			
			BeanUtils.copyProperties(mainLogPOJO, entity);
			
			mainLogRepository.save(entity);
		}
		
		return true;
	}
	
	private boolean saveDetail(SurroundingDetailLogPOJO detailLogPOJO) {
		if (detailLogPOJO != null) {
			SysSurroundingDetailLog entity = new SysSurroundingDetailLog();
			
			BeanUtils.copyProperties(detailLogPOJO, entity);
			
			detailLogRepository.save(entity);
		}
	
		return true;
	}

}
