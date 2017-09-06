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

import com.fet.crm.osp.platform.core.db.model.OrderOperateRecords;
import com.fet.crm.osp.platform.core.db.repository.OrderOperateRecordsRepository;
import com.fet.crm.osp.platform.core.pojo.OrderOperateRecordsPOJO;

/**
 * 
 * @author LawrenceLai
 */
@Component
public class OrderOperateRecordsWarehouse {

	@Autowired
	private OrderOperateRecordsRepository repository;

	public boolean save(List<OrderOperateRecordsPOJO> dataList) {
		if (CollectionUtils.isNotEmpty(dataList)) {
			List<OrderOperateRecords> entityList = new ArrayList<>();
			
			for(OrderOperateRecordsPOJO pojo : dataList) {
				OrderOperateRecords entity = new OrderOperateRecords();

				BeanUtils.copyProperties(pojo, entity);

				entityList.add(entity);
				
				repository.save(entityList);
			}
		}
		
		return true;
	}
	
	public boolean save(OrderOperateRecordsPOJO pojo) {
		OrderOperateRecords entity = new OrderOperateRecords();

		BeanUtils.copyProperties(pojo, entity);

		repository.save(entity);

		return true;
	}

}
