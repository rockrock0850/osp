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

package com.fet.crm.osp.platform.mware.client.restful.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fet.crm.osp.common.code.kernel.ReturnCode;
import com.fet.crm.osp.common.mock.MockService;
import com.fet.crm.osp.common.util.OSPRESTFulWSUtil;
import com.fet.crm.osp.common.vo.kernel.input.param.AddCIEMasterParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.AuthLevelInfoParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.CacheSubscriberInfoParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.CustBillingInfoParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.CustInfoForAppPartParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.CustInfoForOSPParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.IdViewInfoParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.NEInfoParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.OrderLoadParam;
import com.fet.crm.osp.common.vo.kernel.input.param.OrderParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.SOATicketDetailParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.SimIdParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.SpecialOfferParamVO;
import com.fet.crm.osp.common.vo.kernel.output.AddCIEMasterOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.AgentInfoOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.AuthLevelInfoOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.CacheSubscriberInfoOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.CustBillingInfoOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.CustInfoForAppPartOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.IdViewInfoOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.OrderLoadOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.OrderUpdateOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.PromotionDetailOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.SOATicketDetailOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.SalesOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.SpecialOfferOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.StringOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.checkCustInfo.CustInfoForOSPOutputVO;
import com.fet.crm.osp.common.vo.kernel.process.ResultHeader;
import com.fet.crm.osp.common.vo.kernel.result.AuthLevelInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.OrderLoadRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.SOATicketDetailRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.SpecialOfferRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.agentinfo.AgentInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustBillingInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.idviewinfo.IdViewInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.promotiondetail.PromotionDetailRtnVO;
import com.fet.crm.osp.platform.core.common.util.BeanCopyUtil;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.vo.systeminfo.agent.AgentInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.agent.GroupDefinitionVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.agent.InternaluseraccountVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.agent.StaffVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.agent.UsersVO;
import com.fet.crm.osp.platform.mware.client.vo.AddCIEMasterReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.AddCIEMasterVO;
import com.fet.crm.osp.platform.mware.client.vo.AuthLevelInfoReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.AuthLevelInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.CacheSubscriberInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.CustBillingInfoReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.CustBillingInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.CustInfoForAppPartReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.CustInfoForOSPReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.IdViewInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.InvoiceInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.NEInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.OrderLoadReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.OrderLoadVO;
import com.fet.crm.osp.platform.mware.client.vo.PromotionDetailReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.SpecialOfferVO;
import com.fet.crm.osp.platform.webapp.interceptor.accesslog.SimpleAccessLogInterceptor;

/**
 * [OSP Kerne RESTful]用戶端服務 控制器. <br>
 * 
 * @author Lawrence.Lai, Adam Yeh
 */
@Service
public class OSPKernelRESTClient {
	
	private static Logger logger = LoggerFactory.getLogger(SimpleAccessLogInterceptor.class);

	@Autowired
	private MockService mockService;
	
    /**
     * 類型: 資料異動. <br>
     * 新增 CIE Master 資料. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.ADD_CIE_MASTER.getServiceUri()
     * 
     * @param addCIEMasterParamVO POST 請求參數
     *          
     * @return AddCIEMasterOutputVO 回傳結果資訊封裝物件
     */
    public AddCIEMasterReturnVO addCIEMaster(AddCIEMasterVO paramVO, String ntAccount) {
		if (paramVO == null) {
			return new AddCIEMasterReturnVO();
		}
		
		AddCIEMasterParamVO addCIEMasterParamVO = new AddCIEMasterParamVO();
		BeanUtils.copyProperties(paramVO, addCIEMasterParamVO);
		
		addCIEMasterParamVO.setUserId(ntAccount);

		RestTemplate restTemplate = new RestTemplate();
		AddCIEMasterOutputVO result = restTemplate.postForObject(
				OSPRESTFulWSUtil.ADD_CIE_MASTER.getServiceUri(), addCIEMasterParamVO, AddCIEMasterOutputVO.class);

		ResultHeader resultHeader = result.getResultHeader();
		String returnCode = resultHeader.getReturnCode();

		if (!StringUtils.equals(returnCode, ReturnCode.SUCCESS.getCode())) {
			return new AddCIEMasterReturnVO();
		}

		AddCIEMasterReturnVO returnVO = new AddCIEMasterReturnVO();
		BeanUtils.copyProperties(result.getResultBody(), returnVO);
		
		return returnVO;
    }

    /**
     * 類型：查詢. <br>
     * 根據使用者 ntAccountId，取得 使用者登入相關資訊. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_AGENT_INFO.getServiceUri(). <br>
     * 
     * @param ntAccountId
     * @return AgentInfoVO
     */
	public AgentInfoVO getAgentInfo(String ntAccount) {
		if (StringUtils.isNotBlank(ntAccount)) {
			RestTemplate restTemplate = new RestTemplate();

			AgentInfoOutputVO result = restTemplate.postForObject(OSPRESTFulWSUtil.GET_AGENT_INFO.getServiceUri(),
					ntAccount, AgentInfoOutputVO.class);

//			AgentInfoOutputVO result = mockService.getAgentInfo(ntAccount);

			ResultHeader resultHeader = result.getResultHeader();
			String returnCode = resultHeader.getReturnCode();

			if (ReturnCode.SUCCESS.getCode().equals(returnCode)) {
				AgentInfoRtnVO agentInfoResultVO = result.getResultBody();

				if (agentInfoResultVO != null) {
					com.fet.crm.osp.common.vo.kernel.result.agentinfo.InternaluseraccountVO userAccountSRC = agentInfoResultVO.getInternaluseraccountVO();
					com.fet.crm.osp.common.vo.kernel.result.agentinfo.StaffVO staffSRC = agentInfoResultVO.getStaffVO();
					com.fet.crm.osp.common.vo.kernel.result.agentinfo.GroupDefinitionVO groupDefinitionSRC = agentInfoResultVO.getGroupDefinitionVO();
					com.fet.crm.osp.common.vo.kernel.result.agentinfo.UsersVO usersSRC = agentInfoResultVO.getUsersVO();

					AgentInfoVO agentInfoVO = new AgentInfoVO();
					BeanUtils.copyProperties(agentInfoResultVO, agentInfoVO);
					
					if (userAccountSRC != null) {
						InternaluseraccountVO internaluseraccountVO = new InternaluseraccountVO();
						BeanUtils.copyProperties(userAccountSRC, internaluseraccountVO);
						
						agentInfoVO.setInternaluseraccountVO(internaluseraccountVO);
					}
					if (staffSRC != null) {
						StaffVO staffVO = new StaffVO();
						BeanUtils.copyProperties(staffSRC, staffVO);
						
						agentInfoVO.setStaffVO(staffVO);
					}
					if (groupDefinitionSRC != null) {
						GroupDefinitionVO groupDefinitionVO = new GroupDefinitionVO();
						BeanUtils.copyProperties(groupDefinitionSRC, groupDefinitionVO);
						
						agentInfoVO.setGroupDefinitionVO(groupDefinitionVO);
					}
					if (usersSRC != null) {
						UsersVO usersVO = new UsersVO();
						BeanUtils.copyProperties(usersSRC, usersVO);
						
						agentInfoVO.setUsersVO(usersVO);
					}
					
					return agentInfoVO;
				}
			}
		}

		return null;
	}

	/**
	 * 類型: 查詢. <br>
	 * 取得簽核層級與人員資訊. <br>
	 * 
	 * RESTful POST URI - OSPRESTFulWSUtil.GET_AUTH_LEVEL_INFO.getServiceUri()
	 * 
	 * @param paramVO
	 *            POST 請求參數
	 * @return AuthLevelOutputVO 回傳結果資訊封裝物件
	 */
	public AuthLevelInfoReturnVO getAuthLevelInfo(AuthLevelInfoVO inputVO, String ntAccount) {
		if (inputVO == null) {
			return null;
		}

		AuthLevelInfoParamVO paramVO = new AuthLevelInfoParamVO();
    	BeanCopyUtil.nullToEmptyString(inputVO);
		BeanUtils.copyProperties(inputVO, paramVO);
		
		paramVO.setUserId(ntAccount);

		RestTemplate restTemplate = new RestTemplate();
		AuthLevelInfoOutputVO result = restTemplate.postForObject(OSPRESTFulWSUtil.GET_AUTH_LEVEL_INFO.getServiceUri(),
				paramVO, AuthLevelInfoOutputVO.class);

//		AuthLevelInfoOutputVO result = mockService.getAuthLevelInfo(paramVO);

		ResultHeader resultHeader = result.getResultHeader();
		String returnCode = resultHeader.getReturnCode();

		if (!StringUtils.equals(returnCode, ReturnCode.SUCCESS.getCode())) {
			logger.info("resultHeader: " + JsonUtil.toJson(resultHeader));
			
			return null;
		}

		AuthLevelInfoRtnVO resultBody = result.getResultBody();

		AuthLevelInfoReturnVO returnVO = new AuthLevelInfoReturnVO();
		BeanUtils.copyProperties(resultBody, returnVO);
    	BeanCopyUtil.nullToEmptyString(returnVO);

		return returnVO;
	}

    /**
     * 類型: 查詢. <br>
     * 根據門號取得 Cache Subscriber Info 資訊. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_CACHE_SUBSCRIBER_INFO_BY_MSISDN.getServiceUri()
     * 
     * @param msisdn 
     *          POST 請求參數 - 門號
     *          
     * @return CacheSubscriberInfoVO
     */
    public CacheSubscriberInfoVO getCacheSubscriberInfoByMsisdn(String msisdn, String ntAccount) {
    	if (StringUtils.isNotBlank(msisdn)) {
    		CacheSubscriberInfoParamVO paramVO = new CacheSubscriberInfoParamVO();
    		paramVO.setMsisdn(msisdn);
    		paramVO.setUserId(ntAccount);
    		
    		RestTemplate restTemplate = new RestTemplate();
    		CacheSubscriberInfoOutputVO result = restTemplate.postForObject(OSPRESTFulWSUtil.GET_CACHE_SUBSCRIBER_INFO_BY_MSISDN.getServiceUri(),
    				paramVO, CacheSubscriberInfoOutputVO.class);
    		
    		ResultHeader resultHeader = result.getResultHeader();
    		String returnCode = resultHeader.getReturnCode();
    		
    		if (ReturnCode.SUCCESS.getCode().equals(returnCode)) {
    			CacheSubscriberInfoVO cacheSubscriberInfoVO = new CacheSubscriberInfoVO();
    			
    			BeanUtils.copyProperties(result.getResultBody(), cacheSubscriberInfoVO);
    			
    			return cacheSubscriberInfoVO;
    		}
    	}
    	
		return null;
    }
	
	/**
	 * 類型: 查詢. <br>
	 * 
	 * [從ITT取得備註資訊]<br>
	 * 當前台(osp-platform)進行結案動作(有效件/無效件)時，且系統來源(from sourceSysId = 'ITT' )，
	 * 觸發前端呼叫此服務。透過此服務再次呼叫 ITT 系統所提供的API，將回傳的欄位組成字串，回傳前端。 <br>
	 * 做為前端存入OSP的工單備註欄位資訊. <br>
	 * 
	 * RESTful POST URI - OSPRESTFulWSUtil.GET_COMMENT_FROM_ITT.getServiceUri()
	 * 
	 * @param sourceOrderId
	 *            POST 請求參數 - 原始系統工單號
	 * @return StringOutputVO 回傳結果資訊封裝物件
	 */
	public String getCommentFromITT(String sourceOrderId) {
		if (StringUtils.isBlank(sourceOrderId)) {
			return "";
		}

		RestTemplate restTemplate = new RestTemplate();
		StringOutputVO result = restTemplate.postForObject(OSPRESTFulWSUtil.GET_COMMENT_FROM_ITT.getServiceUri(),
				sourceOrderId, StringOutputVO.class);

//		StringOutputVO result = mockService.getCommentFromITT(sourceOrderId);

		ResultHeader resultHeader = result.getResultHeader();
		String returnCode = resultHeader.getReturnCode();

		if (!StringUtils.equals(returnCode, ReturnCode.SUCCESS.getCode())) {
			return "";
		}

		return result.getResultBody();
	}

	/**
	 * 取得核資相關資訊(bill). <br>
	 * 
	 * @param custBillingInfoParamVO
	 * @return CustBillingInfoReturnVO
	 */
	public CustBillingInfoReturnVO getCustBillingInfo(CustBillingInfoVO inputVO, String ntAccount) {
		if (inputVO == null) {
			return new CustBillingInfoReturnVO();
		}

		CustBillingInfoParamVO paramVO = new CustBillingInfoParamVO();
		BeanUtils.copyProperties(inputVO, paramVO);
		
		paramVO.setUserId(ntAccount);

//		CustBillingInfoOutputVO result = mockService.getCustBillingInfo(paramVO);

		RestTemplate restTemplate = new RestTemplate();
		CustBillingInfoOutputVO result = restTemplate.postForObject(
				OSPRESTFulWSUtil.GET_CUST_BILLING_INFO.getServiceUri(), paramVO, CustBillingInfoOutputVO.class);

		ResultHeader resultHeader = result.getResultHeader();
		String returnCode = resultHeader.getReturnCode();

		if (ReturnCode.SUCCESS.getCode().equals(returnCode)) {
			CustBillingInfoRtnVO billingResult = result.getResultBody();
			
			if (billingResult != null) {
				List<com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.InvoiceInfoVO> billingInvoiceList = billingResult.getInvoiceInfoList();
				
				List<InvoiceInfoVO> invoiceInfoList = new ArrayList<>();
				
				if (CollectionUtils.isNotEmpty(billingInvoiceList)) {
					for (com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.InvoiceInfoVO billingInvoice : billingInvoiceList) {
						InvoiceInfoVO invoiceInfoVO = new InvoiceInfoVO();
						
						BeanUtils.copyProperties(billingInvoice, invoiceInfoVO);
						
						invoiceInfoList.add(invoiceInfoVO);
					}
				}
				
				CustBillingInfoReturnVO rtnVO = new CustBillingInfoReturnVO();
				
				BeanUtils.copyProperties(billingResult , rtnVO);
				
				rtnVO.setInvoiceInfoList(invoiceInfoList);

				return rtnVO;
			}
		}

		return null;
	}

	/**
	 * 類型: 查詢. <br>
	 * 取得 AppPart 啟動所需客資. <br>
	 * 
	 * RESTful POST URI -
	 * OSPRESTFulWSUtil.GET_CUST_INFO_FOR_APP_PART.getServiceUri()
	 * 
	 * @param custInfoForAppPartParamVO
	 *            POST 請求參數 - 資訊封裝物件
	 * @return CustInfoForAppPartOutputVO 回傳結果資訊封裝物件
	 */
	public CustInfoForAppPartReturnVO getCustInfoForAppPart(String ownId, String rocId, String idType, String msisdn) {
		if (StringUtils.isNotBlank(ownId) && StringUtils.isNotBlank(msisdn)) {
			CustInfoForAppPartParamVO request = new CustInfoForAppPartParamVO();
			request.setOwnId(ownId);
			request.setRocId(rocId);
			request.setIdType(idType);
			request.setMsisdn(msisdn);

			RestTemplate restTemplate = new RestTemplate();
			CustInfoForAppPartOutputVO result = restTemplate.postForObject(
					OSPRESTFulWSUtil.GET_CUST_INFO_FOR_APP_PART.getServiceUri(), request,
					CustInfoForAppPartOutputVO.class);

			ResultHeader resultHeader = result.getResultHeader();
			String returnCode = resultHeader.getReturnCode();

			if (ReturnCode.SUCCESS.getCode().equals(returnCode)) {
				CustInfoForAppPartReturnVO rtnVO = new CustInfoForAppPartReturnVO();

				BeanUtils.copyProperties(result.getResultBody(), rtnVO);

				return rtnVO;
			}
		}

		return null;
	}

	/**
	 * 取得核資相關資訊. <br>
	 * 
	 * @param accountId
	 * @param subscriberId
	 * @return CustInfoForOSPReturnVO
	 */
	public CustInfoForOSPReturnVO getCustInfoForOSP(String subscriberId, String ntAccount) {
		if (StringUtils.isNotBlank(subscriberId) && StringUtils.isNotBlank(ntAccount)) {
			CustInfoForOSPParamVO paramVO = new CustInfoForOSPParamVO();
			paramVO.setSubscriberId(subscriberId);
			paramVO.setUserId(ntAccount);
			
			RestTemplate restTemplate = new RestTemplate();
			CustInfoForOSPOutputVO result = restTemplate.postForObject(
					OSPRESTFulWSUtil.GET_CUST_INFO_FOR_OSP.getServiceUri(), paramVO, CustInfoForOSPOutputVO.class);
			
//			CustInfoForOSPOutputVO result = mockService.getCustInfoForOSP(paramVO);

			ResultHeader resultHeader = result.getResultHeader();
			String returnCode = resultHeader.getReturnCode();

			if (ReturnCode.SUCCESS.getCode().equals(returnCode)) {
				CustInfoForOSPReturnVO rtnVO = new CustInfoForOSPReturnVO();

				BeanUtils.copyProperties(result.getResultBody(), rtnVO);

				return rtnVO;
			}
		}

		return null;
	}

	/**
	 * 類型: 查詢. <br>
	 * 查詢/異動條件. <br>
	 * 
	 * RESTful POST URI - OSPRESTFulWSUtil.GET_AUTH_LEVEL_INFO.getServiceUri()
	 * 
	 * @param paramVO
	 *            POST 請求參數
	 * @return String
	 */
	public String getNEInfo(NEInfoVO neInfoVO, String ntAccount) {
		if (neInfoVO == null) {
			return "";
		}
		
		NEInfoParamVO neInfoParamVO = new NEInfoParamVO();
		BeanUtils.copyProperties(neInfoVO, neInfoParamVO);
		
		neInfoParamVO.setUserId(ntAccount);

		RestTemplate restTemplate = new RestTemplate();
		StringOutputVO result = restTemplate.postForObject(
				OSPRESTFulWSUtil.GET_NE_INFO.getServiceUri(), neInfoParamVO, StringOutputVO.class);

//		StringOutputVO result = mockService.getNEInfo(neInfoParamVO);

		ResultHeader resultHeader = result.getResultHeader();
		String returnCode = resultHeader.getReturnCode();

		if (!StringUtils.equals(returnCode, ReturnCode.SUCCESS.getCode())) {
			return "";
		}

		String resultBody = result.getResultBody();

		return resultBody;
	}

	/**
	 * 類型: 查詢. <br>
	 * 取得 NE Info. <br>
	 * 
	 * RESTful POST URI - OSPRESTFulWSUtil.GET_COMMENT_FROM_ITT.getServiceUri()
	 * 
	 * @param promotionId
	 *            POST 請求參數 - 原始系統工單號
	 * @return StringOutputVO 回傳結果資訊封裝物件
	 */
	public PromotionDetailReturnVO getPromotionDetail(String promotionId) {
		if (StringUtils.isBlank(promotionId)) {
			return null;
		}

		RestTemplate restTemplate = new RestTemplate();
		PromotionDetailOutputVO result = restTemplate.postForObject(
				OSPRESTFulWSUtil.GET_PROMOTION_DETAIL.getServiceUri(), promotionId, PromotionDetailOutputVO.class);

//		PromotionDetailOutputVO result = mockService.getPromotionDetail(promotionId);

		ResultHeader resultHeader = result.getResultHeader();
		String returnCode = resultHeader.getReturnCode();

		if (!StringUtils.equals(returnCode, ReturnCode.SUCCESS.getCode())) {
			return null;
		}
		
		PromotionDetailRtnVO promotionDetailRtnVO = result.getResultBody();
		PromotionDetailReturnVO promotionDetailReturnVO = new PromotionDetailReturnVO();
		BeanUtils.copyProperties(promotionDetailRtnVO, promotionDetailReturnVO);

		return promotionDetailReturnVO;
	}
	
	 /**
     * 類型: 查詢. <br>
     * 根據 IVR Code 取得業務人員 ID. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_USERID_BY_IVRCODE.getServiceUri()
     * 
     * @param ivrCode
     * @return String
     */
    public String getUserIdByIvrCode(String ivrCode) {
    	if (StringUtils.isNotBlank(ivrCode)) {
    		RestTemplate restTemplate = new RestTemplate();
    		StringOutputVO result = restTemplate.postForObject(
    				OSPRESTFulWSUtil.GET_USERID_BY_IVRCODE.getServiceUri(), ivrCode, StringOutputVO.class);
    		
//    		StringOutputVO result = mockService.getUserIdByIvrCode(ivrCode);
    		
    		ResultHeader resultHeader = result.getResultHeader();
			String returnCode = resultHeader.getReturnCode();

			if (ReturnCode.SUCCESS.getCode().equals(returnCode)) {
				return result.getResultBody();
			}
    	}
    	
    	return "";
    }

	/**
	 * 
	 * @param empNo
	 * @return String E-mail
	 */
	public String getSalesInfo(String empNo) {
		if (StringUtils.isBlank(empNo)) {
			return "";
		}

		String request = empNo;

		RestTemplate restTemplate = new RestTemplate();
		SalesOutputVO result = restTemplate.postForObject(OSPRESTFulWSUtil.QUERY_SALES_INFO.getServiceUri(), request,
				SalesOutputVO.class);

//		SalesOutputVO result = mockService.querySalesInfo(request);

		ResultHeader resultHeader = result.getResultHeader();
		String returnCode = resultHeader.getReturnCode();

		if (ReturnCode.SUCCESS.getCode().equals(returnCode)) {
			return result.getResultBody().getEmail();
		}

		return "";
	}

	/**
	 * 類型: 查詢. <br>
	 * 取得 SOA 進件詳細資訊. <br>
	 * 
	 * RESTful POST URI - OSPRESTFulWSUtil.GET_SOA_TICKET_DETAIL.getServiceUri()
	 * 
	 * @param msisdns
	 *            POST 請求參數 - 門號
	 * @return List<SOATicketDetailRtnVO>
	 */
	public List<SOATicketDetailRtnVO> getSOATicketDetail(List<String> msisdns, String ntAccount) {
		SOATicketDetailParamVO paramVO = new SOATicketDetailParamVO();
		paramVO.setMsisdnList(msisdns);
		paramVO.setUserId(ntAccount);
		
		RestTemplate restTemplate = new RestTemplate();
		SOATicketDetailOutputVO result = restTemplate.postForObject(
				OSPRESTFulWSUtil.GET_SOA_TICKET_DETAIL.getServiceUri(), paramVO, SOATicketDetailOutputVO.class);

//		SOATicketDetailOutputVO result = mockService.getSOATicketDetail(paramVO);

		ResultHeader resultHeader = result.getResultHeader();
		String returnCode = resultHeader.getReturnCode();
		
		if (StringUtils.equals(returnCode, ReturnCode.PARAM_ERROR.getCode())) {
            logger.info("returnCode: " + returnCode);
            throw new RuntimeException(ReturnCode.PARAM_ERROR.getMessage());
        }

		if (!StringUtils.equals(returnCode, ReturnCode.SUCCESS.getCode())) {
			logger.info("returnCode: " + returnCode);
			
			return Collections.emptyList();
		}

		List<SOATicketDetailRtnVO> resultBody = result.getResultBody();

		return resultBody;
	}

	/**
	 * 取得溫暖長青相關資訊. <br>
	 * 
	 * @param rocId
	 * @return SpecialOfferRtnVO
	 */
	public List<SpecialOfferVO> getSpecialOfferByRocId(String rocId, String ntAccount) {
		if (StringUtils.isNotBlank(rocId)) {
			SpecialOfferParamVO paramVO = new SpecialOfferParamVO();
			paramVO.setRocId(rocId);
			paramVO.setUserId(ntAccount);
			
			RestTemplate restTemplate = new RestTemplate();
			SpecialOfferOutputVO result = restTemplate.postForObject(
					OSPRESTFulWSUtil.GET_SPECIAL_OFFER_BY_ROCID.getServiceUri(), paramVO, SpecialOfferOutputVO.class);
			
//			SpecialOfferOutputVO result = mockService.getSpecialOfferByRocId(paramVO);

			ResultHeader resultHeader = result.getResultHeader();
			String returnCode = resultHeader.getReturnCode();

			if (StringUtils.equals(returnCode, ReturnCode.SUCCESS.getCode())) {
				List<SpecialOfferRtnVO> resultBody = result.getResultBody();
				List<SpecialOfferVO> rtnVOList = new ArrayList<SpecialOfferVO>();

				for (int i = 0; i < resultBody.size(); i++) {
					SpecialOfferVO specialOfferVO = new SpecialOfferVO();
					rtnVOList.add(specialOfferVO);
				}

				for (int i = 0; i < resultBody.size(); i++) {
					BeanUtils.copyProperties(resultBody.get(i), rtnVOList.get(i));
				}

				return rtnVOList;
			}
		}

		return Collections.emptyList();
	}

	public boolean updateOrderStatus2TicketPool(String poolKey, String status, String processUser, String ntAccount) {
		OrderParamVO request = new OrderParamVO();
		request.setPoolKey(poolKey);
		request.setStatus(status);
		request.setProcessUser(processUser);
		request.setUserId(ntAccount);

		RestTemplate restTemplate = new RestTemplate();
		OrderUpdateOutputVO result = restTemplate.postForObject(
				OSPRESTFulWSUtil.UPDATE_ORDER_STATUS_TO_TICKETPOOL.getServiceUri(), request, OrderUpdateOutputVO.class);

//		OrderUpdateOutputVO result = mockService.updateOrderStatus2TicketPool(request);

		ResultHeader resultHeader = result.getResultHeader();
		String returnCode = resultHeader.getReturnCode();

		if (ReturnCode.SUCCESS.getCode().equals(returnCode)) {
			return true;
		}

		return false;
	}

    /**
     * 類型: 資料異動. <br>
     * 將各OSP系統建檔抛轉進來的工單再次處理，並寫入TicketPool[ORDER_MAIN_METADATA]. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.SYNC_ORDER_TO_TICKET_POOL_FROM_OSP.getServiceUri()
     * 
     * @param OrderLoadVO 
     *          POST 請求參數
     *          
     * @return OrderLoadReturnVO 回傳結果資訊封裝物件
     */
	public OrderLoadReturnVO syncOrder2TicketPoolFromOSP(OrderLoadVO paramVO) {
		if (paramVO == null) {
			return new OrderLoadReturnVO();
		}
		
		OrderLoadParam orderLoadParam = new OrderLoadParam();
		BeanUtils.copyProperties(paramVO, orderLoadParam);

		RestTemplate restTemplate = new RestTemplate();
		OrderLoadOutputVO result = restTemplate.postForObject(
				OSPRESTFulWSUtil.SYNC_ORDER_TO_TICKET_POOL_FROM_OSP.getServiceUri(), orderLoadParam, OrderLoadOutputVO.class);

		ResultHeader resultHeader = result.getResultHeader();
		String returnCode = resultHeader.getReturnCode();

		if (!StringUtils.equals(returnCode, ReturnCode.SUCCESS.getCode())) {
			return new OrderLoadReturnVO();
		}

		OrderLoadRtnVO body = result.getResultBody();
		OrderLoadReturnVO orderLoadReturnVO = new OrderLoadReturnVO();
		BeanUtils.copyProperties(body, orderLoadReturnVO);

		return orderLoadReturnVO;
	}

	/**
	 * 
	 * @param paymentCategory
	 * @param msisdn
	 * 
	 * @return String sim
	 */
	public String getSimIdByMsisdn(String paymentCategory, String msisdn, String ntAccount) {
		if (StringUtils.isBlank(paymentCategory) || StringUtils.isBlank(msisdn)) {
			return "";
		}

		SimIdParamVO simIdParamVO = new SimIdParamVO();
		simIdParamVO.setPaymentCategory(paymentCategory);
		simIdParamVO.setMsisdn(msisdn);
		simIdParamVO.setUserId(ntAccount);

		RestTemplate restTemplate = new RestTemplate();
		StringOutputVO result = restTemplate.postForObject(OSPRESTFulWSUtil.GET_SIMID_BY_MSISDN.getServiceUri(), simIdParamVO,
				StringOutputVO.class);
		
//		StringOutputVO result = mockService.getSimIdByMsisdn(simIdParamVO);

		ResultHeader resultHeader = result.getResultHeader();
		String returnCode = resultHeader.getReturnCode();

		if (ReturnCode.SUCCESS.getCode().equals(returnCode)) {
			return result.getResultBody();
		}

		return "";
	}

	/**
	 * 證號歷史查詢. <br>
	 * 
	 * @param rocId
	 * @return 
	 */
	public List<IdViewInfoVO> getIdViewInfoByRocId(String rocId, String ntAccount) {
		if (StringUtils.isNotBlank(rocId)) {
			IdViewInfoParamVO paramsVO = new IdViewInfoParamVO();
			paramsVO.setRocId(rocId);
			paramsVO.setUserId(ntAccount);
			
			RestTemplate restTemplate = new RestTemplate();
			IdViewInfoOutputVO result = restTemplate.postForObject(
					OSPRESTFulWSUtil.GET_IDVIEW_INFO_BY_ROCID.getServiceUri(), paramsVO, IdViewInfoOutputVO.class);
			
//			IdViewInfoOutputVO result = mockService.getIdViewInfoByRocId(rocId);

			ResultHeader resultHeader = result.getResultHeader();
			String returnCode = resultHeader.getReturnCode();

			if (StringUtils.equals(returnCode, ReturnCode.SUCCESS.getCode())) {
				IdViewInfoRtnVO resultBody = result.getResultBody();

				// 無筆數就回傳emptyList
				if (resultBody.getTotalCount() > 0) {
					List<IdViewInfoVO> rtnVOList = new ArrayList<IdViewInfoVO>();

					for (int i = 0; i < resultBody.getIdViewInfoList().size(); i++) {
						IdViewInfoVO idViewInfoVO = new IdViewInfoVO();
						rtnVOList.add(idViewInfoVO);
					}

					for (int i = 0; i < resultBody.getIdViewInfoList().size(); i++) {
						BeanUtils.copyProperties(resultBody.getIdViewInfoList().get(i), rtnVOList.get(i));
					}

					return rtnVOList;
				}
			}
		}

		return Collections.emptyList();
	}

}