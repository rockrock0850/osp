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

package com.fet.crm.osp.platform.core.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fet.crm.osp.platform.core.db.model.OrderDetailSpv;

/**
 * [ORDER_DETAIL_SPV] Repository
 * 
 * @author JoeChiu
 *
 */
public interface OrderDetailSpvRepository extends JpaRepository<OrderDetailSpv, String> {
	
	/**
	 * 根據[POOL_KEY] </BR>
	 * 查詢SPV案件詳細資訊。
	 * 
	 * @param poolKey
	 * @return
	 */
	@Query(value = "SELECT * FROM ORDER_DETAIL_SPV WHERE POOL_KEY = :poolKey", nativeQuery = true)
	List<OrderDetailSpv> findByPoolKey(@Param("poolKey")String poolKey);
}
