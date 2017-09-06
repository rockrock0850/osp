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

package com.fet.crm.osp.platform.core.facade.dispatchinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.platform.core.service.dispatchinfo.IShiftManageService;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.ShiftMaintainVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.ShiftTypeSettingVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.ShiftTypeSkillMapVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.condition.ShiftTypeInfoCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.component.FileVO;

/**
 * [班別 班表] 總體服務窗口
 * 
 * @author LawrenceLai,AndrewLee
 */
@Service
public class ShiftManageFacade {

    @Autowired
    private IShiftManageService shiftTypeService;
    
    /**
     * 查詢 班別資訊<br>
     * 
     * @return List<Map<String, Object>>
     */
    public List<ShiftTypeSettingVO> getShiftTypeInfo() {
    	return shiftTypeService.queryShiftTypeInfo();
    }
    
    /**
     * 透過班別名稱.查詢班別資訊<br>
     * 取得條件：班別名稱
     * 
     * @param shiftTypeName
     * @return List<Map<String, Object>>
     */
    @Transactional(readOnly = true)
    public List<ShiftTypeSettingVO> getShiftTypeInfoByshiftTypeName(String shiftTypeName) {
        ShiftTypeInfoCVO cvo = new ShiftTypeInfoCVO();

        cvo.setShiftTypeName(shiftTypeName);

        return shiftTypeService.queryShiftTypeInfo(cvo);
    }

    /**
     * 新增 班別<br>
     * 
     * @param shifttypeSettingVO
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean createShiftType(ShiftTypeSettingVO shifttypeSettingVO) {
        return shiftTypeService.createShiftType(shifttypeSettingVO);
    }

    /**
     * 修改 班別<br>
     * 
     * @param shifttypeSettingVO
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean modifyShiftType(ShiftTypeSettingVO shifttypeSettingVO) {
        return shiftTypeService.modifyShiftType(shifttypeSettingVO);
    }

    /**
     * 修改 對應技能<br>
     * 
     * @param List<ShifttypeSettingVO>
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean modifySkillMapping(List<ShiftTypeSkillMapVO> sysShifttypeSkillMapVOLs) {
        return shiftTypeService.modifySkillMapping(sysShifttypeSkillMapVOLs);
    }

    /**
     * 查詢 當前班別所對應技能資訊<br>
     * 
     * @param shiftTypeId
     * @return List<ShiftTypeSkillMapVO>
     */
    @Transactional(readOnly = true)
    public List<ShiftTypeSkillMapVO> getSkillMappingInfoByShiftTypeId(String shiftTypeId) {
        return shiftTypeService.querySkillMappingInfoByShiftTypeId(shiftTypeId);
    }

    /**
     * 查詢 "非當前班別"的技能資訊(就是他沒有的啦)
     * 
     * @param shiftTypeId
     * @return List<ShiftTypeSkillMapVO>
     */
    @Transactional(readOnly = true)
    public List<ShiftTypeSkillMapVO> getSkillMappingInfoNotInShiftTypeId(String shiftTypeId) {
        return shiftTypeService.querySkillMappingInfoNotInShiftTypeId(shiftTypeId);
    }

    /**
     * 將前端上傳的班表 儲存至DB當中
     * 
     * @param ShiftMaintainVO
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean createShiftAttachment(ShiftMaintainVO shiftMaintainVO) {
        //建立班表附件資料至DB
        shiftTypeService.createShiftAttachment(shiftMaintainVO);
        //建立班表內容資料至DB
        shiftTypeService.createShiftContent(shiftMaintainVO);

        return true;
    }

    /**
     * 以年,月份來查詢班表.得到下載檔案
     * 
     * @return FileVO
     */
    @Transactional(readOnly = true)
    public FileVO queryShiftAttachment(String year, String month) {
        return shiftTypeService.queryShiftAttachment(year, month);
    }

}