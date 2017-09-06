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
package com.fet.crm.osp.kernel.mware.client.workflow.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.common.util.IdentifierIdUtil;
import com.fet.crm.osp.common.util.JsonUtil;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.mware.client.AbstractBasicSOAPClient;
import com.fet.crm.osp.kernel.mware.client.code.CRMWFBizServiceReturnCode;
import com.fet.crm.osp.kernel.mware.client.pojo.ApproverInfoPOJO;
import com.fet.crm.osp.kernel.mware.client.pojo.AuthLevelInfoInputPOJO;
import com.fet.crm.osp.kernel.mware.client.pojo.AuthLevelInfoPOJO;
import com.fet.crm.osp.kernel.mware.client.pojo.GetAuthLevelRequestPOJO;
import com.fet.crm.osp.kernel.mware.client.pojo.ParamAuthInfoPOJO;
import com.fet.crm.osp.kernel.mware.client.pojo.ParamAuthLevelParameterPOJO;
import com.fet.crm.osp.kernel.mware.client.pojo.ParamTransInfoPOJO;
import com.fet.crm.osp.kernel.mware.client.pojo.ReturnHeaderPOJO;
import com.fet.crm.osp.kernel.mware.client.workflow.ICRMWFBizServiceClient;
import com.fet.crm.osp.kernel.mware.exception.ESBAPIException;
import com.fet.crm.osp.kernel.mware.exception.MwareExceptionFactory;
import com.fet.esb.crm.biz.services.sharedresources.xsd.crmwfservice_xsd.AuthLevelParameter;
import com.fet.esb.crm.biz.services.sharedresources.xsd.crmwfservice_xsd.TransactionInfo;

import tw.com.biz.ws.services.www.CRMWFBizServiceStub;
import tw.com.fet.biz.ws.services.www.schemas.crm_support.sharedresources.xsd.schema_xsd.ApproverInfo;
import tw.com.fet.biz.ws.services.www.schemas.crm_support.sharedresources.xsd.schema_xsd.GetAuthLevelRequestDocument;
import tw.com.fet.biz.ws.services.www.schemas.crm_support.sharedresources.xsd.schema_xsd.GetAuthLevelRequestDocument.GetAuthLevelRequest;
import tw.com.fet.biz.ws.services.www.schemas.crm_support.sharedresources.xsd.schema_xsd.GetAuthLevelResponseDocument;
import tw.com.fet.biz.ws.services.www.schemas.crm_support.sharedresources.xsd.schema_xsd.GetAuthLevelResponseDocument.GetAuthLevelResponse;
import tw.com.fet.www.itsoa.schemas.common_domain.sharedresources.xsd.base.schema_xsd2.AuthInfo;
import tw.com.fet.www.itsoa.schemas.common_domain.sharedresources.xsd.base.schema_xsd2.ReturnHeader;

/**
 * 查詢此流程要簽核的層級及要簽核的人員資訊服務 SOAP Client 實作類別
 * 
 * @author RichardHuang
 */
@Service
public class CRMWFBizServiceClientImpl extends AbstractBasicSOAPClient implements ICRMWFBizServiceClient {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public AuthLevelInfoPOJO getAuthLevelInfo(AuthLevelInfoInputPOJO inputParam) {
    	logger.info("getAuthLevelInfo [START]");
    	logger.info("params = " + JsonUtil.toJson(inputParam));
        
        String targetEndpoint = getSysConfValueByConfId(CRM_WF_BIZ_SERVICE_ENDPOINT_URL);
        
        logger.info("Create CRMWFBizService Stub [START]");
        logger.info("CRMWFBizService targetEndpoint = " + targetEndpoint);
        
        CRMWFBizServiceStub proxy = null;
        
		try {
			proxy = createServiceStub(targetEndpoint);
			logger.info("Create CRMWFBizService Stub [END]");
			
		} catch (AxisFault e) {
			logger.error("Create CRMWFBizService Stub [FAIL]", e.getCause());
			throw new MwareExceptionFactory().getException(e);
		}
        
        GetAuthLevelRequestDocument requestDocument = (GetAuthLevelRequestDocument) createRequest(inputParam);
        GetAuthLevelResponseDocument responseDocument = null;
        
        logger.info("Execute CRMWFBizService API - getAuthLevel [START]");
        logger.info("Request - GetAuthLevelRequest : " + printGetAuthLevelRequest(requestDocument.getGetAuthLevelRequest()));
        
		try {
			responseDocument = proxy.getAuthLevel(requestDocument);
			logger.info("Execute CRMWFBizService API - getAuthLevel [END]");
			
		} catch (RemoteException e) {
			logger.error("Execute CRMWFBizService API - getAuthLevel [FAIL]", e.getCause());
			throw new MwareExceptionFactory().getException(e);
		}
		
        GetAuthLevelResponse response = responseDocument.getGetAuthLevelResponse();
        
        ReturnHeader returnHeader = response.getReturnHeader();
        String returnCode = returnHeader.getReturnCode();
        String returnHeaderInfo = printReturnHeader(returnHeader);
        
        logger.info("Response - ReturnHeader : " + returnHeaderInfo);
        
        if (!StringUtils.equals(returnCode, CRMWFBizServiceReturnCode.SUCCESS.getCode())) {
        	logger.warn("Not Success- ReturnHeader : " + returnHeaderInfo);
        	throw new ESBAPIException(returnHeaderInfo);
        }
        
        AuthLevelInfoPOJO authLevelInfo = new AuthLevelInfoPOJO();
        
        String authLevel = response.getAuthLevel();
        authLevelInfo.setAuthLevel(authLevel);
        
        ApproverInfo[] approvers = response.getApproversArray();
        
        if (ArrayUtils.isNotEmpty(approvers)) {
            List<ApproverInfoPOJO> approverList = new ArrayList<>();
            
            for (ApproverInfo approverInfo : approvers) {
                String empId = approverInfo.getEmpId();
                String email = approverInfo.getEmail();
                String name = approverInfo.getName();
                String sms = approverInfo.getSms();
                String level = approverInfo.getLevel();
                String levelDesc = approverInfo.getLevelDesc();
                
                ApproverInfoPOJO approverInfoPOJO = new ApproverInfoPOJO();
                approverInfoPOJO.setEmpId(empId);
                approverInfoPOJO.setEmail(email);
                approverInfoPOJO.setName(name);
                approverInfoPOJO.setSms(sms);
                approverInfoPOJO.setLevel(level);
                approverInfoPOJO.setLevelDesc(levelDesc);
                
                approverList.add(approverInfoPOJO);
            }
            
            authLevelInfo.setApproverList(approverList);
        }
        
        logger.info("getAuthLevelInfo [END]");
        
        return authLevelInfo;
    }
    
    private CRMWFBizServiceStub createServiceStub(String targetEndpoint) throws AxisFault {
        return new CRMWFBizServiceStub(targetEndpoint);
    }
    
    private GetAuthLevelRequestDocument createRequest(AuthLevelInfoInputPOJO inputParam) {
    	String userId = inputParam.getUserId();
    	String salesId = inputParam.getSalesId();
    	
    	// authInfo
        AuthInfo paramAuthInfo = AuthInfo.Factory.newInstance();
        paramAuthInfo.setChannelID(Constant.SYSTEM_ID);
        paramAuthInfo.setPassword(getAPIPassword());
		paramAuthInfo.setUserID(userId);
        
        // transactionInfo
        TransactionInfo paramTransactionInfo = TransactionInfo.Factory.newInstance();
        paramTransactionInfo.setTransChannel(Constant.SYSTEM_ID);
        paramTransactionInfo.setTransUser(salesId);
        paramTransactionInfo.setTransBPID(IdentifierIdUtil.getUuid());
        paramTransactionInfo.setTransPath(Constant.SYSTEM_ID);
        
        // authLevelParameter
        AuthLevelParameter paramAuthLevelParameter = AuthLevelParameter.Factory.newInstance();
        paramAuthLevelParameter.setIsNew(IS_NEW);
        paramAuthLevelParameter.setSystemType(SYSTEM_TYPE);
        paramAuthLevelParameter.setFunctionId(FUNCTION_ID);
        paramAuthLevelParameter.setCheckPoint("");
        
        String rocId = inputParam.getRocId();
        String salesChannelType = inputParam.getSalesChannelType();
        String ivrCode = inputParam.getIvrCode();
        String postpaidWirelessId = inputParam.getPostpaidWirelessId();
        String subscriberId = inputParam.getSubscriberId();
        String identificationType = inputParam.getIdentificationType();
        String authLevel = inputParam.getAuthLevel();
        
        if (StringUtils.isNotBlank(rocId)) {
        	paramAuthLevelParameter.setRocid(rocId);
        }
        
        paramAuthLevelParameter.setSalesChannelType(salesChannelType);
        paramAuthLevelParameter.setIvrCode(ivrCode);
        
        if (StringUtils.isNotBlank(postpaidWirelessId)) {
        	paramAuthLevelParameter.setPostpaidWirelessId(postpaidWirelessId);
        }
        
        if (StringUtils.isNotBlank(subscriberId)) {
        	paramAuthLevelParameter.setSubscriberId(subscriberId);
        }
        
        paramAuthLevelParameter.setBizEntity(BIZ_ENTITY);
        
        if (StringUtils.isNotBlank(identificationType)) {
        	paramAuthLevelParameter.setIdentificationType(identificationType);
        }
        
        if (StringUtils.isNotBlank(authLevel)) {
        	paramAuthLevelParameter.setAuthLevel(Integer.valueOf(authLevel));
        }
        
        GetAuthLevelRequest paramGetAuthLevelRequest = GetAuthLevelRequest.Factory.newInstance();
        paramGetAuthLevelRequest.setAuthInfo(paramAuthInfo);
        paramGetAuthLevelRequest.setTransInfo(paramTransactionInfo);
        paramGetAuthLevelRequest.setFlowType(FLOW_TYPE);
        paramGetAuthLevelRequest.setAuthLevelParameter(paramAuthLevelParameter);
        
        GetAuthLevelRequestDocument requestDocument = GetAuthLevelRequestDocument.Factory.newInstance();
        requestDocument.setGetAuthLevelRequest(paramGetAuthLevelRequest);
        
        return requestDocument;
    }
    
    // ========= 工具程式 =========
    
    private String printReturnHeader(ReturnHeader returnHeader) {
    	String returnCode = returnHeader.getReturnCode();
    	String returnMesg = returnHeader.getReturnMesg();
    	String reserved1 = returnHeader.getReserved1();
    	
    	ReturnHeaderPOJO returnHeaderPOJO = new ReturnHeaderPOJO();
    	returnHeaderPOJO.setReturnCode(returnCode);
    	returnHeaderPOJO.setReturnMesg(returnMesg);
    	returnHeaderPOJO.setReserved1(reserved1);
    	
    	return JsonUtil.toJson(returnHeaderPOJO);
    }
    
    private String printGetAuthLevelRequest(GetAuthLevelRequest request) {
    	AuthInfo paramAuthInfo = request.getAuthInfo();
    	TransactionInfo paramTransactionInfo = request.getTransInfo();
    	String flowType = request.getFlowType();
    	AuthLevelParameter paramAuthLevelParameter = request.getAuthLevelParameter();
    	
    	ParamAuthInfoPOJO paramAuthInfoPOJO = new ParamAuthInfoPOJO();
    	paramAuthInfoPOJO.setChannelID(paramAuthInfo.getChannelID());
    	paramAuthInfoPOJO.setPassword(paramAuthInfo.getPassword());
    	paramAuthInfoPOJO.setUserID(paramAuthInfo.getUserID());
    	
    	ParamTransInfoPOJO paramTransInfoPOJO = new ParamTransInfoPOJO();
    	paramTransInfoPOJO.setTransChannel(paramTransactionInfo.getTransChannel());
    	paramTransInfoPOJO.setTransUser(paramTransactionInfo.getTransUser());
    	paramTransInfoPOJO.setTransBPID(paramTransactionInfo.getTransBPID());
    	paramTransInfoPOJO.setTransPath(paramTransactionInfo.getTransPath());
    	
    	ParamAuthLevelParameterPOJO paramAuthLevelParameterPOJO = new ParamAuthLevelParameterPOJO();
    	paramAuthLevelParameterPOJO.setIsNew(paramAuthLevelParameter.getIsNew());
    	paramAuthLevelParameterPOJO.setSystemType(paramAuthLevelParameter.getSystemType());
    	paramAuthLevelParameterPOJO.setFunctionId(paramAuthLevelParameter.getFunctionId());
    	paramAuthLevelParameterPOJO.setCheckPoint(paramAuthLevelParameter.getCheckPoint());
    	paramAuthLevelParameterPOJO.setRocId(paramAuthLevelParameter.getRocid());
    	paramAuthLevelParameterPOJO.setSalesChannelType(paramAuthLevelParameter.getSalesChannelType());
    	paramAuthLevelParameterPOJO.setIvrCode(paramAuthLevelParameter.getIvrCode());
    	paramAuthLevelParameterPOJO.setPostpaidWirelessId(paramAuthLevelParameter.getPostpaidWirelessId());
    	paramAuthLevelParameterPOJO.setSubscriberId(paramAuthLevelParameter.getSubscriberId());
    	paramAuthLevelParameterPOJO.setBizEntity(paramAuthLevelParameter.getBizEntity());
    	paramAuthLevelParameterPOJO.setIdentificationType(paramAuthLevelParameter.getIdentificationType());
    	paramAuthLevelParameterPOJO.setAuthLevel(paramAuthLevelParameter.getAuthLevel());
    	
    	GetAuthLevelRequestPOJO getAuthLevelRequestPOJO = new GetAuthLevelRequestPOJO();
    	getAuthLevelRequestPOJO.setParamAuthInfoPOJO(paramAuthInfoPOJO);
    	getAuthLevelRequestPOJO.setParamTransInfoPOJO(paramTransInfoPOJO);
    	getAuthLevelRequestPOJO.setFlowType(flowType);
    	getAuthLevelRequestPOJO.setParamAuthLevelParameterPOJO(paramAuthLevelParameterPOJO);
    	
    	return JsonUtil.toJson(getAuthLevelRequestPOJO);
    }
    
}
