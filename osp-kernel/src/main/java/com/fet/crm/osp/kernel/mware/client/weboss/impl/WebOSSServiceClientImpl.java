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
package com.fet.crm.osp.kernel.mware.client.weboss.impl;

import java.rmi.RemoteException;
import java.util.Date;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fet.crm.osp.common.util.DateUtil;
import com.fet.crm.osp.common.util.IdentifierIdUtil;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.mware.client.AbstractBasicSOAPClient;
import com.fet.crm.osp.kernel.mware.client.code.WebOSSServiceReturnCode;
import com.fet.crm.osp.kernel.mware.client.weboss.IWebOSSServiceClient;
import com.fet.crm.osp.kernel.mware.exception.ESBAPIException;
import com.fet.crm.osp.kernel.mware.exception.MwareExceptionFactory;
import com.fet.esb.service.biz.services.WebOSSServiceStub;
import com.tibco.www.schemas.servicemgmt_fulfillment.sharedresources.xsd.webossservice_xsd.BusinessEntityInfo;
import com.tibco.www.schemas.servicemgmt_fulfillment.sharedresources.xsd.webossservice_xsd.ManageNEInfoRequestDocument;
import com.tibco.www.schemas.servicemgmt_fulfillment.sharedresources.xsd.webossservice_xsd.ManageNEInfoRequestType;
import com.tibco.www.schemas.servicemgmt_fulfillment.sharedresources.xsd.webossservice_xsd.ManageNEInfoResponseDocument;
import com.tibco.www.schemas.servicemgmt_fulfillment.sharedresources.xsd.webossservice_xsd.ManageNEInfoResponseType;
import com.tibco.www.schemas.servicemgmt_fulfillment.sharedresources.xsd.webossservice_xsd.NEInfo;
import com.tibco.www.schemas.servicemgmt_fulfillment.sharedresources.xsd.webossservice_xsd.NEResult;

import adam.esb.fet.com.xsd.AuthInfo;
import adam.esb.fet.com.xsd.RequestHeader;
import adam.esb.fet.com.xsd.ReturnHeader;
import adam.esb.fet.com.xsd.TxInfo;

/**
 * WebOSSService SOAP Client 實作
 * 
 * @author RichardHuang
 */
@Service
public class WebOSSServiceClientImpl extends AbstractBasicSOAPClient implements IWebOSSServiceClient {
    
    @Override
    public String getNEInfo(String paymentCategory, String msisdn, String accountName, String subType, String userId) {
        System.out.println("WebOSSServiceClientImpl.getNEInfo : Input Param [paymentCategory = " + paymentCategory
                + ", msisdn = " + msisdn + ", accountName = " + accountName + ", subType = " + subType + "]");
        
        String targetEndpoint = getSysConfValueByConfId(WEB_OSS_SERVICE_ENDPOINT_URL);
        
        WebOSSServiceStub stub = null;
        
        try {
            stub = createServiceStub(targetEndpoint);
            
        } catch (AxisFault e) {
            throw new MwareExceptionFactory().getException(e);
        }
        
		ManageNEInfoRequestDocument requestDocument = 
                createRequest(paymentCategory, msisdn, accountName, Integer.valueOf(subType), userId);
        
        ManageNEInfoResponseDocument responseDocument = null;
        
        try {
            responseDocument = stub.manageNEInfo(requestDocument);
            
        } catch (RemoteException e) {
            throw new MwareExceptionFactory().getException(e);
        }
        
        ManageNEInfoResponseType response = responseDocument.getManageNEInfoResponse();
        ReturnHeader returnHeader = response.getReturnHeader();
        String returnCode = returnHeader.getReturnCode();
        String returnMesg = returnHeader.getReturnMesg();
        String reserved1 = returnHeader.getReserved1();
        
        if (!StringUtils.equals(returnCode, WebOSSServiceReturnCode.SUCCESS.getCode())) {
            throw new ESBAPIException("["+returnCode+"] " + returnMesg + ". " + reserved1);
        }
        
        NEResult nEResult = response.getNeResult();
        Assert.state(nEResult != null);
        
        String message = nEResult.getMessage();
        int startIndex = StringUtils.indexOf(message, APNID);
        Assert.state(startIndex != -1);
        
        message = StringUtils.substring(message, startIndex, message.lastIndexOf("END"));
        
        return message;
    }
    
    private WebOSSServiceStub createServiceStub(String targetEndpoint) throws AxisFault {
        return new WebOSSServiceStub(targetEndpoint);
    }
    
    private ManageNEInfoRequestDocument createRequest(String paymentCategory, String msisdn, String accountName, int subType, String userId) {
        AuthInfo authInfo = AuthInfo.Factory.newInstance();
        authInfo.setChannelID(Constant.SYSTEM_ID);
        authInfo.setUserID(userId);
        authInfo.setPassword(Constant.SYSTEM_ID);
        
        TxInfo transInfo = TxInfo.Factory.newInstance();
        transInfo.setTransChannelID(Constant.SYSTEM_ID);
        transInfo.setTransUserID(userId);
        transInfo.setTransBPID(IdentifierIdUtil.getUuid());
        transInfo.setTransPath(Constant.SYSTEM_ID);
        
        RequestHeader requestHeader = RequestHeader.Factory.newInstance();
        requestHeader.setAuthInfo(authInfo);
        requestHeader.setTransInfo(transInfo);
        
        // beInfo
        BusinessEntityInfo paramBusinessEntityInfo = BusinessEntityInfo.Factory.newInstance();
        paramBusinessEntityInfo.setBusinessEntityNumber(Constant.BUSINESS_ENTITY_NUMBER);
        paramBusinessEntityInfo.setPaymentCategory(paymentCategory);
        
        // neInfo
        NEInfo paramNEInfo = NEInfo.Factory.newInstance();
        paramNEInfo.setMSISDN(msisdn);
        paramNEInfo.setAccountName(accountName);
        paramNEInfo.setAction("VIEW");
        paramNEInfo.setReqChannel(Constant.SYSTEM_ID);
        paramNEInfo.setSubType(subType);
        paramNEInfo.setTimestamp(DateUtil.formatDt(new Date(), DateUtil.DATE_TIME_PATTERN_FOR_FILE_NAME));
//        paramNEInfo.setIsVolteSub("Y");
        
        ManageNEInfoRequestType request = ManageNEInfoRequestType.Factory.newInstance();
        request.setRequestHeader(requestHeader);
        request.setBeInfo(paramBusinessEntityInfo);
        request.setNeInfo(paramNEInfo);
        
        ManageNEInfoRequestDocument requestDocument = ManageNEInfoRequestDocument.Factory.newInstance();
        requestDocument.setManageNEInfoRequest(request);
        
        return requestDocument;
    }
    
}
