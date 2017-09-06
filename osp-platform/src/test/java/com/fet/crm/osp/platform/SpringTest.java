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

package com.fet.crm.osp.platform;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

/**
 * 
 * @author V.J CHOU
 */
@RunWith(SpringJUnit4ClassRunner.class)
//以下設定「ContextConfiguration」是供此程式讀取使用
@ContextConfiguration(locations = { 
		"classpath*:META-INF/applicationContext-test.xml"
})
public class SpringTest extends AbstractTest {

	private RequestMappingInfoHandlerMapping test;
	
}
