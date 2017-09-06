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

import org.apache.commons.collections.MapUtils;

import com.fet.crm.osp.common.code.kernel.ReturnCode;
import com.fet.crm.osp.common.vo.kernel.output.BooleanOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.StringOutputVO;
import com.fet.crm.osp.common.vo.kernel.process.ResultHeader;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.mware.server.vo.process.ExContext;
import com.fet.crm.osp.kernel.mware.server.vo.process.resp.RespBody;
import com.fet.crm.osp.kernel.mware.server.vo.process.resp.RespHeader;

/**
 * RESTFul WS基本共同服務類. <br>
 * 
 * @author VJChou, RichardHuang
 */
public abstract class AbstractBasicRESTFulWS {
    
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
    
    /**
     * String類處理結果於此統一處理回傳訊息. <br>
     * 
     * @param rsContext
     * @return StringOutputVO
     */
    protected StringOutputVO getStringProcessResult(ExContext rsContext) {
        // 準備回傳
        StringOutputVO outputVO = new StringOutputVO();

        RespHeader respHeader = rsContext.getRespHeader();
        ResultHeader resultHeader = respHeader.getResultHeader();

        if ((ReturnCode.SUCCESS.getCode()).equals(resultHeader.getReturnCode())) {
            RespBody respBody = rsContext.getRespBody();

            String result = MapUtils.getString(respBody.getData(), Constant.PROCESS_RESULT);

            outputVO.setResultBody(String.valueOf(result));
        }

        outputVO.setResultHeader(resultHeader);

        return outputVO;
    }

}
