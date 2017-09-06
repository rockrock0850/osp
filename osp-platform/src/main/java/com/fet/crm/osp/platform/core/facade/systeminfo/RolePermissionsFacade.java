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

package com.fet.crm.osp.platform.core.facade.systeminfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.platform.core.service.systeminfo.IRolePermissionsManageService;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleInfoMaintainVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleManualMappingVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleMenuRefVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.condition.RoleInfoCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.condition.RoleManualMappingCVO;

/**
 * 「角色權限」總體服務窗口
 * 
 * @author LawrenceLai,AndrewLee
 */
@Service
public class RolePermissionsFacade {

    @Autowired
    private IRolePermissionsManageService rolePermissionsManageService;

    /**
     * 查詢 角色資訊<br>
     * 取得條件：1.角色名稱 2.員工編號
     * 
     * @param roleInfoCVO
     * @return
     */
    @Transactional(readOnly = true)
    public List<RoleInfoVO> queryRoleInfo(RoleInfoCVO roleInfoCVO) {
        return rolePermissionsManageService.queryRoleInfo(roleInfoCVO);
    }

    /**
     * 創建角色資訊 1.roleId 2.roleName 3.roleType 4.active 5.description
     * 
     * @param roleInfoVO
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean createRole(RoleInfoVO roleInfoVO) {
        return rolePermissionsManageService.createRole(roleInfoVO);
    }

    /**
     * 修改角色資訊
     * 
     * @param roleInfoVO
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean modifyRole(RoleInfoVO roleInfoVO) {
        String roleId = roleInfoVO.getRoleId();
        String roleName = roleInfoVO.getRoleName();
        String roleType = roleInfoVO.getRoleType();
        String updateUser = roleInfoVO.getUpdateUser();
        
        RoleInfoMaintainVO maintainVO = new RoleInfoMaintainVO();
        maintainVO.setRoleId(roleId);
        maintainVO.setRoleName(roleName);
        maintainVO.setRoleType(roleType);
        maintainVO.setUpdateUser(updateUser);
        
        return rolePermissionsManageService.modifyRole(maintainVO);
    }

    /**
     * 透過角色資訊.查詢 角色對應人員資訊<br>
     * 取得條件：1.單位名稱
     * 
     * @return List<RoleManualMappingVO>
     */
    @Transactional(readOnly = true)
    public List<RoleManualMappingVO> queryManualMappingInfoByRoleId(String roleId) {
        RoleManualMappingCVO cvo = new RoleManualMappingCVO();

        cvo.setRoleId(roleId);

        List<RoleManualMappingVO> rtnData = rolePermissionsManageService.queryManualMappingInfo(cvo);

        return rtnData;
    }

    /**
     * 透過條件查詢 找到該角色對應人員資訊<br>
     * 取得條件：1.人員姓名 2.單位名稱
     * 
     * @return List<RoleManualMappingVO>
     */
    @Transactional(readOnly = true)
    public List<RoleManualMappingVO> queryManualMappingInfo(RoleManualMappingCVO manualMappingcVO) {
        return rolePermissionsManageService.queryManualMappingInfo(manualMappingcVO);
    }

    /**
     * 創建角色對應人員資訊
     * 
     * @param roleInfoVO
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean createManualMapping(List<RoleManualMappingVO> manualMappingList) {
        return rolePermissionsManageService.createManualMapping(manualMappingList);
    }

    /**
     * 移除角色對應人員資訊
     * 
     * @param roleInfoVO
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean removeManualMapping(RoleManualMappingVO manualMappingVO) {
        return rolePermissionsManageService.removeManualMapping(manualMappingVO);
    }

    /**
     * 查詢 選單對應角色資訊<br>
     * 取得條件：1.角色Id
     * 
     * @param roleInfoCVO
     * @return List<RoleMenuRefVO>
     */
    @Transactional(readOnly = true)
    public List<RoleMenuRefVO> queryMenuRefInfoByRoleId(String roleId) {
        return rolePermissionsManageService.queryMenuRefInfoByRoleId(roleId);
    }

    /**
     * 查詢 "未與"此角色資訊關聯的選單
     * 
     * @param roleId
     * @return List<RoleMenuRefVO>
     */
    @Transactional(readOnly = true)
    public List<RoleMenuRefVO> queryRoleIdMenuRefNotInRoleId(String roleId) {
        return rolePermissionsManageService.queryMenuRefNotInRoleId(roleId);
    }

    /**
     * 創建角色對應選單資訊
     * 
     * @param roleInfoVO
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean createMenuRef(List<RoleMenuRefVO> MenuRefList) {
        return rolePermissionsManageService.createMenuRef(MenuRefList);
    }

}
