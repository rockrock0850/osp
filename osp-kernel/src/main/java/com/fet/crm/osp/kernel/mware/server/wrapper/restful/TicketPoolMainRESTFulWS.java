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

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fet.crm.osp.common.code.kernel.ReturnCode;
import com.fet.crm.osp.common.vo.kernel.input.param.OrderLoadParam;
import com.fet.crm.osp.common.vo.kernel.input.param.OrderParamVO;
import com.fet.crm.osp.common.vo.kernel.output.OrderLoadOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.OrderUpdateOutputVO;
import com.fet.crm.osp.common.vo.kernel.process.ResultHeader;
import com.fet.crm.osp.common.vo.kernel.result.OrderLoadRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.OrderUpdateRtnVO;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.core.vo.orderinfo.OrderMainMetadataVO;
import com.fet.crm.osp.kernel.mware.server.delegate.TicketPoolMainDelegate;
import com.fet.crm.osp.kernel.mware.server.vo.process.ExContext;
import com.fet.crm.osp.kernel.mware.server.vo.process.req.ReqBody;
import com.fet.crm.osp.kernel.mware.server.vo.process.req.ReqHeader;
import com.fet.crm.osp.kernel.mware.server.vo.process.resp.RespBody;
import com.fet.crm.osp.kernel.mware.server.vo.process.resp.RespHeader;

/**
 * 面向工單池[ORDER_MAIN_METADATA]維護 主要 RESTful Web Services控制器. <br>
 * 
 * @author VJChou, RichardHuang
 */
@RestController
public class TicketPoolMainRESTFulWS extends AbstractBasicRESTFulWS {

    // Request
    protected ReqHeader reqHeader = new ReqHeader();
    protected ReqBody reqBody = new ReqBody();
    protected ExContext exContext = new ExContext();
    
    /**
     * 類型: 資料異動. <br>
     * 更新工單資訊(狀態/處理人員)至TicketPool[ORDER_MAIN_METADATA]. <br>
     * 
     * @param request
     * 				HttpServletRequest 物件
     * 
     * @param orderParamVO 
     * 				更新工單資訊 POST 請求參數資訊封裝物件
     *            
     * @return BooleanOutputVO
     */
    @RequestMapping(value = "/updateOrderStatus2TicketPool", method = { RequestMethod.POST })    
    public OrderUpdateOutputVO updateOrderStatus2TicketPool(
    		HttpServletRequest request, @RequestBody OrderParamVO orderParamVO) {
        
        String remoteAddr = request.getRemoteAddr();
        
        String userId = orderParamVO.getUserId();
        String poolKey = orderParamVO.getPoolKey();
        String status = orderParamVO.getStatus();
        String processUser = orderParamVO.getProcessUser();
        
        // Request record
        reqHeader.setChannelId(Constant.SYSTEM_ID);
        reqHeader.setRemoteAddr(remoteAddr);
        
		// Request param
        reqBody.setUserId(userId);
        reqBody.setPoolKey(poolKey);
        reqBody.setStatus(status);
        reqBody.setProcessUser(processUser);

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = TicketPoolMainDelegate.updateOrderStatus2TicketPoolFromOSP(exContext);

        return getOrderUpdateResult(rsContext);
    }
    
    /**
     * 類型: 資料異動. <br>
     * 將各OSP系統建檔抛轉進來的工單再次處理，並寫入TicketPool[ORDER_MAIN_METADATA]. <br>
     * 
     * @param request
     *              HttpServletRequest 物件
     * 
     * @param orderLoadParam 
     *              建檔新增工單資訊 POST 請求參數資訊封裝物件
     *              
     * @return OrderLoadOutputVO
     */
    @RequestMapping(value = "/syncOrder2TicketPoolFromOSP", method = { RequestMethod.POST })
    public OrderLoadOutputVO syncOrder2TicketPoolFromOSP(
            HttpServletRequest request, @RequestBody OrderLoadParam orderLoadParam) {
        String remoteAddr = request.getRemoteAddr();
        
        // Request record
        reqHeader.setRemoteAddr(remoteAddr);
        
        Date sysDate = new Date();
        
        orderLoadParam.setSourceSysId(Constant.SYSTEM_ID);
        orderLoadParam.setCreateDate(sysDate);
        orderLoadParam.setUpdateDate(sysDate);
        
        OrderMainMetadataVO orderMainMetadataVO = new OrderMainMetadataVO();
        BeanUtils.copyProperties(orderLoadParam, orderMainMetadataVO);
        
        // Request param
        reqBody.setOrderMainMetadata(orderMainMetadataVO);

        // 內部傳遞/操作用的封裝資訊
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = TicketPoolMainDelegate.syncOrder2TicketPoolFromOSP(exContext);

        return getOrderLoadResult(rsContext);
    }
    
    /**
     * 工單更新執行結果封裝 處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return OrderOutputVO
     */
    protected OrderUpdateOutputVO getOrderUpdateResult(ExContext rsContext) {
        // 準備回傳
        OrderUpdateOutputVO outputVO = new OrderUpdateOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();

        if ((ReturnCode.SUCCESS.getCode()).equals(resultHeader.getReturnCode())) {
            RespBody respBody = rsContext.getRespBody();

            OrderUpdateRtnVO result = (OrderUpdateRtnVO) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);

            outputVO.setResultBody(result);
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }
    
    /**
     * 新增工單執行結果封裝 處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return OrderOutputVO
     */
    protected OrderLoadOutputVO getOrderLoadResult(ExContext rsContext) {
        // 準備回傳
        OrderLoadOutputVO outputVO = new OrderLoadOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();

        if ((ReturnCode.SUCCESS.getCode()).equals(resultHeader.getReturnCode())) {
            RespBody respBody = rsContext.getRespBody();

            OrderLoadRtnVO result = (OrderLoadRtnVO) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);

            outputVO.setResultBody(result);
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }
    
}
