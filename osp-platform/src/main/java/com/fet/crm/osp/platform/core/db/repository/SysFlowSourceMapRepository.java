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

package com.fet.crm.osp.platform.core.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fet.crm.osp.platform.core.db.model.SysFlowSourceMap;
import com.fet.crm.osp.platform.core.db.model.SysFlowSourceMapId;

/**
 *
 * @author AndrewLee, Adam Yeh
 */
public interface SysFlowSourceMapRepository extends JpaRepository<SysFlowSourceMap, SysFlowSourceMapId> {

    List<SysFlowSourceMap> findByIdSourceSysId(String sourceSysId);
    
    List<SysFlowSourceMap> findByOrderTypeId(String orderTypeId);

    SysFlowSourceMap findByIdSourceSysIdAndIdSourceProdTypeId(String sourceSysId, String sourceProdTypeId);

}
