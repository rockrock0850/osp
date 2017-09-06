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
package com.fet.crm.osp.kernel.mware.client.getcommentfromitt.impl;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.mware.client.AbstractBasicSOAPClient;
import com.fet.crm.osp.kernel.mware.client.code.QueryTicketDataServiceReturnCode;
import com.fet.crm.osp.kernel.mware.client.getcommentfromitt.IQueryTicketDataServiceClient;
import com.fet.crm.osp.kernel.mware.exception.ESBAPIException;
import com.fet.crm.osp.kernel.mware.exception.MwareExceptionFactory;

import itt.webservices.queryticketdata.CurTicketNote;
import itt.webservices.queryticketdata.CurTicketNoteList;
import itt.webservices.queryticketdata.QueryTicketDataRequestDocument;
import itt.webservices.queryticketdata.QueryTicketDataRequestDocument.QueryTicketDataRequest;
import itt.webservices.queryticketdata.QueryTicketDataResponseDocument;
import itt.webservices.queryticketdata.QueryTicketDataResponseDocument.QueryTicketDataResponse;
import itt.webservices.queryticketdata.QueryTicketDataServiceStub;
import itt.webservices.queryticketdata.TicketParamItem;
import itt.webservices.queryticketdata.TicketParams;

/**
 * 查詢 ITT 備註資訊服務 SOAP Client 實作類別
 * 
 * @author RichardHuang
 */
@Service
public class QueryTicketDataServiceClientImpl extends AbstractBasicSOAPClient implements IQueryTicketDataServiceClient {
    
    @Override
    public String queryTicketData(String sourceOrderId) {

        StringBuffer result = new StringBuffer();
        String targetEndpoint = getSysConfValueByConfId(QUERY_TICKET_DATA_SERVICE_ENDPOINT_URL);
        QueryTicketDataServiceStub proxy = null;
        
        try {
            proxy = createServiceStub(targetEndpoint);
            
        } catch (AxisFault e) {
            throw new MwareExceptionFactory().getException(e);
        }
        
        QueryTicketDataRequestDocument requestDocument = (QueryTicketDataRequestDocument) createRequest(sourceOrderId);
        QueryTicketDataResponseDocument responseDocument = null;
        
        try {
            responseDocument = proxy.queryTicketData(requestDocument);
            
        } catch (RemoteException e) {
            throw new MwareExceptionFactory().getException(e);
        }
        
        QueryTicketDataResponse response = responseDocument.getQueryTicketDataResponse();
        
        String returnCode = response.getResult();
        String returnMessage = response.getMessage();
        
        System.out.println("QueryTicketDataServiceClientImpl.queryTicketData : returnCode = " + returnCode + ", returnMessage = " + returnMessage);
        
        Assert.state(!StringUtils.equals(returnCode, QueryTicketDataServiceReturnCode.NO_DATA.getCode()));
        
        if (!StringUtils.equals(returnCode, QueryTicketDataServiceReturnCode.SUCCESS.getCode())) {
            throw new ESBAPIException("["+returnCode+"] " + returnMessage);
        }

        // 備註內容加入工單附加參數中的「問題原因」與「處理結果」
        TicketParams ticketParams = response.getTicketParams();
        
        if (ticketParams != null) {
            TicketParamItem[] ticketParamItemArray = ticketParams.getTicketParamItemArray();
            
            if (ArrayUtils.isNotEmpty(ticketParamItemArray)) {
            	result.append("[問題原因 第一層]").append("\n");
            	appendResult(result, TROUBLE_REASON_ONE_NAME, ticketParamItemArray);
            	result.append("[問題原因 第二層]").append("\n");
            	appendResult(result, TROUBLE_REASON_TWO_NAME, ticketParamItemArray);
            	result.append("[問題原因 第三層]").append("\n");
            	appendResult(result, TROUBLE_REASON_THREE_NAME, ticketParamItemArray);
            	result.append("[處理結果]").append("\n");
            	appendResult(result, PROC_RESULT_NAME, ticketParamItemArray);
            }
        }
        
        // 備註內容加入補充工單資訊
        CurTicketNoteList curTicketNoteList = response.getCurTicketNoteList();
        
        if (curTicketNoteList != null) {
            CurTicketNote[] curTicketNoteArray = curTicketNoteList.getCurTicketNoteArray();
            
            if (ArrayUtils.isNotEmpty(curTicketNoteArray)) {
                for (CurTicketNote curTicketNote : curTicketNoteArray) {
                	long noteType = curTicketNote.getNoteType();
                    String noteDesc = curTicketNote.getNoteDesc();
                    
                    // 取得補充說明型態為 (1:處理經過) 的註記內容
                    if (NOTE_TYPE == noteType && StringUtils.isNotBlank(noteDesc)) {
                        result.append(noteDesc).append("\n");
                    }
                }
            }
        }
        
        return result.toString();
    }
    
    private void appendResult(StringBuffer result, String paramItemKey, TicketParamItem[] ticketParamItemArray) {
    	for (TicketParamItem ticketParamItem : ticketParamItemArray) {
            String key = ticketParamItem.getKey();
            String value = ticketParamItem.getValue();
            
            if (StringUtils.equals(key, paramItemKey) 
            		&& StringUtils.isNotBlank(value)) {
            	result.append(value).append("\n");
            }
        }
    }

    private QueryTicketDataServiceStub createServiceStub(String targetEndpoint) throws AxisFault {
        return new QueryTicketDataServiceStub(targetEndpoint);
    }

    private QueryTicketDataRequestDocument createRequest(String sourceOrderId) {
        QueryTicketDataRequest request = QueryTicketDataRequest.Factory.newInstance();
        request.setChannel(Constant.SYSTEM_ID);
        request.setIttId(sourceOrderId);
        
        QueryTicketDataRequestDocument requestDocument = QueryTicketDataRequestDocument.Factory.newInstance();
        requestDocument.setQueryTicketDataRequest(request);
        
        return requestDocument;
    }
    
}
