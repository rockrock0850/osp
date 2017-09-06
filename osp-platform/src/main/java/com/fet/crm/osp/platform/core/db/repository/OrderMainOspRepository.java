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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fet.crm.osp.platform.core.db.model.OrderMainOsp;

/**
 * [ORDER_MAIN_OSP] Repository
 *
 * @author AndrewLee, Joe
 */
public interface OrderMainOspRepository extends JpaRepository<OrderMainOsp, String> {
	
	OrderMainOsp findByOrderMId(String orderMId);
	
	/**
	 * 根據[ORDER_M_ID] </BR>
	 * 查詢[POOL_KEY]
	 * 
	 * @param orderMId
	 * @return String
	 */
	@Query(value = "SELECT POOL_KEY FROM ORDER_MAIN_OSP WHERE ORDER_M_ID = :orderMId", nativeQuery = true)
	String findPoolKeyByOrderMId(@Param("orderMId")String orderMId);
	
}
