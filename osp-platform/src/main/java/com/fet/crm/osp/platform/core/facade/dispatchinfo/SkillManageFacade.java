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

import com.fet.crm.osp.platform.core.service.dispatchinfo.ISkillManageService;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.SkillInfoVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.SkillMemberRefVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.SkillOrderTypeMapVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.condition.SysSkillMemberRefCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.MemberInfoVO;

/**
 * [人員技能貼標] 總體服務窗口
 * 
 * @author LawrenceLai,AndrewLee
 */
@Service
public class SkillManageFacade {

    @Autowired
    private ISkillManageService skillManageService;

    /**
     * 查詢 查詢對應人員關係 資訊<br>
     * 取得條件：
     * 1.empNo
     * 2.empName
     * 3.skillId
     * 
     * @param SysSkillMemberRefCVO
     * @return List<SysSkillMemberRefVO>
     */
    @Transactional(readOnly = true)
    public List<SkillMemberRefVO> queryMemberMappingInfo(SysSkillMemberRefCVO skillMemberRefCVO) {
        return skillManageService.queryMemberMappingInfo(skillMemberRefCVO);
    }

    /**
     * 查詢 所有技能資訊<br>
     * 
     * @return List<SkillVO>
     */
    @Transactional(readOnly = true)
    public List<SkillInfoVO> queryAllSkillInfo() {
        return skillManageService.queryAllSkillInfo();
    }

    /**
     * 人員技能貼標
     * 
     * @param skillId 技能編號<br>
     * @param skillName 技能名稱<br>
     * @param empNoList 員工編號列表<br>
     * 
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean excuteStickSkill(List<SkillMemberRefVO> skillMemberRefVoLs) {
        return skillManageService.excuteStickSkill(skillMemberRefVoLs);
    }

    /**
     * 查詢所有技能對應案件類別
     * 
     * @return List<SkillOrderTypeMapVO>
     */
    @Transactional(readOnly = true)
    public List<SkillOrderTypeMapVO> getSkillOrderTypeMap() {
        return skillManageService.querySkillOrderTypeMap();
    }
    
    /**
	 * 查詢 無此技能對象之貼標人員
	 * 
	 * @param skillId
	 * @return List<MemberInfoVO>
	 */
	public List<MemberInfoVO> getMemberWithoutSpecificSkill(String skillId) {
		return skillManageService.queryMemberWithoutSpecificSkill(skillId);
	}

}
