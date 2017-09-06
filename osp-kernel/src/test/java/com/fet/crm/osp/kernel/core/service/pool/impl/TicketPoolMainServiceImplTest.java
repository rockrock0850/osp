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
package com.fet.crm.osp.kernel.core.service.pool.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.common.util.DateUtil;
import com.fet.crm.osp.kernel.SpringTest;
import com.fet.crm.osp.kernel.core.service.pool.ITicketPoolMainService;
import com.fet.crm.osp.kernel.core.vo.orderinfo.OrderMainMetadataVO;

/**
 * 面向工單池[ORDER_MAIN_METADATA]維護 主要服務 單元測試
 * 
 * @author RichardHuang
 */
public class TicketPoolMainServiceImplTest extends SpringTest {
    
    @Autowired
    private ITicketPoolMainService ticketPoolMainService;
    
    @Test
    public void testQueryOrderFromTicketPoolByIds() {
        String sourceSysId = "FaxServer";
        String sourceOrderId = "ticketPool-Sample-001";
        
        OrderMainMetadataVO result = ticketPoolMainService.queryOrderFromTicketPoolByIds(sourceSysId, sourceOrderId);
        
        showPojoContent(result);
    }
    
    @Test
    public void testQueryOrderFromTicketPoolByPoolKey() {
        String poolKey = "e2d8958f-d29c-44d8-b973-d8c9da79569b";
        
        OrderMainMetadataVO result = ticketPoolMainService.queryOrderFromTicketPoolByPoolKey(poolKey);
        
        showPojoContent(result);
    }
    
    @Test
    public void testLoadOrder2TicketPoolFromMiddle() {
        OrderMainMetadataVO orderMetadataVO = new OrderMainMetadataVO();
        orderMetadataVO.setCounts(new BigDecimal(1));
        orderMetadataVO.setCriticalFlag("N");
        orderMetadataVO.setExpectProcessTime(DateUtil.fromDateTime("2017-05-10 11:55:17"));
        orderMetadataVO.setImgStorePath("D:\\FaxIn\\Source\\10.68.67.38\\2012\\0810\\024\\");
        orderMetadataVO.setImgStoreServer("idc-ctifaxfs");
        orderMetadataVO.setSourceCreateTime(DateUtil.fromDateTime("2017-05-10 11:55:17"));
        orderMetadataVO.setSourceOrderId("2223122L5101");
        orderMetadataVO.setSourceProdTypeId("OSPL4012");
        orderMetadataVO.setSourceSysId("Fax to email");
        
        OrderMainMetadataVO result = ticketPoolMainService.loadOrder2TicketPoolFromMiddle(orderMetadataVO);
        
        showPojoContent(result);
    }
    
    @Test
    public void testUpdateOrderStatus2TicketPool() {
        String poolKey = "54154594-0d2b-43a7-8882-c8f4dead12b2";
        String status = "080";
        String processUser = "OSP-BATCH";
        
        OrderMainMetadataVO result = ticketPoolMainService.updateOrderStatus2TicketPool(poolKey, status, processUser);
        
        showPojoContent(result);
    }
    
    @Test
    public void testUpdateOrderInfo2TicketPoolFromAIMS() {
        String expectProcessTime = "2017-05-10 23:59:59";
        String custSpecifyDate = "2017-05-08 18:15:01";
        String sourceOrderId = "ticketPool-Sample-001";
        
        Date expectProcessTimeDt = DateUtil.fromDateTime(expectProcessTime);
        Date custSpecifyDateDt = DateUtil.fromDateTime(custSpecifyDate);
        
        boolean success = false;
        
        try {
            success = ticketPoolMainService.updateOrderInfo2TicketPoolFromAIMS(sourceOrderId, expectProcessTimeDt, custSpecifyDateDt);
        
        } catch (RuntimeException e) {
            e.getStackTrace();
        }
        
        System.out.println("success = " + success);
    }
    
    
}
