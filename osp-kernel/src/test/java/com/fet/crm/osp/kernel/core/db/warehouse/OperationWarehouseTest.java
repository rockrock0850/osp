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

package com.fet.crm.osp.kernel.core.db.warehouse;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.kernel.SpringTest;
import com.fet.crm.osp.kernel.core.pojo.OrderMainOspPOJO;

/**
 * Warehouse操作測試類. <br>
 * 
 * @author VJChou
 */
public class OperationWarehouseTest extends SpringTest {

    @Autowired
    private OrderMainOspWarehouse orderMainOspWarehouse;
    
    @Test
    public void testOrderMainOspWarehouseFindAll() {
        List<OrderMainOspPOJO> dataList = orderMainOspWarehouse.findAll();

        if (dataList != null && !dataList.isEmpty()) {
            System.out.println(dataList.size());

            OrderMainOspPOJO index = dataList.get(0);

            System.out.println(index.getOrderMId());
        }
    }
    
}
