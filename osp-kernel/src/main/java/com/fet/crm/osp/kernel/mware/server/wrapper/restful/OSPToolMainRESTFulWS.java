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

package com.fet.crm.osp.kernel.mware.server.wrapper.restful;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fet.crm.osp.common.code.kernel.ReturnCode;
import com.fet.crm.osp.common.vo.kernel.input.param.AddCIEMasterParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.AuthLevelInfoParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.CacheSubscriberInfoParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.CustBillingInfoParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.CustInfoForAppPartParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.CustInfoForOSPParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.IdViewInfoParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.NEInfoParamVO;
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
import com.fet.crm.osp.common.vo.kernel.result.SOATicketDetailRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.SalesInfoVO;
import com.fet.crm.osp.common.vo.kernel.result.SpecialOfferRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.agentinfo.AgentInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustBillingInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustInfoForOSPRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.cie.AddCIEMasterRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.idviewinfo.IdViewInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.promotiondetail.PromotionDetailRtnVO;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.mware.server.delegate.OSPToolMainDelegate;
import com.fet.crm.osp.kernel.mware.server.vo.process.ExContext;
import com.fet.crm.osp.kernel.mware.server.vo.process.req.ReqBody;
import com.fet.crm.osp.kernel.mware.server.vo.process.req.ReqHeader;
import com.fet.crm.osp.kernel.mware.server.vo.process.resp.RespBody;
import com.fet.crm.osp.kernel.mware.server.vo.process.resp.RespHeader;

/**
 * 面向OSP相關服務 RESTful Web Services控制器. <br>
 * 
 * @author VJChou, RichardHuang
 */
@RestController
public class OSPToolMainRESTFulWS extends AbstractBasicRESTFulWS {

    // Request
    protected ReqHeader reqHeader = new ReqHeader();
    protected ReqBody reqBody = new ReqBody();
    protected ExContext exContext = new ExContext();
    
    /**
     * 類型: 查詢. <br>
     * 透過業務人員代碼取得業務人員相關資訊(E-Mail). <br>
     * 查詢CEDS(eHR). <br>
     * 
     * @param request 
     *              HttpServletRequest 物件
     * @param empNo 
     *              POST 請求參數 - 業務人員員工代碼
     *            
     * @return SalesOutputVO
     */
    @RequestMapping(value = "/querySalesInfo", method = { RequestMethod.POST })
    public SalesOutputVO querySalesInfo(
            HttpServletRequest request, @RequestBody String empNo) {  
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        // Request param
        reqBody.setEmpNo(empNo);

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = OSPToolMainDelegate.querySalesInfo(exContext);

        return getSalesOutputVOProcessResult(rsContext);        
    }
    
    /**
     * 類型: 查詢. <br>
     * 根據 IVR Code 取得業務人員 ID. <br>
     * 
     * @param request
     * @param ivrCode
     * 
     * @return StringOutputVO
     */
    @RequestMapping(value = "/getUserIdByIvrCode", method = { RequestMethod.POST })
    public StringOutputVO getUserIdByIvrCode(HttpServletRequest request, @RequestBody String ivrCode) {
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);

        // Request param
        reqBody.setIvrCode(ivrCode);

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);
        
        ExContext rsContext = OSPToolMainDelegate.getUserIdByIvrCode(exContext);
        
        return getStringProcessResult(rsContext);
    }

    /**
     * 類型: 查詢. <br>
     * [從ITT取得備註資訊]<br>
     * 當前台(osp-platform)進行結案動作(有效件/無效件)時，且系統來源(from sourceSysId = 'ITT' )，
     * 觸發前端呼叫此服務。透過此服務再次呼叫 ITT 系統所提供的API，將回傳的欄位組成字串，回傳前端。
     * <br>
     * 做為前端存入OSP的工單備註欄位資訊 . <br>
     * 
     * @param request HttpServletRequest 物件
     * @param sourceOrderId POST 請求參數 - 原始系統工單號
     * @return StringOutputVO
     */
    @RequestMapping(value = "/getCommentFromITT", method = { RequestMethod.POST })
	public StringOutputVO getCommentFromITT(HttpServletRequest request, @RequestBody String sourceOrderId) {
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);

		// Request param
        reqBody.setSourceOrderId(sourceOrderId);

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);
        
        ExContext rsContext = OSPToolMainDelegate.getCommentFromITT(exContext);
        
        // 從ITT所取回的欄位組合出「備註資訊」
        return getStringProcessResult(rsContext);
	}

    /**
     * 類型: 查詢. <br>
     * 取得 AppPart 啟動所需客資. <br>
     * 
     * @param custInfoForAppPartParamVO 
     *              POST 請求參數 - 資訊封裝物件
     *              
     * @return CustInfoForAppPartOutputVO
     *              回傳資訊封裝物件
    */
    @RequestMapping(value = "/getCustInfoForAppPart", method = { RequestMethod.POST })
    public CustInfoForAppPartOutputVO getCustInfoForAppPart(
            HttpServletRequest request, @RequestBody CustInfoForAppPartParamVO custInfoForAppPartParamVO) {
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        String ownId = custInfoForAppPartParamVO.getOwnId();
        String msisdn = custInfoForAppPartParamVO.getMsisdn();
        String idType = custInfoForAppPartParamVO.getIdType();
        String rocId = custInfoForAppPartParamVO.getRocId();
        
        // Request param
        reqBody.setOwnId(ownId);
        reqBody.setMsisdn(msisdn);
        reqBody.setIdType(idType);
        reqBody.setRocId(rocId);
    
        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);
    
        ExContext rsContext = OSPToolMainDelegate.getCustInfoForAppPart(exContext);
    
        return getCustInfoForAppPartOutputVOProcessResult(rsContext);
    }

    /**
     * 類型: 查詢. <br>
     * 取得核資, 付款人與使用人相關資訊. <br>
     * 
     * @param custInfoForOSPParamVO 
     *              POST 請求參數
     *              
     * @return CustInfoForOSPOutputVO
     *              核資查詢回傳資訊封裝物件
    */
    @RequestMapping(value = "/getCustInfoForOSP", method = { RequestMethod.POST })
    public CustInfoForOSPOutputVO getCustInfoForOSP(
            HttpServletRequest request, @RequestBody CustInfoForOSPParamVO custInfoForOSPParamVO) {
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        // Request param
        reqBody.setUserId(custInfoForOSPParamVO.getUserId());
        reqBody.setSubscriberId(custInfoForOSPParamVO.getSubscriberId());

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = OSPToolMainDelegate.getCustInfoForOSP(exContext);

        return getCustInfoForOSPOutputVOProcessResult(rsContext);
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得帳務相關資訊. <br>
     * 
     * @param custInfoForOSPParamVO 
     *              POST 請求參數 - 資訊封裝物件
     *              
     * @return CustInfoForOSPOutputVO
     *              回傳資訊封裝物件
    */
    @RequestMapping(value = "/getCustBillingInfo", method = { RequestMethod.POST })
    public CustBillingInfoOutputVO getCustBillingInfo(
            HttpServletRequest request, @RequestBody CustBillingInfoParamVO custBillingInfoParamVO) {
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        // Request param
        reqBody.setUserId(custBillingInfoParamVO.getUserId());
        reqBody.setAccountId(custBillingInfoParamVO.getAccountId());
        reqBody.setSubscriberId(custBillingInfoParamVO.getSubscriberId());

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = OSPToolMainDelegate.getCustBillingInfo(exContext);

        return getCustBillingInfoOutputVOProcessResult(rsContext);
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得 AppPart 所需使用者登入相關資訊. <br>
     * 
     * @param ntAccountId 
     *              POST 請求參數 - 使用者 NT Account ID
     *              
     * @return AgentInfoOutputVO
     *              回傳資訊封裝物件
    */
    @RequestMapping(value = "/getAgentInfo", method = { RequestMethod.POST })
    public AgentInfoOutputVO getAgentInfo(
            HttpServletRequest request, @RequestBody String ntAccountId) {
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        // Request param
        reqBody.setAccountId(ntAccountId);

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = OSPToolMainDelegate.getAgentInfo(exContext);

        return getAgentInfoOutputVOProcessResult(rsContext);
    }
    
    /**
     * 類型: 查詢. <br>
     * 根據門號清單，取得 SOA 進件詳細資訊. <br>
     * 
     * @param request 
     *              HttpServletRequest 物件
     * @param soaTicketDetailParamVO 
     *              POST 請求參數
     *            
     * @return SOATicketDetailOutputVO
     */
    @RequestMapping(value = "/getSOATicketDetail", method = { RequestMethod.POST })
    public SOATicketDetailOutputVO getSOATicketDetail(
            HttpServletRequest request, @RequestBody SOATicketDetailParamVO soaTicketDetailParamVO) {  
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
		// Request param
        reqBody.setUserId(soaTicketDetailParamVO.getUserId());
        reqBody.setMsisdnList(soaTicketDetailParamVO.getMsisdnList());

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = OSPToolMainDelegate.getSOATicketDetail(exContext);

        return getSOATicketDetailOutputVOProcessResult(rsContext);        
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得簽核層級與人員資訊. <br>
     * 
     * @param authLevelInfoParamVO
     *              POST 請求參數
     * @return AuthLevelInfoOutputVO
     *              回傳結果資訊封裝物件
     */
    @RequestMapping(value = "/getAuthLevelInfo", method = { RequestMethod.POST })
    public AuthLevelInfoOutputVO getAuthLevelInfo(
            HttpServletRequest request, @RequestBody AuthLevelInfoParamVO authLevelInfoParamVO) {  
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        // Request param
        reqBody.setUserId(authLevelInfoParamVO.getUserId());
        reqBody.setOrderTypeId(authLevelInfoParamVO.getOrderTypeId());
        reqBody.setIvrCode(authLevelInfoParamVO.getIvrCode());
        reqBody.setSalesId(authLevelInfoParamVO.getSalesId());
        reqBody.setRocId(authLevelInfoParamVO.getRocId());
        reqBody.setPromotionId(authLevelInfoParamVO.getPromotionId());
        reqBody.setSubscriberId(authLevelInfoParamVO.getSubscriberId());

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = OSPToolMainDelegate.getAuthLevelInfo(exContext);

        return getAuthLevelInfoOutputVOProcessResult(rsContext);
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得學生溫暖長青申請方案相關資訊. <br>
     * 
     * @param specialOfferParamVO 
     *          POST 請求參數
     *          
     * @return SpecialOfferOutputVO 回傳結果資訊封裝物件
     */
    @RequestMapping(value = "/getSpecialOfferByRocId", method = { RequestMethod.POST })
    public SpecialOfferOutputVO getSpecialOfferByRocId(
            HttpServletRequest request, @RequestBody SpecialOfferParamVO specialOfferParamVO) {
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        // Request param
        reqBody.setUserId(specialOfferParamVO.getUserId());
        reqBody.setRocId(specialOfferParamVO.getRocId());

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = OSPToolMainDelegate.getSpecialOfferByRocId(exContext);

        return getSpecialOfferOutputVOProcessResult(rsContext);
        
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得 SPV 促代資訊. <br>
     * 
     * @param promotionId 
     *          POST 請求參數
     *          
     * @return PromotionDetailOutputVO 回傳結果資訊封裝物件
     */
    @RequestMapping(value = "/getPromotionDetail", method = { RequestMethod.POST })
    public PromotionDetailOutputVO getPromotionDetail(
            HttpServletRequest request, @RequestBody String promotionId) {
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        // Request param
        reqBody.setPromotionId(promotionId);

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = OSPToolMainDelegate.getPromotionDetail(exContext);

        return getPromotionDetailOutputVOProcessResult(rsContext);
        
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得 NE Info. <br>
     * 
     * @param neInfoParamVO 
     *          POST 請求參數
     *          
     * @return StringOutputVO 回傳結果資訊封裝物件
     */
    @RequestMapping(value = "/getNEInfo", method = { RequestMethod.POST })
    public StringOutputVO getNEInfo(
            HttpServletRequest request, @RequestBody NEInfoParamVO neInfoParamVO) {
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        String userId = neInfoParamVO.getUserId();
        String paymentCategory = neInfoParamVO.getPaymentCategory();
        String msisdn = neInfoParamVO.getMsisdn();
        String accountName = neInfoParamVO.getAccountName();
        String subType = neInfoParamVO.getSubType();
        
        // Request param
        reqBody.setUserId(userId);
        reqBody.setPaymentCategory(paymentCategory);
        reqBody.setMsisdn(msisdn);
        reqBody.setAccountName(accountName);
        reqBody.setSubType(subType);

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = OSPToolMainDelegate.getNEInfo(exContext);

        return getStringProcessResult(rsContext);
        
    }
    
    /**
     * 類型: 查詢. <br>
     * 取得 SIM 卡號. <br>
     * 
     * @param simIdParamVO 
     *          POST 請求參數
     *          
     * @return StringOutputVO 回傳結果資訊封裝物件
     */
    @RequestMapping(value = "/getSimIdByMsisdn", method = { RequestMethod.POST })
    public StringOutputVO getSimIdByMsisdn(
            HttpServletRequest request, @RequestBody SimIdParamVO simIdParamVO) {
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        String userId = simIdParamVO.getUserId();
        String paymentCategory = simIdParamVO.getPaymentCategory();
        String msisdn = simIdParamVO.getMsisdn();
        
        // Request param
        reqBody.setUserId(userId);
        reqBody.setPaymentCategory(paymentCategory);
        reqBody.setMsisdn(msisdn);

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = OSPToolMainDelegate.getSimIdByMsisdn(exContext);

        return getStringProcessResult(rsContext);
    }
    
    /**
     * 類型: 查詢. <br>
     * 根據門號取得 Cache Subscriber Info 資訊. <br>
     * 
     * @param msisdn 
     *          POST 請求參數 - 門號
     *          
     * @return CacheSubscriberInfoOutputVO 回傳結果資訊封裝物件
     */
    @RequestMapping(value = "/getCacheSubscriberInfoByMsisdn", method = { RequestMethod.POST })
    public CacheSubscriberInfoOutputVO getCacheSubscriberInfoByMsisdn(
            HttpServletRequest request, @RequestBody CacheSubscriberInfoParamVO cacheSubscriberInfoParamVO) {
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        // Request param
        reqBody.setUserId(cacheSubscriberInfoParamVO.getUserId());
        reqBody.setMsisdn(cacheSubscriberInfoParamVO.getMsisdn());

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = OSPToolMainDelegate.getCacheSubscriberInfoByMsisdn(exContext);

        return getCacheSubscriberInfoProcessResult(rsContext);
    }
    
    /**
     * 類型: 查詢. <br>
     * 根據證號取得客資歷史資訊. <br>
     * 
     * @param rocId 
     *          POST 請求參數 - 證號
     *          
     * @return IdViewInfoOutputVO 回傳結果資訊封裝物件
     */
    @RequestMapping(value = "/getIdViewInfoByRocId", method = { RequestMethod.POST })
    public IdViewInfoOutputVO getIdViewInfoByRocId(
            HttpServletRequest request, @RequestBody IdViewInfoParamVO idViewInfoParamVO) {
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        // Request param
        reqBody.setUserId(idViewInfoParamVO.getUserId());
        reqBody.setRocId(idViewInfoParamVO.getRocId());

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = OSPToolMainDelegate.getIdViewInfoByRocId(exContext);

        return getIdViewInfoProcessResult(rsContext);
    }
    
    /**
     * 類型: 資料異動. <br>
     * 新增 CIE Master 資料. <br>
     * 
     * @param msisdn 
     *          POST 請求參數 - 門號
     *          
     * @return AddCIEMasterOutputVO 回傳結果資訊封裝物件
     */
    @RequestMapping(value = "/addCIEMaster", method = { RequestMethod.POST })
    public AddCIEMasterOutputVO addCIEMaster(
            HttpServletRequest request, @RequestBody AddCIEMasterParamVO addCIEMasterParamVO) {
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        String userId = addCIEMasterParamVO.getUserId();
        String empNo = addCIEMasterParamVO.getEmpNo();
        String contractComponentId = addCIEMasterParamVO.getContractComponentId();
        String partyId = addCIEMasterParamVO.getPartyId();
        String accountId = addCIEMasterParamVO.getAccountId();
        String subscriberId = addCIEMasterParamVO.getSubscriberId();
        String msisdn = addCIEMasterParamVO.getMsisdn();
        String serviceProvider = addCIEMasterParamVO.getServiceProvider();
        String generationCode = addCIEMasterParamVO.getGenerationCode();
        String paymentCategory = addCIEMasterParamVO.getPaymentCategory();
        
        // Request param
        reqBody.setUserId(userId);
        reqBody.setEmpNo(empNo);
        reqBody.setContractComponentId(contractComponentId);
        reqBody.setPartyId(partyId);
        reqBody.setAccountId(accountId);
        reqBody.setSubscriberId(subscriberId);
        reqBody.setMsisdn(msisdn);
        reqBody.setServiceProvider(serviceProvider);
        reqBody.setGenerationCode(generationCode);
        reqBody.setPaymentCategory(paymentCategory);

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = OSPToolMainDelegate.addCIEMaster(exContext);

        return getAddCIEMasterProcessResult(rsContext);
    }
    
    // ========== 以下為工具程式 ==========
    
    /*
     * CustInfoForOSPOutputVO 類處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return CustInfoForOSPOutputVO
     */
    private CustInfoForOSPOutputVO getCustInfoForOSPOutputVOProcessResult(ExContext rsContext) {
        CustInfoForOSPOutputVO outputVO = new CustInfoForOSPOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();

        if ((ReturnCode.SUCCESS.getCode()).equals(resultHeader.getReturnCode())) {
            RespBody respBody = rsContext.getRespBody();

            CustInfoForOSPRtnVO result = (CustInfoForOSPRtnVO) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);

            outputVO.setResultBody(result);
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }
    
    /*
     * CustBillingInfoOutputVO 類處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return CustBillingInfoOutputVO
     */
    private CustBillingInfoOutputVO getCustBillingInfoOutputVOProcessResult(ExContext rsContext) {
        CustBillingInfoOutputVO outputVO = new CustBillingInfoOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();

        if ((ReturnCode.SUCCESS.getCode()).equals(resultHeader.getReturnCode())) {
            RespBody respBody = rsContext.getRespBody();

            CustBillingInfoRtnVO result = (CustBillingInfoRtnVO) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);

            outputVO.setResultBody(result);
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }
    
    /*
     * CustInfoForAppPartOutputVO 類處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return CustInfoForAppPartOutputVO
     */
    private CustInfoForAppPartOutputVO getCustInfoForAppPartOutputVOProcessResult(ExContext rsContext) {
        CustInfoForAppPartOutputVO outputVO = new CustInfoForAppPartOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();

        if ((ReturnCode.SUCCESS.getCode()).equals(resultHeader.getReturnCode())) {
            RespBody respBody = rsContext.getRespBody();

            CustInfoForAppPartRtnVO result = (CustInfoForAppPartRtnVO) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);

            outputVO.setResultBody(result);
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }
    
    /*
     * SalesOutputVO 類處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return SalesOutputVO
     */
    private SalesOutputVO getSalesOutputVOProcessResult(ExContext rsContext) {
        SalesOutputVO outputVO = new SalesOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();

        if ((ReturnCode.SUCCESS.getCode()).equals(resultHeader.getReturnCode())) {
            RespBody respBody = rsContext.getRespBody();

            SalesInfoVO result = (SalesInfoVO) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);

            outputVO.setResultBody(result);
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }
    
    /**
     * AgentInfoOutputVO 類處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return AgentInfoOutputVO
     */
    private AgentInfoOutputVO getAgentInfoOutputVOProcessResult(ExContext rsContext) {
        AgentInfoOutputVO outputVO = new AgentInfoOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();

        if ((ReturnCode.SUCCESS.getCode()).equals(resultHeader.getReturnCode())) {
            RespBody respBody = rsContext.getRespBody();

            AgentInfoRtnVO result = (AgentInfoRtnVO) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);

            outputVO.setResultBody(result);
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }
    
    /*
     * SOATicketDetailOutputVO 類處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return SOATicketDetailOutputVO
     */
    private SOATicketDetailOutputVO getSOATicketDetailOutputVOProcessResult(ExContext rsContext) {
        SOATicketDetailOutputVO outputVO = new SOATicketDetailOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();

        if ((ReturnCode.SUCCESS.getCode()).equals(resultHeader.getReturnCode())) {
            RespBody respBody = rsContext.getRespBody();

            List<SOATicketDetailRtnVO> result = (List<SOATicketDetailRtnVO>) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);

            outputVO.setResultBody(result);
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }
    
    /*
     * AuthLevelInfoOutputVO 類處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return AuthLevelInfoOutputVO
     */
    private AuthLevelInfoOutputVO getAuthLevelInfoOutputVOProcessResult(ExContext rsContext) {
        AuthLevelInfoOutputVO outputVO = new AuthLevelInfoOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();
        
        String returnCode = resultHeader.getReturnCode();

        if (StringUtils.equals(returnCode, ReturnCode.SUCCESS.getCode())) {
            RespBody respBody = rsContext.getRespBody();
            AuthLevelInfoRtnVO result = (AuthLevelInfoRtnVO) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);
            outputVO.setResultBody(result);
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }
    
    /*
     * SpecialOfferOutputVO 類處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return SpecialOfferOutputVO
     */
    private SpecialOfferOutputVO getSpecialOfferOutputVOProcessResult(ExContext rsContext) {
        SpecialOfferOutputVO outputVO = new SpecialOfferOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();
        
        String returnCode = resultHeader.getReturnCode();

        if (StringUtils.equals(returnCode, ReturnCode.SUCCESS.getCode())) {
            RespBody respBody = rsContext.getRespBody();
            List<SpecialOfferRtnVO> result = (List<SpecialOfferRtnVO>) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);
            outputVO.setResultBody(result);
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }
    
    /*
     * PromotionDetailOutputVO 類處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return PromotionDetailOutputVO
     */
    private PromotionDetailOutputVO getPromotionDetailOutputVOProcessResult(ExContext rsContext) {
        PromotionDetailOutputVO outputVO = new PromotionDetailOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();
        
        String returnCode = resultHeader.getReturnCode();

        if (StringUtils.equals(returnCode, ReturnCode.SUCCESS.getCode())) {
            RespBody respBody = rsContext.getRespBody();
            PromotionDetailRtnVO result = (PromotionDetailRtnVO) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);
            outputVO.setResultBody(result);
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }
    
    /*
     * CacheSubscriberInfoOutputVO 類處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return CacheSubscriberInfoOutputVO
     */
    private CacheSubscriberInfoOutputVO getCacheSubscriberInfoProcessResult(ExContext rsContext) {
        CacheSubscriberInfoOutputVO outputVO = new CacheSubscriberInfoOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();
        
        String returnCode = resultHeader.getReturnCode();

        if (StringUtils.equals(returnCode, ReturnCode.SUCCESS.getCode())) {
            RespBody respBody = rsContext.getRespBody();
            CacheSubscriberInfoRtnVO result = (CacheSubscriberInfoRtnVO) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);
            outputVO.setResultBody(result);
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }
    
    /*
     * IdViewInfoOutputVO 類處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return IdViewInfoOutputVO
     */
    private IdViewInfoOutputVO getIdViewInfoProcessResult(ExContext rsContext) {
    	IdViewInfoOutputVO outputVO = new IdViewInfoOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();
        
        String returnCode = resultHeader.getReturnCode();

        if (StringUtils.equals(returnCode, ReturnCode.SUCCESS.getCode())) {
            RespBody respBody = rsContext.getRespBody();
            IdViewInfoRtnVO result = (IdViewInfoRtnVO) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);
            outputVO.setResultBody(result);
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }
    
    /*
     * AddCIEMasterOutputVO 類處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return AddCIEMasterOutputVO
     */
    private AddCIEMasterOutputVO getAddCIEMasterProcessResult(ExContext rsContext) {
        AddCIEMasterOutputVO outputVO = new AddCIEMasterOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();
        
        String returnCode = resultHeader.getReturnCode();

        if (StringUtils.equals(returnCode, ReturnCode.SUCCESS.getCode())) {
            RespBody respBody = rsContext.getRespBody();
            AddCIEMasterRtnVO result = (AddCIEMasterRtnVO) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);
            outputVO.setResultBody(result);
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }
    
}
