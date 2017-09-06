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
import com.fet.crm.osp.platform.core.db.model.OrderMainOsp;
import com.fet.crm.osp.platform.core.db.repository.OrderMainOspRepository;
import com.fet.crm.osp.platform.core.pojo.OrderMainOspPOJO;

/**
 * [OrderMainOsp] Warehouse
 * 
 * @author LawrenceLai
 */
@Component
public class OrderMainOspWarehouse {

    @Autowired
    OrderMainOspRepository repository;
    
    public OrderMainOspPOJO findByOrderMId(String orderMId) {
    	OrderMainOsp entity = repository.findByOrderMId(orderMId);
    	
    	if (entity != null) {
    		OrderMainOspPOJO pojo = new OrderMainOspPOJO();
    		
    		BeanUtils.copyProperties(entity, pojo);
    		
    		return pojo;
    	}
    	
    	return new OrderMainOspPOJO();
    }

    public boolean save(OrderMainOspPOJO pojo) {
        OrderMainOsp entity = new OrderMainOsp();

        BeanUtils.copyProperties(pojo, entity);

        repository.save(entity);

        return true;
    }

	public boolean batchSave(List<OrderMainOspPOJO> pojoList) {
		if (CollectionUtils.isEmpty(pojoList)) {
			return false;
		}
		
		List<OrderMainOsp> entityList = new ArrayList<>();
		for (OrderMainOspPOJO pojo : pojoList) {
	        OrderMainOsp entity = new OrderMainOsp();
	        BeanUtils.copyProperties(pojo, entity);
	        entityList.add(entity);
		}
        repository.save(entityList);
		
		return true;
	}

    public boolean update(OrderMainOspPOJO pojo) {
    	if (pojo != null) {
    		String orderMId = pojo.getOrderMId();
            OrderMainOsp entity = repository.findOne(orderMId);
            
            String[] ignoreProperties = BeanCopyUtil.null2Ignore(pojo);
            BeanUtils.copyProperties(pojo, entity, ignoreProperties);
            
            repository.save(entity);
    	}
    	
    	return true;
    }

    public OrderMainOspPOJO updateHaveNoFindOne(OrderMainOspPOJO pojo) {
    	if (pojo != null) {
            OrderMainOsp entity = new OrderMainOsp();
            
            String[] ignoreProperties = BeanCopyUtil.null2Ignore(pojo);
            BeanUtils.copyProperties(pojo, entity, ignoreProperties);
            
            repository.save(entity);

            BeanUtils.copyProperties(entity, pojo);
    	}
    	
    	return pojo;
    }
    
    public boolean updateInBatch(List<OrderMainOspPOJO> dataList) {
        for (OrderMainOspPOJO pojo : dataList) {
        	update(pojo);
        }

        return true;
    }
    
    /**
     * add by JoeChiu at 2017-05-17
     * 
     * @param orderMId
     * @return
     */
    public String findPoolKeyByOrderMId(String orderMId) {
    	return repository.findPoolKeyByOrderMId(orderMId);
    }

}
