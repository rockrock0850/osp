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

package com.fet.crm.osp.platform.mware.client.restful.proxy;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.platform.SpringTest;

/**
 * 
 * @author LawrenceLai
 */
public class NCPRESTClientProxyTest extends SpringTest {
	
	@Autowired
	private NCPRESTClientProxy proxy;

	@Test
	public void testSaveUsingMsisdnToCache() {
		String ownId = "ce0ff9ae-7a3f-459a-a6e6-52586f9fdd7b";
		String partyId = "1504211214470895845";
		String subscriberId = "210123901";
		String msisdn = "0912677149";
				
		boolean result = proxy.saveUsingMsisdnToCache(ownId, partyId, subscriberId, msisdn);
		
		System.out.println("==================================================");
		System.out.println("result = " + result);
		System.out.println("==================================================");
	}

}
