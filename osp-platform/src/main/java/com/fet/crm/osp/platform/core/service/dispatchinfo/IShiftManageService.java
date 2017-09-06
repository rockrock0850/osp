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

package com.fet.crm.osp.platform.core.service.dispatchinfo;

import java.util.List;

import com.fet.crm.osp.platform.core.vo.dispatchinfo.ShiftMaintainVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.ShiftTypeSettingVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.ShiftTypeSkillMapVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.condition.ShiftTypeInfoCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.component.FileVO;

/**
 * [班別] 服務介面
 * 
 * @author LawrenceLai
 */
public interface IShiftManageService {
	
	 /**
     * 查詢 班別資訊<br>
     * 
     * @param qrderInfoCVO
     * @return List<Map<String, Object>>
     */
    List<ShiftTypeSettingVO> queryShiftTypeInfo();

    /**
     * 查詢 班別資訊<br>
     * 取得條件：班別名稱
     * 
     * @param qrderInfoCVO
     * @return List<Map<String, Object>>
     */
    List<ShiftTypeSettingVO> queryShiftTypeInfo(ShiftTypeInfoCVO infoCVO);

    /**
     * 新增 班別<br>
     * 
     * @param shifttypeSettingVO
     * @return boolean
     */
    boolean createShiftType(ShiftTypeSettingVO shifttypeSettingVO);

    /**
     * 修改 班別<br>
     * 
     * @param shifttypeSettingVO
     * @return boolean
     */
    boolean modifyShiftType(ShiftTypeSettingVO shifttypeSettingVO);

    /**
     * 修改 對應技能<br>
     * 
     * @param shifttypeSettingVO
     * @return boolean
     */
    boolean modifySkillMapping(List<ShiftTypeSkillMapVO> sysShifttypeSkillMapVOLs);

    /**
     * 查詢 對應技能資訊<br>
     * 取得條件：
     * 
     * @param shiftTypeId
     * @return Map<String, List<ShiftTypeSkillMapVO>>
     */
    List<ShiftTypeSkillMapVO> querySkillMappingInfoByShiftTypeId(String shiftTypeId);
    
    /**
     * 查詢所有班別的skill,但會排除掉當前班別已經擁有的skill
     * 
     * @param shiftTypeId
     * @return List<ShiftTypeSkillMapVO>
     */
    List<ShiftTypeSkillMapVO> querySkillMappingInfoNotInShiftTypeId(String shiftTypeId);

    /**
     * 匯入 儲存班表檔案
     * 
     * @param ShiftMaintainVO
     * @return boolean
     */
	public boolean createShiftAttachment(ShiftMaintainVO shiftMaintainVO);

	/**
	 *  匯出 取得班表檔案
	 *  
	 * @return FileVO
	 */
	public FileVO queryShiftAttachment(String year, String month);
	
	 /**
     * 匯入 儲存班表內容
     * 
     * @param ShiftMaintainVO
     * @return boolean
     */
	public boolean createShiftContent(ShiftMaintainVO shiftMaintainVO);

	
}
