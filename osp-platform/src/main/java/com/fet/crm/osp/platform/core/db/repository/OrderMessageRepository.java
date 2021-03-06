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
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.platform.core.db.model.OrderMessage;

/**
 * 
 * @author LawrenceLai
 */
public interface OrderMessageRepository extends JpaRepository<OrderMessage, String> {
	
	OrderMessage findByOrderMId(String orderMId);
	
	@Transactional
	void deleteByOrderMId(String orderMId);

}
