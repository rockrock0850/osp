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
package com.fet.crm.osp.common;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.common.mock.MockService;
import com.fet.crm.osp.common.util.JsonUtil;
import com.fet.crm.osp.common.vo.kernel.input.param.CustInfoForOSPParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.NEInfoParamVO;
import com.fet.crm.osp.common.vo.kernel.output.StringOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.checkCustInfo.CustInfoForOSPOutputVO;
import com.fet.crm.osp.common.vo.kernel.process.ResultHeader;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustInfoForOSPRtnVO;
import com.fet.crm.osp.kernel.SpringTest;

/**
 * @author RichardHuang
 */
public class MockServiceTest extends SpringTest {
    
    @Autowired
    private MockService mockService;
    
    @Test
    public void testGetCustInfoForOSP() {
    	String userId = "pcyang";
        String subscriberId = "1111";
        
        CustInfoForOSPParamVO custInfoForOSPParamVO = new CustInfoForOSPParamVO();
		custInfoForOSPParamVO.setUserId(userId);
		custInfoForOSPParamVO.setSubscriberId(subscriberId);
		
		CustInfoForOSPOutputVO result = mockService.getCustInfoForOSP(custInfoForOSPParamVO);
        
        ResultHeader resultHeader = result.getResultHeader();
        CustInfoForOSPRtnVO resultBody = result.getResultBody();
        
        System.out.println(resultHeader);
        System.out.println(resultBody);
    }
    
    @Test
    public void testGetNEInfo() {
        NEInfoParamVO neInfoParamVO = new NEInfoParamVO();
		StringOutputVO result = mockService.getNEInfo(neInfoParamVO);
        
        ResultHeader resultHeader = result.getResultHeader();
        String resultBody = result.getResultBody();
        
        System.out.println(JsonUtil.toJson(resultHeader));
        System.out.println(resultBody);
    }
    
}
