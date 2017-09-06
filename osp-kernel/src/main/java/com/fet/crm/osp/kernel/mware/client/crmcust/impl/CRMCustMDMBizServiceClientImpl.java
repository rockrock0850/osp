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

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.www.crmcustbizservice.GetContractByAccountIdOrSubscriberIdV2RequestDocument;
import org.example.www.crmcustbizservice.GetContractByAccountIdOrSubscriberIdV2RequestDocument.GetContractByAccountIdOrSubscriberIdV2Request;
import org.example.www.crmcustbizservice.GetContractByAccountIdOrSubscriberIdV2ResponseDocument;
import org.example.www.crmcustbizservice.GetContractByAccountIdOrSubscriberIdV2ResponseDocument.GetContractByAccountIdOrSubscriberIdV2Response;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetCacheSubscriberInfoByKeyRequestDocument;
import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetCacheSubscriberInfoByKeyRequestDocument.GetCacheSubscriberInfoByKeyRequest;
import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetCacheSubscriberInfoByKeyResponseDocument;
import com.fet.crm.cust.biz.services.xsd.crmcustprofilebizservice.GetCacheSubscriberInfoByKeyResponseDocument.GetCacheSubscriberInfoByKeyResponse;
import com.fet.crm.osp.common.util.DateUtil;
import com.fet.crm.osp.common.util.IdentifierIdUtil;
import com.fet.crm.osp.common.vo.kernel.result.CacheSubscriberInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustInfoForOSPRtnVO;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.mware.client.AbstractBasicSOAPClient;
import com.fet.crm.osp.kernel.mware.client.code.CRMCustMDMBizServiceReturnCode;
import com.fet.crm.osp.kernel.mware.client.code.PaymentCategoryCode;
import com.fet.crm.osp.kernel.mware.client.crmcust.ICRMCustMDMBizServiceClient;
import com.fet.crm.osp.kernel.mware.exception.ESBAPIException;
import com.fet.crm.osp.kernel.mware.exception.MwareExceptionFactory;
import com.fet.crm.services.xsd.crmschema.QuerySubscriberKey;
import com.fet.crm.services.xsd.crmschema.SubscriberInfo;
import com.fet.crm.services.xsd.crmschema.SubscriberInfoResult;
import com.fet.esb.crm.biz.services.crmcustmdmbizservice.CRMCustMDMBizServiceStub;
import com.fet.esb.crm.biz.services.crmcustmdmbizservice.ProcessingFault;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.ContractcomponentTCRMExtension;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.PersonTCRMExtension;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.TCRMAddressBObjType;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.TCRMAdminNativeKeyBObjType;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.TCRMContactMethodBObjType;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.TCRMContractBObjType;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.TCRMContractComponentBObjType;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.TCRMContractPartyRoleBObjType;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.TCRMContractPartyRoleIdentifierBObjType;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.TCRMContractRoleLocationBObjType;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.TCRMPartyAddressBObjType;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.TCRMPartyContactMethodBObjType;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.TCRMPartyIdentificationBObjType;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.TCRMPersonBObjType;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.TCRMPersonNameBObjType;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.XContractComponentBObjExtType;
import com.fet.mdm.services.ncp.xsd.mdmschemav2.XPersonBObjExtType;
import com.fet.mdm.services.xsd.mdmschema.DWLControlDocument.DWLControl;
import com.fet.mdm.services.xsd.mdmschema.InquiryParamDocument.InquiryParam;
import com.fet.mdm.services.xsd.mdmschema.RequestControlDocument.RequestControl;
import com.fet.mdm.services.xsd.mdmschema.TCRMInquiryInputTypeDocument.TCRMInquiryInputType;
import com.fet.mdm.services.xsd.mdmschema.TcrmParamDocument.TcrmParam;
import com.fet.mdm.services.xsd.mdmschema.TransactionInfo;

import tw.com.fet.www.itsoa.schemas.common_domain.sharedresources.xsd.base.schema_xsd2.AuthInfo;
import tw.com.fet.www.itsoa.schemas.common_domain.sharedresources.xsd.base.schema_xsd2.ReturnHeader;

/**
 * CRMCustMDMBizService SOAP Client 實作
 * 
 * @author RichardHuang
 */
@Service
public class CRMCustMDMBizServiceClientImpl extends AbstractBasicSOAPClient implements ICRMCustMDMBizServiceClient {
    
    @Override
    public CustInfoForOSPRtnVO getContractBySubscriberId(String subscriberId, String userId) {
        System.out.println("CRMCustMDMBizServiceClientImpl.getContractBySubscriberId : Input Param [subscriberId = " + subscriberId + "]");
        
        String targetEndpoint = getSysConfValueByConfId(CRM_CUST_MDM_BIZ_SERVICE_ENDPOINT_URL);
        
        CRMCustMDMBizServiceStub stub = null;
        
        try {
            stub = createServiceStub(targetEndpoint);
            
        } catch (AxisFault e) {
            throw new MwareExceptionFactory().getException(e);
        }
        
        GetContractByAccountIdOrSubscriberIdV2RequestDocument request = createGetContractBySubscriberIdRequest(subscriberId, userId);
        
        GetContractByAccountIdOrSubscriberIdV2ResponseDocument responseDocument = null;
        
        try {
            responseDocument = stub.getContractByAccountIdOrSubscriberIdV2(request);
            
        } catch (RemoteException e) {
            throw new MwareExceptionFactory().getException(e);
            
        } catch (ProcessingFault e) {
            throw new MwareExceptionFactory().getException(e);
        }
        
        GetContractByAccountIdOrSubscriberIdV2Response response = 
                responseDocument.getGetContractByAccountIdOrSubscriberIdV2Response();
        
        ReturnHeader returnHeader = response.getReturnHeader();
        String returnCode = returnHeader.getReturnCode();
        String returnMesg = returnHeader.getReturnMesg();
        String reserved2 = returnHeader.getReserved2();
        
        System.out.println("CRMCustMDMBizServiceClientImpl.getContractBySubscriberId : returnCode = " + returnCode + 
        		", returnMesg = " + returnMesg + ", reserved2 = " + reserved2);
        
        if (!StringUtils.equals(returnCode, CRMCustMDMBizServiceReturnCode.SUCCESS.getCode())) {
            throw new ESBAPIException("["+returnCode+"] " + returnMesg + ". " + reserved2);
        }
        
        TCRMContractBObjType tcrmContractBObj = response.getTCRMContractBObj();
        
        Assert.state(tcrmContractBObj != null);
        
        TCRMContractComponentBObjType[] tcrmContractComponentBObjArray = 
                tcrmContractBObj.getTCRMContractComponentBObjArray();
        
        Assert.state(ArrayUtils.isNotEmpty(tcrmContractComponentBObjArray));

        CustInfoForOSPRtnVO result = new CustInfoForOSPRtnVO();
        
        for (TCRMContractComponentBObjType tcrmContractComponentBObj : tcrmContractComponentBObjArray) {
        	ContractcomponentTCRMExtension tcrmExtension = tcrmContractComponentBObj.getTCRMExtension();
        	
        	TCRMContractPartyRoleBObjType[] tcrmContractPartyRoleBObjArray = 
                    tcrmContractComponentBObj.getTCRMContractPartyRoleBObjArray();
        	
        	if (ArrayUtils.isNotEmpty(tcrmContractPartyRoleBObjArray)) {
        		if (ArrayUtils.isNotEmpty(tcrmContractPartyRoleBObjArray)) {

                    for (TCRMContractPartyRoleBObjType tcrmContractPartyRoleBObj : tcrmContractPartyRoleBObjArray) {
                        if (tcrmContractPartyRoleBObj != null) {
                            String roleType = tcrmContractPartyRoleBObj.getRoleType();
                            
                            if (StringUtils.equals(roleType, "31")) {
                            	setAccountId(tcrmContractComponentBObj, result);
                            	setPayerInfo(tcrmContractPartyRoleBObj, tcrmExtension, result);
                                
                            } else if ("21".equals(roleType)) {
                            	setContractStatusValue(tcrmContractComponentBObj, result);
                            	setUserInfo(tcrmContractPartyRoleBObj, tcrmExtension, result);
                            }
                        }
                    }
        		}
        	}
		}
        
        return result;
    }

	private void setContractStatusValue(TCRMContractComponentBObjType tcrmContractComponentBObj,
			CustInfoForOSPRtnVO result) {
		String contractStatusValue = tcrmContractComponentBObj.getContractStatusValue();
		System.out.println("contractStatusValue = " + contractStatusValue);
		result.setContractStatusValue(contractStatusValue);
	}
    
	// 取得並設定付款人資訊
    private void setPayerInfo(TCRMContractPartyRoleBObjType tcrmContractPartyRoleBObj, 
    		ContractcomponentTCRMExtension tcrmExtension, CustInfoForOSPRtnVO result) {
    	TCRMPersonBObjType tcrmPersonBObj = tcrmContractPartyRoleBObj.getTCRMPersonBObj();
    	TCRMContractPartyRoleIdentifierBObjType[] tcrmContractPartyRoleIdentifierBObjArray = 
                tcrmContractPartyRoleBObj.getTCRMContractPartyRoleIdentifierBObjArray();
    	
    	if (tcrmPersonBObj != null) {
            if (ArrayUtils.isNotEmpty(tcrmContractPartyRoleIdentifierBObjArray)) {
                boolean continued = true;
                
                for (TCRMContractPartyRoleIdentifierBObjType tcrmContractPartyRoleIdentifierBObj : tcrmContractPartyRoleIdentifierBObjArray) {
                    String description = tcrmContractPartyRoleIdentifierBObj.getDescription();
                    String identifierId = tcrmContractPartyRoleIdentifierBObj.getIdentifierId();
                    System.out.println("description = " + description + ", identifierId = " + identifierId);
                    
                    if ("F".equals(description)) {
                        TCRMPartyIdentificationBObjType[] tcrmPartyIdentificationBObjArray = tcrmPersonBObj.getTCRMPartyIdentificationBObjArray();
                        
                        if (ArrayUtils.isNotEmpty(tcrmPartyIdentificationBObjArray)) {
                            for (TCRMPartyIdentificationBObjType tcrmPartyIdentificationBObj : tcrmPartyIdentificationBObjArray) {
                                String identificationIdPK = tcrmPartyIdentificationBObj.getIdentificationIdPK();
                                String identificationType = tcrmPartyIdentificationBObj.getIdentificationType();
                                String identificationNumber = tcrmPartyIdentificationBObj.getIdentificationNumber();
                                System.out.println("identificationIdPK = " + identificationIdPK + ", identificationType = " 
                                        + identificationType);
                                
                                if (identifierId != null && identifierId.equals(identificationIdPK)) {
                                    result.setIdentificationType(identificationType);
                                    result.setIdentificationNumber(identificationNumber);
                                    continued = false;
                                    break;
                                }
                            }
                            
                            if (!continued) {
                                break;
                            }
                        }
                    }
                }
            }
            
            TCRMPersonNameBObjType[] tcrmPersonNameBObjArray = tcrmPersonBObj.getTCRMPersonNameBObjArray();
            
            if (ArrayUtils.isNotEmpty(tcrmPersonNameBObjArray)) {
                for (TCRMPersonNameBObjType tcrmPersonNameBObj : tcrmPersonNameBObjArray) {
                    if (tcrmPersonNameBObj != null) {
                        String givenNameOne = (StringUtils.isNotBlank(tcrmPersonNameBObj.getGivenNameOne())) ? 
                                tcrmPersonNameBObj.getGivenNameOne() : "";
                        String lastName = (StringUtils.isNotBlank(tcrmPersonNameBObj.getLastName())) ? 
                                tcrmPersonNameBObj.getLastName() : "";
                        System.out.println("payer givenNameOne = " + givenNameOne + ", payer lastName = " + lastName);
                        
                        String payerName = givenNameOne + lastName;
                        result.setPayerName(payerName);
                        
                        break;
                    }
                }
            }
            
            PersonTCRMExtension personTCRMExtension = tcrmPersonBObj.getTCRMExtension();
            
            if (personTCRMExtension != null) {
                XPersonBObjExtType xPersonBObjExt = personTCRMExtension.getXPersonBObjExt();
                
                String companyName = xPersonBObjExt.getCompanyName();
                System.out.println("companyName = " + companyName);
                
                result.setCompanyName(companyName);
            }
            
            String birthDate = tcrmPersonBObj.getBirthDate();
            String citizenshipType = tcrmPersonBObj.getCitizenshipType();
            System.out.println("birthDate = " + birthDate + ", citizenshipType = " + citizenshipType);
            
            result.setBirthDate(birthDate);
            result.setCitizenshipType(citizenshipType);
    	}
    	
    	TCRMContractRoleLocationBObjType[] tcrmContractRoleLocationBObjArray = 
                tcrmContractPartyRoleBObj.getTCRMContractRoleLocationBObjArray();
    	
    	if (ArrayUtils.isNotEmpty(tcrmContractRoleLocationBObjArray)) {
            List<String> contactTelList = new ArrayList<String>();
            List<String> billEmailList = new ArrayList<String>();
            List<String> otherEmailList = new ArrayList<String>();
            
            for (TCRMContractRoleLocationBObjType tcrmContractRoleLocationBObj : tcrmContractRoleLocationBObjArray) {
                if (tcrmContractRoleLocationBObj != null) {
                    TCRMPartyAddressBObjType tcrmPartyAddressBObj = tcrmContractRoleLocationBObj.getTCRMPartyAddressBObj();
                    
                    if (tcrmPartyAddressBObj != null) {
                        String addressUsageType = tcrmPartyAddressBObj.getAddressUsageType();
                        
                        if ("3".equals(addressUsageType)) {
                            TCRMAddressBObjType tcrmAddressBObj = tcrmPartyAddressBObj.getTCRMAddressBObj();
                            
                            if (tcrmAddressBObj != null) {
                                String billCity = (StringUtils.isNotBlank(tcrmAddressBObj.getCity())) ? 
                                        tcrmAddressBObj.getCity() : "";
                                String billRegion = (StringUtils.isNotBlank(tcrmAddressBObj.getRegion())) ? 
                                        tcrmAddressBObj.getRegion() : "";
                                String billAddressLineOne = (StringUtils.isNotBlank(tcrmAddressBObj.getAddressLineOne())) ? 
                                        tcrmAddressBObj.getAddressLineOne() : "";
                                System.out.println("bill City = " + billCity + ", bill Region = " + billRegion + 
                                        ", bill AddressLineOne = " + billAddressLineOne);
                                
                                String billAddress = StringUtils.join(billCity, billRegion, billAddressLineOne);
                                result.setBillAddress(billAddress);
                            }
                            
                        } else if ("1".equals(addressUsageType)) {
                            TCRMAddressBObjType tcrmAddressBObj = tcrmPartyAddressBObj.getTCRMAddressBObj();
                            
                            if (tcrmAddressBObj != null) {
                                String houseHoldCity = (StringUtils.isNotBlank(tcrmAddressBObj.getCity())) ? 
                                        tcrmAddressBObj.getCity() : "";
                                String houseHoldRegion = (StringUtils.isNotBlank(tcrmAddressBObj.getRegion())) ? 
                                        tcrmAddressBObj.getRegion() : "";
                                String houseHoldAddressLineOne = (StringUtils.isNotBlank(tcrmAddressBObj.getAddressLineOne())) ?
                                        tcrmAddressBObj.getAddressLineOne() : "";
                                
                                System.out.println("houseHold City = " + houseHoldCity + ", houseHold Region = " + houseHoldRegion + 
                                        ", houseHold AddressLineOne = " + houseHoldAddressLineOne);
                                
                                String houseHoldAddress = StringUtils.join(houseHoldCity, houseHoldRegion, houseHoldAddressLineOne);
                                result.setHouseHoldAddress(houseHoldAddress);
                            }
                        }
                    }
                    
                    TCRMPartyContactMethodBObjType tcrmPartyContactMethodBObj = 
                            tcrmContractRoleLocationBObj.getTCRMPartyContactMethodBObj();
                    
                    if (tcrmPartyContactMethodBObj != null) {
                        String contactMethodUsageType = tcrmPartyContactMethodBObj.getContactMethodUsageType();
                        System.out.println("contactMethodUsageType = " + contactMethodUsageType);
                        
                        TCRMContactMethodBObjType tcrmContactMethodBObj = tcrmPartyContactMethodBObj.getTCRMContactMethodBObj();
                        
                        if (tcrmContactMethodBObj != null) {
                            String referenceNumber = tcrmContactMethodBObj.getReferenceNumber();
                            System.out.println("referenceNumber = " + referenceNumber);
                            
                            if ("33".equals(contactMethodUsageType)) { // 帳單電子信箱
                                billEmailList.add(referenceNumber);
                                
                            } else if ("34".equals(contactMethodUsageType)) { // 其他電子信箱
                                otherEmailList.add(referenceNumber);
                                
                            } else if ("11".equals(contactMethodUsageType)) { // 住家電話
                                contactTelList.add(referenceNumber);
                            }
                        }
                    }
                }
            }
            
            result.setBillEmailList(billEmailList);
            result.setOtherEmailList(otherEmailList);
            result.setContactTelList(contactTelList);
    	}
    	
    	if (tcrmExtension != null) {
            XContractComponentBObjExtType xContractComponentBObjExt = tcrmExtension.getXContractComponentBObjExt();
            
            if (xContractComponentBObjExt != null) {
                String promotionSMSIndicator = xContractComponentBObjExt.getPromotionSMSIndicator();
                System.out.println("promotionSMSIndicator = " + promotionSMSIndicator);
                
                result.setPromotionSMSIndicator(promotionSMSIndicator);
            }
        }
	}

	private void setAccountId(TCRMContractComponentBObjType tcrmContractComponentBObj, CustInfoForOSPRtnVO result) {
    	TCRMAdminNativeKeyBObjType[] tcrmAdminNativeKeyBObjArray = 
                tcrmContractComponentBObj.getTCRMAdminNativeKeyBObjArray();
        
        if (ArrayUtils.isNotEmpty(tcrmAdminNativeKeyBObjArray)) {
            for (TCRMAdminNativeKeyBObjType tcrmAdminNativeKeyBObj : tcrmAdminNativeKeyBObjArray) {
                if (tcrmAdminNativeKeyBObj != null) {
                    String adminFieldNameType = tcrmAdminNativeKeyBObj.getAdminFieldNameType();
                    String adminFieldNameValue = tcrmAdminNativeKeyBObj.getAdminFieldNameValue();
                    System.out.println("adminFieldNameType = " + adminFieldNameType + ", adminFieldNameValue = " 
                            + adminFieldNameValue);
                    
                    if ("130".equals(adminFieldNameType)) {
                        result.setAccountId(adminFieldNameValue);
                        break;
                    }
                }
            }
        }
    }
    
    private void setUserInfo(TCRMContractPartyRoleBObjType tcrmContractPartyRoleBObj, 
    		ContractcomponentTCRMExtension tCRMExtension, CustInfoForOSPRtnVO result) {
    	TCRMPersonBObjType tcrmPersonBObj = tcrmContractPartyRoleBObj.getTCRMPersonBObj();
        TCRMContractPartyRoleIdentifierBObjType[] tcrmContractPartyRoleIdentifierBObjArray = 
                                            tcrmContractPartyRoleBObj.getTCRMContractPartyRoleIdentifierBObjArray();
        TCRMContractRoleLocationBObjType[] tcrmContractRoleLocationBObjArray = 
                                            tcrmContractPartyRoleBObj.getTCRMContractRoleLocationBObjArray();
        
    	if (tCRMExtension != null) {
            XContractComponentBObjExtType xContractComponentBObjExt = tCRMExtension.getXContractComponentBObjExt();
            
            if (xContractComponentBObjExt != null) {
                String mobileGenerationCode = xContractComponentBObjExt.getMobileGenerationCode();
                String paymentCategory = xContractComponentBObjExt.getPaymentCategory();
                System.out.println("mobileGenerationCode = " + mobileGenerationCode + ", paymentCategory = " 
                        + paymentCategory);
                
                String mobileGeneration = (StringUtils.isNotBlank(mobileGenerationCode)) ? 
                        StringUtils.join(mobileGenerationCode, "G") : "";
                        
                result.setMobileGeneration(mobileGeneration);
                
                if (StringUtils.isNotBlank(paymentCategory)) {
                    if (StringUtils.equalsIgnoreCase(paymentCategory, PaymentCategoryCode.POSTPAID.getCode())) {
                        paymentCategory = PaymentCategoryCode.POSTPAID.getMessage();
                        
                    } else if (StringUtils.equalsIgnoreCase(paymentCategory, PaymentCategoryCode.PREPAID.getCode())) {
                        paymentCategory = PaymentCategoryCode.PREPAID.getMessage();
                    }
                    
                    result.setPaymentCategory(paymentCategory);
                }
                
                String initialActiveDate = xContractComponentBObjExt.getInitialActiveDate();
                result.setActiveDate(initialActiveDate);
            }
    	}
    	
    	if (tcrmPersonBObj != null) {
            TCRMPersonNameBObjType[] tcrmPersonNameBObjArray = tcrmPersonBObj.getTCRMPersonNameBObjArray();
            
            if (ArrayUtils.isNotEmpty(tcrmPersonNameBObjArray)) {
                for (TCRMPersonNameBObjType tcrmPersonNameBObj : tcrmPersonNameBObjArray) {
                    if (tcrmPersonNameBObj != null) {
                        String givenNameOne = (StringUtils.isNotBlank(tcrmPersonNameBObj.getGivenNameOne())) ? tcrmPersonNameBObj.getGivenNameOne() : "";
                        String lastName = (StringUtils.isNotBlank(tcrmPersonNameBObj.getLastName())) ? tcrmPersonNameBObj.getLastName() : "";
                        System.out.println("user givenNameOne = " + givenNameOne + ", user lastName = " + lastName);
                        
                        String userName = givenNameOne + lastName;
                        result.setUserName(userName);
                        break;
                    }
                }
            }
            
            if (ArrayUtils.isNotEmpty(tcrmContractPartyRoleIdentifierBObjArray)) {
                boolean continued = true;
                
                for (TCRMContractPartyRoleIdentifierBObjType tcrmContractPartyRoleIdentifierBObj : tcrmContractPartyRoleIdentifierBObjArray) {
                    String description = tcrmContractPartyRoleIdentifierBObj.getDescription();
                    String identifierId = tcrmContractPartyRoleIdentifierBObj.getIdentifierId();
                    System.out.println("description = " + description + ", identifierId = " + identifierId);
                    
                    if ("S".equals(description)) {
                        TCRMPartyIdentificationBObjType[] tcrmPartyIdentificationBObjArray = tcrmPersonBObj.getTCRMPartyIdentificationBObjArray();
                        
                        if (ArrayUtils.isNotEmpty(tcrmPartyIdentificationBObjArray)) {
                            for (TCRMPartyIdentificationBObjType tcrmPartyIdentificationBObj : tcrmPartyIdentificationBObjArray) {
                                String identificationIdPK = tcrmPartyIdentificationBObj.getIdentificationIdPK();
                                String identificationType = tcrmPartyIdentificationBObj.getIdentificationType();
                                String identificationNumber = tcrmPartyIdentificationBObj.getIdentificationNumber();
                                System.out.println("identificationIdPK = " + identificationIdPK + ", identificationType = " 
                                        + identificationType);
                                
                                if (identifierId != null && identifierId.equals(identificationIdPK)) {
                                    result.setSecondIdentificationType(identificationType);
                                    result.setSecondIdentificationNumber(identificationNumber);
                                    continued = false;
                                    break;
                                }
                            }
                            
                            if (!continued) {
                                break;
                            }
                        }
                    }
                }
            }
    	}
    	
    	if (ArrayUtils.isNotEmpty(tcrmContractRoleLocationBObjArray)) {
            List<String> personalEmailList = new ArrayList<String>();
            
            for (TCRMContractRoleLocationBObjType tcrmContractRoleLocationBObj : tcrmContractRoleLocationBObjArray) {
                if (tcrmContractRoleLocationBObj != null) {
                    TCRMPartyContactMethodBObjType tcrmPartyContactMethodBObj = 
                            tcrmContractRoleLocationBObj.getTCRMPartyContactMethodBObj();
                    
                    if (tcrmPartyContactMethodBObj != null) {
                        String contactMethodUsageType = tcrmPartyContactMethodBObj.getContactMethodUsageType();
                        System.out.println("contactMethodUsageType = " + contactMethodUsageType);
                        
                        TCRMContactMethodBObjType tcrmContactMethodBObj = tcrmPartyContactMethodBObj.getTCRMContactMethodBObj();
                        
                        if (tcrmContactMethodBObj != null) {
                            String referenceNumber = tcrmContactMethodBObj.getReferenceNumber();
                            System.out.println("referenceNumber = " + referenceNumber);
                            
                            if ("31".equals(contactMethodUsageType)) {
                                personalEmailList.add(referenceNumber);
                            }
                        }
                    }
                }
            }
            
            result.setPersonalEmailList(personalEmailList);
    	}
    }
    
    @Override
    public CacheSubscriberInfoRtnVO getCacheSubscriberInfoByKey(String key, String userId) {
        System.out.println("CRMCustMDMBizServiceClientImpl.getCacheSubscriberInfoByKey : Input Param [key = " + key + "]");
        
        String targetEndpoint = getSysConfValueByConfId(CRM_CUST_MDM_BIZ_SERVICE_ENDPOINT_URL);
        CRMCustMDMBizServiceStub stub = null;
        
        try {
            stub = createServiceStub(targetEndpoint);
            
        } catch (AxisFault e) {
            throw new MwareExceptionFactory().getException(e);
        }
        
        GetCacheSubscriberInfoByKeyRequestDocument requestDocument = createGetCacheSubscriberInfoByKeyRequest(key, userId);
        GetCacheSubscriberInfoByKeyResponseDocument responseDocument = null;
        
        try {
            responseDocument = stub.getCacheSubscriberInfoByKey(requestDocument);
            
        } catch (RemoteException e) {
            throw new MwareExceptionFactory().getException(e);
            
        } catch (ProcessingFault e) {
            throw new MwareExceptionFactory().getException(e);
        }
        
        GetCacheSubscriberInfoByKeyResponse response = responseDocument.getGetCacheSubscriberInfoByKeyResponse();
        tw.com.fet.services.www.common.schema.ReturnHeader returnHeader = response.getReturnHeader();
        String returnCode = returnHeader.getReturnCode();
        String returnMesg = returnHeader.getReturnMesg();
        String reserved2 = returnHeader.getReserved2();
        
        System.out.println("CRMCustMDMBizServiceClientImpl.getCacheSubscriberInfoByKey : returnCode = " + returnCode + ", returnMesg = " + returnMesg);
        
        if (!StringUtils.equals(returnCode, CRMCustMDMBizServiceReturnCode.SUCCESS.getCode())) {
            throw new ESBAPIException("["+returnCode+"] " + returnMesg + ", " + reserved2);
        }
        
        SubscriberInfoResult[] subscriberInfoResultArray = response.getSubscriberInfoResultArray();
        Assert.state(ArrayUtils.isNotEmpty(subscriberInfoResultArray));
        
        SubscriberInfo[] subscriberInfoArray = subscriberInfoResultArray[0].getSubscriberInfoArray();
        Assert.state(ArrayUtils.isNotEmpty(subscriberInfoArray));

        SubscriberInfo subscriberInfo = subscriberInfoArray[0];
        Assert.state(subscriberInfo != null);
        
        String subscriberId = subscriberInfo.getSubscriberId();
        BigInteger contractComponentId = subscriberInfo.getContractComponentId();
        String msisdn = subscriberInfo.getMsisdn();
        BigInteger partyId = subscriberInfo.getPartyId();
        String accountId = subscriberInfo.getAccountId();
        BigInteger contractId = subscriberInfo.getContractId();
        String accountContractComponentId = subscriberInfo.getAccountContractComponentId();
        BigInteger accountPartyId = subscriberInfo.getAccountPartyId();
        String customerId = subscriberInfo.getCustomerId();
        BigInteger adminFldNmTpCd = subscriberInfo.getAdminFldNmTpCd();
        String serviceProvider = subscriberInfo.getServiceProvider();
        String generationCode = subscriberInfo.getGenerationCode();
        String paymentCategory = subscriberInfo.getPaymentCategory();
        String subscriberType = subscriberInfo.getSubscriberType();
        String subscriberStatus = subscriberInfo.getSubscriberStatus();
        String ivrLang = subscriberInfo.getIvrLang();
        Calendar lastMsisdnUpdateDt = subscriberInfo.getLastMsisdnUpdateDt();
        Calendar initActiveDt = subscriberInfo.getInitActiveDt();
        Calendar updateDt = subscriberInfo.getUpdateDt();
        Calendar lastSubStDt = subscriberInfo.getLastSubStDt();
        String lastSubStActivity = subscriberInfo.getLastSubStActivity();
        String subStReason = subscriberInfo.getSubStReason();
        String subStReasonDesr = subscriberInfo.getSubStReasonDesr();
        
        CacheSubscriberInfoRtnVO result = new CacheSubscriberInfoRtnVO();
        result.setSubscriberId(subscriberId);
        result.setContractComponentId(contractComponentId);
        result.setMsisdn(msisdn);
        result.setPartyId(partyId);
        result.setAccountId(accountId);
        result.setContractId(contractId);
        result.setAccountContractComponentId(accountContractComponentId);
        result.setAccountPartyId(accountPartyId);
        result.setCustomerId(customerId);
        result.setAdminFldNmTpCd(adminFldNmTpCd);
        result.setServiceProvider(serviceProvider);
        result.setGenerationCode(generationCode);
        result.setPaymentCategory(paymentCategory);
        result.setSubscriberType(subscriberType);
        result.setSubscriberStatus(subscriberStatus);
        result.setIvrLang(ivrLang);
        
        if (lastMsisdnUpdateDt != null) {
            result.setLastMsisdnUpdateDt(lastMsisdnUpdateDt.getTime());
        }
        
        if (initActiveDt != null) {
            result.setInitActiveDt(initActiveDt.getTime());
        }
        
        if (updateDt != null) {
            result.setUpdateDt(updateDt.getTime());
        }
        
        if (lastSubStDt != null) {
            result.setLastSubStDt(lastSubStDt.getTime());
        }
        
        result.setLastSubStActivity(lastSubStActivity);
        result.setSubStReason(subStReason);
        result.setSubStReasonDesr(subStReasonDesr);
        
        return result;
    }

    private CRMCustMDMBizServiceStub createServiceStub(String targetEndpoint) throws AxisFault {
        return new CRMCustMDMBizServiceStub(targetEndpoint);
    }

    private GetContractByAccountIdOrSubscriberIdV2RequestDocument createGetContractBySubscriberIdRequest(String subscriberId, String userId) {
        AuthInfo paramAuthInfo = AuthInfo.Factory.newInstance();
        paramAuthInfo.setChannelID(Constant.SYSTEM_ID);
        paramAuthInfo.setPassword(getAPIPassword());
        paramAuthInfo.setUserID(userId);
        
        TransactionInfo paramTransactionInfo = TransactionInfo.Factory.newInstance();
        paramTransactionInfo.setTransChannelId(Constant.SYSTEM_ID);
        paramTransactionInfo.setTransUserId(userId);
        paramTransactionInfo.setTransBpId(IdentifierIdUtil.getUuid());
        paramTransactionInfo.setTransPath(Constant.SYSTEM_ID);
        
        // TCRMInquiryInputType
        String requestID = DateUtil.formatDt(new Date(), DateUtil.DATE_TIME_PATTERN_FOR_FILE_NAME);
        DWLControl dWLControl = DWLControl.Factory.newInstance();
        
        RequestControl requestControl = RequestControl.Factory.newInstance();
        requestControl.setRequestID(requestID);
        requestControl.setDWLControl(dWLControl);
        
        TcrmParam idTypeParam = TcrmParam.Factory.newInstance();
        idTypeParam.setName("IdType");
        idTypeParam.setStringValue("120");
        
        TcrmParam idNumberParam = TcrmParam.Factory.newInstance();
        idNumberParam.setName("IdNumber");
        idNumberParam.setStringValue(subscriberId);
        
        TcrmParam[] tcrmParamArray = new TcrmParam[] { idTypeParam, idNumberParam };
        
        InquiryParam inquiryParam = InquiryParam.Factory.newInstance();
        inquiryParam.setTcrmParamArray(tcrmParamArray);
        
        TCRMInquiryInputType paramTCRMInquiryInputType = TCRMInquiryInputType.Factory.newInstance();
        paramTCRMInquiryInputType.setRequestControl(requestControl);
        paramTCRMInquiryInputType.setInquiryParam(inquiryParam);
        
        GetContractByAccountIdOrSubscriberIdV2Request paramGetContractByAccountIdOrSubscriberIdV2Request = 
                GetContractByAccountIdOrSubscriberIdV2Request.Factory.newInstance();
        
        paramGetContractByAccountIdOrSubscriberIdV2Request.setAuthInfo(paramAuthInfo);
        paramGetContractByAccountIdOrSubscriberIdV2Request.setTransactionInfo(paramTransactionInfo);
        paramGetContractByAccountIdOrSubscriberIdV2Request.setTCRMInquiryInputType(paramTCRMInquiryInputType);
        
        GetContractByAccountIdOrSubscriberIdV2RequestDocument requestDocument = 
                GetContractByAccountIdOrSubscriberIdV2RequestDocument.Factory.newInstance();
        
        requestDocument.setGetContractByAccountIdOrSubscriberIdV2Request(paramGetContractByAccountIdOrSubscriberIdV2Request);
        
        return requestDocument;
    }
    
    private GetCacheSubscriberInfoByKeyRequestDocument createGetCacheSubscriberInfoByKeyRequest(String key, String userId) {
        GetCacheSubscriberInfoByKeyRequestDocument requestDocument = 
                GetCacheSubscriberInfoByKeyRequestDocument.Factory.newInstance();
        
        GetCacheSubscriberInfoByKeyRequest paramGetCacheSubscriberInfoByKeyRequest = 
                GetCacheSubscriberInfoByKeyRequest.Factory.newInstance();
        
        tw.com.fet.services.www.common.schema.AuthInfo paramAuthInfo = 
                tw.com.fet.services.www.common.schema.AuthInfo.Factory.newInstance();
        
        paramAuthInfo.setChannelID(Constant.SYSTEM_ID);
        paramAuthInfo.setUserID(userId);
        paramAuthInfo.setPassword(Constant.SYSTEM_ID);
        
        tw.com.fet.services.www.common.schema.TransactionInfo paramTransactionInfo = 
                tw.com.fet.services.www.common.schema.TransactionInfo.Factory.newInstance();
        
        paramTransactionInfo.setTransChannelId(Constant.SYSTEM_ID);
        paramTransactionInfo.setTransUserId(userId);
        paramTransactionInfo.setTransBpId(IdentifierIdUtil.getUuid());
        paramTransactionInfo.setTransPath(Constant.SYSTEM_ID);
        
        // querySubscriberKey
        QuerySubscriberKey paramQuerySubscriberKey = QuerySubscriberKey.Factory.newInstance();
        paramQuerySubscriberKey.setMsisdn(key);
        
        paramGetCacheSubscriberInfoByKeyRequest.setAuthInfo(paramAuthInfo);
        paramGetCacheSubscriberInfoByKeyRequest.setTransactionInfo(paramTransactionInfo);
        paramGetCacheSubscriberInfoByKeyRequest.setQuerySubscriberKey(paramQuerySubscriberKey);
        
        requestDocument.setGetCacheSubscriberInfoByKeyRequest(paramGetCacheSubscriberInfoByKeyRequest);
        
        return requestDocument;
    }

}
