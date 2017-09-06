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

import com.fet.crm.osp.platform.core.db.model.SysSkillMemberRef;
import com.fet.crm.osp.platform.core.db.model.SysSkillMemberRefId;
import com.fet.crm.osp.platform.core.db.repository.SysSkillMemberRefRepository;
import com.fet.crm.osp.platform.core.pojo.SysSkillMemberRefPOJO;

/**
 * [SysSkillMemberRef] 倉庫
 *
 * @author AndrewLee
 */
@Component
public class SkillManageWareHouse {

    @Autowired
    private SysSkillMemberRefRepository repository;

    public boolean save(SysSkillMemberRefPOJO pojo) {
        SysSkillMemberRef entity = new SysSkillMemberRef();
        SysSkillMemberRefId id = new SysSkillMemberRefId();

        BeanUtils.copyProperties(pojo, entity);
        id.setEmpno(pojo.getEmpNo());
        id.setSkillId(pojo.getSkillId());

        entity.setId(id);

        repository.save(entity);

        return true;
    }

    public boolean batchSave(List<SysSkillMemberRefPOJO> pojoLs) {
        for (SysSkillMemberRefPOJO pojo : pojoLs) {
            save(pojo);
        }

        return true;
    }
}
