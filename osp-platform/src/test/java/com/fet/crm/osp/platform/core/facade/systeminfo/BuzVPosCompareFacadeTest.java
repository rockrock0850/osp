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


import java.io.File;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.platform.SpringTest;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.common.util.PropertiesUtil;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.content.compare.BuzVPOSCompareResultVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.content.compare.BuzVPOSCompareVO;

/**
 *
 * @author AndrewLee
 */
public class BuzVPosCompareFacadeTest extends SpringTest {
    
    @Autowired
    private BuzVPosCompareFacade facade;

    /**
     * 取得檢核結果
     * 
     * @return List
     */
    @Test
    public void testGetCompareResult() {
        List<BuzVPOSCompareResultVO> dataList = facade.getCompareResult();

        System.out.println(JsonUtil.toJson(dataList));
    }
    
    @Test
    public void testDoExcelCompare() {
      //讀取兩份Excel的路徑
        String path = PropertiesUtil.getProperty("application.temp.file.xlsx");
        
        String VposFilePath = path + "/V_POS.xlsx";//FIXME 暫定這個名稱.後續再根據需求來改
        String aimsFilePath = path + "/AIMS.xlsx";
        File vPosfile = new File(VposFilePath);
        File aimsFile = new File(aimsFilePath);
        
        BuzVPOSCompareVO vo = new BuzVPOSCompareVO();
        vo.setvPosfile(vPosfile);
        vo.setAimsFile(aimsFile);
        vo.setUserId("9527");
        
        //WARNING service有從Session取資料塞createUser.若直接本機測試會拋Exception.建議先手動修改之後.測完在改回來
        facade.doExcelCompare(vo);
    }
}
