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

package com.fet.crm.osp.platform.core.service.orderinfo;

import com.fet.crm.osp.platform.core.vo.orderinfo.OrderImageSettingVO;

/**
 * [訂單管理輔助資訊] 服務介面
 * 
 * @author LawrenceLai
 */
public interface IOrderReferenceService {
	
	/**
	 * 查詢案件影響檔開啟資訊
	 * 
	 * @param orderMId
	 * @param userId
	 * @return
	 */
	OrderImageSettingVO queryOrderImageSetting(String orderMId, String userId, String ntAccount);
	
	/**
	 * 查詢是否有新進待處理案件，並於前端顯示新進件通知
	 * 
	 * @param userId
	 * @return boolean
	 */
	boolean dispatchNotify(String userId);

}
