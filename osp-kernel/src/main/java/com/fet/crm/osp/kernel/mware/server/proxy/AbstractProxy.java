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

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import com.fet.crm.osp.common.code.kernel.ReturnCode;
import com.fet.crm.osp.common.vo.kernel.process.ResultHeader;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.mware.server.vo.process.ExContext;
import com.fet.crm.osp.kernel.mware.server.vo.process.resp.RespBody;
import com.fet.crm.osp.kernel.mware.server.vo.process.resp.RespHeader;

/**
 * @author RichardHuang
 */
public class AbstractProxy {
    
    // 回傳用物件
    private ResultHeader resultHeader = new ResultHeader();
    private RespHeader respHeader = new RespHeader();
    private RespBody respBody = new RespBody();
    private ExContext rsContext = new ExContext();
    
    protected String pattern = "{0} ({1})";
    
    protected void setFailResult(ReturnCode returnCode, Exception ex) {
        resultHeader.setReturnCode(returnCode.getCode());
        String message = MessageFormat.format(pattern, returnCode.getMessage(), ex.getMessage());
        resultHeader.setReturnMessage(message);
    }
    
    protected <T> void setSuccessResult(T result) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(Constant.PROCESS_RESULT, result);

        resultHeader.setReturnCode(ReturnCode.SUCCESS.getCode());
        resultHeader.setReturnMessage(ReturnCode.SUCCESS.getMessage());
        respBody.setData(data);
    }
    
    protected void setNoDataResult() {
        resultHeader.setReturnCode(ReturnCode.NO_DATA.getCode());
        resultHeader.setReturnMessage(ReturnCode.NO_DATA.getMessage());
    }
    
    protected void setNoDataResult(String message) {
        resultHeader.setReturnCode(ReturnCode.NO_DATA.getCode());
        String returnMessage = MessageFormat.format(pattern, ReturnCode.NO_DATA.getMessage(), message);
        resultHeader.setReturnMessage(returnMessage);
    }
    
    protected void setNoDataResult(Exception ex) {
        resultHeader.setReturnCode(ReturnCode.NO_DATA.getCode());
        String message = MessageFormat.format(pattern, ReturnCode.NO_DATA.getMessage(), ex.getMessage());
        resultHeader.setReturnMessage(message);
    }
    
    protected void setAuthErrorResult() {
        resultHeader.setReturnCode(ReturnCode.AUTH_ERROR.getCode());
        String message = MessageFormat.format(pattern, ReturnCode.AUTH_ERROR.getMessage());
        resultHeader.setReturnMessage(message);
    }
    
    protected ExContext getExContext() {
        respHeader.setResultHeader(resultHeader);
        rsContext.setRespHeader(respHeader);
        rsContext.setRespBody(respBody);
        
        return rsContext;
    }
    
}
