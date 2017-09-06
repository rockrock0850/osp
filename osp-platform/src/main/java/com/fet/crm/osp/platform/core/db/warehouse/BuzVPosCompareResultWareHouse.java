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

import com.fet.crm.osp.platform.core.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.db.model.BuzVPosCompareResult;
import com.fet.crm.osp.platform.core.db.model.BuzVPosCompareResultId;
import com.fet.crm.osp.platform.core.db.repository.BuzVPosCompareResultRepository;
import com.fet.crm.osp.platform.core.pojo.BuzVPosCompareResultPOJO;

/**
 * BuzVPosCompareResultWareHouse 倉庫
 *
 * @author AndrewLee
 */
@Component
public class BuzVPosCompareResultWareHouse {

    @Autowired
    private BuzVPosCompareResultRepository repository;

    public boolean save(BuzVPosCompareResultPOJO pojo) {
        BuzVPosCompareResult entity = new BuzVPosCompareResult();

        String compareId = IdentifierIdUtil.getUuid();
        String VPosAuthCode = pojo.getVPosAuthCode();

        BeanUtils.copyProperties(pojo, entity);
        
        BuzVPosCompareResultId id = new BuzVPosCompareResultId(compareId, VPosAuthCode);
        entity.setId(id);

        repository.save(entity);

        return true;
    }

    public boolean batchSave(List<BuzVPosCompareResultPOJO> pojoList) {
        for (BuzVPosCompareResultPOJO pojo : pojoList) {
            save(pojo);
        }

        return true;
    }

    public List<BuzVPosCompareResultPOJO> findAll() {
        List<BuzVPosCompareResultPOJO> pojoList = new ArrayList<>();

        List<BuzVPosCompareResult> dataList = repository.findAll();

        for (BuzVPosCompareResult entity : dataList) {
            BuzVPosCompareResultPOJO pojo = new BuzVPosCompareResultPOJO();
            BeanUtils.copyProperties(entity, pojo);

            pojoList.add(pojo);
        }

        return pojoList;
    }
    
    public void deleteAll() {
        repository.deleteAll();
    }

}
