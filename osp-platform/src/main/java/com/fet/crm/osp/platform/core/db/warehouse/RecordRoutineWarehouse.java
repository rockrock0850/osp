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

import com.fet.crm.osp.platform.core.db.model.BuzRecordRoutine;
import com.fet.crm.osp.platform.core.db.model.BuzRecordRoutineId;
import com.fet.crm.osp.platform.core.db.repository.BuzRecordRoutineRepository;
import com.fet.crm.osp.platform.core.pojo.BuzRecordRoutinePOJO;

/**
 * 
 * @author LawrenceLai
 */
@Component
public class RecordRoutineWarehouse {
	
	@Autowired
	private BuzRecordRoutineRepository repository;
	
	public boolean save(List<BuzRecordRoutinePOJO> dataList) {
		if (CollectionUtils.isNotEmpty(dataList)) {
			List<BuzRecordRoutine> entityList = new ArrayList<>();
			
			for (BuzRecordRoutinePOJO pojo : dataList) {
				BuzRecordRoutine entity = new BuzRecordRoutine();
				BuzRecordRoutineId entityId = new BuzRecordRoutineId();

				BeanUtils.copyProperties(pojo, entityId);
				BeanUtils.copyProperties(pojo, entity);
				
				entity.setId(entityId);
				entityList.add(entity);
			}
			repository.save(entityList);
		}
		
		return true;
	}

}
