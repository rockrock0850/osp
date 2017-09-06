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

package com.fet.crm.osp.platform.core.service.systeminfo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.condition.Condition;
import com.fet.crm.osp.platform.core.db.condition.api.Restrictions;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.db.warehouse.SysRoleManualMappingWareHouse;
import com.fet.crm.osp.platform.core.db.warehouse.SysRoleMenuRefWareHouse;
import com.fet.crm.osp.platform.core.db.warehouse.SysRoleWarehouse;
import com.fet.crm.osp.platform.core.pojo.SysRoleManualMappingPOJO;
import com.fet.crm.osp.platform.core.pojo.SysRoleMenuRefPOJO;
import com.fet.crm.osp.platform.core.pojo.SysRolePOJO;
import com.fet.crm.osp.platform.core.service.systeminfo.IRolePermissionsManageService;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleInfoMaintainVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleManualMappingVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleMenuRefVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.condition.RoleInfoCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.condition.RoleManualMappingCVO;

/**
 * 角色權限 服務界面
 *
 * @author AndrewLee
 */
@Service
public class RolePermissionsManageServiceImpl implements IRolePermissionsManageService {

    @Autowired
    private SysRoleWarehouse sysRoleWarHouse;

    @Autowired
    private SysRoleManualMappingWareHouse sysRoleManualMappingWareHouse;

    @Autowired
    private SysRoleMenuRefWareHouse sysRoleMenuRefWareHouse;

    @Autowired
    private JdbcDAO jdbcDAO;

    @Override
    public List<RoleInfoVO> queryRoleInfo(RoleInfoCVO roleInfoCVO) {
        String sqlText = ResourceFileUtil.SQL_SYSTEM.getResource("role", "QUERY_ROLE_INFO_BY_CONDITION");
        Condition condition = new Condition();

        String roleName = roleInfoCVO.getRoleName();
        String empNo = roleInfoCVO.getEmpNo();

        if (StringUtils.isNotBlank(roleName)) {
            condition.and(Restrictions.like("T1.ROLE_NAME", roleName));
        }
        
        if(StringUtils.isNotBlank(empNo)) {
            String subQueryText = "T1.ROLE_ID IN (SELECT ROLE_ID FROM SYS_ROLE_MANUAL_MAPPING WHERE EMPNO = :EMPNO)";
            
            Map<String,Object> params = new HashMap<>();
            params.put("EMPNO", empNo);
            
            condition.and(Restrictions.sqlRestrictions(subQueryText,params));
        }

        sqlText = condition.getCompleteSQL(sqlText);

        Map<String, Object> params = condition.getParams();

        List<RoleInfoVO> rtnData = jdbcDAO.queryForBean(sqlText, params, RoleInfoVO.class);

        return rtnData;
    }

    @Override
    public boolean createRole(RoleInfoVO roleInfoVO) {
        if (roleInfoVO != null) {
            String roleId = IdentifierIdUtil.getOspRoleId();
            String roleName = roleInfoVO.getRoleName();
            String roleType = roleInfoVO.getRoleType();
            String active = "Y";
            String description = roleInfoVO.getDescription();
            String createUser = roleInfoVO.getCreateUser();
            String updateUser = createUser;

            SysRolePOJO pojo = new SysRolePOJO();

            pojo.setRoleId(roleId);
            pojo.setRoleName(roleName);
            pojo.setRoleType(roleType);
            pojo.setActive(active);
            pojo.setDescription(description);
            pojo.setCreateUser(createUser);
            pojo.setUpdateUser(updateUser);
            pojo.setCreateDate(new Date());
            pojo.setUpdateDate(new Date());

            sysRoleWarHouse.save(pojo);
        }

        return true;
    }

    @Override
    public boolean modifyRole(RoleInfoMaintainVO maintainVO) {
        String roleId = maintainVO.getRoleId();
        String roleName = maintainVO.getRoleName();
        String roleType = maintainVO.getRoleType();
        String updateUser = maintainVO.getUpdateUser();

        SysRolePOJO pojo = new SysRolePOJO();
        pojo.setRoleId(roleId);
        pojo.setRoleName(roleName);
        pojo.setRoleType(roleType);
        pojo.setUpdateUser(updateUser);
        pojo.setUpdateDate(new Date());

        sysRoleWarHouse.update(pojo);

        return true;
    }

    @Override
    public List<RoleManualMappingVO> queryManualMappingInfo(RoleManualMappingCVO manualMappingcVO) {
        String sqlText = ResourceFileUtil.SQL_SYSTEM.getResource("role", "QUERY_ROLE_MANUAL_MAPPING_BY_CONDITION");

        Condition condition = new Condition();

        String roleId = manualMappingcVO.getRoleId();
        String empName = manualMappingcVO.getEmpName();
        String deptName = manualMappingcVO.getDeptchiName();

        if (StringUtils.isNotBlank(roleId)) {
            condition.and(Restrictions.eq("T1.ROLE_ID", roleId));
        }

        if (StringUtils.isNotBlank(empName)) {
            condition.and(Restrictions.like("T1.EMPNAME", empName));
        }

        if (StringUtils.isNotBlank(deptName)) {
            condition.and(Restrictions.like("T2.DEPTCHINAME", deptName));
        }

        sqlText = condition.getCompleteSQL(sqlText);

        Map<String, Object> params = condition.getParams();

        List<RoleManualMappingVO> rtnLs = jdbcDAO.queryForBean(sqlText, params, RoleManualMappingVO.class);

        return rtnLs;
    }

    @Override
    public boolean createManualMapping(List<RoleManualMappingVO> manualMappingList) {
        List<SysRoleManualMappingPOJO> pojoLs = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(manualMappingList)) {
            for (RoleManualMappingVO vo : manualMappingList) {
                String roleId = vo.getRoleId();
                String empno = vo.getEmpNo();
                String roleName = vo.getRoleName();
                String empname = vo.getEmpName();
                String deptcode = vo.getDeptCode();
                String deptchiname = vo.getDeptName();
                String createUser = vo.getCreateUser();
                String updateUser = createUser;

                SysRoleManualMappingPOJO pojo = new SysRoleManualMappingPOJO();

                pojo.setRoleId(roleId);
                pojo.setEmpno(empno);
                pojo.setRoleName(roleName);
                pojo.setEmpname(empname);
                pojo.setDeptcode(deptcode);
                pojo.setDeptchiname(deptchiname);
                pojo.setCreateUser(createUser);
                pojo.setUpdateUser(updateUser);
                pojo.setCreateDate(new Date());
                pojo.setUpdateDate(new Date());

                pojoLs.add(pojo);
            }
            sysRoleManualMappingWareHouse.batchSave(pojoLs);
        }

        return true;
    }

    @Override
    public boolean removeManualMapping(RoleManualMappingVO manualMappingVO) {
        if (manualMappingVO != null) {
            String empNo = manualMappingVO.getEmpNo();
            String roleId = manualMappingVO.getRoleId();

            SysRoleManualMappingPOJO pojo = new SysRoleManualMappingPOJO();

            pojo.setEmpno(empNo);
            pojo.setRoleId(roleId);

            sysRoleManualMappingWareHouse.remove(pojo);
        }

        return true;
    }

    @Override
    public List<RoleMenuRefVO> queryMenuRefInfoByRoleId(String roleId) {
        String sqlText = ResourceFileUtil.SQL_SYSTEM.getResource("role", "QUERY_MENU_REF_BY_ROLE_ID");
        Map<String, Object> params = new HashMap<>();

        params.put("ROLE_ID", roleId);

        List<RoleMenuRefVO> rtnData = jdbcDAO.queryForBean(sqlText, params, RoleMenuRefVO.class);

        return rtnData;
    }

    @Override
    public List<RoleMenuRefVO> queryMenuRefNotInRoleId(String roleId) {
        String sqlText = ResourceFileUtil.SQL_SYSTEM.getResource("role", "QUERY_MENU_REF_NOT_IN_ROLE_ID");
        Map<String, Object> params = new HashMap<>();

        params.put("ROLE_ID", roleId);

        List<RoleMenuRefVO> rtnData = jdbcDAO.queryForBean(sqlText, params, RoleMenuRefVO.class);

        return rtnData;
    }

    @Override
    public boolean createMenuRef(List<RoleMenuRefVO> MenuRefList) {
        List<SysRoleMenuRefPOJO> pojoLs = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(MenuRefList)) {
            // 先清楚該角色所有跟menu相關的資料.後面在新增新的
            String sqlText = ResourceFileUtil.SQL_SYSTEM.getResource("role", "DELETE_MENU_REF_BY_ROLE_ID");
            Map<String, Object> params = new HashMap<>();

            // RoleId必然是同一筆.所以只取第一個
            String firstRoleId = MenuRefList.get(0).getRoleId();
            params.put("ROLE_ID", firstRoleId);

            jdbcDAO.update(sqlText, params);

            for (RoleMenuRefVO vo : MenuRefList) {
                String roleId = vo.getRoleId();
                String menuId = vo.getMenuId();
                String createUser = vo.getCreateUser();
                String updateUser = createUser;

                SysRoleMenuRefPOJO pojo = new SysRoleMenuRefPOJO();

                pojo.setRoleId(roleId);
                pojo.setMenuId(menuId);
                pojo.setCreateUser(createUser);
                pojo.setUpdateUser(updateUser);
                pojo.setCreateDate(new Date());
                pojo.setUpdateDate(new Date());

                pojoLs.add(pojo);
            }
            sysRoleMenuRefWareHouse.batchSave(pojoLs);
        }

        return true;
    }

}
