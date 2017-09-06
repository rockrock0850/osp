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
package com.fet.crm.osp.common.mock;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fet.crm.osp.common.code.kernel.ReturnCode;
import com.fet.crm.osp.common.util.IdentifierIdUtil;
import com.fet.crm.osp.common.util.ResourceFileUtil;
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
import com.fet.crm.osp.common.vo.kernel.result.CacheSubscriberInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.CustInfoForAppPartRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.OrderLoadRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.OrderUpdateRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.SOATicketDetailRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.SalesInfoVO;
import com.fet.crm.osp.common.vo.kernel.result.SpecialOfferRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.agentinfo.AgentInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.agentinfo.GroupDefinitionVO;
import com.fet.crm.osp.common.vo.kernel.result.agentinfo.InternaluseraccountVO;
import com.fet.crm.osp.common.vo.kernel.result.agentinfo.StaffVO;
import com.fet.crm.osp.common.vo.kernel.result.agentinfo.UsersVO;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustBillingInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustInfoForOSPRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.cie.AddCIEMasterRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.idviewinfo.IdViewInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.promotiondetail.DiscountOfferVO;
import com.fet.crm.osp.common.vo.kernel.result.promotiondetail.PromotionDetailRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.promotiondetail.VasOfferVO;

/**
 * OSP RESTful Web Services API 單一窗口<br>
 * 僅作為開發階段使用<br>
 * 
 * @author RichardHuang
 */
@Service
public class MockService {
    
    private String pattern = "{0} ({1})";
    
    /**
     * 類型: 查詢. <br>
     * 透過業務人員代碼取得業務人員相關資訊(E-Mail). <br>
     * 查詢CEDS(eHR). <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.QUERY_SALES_INFO.getServiceUri()
     * 
     * @param empNo POST 請求參數 - 業務人員員工代碼
     * @return SalesOutputVO 回傳結果資訊封裝物件
     */
    public SalesOutputVO querySalesInfo(String empNo) {
        SalesOutputVO outputVO = new SalesOutputVO();
        
        try {
            Assert.hasText(empNo, "empNo must not be null, empty, or blank");
            
            SalesInfoVO result = new SalesInfoVO();
            result.setEmpNo("1111");
            result.setEmail("test@fareastone.com.tw");
            
            Assert.state(result != null);
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (IllegalStateException ex) {
            outputVO.setResultHeader(getNoDataResultHeader());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }
    
    /**
     * 類型: 查詢. <br>
     * 根據 IVR Code 取得業務人員 ID. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_USERID_BY_IVRCODE.getServiceUri()
     * 
     * @param ivrCode
     * @return
     */
    public StringOutputVO getUserIdByIvrCode(String ivrCode) {
        StringOutputVO outputVO = new StringOutputVO();
        
        try {
            Assert.hasText(ivrCode, "ivrCode must not be null, empty, or blank");
            
            String result = "72120";
            
            Assert.state(result != null);
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (IllegalStateException ex) {
            outputVO.setResultHeader(getNoDataResultHeader());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
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
     * RESTful POST URI - OSPRESTFulWSUtil.GET_COMMENT_FROM_ITT.getServiceUri()
     * 
     * @param sourceOrderId POST 請求參數
     * @return StringOutputVO 回傳結果資訊封裝物件
     */
    public StringOutputVO getCommentFromITT(String sourceOrderId) {
        StringOutputVO outputVO = new StringOutputVO();
        
        try {
            Assert.hasText(sourceOrderId, "sourceOrderId must not be null, empty, or blank");
            
            String result = "備註資訊測試";
            
            Assert.state(result != null);
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (IllegalStateException ex) {
            outputVO.setResultHeader(getNoDataResultHeader());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }

    /**
     * 類型: 查詢. <br>
     * 取得 AppPart 啟動所需客資. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_CUST_INFO_FOR_APP_PART.getServiceUri()
     * 
     * @param custInfoForAppPartParamVO
     *              POST 請求參數 - 資訊封裝物件
     * @return CustInfoForAppPartOutputVO
     *              回傳結果資訊封裝物件
     */
    public CustInfoForAppPartOutputVO getCustInfoForAppPart(CustInfoForAppPartParamVO custInfoForAppPartParamVO) {
        CustInfoForAppPartOutputVO outputVO = new CustInfoForAppPartOutputVO();
        
        try {
            String ownId = custInfoForAppPartParamVO.getOwnId();
            String msisdn = custInfoForAppPartParamVO.getMsisdn();
            
            Assert.hasText(ownId, "ownId must not be null, empty, or blank");
            Assert.hasText(msisdn, "msisdn must not be null, empty, or blank");
            
            CustInfoForAppPartRtnVO result = new CustInfoForAppPartRtnVO();
            result.setAcctId("100148563");
            result.setArpb("0.0");
            result.setCancelDate(null);
            result.setCancelDate_str(null);
            result.setFirstName(null);
            result.setGsmStatus("A");
            result.setGsmStatusDesc("正常使用中");
            result.setGsmStaus_order("1");
            result.setIdType(0);
            result.setInitActiveDt("2000/12/20");
            result.setLastName("黃*照");
            result.setLastSubStActivity("NEW_SUB_ACTIVATION");
            result.setLastSubStDt("2000/12/20");
            result.setMsisdn("0926636087");
            result.setPaymentCategory("PS");
            result.setPrefixNameTpCd(null);
            result.setProductType("遠傳2G月租");
            result.setRocId(null);
            result.setSim(null);
            result.setStartDate(null);
            result.setStartDate_str("2000/12/20");
            result.setSubStReason("2NT");
            result.setSubStReasonDscr("I-網路啟用");
            result.setSubscribeId("305239767");
            result.setSubscriberType("G");
            
            Assert.state(result != null);
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (IllegalStateException ex) {
            outputVO.setResultHeader(getNoDataResultHeader());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }

    /**
     * 類型: 查詢. <br>
     * 取得 OSP 核資客戶相關資訊. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_CUST_INFO_FOR_OSP.getServiceUri()
     * 
     * @param custInfoForOSPParamVO
     *              POST 請求參數 - 資訊封裝物件
     * @return CustInfoForOSPOutputVO
     *              核資回傳結果資訊封裝物件
     */
    public CustInfoForOSPOutputVO getCustInfoForOSP(CustInfoForOSPParamVO custInfoForOSPParamVO) {
        CustInfoForOSPOutputVO outputVO = new CustInfoForOSPOutputVO();
        
        try {
        	Assert.hasText(custInfoForOSPParamVO.getUserId(), "userId must not be null, empty, or blank");
            Assert.hasText(custInfoForOSPParamVO.getSubscriberId(), "subscriberId must not be null, empty, or blank");
            
            CustInfoForOSPRtnVO result = new CustInfoForOSPRtnVO();
            
            Assert.state(result != null);
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (IllegalStateException ex) {
            outputVO.setResultHeader(getNoDataResultHeader());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }

    /**
     * 類型: 查詢. <br>
     * 取得帳務相關資訊. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_CUST_BILLING_INFO.getServiceUri()
     * 
     * @param custBillingInfoParamVO 
     *              POST 請求參數 - 資訊封裝物件
     *              
     * @return CustBillingInfoOutputVO
     *              回傳資訊封裝物件
    */
    public CustBillingInfoOutputVO getCustBillingInfo(CustBillingInfoParamVO custBillingInfoParamVO) {
        CustBillingInfoOutputVO outputVO = new CustBillingInfoOutputVO();
        
        String userId = custBillingInfoParamVO.getUserId();
        String accountId = custBillingInfoParamVO.getAccountId();
        String subscriberId = custBillingInfoParamVO.getSubscriberId();
        
        try {
        	Assert.hasText(userId, "userId must not be null, empty, or blank");
            Assert.hasText(accountId, "accountId must not be null, empty, or blank");
            Assert.hasText(subscriberId, "subscriberId must not be null, empty, or blank");
            
            CustBillingInfoRtnVO result = new CustBillingInfoRtnVO();
            
            Assert.state(result != null);
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (IllegalStateException ex) {
            outputVO.setResultHeader(getNoDataResultHeader());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }

    /**
     * 類型：查詢. <br>
     * 根據使用者 ntAccountId，取得 使用者登入相關資訊. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_AGENT_INFO.getServiceUri(). <br>
     * 
     * @param ntAccountId
     * @return AgentInfoOutputVO
     */
    public AgentInfoOutputVO getAgentInfo(String ntAccountId) {
        AgentInfoOutputVO outputVO = new AgentInfoOutputVO();
        
        try {
            Assert.hasText(ntAccountId, "ntAccountId must not be null, empty, or blank");
            
            InternaluseraccountVO internaluseraccountVO = new InternaluseraccountVO();
            internaluseraccountVO.setUserid("60564");
            internaluseraccountVO.setName("李素瑜");
            internaluseraccountVO.setEnglishname("Anita Lee");
            internaluseraccountVO.setPassword("kGpYx/9cYRI=");
            internaluseraccountVO.setEmail("sylee@fareastone.com.tw");
            internaluseraccountVO.setStatus("locked");
            internaluseraccountVO.setIsPasswordLocked("N");
            internaluseraccountVO.setPosition("協理");
            internaluseraccountVO.setAgentcode(null);
            
            StaffVO staffVO = new StaffVO();
            staffVO.setNtdomainAccountId("sylee");
            staffVO.setAmdocsId("SIT01_02");
            staffVO.setStaffName("Lee, Anita 李素瑜 (15953)");
            staffVO.setEmailAddress("sylee@fareastone.com.tw");
            staffVO.setGroupNumber(new BigDecimal(900000242));
            staffVO.setLogname("sylee");
            
            UsersVO usersVO = new UsersVO();
            usersVO.setUserId(new BigDecimal(12824));
            usersVO.setUserLoginName("SIT01_02");
            usersVO.setUserPassword("IT01_21A");
            usersVO.setUserEmplId("Z0003");
            
            GroupDefinitionVO groupDefinitionVO = new GroupDefinitionVO();
            groupDefinitionVO.setGroupNumber(new BigDecimal(900000242));
            groupDefinitionVO.setGroupName("801-IT-All-系統管理");
            groupDefinitionVO.setManagerId(null);
            groupDefinitionVO.setPrivilegeLevel(null);
            groupDefinitionVO.setDefaultFlag(null);
            groupDefinitionVO.setStatus(new BigDecimal(1));
            groupDefinitionVO.setGroupDesc("801-IT-All-系統管理");
            
            AgentInfoRtnVO result = new AgentInfoRtnVO();
            result.setInternaluseraccountVO(internaluseraccountVO);
            result.setStaffVO(staffVO);
            result.setUsersVO(usersVO);
            result.setGroupDefinitionVO(groupDefinitionVO);
            result.setCacheChannel("7000");
            result.setCacheChannelIdPasswordPostpaid(null);
            result.setCacheChannelIdPasswordPrepaid(null);
            result.setCacheChannelIdPostpaid(null);
            result.setCacheChannelIdPrepaid(null);
            result.setCacheChannelPostpaid(null);
            result.setCacheChannelPrepaid(null);
            result.setIvrcode(null);
            result.setChannelGroupId("00001");
             
            Assert.state(result != null);
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (IllegalStateException ex) {
            outputVO.setResultHeader(getNoDataResultHeader());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }

    /**
     * 類型: 查詢. <br>
     * 取得 SOA 進件詳細資訊. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_SOA_TICKET_DETAIL.getServiceUri()
     * 
     * @param msisdnList
     *              POST 請求參數 - 門號清單
     * @return SOATicketDetailOutputVO
     *              回傳結果資訊封裝物件
     */
    public SOATicketDetailOutputVO getSOATicketDetail(List<String> msisdnList) {
        SOATicketDetailOutputVO outputVO = new SOATicketDetailOutputVO();
        
        try {
            Assert.notEmpty(msisdnList, "msisdnList must not be empty");
            
            List<SOATicketDetailRtnVO> result = new ArrayList<SOATicketDetailRtnVO>();
            
            int custID = 100000000;
            
            for (String msisdn : msisdnList) {
                SOATicketDetailRtnVO soaTicketInfoVO = new SOATicketDetailRtnVO();
                soaTicketInfoVO.setTicketNo(IdentifierIdUtil.getUuid());
                soaTicketInfoVO.setMsisdn(msisdn);
                soaTicketInfoVO.setCustID(String.valueOf(custID++));
                soaTicketInfoVO.setRecipientID(null);
                soaTicketInfoVO.setDornorID(null);
                soaTicketInfoVO.setFID(null);
                soaTicketInfoVO.setContractDate(null);
                soaTicketInfoVO.setState(null);
                
                result.add(soaTicketInfoVO);
            }
            
            Assert.state(result != null);
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (IllegalStateException ex) {
            outputVO.setResultHeader(getNoDataResultHeader());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }

    /**
     * 類型: 查詢. <br>
     * 取得簽核層級與人員資訊. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_AUTH_LEVEL_INFO.getServiceUri()
     * 
     * @param authLevelInfoParamVO
     *              POST 請求參數
     * @return AuthLevelOutputVO
     *              回傳結果資訊封裝物件
     */
    public AuthLevelInfoOutputVO getAuthLevelInfo(AuthLevelInfoParamVO authLevelInfoParamVO) {
        AuthLevelInfoRtnVO result = new AuthLevelInfoRtnVO();
        result.setAuthLevel("4"); // 特授層級
        result.setEmpId("60258"); // 簽核者的員工代碼
        result.setEmail("ssung@fareastone.com.tw"); // 簽核者的email
        result.setName("宋玉海"); // 簽核者的name
        result.setSms("0936105529"); // 簽核者的sms
        result.setLevel("L2"); // 層級
        result.setLevelDesc("L2"); // 職稱
        
        AuthLevelInfoOutputVO outputVO = new AuthLevelInfoOutputVO();
        outputVO.setResultHeader(getSuccessResultHeader());
        outputVO.setResultBody(result);
        
        return outputVO;
    }

    /**
     * 類型: 查詢. <br>
     * 取得學生溫暖長青申請方案相關資訊. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_SPECIAL_OFFER_BY_ROCID.getServiceUri()
     * 
     * @param specialOfferParamVO 
     *          POST 請求參數
     *          
     * @return SpecialOfferOutputVO 回傳結果資訊封裝物件
     */
    public SpecialOfferOutputVO getSpecialOfferByRocId(SpecialOfferParamVO specialOfferParamVO) {
        SpecialOfferOutputVO outputVO = new SpecialOfferOutputVO();
        
        try {
        	Assert.hasText(specialOfferParamVO.getUserId(), "userId must not be null, empty, or blank");
            Assert.hasText(specialOfferParamVO.getRocId(), "rocId must not be null, empty, or blank");
            
            List<SpecialOfferRtnVO> result = new ArrayList<>();
            
            SpecialOfferRtnVO rtnVO = new SpecialOfferRtnVO();
            rtnVO.setOfferId("120823");
            rtnVO.setOfferName("POS3RV 學生價優惠專案月租");
            rtnVO.setMsisdn("0913747374");
            rtnVO.setStartDate("2011-08-12 09:33:13");
            rtnVO.setEndDate("2011-08-12 09:34:54");
            
            result.add(rtnVO);
            
            Assert.state(result != null);
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (IllegalStateException ex) {
            outputVO.setResultHeader(getNoDataResultHeader());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }

    /**
     * 類型: 查詢. <br>
     * 取得促代相關資訊. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_PROMOTION_DETAIL.getServiceUri()
     * 
     * @param promotionId 
     *              POST 請求參數 - 原始系統工單號
     *              
     * @return PromotionDetailOutputVO 回傳結果資訊封裝物件
     */
    public PromotionDetailOutputVO getPromotionDetail(String promotionId) {
        PromotionDetailOutputVO outputVO = new PromotionDetailOutputVO();
        
        try {
            Assert.hasText(promotionId, "promotionId must not be null, empty, or blank");
            
            PromotionDetailRtnVO result = new PromotionDetailRtnVO();
            
            String attach = ResourceFileUtil.TXT_MOCK.getResource("promotion-attach");
            attach = (StringUtils.isBlank(attach)) ? "" : attach;
            result.setAttach(attach);
            
            result.setPromotionCode("GOMLIEPUYEY3");
            result.setName("4G續約-A-C超值專案A590限24+Smart 550限24手機-預繳12000");
            result.setPromotionCategoryName("3G續約-未上線專案");
            result.setPromotionType("L1");
            result.setStartDate("2017/05/01 00:00:00");
            result.setEndDate("2019/05/01 00:00:00");
            result.setMsisdnType("4G");
            
            List<DiscountOfferVO> discountOfferList = new ArrayList<>();
            
            DiscountOfferVO discountOfferVO = new DiscountOfferVO();
            discountOfferVO.setId("124294");
            discountOfferVO.setName("POS DB 攜碼手續費優惠240元");
            discountOfferVO.setHappyGo(0);
            
			discountOfferList.add(discountOfferVO);
            
			result.setDiscountOfferList(discountOfferList);
            
            List<VasOfferVO> vasOfferList = new ArrayList<VasOfferVO>();
            
            VasOfferVO vasOffer1 = new VasOfferVO();
            vasOffer1.setId("142225");
            vasOffer1.setName("POS3RC Smart550月租費");
            
            VasOfferVO vasOffer2 = new VasOfferVO();
            vasOffer2.setId("142226");
            vasOffer2.setName("POS3RC Smart750月租費");
            
            vasOfferList.add(vasOffer1);
            vasOfferList.add(vasOffer2);
            
            result.setVasOfferList(vasOfferList);
            
            Assert.state(result != null);
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (IllegalStateException ex) {
            outputVO.setResultHeader(getNoDataResultHeader());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }

    /**
     * 類型: 查詢. <br>
     * 取得 NE Info. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_NE_INFO.getServiceUri()
     * 
     * @param neInfoParamVO 
     *              POST 請求參數 - 原始系統工單號
     *              
     * @return StringOutputVO 回傳結果資訊封裝物件
     */
    public StringOutputVO getNEInfo(NEInfoParamVO neInfoParamVO) {
        StringOutputVO outputVO = new StringOutputVO();
        
        try {
//        	String paymentCategory = neInfoParamVO.getPaymentCategory();
//            String msisdn = neInfoParamVO.getMsisdn();
//            String accountName = neInfoParamVO.getAccountName();
//            String subType = neInfoParamVO.getSubType();
//            Assert.hasText(paymentCategory, "paymentCategory must not be null, empty, or blank");
//            Assert.hasText(msisdn, "msisdn must not be null, empty, or blank");
//            Assert.hasText(accountName, "accountName must not be null, empty, or blank");
//            Assert.hasText(subType, "subType must not be null, empty, or blank");
            
            String result = ResourceFileUtil.TXT_MOCK.getResource("neinfo");
            Assert.state(result != null);
            
            int startIndex = StringUtils.indexOf(result, "APNID");
            Assert.state(startIndex != -1);
            
            result = StringUtils.substring(result, startIndex, result.lastIndexOf("END"));
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (IllegalStateException ex) {
            outputVO.setResultHeader(getNoDataResultHeader());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }

    /**
     * 類型: 查詢. <br>
     * 取得 SIM 卡號. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_SIMID_BY_MSISDN.getServiceUri()
     * 
     * @param simIdParamVO 
     *              POST 請求參數 - 資訊封裝物件
     *              
     * @return StringOutputVO
     *              回傳資訊封裝物件
    */
    public StringOutputVO getSimIdByMsisdn(SimIdParamVO simIdParamVO) {
        StringOutputVO outputVO = new StringOutputVO();
        
        String userId = simIdParamVO.getUserId();
        String paymentCategory = simIdParamVO.getPaymentCategory();
        String msisdn = simIdParamVO.getMsisdn();
        
        try {
        	Assert.hasText(userId, "userId must not be null, empty, or blank");
            Assert.hasText(paymentCategory, "paymentCategory must not be null, empty, or blank");
            Assert.hasText(msisdn, "msisdn must not be null, empty, or blank");
            
            String result = "SIM-ID-TEST";
            
            Assert.state(result != null);
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (IllegalStateException ex) {
            outputVO.setResultHeader(getNoDataResultHeader());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }

    /**
     * 類型: 查詢. <br>
     * 根據門號取得 Cache Subscriber Info 資訊. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_CACHE_SUBSCRIBER_INFO_BY_MSISDN.getServiceUri()
     * 
     * @param cacheSubscriberInfoParamVO 
     *          POST 請求參數
     *          
     * @return CacheSubscriberInfoOutputVO 回傳結果資訊封裝物件
     */
    public CacheSubscriberInfoOutputVO getCacheSubscriberInfoByMsisdn(CacheSubscriberInfoParamVO cacheSubscriberInfoParamVO) {
        CacheSubscriberInfoOutputVO outputVO = new CacheSubscriberInfoOutputVO();
        
        try {
        	Assert.hasText(cacheSubscriberInfoParamVO.getUserId(), "userId must not be null, empty, or blank");
            Assert.hasText(cacheSubscriberInfoParamVO.getMsisdn(), "msisdn must not be null, empty, or blank");
            
            CacheSubscriberInfoRtnVO result = new CacheSubscriberInfoRtnVO();
            
            Assert.state(result != null);
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (IllegalStateException ex) {
            outputVO.setResultHeader(getNoDataResultHeader());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }
    
    /**
     * 類型: 查詢. <br>
     * 根據證號取得客資歷史資訊. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.GET_IDVIEW_INFO_BY_ROCID.getServiceUri()
     * 
     * @param idViewInfoParamVO 
     *          POST 請求參數
     *          
     * @return IdViewInfoOutputVO 回傳結果資訊封裝物件
     */
    public IdViewInfoOutputVO getIdViewInfoByRocId(IdViewInfoParamVO idViewInfoParamVO) {
    	IdViewInfoOutputVO outputVO = new IdViewInfoOutputVO();
        
        try {
        	Assert.hasText(idViewInfoParamVO.getUserId(), "userId must not be null, empty, or blank");
            Assert.hasText(idViewInfoParamVO.getRocId(), "rocId must not be null, empty, or blank");
            
            IdViewInfoRtnVO result = new IdViewInfoRtnVO();
            
            Assert.state(result != null);
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (IllegalStateException ex) {
            outputVO.setResultHeader(getNoDataResultHeader());
            
        } catch (Exception ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }

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
    public AddCIEMasterOutputVO addCIEMaster(AddCIEMasterParamVO addCIEMasterParamVO) {
        AddCIEMasterOutputVO outputVO = new AddCIEMasterOutputVO();
        
        try {
            AddCIEMasterRtnVO result = new AddCIEMasterRtnVO();
            
            Assert.state(result != null);
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }
    
    /**
     * 類型: 資料異動. <br>
     * 更新工單資訊(狀態/處理人員)至TicketPool[ORDER_MAIN_METADATA]. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.UPDATE_ORDER_STATUS_TO_TICKETPOOL.getServiceUri()
     * 
     * @param orderParamVO POST 請求參數 - 資訊封裝物件
     * @param outputVOType 回傳結果資訊封裝 class type
     * @return OrderUpdateOutputVO 回傳結果資訊封裝物件
     */
    public OrderUpdateOutputVO updateOrderStatus2TicketPool(OrderParamVO orderParamVO) {
        OrderUpdateOutputVO outputVO = new OrderUpdateOutputVO();
        
        try {
            String poolKey = orderParamVO.getPoolKey();
            String status = orderParamVO.getStatus();
            String processUser = orderParamVO.getProcessUser();
            
            Assert.hasText(poolKey, "poolKey must not be null, empty, or blank");
            Assert.hasText(status, "status must not be null, empty, or blank");
            Assert.hasText(processUser, "processUser must not be null, empty, or blank");
            
            OrderUpdateRtnVO result = new OrderUpdateRtnVO();
            result.setNtAccount("ychou");
            result.setPoolKey(poolKey);
            result.setSourceOrderId(IdentifierIdUtil.getUuid());
            result.setSourceSysId("FaxServer");
            
            Assert.state(result != null);
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);
            
        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (IllegalStateException ex) {
            outputVO.setResultHeader(getNoDataResultHeader());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }

    /**
     * 類型: 資料異動. <br>
     * 將各OSP系統建檔抛轉進來的工單再次處理，並寫入TicketPool[ORDER_MAIN_METADATA]. <br>
     * 
     * RESTful POST URI - OSPRESTFulWSUtil.SYNC_ORDER_TO_TICKET_POOL_FROM_OSP.getServiceUri()
     * 
     * @param orderLoadParam 
     *          POST 請求參數
     *          
     * @return OrderLoadOutputVO 回傳結果資訊封裝物件
     */
    public OrderLoadOutputVO syncOrder2TicketPoolFromOSP(OrderLoadParam orderLoadParam) {
        OrderLoadOutputVO outputVO = new OrderLoadOutputVO();
        
        try {
            // 驗證輸入參數 : orderLoadParam 不允許 null
            Assert.notNull(orderLoadParam);
            
            OrderLoadRtnVO result = new OrderLoadRtnVO();
            result.setPoolKey("test");
            
            outputVO.setResultHeader(getSuccessResultHeader());
            outputVO.setResultBody(result);

        } catch (IllegalArgumentException ex) {
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.PARAM_ERROR, ex));
            
        } catch (Exception ex) {
            ex.printStackTrace();
            outputVO.setResultHeader(getFailResultHeader(ReturnCode.ERROR, ex));
        }
        
        return outputVO;
    }
    
    protected ResultHeader getFailResultHeader(ReturnCode returnCode, Exception ex) {
        ResultHeader resultHeader = new ResultHeader();
        resultHeader.setReturnCode(returnCode.getCode());
        String message = MessageFormat.format(pattern, returnCode.getMessage(), ex.getMessage());
        resultHeader.setReturnMessage(message);
        
        return resultHeader;
    }
    
    protected ResultHeader getSuccessResultHeader() {
        ResultHeader resultHeader = new ResultHeader();
        resultHeader.setReturnCode(ReturnCode.SUCCESS.getCode());
        resultHeader.setReturnMessage(ReturnCode.SUCCESS.getMessage());
        
        return resultHeader;
    }
    
    protected ResultHeader getNoDataResultHeader() {
        ResultHeader resultHeader = new ResultHeader();
        resultHeader.setReturnCode(ReturnCode.NO_DATA.getCode());
        resultHeader.setReturnMessage(ReturnCode.NO_DATA.getMessage());
        
        return resultHeader;
    }
    
}
