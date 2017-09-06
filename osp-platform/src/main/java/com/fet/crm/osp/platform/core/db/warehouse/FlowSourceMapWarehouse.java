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

import com.fet.crm.osp.platform.core.db.model.SysFlowSourceMap;
import com.fet.crm.osp.platform.core.db.model.SysFlowSourceMapId;
import com.fet.crm.osp.platform.core.db.repository.SysFlowSourceMapRepository;
import com.fet.crm.osp.platform.core.pojo.SysFlowSourcePOJO;

/**
 * [進件來源] 倉庫
 *
 * @author AndrewLee
 */
@Component
public class FlowSourceMapWarehouse {

    @Autowired
    private SysFlowSourceMapRepository repository;
    
    public List<SysFlowSourcePOJO> findByIdSourceSysId(String sourceSysId) {
    	if (StringUtils.isNotBlank(sourceSysId)) {
    		List<SysFlowSourceMap> entityList = repository.findByIdSourceSysId(sourceSysId);
    		
    		if (CollectionUtils.isNotEmpty(entityList)) {
    			List<SysFlowSourcePOJO> pojoList = new ArrayList<>();
    			
    			for (SysFlowSourceMap entity : entityList) {
    				SysFlowSourcePOJO pojo = new SysFlowSourcePOJO();
    				
    				SysFlowSourceMapId entityId = new SysFlowSourceMapId();
    				
    				BeanUtils.copyProperties(entityId, pojo);
    				BeanUtils.copyProperties(entity, pojo);
    				
    				pojoList.add(pojo);
    			}
    			
    			return pojoList;
    		}
    	}
    	
    	return Collections.emptyList();
    }

    public SysFlowSourcePOJO findByIdSourceSysIdAndSourceProdTypeId(String sourceSysId, String sourceProdTypeId) {
		if (StringUtils.isBlank(sourceSysId) && StringUtils.isBlank(sourceProdTypeId)) {
			return new SysFlowSourcePOJO();
		}
    	
    	SysFlowSourceMap entity = repository.findByIdSourceSysIdAndIdSourceProdTypeId(sourceSysId, sourceProdTypeId);
    	
		if (entity == null) {
			return new SysFlowSourcePOJO();
		}
    	
    	SysFlowSourceMapId id = entity.getId();
    	SysFlowSourcePOJO pojo = new SysFlowSourcePOJO();

    	BeanUtils.copyProperties(id, pojo);
    	BeanUtils.copyProperties(entity, pojo);

        return pojo;
    }

    public List<SysFlowSourcePOJO> findByOrderTypeId(String orderTypeId) {
        List<SysFlowSourceMap> dataLs = repository.findByOrderTypeId(orderTypeId);
        List<SysFlowSourcePOJO> pojoLs = new ArrayList<>();

        for (SysFlowSourceMap entity : dataLs) {
            SysFlowSourcePOJO pojo = new SysFlowSourcePOJO();
            String sourceSysId = entity.getId().getSourceSysId();
            String flowId = entity.getId().getFlowId();
            String sourceProdTypeId = entity.getId().getSourceProdTypeId();

            BeanUtils.copyProperties(entity, pojo);

            pojo.setSourceSysId(sourceSysId);
            pojo.setFlowId(flowId);
            pojo.setSourceProdTypeId(sourceProdTypeId);

            pojoLs.add(pojo);
        }

        return pojoLs;
    }
}
