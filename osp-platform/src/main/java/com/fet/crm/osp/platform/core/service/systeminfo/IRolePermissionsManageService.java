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

package com.fet.crm.osp.platform.core.service.systeminfo;

import java.util.List;

import com.fet.crm.osp.platform.core.vo.systeminfo.RoleInfoMaintainVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleManualMappingVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleMenuRefVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.condition.RoleInfoCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.condition.RoleManualMappingCVO;

/**
 * [角色權限管理] 服務介面
 * 
 * @author LawrenceLai
 */
public interface IRolePermissionsManageService {

	/**
	 *  查詢 角色資訊<br>
	 *  取得條件：1.角色名稱 2.員工編號
	 * 
	 * @param roleInfoCVO
	 * @return
	 */
	List<RoleInfoVO> queryRoleInfo(RoleInfoCVO roleInfoCVO);
	
	/**
	 * 創建角色資訊
	 * 
	 * @param roleInfoVO
	 * @return boolean
	 */
	boolean createRole(RoleInfoVO roleInfoVO);
	
	/**
	 * 修改角色資訊
	 * 
	 * @param RoleInfoMaintainVO
	 * @return boolean
	 */
	boolean modifyRole(RoleInfoMaintainVO maintainVO);
	
	/**
	 * 查詢 角色對應人員資訊<br>
	 * 取得條件：1.人員姓名 2.單位名稱
	 * 
	 * @return List<RoleManualMappingVO>
	 */
	List<RoleManualMappingVO> queryManualMappingInfo(RoleManualMappingCVO roleManualMappingCVO);
	
	/**
	 * 創建角色對應人員資訊
	 * 
	 * @param roleInfoVO
	 * @return boolean
	 */
	boolean createManualMapping(List<RoleManualMappingVO> manualMappingList);
	
	/**
	 * 移除角色對應人員資訊
	 * 
	 * @param roleInfoVO
	 * @return boolean
	 */
	boolean removeManualMapping(RoleManualMappingVO manualMappingVO);
	
	/**
	 *  查詢 選單對應角色資訊<br>
	 *  取得條件：1.角色Id
	 * 
	 * @param roleInfoCVO
	 * @return
	 */
	List<RoleMenuRefVO> queryMenuRefInfoByRoleId(String roleId);
	
	/**
	 * 查詢 "未與"此角色資訊關聯的選單
	 * 
	 * @param roleId
	 * @return List<RoleMenuRefVO>
	 */
	List<RoleMenuRefVO> queryMenuRefNotInRoleId(String roleId);
	
	/**
	 * 創建角色對應選單資訊
	 * 
	 * @param roleInfoVO
	 * @return boolean
	 */
	boolean createMenuRef(List<RoleMenuRefVO> MenuRefList);
	

	
}
