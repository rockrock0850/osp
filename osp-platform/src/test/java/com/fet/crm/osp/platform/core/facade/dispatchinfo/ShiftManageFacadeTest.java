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

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.platform.SpringTest;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.ShiftMaintainVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.ShiftTypeSettingVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.ShiftTypeSkillMapVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.component.FileVO;

/**
 * [班別 班表] 總體服務窗口 測試單元
 * 
 * @author LawrenceLai,AndrewLee
 */
public class ShiftManageFacadeTest extends SpringTest {

    @Autowired
    private ShiftManageFacade facade;
    
    @Test
    public void testGetShiftTypeInfo() {
    	List<ShiftTypeSettingVO> dataList = facade.getShiftTypeInfo();
    	
    	System.out.println("==================================================");
	    System.out.println(JsonUtil.toJson(dataList));
	    System.out.println("==================================================");
    }
    
    
    @Test
    public void testQueryShiftTypeInfoByshiftTypeName() {
        List<ShiftTypeSettingVO> dataLs = facade.getShiftTypeInfoByshiftTypeName("早");

        System.out.println("==================================================");
	    System.out.println(JsonUtil.toJson(dataLs));
	    System.out.println("==================================================");
    }

    @Test
    public void testCreateShiftType() throws UnknownHostException {
        String shiftTypeId = "大夜班";
        String shiftTypeName = "大夜班";
        String startTime = "00:00";
        String endTime = "06:45";
        String breakStartTime = "01:00";
        String breakEndTime = "04:00";
        String createUser = InetAddress.getLocalHost().getHostName();
        String updateUser = InetAddress.getLocalHost().getHostName();
        ;

        ShiftTypeSettingVO vo = new ShiftTypeSettingVO();

        vo.setShiftTypeId(shiftTypeId);
        vo.setShiftTypeName(shiftTypeName);
        vo.setStartTime(startTime);
        vo.setEndTime(endTime);
        vo.setBreakStartTime(breakStartTime);
        vo.setBreakEndTime(breakEndTime);
        vo.setCreateUser(createUser);
        vo.setUpdateUser(updateUser);

        facade.createShiftType(vo);
    }

    @Test
    public void testModifyShiftType() throws UnknownHostException {
        String shiftTypeId = "大夜班";
        String shiftTypeName = "大夜班";
        String startTime = "00:00";
        String endTime = "06:45";
        String breakStartTime = "01:00";
        String breakEndTime = "04:00";
        String updateUser = InetAddress.getLocalHost().getHostName();

        ShiftTypeSettingVO vo = new ShiftTypeSettingVO();

        vo.setShiftTypeId(shiftTypeId);
        vo.setShiftTypeName(shiftTypeName);
        vo.setStartTime(startTime);
        vo.setEndTime(endTime);
        vo.setBreakStartTime(breakStartTime);
        vo.setBreakEndTime(breakEndTime);
        vo.setUpdateUser(updateUser);

        facade.modifyShiftType(vo);
    }

    @Test
    public void testModifySkillMapping() throws UnknownHostException {
        List<ShiftTypeSkillMapVO> voLs = new ArrayList<>();

        ShiftTypeSkillMapVO vo = new ShiftTypeSkillMapVO();

        vo.setShiftTypeId("大夜班");
        vo.setSkillId("SK005");
        // updateUdate & Update User 根據DB檔案的敘述.若是insert的話等同createDate & creatUser
        // 此處的邏輯為先刪除再新增.所以並非真正意義上的modify
        vo.setCreateUser(InetAddress.getLocalHost().getHostName());
        vo.setUpdateUser(InetAddress.getLocalHost().getHostName());

        voLs.add(vo);

        vo = new ShiftTypeSkillMapVO();

        vo.setShiftTypeId("大夜班");
        vo.setSkillId("SK008");
        vo.setCreateUser(InetAddress.getLocalHost().getHostName());
        vo.setUpdateUser(InetAddress.getLocalHost().getHostName());

        voLs.add(vo);

        facade.modifySkillMapping(voLs);
    }

    @Test
    public void testQuerySkillMappingInfoByShiftTypeId() {
        List<ShiftTypeSkillMapVO> dataLsLeft = facade.getSkillMappingInfoNotInShiftTypeId("E異");
        List<ShiftTypeSkillMapVO> dataLsRight = facade.getSkillMappingInfoByShiftTypeId("E異");

        System.out.println("==========================");
        System.out.println(JsonUtil.toJson(dataLsLeft));
        System.out.println(JsonUtil.toJson(dataLsRight));
    }

    @Test
    public void testCreateShiftAttachment() throws IOException {
        String filePath = "tw.yahoo.com";
        int fileLength = 9527;
        String createUser = InetAddress.getLocalHost().getHostName();
        String dtYear = "2016";
        String dtMonth = "11";
        String fileExtensions = "xls";
        
        //改成自己的路徑.否則很大的機率發生NullPointerException
        Path path = Paths.get("D:/WorkSpace/document/03_專案架構設計文件(SD)/班表匯入格式.xlsx");
        String fileName = path.getFileName().toString();

        byte[] data = java.nio.file.Files.readAllBytes(path);        
        
        FileVO fileVO = new FileVO();
        
        fileVO.setName(fileName);
        fileVO.setFileBinary(data);
        fileVO.setCreateUser(createUser);
        fileVO.setExtension(fileExtensions);
        //改成自己的路徑.否則很大的機率發生NullPointerException
        fileVO.setFile(new File("D:/WorkSpace/document/03_專案架構設計文件(SD)/班表匯入格式.xlsx"));
        
        ShiftMaintainVO maintainVO = new ShiftMaintainVO();

        maintainVO.setDtMonth(dtMonth);
        maintainVO.setDtYear(dtYear);
        maintainVO.setShiftAttachment(fileVO);
        maintainVO.setCreateUser(createUser);


        facade.createShiftAttachment(maintainVO);
    }

    @Test
    public void testQueryShiftAttachment() {
        //注意.若都是空的話代表查無資料
        FileVO fileVO = facade.queryShiftAttachment("2016","12");
        
        System.out.println("==========================");
        System.out.println(JsonUtil.toJson(fileVO));
    }

}
