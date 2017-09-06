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
package com.fet.crm.osp.kernel.mware.client.billing.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustBillingInfoRtnVO;
import com.fet.crm.osp.kernel.SpringTest;
import com.fet.crm.osp.kernel.mware.client.billling.impl.DecisionDataServiceClientImpl;

public class DecisionDataServiceClientImplTest extends SpringTest {
    
    @Autowired
    private DecisionDataServiceClientImpl decisionDataServiceClient;
    
    @Test
    public void testQueryCustomerBillingInfo() {
    	String clientIP = "10.64.195.251";
        String accountId = "pcyang";
        String subscriberId = "325850845";
        
		CustBillingInfoRtnVO result = decisionDataServiceClient.queryCustomerBillingInfo(clientIP, accountId, subscriberId);
        
        showPojoContent(result);
    }
}
