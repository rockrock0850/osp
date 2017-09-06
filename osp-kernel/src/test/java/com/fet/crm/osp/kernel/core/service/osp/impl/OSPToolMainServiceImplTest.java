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
package com.fet.crm.osp.kernel.core.service.osp.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.common.vo.kernel.result.CustInfoForAppPartRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.agentinfo.AgentInfoRtnVO;
import com.fet.crm.osp.kernel.SpringTest;
import com.fet.crm.osp.kernel.core.service.osp.IOSPToolMainService;

/**
 * @author RichardHuang
 */
public class OSPToolMainServiceImplTest extends SpringTest {
    
    @Autowired
    private IOSPToolMainService ospToolMainService;
    
    @Test
    public void testUpdateTxIdByIdentifyId() {
        String ospKey = "1";
        String txId = "22";
        
        boolean result = ospToolMainService.updateTxIdByIdentifyId(ospKey, txId);
        
        Assert.assertTrue(result);
    }
    
    @Test
    public void testGetCustInfoForAppPart() {
//        String ownId = "0030594f-8192-4e48-be1d-355702c0ca2b";
//        String msisdn = "0926636087";
        
        String ownId = "0efb6bb9-79d4-4658-9641-6d3fd68f22af";
        String msisdn = "0955886467";
//        String idType = "NBT03";
//        String rocId = "206607922";
        
        String idType = null;
        String rocId = null;
        
        CustInfoForAppPartRtnVO result = ospToolMainService.getCustInfoForAppPart(ownId, msisdn, idType, rocId);
        
        System.out.println(result);
        
        Assert.assertTrue(result != null);
    }
    
    @Test
    public void testGetAgentInfo() {
//        String ntAccountId = "sylee";
//        String ntAccountId = "ahong";
//        String ntAccountId = "wplee";
//        String ntAccountId = "pcyang";
//        String ntAccountId = "sywang";
//        String ntAccountId = "rhhsieh";
        String ntAccountId = "ychou";
        
        AgentInfoRtnVO agentInfoRtnVO = ospToolMainService.getAgentInfo(ntAccountId);
        
        System.out.println(agentInfoRtnVO.getInternaluseraccountVO());
        System.out.println(agentInfoRtnVO.getStaffVO());
        System.out.println(agentInfoRtnVO.getUsersVO());
        System.out.println(agentInfoRtnVO.getGroupDefinitionVO());
        
        System.out.println("CacheChannel = " + agentInfoRtnVO.getCacheChannel());
        System.out.println("CacheChannelIdPasswordPostpaid = " + agentInfoRtnVO.getCacheChannelIdPasswordPostpaid());
        System.out.println("CacheChannelIdPasswordPrepaid = " + agentInfoRtnVO.getCacheChannelIdPasswordPrepaid());
        System.out.println("CacheChannelIdPostpaid = " + agentInfoRtnVO.getCacheChannelIdPostpaid());
        System.out.println("CacheChannelIdPrepaid = " + agentInfoRtnVO.getCacheChannelIdPrepaid());
        System.out.println("CacheChannelPostpaid = " + agentInfoRtnVO.getCacheChannelPostpaid());
        System.out.println("CacheChannelPrepaid = " + agentInfoRtnVO.getCacheChannelPrepaid());
        System.out.println("Ivrcode = " + agentInfoRtnVO.getIvrcode());
        
        System.out.println("ChannelGroupId = " + agentInfoRtnVO.getChannelGroupId());
        
        Assert.assertTrue(agentInfoRtnVO != null);
    }
    
}
