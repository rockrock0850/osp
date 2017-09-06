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

import com.fet.crm.osp.platform.core.db.model.DispatchMemberConfig;

/**
 * [Dispatch_Member_Config] Repository
 *
 * @author AndrewLee
 */
public interface DispatchMemberConfigRepository extends JpaRepository<DispatchMemberConfig, String> {

}
