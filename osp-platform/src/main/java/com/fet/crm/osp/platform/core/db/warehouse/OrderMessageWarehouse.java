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

import com.fet.crm.osp.platform.core.db.model.OrderMessage;
import com.fet.crm.osp.platform.core.db.repository.OrderMessageRepository;
import com.fet.crm.osp.platform.core.pojo.OrderMessagePOJO;

/**
 * 
 * @author LawrenceLai
 */
@Component
public class OrderMessageWarehouse {
	
	@Autowired
	private OrderMessageRepository repository;
	
	public OrderMessagePOJO findByOrderMId(String orderMId) {
		OrderMessage entity = repository.findByOrderMId(orderMId);
		
		if (entity == null) {
			return null;
		}
		
		OrderMessagePOJO pojo = new OrderMessagePOJO();
		BeanUtils.copyProperties(entity, pojo);
		
		return pojo;
	}
	
	public boolean save(OrderMessagePOJO pojo) {
		if (pojo != null) {
			OrderMessage entity = new OrderMessage();

			BeanUtils.copyProperties(pojo, entity);

			repository.save(entity);
		}
		
		return true;
	}

	public boolean delete(String orderMId) {
		repository.deleteByOrderMId(orderMId);
		
		return true;
	}
	
}
