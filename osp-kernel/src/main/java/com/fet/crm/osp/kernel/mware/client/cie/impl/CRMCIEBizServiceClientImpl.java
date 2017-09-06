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

package com.fet.crm.osp.kernel.mware.client.cie.impl;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.common.util.IdentifierIdUtil;
import com.fet.crm.osp.common.vo.kernel.input.param.AddCIEMasterParamVO;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.mware.client.AbstractBasicSOAPClient;
import com.fet.crm.osp.kernel.mware.client.cie.ICRMCIEBizServiceClient;
import com.fet.crm.osp.kernel.mware.client.code.CRMCIEBizServiceReturnCode;
import com.fet.crm.osp.kernel.mware.exception.ESBAPIException;
import com.fet.crm.osp.kernel.mware.exception.MwareExceptionFactory;

import tw.com.biz.cie.services.www.CRMCIEBizServiceStub;
import tw.com.fet.biz.cie.services.www.schemas.crm_readiness.sharedresources.xsd.schema_xsd.AddCieMasterRequest;
import tw.com.fet.biz.cie.services.www.schemas.crm_readiness.sharedresources.xsd.schema_xsd.AddCieMasterRequestDocument;
import tw.com.fet.biz.cie.services.www.schemas.crm_readiness.sharedresources.xsd.schema_xsd.AddCieMasterResponse;
import tw.com.fet.biz.cie.services.www.schemas.crm_readiness.sharedresources.xsd.schema_xsd.AddCieMasterResponseDocument;
import tw.com.fet.biz.cie.services.www.schemas.crm_readiness.sharedresources.xsd.schema_xsd.TransactionInfo;
import tw.com.fet.cie.services.www.bobj.schema.CieDetailBObj;
import tw.com.fet.cie.services.www.bobj.schema.CieMasterBObj;
import tw.com.fet.www.itsoa.schemas.common_domain.sharedresources.xsd.base.schema_xsd2.AuthInfo;
import tw.com.fet.www.itsoa.schemas.common_domain.sharedresources.xsd.base.schema_xsd2.ReturnHeader;

/**
 * CRMCIEBizService 服務 SOAP Client 實作類別
 * 
 * @author RichardHuang
 */
@Service
public class CRMCIEBizServiceClientImpl extends AbstractBasicSOAPClient implements ICRMCIEBizServiceClient {

    @Override
    public CieMasterBObj addCIEMaster(AddCIEMasterParamVO requestParam) {
        System.out.println("CRMCIEBizServiceClientImpl.addCIEMaster : Input Param [" + requestParam + "]");
        
        String targetEndpoint = getSysConfValueByConfId(CRM_CIE_BIZ_SERVICE_ENDPOINT_URL);
        AddCieMasterRequestDocument requestDocument = createRequest(requestParam);
        CRMCIEBizServiceStub proxy = null;
        
		try {
			proxy = createServiceStub(targetEndpoint);
			
		} catch (AxisFault e) {
			throw new MwareExceptionFactory().getException(e);
		}
		
        AddCieMasterResponseDocument responseDocument = null;
        
		try {
			responseDocument = proxy.addCIEMaster(requestDocument);
			
		} catch (RemoteException e) {
			throw new MwareExceptionFactory().getException(e);
		}
		
        AddCieMasterResponse response = responseDocument.getAddCieMasterResponse();
        
        ReturnHeader returnHeader = response.getReturnHeader();
        String returnCode = returnHeader.getReturnCode();
        String returnMesg = returnHeader.getReturnMesg();
        
        System.out.println("CRMCIEBizServiceClientImpl.addCIEMaster : returnCode = " + returnCode + ", returnMesg = " + returnMesg);
        
        if (!StringUtils.equals(returnCode, CRMCIEBizServiceReturnCode.SUCCESS.getCode())) {
            throw new ESBAPIException("["+returnCode+"] " + returnMesg);
        }
        
        return response.getMasterBObj();
    }
    
    private CRMCIEBizServiceStub createServiceStub(String targetEndpoint) throws AxisFault {
        return new CRMCIEBizServiceStub(targetEndpoint);
    }
    
    private AddCieMasterRequestDocument createRequest(AddCIEMasterParamVO requestParam) {
        // authInfo
        AuthInfo authInfo = AuthInfo.Factory.newInstance();
        authInfo.setChannelID(Constant.SYSTEM_ID);
        authInfo.setPassword(getAPIPassword());
        authInfo.setUserID(requestParam.getUserId());
        
        // transInfo
        TransactionInfo transInfo = TransactionInfo.Factory.newInstance();
        transInfo.setTransChannelId(Constant.SYSTEM_ID);
        transInfo.setTransUserId(requestParam.getUserId());
        transInfo.setTransBpId(IdentifierIdUtil.getUuid());
        transInfo.setTransPath(Constant.SYSTEM_ID);
        
        // masterBObj
        String potentialContactIndicator = "N"; // 潛客註記(Y:潛客 / N:既有客戶)
        String inOutIndicator = "I"; // I:Inbound / O:Outbound 註記
        BigInteger channelType = new BigInteger("99"); // 接觸管道代碼(其他)
        BigInteger fetContactMethodType = new BigInteger("99"); // 接觸媒體代碼(其他)
        Calendar sysDate = Calendar.getInstance();
        sysDate.setTime(new Date());
        
        // detailBObj
        BigInteger accountContactId = new BigInteger(requestParam.getPartyId());
        BigInteger subscriberContactId = new BigInteger(requestParam.getPartyId());
        BigInteger accountContractCompId = new BigInteger(requestParam.getContractComponentId());
        BigInteger subscriberContractCompId = new BigInteger(requestParam.getContractComponentId());
        BigInteger billingAccountId = new BigInteger(requestParam.getAccountId());
        BigInteger billingSubscriberId = new BigInteger(requestParam.getSubscriberId());
        
        CieDetailBObj detailBObj = CieDetailBObj.Factory.newInstance();
        detailBObj.setAccountContactId(accountContactId);
        detailBObj.setSubscriberContactId(subscriberContactId);
        detailBObj.setAccountContractCompId(accountContractCompId);
        detailBObj.setSubscriberContractCompId(subscriberContractCompId);
        detailBObj.setBillingAccountId(billingAccountId);
        detailBObj.setBillingSubscriberId(billingSubscriberId);
        detailBObj.setResourceValue(requestParam.getMsisdn());
        detailBObj.setServiceProvider(requestParam.getServiceProvider());
        detailBObj.setMobileGenerationCode(requestParam.getGenerationCode());
        detailBObj.setPaymentCategory(requestParam.getPaymentCategory());
        detailBObj.setCreatedDate(sysDate);
        
        CieDetailBObj[] paramArrayOfCieDetailBObj = new CieDetailBObj[] { detailBObj };
        
        CieMasterBObj masterBObj = CieMasterBObj.Factory.newInstance();
        masterBObj.setPotentialContactIndicator(potentialContactIndicator);
        masterBObj.setInOutIndicator(inOutIndicator);
        masterBObj.setChannelType(channelType);
        masterBObj.setFetContactMethodType(fetContactMethodType);
        masterBObj.setCreatedAgent(requestParam.getEmpNo());
        masterBObj.setCreatedSystem(Constant.SYSTEM_ID);
        masterBObj.setStartDate(sysDate);
        masterBObj.setEndDate(sysDate);
        masterBObj.setCreateDate(sysDate);
        masterBObj.setDetailBObjArray(paramArrayOfCieDetailBObj);
        
        AddCieMasterRequest request = AddCieMasterRequest.Factory.newInstance();
        request.setAuthInfo(authInfo);
        request.setTransInfo(transInfo);
        request.setMasterBObj(masterBObj);
        
        AddCieMasterRequestDocument requestDocument = AddCieMasterRequestDocument.Factory.newInstance();
        requestDocument.setAddCieMasterRequest(request);
        
        return requestDocument;
    }

}
