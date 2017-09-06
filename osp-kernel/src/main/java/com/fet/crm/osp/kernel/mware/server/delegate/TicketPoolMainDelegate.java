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

package com.fet.crm.osp.kernel.mware.server.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.kernel.mware.server.proxy.TicketPoolMainProxy;
import com.fet.crm.osp.kernel.mware.server.vo.process.ExContext;

/**
 * 面向工單池[ORDER_MAIN_METADATA]維護 資源管理/處理主要入口. <br>
 * 主要是生成後端唯一服務實例(singleton)，避免每一次連線連入的時候都生成一個服務實例. <br>
 * 
 * @author VJChou, RichardHuang
 */
@Component
public class TicketPoolMainDelegate {

	private static TicketPoolMainProxy ticketPoolProxy;

	/**
	 * 類型: 資料異動. <br>
	 * 將各進件系統抛轉進來的工單再次處理，並寫入TicketPool[ORDER_MAIN_METADATA]. <br>
	 * 
	 * @param exContext
	 * @return ExContext
	 */
	public static ExContext loadOrder2TicketPoolFromMiddle(ExContext exContext) {
		return ticketPoolProxy.loadOrder2TicketPoolFromMiddle(exContext);
	}
	
	/**
     * 類型: 資料異動. <br>
     * 將OSP系統人工建檔抛轉進來的工單再次處理，並寫入TicketPool[ORDER_MAIN_METADATA]. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext syncOrder2TicketPoolFromOSP(ExContext exContext) {
        return ticketPoolProxy.syncOrder2TicketPoolFromOSP(exContext);
    }

	/**
	 * 類型: 資料異動. <br>
	 * 將SPV抛轉進來的工單明細，寫入TicketPool[ORDER_DETAIL_SPV_MIDDLE]. <br>
	 * 
	 * @param exContext
	 * @return ExContext
	 */
	public static ExContext loadOrderDetail2TicketPoolFromSPV(ExContext exContext) {
		return ticketPoolProxy.loadOrderDetail2TicketPoolFromSPV(exContext);
	}

	/**
	 * 類型: 查詢 -1. <br>
	 * 根據[來源系統代碼][來源系統原始單號(子母單仍是不同的單號)]，從工單池中取回指定工單資訊(包含狀態). <br>
	 * 
	 * @param exContext
	 * @return ExContext
	 */
	public static ExContext queryOrderFromTicketPoolByIds(ExContext exContext) {
		return ticketPoolProxy.queryOrderFromTicketPoolByIds(exContext);
	}

	/**
	 * 類型: 查詢 -2. <br>
	 * 根據[PoolKey]，從工單池中取回指定工單資訊(包含狀態). <br>
	 * 
	 * @param exContext
	 * @return ExContext
	 */
	public static ExContext queryOrderFromTicketPoolByPoolKey(ExContext exContext) {
		return ticketPoolProxy.queryOrderFromTicketPoolByPoolKey(exContext);
	}

	/**
	 * 類型: 資料異動. <br>
	 * 更新工單資訊(狀態/處理人員)至TicketPool[ORDER_MAIN_METADATA]. <br>
	 * 
	 * @param exContext
	 * @return ExContext
	 */
	public static ExContext updateOrderStatus2TicketPool(ExContext exContext) {
		return ticketPoolProxy.updateOrderStatus2TicketPool(exContext);
	}
	
	/**
	 * 類型: 資料異動. <br>
	 * 更新 OSP 工單資訊(狀態/處理人員)至TicketPool[ORDER_MAIN_METADATA]. <br>
	 * 
	 * @param exContext
	 * @return ExContext
	 */
	public static ExContext updateOrderStatus2TicketPoolFromOSP(ExContext exContext) {
		return ticketPoolProxy.updateOrderStatus2TicketPoolFromOSP(exContext);
	}

	/**
	 * 類型: 資料異動. <br>
	 * 來源系統AIMS欄位異動[預計作業時間/客戶指定生效日]，更新至[ORDER_MAIN_METADATA]. <br>
	 * OSP狀態處於「未結案」之前才允許更新. <br>
	 * 
	 * @param exContext
	 * @return ExContext
	 */
	public static ExContext updateOrderInfo2TicketPoolFromAIMS(ExContext exContext) {
		return ticketPoolProxy.updateOrderInfo2TicketPoolFromAIMS(exContext);
	}

	// 以下為注入Spring實例
	@Autowired
	public void setTicketPoolProxy(TicketPoolMainProxy ticketPoolProxy) {
		TicketPoolMainDelegate.ticketPoolProxy = ticketPoolProxy;
	}

}
