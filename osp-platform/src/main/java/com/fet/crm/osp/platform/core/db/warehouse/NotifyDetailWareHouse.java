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

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.common.util.BeanCopyUtil;
import com.fet.crm.osp.platform.core.db.model.NotifyDetail;
import com.fet.crm.osp.platform.core.db.repository.NotifyDetailRepository;
import com.fet.crm.osp.platform.core.pojo.NotifyDetailPOJO;

/**
 * OrderKpiOwnerWareHouse 倉庫
 *
 * @author Adam Yeh
 */
@Component
public class NotifyDetailWareHouse {

    @Autowired
    private NotifyDetailRepository repository;

    public boolean save(NotifyDetailPOJO pojo) {
    	if (pojo != null) {
    		NotifyDetail entity = new NotifyDetail();
            String[] ignoreProperties = BeanCopyUtil.null2Ignore(pojo);
            BeanUtils.copyProperties(pojo, entity, ignoreProperties);
            
            repository.save(entity);
    	}
    	
    	return true;
    }
    
	public boolean saveInBatch(List<NotifyDetailPOJO> dataList) {
		if (CollectionUtils.isEmpty(dataList)) {
			return false;
		}
    	
    	List<NotifyDetail> list = new ArrayList<>();
    	for (NotifyDetailPOJO pojo : dataList) {
    		NotifyDetail entity = new NotifyDetail();
    		BeanUtils.copyProperties(pojo, entity);
    		
    		list.add(entity);
		}
        repository.save(list);

        return true;
    }
    
}
