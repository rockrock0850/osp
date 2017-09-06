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
package com.fet.crm.osp.kernel.core.common.util;

import java.util.Date;

import org.junit.Test;

import com.fet.crm.osp.common.util.DateUtil;
import com.fet.crm.osp.kernel.SpringTest;

/**
 * @author RichardHuang
 */
public class CipherUtilTest extends SpringTest {
    
    @Test
    public void testEncrypt() {
        String encrptKey = "1234567890123456";
        String token = CipherUtil.encrypt(DateUtil.formatDt(new Date(), DateUtil.DATE_TIME_PATTERN), encrptKey);
        
        System.out.println("token = " + token);
    }
    
}
