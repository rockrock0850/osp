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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.fet.crm.osp.common.vo.kernel.input.param.AddCIEMasterParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.AuthLevelInfoParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.CacheSubscriberInfoParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.CustBillingInfoParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.CustInfoForAppPartParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.IdViewInfoParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.NEInfoParamVO;
import com.fet.crm.osp.common.vo.kernel.input.param.SimIdParamVO;
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
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustBillingInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.InvoiceInfoVO;
import com.fet.crm.osp.common.vo.kernel.result.idviewinfo.IdViewInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.promotiondetail.PromotionDetailRtnVO;
import com.fet.crm.osp.kernel.SpringTest;

/**
 * 面向OSP相關服務 RESTful Web Services 單元測試
 * 
 * @author RichardHuang
 */
public class OSPToolMainRESTFulWSTest extends SpringTest {

	final String restUri = "http://localhost:7001/osp-kernel/rest/";
//	final String restUri = "http://ospuat.fareastone.com.tw:6203/osp-kernel/rest/";
//	final String restUri = "http://ospsit.fareastone.com.tw:8003/osp-kernel/rest/";
	
	/**
	 * 類型: 查詢. <br>
     * 透過業務人員代碼取得業務人員相關資訊(E-Mail). <br>
     * 查詢CEDS(eHR). <br>
	 */
	@Test
    public void testQuerySalesInfo() {
        final String uri = restUri + "querySalesInfo";
        String empNo = "65196";

        RestTemplate restTemplate = new RestTemplate();
        SalesOutputVO result = restTemplate.postForObject(uri, empNo, SalesOutputVO.class);

        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        showPojoContent(result.getResultBody());
    }
	
	@Test
    public void testGetUserIdByIvrCode() {
        final String uri = restUri + "getUserIdByIvrCode";
        String ivrCode = "70106";
        
        RestTemplate restTemplate = new RestTemplate();
        StringOutputVO result = restTemplate.postForObject(uri, ivrCode, StringOutputVO.class);
        
        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        System.out.println("userId = " + result.getResultBody());
    }

    @Test
    public void testGetCommentFromITT() {
        final String uri = restUri + "getCommentFromITT";
        String sourceOrderId = "M1705220055";
        
        RestTemplate restTemplate = new RestTemplate();
        StringOutputVO result = restTemplate.postForObject(uri, sourceOrderId, StringOutputVO.class);
        
        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        System.out.println("comment :\n" + result.getResultBody());
    }

    @Test
    public void testGetCustInfoForAppPart() {
        final String uri = restUri + "getCustInfoForAppPart";
        String ownId = "28f0f7bb-2dd4-422e-9a02-e291d7d2f2af";
        String msisdn = "0912677149";
        
        CustInfoForAppPartParamVO param = new CustInfoForAppPartParamVO();
        param.setOwnId(ownId);
        param.setMsisdn(msisdn);
        
        RestTemplate restTemplate = new RestTemplate();
        CustInfoForAppPartOutputVO result = restTemplate.postForObject(uri, param, CustInfoForAppPartOutputVO.class);
        
        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        showPojoContent(result.getResultBody());
    }

    @Test
    public void testGetCustInfoForOSP() {
        final String uri = restUri + "getCustInfoForOSP";
//        String subscriberId = "800209330";
//        String subscriberId = "210123901";
        String subscriberId = "800223868";
        
        RestTemplate restTemplate = new RestTemplate();
        CustInfoForOSPOutputVO result = restTemplate.postForObject(uri, subscriberId, CustInfoForOSPOutputVO.class);
        
        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        showPojoContent(result.getResultBody());
    }
    
    @Test
    public void testGetCustBillingInfo() {
        final String uri = restUri + "getCustBillingInfo";
        String accountId = "80225850";
        String subscriberId = "800209330";
//        String accountId = "838966059";
//        String subscriberId = "210123901";
        
        CustBillingInfoParamVO param = new CustBillingInfoParamVO();
        param.setAccountId(accountId);
        param.setSubscriberId(subscriberId);
        
        RestTemplate restTemplate = new RestTemplate();
        CustBillingInfoOutputVO result = restTemplate.postForObject(uri, param, CustBillingInfoOutputVO.class);
        CustBillingInfoRtnVO resultBody = result.getResultBody();
        List<InvoiceInfoVO> invoiceInfoList = resultBody.getInvoiceInfoList();
        
        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        showPojoContent(resultBody);
        System.out.println("invoiceInfoList :");
        showPojoList(invoiceInfoList);
    }
	
	@Test
	public void testGetAgentInfo() {
	    final String uri = restUri + "getAgentInfo";
	    String ntAccountId = "pcyang";
//	    String ntAccountId = "sylee";
//        String ntAccountId = "ahong";
//        String ntAccountId = "wplee";
//        String ntAccountId = "sywang";
//        String ntAccountId = "rhhsieh";
//        String ntAccountId = "ychou";
        
        RestTemplate restTemplate = new RestTemplate();
        AgentInfoOutputVO result = restTemplate.postForObject(uri, ntAccountId, AgentInfoOutputVO.class);
        
        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        showPojoContent(result.getResultBody());
	}
	
	@Test
    public void testGetSOATicketDetail() {
	    final String uri = restUri + "getSOATicketDetail";
	    
	    List<String> msisdnList = new ArrayList<String>();
        msisdnList.add("0960965432");
        msisdnList.add("0960983125");
        msisdnList.add("0960973263");
        msisdnList.add("0960973268");
        
        RestTemplate restTemplate = new RestTemplate();
        SOATicketDetailOutputVO result = restTemplate.postForObject(uri, msisdnList, SOATicketDetailOutputVO.class);
        
        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        showPojoList(result.getResultBody());
	}
	
	@Test
	public void testGetAuthLevelInfo() {
	    final String uri = restUri + "getAuthLevelInfo";
	    
	    AuthLevelInfoParamVO param = new AuthLevelInfoParamVO();
	    param.setUserId("wplee"); // 使用者 NT Account (必填)
	    param.setOrderTypeId("OSPL4022"); // 案件類型 (必填)
	    param.setIvrCode("2101"); // 必填
	    param.setSalesId("65555"); // 若 ivrCode 為 4 碼則為必填
//        param.setSubscriberId("206607922"); // 查詢簽核層級資訊 (必填)
//        param.setRocId("F202979771"); // 查詢簽核層級資訊 (必填)
//        param.setPromotionId("");
        
        RestTemplate restTemplate = new RestTemplate();
        AuthLevelInfoOutputVO result = restTemplate.postForObject(uri, param, AuthLevelInfoOutputVO.class);
        
        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        showPojoContent(result.getResultBody());
	}
	
	@Test
    public void testGetSpecialOfferByRocId() {
        final String uri = restUri + "getSpecialOfferByRocId";
        String rocid = "G243306546";
        
        RestTemplate restTemplate = new RestTemplate();
        SpecialOfferOutputVO result = restTemplate.postForObject(uri, rocid, SpecialOfferOutputVO.class);
        
        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        showPojoList(result.getResultBody());
    }
	
	@Test
    public void testGetPromotionDetail() {
        final String uri = restUri + "getPromotionDetail";
        String promotionId = "ZIR000N-N0N4";
        
        RestTemplate restTemplate = new RestTemplate();
        PromotionDetailOutputVO result = restTemplate.postForObject(uri, promotionId, PromotionDetailOutputVO.class);
        
        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        PromotionDetailRtnVO resultBody = result.getResultBody();
        showPojoContent(resultBody);
        showPojoList(resultBody.getDiscountOfferList());
        System.out.println("========================");
        showPojoList(resultBody.getVasOfferList());
    }

    @Test
    public void testGetNEInfo() {
        final String uri = restUri + "getNEInfo";
        
        NEInfoParamVO requestParam = new NEInfoParamVO();
        requestParam.setPaymentCategory("PP");
        requestParam.setMsisdn("0989446654");
//        requestParam.setMsisdn("0976469486");
        requestParam.setAccountName("laibo");
        requestParam.setSubType("4");
        
        RestTemplate restTemplate = new RestTemplate();
        StringOutputVO result = restTemplate.postForObject(uri, requestParam, StringOutputVO.class);
        
        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        System.out.println(result.getResultBody());
    }
    
    @Test
    public void testGetSimIdByMsisdn() {
        final String uri = restUri + "getSimIdByMsisdn";
        
        SimIdParamVO requestParam = new SimIdParamVO();
        
        requestParam.setPaymentCategory("PP"); // PRE 預付
        requestParam.setMsisdn("0912677149");
        
//        requestParam.setPaymentCategory("PS"); // POST 月租
//        requestParam.setMsisdn("0960974380");
        
        RestTemplate restTemplate = new RestTemplate();
        StringOutputVO result = restTemplate.postForObject(uri, requestParam, StringOutputVO.class);
        
        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        System.out.println("SIM ID = " + result.getResultBody());
    }

    @Test
    public void testGetCacheSubscriberInfoByMsisdn() {
        final String uri = restUri + "getCacheSubscriberInfoByMsisdn";
//        String msisdn = "0989446654";
        String msisdn = "0917043046";
//        String msisdn = "0960987745";
        
        CacheSubscriberInfoParamVO param = new CacheSubscriberInfoParamVO();
        param.setUserId("pcyang");
        param.setMsisdn(msisdn);
        
        RestTemplate restTemplate = new RestTemplate();
        CacheSubscriberInfoOutputVO result = restTemplate.postForObject(uri, param, CacheSubscriberInfoOutputVO.class);
        
        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        showPojoContent(result.getResultBody());
    }
    
    @Test
    public void testGetIdViewInfoByRocId() {
        final String uri = restUri + "getIdViewInfoByRocId";
        String rocId = "L223775940";
        
        IdViewInfoParamVO param = new IdViewInfoParamVO();
        param.setRocId(rocId);
        param.setUserId("pcyang");
        
        RestTemplate restTemplate = new RestTemplate();
        IdViewInfoOutputVO result = restTemplate.postForObject(uri, param, IdViewInfoOutputVO.class);
        
        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        IdViewInfoRtnVO resultBody = result.getResultBody();
        
        if (resultBody != null) {
        	System.out.println("totalCount = " + resultBody.getTotalCount());
        	showPojoList(resultBody.getIdViewInfoList());
        }
    }
	
	@Test
    public void testAddCIEMaster() {
        final String uri = restUri + "addCIEMaster";
        String msisdn = "0917043046";
        
        AddCIEMasterParamVO requestParam = new AddCIEMasterParamVO();
        requestParam.setAccountId("80225850");
        requestParam.setContractComponentId("232149077662330354");
        requestParam.setEmpNo("65196");
        requestParam.setGenerationCode("3");
        requestParam.setMsisdn(msisdn);
        requestParam.setPartyId("235049077657178920");
        requestParam.setPaymentCategory("PS");
        requestParam.setServiceProvider("FET");
        requestParam.setSubscriberId("800209330");
        requestParam.setUserId("pcyang");
        
        RestTemplate restTemplate = new RestTemplate();
        AddCIEMasterOutputVO result = restTemplate.postForObject(uri, requestParam, AddCIEMasterOutputVO.class);
        
        showPojoContent(result.getResultHeader());
        System.out.println("========================");
        showPojoContent(result.getResultBody());
    }
	
}
