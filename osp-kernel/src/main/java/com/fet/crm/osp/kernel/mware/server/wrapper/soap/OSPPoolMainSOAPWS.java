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

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.fet.crm.osp.common.vo.kernel.input.record.BasicRecordVO;
import com.fet.crm.osp.common.vo.kernel.output.BooleanOutputVO;
import com.fet.crm.osp.kernel.mware.server.delegate.OSPPoolMainDelegate;
import com.fet.crm.osp.kernel.mware.server.vo.process.ExContext;

/**
 * 面向OSP工單[ORDER_MAIN_OSP]維護 Webservice. <br>
 * 
 * @author VJChou
 */
@WebService(
		serviceName = "OSPPoolMainService", 
		name = "OSPPoolMainService", 
		targetNamespace = "osppoolmainservice.soap.wrapper.server.mware.kernel.osp.crm.fet.com")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class OSPPoolMainSOAPWS extends AbstractBasicSOAPWS {

    /**
     * 類型: 資料異動. <br>
     * 週邊系統更新工單處理狀態 (分段工序).<br>
     * 分段流程中，他系統已回覆相同工單編號的所有處理結果至OSP，案件狀態變更為「系統回覆」. <br>
     * 
     * @param recordVO
     *            基本記錄物件
     * @param txId
     *            交易代碼
     * @return BooleanOutputVO
     */
    @WebMethod(operationName = "updateOrderStatus2OSPFromSurrounding", exclude = false)
    public BooleanOutputVO updateOrderStatus2OSPFromSurrounding(
            @WebParam(name = "BasicRecord", header = true) BasicRecordVO recordVO, 
            @WebParam(name = "txId") String txId) {
        // Request record
        reqHeader.setChannelId(recordVO.getChannelId());
        reqHeader.setRemoteAddr(getRemoteAddr());

        // Request param
        reqBody.setTxId(txId);

        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = OSPPoolMainDelegate.updateOrderStatus2OSPFromSurrounding(exContext);

        return getBooleanProcessResult(rsContext);
    }

}
