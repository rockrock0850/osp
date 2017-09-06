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

package com.fet.crm.osp.kernel.mware.server.vo.process;

import com.fet.crm.osp.kernel.mware.server.vo.process.req.ReqBody;
import com.fet.crm.osp.kernel.mware.server.vo.process.req.ReqHeader;
import com.fet.crm.osp.kernel.mware.server.vo.process.resp.RespBody;
import com.fet.crm.osp.kernel.mware.server.vo.process.resp.RespHeader;

/**
 * osp-kernel內部傳遞/操作用的總封裝物件.<br>
 * 
 * @author VJChou
 */
public class ExContext {

    // Request
    private ReqHeader reqHeader;
    private ReqBody reqBody;

    // Response
    private RespHeader respHeader;
    private RespBody respBody;

    public ReqHeader getReqHeader() {
        return reqHeader;
    }

    public void setReqHeader(ReqHeader reqHeader) {
        this.reqHeader = reqHeader;
    }

    public ReqBody getReqBody() {
        return reqBody;
    }

    public void setReqBody(ReqBody reqBody) {
        this.reqBody = reqBody;
    }

    public RespHeader getRespHeader() {
        return respHeader;
    }

    public void setRespHeader(RespHeader respHeader) {
        this.respHeader = respHeader;
    }

    public RespBody getRespBody() {
        return respBody;
    }

    public void setRespBody(RespBody respBody) {
        this.respBody = respBody;
    }

}
