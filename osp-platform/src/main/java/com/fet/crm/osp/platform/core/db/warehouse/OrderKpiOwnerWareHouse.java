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
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.common.util.BeanCopyUtil;
import com.fet.crm.osp.platform.core.db.model.NotifyDetail;
import com.fet.crm.osp.platform.core.db.model.OrderKpiOwner;
import com.fet.crm.osp.platform.core.db.model.OrderKpiOwnerId;
import com.fet.crm.osp.platform.core.db.repository.OrderKpiOwnerRepository;
import com.fet.crm.osp.platform.core.pojo.NotifyDetailPOJO;
import com.fet.crm.osp.platform.core.pojo.OrderKpiOwnerPOJO;

/**
 * NotifyDetailWareHouse 倉庫
 *
 * @author Adam Yeh
 */
@Component
public class OrderKpiOwnerWareHouse {

	@Autowired
    private OrderKpiOwnerRepository repository;

	public OrderKpiOwnerPOJO findByOrderMId(String orderMId) {
		OrderKpiOwnerPOJO pojo = new OrderKpiOwnerPOJO();
		
		OrderKpiOwner entity = repository.findByIdOrderMId(orderMId);
		
		if (entity != null) {
			BeanUtils.copyProperties(entity.getId(), pojo);
			BeanUtils.copyProperties(entity, pojo);
		}
		
		return pojo;
	}
    
	public boolean save(OrderKpiOwnerPOJO pojo) {
		if (pojo != null) {
			OrderKpiOwner entity = new OrderKpiOwner();
			OrderKpiOwnerId entityId = new OrderKpiOwnerId();
			
			BeanUtils.copyProperties(pojo, entityId);
			BeanUtils.copyProperties(pojo, entity);
			
			entity.setId(entityId);
			
			repository.save(entity);
		}
		
		return true;
	}
    
	public boolean saveInBatch(List<OrderKpiOwnerPOJO> dataList) {
		if (CollectionUtils.isEmpty(dataList)) {
			return false;
		}
    	
    	List<OrderKpiOwner> list = new ArrayList<>();
    	for (OrderKpiOwnerPOJO pojo : dataList) {
    		OrderKpiOwner entity = new OrderKpiOwner();
    		BeanUtils.copyProperties(pojo, entity);
    		
    		list.add(entity);
		}
        repository.save(list);

        return true;
    }
    
}
