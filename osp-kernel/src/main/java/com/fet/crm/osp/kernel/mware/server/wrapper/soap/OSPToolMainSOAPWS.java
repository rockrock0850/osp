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
import javax.jws.soap.SOAPBinding.Style;

import com.fet.crm.osp.common.vo.kernel.input.record.BasicRecordVO;
import com.fet.crm.osp.common.vo.kernel.output.BooleanOutputVO;
import com.fet.crm.osp.kernel.mware.server.delegate.OSPToolMainDelegate;
import com.fet.crm.osp.kernel.mware.server.vo.process.ExContext;

/**
 * 面向OSP相關服務 Webservice. <br>
 * 
 * @author VJChou
 */
@WebService(
		serviceName = "OSPToolMainService", 
		name = "OSPToolMainService", 
		targetNamespace = "osptoolmainservice.soap.wrapper.server.mware.kernel.osp.crm.fet.com")
@SOAPBinding(style = Style.RPC)
public class OSPToolMainSOAPWS extends AbstractBasicSOAPWS {

    /**
     * 類型: 資料異動. <br>
     * 傳入OSP之前呼叫服務所代入的參數「ospKey」，將TxId更新回OSP. <br>
     * 
     * @param recordVO
     *            基本記錄物件
     * @param ospKey
     * @param txId
     *            交易代碼
     * @return BooleanOutputVO
     */
    @WebMethod(operationName = "updateTxIdByIdentifyId", exclude = false)
    public BooleanOutputVO updateTxIdByIdentifyId(
            @WebParam(name = "BasicRecord", header = true) BasicRecordVO recordVO,
            @WebParam(name = "ospKey") String ospKey, 
            @WebParam(name = "txId") String txId) {
        // Request record
        reqHeader.setChannelId(recordVO.getChannelId());
        reqHeader.setRemoteAddr(getRemoteAddr());

        // Request param
        reqBody.setOspKey(ospKey);
        reqBody.setTxId(txId);

        exContext.setReqHeader(reqHeader);
        exContext.setReqBody(reqBody);

        ExContext rsContext = OSPToolMainDelegate.updateTxIdByIdentifyId(exContext);

        return getBooleanProcessResult(rsContext);
    }

}
