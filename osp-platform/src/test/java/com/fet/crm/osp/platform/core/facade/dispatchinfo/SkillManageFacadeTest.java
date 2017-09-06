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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.platform.SpringTest;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.facade.dispatchinfo.SkillManageFacade;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.SkillInfoVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.SkillMemberRefVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.SkillOrderTypeMapVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.condition.SysSkillMemberRefCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.MemberInfoVO;

/**
 *
 * @author AndrewLee
 */
public class SkillManageFacadeTest extends SpringTest {

    @Autowired
    private SkillManageFacade facade;

    @Test
    public void testQueryMemberMappingInfoTest() {
        SysSkillMemberRefCVO cvo = new SysSkillMemberRefCVO();
        
        String skillId = "";
        String empName = "";
        String empNo = "";

        cvo.setSkillId(skillId);
        cvo.setEmpName(empName);
        cvo.setEmpNo(empNo);

        List<SkillMemberRefVO> dataLs = facade.queryMemberMappingInfo(cvo);

        System.out.println("===========================");
        System.out.println(JsonUtil.toJson(dataLs));
    }

    @Test
    public void testQueryAllSkillInfo() {
        List<SkillInfoVO> dataLs = facade.queryAllSkillInfo();
        System.out.println("===========================");
        System.out.println(JsonUtil.toJson(dataLs));
    }

    @Test
    public void testExcuteStickSkill() throws UnknownHostException {
        List<SkillMemberRefVO> dataLs = new ArrayList<>();

        SkillMemberRefVO vo = new SkillMemberRefVO();

        vo.setCreateUser(InetAddress.getLocalHost().getHostName());
        vo.setUpdateUser(InetAddress.getLocalHost().getHostName());
        vo.setSkillId("SK001");
        vo.setSkillName("Test3");
        vo.setEmpName("EmployeeName");
        vo.setEmpNo("12345");

        dataLs.add(vo);

        vo = new SkillMemberRefVO();

        vo.setCreateUser(InetAddress.getLocalHost().getHostName());
        vo.setUpdateUser(InetAddress.getLocalHost().getHostName());
        vo.setSkillId("SK001");
        vo.setSkillName("Test45");
        vo.setEmpName("EmployeeName");
        vo.setEmpNo("123456");

        dataLs.add(vo);

        facade.excuteStickSkill(dataLs);
    }

    @Test
    public void testGetSkillOrderType() {
        List<SkillOrderTypeMapVO> dataLs = facade.getSkillOrderTypeMap();
        System.out.println("===========================");
        System.out.println(JsonUtil.toJson(dataLs));
    }
    
    @Test
    public void testGetMemberWithoutSpecificSkill() {
    	String skillId = "SK001";
    	
    	List<MemberInfoVO> dataList = facade.getMemberWithoutSpecificSkill(skillId);
    	
    	System.out.println("==================================================");
        System.out.println(JsonUtil.toJson(dataList));
        System.out.println("==================================================");
    }
}
