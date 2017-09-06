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

package com.fet.crm.osp.kernel.mware.client.billling.impl;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fet.crm.osp.common.util.IdentifierIdUtil;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustBillingInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.InvoiceInfoVO;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.core.common.util.CipherUtil;
import com.fet.crm.osp.kernel.mware.client.AbstractBasicSOAPClient;
import com.fet.crm.osp.kernel.mware.client.billling.IDecisionDataServiceClient;
import com.fet.crm.osp.kernel.mware.client.code.DecisionDataServiceReturnCode;
import com.fet.crm.osp.kernel.mware.exception.ESBAPIException;
import com.fet.crm.osp.kernel.mware.exception.MwareExceptionFactory;
import com.fet.esb.crm.biz.services.decisiondataservice.DecisionDataServiceStub;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.querycustomerbillinginfo_xsd.BillingInfo;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.querycustomerbillinginfo_xsd.ContractAndBillingInfo;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.querycustomerbillinginfo_xsd.ContractInfo;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.querycustomerbillinginfo_xsd.InvoiceListByMonthInfo;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.querycustomerbillinginfo_xsd.InvoiceListByMonthList;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.querycustomerbillinginfo_xsd.InvoiceListBySubBEInfo;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.querycustomerbillinginfo_xsd.InvoiceListBySubBEList;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.querycustomerbillinginfo_xsd.QueryCustomerBillingInfoRequestDocument;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.querycustomerbillinginfo_xsd.QueryCustomerBillingInfoRequestDocument.QueryCustomerBillingInfoRequest;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.querycustomerbillinginfo_xsd.QueryCustomerBillingInfoResponseDocument;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.querycustomerbillinginfo_xsd.QueryCustomerBillingInfoResponseDocument.QueryCustomerBillingInfoResponse;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.querycustomerbillinginfo_xsd.QueryInfo;

import adam.esb.fet.com.xsd.AuthInfo;
import adam.esb.fet.com.xsd.RequestHeader;
import adam.esb.fet.com.xsd.ReturnHeader;
import adam.esb.fet.com.xsd.TxInfo;

/**
 * 核資服務 SOAP Client 實作類別
 * 
 * @author RichardHuang
 */
@Service
public class DecisionDataServiceClientImpl extends AbstractBasicSOAPClient implements IDecisionDataServiceClient {

    @Override
    public CustBillingInfoRtnVO queryCustomerBillingInfo(String clientIP, String accountId, String subscriberId) {
        System.out.println("DecisionDataServiceClientImpl.getContractBySubscriberId : Input Param [clientIP = " + 
        		clientIP + ", accountId = " + accountId + ", subscriberId = " + subscriberId + "]");
        
        String encrptKey = getSysConfValueByConfId(DECISION_DATA_SERVICE_ENCRYPT_KEY);
        
        DateFormat sdf = SimpleDateFormat.getDateInstance();
        String dateStr = sdf.format(new Date());
        String token = CipherUtil.encrypt(dateStr, encrptKey);
        
        String targetEndpoint = getSysConfValueByConfId(DECISION_DATA_SERVICE_ENDPOINT_URL);
        
        DecisionDataServiceStub proxy = null;
        
        try {
            proxy = createServiceStub(targetEndpoint);
            
        } catch (AxisFault e) {
            throw new MwareExceptionFactory().getException(e);
        }
        
        QueryInfo queryInfo = QueryInfo.Factory.newInstance();
        queryInfo.setAccountID(accountId);
        queryInfo.setSubscriberID(subscriberId);
        queryInfo.setBusinessEntityNumber(String.valueOf(Constant.BUSINESS_ENTITY_NUMBER));
        queryInfo.setCycleCount(CYCLE_COUNT);
        queryInfo.setSubBE(SUB_BE);
        queryInfo.setToken(token);
        queryInfo.setClientIP(clientIP);
        
        QueryCustomerBillingInfoRequestDocument request = 
        		(QueryCustomerBillingInfoRequestDocument) createRequest(queryInfo);
        
        QueryCustomerBillingInfoResponseDocument responseDocument = null;
        
        try {
            responseDocument = proxy.queryCustomerBillingInfo(request);
            
        } catch (RemoteException e) {
            throw new MwareExceptionFactory().getException(e);
        }
        
        QueryCustomerBillingInfoResponse response = responseDocument.getQueryCustomerBillingInfoResponse();
        
        ReturnHeader returnHeader = response.getReturnHeader();
        String returnCode = returnHeader.getReturnCode();
        String returnMesg = returnHeader.getReturnMesg();
        
        System.out.println("DecisionDataServiceClientImpl.queryCustomerBillingInfo : returnCode = " + returnCode + ", returnMesg = " + returnMesg);
        
        if (!StringUtils.equals(returnCode, DecisionDataServiceReturnCode.SUCCESS.getCode()) 
        		&& !StringUtils.equals(returnCode, DecisionDataServiceReturnCode.SUCCESS_WITH_WARNING.getCode())) {
            throw new ESBAPIException("["+returnCode+"] " + returnMesg);
        }
        
        ContractAndBillingInfo contractAndBillingInfo = response.getContractAndBillingInfo();
        
        Assert.state(contractAndBillingInfo != null);
        
        CustBillingInfoRtnVO result = new CustBillingInfoRtnVO();

        ContractInfo contractInfo = contractAndBillingInfo.getContractInfo();
        
        if (contractInfo != null) {
            String arpb = contractInfo.getARPB();
            String customerScore = contractInfo.getCustomerScore();
            
            result.setArpb(arpb);
            result.setCustomerScore(customerScore);
        }
        
        BillingInfo billingInfo = contractAndBillingInfo.getBillingInfo();
        
        if (billingInfo != null) {
            String cycleDate = billingInfo.getCycleDate();
            String paymentMethod = billingInfo.getPaymentMethod();
            
            result.setCycleDate(cycleDate);
            result.setPaymentMethod(paymentMethod);
        }

        InvoiceListByMonthList invoiceListByMonthList = billingInfo.getInvoiceListByMonthList();
        
        if (invoiceListByMonthList != null) {
            InvoiceListByMonthInfo[] invoiceListByMonthArray = invoiceListByMonthList.getInvoiceListByMonthArray();
            
            if (ArrayUtils.isNotEmpty(invoiceListByMonthArray)) {
                List<InvoiceInfoVO> invoiceInfoList = new ArrayList<InvoiceInfoVO>();
                
                for (InvoiceListByMonthInfo invoiceListByMonthInfo : invoiceListByMonthArray) {
                    if (invoiceListByMonthInfo == null) {
                        continue;
                    }
                    
                    InvoiceInfoVO invoiceInfo = new InvoiceInfoVO();
                    
                    String invoiceType = invoiceListByMonthInfo.getInvoiceType();
                    String totalInvoiceAmount = invoiceListByMonthInfo.getTotalInvoiceAmount();
                    
                    invoiceInfo.setInvoiceType(invoiceType);
                    invoiceInfo.setTotalInvoiceAmount(totalInvoiceAmount);
                    
                    InvoiceListBySubBEList invoiceListBySubBEList = invoiceListByMonthInfo.getInvoiceListBySubBEList();
                    
                    if (invoiceListBySubBEList == null) {
                        continue;
                    }

                    InvoiceListBySubBEInfo[] invoiceListBySubBEArray = invoiceListBySubBEList.getInvoiceListBySubBEArray();
                    
                    if (ArrayUtils.isEmpty(invoiceListBySubBEArray)) {
                        continue;
                    }

                    for (InvoiceListBySubBEInfo invoiceListBySubBEInfo : invoiceListBySubBEArray) {
                        if (invoiceListBySubBEInfo == null) {
                            continue;
                        }
                        
                        Calendar statementDt = invoiceListBySubBEInfo.getStatementDate();
                        Date statementDate = statementDt.getTime();
                        Calendar dueDt = invoiceListBySubBEInfo.getDueDay();
                        Date dueDay = dueDt.getTime();
                        String subBE = invoiceListBySubBEInfo.getSubBE();
                        String totalAmount = invoiceListBySubBEInfo.getTotalAmount();
                        String invoiceBalance = invoiceListBySubBEInfo.getInvoiceBalance();
                        String billingInvoiceNumber = invoiceListBySubBEInfo.getBillingInvoiceNumber();
                        
                        invoiceInfo.setStatementDate(statementDate);
                        invoiceInfo.setDueDay(dueDay);
                        invoiceInfo.setSubBE(subBE);
                        invoiceInfo.setTotalAmount(totalAmount);
                        invoiceInfo.setInvoiceBalance(invoiceBalance);
                        invoiceInfo.setBillingInvoiceNumber(billingInvoiceNumber);
                        
                        invoiceInfoList.add(invoiceInfo);
                    }
                }
                
                result.setInvoiceInfoList(invoiceInfoList);
            }
        }
        
        return result;
    }

    private DecisionDataServiceStub createServiceStub(String targetEndpoint) throws AxisFault {
        return new DecisionDataServiceStub(targetEndpoint);
    }

    private QueryCustomerBillingInfoRequestDocument createRequest(QueryInfo queryInfo) {
    	String userId = queryInfo.getAccountID();
    	
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
        
        QueryCustomerBillingInfoRequest request = QueryCustomerBillingInfoRequest.Factory.newInstance();
        request.setRequestHeader(requestHeader);
        request.setQueryInfo(queryInfo);
        
        QueryCustomerBillingInfoRequestDocument requestDocument = 
                QueryCustomerBillingInfoRequestDocument.Factory.newInstance();
        
        requestDocument.setQueryCustomerBillingInfoRequest(request);
        
        return requestDocument;
    }

}
