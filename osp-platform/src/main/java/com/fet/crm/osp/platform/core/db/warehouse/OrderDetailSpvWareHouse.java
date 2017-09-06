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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.fet.crm.osp.platform.core.db.model.OrderDetailSpv;
import com.fet.crm.osp.platform.core.db.repository.OrderDetailSpvRepository;
import com.fet.crm.osp.platform.core.pojo.OrderDetailSpvPOJO;

/**
 * [OrderDeatailSpv] Warehouse
 * 
 * @author JoeChiu
 *
 */
@Component
public class OrderDetailSpvWareHouse {
	
	@Autowired
	private OrderDetailSpvRepository repository;
	
	public List<OrderDetailSpvPOJO> findByPoolKey(String poolKey) {
		List<OrderDetailSpv> entityLs = repository.findByPoolKey(poolKey);
		
		if(!CollectionUtils.isEmpty(entityLs)) {
			List<OrderDetailSpvPOJO> pojoLs = new ArrayList<OrderDetailSpvPOJO>();
			
			for(OrderDetailSpv entity : entityLs) {
				OrderDetailSpvPOJO pojo = new OrderDetailSpvPOJO();
	    		
	    		BeanUtils.copyProperties(entity, pojo);
	    		
	    		pojoLs.add(pojo);
			}
			
			return pojoLs;
		}
		
		return Collections.emptyList();
	}

}
