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

import com.fet.crm.osp.kernel.mware.server.proxy.OSPPoolMainProxy;
import com.fet.crm.osp.kernel.mware.server.vo.process.ExContext;

/**
 * 面向OSP工單[ORDER_MAIN_OSP]維護 資源管理/處理主要入口. <br>
 * 主要是生成後端唯一服務實例(singleton)，避免每一次連線連入的時候都生成一個服務實例. <br>
 * 
 * @author VJChou, RichardHuang
 */
@Component
public class OSPPoolMainDelegate {

    private static OSPPoolMainProxy ospPoolProxy;

    /**
     * 類型: 資料異動. <br>
     * 週邊系統更新工單處理狀態 (分段工序).<br>
     * 分段流程中，他系統已回覆相同工單編號的所有處理結果至OSP，案件狀態變更為「系統回覆」. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext updateOrderStatus2OSPFromSurrounding(ExContext exContext) {
        return ospPoolProxy.updateOrderStatus2OSPFromSurrounding(exContext);
    }
    
    /**
     * 類型: 自動派件. <br>
     * 依據各班別上/下班時間，產生對映的自動派件類別(task). <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext initDailyShiftDispatcherTask(ExContext exContext) {
    	return ospPoolProxy.initDailyShiftDispatcherTask(exContext);
    }
    
    /**
     * 類型: 自動派件. <br>
     * 強制清除所有自動派件執行任務. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public static ExContext cleanShiftDispatcherTask(ExContext exContext) {
        return ospPoolProxy.cleanShiftDispatcherTask(exContext);
    }

    @Autowired
    public void setOspPoolProxy(OSPPoolMainProxy ospPoolProxy) {
        OSPPoolMainDelegate.ospPoolProxy = ospPoolProxy;
    }

}
