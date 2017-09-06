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

package com.fet.crm.osp.platform.webapp.action.setting;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.facade.dispatchinfo.SkillManageFacade;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.SkillInfoVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.SkillMemberRefVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.SkillOrderTypeMapVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.condition.SysSkillMemberRefCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.MemberInfoVO;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;

/**
 * [人員技能貼標維護作業] 導頁控制器
 * 
 * @author LawrenceLai, Adam Yeh
 */
@Controller
@RequestMapping("/setting/skill")
public class SkillMaintainAction {

    @Autowired
    private SkillManageFacade skillManageFacade;
	
	/**
	 * 「人員技能貼標維護」服務頁面
	 * 
	 * @return String
	 */
	@RequestMapping(value="/service-skill", method = RequestMethod.GET)
	public String serviceSkill() {
		return ForwardUtil.OSPLV2010.getView();
	}
	
	/**
	 * 「查詢對應人員關係」
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-member-mapping-info", method = RequestMethod.POST)
	public String getMemberMappingInfo() {
		String requestData = HttpRequestHandler.getJsonData();

        SysSkillMemberRefCVO vo = JsonUtil.fromJson(requestData, SysSkillMemberRefCVO.class);
		List<SkillMemberRefVO> voList = skillManageFacade.queryMemberMappingInfo(vo);
		
		String responseData = JsonUtil.toJson(voList);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 查詢對應案件類別
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-order-type-mapping-info", method = RequestMethod.POST)
	public String getOrderTypeMappingInfo() {
		List<SkillOrderTypeMapVO> voList = skillManageFacade.getSkillOrderTypeMap();
		
		String responseData = JsonUtil.toJson(voList);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 取得技能列表
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-skill-info", method = {RequestMethod.POST})
	public String getSkillInfo() {
		List<SkillInfoVO> voList = skillManageFacade.queryAllSkillInfo();
		
		String responseData = JsonUtil.toJson(voList);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 查詢貼標人員名單
	 * 
	 * @return String
	 */
	@RequestMapping(value="/skill-member-window", method = RequestMethod.POST)
	public String skillMemberWindow() {
		Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();
		
		String skillId = MapUtils.getString(requestData, "skillId");
		List<MemberInfoVO> voList = skillManageFacade.getMemberWithoutSpecificSkill(skillId);
		
		String responseData = JsonUtil.toJson(voList);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 技能貼標
	 * 
	 * @return String
	 */
	@RequestMapping(value="/stick-skill", method = RequestMethod.POST)
	public String stickSkill() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();

		List<SkillMemberRefVO> voList = JsonUtil.fromJsonToList(requestData, SkillMemberRefVO.class);
		for (SkillMemberRefVO vo : voList) {
			vo.setCreateUser(userId);
			vo.setUpdateUser(userId);
		}
		
		boolean dataMap = skillManageFacade.excuteStickSkill(voList);
		String responseData = JsonUtil.toJson(dataMap);
		
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
}
