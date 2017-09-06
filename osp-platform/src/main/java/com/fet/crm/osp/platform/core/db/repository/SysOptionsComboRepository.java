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

import com.fet.crm.osp.platform.core.db.model.SysOptionsCombo;
import com.fet.crm.osp.platform.core.db.model.SysOptionsComboId;

/**
 * 
 * @author Adam Yeh
 */
public interface SysOptionsComboRepository extends JpaRepository<SysOptionsCombo, SysOptionsComboId> {

	List<SysOptionsCombo> findByIdComboType(String type);

	List<SysOptionsCombo> findByIdComboTypeAndIdComboValue(String type, String value); 
	
}
