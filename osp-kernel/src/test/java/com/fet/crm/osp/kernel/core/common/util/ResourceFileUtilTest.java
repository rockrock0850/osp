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

import org.junit.Test;

import com.fet.crm.osp.kernel.BasicTest;

/**
 * 資源管理器測試. <br>
 * 
 * @author VJChou
 */
public class ResourceFileUtilTest extends BasicTest {

    @Test
    public void testGetResourcePath() {
        String sqltext = ResourceFileUtil.SQL_ORDER.getResource("GET_ORDER_MAIN_OSP");

        System.out.println(sqltext);
    }

}
