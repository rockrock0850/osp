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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.facade.systeminfo.HRManageFacade;
import com.fet.crm.osp.platform.core.facade.systeminfo.RolePermissionsFacade;
import com.fet.crm.osp.platform.core.vo.systeminfo.MemberInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleManualMappingVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleMenuRefVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.condition.RoleInfoCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.condition.RoleManualMappingCVO;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;

/**
 * [角色維護作業] 導頁控制器
 * 
 * @author LawrenceLai, Adam Yeh
 */
@Controller
@RequestMapping("/setting/role")
public class RoleMaintainAction {
	
    @Autowired
    RolePermissionsFacade rolePermissionsFacade;
    @Autowired
	private HRManageFacade hrManageFacade;
	
	/**
	 * 「角色維護」服務頁面
	 * 
	 * @return String
	 */
	@RequestMapping(value="/service-role", method = RequestMethod.GET)
	public String serviceRole() {
		return ForwardUtil.OSPLV2012.getView();
	}
	
	/**
	 * 「查詢角色資訊」
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-role-info", method = RequestMethod.POST)
	public String getRoleInfo() {
		String requestData = HttpRequestHandler.getJsonData();
		
		RoleInfoCVO vo = JsonUtil.fromJson(requestData, RoleInfoCVO.class);
        List<RoleInfoVO> voList = rolePermissionsFacade.queryRoleInfo(vo);
		
		String responseData = JsonUtil.toJson(voList);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 創建角色
	 * 
	 * @return String
	 */
	@RequestMapping(value="/create-role", method = RequestMethod.POST)
	public String createRole() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();

		RoleInfoVO vo = JsonUtil.fromJson(requestData, RoleInfoVO.class);
		vo.setCreateUser(userId);
		vo.setUpdateUser(userId);
		boolean dataMap = rolePermissionsFacade.createRole(vo);
		
		String responseData = JsonUtil.toJson(dataMap);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 修改角色
	 * 
	 * @return String
	 */
	@RequestMapping(value="/modify-role", method = {RequestMethod.POST})
	public String modifyRole() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();

		RoleInfoVO vo = JsonUtil.fromJson(requestData, RoleInfoVO.class);
		vo.setCreateUser(userId);
		vo.setUpdateUser(userId);
		
		boolean dataMap = rolePermissionsFacade.modifyRole(vo);
		
		String responseData = JsonUtil.toJson(dataMap);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 查詢對應員工帳號關係資訊by id
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-manual-mapping-info-by-id", method = RequestMethod.POST)
	public String getManualMappingInfoById() {
		Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();
		
		String roleId = MapUtils.getString(requestData, "roleId");
		List<RoleManualMappingVO> voList = rolePermissionsFacade.queryManualMappingInfoByRoleId(roleId);
		
		String responseData = JsonUtil.toJson(voList);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 查詢對應員工帳號關係資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-manual-mapping-info", method = RequestMethod.POST)
	public String getManualMappingInfo() {
		String requestData = HttpRequestHandler.getJsonData();

		RoleManualMappingCVO vo = JsonUtil.fromJson(requestData, RoleManualMappingCVO.class);
        List<RoleManualMappingVO> voList = rolePermissionsFacade.queryManualMappingInfo(vo);
		
		String responseData = JsonUtil.toJson(voList);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 查詢新增角色員工名單
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-manual-member", method = RequestMethod.POST)
	public String getManualMember() {
		Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

		String roleId = MapUtils.getString(requestData, "roleId");

	    List<MemberInfoVO> voList = hrManageFacade.getMemberInfoBy7331TeamWithoutBeSettedRoleMember(roleId);

		String responseData = JsonUtil.toJson(voList);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 創建對應員工帳號關係
	 * 
	 * @return String
	 */
	@RequestMapping(value="/create-manual-mapping", method = RequestMethod.POST)
	public String createManualMapping() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();

		List<RoleManualMappingVO> voList = JsonUtil.fromJsonToList(requestData, RoleManualMappingVO.class);
	    for (RoleManualMappingVO vo : voList) {
			vo.setCreateUser(userId);
			vo.setUpdateUser(userId);
		}
		
		boolean dataMap = rolePermissionsFacade.createManualMapping(voList);
		
		String responseData = JsonUtil.toJson(dataMap);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 刪除對應員工帳號關係
	 * 
	 * @return String
	 */
	@RequestMapping(value="/remove-manual-mapping", method = RequestMethod.POST)
	public String removeManualMapping() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();

		RoleManualMappingVO vo = JsonUtil.fromJson(requestData, RoleManualMappingVO.class);
		vo.setCreateUser(userId);
		vo.setUpdateUser(userId);
		
		boolean dataMap = rolePermissionsFacade.removeManualMapping(vo);
		
		String responseData = JsonUtil.toJson(dataMap);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 查詢系統選單關係
	 * 
	 * @return String
	 */
	@RequestMapping(value="/service-menu-ref", method = RequestMethod.POST)
	public String serviceMenuRef() {
		Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

		String roleId = MapUtils.getString(requestData, "roleId");
		Map<String, Object> dataMap = new HashMap<>();
        List<RoleMenuRefVO> no = rolePermissionsFacade.queryRoleIdMenuRefNotInRoleId(roleId);
        List<RoleMenuRefVO> has = rolePermissionsFacade.queryMenuRefInfoByRoleId(roleId);
        dataMap.put("has", has);
        dataMap.put("no", no);
		
		String responseData = JsonUtil.toJson(dataMap);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 創建系統選單關係
	 * 
	 * @return String
	 */
	@RequestMapping(value="/create-menu-ref", method = RequestMethod.POST)
	public String createMenuRef() {
		Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();

		String data = MapUtils.getString(requestData, "collectionRight");
		List<RoleMenuRefVO> voList = JsonUtil.fromJsonToList(data, RoleMenuRefVO.class);
		for (RoleMenuRefVO vo : voList) {
			vo.setCreateUser(userId);
			vo.setUpdateUser(userId);
		}
		
		boolean dataMap = rolePermissionsFacade.createMenuRef(voList);
		
		String responseData = JsonUtil.toJson(dataMap);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
}
