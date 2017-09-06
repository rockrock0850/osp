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

package com.fet.crm.osp.kernel.core.db.repository.osp;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.kernel.core.db.model.osp.OrderMainOsp;

/**
 * [ORDER_MAIN_OSP] Repository
 *
 * @author VJChou, RichardHuang
 */
public interface OrderMainOspRepository extends JpaRepository<OrderMainOsp, String> {

	/**
	 * 來源系統AIMS欄位異動[預計作業時間/客戶指定生效日]，更新至OSP Pool[ORDER_MAIN_OSP]. <br>
     * OSP狀態處於「未結案」之前才允許更新. <br>
     * 
	 * @param sourceOrderId
	 *            來源系統AIMS原始單號
	 * @param expectProcessTime
	 *            預計作業處理時間
	 * @param custSpecifyDate
	 *            客戶指定生效日
	 * @param expectCompleteTime
	 * 			  預計完成時間
	 * 
	 * @return int 影響筆數
	 */
	@Modifying
	@Transactional
	@Query(value = "UPDATE ORDER_MAIN_OSP SET EXPECT_PROCESS_TIME = ?2, CUST_SPECIFY_DATE = ?3, EXPECT_COMPLETE_TIME = ?4, UPDATE_DATE = SYSDATE, UPDATE_USER = 'BUS_SERVICE' WHERE SOURCE_ORDER_ID = ?1 AND ORDER_STATUS <> '070' AND ORDER_STATUS <> '080'", nativeQuery = true)
	int updateOrderInfo2OSPFromAIMS(String sourceOrderId, Date expectProcessTime, Date custSpecifyDate, Date expectCompleteTime);
	
}
