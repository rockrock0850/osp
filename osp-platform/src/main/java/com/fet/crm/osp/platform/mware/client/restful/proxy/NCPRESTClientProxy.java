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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.mware.client.restful.service.NCPRESTClient;

/**
 * 
 * @author LawrenceLai
 */
@Service
public class NCPRESTClientProxy {

	@Autowired
	private NCPRESTClient ncpRESTClient;

	public boolean saveUsingMsisdnToCache(String ownId, String partyId, String subscriberId, String msisdn) {
		return ncpRESTClient.saveUsingMsisdnToCache(ownId, partyId, subscriberId, msisdn);
	}
	
}
