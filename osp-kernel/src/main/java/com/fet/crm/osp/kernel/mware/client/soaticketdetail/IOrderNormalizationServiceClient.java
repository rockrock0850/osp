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
package com.fet.crm.osp.kernel.mware.client.soaticketdetail;

import java.util.List;

import com.tibco.www.schemas.crm_fulfillment.sharedresources.xsd.ordernormalizationservice_xsd.SOATicketIofo;

/**
 * SOA 進件詳細資訊服務 SOAP Client 介面. <br>
 * 
 * @author RichardHuang
 */
public interface IOrderNormalizationServiceClient {
    
    public final String ORDER_NORMALIZATION_SERVICE_ENDPOINT_URL = "ORDER_NORMALIZATION_SERVICE_ENDPOINT_URL";
    
    /**
     * 呼叫 OrderNormalizationService API 查詢 SOA 進件詳細資訊. <br>
     * 
     * @param msisdnList
     * @param userId 使用者 NT Account
     * 
     * @return List<SOATicketIofo>
     */
    List<SOATicketIofo> getSOATicketDetail(List<String> msisdnList, String userId);
    
}
