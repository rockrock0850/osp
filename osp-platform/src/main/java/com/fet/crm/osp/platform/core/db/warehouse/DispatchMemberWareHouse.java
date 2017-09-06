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

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.fet.crm.osp.platform.core.db.model.DispatchMemberConfig;
import com.fet.crm.osp.platform.core.db.repository.DispatchMemberConfigRepository;
import com.fet.crm.osp.platform.core.pojo.DispatchMemberConfigPOJO;

/**
 * [DispatchMember] WareHouse
 *
 * @author AndrewLee
 */
@Component
public class DispatchMemberWareHouse {

    @Autowired
    DispatchMemberConfigRepository repository;
    
    public boolean saveInBatch(List<DispatchMemberConfigPOJO> dataList) {
    	if (!CollectionUtils.isEmpty(dataList)) {
    		for (DispatchMemberConfigPOJO pojo : dataList) {
    			save(pojo);
    		}
    	}

        return true;
    }
    
    public boolean save(DispatchMemberConfigPOJO pojo) {
    	if (pojo != null) {
    		DispatchMemberConfig entity = new DispatchMemberConfig();
    		BeanUtils.copyProperties(pojo, entity);
    		
    		repository.save(entity);
    	}
    	
        return true;
    }

}