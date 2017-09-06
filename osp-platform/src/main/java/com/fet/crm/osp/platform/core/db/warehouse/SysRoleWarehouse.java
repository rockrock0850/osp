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

import com.fet.crm.osp.platform.core.common.util.BeanCopyUtil;
import com.fet.crm.osp.platform.core.db.model.SysRole;
import com.fet.crm.osp.platform.core.db.repository.SysRoleRepository;
import com.fet.crm.osp.platform.core.pojo.SysRolePOJO;

/**
 * [SysRole] Warehouse
 * 
 * @author LawrenceLai
 */
@Component
public class SysRoleWarehouse {

    @Autowired
    private SysRoleRepository repository;
    
    public List<SysRolePOJO> findAll() {
		List<SysRole> entityList = repository.findAll();
			
		if (CollectionUtils.isNotEmpty(entityList)) {
			List<SysRolePOJO> dataList = new ArrayList<>();
				
			for (SysRole entity : entityList) {
				SysRolePOJO pojo = new SysRolePOJO();
					
				BeanUtils.copyProperties(entity, pojo);
					
				dataList.add(pojo);
			}
				
			return dataList; 
		}
			
		return Collections.emptyList();
	}

    public boolean save(SysRolePOJO pojo) {
        SysRole entity = new SysRole();
        BeanUtils.copyProperties(pojo, entity);

        repository.save(entity);

        return true;
    }

    public boolean batchSave(List<SysRolePOJO> pojoLs) {
        for (SysRolePOJO pojo : pojoLs) {
            save(pojo);
        }

        return true;
    }

    public boolean update(SysRolePOJO pojo) {
        SysRole entity = repository.findOne(pojo.getRoleId());

        String[] ignoreProperties = BeanCopyUtil.null2Ignore(pojo);

        BeanUtils.copyProperties(pojo, entity, ignoreProperties);

        repository.saveAndFlush(entity);

        return true;
    }

}
