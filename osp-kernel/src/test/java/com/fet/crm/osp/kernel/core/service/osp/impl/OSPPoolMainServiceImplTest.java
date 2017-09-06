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

package com.fet.crm.osp.kernel.core.service.osp.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fet.crm.osp.kernel.SpringTest;
import com.fet.crm.osp.kernel.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.kernel.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.kernel.core.pojo.OrderDispatchNotifyPOJO;
import com.fet.crm.osp.kernel.core.service.osp.IOSPPoolMainService;

/**
 * 案件分派 單元測試
 * 
 * @author RichardHuang
 *
 */
public class OSPPoolMainServiceImplTest extends SpringTest {
	
	@Autowired
    @Qualifier("ospJdbcDAO")
    private JdbcDAO ospJdbcDAO;
	
	@Autowired
	private IOSPPoolMainService ospPoolMainServiceImpl;
	
	@Test
	public void testDispatch() {
		String employeeNo = "62830";
		String teamGroup = "M";
		
		int effCount = ospPoolMainServiceImpl.dispatch(employeeNo, teamGroup);
		
		System.out.println("派件筆數 = " + effCount);
		
		String sqlText = ResourceFileUtil.SQL_OSP.getResource("GET_ORDER_DISPATCH_NOTIFY_BY_EMPNO");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("employeeNo", employeeNo);
        
        List<OrderDispatchNotifyPOJO> dataList = ospJdbcDAO.queryForBean(sqlText, params, OrderDispatchNotifyPOJO.class);
        
        if (CollectionUtils.isNotEmpty(dataList)) {
        	System.out.println("\n新派件通知：\n========================");
        	
        	int count = 0;
        	
        	for (OrderDispatchNotifyPOJO data : dataList) {
        		System.out.println(++count + ". " + data);
			}
        }
	}
	
	@Test
    public void testReDispatch() {
        int effCount = ospPoolMainServiceImpl.reDispatch();
        
        System.out.println("派件筆數 = " + effCount);
        
        String sqlText = ResourceFileUtil.SQL_OSP.getResource("GET_ORDER_DISPATCH_NOTIFY");
        
        List<OrderDispatchNotifyPOJO> dataList = ospJdbcDAO.queryForBean(sqlText, OrderDispatchNotifyPOJO.class);
        
        if (CollectionUtils.isNotEmpty(dataList)) {
            System.out.println("\n新派件通知：\n========================");
            
            int count = 0;
            
            for (OrderDispatchNotifyPOJO data : dataList) {
                System.out.println(++count + ". " + data);
            }
        }
    }
	
	@Test
	public void testUpdateOrderStatus2OSPFromSurrounding() {
	    String txId = "1";
	    
        boolean success = ospPoolMainServiceImpl.updateOrderStatus2OSPFromSurrounding(txId);
        
        System.out.println(success);
	}

}
