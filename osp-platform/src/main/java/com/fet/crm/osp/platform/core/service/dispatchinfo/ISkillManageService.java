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

import com.fet.crm.osp.platform.core.vo.dispatchinfo.SkillInfoVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.SkillMemberRefVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.SkillOrderTypeMapVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.condition.SysSkillMemberRefCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.MemberInfoVO;

/**
 * [人員技能貼標] 服務介面
 * 
 * @author LawrenceLai
 */
public interface ISkillManageService {
	
	/**
	 * 查詢 查詢對應人員關係 資訊<br>
	 * 取得條件：
	 * 
	 * @param SysSkillMemberRefCVO
	 * @return List<SysSkillMemberRefVO>
	 */
	List<SkillMemberRefVO> queryMemberMappingInfo(SysSkillMemberRefCVO skillMemberRefCVO);
	
	/**
	 * 查詢 所有技能資訊<br>
	 * 
	 * @return List<SkillVO>
	 */
	List<SkillInfoVO> queryAllSkillInfo();
	
	/**
	 * 查詢 技能對應案件類別資訊<br>
	 * 
	 * @return List<SkillVO>
	 */
	List<SkillOrderTypeMapVO> querySkillOrderTypeMap();
	
	/**
	 * 人員技能貼標
	 * 
	 * @param empNo
	 * @param skillList
	 * @return boolean
	 */
	boolean excuteStickSkill(List<SkillMemberRefVO> skillMemberRefVoLs);
	
	/**
	 * 查詢 無此技能對象之貼標人員
	 * 
	 * @param skillId
	 * @return List<MemberInfoVO>
	 */
	List<MemberInfoVO> queryMemberWithoutSpecificSkill(String skillId);

}
