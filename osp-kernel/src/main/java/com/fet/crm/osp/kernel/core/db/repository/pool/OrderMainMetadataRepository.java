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

package com.fet.crm.osp.kernel.core.db.repository.pool;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.kernel.core.db.model.pool.OrderMainMetadata;

/**
 * [ORDER_MAIN_METADATA] Repository
 * 
 * @author VJChou
 */
public interface OrderMainMetadataRepository extends JpaRepository<OrderMainMetadata, String> {

	/**
	 * 根據[來源系統代碼][來源系統原始單號(子母單仍是不同的單號)]，從工單池中取回指定工單資訊(包含狀態). <br>
	 * 
	 * @param sourceSysId
	 *            來源系統代碼
	 * @param sourceOrderId
	 *            來源系統原始單號
	 * @return List<OrderMainMetadata>
	 */
	@Query(value = "SELECT * FROM ORDER_MAIN_METADATA WHERE SOURCE_SYS_ID = ?1 AND SOURCE_ORDER_ID = ?2", nativeQuery = true)
	List<OrderMainMetadata> findBySourceSysIdAndSourceOrderId(String sourceSysId, String sourceOrderId);

	/**
	 * 根據[POOL_KEY]，從工單池中取回指定工單資訊(包含狀態). <br> = repository.findOne(poolKey);
	 * 
	 * @param poolKey
	 * @return OrderMainMetadata 
	@Query(value = "SELECT * FROM ORDER_MAIN_METADATA WHERE POOL_KEY = ?1", nativeQuery = true)
	OrderMainMetadata findByPoolKey(String poolKey);
	*/

	/**
	 * 
	 * @param poolKey
	 *            工單池鍵值
	 * @param status
	 *            工單狀態
	 * @param processUser
	 *            處理人員
	 * @return int 影響筆數
	 */
	@Modifying
	@Transactional
	@Query(value = "UPDATE ORDER_MAIN_METADATA SET STATUS = ?2 , PROCESS_USER = ?3, UPDATE_DATE = SYSDATE WHERE POOL_KEY = ?1", nativeQuery = true)
	int updateOrderStatus2TicketPool(String poolKey, String status, String processUser);

	/**
	 * 
	 * @param sourceOrderId
	 *            來源系統AIMS原始單號
	 * @param expectProcessTime
	 *            預計作業處理時間
	 * @param custSpecifyDate
	 *            客戶指定生效日
	 * @return int 影響筆數
	 */
	@Modifying
	@Transactional
	@Query(value = "UPDATE ORDER_MAIN_METADATA SET EXPECT_PROCESS_TIME = ?2, CUST_SPECIFY_DATE = ?3, UPDATE_DATE = SYSDATE, UPDATE_USER = 'BUS_SERVICE' WHERE SOURCE_ORDER_ID = ?1 AND STATUS <> '070' AND STATUS <> '080'", nativeQuery = true)
	int updateOrderInfo2TicketPoolFromAIMS(String sourceOrderId, Date expectProcessTime, Date custSpecifyDate);

}
