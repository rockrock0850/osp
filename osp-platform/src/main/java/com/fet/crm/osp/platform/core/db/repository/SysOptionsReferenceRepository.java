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

import com.fet.crm.osp.platform.core.db.model.SysOptionsReference;
import com.fet.crm.osp.platform.core.db.model.SysOptionsReferenceId;

/**
 * 
 * @author LawrenceLai,AndrewLee
 */
public interface SysOptionsReferenceRepository extends JpaRepository<SysOptionsReference, SysOptionsReferenceId> {
	
	List<SysOptionsReference> findByIdOptionsTypeIn(List<String> optionsType);

	List<SysOptionsReference> findByIdOptionsType(String optionsType);

}
