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
package com.fet.crm.osp.kernel.mware.client.crmcust.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetIdViewInfoRequestDocument;
import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetIdViewInfoRequestType;
import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetIdViewInfoResponseDocument;
import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetIdViewInfoResponseType;
import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetRecordBySubscrIdRequestDocument;
import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetRecordBySubscrIdRequestDocument.GetRecordBySubscrIdRequest;
import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetRecordBySubscrIdResponseDocument;
import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetRecordBySubscrIdResponseDocument.GetRecordBySubscrIdResponse;
import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetSpecialOfferByRocIdRequestDocument;
import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetSpecialOfferByRocIdRequestDocument.GetSpecialOfferByRocIdRequest;
import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetSpecialOfferByRocIdResponseDocument;
import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetSpecialOfferByRocIdResponseDocument.GetSpecialOfferByRocIdResponse;
import com.fet.crm.osp.common.util.DateUtil;
import com.fet.crm.osp.common.util.IdentifierIdUtil;
import com.fet.crm.osp.common.vo.kernel.result.SCVAccountInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.SpecialOfferRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.idviewinfo.IdViewInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.idviewinfo.IdViewInfoVO;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.mware.client.AbstractBasicSOAPClient;
import com.fet.crm.osp.kernel.mware.client.code.CRMCustBizServiceReturnCode;
import com.fet.crm.osp.kernel.mware.client.crmcust.ICRMCustBizServiceClient;
import com.fet.crm.osp.kernel.mware.exception.ESBAPIException;
import com.fet.crm.osp.kernel.mware.exception.MwareExceptionFactory;
import com.fet.crm.services.xsd.crmschema.IdViewInfoParameterType;
import com.fet.crm.services.xsd.crmschema.IdViewRecord;
import com.fet.crm.services.xsd.crmschema.InsightQueryParam;
import com.fet.crm.services.xsd.crmschema.InsightQueryParamTypeDocument.InsightQueryParamType;
import com.fet.crm.services.xsd.crmschema.SCVAccountInfoVO;
import com.fet.crm.services.xsd.crmschema.SCVSpecialWarmOfferVO;
import com.fet.esb.crm.biz.services.crmcustbizservice.CRMCustBizServiceStub;
import com.fet.esb.crm.biz.services.crmcustbizservice.ProcessingFault;

import tw.com.fet.services.www.common.schema.AuthInfo;
import tw.com.fet.services.www.common.schema.ReturnHeader;
import tw.com.fet.services.www.common.schema.TransactionInfo;

/**
 * CRMCustBizService SOAP Client 實作
 * 
 * @author RichardHuang
 */
@Service
public class CRMCustBizServiceClientImpl extends AbstractBasicSOAPClient implements ICRMCustBizServiceClient {
    
    @Override
    public List<SpecialOfferRtnVO> getSpecialOfferByRocId(String rocId, String userId) {
        System.out.println("CRMCustBizServiceClientImpl.getSpecialOfferByRocId : Input Param [rocId = " + rocId + "]");

        String targetEndpoint = getSysConfValueByConfId(CRM_CUST_BIZ_SERVICE_ENDPOINT_URL);
        CRMCustBizServiceStub stub = null;
        
		try {
			stub = createServiceStub(targetEndpoint);
			
		} catch (AxisFault e) {
			throw new MwareExceptionFactory().getException(e);
		}
		
		GetSpecialOfferByRocIdRequestDocument requestDocument = createGetSpecialOfferByRocIdRequest(rocId, userId);
        GetSpecialOfferByRocIdResponseDocument responseDocument = null;
        
		try {
			responseDocument = stub.getSpecialOfferByRocId(requestDocument);
			
		} catch (RemoteException e) {
			throw new MwareExceptionFactory().getException(e);
			
		} catch (ProcessingFault e) {
			throw new MwareExceptionFactory().getException(e);
		}
        
        GetSpecialOfferByRocIdResponse response = responseDocument.getGetSpecialOfferByRocIdResponse();
        ReturnHeader returnHeader = response.getReturnHeader();
        String returnCode = returnHeader.getReturnCode();
        String returnMesg = returnHeader.getReturnMesg();
        
        System.out.println("CRMCustBizServiceClientImpl.getSpecialOfferByRocId : returnCode = " + returnCode + ", returnMesg = " + returnMesg);
        
        Assert.state(!StringUtils.equals(returnCode, CRMCustBizServiceReturnCode.NO_RECORD_FOUND.getCode()));
        
        if (!StringUtils.equals(returnCode, CRMCustBizServiceReturnCode.SUCCESS.getCode())) {
            throw new ESBAPIException("["+returnCode+"] " + returnMesg);
        }
        
        SCVSpecialWarmOfferVO[] specialWarmOfferArray = response.getResultArray();
        
        Assert.state(ArrayUtils.isNotEmpty(specialWarmOfferArray));
        
        List<SpecialOfferRtnVO> result = new ArrayList<>();
        
        for (SCVSpecialWarmOfferVO scvSpecialWarmOffer : specialWarmOfferArray) {
            SpecialOfferRtnVO rtnVO = new SpecialOfferRtnVO();
            
            String offerId = scvSpecialWarmOffer.getOfferId();
            String offerName = scvSpecialWarmOffer.getOfferName();
            String msisdn = scvSpecialWarmOffer.getMsisdn();
            Calendar startDate = scvSpecialWarmOffer.getStartDate();
            Calendar endDate = scvSpecialWarmOffer.getEndDate();
            
            rtnVO.setOfferId(offerId);
            rtnVO.setOfferName(offerName);
            rtnVO.setMsisdn(msisdn);
            
            if (startDate != null) {
                rtnVO.setStartDate(DateUtil.formatDt(startDate.getTime(), DateUtil.DATE_TIME_PATTERN));
            }
            
            if (endDate != null) {
                rtnVO.setEndDate(DateUtil.formatDt(endDate.getTime(), DateUtil.DATE_TIME_PATTERN));
            }
            
            result.add(rtnVO);
        }
        
        return result;
    }
    
    public List<SCVAccountInfoRtnVO> getRecordBySubscrId(String subscriberId, String userId) {
        System.out.println("CRMCustBizServiceClientImpl.getRecordBySubscrId : Input Param [subscrId = " + subscriberId + "]");

        String targetEndpoint = getSysConfValueByConfId(CRM_CUST_BIZ_SERVICE_ENDPOINT_URL);
        CRMCustBizServiceStub stub = null;
        
		try {
			stub = createServiceStub(targetEndpoint);
			
		} catch (AxisFault e) {
			throw new MwareExceptionFactory().getException(e);
		}
		
        GetRecordBySubscrIdRequestDocument requestDocument = createGetRecordBySubscrIdRequest(subscriberId, userId);
        GetRecordBySubscrIdResponseDocument responseDocument = null;
        
		try {
			responseDocument = stub.getRecordBySubscrId(requestDocument);
			
		} catch (RemoteException e) {
			throw new MwareExceptionFactory().getException(e);
		}
        
        GetRecordBySubscrIdResponse response = responseDocument.getGetRecordBySubscrIdResponse();
        ReturnHeader returnHeader = response.getReturnHeader();
        String returnCode = returnHeader.getReturnCode();
        String returnMesg = returnHeader.getReturnMesg();
        
        System.out.println("CRMCustBizServiceClientImpl.getRecordBySubscrId : returnCode = " + returnCode + ", returnMesg = " + returnMesg);
        
        Assert.state(!StringUtils.equals(returnCode, CRMCustBizServiceReturnCode.NO_RECORD_FOUND.getCode()));
        
        if (!StringUtils.equals(returnCode, CRMCustBizServiceReturnCode.SUCCESS.getCode())) {
            throw new ESBAPIException("["+returnCode+"] " + returnMesg);
        }
        
        SCVAccountInfoVO[] scvAccountInfoArray = response.getResultArray();
        
        Assert.state(ArrayUtils.isNotEmpty(scvAccountInfoArray));
        
        List<SCVAccountInfoRtnVO> result = new ArrayList<>();
        
        for (SCVAccountInfoVO scvAccountInfo : scvAccountInfoArray) {
        	SCVAccountInfoRtnVO rtnVO = new SCVAccountInfoRtnVO();
            
            rtnVO.setIsHybrid(scvAccountInfo.getIsHybrid());
            rtnVO.setIsGSMBaseStation(scvAccountInfo.getIsGSMBaseStation());
            rtnVO.setIsCorp(scvAccountInfo.getIsCorp());
            rtnVO.setIsDataCard(scvAccountInfo.getIsDataCard());
            rtnVO.setIsVDUser(scvAccountInfo.getIsVDUser());
            rtnVO.setSrvProdType(scvAccountInfo.getSrvProdType());
            rtnVO.setSrvProdName(scvAccountInfo.getSrvProdName());
            rtnVO.setDsParentInd(scvAccountInfo.getDsParentInd());
            rtnVO.setDsOuid(scvAccountInfo.getDsOuid());
            rtnVO.setNfcInx(scvAccountInfo.getNfcInx());
            rtnVO.setBlDt(scvAccountInfo.getBlDt());
            rtnVO.setAutopayInd(scvAccountInfo.getAutopayInd());
            rtnVO.setHandsetContrInx(scvAccountInfo.getHandsetContrInx());
            rtnVO.setWbbInx(scvAccountInfo.getWbbInx());
            rtnVO.setMvpnInx(scvAccountInfo.getMvpnInx());
            
            Calendar firstContrDt = scvAccountInfo.getFirstContrDt();
            if (firstContrDt != null) {
            	rtnVO.setFirstContrDt(firstContrDt.getTime());
            }
            
            Calendar contrExpDt = scvAccountInfo.getContrExpDt();
            if (contrExpDt != null) {
            	rtnVO.setContrExpDt(contrExpDt.getTime());
            }
            
            rtnVO.setL6mAvgBillAmtPenalty(scvAccountInfo.getL6MAvgBillAmtPenalty());
            rtnVO.setScappInd(scvAccountInfo.getScappInd());
            rtnVO.setDepositAmt(scvAccountInfo.getDepositAmt());
            rtnVO.setSrvTypeCode(scvAccountInfo.getSrvTypeCode());
            rtnVO.setSrvProName(scvAccountInfo.getSrvProdName());
            rtnVO.setPartyIdNum(scvAccountInfo.getPartyIdNum());
            rtnVO.setAcctId(scvAccountInfo.getAcctId());
            rtnVO.setBusiEntityId(scvAccountInfo.getBusiEntityId());
            
            Calendar actvDt = scvAccountInfo.getActvDt();
            if (actvDt != null) {
            	rtnVO.setActvDt(actvDt.getTime());
            }
            
            rtnVO.setIsD2(scvAccountInfo.getIsD2());
            rtnVO.setIsD3(scvAccountInfo.getIsD3());
            
            result.add(rtnVO);
        }
        
        return result;
    }
    
    @Override
	public IdViewInfoRtnVO getIdViewInfoByRocId(String rocId, String userId) {
        String targetEndpoint = getSysConfValueByConfId(CRM_CUST_BIZ_SERVICE_ENDPOINT_URL);
        CRMCustBizServiceStub stub = null;
        
		try {
			stub = createServiceStub(targetEndpoint);
			
		} catch (AxisFault e) {
			throw new MwareExceptionFactory().getException(e);
		}
		
		GetIdViewInfoRequestDocument requestDocument = createGetIdViewInfoByRocIdRequest(rocId, userId);
		GetIdViewInfoResponseDocument responseDocument = null;
		
		try {
			responseDocument = stub.getIdViewInfo(requestDocument);
			
		} catch (RemoteException e) {
			throw new MwareExceptionFactory().getException(e);
			
		} catch (ProcessingFault e) {
			throw new MwareExceptionFactory().getException(e);
		}
		
		GetIdViewInfoResponseType response = responseDocument.getGetIdViewInfoResponse();
		
		ReturnHeader returnHeader = response.getReturnHeader();
		String returnCode = returnHeader.getReturnCode();
		String returnMesg = returnHeader.getReturnMesg();
		
		System.out.println("CRMCustBizService getIdViewInfo API : returnCode = " + returnCode + ", returnMesg = " + returnMesg);
        
        Assert.state(!StringUtils.equals(returnCode, CRMCustBizServiceReturnCode.NO_RECORD_FOUND.getCode()));
        
        if (!StringUtils.equals(returnCode, CRMCustBizServiceReturnCode.SUCCESS.getCode())) {
            throw new ESBAPIException("["+returnCode+"] " + returnMesg);
        }
        
        int totalCount = response.getTotalCount();
        
        IdViewInfoRtnVO idViewInfoRtnVO = new IdViewInfoRtnVO();
        idViewInfoRtnVO.setTotalCount(totalCount);
        
        IdViewRecord[] idViewRecordsArray = response.getIdViewRecordsArray();
        
        if (ArrayUtils.isNotEmpty(idViewRecordsArray)) {
        	List<IdViewInfoVO> idViewInfoList = new ArrayList<>();
        	
        	for (IdViewRecord idViewRecord : idViewRecordsArray) {
				if (idViewRecord != null) {
					String msisdn = idViewRecord.getMsisdn();
					String accountId = idViewRecord.getAccountId();
					String statusDesc = idViewRecord.getStatusDesc();
					Calendar lastSubStDt = idViewRecord.getLastSubStDt();
					String subStReasonDscr = idViewRecord.getSubStReasonDscr();
					Calendar initActiveDt = idViewRecord.getInitActiveDt();
					String lastName = idViewRecord.getLastName();
					String subscriberType = idViewRecord.getSubscriberType();
					String paymentCategory = idViewRecord.getPaymentCategory();
					BigDecimal arpb = idViewRecord.getArpb();
					
					IdViewInfoVO idViewInfoVO = new IdViewInfoVO();
					idViewInfoVO.setMsisdn(msisdn);
					idViewInfoVO.setAccountId(accountId);
					idViewInfoVO.setStatusDesc(statusDesc);
					idViewInfoVO.setLastName(lastName);
					
					if (lastSubStDt != null) {
						idViewInfoVO.setLastSubStDt(lastSubStDt.getTime());
					}
					
					idViewInfoVO.setSubStReasonDscr(subStReasonDscr);
					
					if (initActiveDt != null) {
						idViewInfoVO.setInitActiveDt(initActiveDt.getTime());
					}
					
					String productType = StringUtils.join(subscriberType, paymentCategory);
					String productTypeDesc = getProductTypeDesc(productType);
					
					idViewInfoVO.setProductTypeDesc(productTypeDesc);
					idViewInfoVO.setArpb(arpb);
					
					idViewInfoList.add(idViewInfoVO);
				}
				
				idViewInfoRtnVO.setIdViewInfoList(idViewInfoList);
			}
        }
		
		return idViewInfoRtnVO;
	}
    
    private String getProductTypeDesc(String productType) {
    	Map<String, String> productTypeMap = new HashMap<>();
    	productTypeMap.put("PPS", "企業客戶");
    	productTypeMap.put("EPS", "企業簡訊");
    	productTypeMap.put("WPS", "全虹3G月租");
    	productTypeMap.put("VPS", "和信2G月租");
    	productTypeMap.put("VPP", "和信2G預付");
    	productTypeMap.put("KPP", "統一2G預付");
    	productTypeMap.put("QPS", "統一3G月租");
    	productTypeMap.put("QPP", "統一3G預付");
    	productTypeMap.put("UPP", "統一4G預付");
    	productTypeMap.put("GPS", "遠傳2G月租");
    	productTypeMap.put("GPP", "遠傳2G預付");
    	productTypeMap.put("HPS", "遠傳3G安心講");
    	productTypeMap.put("TPS", "遠傳3G月租");
    	productTypeMap.put("TPP", "遠傳3G預付");
    	productTypeMap.put("LPS", "遠傳4G月租");
    	productTypeMap.put("LPP", "遠傳4G預付");
    	productTypeMap.put("XPS", "非遠傳用戶"); 
    	
    	return productTypeMap.get(productType);
    }
    
    private GetIdViewInfoRequestDocument createGetIdViewInfoByRocIdRequest(String rocId, String userId) {
    	String password = getAPIPassword();
    	
    	AuthInfo authInfo = AuthInfo.Factory.newInstance();
    	authInfo.setChannelID(Constant.SYSTEM_ID);
		authInfo.setUserID(userId);
		authInfo.setPassword(password);
		
		TransactionInfo transInfo = TransactionInfo.Factory.newInstance();
		transInfo.setTransChannelId(Constant.SYSTEM_ID);
		transInfo.setTransUserId(userId);
		transInfo.setTransBpId(Constant.SYSTEM_ID);
		transInfo.setTransPath(Constant.SYSTEM_ID);
		
		IdViewInfoParameterType idViewInfoParameter = IdViewInfoParameterType.Factory.newInstance();
		idViewInfoParameter.setQueryType(QUERY_TYPE);
		idViewInfoParameter.setSearchKey(rocId);
		idViewInfoParameter.setCountFlag(COUNT_FLAG);
    	
    	GetIdViewInfoRequestType request = GetIdViewInfoRequestType.Factory.newInstance();
		request.setAuthInfo(authInfo);
		request.setTransInfo(transInfo);
		request.setIdViewInfoParameter(idViewInfoParameter);
    	
    	GetIdViewInfoRequestDocument requestDocument = GetIdViewInfoRequestDocument.Factory.newInstance();
		requestDocument.setGetIdViewInfoRequest(request);
    	
    	return requestDocument;
    }
    
    private GetSpecialOfferByRocIdRequestDocument createGetSpecialOfferByRocIdRequest(String rocId, String userId) {
        // authInfo
        AuthInfo authInfo = AuthInfo.Factory.newInstance();
        authInfo.setChannelID(Constant.SYSTEM_ID);
        authInfo.setUserID(userId);
        authInfo.setPassword(getAPIPassword());
        
        // transactionInfo
        TransactionInfo transactionInfo = TransactionInfo.Factory.newInstance();
        transactionInfo.setTransChannelId(Constant.SYSTEM_ID);
        transactionInfo.setTransUserId(userId);
        transactionInfo.setTransBpId(IdentifierIdUtil.getUuid());
        transactionInfo.setTransPath(Constant.SYSTEM_ID);
        
        // insightQueryParam
        InsightQueryParam insightQueryParam = InsightQueryParam.Factory.newInstance();
        insightQueryParam.setRocid(rocId);
        
        GetSpecialOfferByRocIdRequest request = GetSpecialOfferByRocIdRequest.Factory.newInstance();
        request.setAuthInfo(authInfo);
        request.setTransactionInfo(transactionInfo);
        request.setInsightQueryParam(insightQueryParam);
        
        GetSpecialOfferByRocIdRequestDocument requestDocument = GetSpecialOfferByRocIdRequestDocument.Factory.newInstance();
        requestDocument.setGetSpecialOfferByRocIdRequest(request);
        
        return requestDocument;
    }
    
    private GetRecordBySubscrIdRequestDocument createGetRecordBySubscrIdRequest(String subscriberId, String userId) {
    	// authInfo
        AuthInfo authInfo = AuthInfo.Factory.newInstance();
        authInfo.setChannelID(Constant.SYSTEM_ID);
        authInfo.setUserID(userId);
        authInfo.setPassword(getAPIPassword());
        
        // transactionInfo
        TransactionInfo transactionInfo = TransactionInfo.Factory.newInstance();
        transactionInfo.setTransChannelId(Constant.SYSTEM_ID);
        transactionInfo.setTransUserId(userId);
        transactionInfo.setTransBpId(IdentifierIdUtil.getUuid());
        transactionInfo.setTransPath(Constant.SYSTEM_ID);
        
        InsightQueryParam paramInsightQueryParam = InsightQueryParam.Factory.newInstance();
        paramInsightQueryParam.setSubscriberId(subscriberId);
        
        InsightQueryParamType insightQueryParamType = InsightQueryParamType.Factory.newInstance();
		insightQueryParamType.setInsightQueryParam(paramInsightQueryParam);
        
        GetRecordBySubscrIdRequest request = GetRecordBySubscrIdRequest.Factory.newInstance();
        request.setAuthInfo(authInfo);
        request.setTransactionInfo(transactionInfo);
		request.setInsightQueryParamType(insightQueryParamType);
        
        GetRecordBySubscrIdRequestDocument requestDocument = GetRecordBySubscrIdRequestDocument.Factory.newInstance();
        requestDocument.setGetRecordBySubscrIdRequest(request);
        
        return requestDocument;
    }

    private CRMCustBizServiceStub createServiceStub(String targetEndpoint) throws AxisFault {
        return new CRMCustBizServiceStub(targetEndpoint);
    }

}
