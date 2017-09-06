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


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.db.model.SysShiftAttachment;
import com.fet.crm.osp.platform.core.db.repository.SysShiftAttachmentRepository;
import com.fet.crm.osp.platform.core.pojo.SysShiftAttachmentPOJO;

/**
 * SysShiftAttachment 倉庫
 *
 * @author AndrewLee
 */
@Component
public class SysShiftAttachmentWareHouse {

    @Autowired
    private SysShiftAttachmentRepository repository;

    public boolean save(SysShiftAttachmentPOJO pojo) {
        SysShiftAttachment entity = new SysShiftAttachment();

        BeanUtils.copyProperties(pojo, entity);

        repository.save(entity);

        return true;
    }

    public boolean deleteByDtYearAndDtMonth(String dtYear, String dtMonth) {
        repository.deleteByDtYearAndDtMonth(dtYear, dtMonth);

        return true;
    }

    public SysShiftAttachmentPOJO findByDtYearAndDtMonth(String dtYear, String dtMonth) {
        SysShiftAttachmentPOJO pojo = new SysShiftAttachmentPOJO();

        SysShiftAttachment entity = repository.findByDtYearAndDtMonth(dtYear, dtMonth);

        if(entity != null) {
            BeanUtils.copyProperties(entity, pojo);            
        }

        return pojo;
    }

}
