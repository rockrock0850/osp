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

import com.fet.crm.osp.platform.core.db.model.SysFlowReasonMap;
import com.fet.crm.osp.platform.core.db.repository.SysFlowReasonMapRepository;
import com.fet.crm.osp.platform.core.pojo.SysFlowReasonMapPOJO;

/**
 * SysFlowReasonMapWareHouse 倉庫
 *
 * @author Adam Yeh
 */
@Component
public class SysFlowReasonMapWareHouse {

    @Autowired
    private SysFlowReasonMapRepository repository;

    public List<SysFlowReasonMapPOJO> findByFlowIdAndOrderStatusAndReasonType(String flowId, String orderStatus, String reasonType) {
        List<SysFlowReasonMapPOJO> pojoList = new ArrayList<>();

        List<SysFlowReasonMap> dataList = repository.findByFlowIdAndOrderStatusAndReasonType(flowId, orderStatus, reasonType);

        for (SysFlowReasonMap entity : dataList) {
        	SysFlowReasonMapPOJO pojo = new SysFlowReasonMapPOJO();
            BeanUtils.copyProperties(entity, pojo);

            pojoList.add(pojo);
        }

        return pojoList;
    }

}
