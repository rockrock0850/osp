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

package com.fet.crm.osp.kernel.mware.client.oe.impl;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fet.crm.osp.common.util.IdentifierIdUtil;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.mware.client.AbstractBasicSOAPClient;
import com.fet.crm.osp.kernel.mware.client.code.CRMOEBizServiceReturnCode;
import com.fet.crm.osp.kernel.mware.client.code.PaymentCategoryCode;
import com.fet.crm.osp.kernel.mware.client.oe.ICRMOEBizServiceClient;
import com.fet.crm.osp.kernel.mware.exception.ESBAPIException;
import com.fet.crm.osp.kernel.mware.exception.MwareExceptionFactory;
import com.fet.esb.crm.biz.services.crmoebizservice.CRMOEBizServiceStub;
import com.tibco.www.schemas.crm_fulfillmentbizservices.sharedresources.xsd.schema_xsd.AgreementResource;
import com.tibco.www.schemas.crm_fulfillmentbizservices.sharedresources.xsd.schema_xsd.AgreementResourceCriteria;
import com.tibco.www.schemas.crm_fulfillmentbizservices.sharedresources.xsd.schema_xsd.AgreementResourceResult;
import com.tibco.www.schemas.crm_fulfillmentbizservices.sharedresources.xsd.schema_xsd.ArrayOfAgreementResource;
import com.tibco.www.schemas.crm_fulfillmentbizservices.sharedresources.xsd.schema_xsd.BusinessEntityInfo;
import com.tibco.www.schemas.crm_fulfillmentbizservices.sharedresources.xsd.schema_xsd.QuerySimByMsisdnRequestDocument;
import com.tibco.www.schemas.crm_fulfillmentbizservices.sharedresources.xsd.schema_xsd.QuerySimByMsisdnRequestDocument.QuerySimByMsisdnRequest;
import com.tibco.www.schemas.crm_fulfillmentbizservices.sharedresources.xsd.schema_xsd.QuerySimByMsisdnResponseDocument;
import com.tibco.www.schemas.crm_fulfillmentbizservices.sharedresources.xsd.schema_xsd.QuerySimByMsisdnResponseDocument.QuerySimByMsisdnResponse;
import com.tibco.www.schemas.crm_fulfillmentbizservices.sharedresources.xsd.schema_xsd.TransactionInfo;

import tw.com.fet.www.itsoa.schemas.common_domain.sharedresources.xsd.base.schema_xsd2.AuthInfo;
import tw.com.fet.www.itsoa.schemas.common_domain.sharedresources.xsd.base.schema_xsd2.ReturnHeader;

/**
 * CRMOEBizService SOAP Client 實作類別
 * 
 * @author RichardHuang
 */
@Service
public class CRMOEBizServiceClientImpl extends AbstractBasicSOAPClient implements ICRMOEBizServiceClient {

	@Override
	public String querySimByMsisdn(String msisdn, String paymentCategory, String userId) {
		String targetEndpoint = getSysConfValueByConfId(CRM_OE_BIZ_SERVICE_ENDPOINT_URL);
		
		CRMOEBizServiceStub proxy = null;
				
		try {
			proxy = createServiceStub(targetEndpoint);
			
		} catch (AxisFault e) {
			throw new MwareExceptionFactory().getException(e);
		}
		
		QuerySimByMsisdnRequestDocument requestDocument = createRequest(msisdn, paymentCategory, userId);
		QuerySimByMsisdnResponseDocument responseDocument = null;
		
		try {
			responseDocument = proxy.querySimByMsisdn(requestDocument);
			
		} catch (RemoteException e) {
			throw new MwareExceptionFactory().getException(e);
		}
		
		QuerySimByMsisdnResponse response = responseDocument.getQuerySimByMsisdnResponse();
		
		ReturnHeader returnHeader = response.getReturnHeader();
		String returnCode = returnHeader.getReturnCode();
		String returnMesg = returnHeader.getReturnMesg();
		
		System.out.println("CRMOEBizServiceClientImpl.querySimByMsisdn : returnCode = " + returnCode + ", returnMesg = " + returnMesg);
		
		if (!StringUtils.equals(returnCode, CRMOEBizServiceReturnCode.SUCCESS.getCode()) 
				&& !StringUtils.equals(returnCode, CRMOEBizServiceReturnCode.SUCCESS_WITH_WARNING.getCode())) {
			throw new ESBAPIException("[" + returnCode + "]" + returnMesg);
		}
		
		AgreementResourceResult agreementResourceResult = response.getAgreementResourceResult();
		Assert.state(agreementResourceResult != null, "agreementResourceResult is null");
		
		ArrayOfAgreementResource resources = agreementResourceResult.getResources();
		Assert.state(resources != null, "resources is null");
		
		AgreementResource[] agreementResourceArray = resources.getAgreementResourceArray();
		Assert.state(ArrayUtils.isNotEmpty(agreementResourceArray));
		
		for (AgreementResource agreementResource : agreementResourceArray) {
			String resourceType = agreementResource.getResourceType();
			
			if (StringUtils.equals(resourceType, RESOURCE_TYPE_SIM)) {
				String resourceValue = agreementResource.getResourceValue();
				System.out.println("CRMOEBizServiceClientImpl.querySimByMsisdn : resourceValue = " + resourceValue);
				return resourceValue;
			}
		}
		
		return null;
	}

    private CRMOEBizServiceStub createServiceStub(String targetEndpoint) throws AxisFault {
        return new CRMOEBizServiceStub(targetEndpoint);
    }

    private QuerySimByMsisdnRequestDocument createRequest(String msisdn, String paymentCategory, String userId) {
        AuthInfo authInfo = AuthInfo.Factory.newInstance();
        authInfo.setChannelID(Constant.SYSTEM_ID);
        authInfo.setPassword(getAPIPassword());
        authInfo.setUserID(userId);
        
        TransactionInfo transInfo = TransactionInfo.Factory.newInstance();
        transInfo.setTransChannelId(Constant.SYSTEM_ID);
        transInfo.setTransUserId(userId);
        transInfo.setTransBpId(IdentifierIdUtil.getUuid());
        transInfo.setTransPath(Constant.SYSTEM_ID);
        
        BusinessEntityInfo beInfo = BusinessEntityInfo.Factory.newInstance();
        beInfo.setBusinessEntityNumber(Constant.BUSINESS_ENTITY_NUMBER);
        
        if (StringUtils.equals(paymentCategory, PaymentCategoryCode.POSTPAID.getCode())) {
        	paymentCategory = PaymentCategoryCode.POSTPAID.getCategory();
        	
        } else if (StringUtils.equals(paymentCategory, PaymentCategoryCode.PREPAID.getCode())) {
        	paymentCategory = PaymentCategoryCode.PREPAID.getCategory();
        }
        
        beInfo.setPaymentCategory(paymentCategory);
        
        AgreementResourceCriteria agreementResourceCriteria = AgreementResourceCriteria.Factory.newInstance();
        agreementResourceCriteria.setIsWithHistory(IS_WITH_HISTORY);
        agreementResourceCriteria.setResourceType(RESOURCE_TYPE_MSISDN);
        agreementResourceCriteria.setResourceValue(msisdn);
        
        QuerySimByMsisdnRequest request = QuerySimByMsisdnRequest.Factory.newInstance();
		request.setAuthInfo(authInfo);
		request.setTransInfo(transInfo);
		request.setBeInfo(beInfo);
		request.setAgreementResourceCriteria(agreementResourceCriteria);
        
        QuerySimByMsisdnRequestDocument requestDocument = QuerySimByMsisdnRequestDocument.Factory.newInstance();
		requestDocument.setQuerySimByMsisdnRequest(request);
        
        return requestDocument;
    }

}
