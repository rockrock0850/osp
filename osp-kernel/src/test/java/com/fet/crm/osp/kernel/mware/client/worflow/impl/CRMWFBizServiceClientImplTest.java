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
package com.fet.crm.osp.kernel.mware.client.worflow.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.kernel.SpringTest;
import com.fet.crm.osp.kernel.mware.client.pojo.AuthLevelInfoInputPOJO;
import com.fet.crm.osp.kernel.mware.client.pojo.AuthLevelInfoPOJO;
import com.fet.crm.osp.kernel.mware.client.workflow.ICRMWFBizServiceClient;

/**
 * @author RichardHuang
 */
public class CRMWFBizServiceClientImplTest extends SpringTest {
    
    @Autowired
    private ICRMWFBizServiceClient crmWFBizServiceClient;
    
    @Test
    public void testGetAuthLevelInfo() {
        String salesId = "62830";
        String rocId = "S122682986";
        String ivrCode = "2484";
        String salesChannelType = "Retail";
		String identificationType = null;
		String subscriberId = null;
		String postpaidWirelessId = null;
		String authLevel = "1";
		
		AuthLevelInfoInputPOJO inputParam = new AuthLevelInfoInputPOJO();
		inputParam.setSalesId(salesId);
		inputParam.setRocId(rocId);
		inputParam.setIvrCode(ivrCode);
		inputParam.setSubscriberId(subscriberId);
		inputParam.setSalesChannelType(salesChannelType);
		inputParam.setPostpaidWirelessId(postpaidWirelessId);
		inputParam.setIdentificationType(identificationType);
		inputParam.setAuthLevel(authLevel);
		
		AuthLevelInfoPOJO authLevelInfo = crmWFBizServiceClient.getAuthLevelInfo(inputParam);
        
        showPojoContent(authLevelInfo);
    }
    
}
