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

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.common.util.BeanCopyUtil;
import com.fet.crm.osp.platform.core.db.model.NotifyMain;
import com.fet.crm.osp.platform.core.db.repository.NotifyMainRepository;
import com.fet.crm.osp.platform.core.pojo.NotifyMainPOJO;

/**
 * NotifyMainWareHouse 倉庫
 *
 * @author Adam Yeh
 */
@Component
public class NotifyMainWareHouse {

    @Autowired
    private NotifyMainRepository repository;

	public boolean save(NotifyMainPOJO pojo) {
		if (pojo == null) {
			return false;
		}
    	
		NotifyMain entity = new NotifyMain();
        String[] ignoreProperties = BeanCopyUtil.null2Ignore(pojo);
        BeanUtils.copyProperties(pojo, entity, ignoreProperties);
        
        repository.save(entity);
    	
    	return true;
    }
    
    public boolean updateInBatch(List<NotifyMainPOJO> dataList) {
        for (NotifyMainPOJO pojo : dataList) {
        	save(pojo);
        }

        return true;
    }
    
}
