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

package com.fet.crm.osp.kernel.mware.server.proxy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fet.crm.osp.common.code.kernel.ReturnCode;
import com.fet.crm.osp.kernel.core.service.osp.IOSPPoolMainService;
import com.fet.crm.osp.kernel.core.task.ShiftDispatcherTaskManager;
import com.fet.crm.osp.kernel.mware.server.vo.process.ExContext;
import com.fet.crm.osp.kernel.mware.server.vo.process.req.ReqBody;

/**
 * 面向OSP工單[ORDER_MAIN_OSP]維護 服務代理窗口. <br>
 * 作用類似以往開發時的「Facade」對象，用來將服務統籌/服務縫合/資料加工/資料處理. <br>
 * 放在這個位置，在於部分服務的方式為轉介其他Webservices，故不適合在core內部進行呼叫. <br>
 * 
 * @author VJChou, RichardHuang
 */
@Service
public class OSPPoolMainProxy extends AbstractProxy {

    @Autowired
    private IOSPPoolMainService ospPoolMainService;
    
    @Autowired
    private ShiftDispatcherTaskManager shiftDispatcherTaskManager;

    /**
     * 類型: 資料異動. <br>
     * 週邊系統更新工單處理狀態 (分段工序).<br>
     * 分段流程中，他系統已回覆相同工單編號的所有處理結果至OSP，案件狀態變更為「系統回覆」. <br>
     *  
     * @param exContext
     * @return ExContext
     */
    public ExContext updateOrderStatus2OSPFromSurrounding(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            String txId = reqBody.getTxId();
            
            Assert.hasText(txId, "txId must not be null, empty, or blank");
            
            boolean success = ospPoolMainService.updateOrderStatus2OSPFromSurrounding(txId);
            
            setSuccessResult(success);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setFailResult(ReturnCode.NO_DATA, ex);
            
        } catch (Exception ex) {
            setFailResult(ReturnCode.ERROR, ex);
        }

        return getExContext();
    }
    
    /**
     * 類型: 自動派件. <br>
     * 依據各班別上/下班時間，產生對映的自動派件類別(task). <br>
     * 
     * @param exContext
     * @return ExContext
     */
	public ExContext initDailyShiftDispatcherTask(ExContext exContext) {
		try {
		    // 取出需要往後傳的參數
	        ReqBody reqBody = exContext.getReqBody();
	        String sourceSysId = reqBody.getSourceSysId();
	        
	        Assert.hasText(sourceSysId, "sourceSysId must not be null, empty, or blank");
	        Assert.state(StringUtils.equals(sourceSysId, "OSP-Batch"));
	        
	        shiftDispatcherTaskManager.initDailyShiftDispatcherTask();
			
			setSuccessResult(true);
			
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setAuthErrorResult();
            
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            setFailResult(ReturnCode.ERROR, ex);
        }

        return getExContext();
	}
	
	/**
     * 類型: 自動派件. <br>
     * 強制清除所有自動派件執行任務. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public ExContext cleanShiftDispatcherTask(ExContext exContext) {
        try {
            // 取出需要往後傳的參數
            ReqBody reqBody = exContext.getReqBody();
            String sourceSysId = reqBody.getSourceSysId();
            
            Assert.hasText(sourceSysId, "sourceSysId must not be null, empty, or blank");
            Assert.state(StringUtils.equals(sourceSysId, "OSP-Batch"));
            
            shiftDispatcherTaskManager.cleanShiftDispatcherTask();
            
            setSuccessResult(true);
            
        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (IllegalStateException ex) {
            setAuthErrorResult();
            
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            setFailResult(ReturnCode.ERROR, ex);
        }

        return getExContext();
    }
	
}
