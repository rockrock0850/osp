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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.platform.core.db.model.SysShiftAttachment;

/**
 *
 * @author AndrewLee
 */
public interface SysShiftAttachmentRepository extends JpaRepository<SysShiftAttachment, String> {

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM SYS_SHIFT_ATTACHMENT WHERE DT_YEAR = ? AND DT_MONTH = ?", nativeQuery = true)
    int deleteByDtYearAndDtMonth(String dtYear, String dtMonth);

    SysShiftAttachment findByDtYearAndDtMonth(String dtYear, String dtMonth);
    
}
