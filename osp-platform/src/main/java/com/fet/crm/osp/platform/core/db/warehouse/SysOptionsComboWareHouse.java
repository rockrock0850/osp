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

import com.fet.crm.osp.platform.core.db.model.SysOptionsCombo;
import com.fet.crm.osp.platform.core.db.model.SysOptionsComboId;
import com.fet.crm.osp.platform.core.db.repository.SysOptionsComboRepository;
import com.fet.crm.osp.platform.core.pojo.SysOptionsComboPOJO;

/**
 * SysOptionsComboWareHouse 倉庫
 *
 * @author Adam Yeh
 */
@Component
public class SysOptionsComboWareHouse {

    @Autowired
    private SysOptionsComboRepository repository;

    public List<SysOptionsComboPOJO> findByComboType(String type) {
    	List<SysOptionsCombo> entityList = repository.findByIdComboType(type);
			
		if (CollectionUtils.isNotEmpty(entityList)) {
			List<SysOptionsComboPOJO> dataList = new ArrayList<>();
				
			for (SysOptionsCombo entity : entityList) {
				SysOptionsComboPOJO pojo = new SysOptionsComboPOJO();
				SysOptionsComboId id = entity.getId();

				BeanUtils.copyProperties(id, pojo);
				BeanUtils.copyProperties(entity, pojo);
					
				dataList.add(pojo);
			}
				
			return dataList; 
		}
			
		return Collections.emptyList();
    }

	public List<SysOptionsComboPOJO> findByComboTypeAndComboValue(String type, String value) {
		if (StringUtils.isEmpty(type) || StringUtils.isEmpty(value)) {
			return Collections.emptyList();
		}
		
		List<SysOptionsCombo> entitys = repository.findByIdComboTypeAndIdComboValue(type, value);
		List<SysOptionsComboPOJO> pojos = new ArrayList<>();
		
		for (SysOptionsCombo entity : entitys) {
			SysOptionsComboPOJO pojo = new SysOptionsComboPOJO();
			SysOptionsComboId id = entity.getId();
			
			BeanUtils.copyProperties(id, pojo);
			BeanUtils.copyProperties(entity, pojo);
			pojos.add(pojo);
		}
		
		return pojos;
	}
    
}
