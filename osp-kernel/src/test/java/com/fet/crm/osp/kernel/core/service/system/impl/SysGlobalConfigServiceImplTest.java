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
package com.fet.crm.osp.kernel.core.service.system.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.kernel.SpringTest;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.core.service.system.ISysGlobalConfigService;

/**
 * @author RichardHuang
 */
public class SysGlobalConfigServiceImplTest extends SpringTest {
    
    @Autowired
    private ISysGlobalConfigService sysGlobalConfigService;
    
    @Test
    public void testGetSysConfValue() {
        String confValue = sysGlobalConfigService.getSysConfValue(Constant.NCP_QUERY_CUST_INFO_ACTION_URL_CONFIG);
        
        System.out.println(confValue);
    }
    
}
