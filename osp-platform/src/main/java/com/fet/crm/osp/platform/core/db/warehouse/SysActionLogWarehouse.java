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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.db.model.SysActionLog;
import com.fet.crm.osp.platform.core.db.model.SysActionLogId;
import com.fet.crm.osp.platform.core.db.repository.SysActionLogRepository;
import com.fet.crm.osp.platform.core.pojo.ActionLogPOJO;

/**
 * 
 * @author LawrenceLai
 */
@Component
public class SysActionLogWarehouse {

	@Autowired
	private SysActionLogRepository repository;
	
	public boolean save(ActionLogPOJO pojo) {
		if (pojo != null) {
			SysActionLog entity = new SysActionLog();
			SysActionLogId entityId = new SysActionLogId();
			
			BeanUtils.copyProperties(pojo, entityId);
			BeanUtils.copyProperties(pojo, entity);
			
			entity.setId(entityId);
			
			repository.save(entity);
		}
		
		return true;
	}
	
}
