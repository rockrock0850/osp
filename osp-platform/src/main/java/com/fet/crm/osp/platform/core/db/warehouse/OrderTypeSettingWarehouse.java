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
import com.fet.crm.osp.platform.core.db.model.SysOrderTypeSetting;
import com.fet.crm.osp.platform.core.db.repository.SysOrderTypeSettingRepository;
import com.fet.crm.osp.platform.core.pojo.SysOrderTypeSettingPOJO;

/**
 * [SysOrderTypeSetting] 倉庫
 *
 * @author AndrewLee
 */
@Component
public class OrderTypeSettingWarehouse {

    @Autowired
    private SysOrderTypeSettingRepository repository;
    
    public List<SysOrderTypeSettingPOJO> findAll() {
		List<SysOrderTypeSetting> entityList = repository.findAll();
		
		if (CollectionUtils.isNotEmpty(entityList)) {
			List<SysOrderTypeSettingPOJO> dataList = new ArrayList<>();
			
			for (SysOrderTypeSetting entity : entityList) {
				SysOrderTypeSettingPOJO pojo = new SysOrderTypeSettingPOJO();
				
				BeanUtils.copyProperties(entity, pojo);
				
				dataList.add(pojo);
			}
			
			return dataList; 
		}
		
		return Collections.emptyList();
	}

    public boolean update(SysOrderTypeSettingPOJO pojo) {
        SysOrderTypeSetting entity = repository.findByOrderTypeId(pojo.getOrderTypeId());

        String[] ignoreProperties = BeanCopyUtil.null2Ignore(pojo);
        
        BeanUtils.copyProperties(pojo, entity, ignoreProperties);
        
        repository.save(entity);
        
        return true;
    }
    
}
