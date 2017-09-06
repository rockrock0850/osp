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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.kernel.core.common.util.BeanCopyUtil;
import com.fet.crm.osp.kernel.core.db.model.pool.OrderDetailSpvMiddle;
import com.fet.crm.osp.kernel.core.db.repository.pool.OrderDetailSpvMiddleRepository;
import com.fet.crm.osp.kernel.core.pojo.OrderDetailSpvPOJO;

/**
 * [OrderDetailSpvMiddle] Warehouse. <br>
 * 
 * @author VJChou
 */
@Component
public class OrderDetailSpvMiddleWarehouse {

    @Autowired
    private OrderDetailSpvMiddleRepository repository;

    /**
     * 查詢資料庫，回傳操作用POJO物件. <br>
     * 
     * @param pojo
     * @return List<OrderDetailSpvPOJO>
     */
    public List<OrderDetailSpvPOJO> findAll() {
        List<OrderDetailSpvMiddle> entityList = repository.findAll();

        try {
            if (entityList != null && !entityList.isEmpty()) {
                List<OrderDetailSpvPOJO> pojoList = new ArrayList<>();

                for (OrderDetailSpvMiddle entity : entityList) {
                    OrderDetailSpvPOJO pojoObj = new OrderDetailSpvPOJO();

                    BeanUtils.copyProperties(entity, pojoObj);

                    pojoList.add(pojoObj);
                }

                return pojoList;
            }

            return Collections.emptyList();
        } finally {
            entityList = null;
        }
    }

    /**
     * 新增物件進入資料庫. <br>
     * 
     * @param pojo
     * @return boolean
     */
    public boolean save(OrderDetailSpvPOJO pojo) {
    	OrderDetailSpvMiddle entity = new OrderDetailSpvMiddle();

        BeanUtils.copyProperties(pojo, entity);

        repository.save(entity);

        return true;
    }

    /**
     * 更新物件進入資料庫. <br>
     * 
     * @param pojo
     * @return boolean
     */
    public boolean update(OrderDetailSpvPOJO pojo) {
        if (pojo != null) {
            String orderDId = pojo.getOrderDId();
            OrderDetailSpvMiddle entity = repository.findOne(orderDId);

            String[] ignoreProperties = BeanCopyUtil.null2Ignore(pojo);
            BeanUtils.copyProperties(pojo, entity, ignoreProperties);

            repository.save(entity);
        }

        return true;
    }

    /**
     * 批次更新物件進入資料庫. <br>
     * 
     * @param dataList
     * @return boolean
     */
    public boolean updateInBatch(List<OrderDetailSpvPOJO> dataList) {
        for (OrderDetailSpvPOJO pojo : dataList) {
            update(pojo);
        }

        return true;
    }
    
}
