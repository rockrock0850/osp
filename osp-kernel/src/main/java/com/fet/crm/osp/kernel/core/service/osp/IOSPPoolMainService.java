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

package com.fet.crm.osp.kernel.core.service.osp;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 面向OSP工單[ORDER_MAIN_OSP]維護 主要服務介面. <br>
 * 
 * @author VJChou, RichardHuang
 */
public interface IOSPPoolMainService {

    /**
     * 類型: 資料異動. <br>
     * 來源系統AIMS欄位異動[預計作業時間/客戶指定生效日]，更新至OSP Pool[ORDER_MAIN_OSP]. <br>
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
    boolean updateOrderInfo2OSPFromAIMS(String sourceOrderId, Date expectProcessTime, Date custSpecifyDate);

    /**
     * 類型: 資料異動. <br>
     * 週邊系統更新工單處理狀態 (分段工序).<br>
     * 分段流程中，他系統已回覆相同工單編號的所有處理結果至OSP，案件狀態變更為「系統回覆」. <br>
     * 
     * @param txId
     * @return boolean
     */
    boolean updateOrderStatus2OSPFromSurrounding(String txId);
    
    /**
     * 類型: 資料異動. <br>
	 * 根據「處理人員編號」與「人員所屬進件組別」, 執行案件分派. <br>
	 * 
	 * @param employeeNo - 處理人員編號
	 * @param teamGroup - 人員所屬進件組別
	 * @return int - 成功派件筆數
	 */
	int dispatch(String employeeNo, String teamGroup);
	
	/**
	 * 類型: 資料異動. <br>
     * 將母單已派件，但尚未被派件的子單，補派件給母單的「處理人員」. <br>
     * 
     * @return int - 成功派件筆數
	 */
	int reDispatch();
	
	/**
	 * 類型: 查詢. <br>
	 * 取得當日需進行自動派件的人員, 班表與派件間隔分鐘數等資訊. <br>
	 * 
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getDispatchEmpInfo();

}
