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

import com.fet.crm.osp.platform.core.db.model.SysMenu;
import com.fet.crm.osp.platform.core.db.repository.SysMenuRepository;
import com.fet.crm.osp.platform.core.pojo.MenuPOJO;

/**
 * 
 * @author LawrenceLai
 */
@Component
public class MenuWarehouse {

	@Autowired
	private SysMenuRepository repository; 
	
	public List<MenuPOJO> findAll() {
		List<SysMenu> entityList = repository.findAll();
		
		if (CollectionUtils.isNotEmpty(entityList)) {
			List<MenuPOJO> dataList = new ArrayList<>();
			
			for (SysMenu entity : entityList) {
				MenuPOJO pojo = new MenuPOJO();
				
				BeanUtils.copyProperties(entity, pojo);
				
				dataList.add(pojo);
			}
			
			return dataList;
		}
		
		return Collections.emptyList();
	}
	
	public MenuPOJO findById(String id) {
		MenuPOJO pojo = new MenuPOJO();
		
		SysMenu entity = repository.findOne(id);
		
		BeanUtils.copyProperties(entity, pojo);
		
		return pojo;
	}
	
}
