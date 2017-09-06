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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.platform.core.db.model.OrderDispatchNotify;
import com.fet.crm.osp.platform.core.db.model.OrderDispatchNotifyId;

/**
 * 
 * @author LawrenceLai
 */
public interface OrderDispatchNotifyRepository extends JpaRepository<OrderDispatchNotify, OrderDispatchNotifyId> {
	
	List<OrderDispatchNotify> findByProcessUserId(String processUserId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM ORDER_DISPATCH_NOTIFY WHERE BATCH_NO = ? AND PROCESS_USER_ID = ?", nativeQuery = true)
	int deleteDispatchNotifyByBatchNoAndProcessUserId(String batchNo, String processUserId);

}
