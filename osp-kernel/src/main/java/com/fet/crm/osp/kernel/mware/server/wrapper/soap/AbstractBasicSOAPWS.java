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

package com.fet.crm.osp.kernel.mware.server.wrapper.soap;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.commons.collections.MapUtils;

import com.fet.crm.osp.common.code.kernel.ReturnCode;
import com.fet.crm.osp.common.vo.kernel.input.record.BasicRecordVO;
import com.fet.crm.osp.common.vo.kernel.output.BooleanOutputVO;
import com.fet.crm.osp.common.vo.kernel.process.ResultHeader;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.mware.server.vo.process.ExContext;
import com.fet.crm.osp.kernel.mware.server.vo.process.req.ReqBody;
import com.fet.crm.osp.kernel.mware.server.vo.process.req.ReqHeader;
import com.fet.crm.osp.kernel.mware.server.vo.process.resp.RespBody;
import com.fet.crm.osp.kernel.mware.server.vo.process.resp.RespHeader;

/**
 * SOAP WS基本共同服務類. <br>
 * 
 * @author VJChou
 */
public abstract class AbstractBasicSOAPWS {

    @Resource
    private WebServiceContext wsctx;

    // Request
    protected ReqHeader reqHeader = new ReqHeader();
    protected ReqBody reqBody = new ReqBody();
    protected ExContext exContext = new ExContext();

    /**
     * 抓取遠端呼叫服務的IP位置. <br>
     * 
     * @return String
     */
    @WebMethod(exclude = true)
    protected String getRemoteAddr() {
        try {
            MessageContext msgctx = wsctx.getMessageContext();
            HttpServletRequest request = (HttpServletRequest) msgctx.get(MessageContext.SERVLET_REQUEST);
            String ipAddr = request.getRemoteAddr();

            return ipAddr;
        } catch (Exception ex) {
            ;
        }

        return "unKnow";
    }

    // ========== 以下為工具程式 ==========

    /**
     * Boolean類處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return BooleanOutputVO
     */
    protected BooleanOutputVO getBooleanProcessResult(ExContext rsContext) {
        // 準備回傳
        BooleanOutputVO outputVO = new BooleanOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();

        if ((ReturnCode.SUCCESS.getCode()).equals(resultHeader.getReturnCode())) {
            RespBody respBody = rsContext.getRespBody();

            Boolean result = MapUtils.getBoolean(respBody.getData(), Constant.PROCESS_RESULT);

            outputVO.setResultBody(String.valueOf(result));
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }
    
    protected void setExContext(BasicRecordVO basicRecordVO) {
        // Request record
        reqHeader.setChannelId(basicRecordVO.getChannelId());
        reqHeader.setRemoteAddr(getRemoteAddr());
        
        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);
    }

}
