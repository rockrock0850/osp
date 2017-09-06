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

import com.fet.crm.osp.platform.core.db.model.SysRoleMenuRef;
import com.fet.crm.osp.platform.core.db.model.SysRoleMenuRefId;
import com.fet.crm.osp.platform.core.db.repository.SysRoleMenuRefRepository;
import com.fet.crm.osp.platform.core.pojo.SysRoleMenuRefPOJO;

/**
 * [SysRoleMenuRef] 倉庫
 *
 * @author AndrewLee
 */
@Component
public class SysRoleMenuRefWareHouse {

    @Autowired
    private SysRoleMenuRefRepository repository;

    public boolean save(SysRoleMenuRefPOJO pojo) {
        SysRoleMenuRef entity = new SysRoleMenuRef();
        SysRoleMenuRefId id = new SysRoleMenuRefId();
        
        BeanUtils.copyProperties(pojo, entity);
        id.setMenuId(pojo.getMenuId());
        id.setRoleId(pojo.getRoleId());
        entity.setId(id);

        repository.save(entity);

        return true;
    }

    public boolean batchSave(List<SysRoleMenuRefPOJO> pojoLs) {
        for (SysRoleMenuRefPOJO pojo : pojoLs) {
            save(pojo);
        }

        return true;
    }
}
