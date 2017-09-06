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

import com.fet.crm.osp.platform.core.db.model.SysShiftContent;
import com.fet.crm.osp.platform.core.db.model.SysShiftContentId;

/**
 * 
 * 
 * @author LawrenceLai,AndrewLee
 */
public interface SysShiftContentRepository extends JpaRepository<SysShiftContent, SysShiftContentId> {
	
	@Query(value = "SELECT * FROM SYS_SHIFT_CONTENT WHERE EMPNO = ? AND DT_YEAR = ? AND DT_MONTH = ? AND DT_DAY = ?", nativeQuery = true)
	SysShiftContent findByEmpNoAndSysDate(String empNo, String year, String month, String day);

}
