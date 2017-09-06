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

import com.fet.crm.osp.platform.core.db.model.SysShifttypeSkillMap;
import com.fet.crm.osp.platform.core.db.model.SysShifttypeSkillMapId;
import com.fet.crm.osp.platform.core.db.repository.SysShifttypeSkillMapRepository;
import com.fet.crm.osp.platform.core.pojo.ShiftSkillMapPOJO;

/**
 * [SysShifttypeSkillMap] 倉庫
 *
 * @author AndrewLee
 */
@Component
public class ShiftTypeSkillsMapWareHouse {

    @Autowired
    private SysShifttypeSkillMapRepository skillRepository;

    /**
     * 根據ID新增多筆SKILL
     * 
     * @param pojoLs
     * @return boolean
     */
    public boolean save(ShiftSkillMapPOJO pojo) {
        SysShifttypeSkillMap entity = new SysShifttypeSkillMap();
        SysShifttypeSkillMapId entityId = new SysShifttypeSkillMapId();

        BeanUtils.copyProperties(pojo, entity);

        entityId.setShiftTypeId(pojo.getShiftTypeId());
        entityId.setSkillId(pojo.getSkillId());

        entity.setId(entityId);

        skillRepository.save(entity);

        return true;
    }

    public boolean batchSave(List<ShiftSkillMapPOJO> pojoLs) {
        for (ShiftSkillMapPOJO pojo : pojoLs) {
            save(pojo);
        }

        return true;
    }

}
