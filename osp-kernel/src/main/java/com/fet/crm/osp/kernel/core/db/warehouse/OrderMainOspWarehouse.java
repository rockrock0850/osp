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
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.kernel.core.common.util.BeanCopyUtil;
import com.fet.crm.osp.kernel.core.db.model.osp.OrderMainOsp;
import com.fet.crm.osp.kernel.core.db.repository.osp.OrderMainOspRepository;
import com.fet.crm.osp.kernel.core.pojo.OrderMainOspPOJO;

/**
 * [OrderMainOsp] Warehouse. <br>
 * 
 * @author VJChou
 */
@Component
public class OrderMainOspWarehouse {

    @Autowired
    private OrderMainOspRepository repository;

    /**
     * 查詢資料庫，回傳操作用POJO物件. <br>
     * 
     * @param pojo
     * @return List<OrderMainOspPOJO>
     */
    public List<OrderMainOspPOJO> findAll() {
        List<OrderMainOsp> entityList = repository.findAll();

        try {
            if (entityList != null && !entityList.isEmpty()) {
                List<OrderMainOspPOJO> pojoList = new ArrayList<>();

                for (OrderMainOsp entity : entityList) {
                    OrderMainOspPOJO pojoObj = new OrderMainOspPOJO();

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
    public boolean save(OrderMainOspPOJO pojo) {
        OrderMainOsp entity = new OrderMainOsp();

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
    public boolean update(OrderMainOspPOJO pojo) {
        if (pojo != null) {
            String orderMId = pojo.getOrderMId();
            OrderMainOsp entity = repository.findOne(orderMId);

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
    public boolean updateInBatch(List<OrderMainOspPOJO> dataList) {
        for (OrderMainOspPOJO pojo : dataList) {
            update(pojo);
        }

        return true;
    }
    
    /**
	 * 來源系統AIMS欄位異動[預計作業時間/客戶指定生效日]，更新至OSP Pool[ORDER_MAIN_OSP]. <br>
     * OSP狀態處於「未結案」之前才允許更新. <br>
     * 
	 * @param sourceOrderId
	 *            來源系統AIMS原始單號
	 * @param expectProcessTime
	 *            預計作業處理時間
	 * @param custSpecifyDate
	 *            客戶指定生效日
	 * @param expectCompleteTime
	 * 			  預計完成時間
	 * 
	 * @return boolean
	 */
	public boolean updateOrderInfo2OSPFromAIMS(
			String sourceOrderId, Date expectProcessTime, Date custSpecifyDate, Date expectCompleteTime) {
	    int impact = repository.updateOrderInfo2OSPFromAIMS(
	    		sourceOrderId, expectProcessTime, custSpecifyDate, expectCompleteTime);
	    
	    if (impact == 0) {
            return false;
        }

        return true;
	}

}
