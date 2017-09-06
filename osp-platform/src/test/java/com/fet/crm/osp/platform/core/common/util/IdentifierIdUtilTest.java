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

package com.fet.crm.osp.platform.core.common.util;

import org.junit.Test;

import com.fet.crm.osp.platform.BasicTest;

/**
 * 
 * @author VJChou
 */
public class IdentifierIdUtilTest extends BasicTest {

	@Test
	public void testGetOspOrderMId() {
		String orderMId = IdentifierIdUtil.getOspOrderMId();

		System.out.println("orderMId = " + orderMId);
	}

	@Test
	public void testGetUuid() {
		String uuid = IdentifierIdUtil.getUuid();

		System.out.println("uuid = " + uuid);
	}

}
