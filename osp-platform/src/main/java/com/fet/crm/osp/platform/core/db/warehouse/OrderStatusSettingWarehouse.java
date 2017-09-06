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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.db.model.SysOrderStatusSetting;
import com.fet.crm.osp.platform.core.db.repository.SysOrderStatusSettingRepository;
import com.fet.crm.osp.platform.core.pojo.OrderStatusSettingPOJO;

/**
 * 
 * @author LawrenceLai
 */
@Component
public class OrderStatusSettingWarehouse {
	
	@Autowired
	private SysOrderStatusSettingRepository repository;
	
	public List<OrderStatusSettingPOJO> findAll() {
		List<SysOrderStatusSetting> entityList = repository.findAll();
			
		if (CollectionUtils.isNotEmpty(entityList)) {
			List<OrderStatusSettingPOJO> dataList = new ArrayList<>();
				
			for (SysOrderStatusSetting entity : entityList) {
				OrderStatusSettingPOJO pojo = new OrderStatusSettingPOJO();
					
				BeanUtils.copyProperties(entity, pojo);
					
				dataList.add(pojo);
			}
				
			return dataList; 
		}
			
		return Collections.emptyList();
	}

	public OrderStatusSettingPOJO findByStatusId(String id) {
		SysOrderStatusSetting entity = repository.findByStatusId(id);

		if (entity == null) {
			return new OrderStatusSettingPOJO();
		}
		
		OrderStatusSettingPOJO pojo = new OrderStatusSettingPOJO();
		
		BeanUtils.copyProperties(entity, pojo);
		
		return pojo;
	}

}
