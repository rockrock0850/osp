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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.platform.SpringTest;
import com.fet.crm.osp.platform.core.common.util.DateUtil;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleManualMappingVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleMenuRefVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.condition.RoleInfoCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.condition.RoleManualMappingCVO;

/**
 *
 * @author AndrewLee
 */
public class RolePermissionsFacadeTest extends SpringTest {

    @Autowired
    RolePermissionsFacade facade;

    @Test
    public void testQueryRoleInfoTest() {
        RoleInfoCVO cvo = new RoleInfoCVO();
        cvo.setRoleId("002");
        cvo.setEmpName("林*羽");

        List<RoleInfoVO> dataLs = facade.queryRoleInfo(cvo);

        System.out.println("==========================");
        System.out.println(JsonUtil.toJson(dataLs));

    }

    @Test
    public void testCreateRoleTest() throws UnknownHostException {
        RoleInfoVO vo = new RoleInfoVO();

        vo.setRoleType("0001");
        vo.setRoleId("95278");
        vo.setRoleName("RoleName");
        vo.setCreateUser(InetAddress.getLocalHost().getHostName());
        vo.setUpdateUser(InetAddress.getLocalHost().getHostName());
        vo.setActive("Y");

        facade.createRole(vo);
    }

    @Test
    public void testModifyRole() throws UnknownHostException {
        RoleInfoVO vo = new RoleInfoVO();

        vo.setRoleType("12345");
        vo.setRoleId("9527");
        vo.setRoleName("RoleName");
        vo.setUpdateUser(InetAddress.getLocalHost().getHostName());
        vo.setUpdateDateTxt(DateUtil.fromDate(new Date(),DateUtil.DATE_TIME_PATTERN));

        facade.modifyRole(vo);
    }

    @Test
    public void testQueryManualMappingInfoByRoleIdTest() {
        String roleId = "10001";

        List<RoleManualMappingVO> dataLs = facade.queryManualMappingInfoByRoleId(roleId);

        System.out.println("===========================");
        System.out.println(JsonUtil.toJson(dataLs));

    }

    @Test
    public void testQueryManualMappingInfoTest() {
        String roleId = "001";
        String empName = "鄭*妍";
        String deptchiName = "";

        RoleManualMappingCVO cvo = new RoleManualMappingCVO();
        cvo.setDeptchiName(deptchiName);
        cvo.setEmpName(empName);
        cvo.setRoleId(roleId);

        List<RoleManualMappingVO> dataLs = facade.queryManualMappingInfo(cvo);

        System.out.println("===========================");
        System.out.println(dataLs);
    }

    @Test
    public void testCreateManualMapping() throws UnknownHostException {
        List<RoleManualMappingVO> voLs = new ArrayList<>();
        RoleManualMappingVO vo = new RoleManualMappingVO();
        String roleId = "9527";
        String empno = "9527";
        String roleName = "TestRole";
        String empname = "TestName";
        String deptcode = "100001";
        String deptchiname = "測試";
        String createUser = InetAddress.getLocalHost().getHostName();
        String createDate = DateUtil.fromDate(new Date(), DateUtil.DATE_TIME_PATTERN);
        String updateUser = createUser;
        String updateDate = createDate;
        
        vo.setRoleId(roleId);
        vo.setEmpNo(empno);
        vo.setRoleName(roleName);
        vo.setEmpName(empname);
        vo.setDeptCode(deptcode);
        vo.setDeptCode(deptchiname);
        vo.setCreateDateTxt(createDate);
        vo.setCreateUser(createUser);
        vo.setUpdateDateTxt(updateDate);
        vo.setUpdateUser(updateUser);
        
        
        voLs.add(vo);

        facade.createManualMapping(voLs);
    }

    @Test
    public void testRemoveManualMapping() {
        RoleManualMappingVO vo = new RoleManualMappingVO();
        String empNo = "9527";
        String roleId = "9527";

        vo.setEmpNo(empNo);
        vo.setRoleId(roleId);

        facade.removeManualMapping(vo);
    }

    @Test
    public void testQueryMenuRefInfoByRoleId() {
        String roleId = "0001";
        List<RoleMenuRefVO> dataLs = facade.queryMenuRefInfoByRoleId(roleId);
        
        System.out.println("========================================");
        System.out.println(JsonUtil.toJson(dataLs));
    }

    @Test
    public void testQueryMenuRefNotInRoleId() {
        String roleId = "0001";
        List<RoleMenuRefVO> dataLs = facade.queryRoleIdMenuRefNotInRoleId(roleId);
        
        System.out.println("=====================================");
        System.out.println(JsonUtil.toJson(dataLs));

    }

    @Test
    public void testCreateMenuRef() throws UnknownHostException {
        List<RoleMenuRefVO> voLs = new ArrayList<>();
        
        RoleMenuRefVO vo = new RoleMenuRefVO();
        String roleId = "952787";
        String menuId = "OSPL1003";
        String createUser = InetAddress.getLocalHost().getHostName();
        String createDate = DateUtil.fromDate(new Date(), DateUtil.DATE_TIME_PATTERN);
        String updateUser = createUser;
        String updateDate = createDate;
        
        vo.setRoleId(roleId);
        vo.setMenuId(menuId);
        vo.setCreateDateTxt(createDate);
        vo.setUpdateDateTxt(updateDate);
        vo.setUpdateUser(updateUser);
        vo.setCreateUser(createUser);
        
        voLs.add(vo);
        
        vo = new RoleMenuRefVO();
        roleId = "95275";
        menuId = "OSPL1003";
        createUser = InetAddress.getLocalHost().getHostName();
        createDate = DateUtil.fromDate(new Date(), DateUtil.DATE_TIME_PATTERN);
        updateUser = createUser;
        updateDate = createDate;
        
        vo.setRoleId(roleId);
        vo.setMenuId(menuId);
        vo.setCreateDateTxt(createDate);
        vo.setUpdateDateTxt(updateDate);
        vo.setUpdateUser(updateUser);
        vo.setCreateUser(createUser);
        
        voLs.add(vo);
        
        facade.createMenuRef(voLs);
    }
}
