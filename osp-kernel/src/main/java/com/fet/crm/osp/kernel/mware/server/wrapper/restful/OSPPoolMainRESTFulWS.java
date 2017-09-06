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

package com.fet.crm.osp.kernel.mware.server.wrapper.restful;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fet.crm.osp.common.vo.kernel.output.BooleanOutputVO;
import com.fet.crm.osp.kernel.mware.server.delegate.OSPPoolMainDelegate;
import com.fet.crm.osp.kernel.mware.server.vo.process.ExContext;
import com.fet.crm.osp.kernel.mware.server.vo.process.req.ReqBody;
import com.fet.crm.osp.kernel.mware.server.vo.process.req.ReqHeader;

/**
 * 面向OSP工單[ORDER_MAIN_OSP]維護 RESTful Web Services 控制器. <br>
 * 
 * @author VJChou, RichardHuang
 */
@RestController
public class OSPPoolMainRESTFulWS extends AbstractBasicRESTFulWS {
	
	// Request
    protected ReqHeader reqHeader = new ReqHeader();
    protected ReqBody reqBody = new ReqBody();
    protected ExContext exContext = new ExContext();
    
    /**
     * 類型: 自動派件. <br>
	 * 依據各班別上/下班時間，產生對映的自動派件類別(task). <br>
	 * 
     * @param request 
     * 				HttpServletRequest 物件
     * 
     * @param sourceSysId 
     * 				POST 請求參數 - 來源系統 ID (固定值 : OSP-Batch)
     * 
     * @return BooleanOutputVO
     */
    @RequestMapping(value = "/initDailyShiftDispatcherTask", method = { RequestMethod.POST })
    public BooleanOutputVO initDailyShiftDispatcherTask(
    		HttpServletRequest request, @RequestBody String sourceSysId) {
    	
    	String remoteAddr = request.getRemoteAddr();
    	
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        // Request param
        reqBody.setSourceSysId(sourceSysId);
        
        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);
        
        ExContext rsContext = OSPPoolMainDelegate.initDailyShiftDispatcherTask(exContext);

        return getBooleanProcessResult(rsContext);
    }
    
    /**
     * 類型: 自動派件. <br>
     * 強制清除所有自動派件執行任務. <br>
     * 
     * @param request 
     *              HttpServletRequest 物件
     * 
     * @param sourceSysId 
     *              POST 請求參數 - 來源系統 ID (固定值 : OSP-Batch)
     * 
     * @return BooleanOutputVO
     */
    @RequestMapping(value = "/cleanShiftDispatcherTask", method = { RequestMethod.POST })
    public BooleanOutputVO cleanShiftDispatcherTask(
            HttpServletRequest request, @RequestBody String sourceSysId) {
        
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        // Request param
        reqBody.setSourceSysId(sourceSysId);
        
        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);
        
        ExContext rsContext = OSPPoolMainDelegate.cleanShiftDispatcherTask(exContext);

        return getBooleanProcessResult(rsContext);
    }

}
