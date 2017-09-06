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
package com.fet.crm.osp.kernel.mware.client.custmdm.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.common.util.JsonUtil;
import com.fet.crm.osp.common.vo.kernel.result.CacheSubscriberInfoRtnVO;
import com.fet.crm.osp.kernel.SpringTest;
import com.fet.crm.osp.kernel.mware.client.crmcust.impl.CRMCustMDMBizServiceClientImpl;

public class CRMCustMDMBizServiceClientImplTest extends SpringTest {
    
    @Autowired
    private CRMCustMDMBizServiceClientImpl crmCustMDMBizServiceClient;
    
    @Test
    public void testQueryCustomerBillingInfo() {
        String msisdn = "0912677149";
        String userId = "pcyang";
        
		CacheSubscriberInfoRtnVO result = crmCustMDMBizServiceClient.getCacheSubscriberInfoByKey(msisdn, userId);
        
        System.out.println(JsonUtil.toJson(result));
    }
}
