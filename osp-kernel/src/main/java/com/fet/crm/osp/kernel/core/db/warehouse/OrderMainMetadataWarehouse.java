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
import com.fet.crm.osp.kernel.core.db.model.pool.OrderMainMetadata;
import com.fet.crm.osp.kernel.core.db.repository.pool.OrderMainMetadataRepository;
import com.fet.crm.osp.kernel.core.pojo.OrderMainMetadataPOJO;

/**
 * [OrderMainMetadata] Warehouse. <br>
 * 
 * @author VJChou
 */
@Component
public class OrderMainMetadataWarehouse {

	@Autowired
	private OrderMainMetadataRepository repository;

	/**
	 * 查詢資料庫，回傳操作用POJO物件. <br>
	 * 
	 * @param pojo
	 * @return List<OrderMainMetadataPOJO>
	 */
	public List<OrderMainMetadataPOJO> findAll() {
		List<OrderMainMetadata> entityList = repository.findAll();

		return processEntityListToPojoList(entityList);
	}

	/**
	 * 根據[來源系統代碼][來源系統原始單號(子母單仍是不同的單號)]，從工單池中取回指定工單資訊(包含狀態). <br>
	 * 
	 * @param sourceSysId
	 * @param sourceOrderId
	 * @return List<OrderMainMetadataPOJO>
	 */
	public List<OrderMainMetadataPOJO> findBySourceSysIdAndSourceOrderId(String sourceSysId, String sourceOrderId) {
		List<OrderMainMetadata> entityList = repository.findBySourceSysIdAndSourceOrderId(sourceSysId, sourceOrderId);

		return processEntityListToPojoList(entityList);
	}

	/**
	 * 根據[POOL_KEY]，從工單池中取回指定工單資訊(包含狀態). <br>
	 * 
	 * @param poolKey
	 * @return OrderMainMetadataPOJO
	 */
	public OrderMainMetadataPOJO findByPoolKey(String poolKey) {
		OrderMainMetadata entity = repository.findOne(poolKey);
		
		if (entity != null) {
		    OrderMainMetadataPOJO pojo = new OrderMainMetadataPOJO();
	        BeanUtils.copyProperties(entity, pojo);
	        
	        return pojo;
		}

		return null;
	}

	/**
	 * 新增物件進入資料庫. <br>
	 * 
	 * @param pojo
	 * @return boolean
	 */
	public boolean save(OrderMainMetadataPOJO pojo) {
		OrderMainMetadata entity = new OrderMainMetadata();

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
	public boolean update(OrderMainMetadataPOJO pojo) {
		if (pojo != null) {
			String poolKey = pojo.getPoolKey();
			OrderMainMetadata entity = repository.findOne(poolKey);

			String[] ignoreProperties = BeanCopyUtil.null2Ignore(pojo);
			BeanUtils.copyProperties(pojo, entity, ignoreProperties);

			repository.save(entity);
		}

		return true;
	}

	/**
	 * 
	 * @param poolKey
	 * @param status
	 * @param processUser
	 * @return boolean
	 */
	public boolean updateOrderStatus2TicketPool(String poolKey, String status, String processUser) {
		int impact = repository.updateOrderStatus2TicketPool(poolKey, status, processUser);

		if (impact == 0) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param sourceOrderId
	 * @param expectProcessTime
	 * @param custSpecifyDate
	 * @return boolean
	 */
	public boolean updateOrderInfo2TicketPoolFromAIMS(String sourceOrderId, Date expectProcessTime, Date custSpecifyDate) {
	    int impact = repository.updateOrderInfo2TicketPoolFromAIMS(sourceOrderId, expectProcessTime, custSpecifyDate);
	    
	    if (impact == 0) {
            return false;
        }

        return true;
	}

	/**
	 * 批次更新物件進入資料庫. <br>
	 * 
	 * @param dataList
	 * @return boolean
	 */
	public boolean updateInBatch(List<OrderMainMetadataPOJO> dataList) {
		for (OrderMainMetadataPOJO pojo : dataList) {
			update(pojo);
		}

		return true;
	}

	// ==================== 以下為工具程式 ====================

	/**
	 * 將JPA回傳的Model物件(Entity)，轉換成系統操作用的物件(POJO). <br>
	 * 
	 * @param entityList
	 * @return List<OrderMainMetadataPOJO>
	 */
	private List<OrderMainMetadataPOJO> processEntityListToPojoList(List<OrderMainMetadata> entityList) {
		try {
			if (entityList != null && !entityList.isEmpty()) {
				List<OrderMainMetadataPOJO> pojoList = new ArrayList<>();

				for (OrderMainMetadata entity : entityList) {
					OrderMainMetadataPOJO pojoObj = new OrderMainMetadataPOJO();

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

}
