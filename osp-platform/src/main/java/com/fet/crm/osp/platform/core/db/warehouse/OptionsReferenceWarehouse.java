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

import com.fet.crm.osp.platform.core.db.model.SysOptionsReference;
import com.fet.crm.osp.platform.core.db.model.SysOptionsReferenceId;
import com.fet.crm.osp.platform.core.db.repository.SysOptionsReferenceRepository;
import com.fet.crm.osp.platform.core.pojo.OptionsReferencePOJO;

/**
 * 
 * @author LawrenceLai
 */
@Component
public class OptionsReferenceWarehouse {
	
	@Autowired
	private SysOptionsReferenceRepository repository;
	
	/**
	 * 
	 * @return List<OptionsReferencePOJO>
	 */
	public List<OptionsReferencePOJO> findByOptionsTypeList(List<String> optionsType) {
		List<SysOptionsReference> entityList = repository.findByIdOptionsTypeIn(optionsType);
		
		if (CollectionUtils.isNotEmpty(entityList)) {
			List<OptionsReferencePOJO> dataList = new ArrayList<>();
			
			for (SysOptionsReference entity : entityList) {
				SysOptionsReferenceId entityId = entity.getId();
				OptionsReferencePOJO pojo = new OptionsReferencePOJO();
				
				BeanUtils.copyProperties(entityId, pojo);
				BeanUtils.copyProperties(entity, pojo);
				
				dataList.add(pojo);
			}
			
			return dataList; 
		}
		
		return Collections.emptyList();
	}
	
	/**
	 * 
	 * @return List<OptionsReferencePOJO>
	 */
	public List<OptionsReferencePOJO> findByOptionsType(String optionsType) {
	    List<SysOptionsReference> entityList = repository.findByIdOptionsType(optionsType);
	    
	    if (CollectionUtils.isNotEmpty(entityList)) {
	        List<OptionsReferencePOJO> dataList = new ArrayList<>();
	        
	        for (SysOptionsReference entity : entityList) {
	            SysOptionsReferenceId entityId = entity.getId();
	            OptionsReferencePOJO pojo = new OptionsReferencePOJO();
	            
	            BeanUtils.copyProperties(entityId, pojo);
	            BeanUtils.copyProperties(entity, pojo);
	            
	            dataList.add(pojo);
	        }
	        
	        return dataList; 
	    }
	    
	    return Collections.emptyList();
	}

}
