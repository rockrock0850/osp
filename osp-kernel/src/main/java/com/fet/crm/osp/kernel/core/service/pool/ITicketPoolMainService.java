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

package com.fet.crm.osp.kernel.core.service.pool;

import java.util.Date;

import com.fet.crm.osp.kernel.core.vo.orderinfo.OrderDetailSpvVO;
import com.fet.crm.osp.kernel.core.vo.orderinfo.OrderMainMetadataVO;

/**
 * 面向工單池[ORDER_MAIN_METADATA]維護 主要服務介面. <br>
 * 
 * @author VJChou
 */
public interface ITicketPoolMainService {

	/**
	 * 類型: 資料異動. <br>
	 * 將各進件系統抛轉進來的工單再次處理，並寫入TicketPool[ORDER_MAIN_METADATA]. <br>
	 * 執行後，將執行結果再次取出回傳. <br>
	 * 
	 * @param orderMetadataVO
	 *            工單封裝物件
	 * @return OrderMainMetadataVO
	 */
	OrderMainMetadataVO loadOrder2TicketPoolFromMiddle(OrderMainMetadataVO orderMetadataVO);

	/**
	 * 類型: 資料異動. <br>
	 * 將SPV抛轉進來的工單明細，寫入TicketPool[ORDER_DETAIL_SPV_MIDDLE]. <br>
	 * 
	 * @param orderDetailSpvVO
	 *            SPV工單明細封裝物件
	 * @return boolean
	 */
	boolean loadOrderDetail2TicketPoolFromSPV(OrderDetailSpvVO orderDetailSpvVO);

	/**
	 * 類型: 查詢 -1. <br>
	 * 根據[來源系統代碼][來源系統原始單號(子母單仍是不同的單號)]，從工單池中取回指定工單資訊(包含狀態). <br>
	 * 
	 * @param sourceSysId
	 *            來源系統代碼, ex: AIMS
	 * @param sourceOrderId
	 *            來源系統原始單號
	 * @return OrderMainMetadataVO
	 */
	OrderMainMetadataVO queryOrderFromTicketPoolByIds(String sourceSysId, String sourceOrderId);

	/**
	 * 類型: 查詢 -2. <br>
	 * 根據[PoolKey]，從工單池中取回指定工單資訊(包含狀態). <br>
	 * 
	 * @param poolKey
	 * @return OrderMainMetadataVO
	 */
	OrderMainMetadataVO queryOrderFromTicketPoolByPoolKey(String poolKey);

	/**
	 * 類型: 資料異動. <br>
	 * 更新工單資訊(狀態/處理人員)至TicketPool[ORDER_MAIN_METADATA]. <br>
	 * 執行後，將執行結果再次取出回傳. <br>
	 * 
	 * @param poolKey
	 *            工單池鍵值
	 * @param status
	 *            工單狀態
	 * @param processUser
	 *            處理人員
	 * @return OrderMainMetadataVO
	 */
	OrderMainMetadataVO updateOrderStatus2TicketPool(String poolKey, String status, String processUser);

	/**
	 * 類型: 資料異動. <br>
	 * 來源系統AIMS欄位異動[預計作業時間/客戶指定生效日]，更新至[ORDER_MAIN_METADATA]. <br>
	 * OSP狀態處於「未結案」之前才允許更新. <br>
	 * 
	 * @param sourceOrderId
	 *            來源系統AIMS原始單號
	 * @param expectProcessTime
	 *            預計作業處理時間
	 * @param custSpecifyDate
	 *            客戶指定生效日
	 * @return boolean
	 */
	boolean updateOrderInfo2TicketPoolFromAIMS(String sourceOrderId, Date expectProcessTime, Date custSpecifyDate);

	/**
     * 類型: 資料異動. <br>
     * 將OSP系統人工建檔抛轉進來的工單再次處理，並寫入TicketPool[ORDER_MAIN_METADATA]. <br>
     * 執行後，將執行結果再次取出回傳. <br>
     * 
     * @param orderMetadataVO
     *            工單封裝物件
     * @return OrderMainMetadataVO
     */
    OrderMainMetadataVO syncOrder2TicketPoolFromOSP(OrderMainMetadataVO orderMetadataVO);
}
