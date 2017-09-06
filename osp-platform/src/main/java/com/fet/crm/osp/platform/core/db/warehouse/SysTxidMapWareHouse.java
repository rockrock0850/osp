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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.db.model.SysTxidMap;
import com.fet.crm.osp.platform.core.db.model.SysTxidMapId;
import com.fet.crm.osp.platform.core.db.repository.SysTxidMapRepository;
import com.fet.crm.osp.platform.core.pojo.SysTxidMapPOJO;

/**
 * SysTxidMapWareHouse 倉庫
 *
 * @author AndrewLee
 */
@Component
public class SysTxidMapWareHouse {

    @Autowired
    private SysTxidMapRepository repository;

    public SysTxidMapPOJO save(SysTxidMapPOJO pojo) {
    	SysTxidMap entity = new SysTxidMap();
    	SysTxidMapId id = new SysTxidMapId();

        BeanUtils.copyProperties(pojo, id);
        BeanUtils.copyProperties(pojo, entity);
        entity.setId(id);
    	repository.save(entity);

        return pojo;
    }

    public boolean batchSave(List<SysTxidMapPOJO> pojoList) {
    	List<SysTxidMap> entitys = new ArrayList<>();
    	for (SysTxidMapPOJO pojo : pojoList) {
    		SysTxidMap entity = new SysTxidMap();
			SysTxidMapId id = entity.getId();

	        BeanUtils.copyProperties(pojo, id);		
	        BeanUtils.copyProperties(pojo, entity);	
	        entitys.add(entity);
		}
    	
        repository.save(entitys);

        return true;
    }

}
