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
package com.fet.crm.osp.kernel.mware.client.ticketpool.impl;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.common.util.IdentifierIdUtil;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.mware.client.AbstractBasicSOAPClient;
import com.fet.crm.osp.kernel.mware.client.code.TicketPoolServiceReturnCode;
import com.fet.crm.osp.kernel.mware.client.ticketpool.ITicketPoolServiceClient;
import com.fet.crm.osp.kernel.mware.exception.ESBAPIException;
import com.fet.crm.osp.kernel.mware.exception.MwareExceptionFactory;
import com.fet.esb.crm.biz.services.ticketpoolservice.TicketPoolServiceStub;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.ticketpoolservice_xsd.OrderStatus;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.ticketpoolservice_xsd.ProcessUser;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.ticketpoolservice_xsd.UpdateTicketStatusRequestDocument;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.ticketpoolservice_xsd.UpdateTicketStatusRequestDocument.UpdateTicketStatusRequest;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.ticketpoolservice_xsd.UpdateTicketStatusResponseDocument;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.ticketpoolservice_xsd.UpdateTicketStatusResponseDocument.UpdateTicketStatusResponse;

import adam.esb.fet.com.xsd.AuthInfo;
import adam.esb.fet.com.xsd.RequestHeader;
import adam.esb.fet.com.xsd.ReturnHeader;
import adam.esb.fet.com.xsd.TxInfo;

/**
 * TicketPoolMainService SOAP Client 實作
 * 
 * @author RichardHuang
 */
@Service
public class TicketPoolServiceClientImpl extends AbstractBasicSOAPClient implements ITicketPoolServiceClient {

	@Override
	public boolean updateTicketStatus(String userId, String poolKey, String status, String processUser, String ntAccount) {
		String targetEndpoint = getSysConfValueByConfId(TICKET_POOL_SERVICE_ENDPOINT_URL);
		TicketPoolServiceStub stub = null;
		
		try {
			stub = createServiceStub(targetEndpoint);
			
		} catch (AxisFault e) {
			throw new MwareExceptionFactory().getException(e);
		}
		
		UpdateTicketStatusRequestDocument requestDocument = 
				createUpdateTicketStatusRequest(userId, poolKey, status, processUser, ntAccount);
		
		UpdateTicketStatusResponseDocument responseDocument = null;
		
		try {
			responseDocument = stub.updateTicketStatus(requestDocument);
			
		} catch (RemoteException e) {
			throw new MwareExceptionFactory().getException(e);
		}
		
		UpdateTicketStatusResponse response = responseDocument.getUpdateTicketStatusResponse();
		ReturnHeader returnHeader = response.getReturnHeader();
		String returnCode = returnHeader.getReturnCode();
		String returnMesg = returnHeader.getReturnMesg();
		
		System.out.println("TicketPoolService.updateTicketStatus API : returnCode = " + returnCode + ", returnMesg = " + returnMesg);
		
		if (!StringUtils.equals(returnCode, TicketPoolServiceReturnCode.SUCCESS.getCode())) {
            throw new ESBAPIException("["+returnCode+"] " + returnMesg);
        }
		
		return true;
	}

	private TicketPoolServiceStub createServiceStub(String targetEndpoint) throws AxisFault {
		return new TicketPoolServiceStub(targetEndpoint);
	}
	
	private UpdateTicketStatusRequestDocument createUpdateTicketStatusRequest(
			String userId, String poolKey, String status, String processUser, String ntAccount) {
		// AuthInfo
		AuthInfo authInfo = AuthInfo.Factory.newInstance();
		authInfo.setChannelID(Constant.SYSTEM_ID);
		authInfo.setUserID(userId);
		authInfo.setPassword(Constant.ESB_API_PASSWORD);
		
		// TransInfo
		TxInfo transInfo = TxInfo.Factory.newInstance();
		transInfo.setTransChannelID(Constant.SYSTEM_ID);
		transInfo.setTransUserID(userId);
		transInfo.setTransBPID(IdentifierIdUtil.getUuid());
		transInfo.setTransPath(Constant.SYSTEM_ID);
		
		RequestHeader requestHeader = RequestHeader.Factory.newInstance();
		requestHeader.setAuthInfo(authInfo);
		requestHeader.setTransInfo(transInfo);
		
		ProcessUser paramProcessUser = ProcessUser.Factory.newInstance();
		paramProcessUser.setProcessUserID(processUser);
		paramProcessUser.setProcessUserAccount(ntAccount);
		
		OrderStatus orderStatus = OrderStatus.Factory.newInstance();
		orderStatus.setPoolKey(poolKey);
		orderStatus.setStatus(status);
		orderStatus.setProcessUser(paramProcessUser);
		
		UpdateTicketStatusRequest request = UpdateTicketStatusRequest.Factory.newInstance();
		request.setRequestHeader(requestHeader);
		request.setOrderStatus(orderStatus);
		
		UpdateTicketStatusRequestDocument requestDocument = 
				UpdateTicketStatusRequestDocument.Factory.newInstance();
		
		requestDocument.setUpdateTicketStatusRequest(request);
		
		return requestDocument;
	}
    
}
