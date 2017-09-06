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

package com.fet.crm.osp.platform.mware.client.restful.proxy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.common.vo.kernel.output.PromotionDetailOutputVO;
import com.fet.crm.osp.common.vo.kernel.result.SOATicketDetailRtnVO;
import com.fet.crm.osp.platform.SpringTest;
import com.fet.crm.osp.platform.core.common.util.DateUtil;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.vo.systeminfo.agent.AgentInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.AuthLevelInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.CacheSubscriberInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.CustInfoForAppPartReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.CustInfoForOSPReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.IdViewInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.OrderLoadReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.OrderLoadVO;
import com.fet.crm.osp.platform.mware.client.vo.PromotionDetailReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.SpecialOfferVO;

/**	
 * 
 * @author Lawrence.Lai
 */
public class OSPKernelRESTClientProxyTest extends SpringTest {

	@Autowired
	private OSPKernelRESTClientProxy proxy;

	private String ntAccount = "";
	
	@Test
	public void testGetSOATicketDetail() {
		List<String> msisdns = new ArrayList<>();
		msisdns.add("0960973268");
		msisdns.add("0960973263");
		msisdns.add("0960983125");
		msisdns.add("0960965432");
		List<SOATicketDetailRtnVO> result = proxy.getSOATicketDetail(msisdns, ntAccount);
		
		System.out.println("==================================================");
		System.out.println("result = " + JsonUtil.toJson(result));
		System.out.println("==================================================");
	}
	
	@Test
	public void testSyncOrder2TicketPoolFromOSP() {
		String poolKey = "4ac70440-1bb2-44fd-9dcd-acc9f6c06123";
		String sourceOrderId = "OSP-20170531-161328-7923603C868449E0";
		String teamGroup = "M";
		String sourceSysId = "Paper";
		String criticalFlag = "N";
		String sourceCreateTime = "2017-05-31 16:13:28";
		String counts = "1";
		String expectProcessTime = "2017-05-31 16:13:28";
		String status = "020";
		
		OrderLoadVO params = new OrderLoadVO();
		params.setPoolKey(poolKey);
		params.setSourceOrderId(sourceOrderId);
		params.setTeamGroup(teamGroup);
		params.setSourceSysId(sourceSysId);
		params.setCriticalFlag(criticalFlag);
		params.setSourceCreateTime(DateUtil.toDateTime(sourceCreateTime));
		params.setCounts(new BigDecimal(counts));
		params.setExpectProcessTime(DateUtil.toDateTime(expectProcessTime));
		params.setStatus(status);
		OrderLoadReturnVO result = proxy.syncOrder2TicketPoolFromOSP(params);
		
		System.out.println("==================================================");
		System.out.println("result = " + JsonUtil.toJson(result));
		System.out.println("==================================================");
	}

	@Test
	public void testGetAuthLevelInfo() {
		String systemType = ""; 		// IA or SSI
		String functionId = ""; 		// GA 類的 functionId
		String rocId = ""; 				// 客戶身分證號碼
		String salesChannelType = ""; 	// Retail or VASS
		String ivrCode = ""; 			// 店組代碼
		
		AuthLevelInfoVO params = new AuthLevelInfoVO();
		params.setSystemType(systemType);
		params.setFunctionId(functionId);
		params.setRocId(rocId);
		params.setSalesChannelType(salesChannelType);
		params.setIvrCode(ivrCode);
		
		proxy.getAuthLevelInfo(params, ntAccount);
    }
	
	@Test
	public void testGetPromotionDetail() {
		String promotionId = "ZIR000N-N0N4";
		
		PromotionDetailReturnVO result = proxy.getPromotionDetail(promotionId);

		System.out.println("==================================================");
		System.out.println("result = " + JsonUtil.toJson(result));
		System.out.println("==================================================");
	}

	@Test
	public void testGetSalesInfo() {
		String empNo = "Richard";
		String result = proxy.getSalesInfo(empNo);

		System.out.println("==================================================");
		System.out.println("result = " + result);
		System.out.println("==================================================");
	}

	@Test
	public void testGetCustInfoForAppPart() {
		String ownId = "fad7b11a-f00f-41bc-bbd4-82f13776321d"; 	// cache 識別編號
		String rocId = null; 									// 證號
		String idType = null; 									// 證號類別
		String msisdn = "0960974380"; 							// 門號
		
		CustInfoForAppPartReturnVO result = proxy.getCustInfoForAppPart(ownId, rocId, idType, msisdn);
		
		System.out.println("==================================================");
		System.out.println("result = " + JsonUtil.toJson(result));
		System.out.println("==================================================");
    }
	
	@Test
	public void testGetCustInfoForOSP() {
		String subscriberId = "325850845";

		CustInfoForOSPReturnVO result = proxy.getCustInfoForOSP(subscriberId, ntAccount);

		System.out.println("==================================================");
		System.out.println("result = " + JsonUtil.toJson(result));
		System.out.println("==================================================");
	}
	
	@Test
	public void testGetAgentInfo() {
		String ntAccount = "pcyang";
		
		AgentInfoVO result = proxy.getAgentInfo(ntAccount);
		
		System.out.println("==================================================");
		System.out.println("result = " + JsonUtil.toJson(result));
		System.out.println("==================================================");
	}

	@Test
	public void testUpdateOrderStatus2TicketPool() { 
		String poolKey = "AAA";
		String status = "010";
		String processUser = "Richard";
	
		boolean result = proxy.updateOrderStatus2TicketPool(poolKey, status, processUser, ntAccount);
	
		System.out.println("==================================================");
		System.out.println("result = " + result);
		System.out.println("==================================================");
	}
	
	@Test
	public void testGetSpecialOfferByRocId() {
		String rocId = "F124631414";
		
		List<SpecialOfferVO> result = proxy.getSpecialOfferByRocId(rocId, ntAccount);
		
		System.out.println("==================================================");
		System.out.println("result = " + JsonUtil.toJson(result));
		System.out.println("==================================================");
	}

	@Test
	public void testGetCacheSubscriberInfoByMsisdn() {
		String msisdn = "";
		
		CacheSubscriberInfoVO result = proxy.getCacheSubscriberInfoByMsisdn(msisdn, ntAccount);
		
		print2Json(result);
	}

	@Test
	public void testGetSimIdByMsisdn() {
		String paymentCategory = "PP";
		String msisdn = "0912677149";
		String result = proxy.getSimIdByMsisdn(paymentCategory, msisdn, ntAccount);
		
		System.out.println("==================================================");
		System.out.println("result = " + JsonUtil.toJson(result));
		System.out.println("==================================================");
	}
	
	@Test
	public void testGetIdViewInfoByRocId() {
		String rocId = "F124631414";
		
		List<IdViewInfoVO> result = proxy.getIdViewInfoByRocId(rocId, ntAccount);
		
		System.out.println("==================================================");
		System.out.println("result = " + JsonUtil.toJson(result));
		System.out.println("==================================================");
	}
	
}
