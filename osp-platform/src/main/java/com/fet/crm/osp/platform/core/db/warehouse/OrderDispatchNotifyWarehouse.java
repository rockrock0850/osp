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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.db.model.OrderDispatchNotify;
import com.fet.crm.osp.platform.core.db.model.OrderDispatchNotifyId;
import com.fet.crm.osp.platform.core.db.repository.OrderDispatchNotifyRepository;
import com.fet.crm.osp.platform.core.pojo.OrderDispatchNotifyPOJO;

/**
 * 
 * @author LawrenceLai
 */
@Component
public class OrderDispatchNotifyWarehouse {
	
	@Autowired
	private OrderDispatchNotifyRepository repository;
	
	public List<OrderDispatchNotifyPOJO> findByProcessUserId(String processUserId) {
		if (StringUtils.isNotBlank(processUserId)) {
			List<OrderDispatchNotify> entityList = repository.findByProcessUserId(processUserId);
			
			if (CollectionUtils.isNotEmpty(entityList)) {
				List<OrderDispatchNotifyPOJO> pojoList = new ArrayList<>();
				
				for (OrderDispatchNotify entity : entityList) {
					OrderDispatchNotifyId entityId = entity.getId(); 
					
					OrderDispatchNotifyPOJO pojo = new OrderDispatchNotifyPOJO();
					
					BeanUtils.copyProperties(entityId, pojo);
					BeanUtils.copyProperties(entity, pojo);
					
					pojoList.add(pojo);
				}
				
				return pojoList;
			}
		}
		
		return Collections.emptyList();
	}
	
	public int deleteDispatchNotifyByBatchNoAndProcessUserId(String batchNo, String processUserId) {
		return repository.deleteDispatchNotifyByBatchNoAndProcessUserId(batchNo, processUserId);
	}

}
