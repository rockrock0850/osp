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

package com.fet.crm.osp.kernel.mware.server.proxy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fet.crm.osp.common.code.kernel.ReturnCode;
import com.fet.crm.osp.common.util.DateUtil;
import com.fet.crm.osp.common.vo.kernel.input.param.AddCIEMasterParamVO;
import com.fet.crm.osp.common.vo.kernel.result.AuthLevelInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.CacheSubscriberInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.CustInfoForAppPartRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.SCVAccountInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.SOATicketDetailRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.SalesInfoVO;
import com.fet.crm.osp.common.vo.kernel.result.SpecialOfferRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.agentinfo.AgentInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustBillingInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustInfoForOSPRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.cie.AddCIEMasterRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.cie.CIEDetailVO;
import com.fet.crm.osp.common.vo.kernel.result.idviewinfo.IdViewInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.promotiondetail.PromotionDetailRtnVO;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.core.service.osp.IOSPToolMainService;
import com.fet.crm.osp.kernel.mware.client.billling.IDecisionDataServiceClient;
import com.fet.crm.osp.kernel.mware.client.cie.ICRMCIEBizServiceClient;
import com.fet.crm.osp.kernel.mware.client.code.PaymentCategoryCode;
import com.fet.crm.osp.kernel.mware.client.coes.ICARServiceClient;
import com.fet.crm.osp.kernel.mware.client.crmcust.ICRMCustBizServiceClient;
import com.fet.crm.osp.kernel.mware.client.crmcust.ICRMCustMDMBizServiceClient;
import com.fet.crm.osp.kernel.mware.client.getcommentfromitt.IQueryTicketDataServiceClient;
import com.fet.crm.osp.kernel.mware.client.oe.ICRMOEBizServiceClient;
import com.fet.crm.osp.kernel.mware.client.pojo.ApproverInfoPOJO;
import com.fet.crm.osp.kernel.mware.client.pojo.AuthLevelInfoInputPOJO;
import com.fet.crm.osp.kernel.mware.client.pojo.AuthLevelInfoPOJO;
import com.fet.crm.osp.kernel.mware.client.soaticketdetail.IOrderNormalizationServiceClient;
import com.fet.crm.osp.kernel.mware.client.weboss.IWebOSSServiceClient;
import com.fet.crm.osp.kernel.mware.client.workflow.ICRMWFBizServiceClient;
import com.fet.crm.osp.kernel.mware.exception.DataFormatException;
import com.fet.crm.osp.kernel.mware.exception.ESBAPIException;
import com.fet.crm.osp.kernel.mware.exception.ESBConnectionException;
import com.fet.crm.osp.kernel.mware.exception.HTTPConnectionException;
import com.fet.crm.osp.kernel.mware.server.vo.process.ExContext;
import com.fet.crm.osp.kernel.mware.server.vo.process.req.ReqBody;
import com.fet.crm.osp.kernel.mware.server.vo.process.req.ReqHeader;
import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.ordernormalizationservice_xsd.SOATicketIofo;

import tw.com.fet.cie.services.www.bobj.schema.CieDetailBObj;
import tw.com.fet.cie.services.www.bobj.schema.CieMasterBObj;

/**
 * 面向OSP相關服務 服務代理窗口. <br>
 * 作用類似以往開發時的「Facade」對象，用來將服務統籌/服務縫合/資料加工/資料處理. <br>
 * 放在這個位置，在於部分服務的方式為轉介其他Webservices，故不適合在core內部進行呼叫. <br>
 * 
 * @author VJChou, RichardHuang
 */
@Service
public class OSPToolMainProxy extends AbstractProxy {

    @Autowired
    private IOSPToolMainService ospToolMainService;
    @Autowired
    private IDecisionDataServiceClient decisionDataServiceClient;
    @Autowired
    private ICRMCustMDMBizServiceClient crmCustMDMBizServiceClient;
    @Autowired
    private ICRMWFBizServiceClient crmWFBizServiceClient;
    @Autowired
    private IQueryTicketDataServiceClient queryTicketDataServiceClient;
    @Autowired
    private ICRMCIEBizServiceClient crmCIEBizServiceClient;
    @Autowired
    private IWebOSSServiceClient webOSSServiceClient;
    @Autowired
    private IOrderNormalizationServiceClient orderNormalizationServiceClient;
    @Autowired
    private ICRMCustBizServiceClient crmCustBizServiceClient;
    @Autowired
    private ICRMOEBizServiceClient crmOEBizServiceClient;
    @Autowired
    private ICARServiceClient carServiceClient;
    
    /**
     * 類型: 查詢. <br>
     * 透過業務人員代碼取得業務人員相關資訊(E-Mail). <br>
     * 查詢CEDS(eHR). <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public ExContext querySalesInfo(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            String empNo = reqBody.getEmpNo();
            Assert.hasText(empNo, "empNo must not be null, empty, or blank");
            
            SalesInfoVO result = ospToolMainService.querySalesInfo(empNo);
            
            Assert.state(result != null);
            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            setFailResult(ReturnCode.ERROR, ex);
        }
        
        return getExContext();
    }
    
    /**
     * 類型: 查詢. <br>
     * 根據 IVR Code 取得業務人員 ID. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public ExContext getUserIdByIvrCode(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            String ivrCode = reqBody.getIvrCode();
            Assert.hasText(ivrCode, "ivrCode must not be null, empty, or blank");
            
            String result = ospToolMainService.getUserIdByIvrCode(ivrCode);
            
            Assert.state(StringUtils.isNotBlank(result));
            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            setFailResult(ReturnCode.ERROR, ex);
        }
        
        return getExContext();
    }
    
    /**
     * 類型: 查詢. <br>
     * 
     * [從ITT取得備註資訊]<br>
     * 當前台(osp-platform)進行結案動作(有效件/無效件)時，且系統來源(from sourceSysId = 'ITT' )，
     * 觸發前端呼叫此服務。透過此服務再次呼叫 ITT 系統所提供的API，將回傳的欄位組成字串，回傳前端。
     * <br>
     * 做為前端存入OSP的工單備註欄位資訊. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public ExContext getCommentFromITT(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            
            String sourceOrderId = reqBody.getSourceOrderId();
            
            Assert.hasText(sourceOrderId, "sourceOrderId must not be null, empty, or blank");
            
			String result = queryTicketDataServiceClient.queryTicketData(sourceOrderId);
             
            Assert.state(StringUtils.isNotBlank(result));
            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult();
            
        } catch (ESBConnectionException ex) {
            setFailResult(ReturnCode.ESB_CONNECTION_ERROR, ex);
            
        } catch (ESBAPIException ex) {
            setFailResult(ReturnCode.ESB_API_ERROR, ex);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            setFailResult(ReturnCode.ERROR, ex);
        }
    
        return getExContext();
    }

    /**
     * 類型: 查詢. <br>
     * 
     * 取得 AppPart 啟動所需客資. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public ExContext getCustInfoForAppPart(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            
            String ownId = reqBody.getOwnId();
            String msisdn = reqBody.getMsisdn();
            String idType = reqBody.getIdType();
            String rocId = reqBody.getRocId();
            
            Assert.hasText(ownId, "ownId must not be null, empty, or blank");
            Assert.hasText(msisdn, "msisdn must not be null, empty, or blank");
            
            CustInfoForAppPartRtnVO result = ospToolMainService.getCustInfoForAppPart(ownId, msisdn, idType, rocId);
            
            Assert.state(result != null);
            
            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult();
            
        } catch (HTTPConnectionException ex) {
            setFailResult(ReturnCode.HTTP_CONNECTION_ERROR, ex);
            
        } catch (DataFormatException ex) {
            setFailResult(ReturnCode.DATA_FORMAT_ERROR, ex);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            setFailResult(ReturnCode.ERROR, ex);
        }
    
        return getExContext();
    }

    /**
     * 類型: 查詢. <br>
     * 
     * 取得核資, 付款人與使用人相關資訊. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public ExContext getCustInfoForOSP(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            
            String userId = reqBody.getUserId();
            String subscriberId = reqBody.getSubscriberId();
            
            Assert.hasText(userId, "userId must not be null, empty, or blank");
            Assert.hasText(subscriberId, "subscriberId must not be null, empty, or blank");
            
			CustInfoForOSPRtnVO result = crmCustMDMBizServiceClient.getContractBySubscriberId(subscriberId, userId);
            
            Assert.state(result != null);
            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult();
            
        } catch (ESBConnectionException ex) {
            setFailResult(ReturnCode.ESB_CONNECTION_ERROR, ex);
            
        } catch (ESBAPIException ex) {
            setFailResult(ReturnCode.ESB_API_ERROR, ex);
            
        } catch (Exception ex) {
            setFailResult(ReturnCode.ERROR, ex);
        }
    
        return getExContext();
    }

    /**
     * 類型: 查詢. <br>
     * 
     * 取得帳務相關資訊. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public ExContext getCustBillingInfo(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
        	ReqHeader reqHeader = exContext.getReqHeader();
            ReqBody reqBody = exContext.getReqBody();
            
            String clientIP = reqHeader.getRemoteAddr();
            String accountId = reqBody.getAccountId();
            String subscriberId = reqBody.getSubscriberId();
            
            Assert.hasText(accountId, "accountId must not be null, empty, or blank");
            Assert.hasText(subscriberId, "subscriberId must not be null, empty, or blank");
            
			CustBillingInfoRtnVO result = decisionDataServiceClient.queryCustomerBillingInfo(clientIP, accountId, subscriberId);
            
            Assert.state(result != null);
            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult();
            
        } catch (ESBConnectionException ex) {
            setFailResult(ReturnCode.ESB_CONNECTION_ERROR, ex);
            
        } catch (ESBAPIException ex) {
            setFailResult(ReturnCode.ESB_API_ERROR, ex);
            
        } catch (Exception ex) {
            setFailResult(ReturnCode.ERROR, ex);
        }
        
        return getExContext();
    }

    /**
     * 類型: 查詢. <br>
     * 
     * 取得 AppPart 所需使用者登入相關資訊. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public ExContext getAgentInfo(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            String accountId = reqBody.getAccountId();
            
            Assert.hasText(accountId, "accountId must not be null, empty, or blank");
            
            AgentInfoRtnVO result = ospToolMainService.getAgentInfo(accountId);
             
            Assert.state(result != null);
            
            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult();
            
        } catch (Exception ex) {
            setFailResult(ReturnCode.ERROR, ex);
        }

        return getExContext();
    }
    
    /**
     * 類型: 查詢. <br>
     * 根據門號清單，取得 SOA 進件詳細資訊. <br>
     * 
     * @param exContext
     * 
     * @return ExContext
     */
    public ExContext getSOATicketDetail(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            
            String userId = reqBody.getUserId();
            List<String> msisdnList = reqBody.getMsisdnList();
            
            Assert.hasText(userId, "userId must not be null, empty, or blank");
            Assert.notEmpty(msisdnList, "msisdnList must not be empty");
            
			List<SOATicketIofo> soaTicketInfoList = orderNormalizationServiceClient.getSOATicketDetail(msisdnList, userId);
            
            Assert.state(CollectionUtils.isNotEmpty(soaTicketInfoList));
            
            List<SOATicketDetailRtnVO> result = new ArrayList<>();
            
            for (SOATicketIofo soaTicketInfo : soaTicketInfoList) {
                SOATicketDetailRtnVO soaTicketDetailRtnVO = new SOATicketDetailRtnVO();
                
                String ticketNo = soaTicketInfo.getTicketNo();
                String msisdn = soaTicketInfo.getMsisdn();
                String recipientID = soaTicketInfo.getRecipientID();
                String dornorID = soaTicketInfo.getDornorID();
                String custID = soaTicketInfo.getCustID();
                Calendar fIDCal = soaTicketInfo.getFID();
                Date fID = null;
                
                if (fIDCal != null) {
                    fID = fIDCal.getTime();
                }
                
                String contractDate = soaTicketInfo.getContractDate();
                String state = soaTicketInfo.getState();
                
                soaTicketDetailRtnVO.setTicketNo(ticketNo);
                soaTicketDetailRtnVO.setMsisdn(msisdn);
                soaTicketDetailRtnVO.setRecipientID(recipientID);
                soaTicketDetailRtnVO.setDornorID(dornorID);
                soaTicketDetailRtnVO.setCustID(custID);
                soaTicketDetailRtnVO.setFID(DateUtil.formatDt(fID, DateUtil.DATE_TIME_PATTERN));
                soaTicketDetailRtnVO.setContractDate(contractDate);
                soaTicketDetailRtnVO.setState(state);
                
                result.add(soaTicketDetailRtnVO);
            }
            
            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult();
            
        } catch (ESBConnectionException ex) {
            setFailResult(ReturnCode.ESB_CONNECTION_ERROR, ex);
            
        } catch (ESBAPIException ex) {
            setFailResult(ReturnCode.ESB_API_ERROR, ex);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            setFailResult(ReturnCode.ERROR, ex);
        }

        return getExContext();
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得簽核層級與人員資訊. <br>
     * 
     * @param exContext
     * 
     * @return ExContext
     */
    public ExContext getAuthLevelInfo(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            
            String userId = reqBody.getUserId();
            String orderTypeId = reqBody.getOrderTypeId();
            String ivrCode = reqBody.getIvrCode();
            String rocId = reqBody.getRocId();
            String salesId = reqBody.getSalesId(); // 業務人員員編
            String promotionId = reqBody.getPromotionId(); // 申辦的 Promotion
            String subscriberId = reqBody.getSubscriberId();
            
            Assert.hasText(userId, "userId must not be null, empty, or blank");
            Assert.hasText(orderTypeId, "orderTypeId must not be null, empty, or blank");
            Assert.hasText(ivrCode, "ivrCode must not be null, empty, or blank");
            
            AuthLevelInfoInputPOJO inputParam = new AuthLevelInfoInputPOJO();
            inputParam.setUserId(userId);
            inputParam.setIvrCode(ivrCode);
            
            // 若是加盟門市，則從 NSP 取得其業務人員員編
            if (StringUtils.length(ivrCode) == 7) {
            	salesId = ospToolMainService.getUserIdByIvrCode(ivrCode);
            	inputParam.setSalesChannelType(Constant.VASS_CHANNEL_TYPE); // 加盟
            	
            } else {
            	inputParam.setSalesChannelType(Constant.RETAIL_CHANNEL_TYPE); // 直營
            }
            
            inputParam.setSalesId(salesId);
            
            // 需要取得簽核層級資訊的案件類型清單
 			String[] authLevelOrderTypeIds = 
 	         		new String[] { "OSPL4001", "OSPL4002", "OSPL4013", "OSPL4014", "OSPL4018" };
			
			// 若存在於需要取得簽核層級資訊的案件類型清單中
			if (ArrayUtils.contains(authLevelOrderTypeIds, orderTypeId)) {
				List<SCVAccountInfoRtnVO> scvAccountInfoList = 
						crmCustBizServiceClient.getRecordBySubscrId(subscriberId, userId);
				
				Assert.state(CollectionUtils.isNotEmpty(scvAccountInfoList));
				
				SCVAccountInfoRtnVO scvAccountInfo = scvAccountInfoList.get(0);
				String isCorp = scvAccountInfo.getIsCorp();
				
				// 若為公司戶
				if (StringUtils.equals(isCorp, "Y")) {
					inputParam.setIdentificationType("2");
					
				} else {
					inputParam.setIdentificationType("1");
				}
				
				inputParam.setRocId(rocId);
				inputParam.setPostpaidWirelessId(promotionId);
				inputParam.setSubscriberId(subscriberId);
				
			} else { // 否則設定簽核層級數目為 1，以取得上層主管資訊
				inputParam.setAuthLevel("1");
			}
            
            AuthLevelInfoPOJO authLevelInfo = crmWFBizServiceClient.getAuthLevelInfo(inputParam);
            
            Assert.state(authLevelInfo != null);

            String authLevel = authLevelInfo.getAuthLevel();
            List<ApproverInfoPOJO> approverList = authLevelInfo.getApproverList();
            
            AuthLevelInfoRtnVO result = new AuthLevelInfoRtnVO();
            result.setAuthLevel(authLevel);
            
            if (CollectionUtils.isNotEmpty(approverList)) {
                ApproverInfoPOJO approver = approverList.get(0);
                
                String empId = approver.getEmpId();
                String email = approver.getEmail();
                String name = approver.getName();
                String sms = approver.getSms();
                String level = approver.getLevel();
                String levelDesc = approver.getLevelDesc();
                
                result.setEmpId(empId);
                result.setEmail(email);
                result.setName(name);
                result.setSms(sms);
                result.setLevel(level);
                result.setLevelDesc(levelDesc);
            }
        
            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult(ex);
            
        } catch (ESBConnectionException ex) {
            setFailResult(ReturnCode.ESB_CONNECTION_ERROR, ex);
            
        } catch (ESBAPIException ex) {
            setFailResult(ReturnCode.ESB_API_ERROR, ex);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            setFailResult(ReturnCode.ERROR, ex);
        }

        return getExContext();
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得學生溫暖長青申請方案相關資訊. <br>
     * 
     * @param exContext
     * 
     * @return ExContext
     */
    public ExContext getSpecialOfferByRocId(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            
            String userId = reqBody.getUserId();
            String rocId = reqBody.getRocId();
            
            Assert.hasText(userId, "userId must not be null, empty, or blank");
            Assert.hasText(rocId, "rocId must not be null, empty, or blank");
            
			List<SpecialOfferRtnVO> result = crmCustBizServiceClient.getSpecialOfferByRocId(rocId, userId);
            
            Assert.state(CollectionUtils.isNotEmpty(result));

            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult(ex);
            
        } catch (ESBConnectionException ex) {
            setFailResult(ReturnCode.ESB_CONNECTION_ERROR, ex);
            
        } catch (ESBAPIException ex) {
            setFailResult(ReturnCode.ESB_API_ERROR, ex);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            setFailResult(ReturnCode.ERROR, ex);
        }

        return getExContext();
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得 SPV 促代資訊. <br>
     * 
     * @param exContext
     * 
     * @return ExContext
     */
    public ExContext getPromotionDetail(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            String promotionId = reqBody.getPromotionId();
            
            Assert.hasText(promotionId, "promotionId must not be null, empty, or blank");
            
            PromotionDetailRtnVO result = carServiceClient.getPromotionDetail(promotionId);
            
            Assert.state(result != null, "promotionDetailRtnVO is null");

            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult(ex);
            
        } catch (Exception ex) {
            setFailResult(ReturnCode.ERROR, ex);
        }

        return getExContext();
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得 NE Info. <br>
     * 
     * @param exContext
     * 
     * @return ExContext
     */
    public ExContext getNEInfo(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            
            String userId = reqBody.getUserId();
            String paymentCategory = reqBody.getPaymentCategory();
            String msisdn = reqBody.getMsisdn();
            String accountName = reqBody.getAccountName();
            String subType = reqBody.getSubType();
            
            Assert.hasText(userId, "userId must not be null, empty, or blank");
            Assert.hasText(paymentCategory, "paymentCategory must not be null, empty, or blank");
            Assert.hasText(msisdn, "msisdn must not be null, empty, or blank");
            Assert.hasText(accountName, "accountName must not be null, empty, or blank");
            Assert.hasText(subType, "subType must not be null, empty, or blank");
            
            if (StringUtils.equals(paymentCategory, PaymentCategoryCode.POSTPAID.getCode())) {
                paymentCategory = PaymentCategoryCode.POSTPAID.getCategory();
                
            } else if (StringUtils.equals(paymentCategory, PaymentCategoryCode.PREPAID.getCode())) {
                paymentCategory = PaymentCategoryCode.PREPAID.getCategory();
            }
            
			String result = webOSSServiceClient.getNEInfo(paymentCategory, msisdn, accountName, subType, userId);
            
            Assert.state(StringUtils.isNotBlank(result));
            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult(ex);
            
        } catch (ESBConnectionException ex) {
            setFailResult(ReturnCode.ESB_CONNECTION_ERROR, ex);
            
        } catch (ESBAPIException ex) {
            setFailResult(ReturnCode.ESB_API_ERROR, ex);
            
        } catch (Exception ex) {
            setFailResult(ReturnCode.ERROR, ex);
        }

        return getExContext();
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得 SIM 卡號. <br>
     * 
     * @param exContext
     * 
     * @return ExContext
     */
    public ExContext getSimIdByMsisdn(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            
            String userId = reqBody.getUserId();
            String paymentCategory = reqBody.getPaymentCategory();
            String msisdn = reqBody.getMsisdn();
            
            Assert.hasText(userId, "userId must not be null, empty, or blank");
            Assert.hasText(paymentCategory, "paymentCategory must not be null, empty, or blank");
            Assert.hasText(msisdn, "msisdn must not be null, empty, or blank");
            
			String result = crmOEBizServiceClient.querySimByMsisdn(msisdn, paymentCategory, userId);
            
            Assert.state(result != null);
            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult(ex);
            
        } catch (ESBConnectionException ex) {
            setFailResult(ReturnCode.ESB_CONNECTION_ERROR, ex);
            
        } catch (ESBAPIException ex) {
            setFailResult(ReturnCode.ESB_API_ERROR, ex);
            
        } catch (Exception ex) {
            setFailResult(ReturnCode.ERROR, ex);
        }

        return getExContext();
    }
    
    /**
     * 類型: 查詢. <br>
     * 根據門號取得 Cache Subscriber Info 資訊. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public ExContext getCacheSubscriberInfoByMsisdn(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            
            String userId = reqBody.getUserId();
            String msisdn = reqBody.getMsisdn();
            
            Assert.hasText(userId, "userId must not be null, empty, or blank");
            Assert.hasText(msisdn, "msisdn must not be null, empty, or blank");
            
			CacheSubscriberInfoRtnVO result = crmCustMDMBizServiceClient.getCacheSubscriberInfoByKey(msisdn, userId);
            
            Assert.state(result != null);
            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult();
            
        } catch (ESBConnectionException ex) {
            setFailResult(ReturnCode.ESB_CONNECTION_ERROR, ex);
            
        } catch (ESBAPIException ex) {
            setFailResult(ReturnCode.ESB_API_ERROR, ex);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            setFailResult(ReturnCode.ERROR, ex);
        }
        
        return getExContext();
    }
    
    /**
     * 類型: 查詢. <br>
     * 根據證號取得客資歷史資訊. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public ExContext getIdViewInfoByRocId(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            
            String userId = reqBody.getUserId();
            String rocId = reqBody.getRocId();
            
            Assert.hasText(userId, "userId must not be null, empty, or blank");
            Assert.hasText(rocId, "rocId must not be null, empty, or blank");
            
			IdViewInfoRtnVO result = crmCustBizServiceClient.getIdViewInfoByRocId(rocId, userId);
            
            Assert.state(result != null 
            		&& result.getTotalCount() > 0 
            		&& CollectionUtils.isNotEmpty(result.getIdViewInfoList()));
            
            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult();
            
        } catch (ESBConnectionException ex) {
            setFailResult(ReturnCode.ESB_CONNECTION_ERROR, ex);
            
        } catch (ESBAPIException ex) {
            setFailResult(ReturnCode.ESB_API_ERROR, ex);
            
        } catch (Exception ex) {
            setFailResult(ReturnCode.ERROR, ex);
        }
        
        return getExContext();
    }
    
    /**
     * 類型: 資料異動. <br>
     * 傳入OSP之前呼叫服務所代入的參數「ospKey」，將TxId更新回OSP. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public ExContext updateTxIdByIdentifyId(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            
            String ospKey = reqBody.getOspKey();
            String txId = reqBody.getTxId();
            
            Assert.hasText(ospKey, "ospKey must not be null, empty, or blank");
            Assert.hasText(txId, "txId must not be null, empty, or blank");
            
            boolean success = ospToolMainService.updateTxIdByIdentifyId(ospKey, txId);
            
            Assert.state(success);
            
            setSuccessResult(success);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setNoDataResult();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            setFailResult(ReturnCode.ERROR, ex);
        }

        return getExContext();
    }
    
    /**
     * 類型: 資料異動. <br>
     * 新增 CIE Master 資料. <br>
     * 
     * @param exContext
     * 
     * @return ExContext
     */
    public ExContext addCIEMaster(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            
            String userId = reqBody.getUserId();
            String empNo = reqBody.getEmpNo();
            String contractComponentId = reqBody.getContractComponentId();
            String partyId = reqBody.getPartyId();
            String accountId = reqBody.getAccountId();
            String subscriberId = reqBody.getSubscriberId();
            String msisdn = reqBody.getMsisdn();
            String serviceProvider = reqBody.getServiceProvider();
            String generationCode = reqBody.getGenerationCode();
            String paymentCategory = reqBody.getPaymentCategory();
            
            Assert.hasText(userId, "userId must not be null, empty, or blank");
            Assert.hasText(empNo, "empNo must not be null, empty, or blank");
            Assert.hasText(contractComponentId, "contractComponentId must not be null, empty, or blank");
            Assert.hasText(partyId, "partyId must not be null, empty, or blank");
            Assert.hasText(accountId, "accountId must not be null, empty, or blank");
            Assert.hasText(subscriberId, "subscriberId must not be null, empty, or blank");
            Assert.hasText(msisdn, "msisdn must not be null, empty, or blank");
            Assert.hasText(serviceProvider, "serviceProvider must not be null, empty, or blank");
            Assert.hasText(generationCode, "generationCode must not be null, empty, or blank");
            Assert.hasText(paymentCategory, "paymentCategory must not be null, empty, or blank");
            
            AddCIEMasterParamVO requestParam = new AddCIEMasterParamVO();
            requestParam.setUserId(userId);
            requestParam.setAccountId(accountId);
            requestParam.setContractComponentId(contractComponentId);
            requestParam.setEmpNo(empNo);
            requestParam.setGenerationCode(generationCode);
            requestParam.setMsisdn(msisdn);
            requestParam.setPartyId(partyId);
            requestParam.setPaymentCategory(paymentCategory);
            requestParam.setServiceProvider(serviceProvider);
            requestParam.setSubscriberId(subscriberId);
            
            CieMasterBObj cieMasterBObj = crmCIEBizServiceClient.addCIEMaster(requestParam);
            
            Assert.state(cieMasterBObj != null);
            
            AddCIEMasterRtnVO result = new AddCIEMasterRtnVO();
            result.setCieId(String.valueOf(cieMasterBObj.getCieId().longValue()));
            result.setPotentialContactIndicator(cieMasterBObj.getPotentialContactIndicator());
            result.setInOutIndicator(cieMasterBObj.getInOutIndicator());
            result.setChannelType(String.valueOf(cieMasterBObj.getChannelType().longValue()));
            result.setChannelValue(cieMasterBObj.getChannelValue());
            result.setFetContactMethodType(String.valueOf(cieMasterBObj.getFetContactMethodType().longValue()));
            result.setFetContactMethodValue(cieMasterBObj.getFetContactMethodValue());
            result.setCreatedAgent(cieMasterBObj.getCreatedAgent());
            result.setCreatedSystem(cieMasterBObj.getCreatedSystem());
            result.setStartDate(cieMasterBObj.getStartDate().getTime());
            result.setEndDate(cieMasterBObj.getEndDate().getTime());
            result.setCreateDate(cieMasterBObj.getCreateDate().getTime());
            result.setCieMasterLastUpdateDate(cieMasterBObj.getCieMasterLastUpdateDate().getTime());
            result.setCieMasterLastUpdateUser(cieMasterBObj.getCieMasterLastUpdateUser());
            result.setCieMasterLastUpdateTxId(String.valueOf(cieMasterBObj.getCieMasterLastUpdateTxId().longValue()));
            
            CieDetailBObj[] detailBObjArray = cieMasterBObj.getDetailBObjArray();
            
            if (ArrayUtils.isNotEmpty(detailBObjArray)) {
                List<CIEDetailVO> cieDetailVOList = new ArrayList<>();
                
                for (CieDetailBObj cieDetailBObj : detailBObjArray) {
                    CIEDetailVO cieDetailVO = new CIEDetailVO();
                    cieDetailVO.setCieDetailId(String.valueOf(cieDetailBObj.getCieDetailId().longValue()));
                    cieDetailVO.setCieId(String.valueOf(cieDetailBObj.getCieId().longValue()));
                    cieDetailVO.setAccountContactId(String.valueOf(cieDetailBObj.getAccountContactId().longValue()));
                    cieDetailVO.setSubscriberContactId(String.valueOf(cieDetailBObj.getSubscriberContactId().longValue()));
                    cieDetailVO.setAccountContractCompId(String.valueOf(cieDetailBObj.getAccountContractCompId().longValue()));
                    cieDetailVO.setSubscriberContractCompId(String.valueOf(cieDetailBObj.getSubscriberContractCompId().longValue()));
                    cieDetailVO.setBillingAccountId(String.valueOf(cieDetailBObj.getBillingAccountId().longValue()));
                    cieDetailVO.setBillingSubscriberId(String.valueOf(cieDetailBObj.getBillingSubscriberId().longValue()));
                    cieDetailVO.setMsisdn(cieDetailBObj.getResourceValue());
                    cieDetailVO.setServiceProvider(cieDetailBObj.getServiceProvider());
                    cieDetailVO.setMobileGenerationCode(cieDetailBObj.getMobileGenerationCode());
                    cieDetailVO.setPaymentCategory(cieDetailBObj.getPaymentCategory());
                    cieDetailVO.setCreatedDate(cieDetailBObj.getCreatedDate().getTime());
                    cieDetailVO.setCieDetailLastUpdateDate(cieDetailBObj.getCieDetailLastUpdateDate().getTime());
                    cieDetailVO.setCieDetailLastUpdateUser(cieDetailBObj.getCieDetailLastUpdateUser());
                    cieDetailVO.setCieDetailLastUpdateTxId(String.valueOf(cieDetailBObj.getCieDetailLastUpdateTxId().longValue()));
                    
                    cieDetailVOList.add(cieDetailVO);
                }
                
                result.setCieDetailVOList(cieDetailVOList);
            }
            
            setSuccessResult(result);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (ESBConnectionException ex) {
            setFailResult(ReturnCode.ESB_CONNECTION_ERROR, ex);
            
        } catch (ESBAPIException ex) {
            setFailResult(ReturnCode.ESB_API_ERROR, ex);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            setFailResult(ReturnCode.ERROR, ex);
        }

        return getExContext();
    }
    
}
