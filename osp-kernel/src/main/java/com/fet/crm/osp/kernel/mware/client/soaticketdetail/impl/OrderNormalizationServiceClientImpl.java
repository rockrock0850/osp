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
package com.fet.crm.osp.kernel.mware.client.soaticketdetail.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fet.crm.osp.common.util.IdentifierIdUtil;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.mware.client.AbstractBasicSOAPClient;
import com.fet.crm.osp.kernel.mware.client.code.OrderNormalizationServiceReturnCode;
import com.fet.crm.osp.kernel.mware.client.soaticketdetail.IOrderNormalizationServiceClient;
import com.fet.crm.osp.kernel.mware.exception.ESBAPIException;
import com.fet.crm.osp.kernel.mware.exception.MwareExceptionFactory;
import com.fet.esb.crm.biz.services.ordernormalizationservice.OrderNormalizationServiceStub;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.ordernormalizationservice_xsd.GetSOATicketDetailRequestDocument;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.ordernormalizationservice_xsd.GetSOATicketDetailRequestDocument.GetSOATicketDetailRequest;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.ordernormalizationservice_xsd.GetSOATicketDetailResponseDocument;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.ordernormalizationservice_xsd.GetSOATicketDetailResponseDocument.GetSOATicketDetailResponse;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.ordernormalizationservice_xsd.SOATicketIofo;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.ordernormalizationservice_xsd.TicketInfo;

import adam.esb.fet.com.xsd.AuthInfo;
import adam.esb.fet.com.xsd.RequestHeader;
import adam.esb.fet.com.xsd.ReturnHeader;
import adam.esb.fet.com.xsd.TxInfo;

/**
 * SOA 進件詳細資訊服務 SOAP Client 實作類別
 * 
 * @author RichardHuang
 */
@Service
public class OrderNormalizationServiceClientImpl extends AbstractBasicSOAPClient implements IOrderNormalizationServiceClient {
    
    @Override
    public List<SOATicketIofo> getSOATicketDetail(List<String> msisdnParamList, String userId) {
        String targetEndpoint = getSysConfValueByConfId(ORDER_NORMALIZATION_SERVICE_ENDPOINT_URL);
        OrderNormalizationServiceStub proxy = null;
        
		try {
			proxy = createServiceStub(targetEndpoint);
			
		} catch (AxisFault e) {
			throw new MwareExceptionFactory().getException(e);
		}

        List<SOATicketIofo> soaTicketInfoList = new ArrayList<>();
        
        Date sysDate = new Date();
        
        for (String msisdnParam : msisdnParamList) {
            GetSOATicketDetailRequestDocument requestDocument = createRequest(msisdnParam, sysDate, userId);
            GetSOATicketDetailResponseDocument responseDocument = null;
            
			try {
				responseDocument = proxy.getSOATicketDetail(requestDocument);
				
			} catch (RemoteException e) {
				throw new MwareExceptionFactory().getException(e);
			}
			
            GetSOATicketDetailResponse response = responseDocument.getGetSOATicketDetailResponse();
            ReturnHeader returnHeader = response.getReturnHeader();
            String returnCode = returnHeader.getReturnCode();
            String returnMesg = returnHeader.getReturnMesg();
            
            System.out.println("OrderNormalizationServiceImpl.getSOATicketDetail : Input Param [ msisdn = " 
                    + msisdnParam + " ], returnCode = " + returnCode + ", returnMesg = " + returnMesg);
            
            Assert.state(!StringUtils.equals(returnCode, OrderNormalizationServiceReturnCode.NO_DATA.getCode()));
            
            if (!StringUtils.equals(returnCode, OrderNormalizationServiceReturnCode.SUCCESS.getCode())) {
                throw new ESBAPIException("[" + returnCode + "] " + returnMesg);
            }
            
            SOATicketIofo soaTicketInfo = response.getSOATicketIofo();
            Assert.state(soaTicketInfo != null);
            
            soaTicketInfoList.add(soaTicketInfo);
        }
        
        return soaTicketInfoList;
    }

    private GetSOATicketDetailRequestDocument createRequest(String msisdn, Date sysDate, String userId) {
        AuthInfo authInfo = AuthInfo.Factory.newInstance();
        authInfo.setChannelID(Constant.SYSTEM_ID);
        authInfo.setUserID(userId);
        authInfo.setPassword(getAPIPassword());
        
        TxInfo transInfo = TxInfo.Factory.newInstance();
        transInfo.setTransChannelID(Constant.SYSTEM_ID);
        transInfo.setTransUserID(userId);
        transInfo.setTransBPID(IdentifierIdUtil.getUuid());
        transInfo.setTransPath(Constant.SYSTEM_ID);
        
        RequestHeader requestHeader = RequestHeader.Factory.newInstance();
        requestHeader.setAuthInfo(authInfo);
        requestHeader.setTransInfo(transInfo);
        
        Calendar custSpecifyDate = Calendar.getInstance();
        custSpecifyDate.setTime(sysDate);
        
        TicketInfo ticketInfo = TicketInfo.Factory.newInstance();
        ticketInfo.setMsisdn(msisdn);
        ticketInfo.setCustSpecifyDate(custSpecifyDate);
        
        GetSOATicketDetailRequest request = GetSOATicketDetailRequest.Factory.newInstance();
        request.setRequestHeader(requestHeader);
        request.setTicketInfo(ticketInfo);
        
        
        GetSOATicketDetailRequestDocument requestDocument = GetSOATicketDetailRequestDocument.Factory.newInstance();
        requestDocument.setGetSOATicketDetailRequest(request);
        return requestDocument;
    }

    private OrderNormalizationServiceStub createServiceStub(String targetEndpoint) throws AxisFault {
        return new OrderNormalizationServiceStub(targetEndpoint);
    }
    
}
