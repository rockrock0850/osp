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

import com.fet.crm.osp.platform.core.db.model.BuzRecordContent;
import com.fet.crm.osp.platform.core.db.model.BuzRecordContentId;
import com.fet.crm.osp.platform.core.db.repository.BuzRecordContentRepository;
import com.fet.crm.osp.platform.core.pojo.BuzRecordContentPOJO;

/**
 * 
 * @author LawrenceLai
 */
@Component
public class RecordContentWarehouse {
	
	@Autowired
	private BuzRecordContentRepository repository;
    
    public List<BuzRecordContentPOJO> findByOrderMId(String orderMId) {
    	List<BuzRecordContent> entityList = repository.findByIdOrderMId(orderMId);
    	
    	if(CollectionUtils.isEmpty(entityList)) {
    		return Collections.emptyList();
		}
    	
    	List<BuzRecordContentPOJO> pojoList = new ArrayList<>();
    	for (BuzRecordContent entity : entityList) {
    		BuzRecordContentPOJO pojo = new BuzRecordContentPOJO();
    		
    		BeanUtils.copyProperties(entity, pojo);
    		pojoList.add(pojo);
		}
		
		return pojoList;
    }
	
	public boolean save(List<BuzRecordContentPOJO> dataList) {
		if (CollectionUtils.isNotEmpty(dataList)) {
			List<BuzRecordContent> entityList = new ArrayList<>();
			
			for (BuzRecordContentPOJO pojo : dataList) {
				BuzRecordContent entity = new BuzRecordContent();
				BuzRecordContentId entityId = new BuzRecordContentId();

				BeanUtils.copyProperties(pojo, entityId);
				BeanUtils.copyProperties(pojo, entity);

				entity.setId(entityId);
				
				entityList.add(entity);
			}
			
			repository.save(entityList);
		}
		
		return true;
	}
	
	public boolean save(BuzRecordContentPOJO pojo) {
		if (pojo != null) {
			BuzRecordContent entity = new BuzRecordContent();
			BuzRecordContentId entityId = new BuzRecordContentId();

			BeanUtils.copyProperties(pojo, entityId);
			BeanUtils.copyProperties(pojo, entity);

			entity.setId(entityId);

			repository.save(entity);
		}
		
		return true;
	}
	
	public boolean deleteByOrderMId(String orderMId) {
		List<BuzRecordContent> list = repository.deleteByIdOrderMId(orderMId);
		
		return true;
	}

}
