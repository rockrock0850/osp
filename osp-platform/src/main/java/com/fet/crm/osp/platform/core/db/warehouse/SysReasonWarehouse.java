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

import com.fet.crm.osp.platform.core.db.model.SysReason;
import com.fet.crm.osp.platform.core.db.repository.SysReasonRepository;
import com.fet.crm.osp.platform.core.pojo.SysReasonPOJO;

/**
 * 
 * @author Adam Yeh
 */
@Component
public class SysReasonWarehouse {
	
	@Autowired
	private SysReasonRepository repository;

	public SysReasonPOJO findByReasonId(String reasonId) {
		SysReason entity = repository.findByReasonId(reasonId);

		if (entity == null) {
			return new SysReasonPOJO();
		}

		SysReasonPOJO pojo = new SysReasonPOJO();
		
		BeanUtils.copyProperties(entity, pojo);
		
		return pojo;
	}
    
}
