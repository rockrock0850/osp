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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.db.model.SysProdTypeMap;
import com.fet.crm.osp.platform.core.db.model.SysProdTypeMapId;
import com.fet.crm.osp.platform.core.db.repository.SysProdTypeMapRepository;
import com.fet.crm.osp.platform.core.pojo.ProdTypeMapPOJO;

/**
 * 
 * @author LawrenceLai
 * @author AllenChen at 2017-0508
 */
@Component
public class ProdTypeMapWarehouse {

	@Autowired
	private SysProdTypeMapRepository repository;
	
	public List<ProdTypeMapPOJO> findAll() {
		List<SysProdTypeMap> entityList = repository.findAll();
		
		if (CollectionUtils.isNotEmpty(entityList)) {
			List<ProdTypeMapPOJO> dataList = new ArrayList<>();
			
			for (SysProdTypeMap entity : entityList) {
				SysProdTypeMapId entityId = entity.getId();
				ProdTypeMapPOJO pojo = new ProdTypeMapPOJO();
				
				BeanUtils.copyProperties(entityId, pojo);
				BeanUtils.copyProperties(entity, pojo);
				
				dataList.add(pojo);
			}
			
			return dataList; 
		}
		
		return Collections.emptyList();
	}

	public Map<String, String> getProdTypeMap() {
		List<SysProdTypeMap> entityList = repository.findAll();

		if (CollectionUtils.isNotEmpty(entityList)) {
			Map<String, String> dataMap = new HashMap<String, String>();

			for (SysProdTypeMap entity : entityList) {
				SysProdTypeMapId entityId = entity.getId();

				String sourceProdTypeId =  entityId.getSourceProdTypeId();
				String sourceProdTypeName = entity.getSourceProdTypeName();

				dataMap.put(sourceProdTypeId, sourceProdTypeName);
			}

			return dataMap; 
		}

		return Collections.emptyMap();
	}

}
