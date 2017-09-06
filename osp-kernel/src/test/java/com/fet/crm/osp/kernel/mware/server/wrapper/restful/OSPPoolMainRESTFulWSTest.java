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

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.fet.crm.osp.common.vo.kernel.input.param.DispatchParamVO;
import com.fet.crm.osp.common.vo.kernel.output.BooleanOutputVO;
import com.fet.crm.osp.common.vo.kernel.process.ResultHeader;

/**
 * 面向OSP工單[ORDER_MAIN_OSP]維護 RESTful Web Services 單元測試. <br>
 * 
 * 參考連結. <br>
 * 1. http://howtodoinjava.com/spring/spring-restful/spring-restful-client-resttemplate-example/ <br>
 * 2. https://spring.io/guides/gs/consuming-rest/
 * 
 * @author VJChou, RichardHuang
 */
public class OSPPoolMainRESTFulWSTest {

    private String empNo = "Richard";
    private String teamGroup = "M";
    
    @Test
    public void testDispatchDoPost() {
//    	final String uri = "http://localhost:7001/osp-kernel/rest/dispatch";
    	final String uri = "http://ospsit.fareastone.com.tw:8003/osp-kernel/rest/dispatch";
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	DispatchParamVO param = new DispatchParamVO();
    	param.setEmpNo(empNo);
    	param.setTeamGroup(teamGroup);

        BooleanOutputVO result = restTemplate.postForObject(uri, param, BooleanOutputVO.class);
        
        ResultHeader resultHeader = result.getResultHeader();
        String resultBody = result.getResultBody();
        
        System.out.println("resultHeader = " + resultHeader);
        System.out.println("resultBody = " + resultBody);
        System.out.println("=================================================");
    }
    
    @Test
    public void testInitDailyShiftDispatcherTask() {
//        final String uri = "http://ospsit.fareastone.com.tw:8003/osp-kernel/rest/initDailyShiftDispatcherTask";
        final String uri = "http://localhost:7001/osp-kernel/rest/initDailyShiftDispatcherTask";
        
        RestTemplate restTemplate = new RestTemplate();
        
        String sourceSysId = "OSP-Batch";

        BooleanOutputVO result = restTemplate.postForObject(uri, sourceSysId, BooleanOutputVO.class);
        
        ResultHeader resultHeader = result.getResultHeader();
        String resultBody = result.getResultBody();
        
        System.out.println("resultHeader = " + resultHeader);
        System.out.println("resultBody = " + resultBody);
        System.out.println("=================================================");
    }
    
}
