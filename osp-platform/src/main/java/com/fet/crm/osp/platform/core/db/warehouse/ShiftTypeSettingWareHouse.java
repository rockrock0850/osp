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

import com.fet.crm.osp.platform.core.common.util.BeanCopyUtil;
import com.fet.crm.osp.platform.core.db.model.SysShifttypeSetting;

import com.fet.crm.osp.platform.core.db.repository.SysShifttypeSettingRepository;
import com.fet.crm.osp.platform.core.pojo.ShiftTypePOJO;

/**
 * [SysShifttypeSetting] 倉庫
 *
 * @author AndrewLee
 */
@Component
public class ShiftTypeSettingWareHouse {

    @Autowired
    private SysShifttypeSettingRepository shiftRepository;

    /**
     * 透過 shiftTypeName 搜索班別
     * 
     * @param shiftTypeName
     * @return List<SysShifttypeSettingVO>
     */
    public List<ShiftTypePOJO> findByShiftTypeName(String shiftTypeName) {
        List<SysShifttypeSetting> dataLs = shiftRepository.findByShiftTypeNameContaining(shiftTypeName);

        List<ShiftTypePOJO> rtnLs = parseSysShifttypeSettingDataToVO(dataLs);

        return rtnLs;
    }

    /**
     * 執行新增班表
     * 
     * @param pojo
     * @return boolean
     */
    public boolean save(ShiftTypePOJO pojo) {
        SysShifttypeSetting entity = new SysShifttypeSetting();

        BeanUtils.copyProperties(pojo, entity);

        shiftRepository.save(entity);

        return true;
    }

    /**
     * 修改班表內容
     * 
     * @param pojo
     * @return boolean
     */
    public boolean update(ShiftTypePOJO pojo) {
        String shiftTypeId = pojo.getShiftTypeId();
        SysShifttypeSetting entity = shiftRepository.findOne(shiftTypeId);

        String[] ignoreProperties = BeanCopyUtil.null2Ignore(pojo);
        BeanUtils.copyProperties(pojo, entity, ignoreProperties);

        shiftRepository.save(entity);

        return true;
    }
    
    //=========================Parse Entity To PoJo======================================
    /**
     * 將SysShifttypeSetting 中的資料轉移到VO中
     * 
     * @return List<SysShifttypeSettingVO>
     */
    private List<ShiftTypePOJO> parseSysShifttypeSettingDataToVO(List<SysShifttypeSetting> dataLs) {
        List<ShiftTypePOJO> rtnLs = new ArrayList<>();

        for (SysShifttypeSetting eneity : dataLs) {
            ShiftTypePOJO vo = new ShiftTypePOJO();

            BeanUtils.copyProperties(eneity, vo);

            rtnLs.add(vo);
        }

        return rtnLs;
    }

}
