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

package com.fet.crm.osp.platform.mware.client.restful.service;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fet.crm.osp.common.util.JsonUtil;
import com.fet.crm.osp.common.util.RestUtil;
import com.fet.crm.osp.platform.mware.client.common.code.NCPCustDataQueryStatusCode;
import com.fet.crm.osp.platform.mware.client.vo.NCPRESTReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.componet.MessageVO;

/**
 * 
 * @author LawrenceLai
 */
@Service
public class NCPRESTClient {

	/**
	 * 
	 * @param ownId
	 * @param subscribeId
	 * @return boolean
	 */
	public boolean saveUsingMsisdnToCache(String ownId, String partyId, String subscriberId, String msisdn) {
		final String url = "http://ospsit.fareastone.com.tw/NSP2/ncpCustDataQuery/saveUsingMsisdnToCache.action";

		HashMap<String, Object> request = new HashMap<String, Object>();
		request.put("ownId", ownId);
		request.put("partyId", partyId);
		request.put("clickedSubscrId", subscriberId);
		request.put("clickedMsisdn", msisdn);

		String requestJson = JsonUtil.toJson(request);

		ResponseEntity<String> response = RestUtil.doPostFromJson(url, requestJson);

		HttpStatus responseStatusCode = response.getStatusCode();

		if (HttpStatus.OK.equals(responseStatusCode)) {
			try {
				String responseBody = response.getBody();

				ObjectMapper mapper = new ObjectMapper();
				JsonNode root;
				root = mapper.readTree(responseBody);
				
				JsonNode resultNode = root.get("result");

				NCPRESTReturnVO result = JsonUtil.fromJson(resultNode.toString(), NCPRESTReturnVO.class);

				String resultStatus = result.getStatus();
				MessageVO message = result.getMessage();

				// NSP2 API 發生系統未知錯誤時，result message 不為 null
				if (message != null) {
					// TODO 將回傳訊息的內容記錄至 log 中
				}

				// JsonNode statusNode = resultNode.get("status");
				// String status = statusNode.getTextValue();

				if (NCPCustDataQueryStatusCode.SUCCESS.getCode().equals(resultStatus)) {
					return true;
				}
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

}
