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

import com.fet.crm.osp.platform.core.db.model.SysRoleManualMapping;
import com.fet.crm.osp.platform.core.db.model.SysRoleManualMappingId;
import com.fet.crm.osp.platform.core.db.repository.SysRoleManualMappingRepository;
import com.fet.crm.osp.platform.core.pojo.SysRoleManualMappingPOJO;

/**
 * [SysRoleManualMapping] 倉庫
 * 
 * @author AndrewLee
 */
@Component
public class SysRoleManualMappingWareHouse {

    @Autowired
    private SysRoleManualMappingRepository repository;

    public boolean save(SysRoleManualMappingPOJO pojo) {
        String empNo = pojo.getEmpno();
        String roleId = pojo.getRoleId();

        SysRoleManualMapping entity = new SysRoleManualMapping();
        SysRoleManualMappingId id = new SysRoleManualMappingId();

        BeanUtils.copyProperties(pojo, entity);
        id.setEmpno(empNo);
        id.setRoleId(roleId);
        entity.setId(id);

        repository.save(entity);

        return true;
    }

    public boolean batchSave(List<SysRoleManualMappingPOJO> pojoLs) {
        for (SysRoleManualMappingPOJO pojo : pojoLs) {
            save(pojo);
        }

        return true;
    }

    public boolean remove(SysRoleManualMappingPOJO pojo) {
        String empNo = pojo.getEmpno();
        String roleId = pojo.getRoleId();

        SysRoleManualMappingId id = new SysRoleManualMappingId();

        id.setEmpno(empNo);
        id.setRoleId(roleId);

        repository.delete(id);

        return true;
    }
}
