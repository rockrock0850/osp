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

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.fet.crm.osp.common.util.DateUtil;
import com.fet.crm.osp.common.util.JsonUtil;
import com.fet.crm.osp.common.vo.kernel.input.param.OrderLoadParam;
import com.fet.crm.osp.common.vo.kernel.input.param.OrderParamVO;
import com.fet.crm.osp.common.vo.kernel.output.OrderLoadOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.OrderUpdateOutputVO;
import com.fet.crm.osp.common.vo.kernel.process.ResultHeader;
import com.fet.crm.osp.common.vo.kernel.result.OrderLoadRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.OrderUpdateRtnVO;

/**
 * 面向工單池[ORDER_MAIN_METADATA]維護 主要 RESTful Web Services 單元測試
 * 
 * @author RichardHuang
 */
public class TicketPoolMainRESTFulWSTest {
	
//	final String restUri = "http://localhost:7001/osp-kernel/rest/";
	final String restUri = "http://ospsit.fareastone.com.tw:8003/osp-kernel/rest/";

	/**
	 * 類型: 資料異動. <br>
     * 更新工單資訊(狀態/處理人員)至TicketPool[ORDER_MAIN_METADATA]. <br>
	 */
	@Test
    public void testUpdateOrderStatus2TicketPoolDoPost() {
		final String uri = restUri + "updateOrderStatus2TicketPool";
        String poolKey = "c76d8033-c43a-4a87-a997-cc19108d304f";
        String status = "080";
        String processUser = "80030";

        RestTemplate restTemplate = new RestTemplate();
        
        OrderParamVO request = new OrderParamVO();
		request.setPoolKey(poolKey);
		request.setStatus(status);
		request.setProcessUser(processUser);

		OrderUpdateOutputVO result = restTemplate.postForObject(uri, request, OrderUpdateOutputVO.class);

		ResultHeader resultHeader = result.getResultHeader();
        OrderUpdateRtnVO resultBody = result.getResultBody();
        
        System.out.println("resultHeader = " + JsonUtil.toJson(resultHeader));
        System.out.println("resultBody = " + JsonUtil.toJson(resultBody));
        System.out.println("=================================================");
    }
	
	@Test
	public void testSyncOrder2TicketPoolFromOSP() {
	    final String uri = restUri + "syncOrder2TicketPoolFromOSP";
	    
	    RestTemplate restTemplate = new RestTemplate();
	    
	    String poolKey = "1111";
        String sourceOrderId = "OSP-20170531-161328-7923603C868449E0";
        String teamGroup = "M";
        String sourceSysId = "Paper";
        String criticalFlag = "N";
        String sourceCreateTime = "2017-05-31 16:13:28";
        String counts = "1";
        String expectProcessTime = "2017-05-31 16:13:28";
        String status = "020";
        String createUser = "65196";
	    
	    OrderLoadParam request = new OrderLoadParam();
	    request.setPoolKey(poolKey);
	    request.setSourceOrderId(sourceOrderId);
        request.setTeamGroup(teamGroup);
        request.setSourceSysId(sourceSysId);
//        request.setSourceProdTypeId("1");
//        request.setOperateType("1");
        request.setCriticalFlag(criticalFlag);
        request.setSourceCreateTime(DateUtil.fromDateTime(sourceCreateTime));
//        request.setMsisdn("1");
//        request.setCustName("1");
//        request.setImgIdApid("1");
//        request.setPartentOrderId("1");
        request.setCounts(new BigDecimal(counts));
        request.setExpectProcessTime(DateUtil.fromDateTime(expectProcessTime));
//        request.setCustSpecifyDate(new Date());
//        request.setCustId("1");
//        request.setSalesId("1");
//        request.setIvrCode("1");
//        request.setPromotionId("1");
//        request.setImgStorePath("1");
//        request.setImgStoreServer("1");
//        request.setImgStorePath("1");
//        request.setCid("1");
        request.setStatus(status);
        request.setProcessUser(createUser);
        
        OrderLoadOutputVO result = restTemplate.postForObject(uri, request, OrderLoadOutputVO.class);

        ResultHeader resultHeader = result.getResultHeader();
        OrderLoadRtnVO resultBody = result.getResultBody();
        
        System.out.println("resultHeader = " + resultHeader);
        System.out.println("poolKey = " + resultBody.getPoolKey());
        System.out.println("=================================================");
	}

}
