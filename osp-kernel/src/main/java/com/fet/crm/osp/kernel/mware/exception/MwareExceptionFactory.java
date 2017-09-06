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

package com.fet.crm.osp.kernel.mware.exception;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.springframework.stereotype.Component;

import com.fet.car.exception.CARException;
import com.fet.crm.osp.common.exception.ExceptionFactory;

/**
 * 核心組件例外轉換類別
 * 
 * @author PaulChen, RichardHuang
 */
@Component
public class MwareExceptionFactory extends ExceptionFactory {

	/**
	 * 將非預期之例外轉換成 CoreException
	 * 
	 * @param ex 發生Exception 類別
	 * @return MwareException
	 */
	public MwareException getException(Throwable ex) {
		String message = ex.getMessage();
		Throwable cause = ex;

		MwareException cex = new MwareException(message, cause);
		return cex;
	}
	
	/**
     * 將 AxisFault 轉換成 ESBConnectionException
     * 
     * @param e 發生 Exception 類別
     * @return ESBConnectionException
     */
    public ESBConnectionException getException(AxisFault e) {
        String message = e.getMessage();
        Throwable cause = e;

        ESBConnectionException ex = new ESBConnectionException(message, cause);
        return ex;
    }
    
    /**
     * 將 RemoteException 轉換成 ESBAPIException
     * 
     * @param e 發生 Exception 類別
     * @return ESBAPIException
     */
    public ESBAPIException getException(RemoteException e) {
        String message = e.getMessage();
        Throwable cause = e;

        ESBAPIException ex = new ESBAPIException(message, cause);
        return ex;
    }
    
    /**
     * 將 com.fet.esb.crm.biz.services.crmcustbizservice.ProcessingFault 轉換成 ESBAPIException
     * 
     * @param e 發生 Exception 類別
     * @return ESBAPIException
     */
    public ESBAPIException getException(
    		com.fet.esb.crm.biz.services.crmcustbizservice.ProcessingFault e) {
        String message = e.getMessage();
        Throwable cause = e;

        ESBAPIException ex = new ESBAPIException(message, cause);
        return ex;
    }
    
    /**
     * 將 com.fet.esb.crm.biz.services.crmcustmdmbizservice.ProcessingFault 轉換成 ESBAPIException
     * 
     * @param e 發生 Exception 類別
     * @return ESBAPIException
     */
    public ESBAPIException getException(
    		com.fet.esb.crm.biz.services.crmcustmdmbizservice.ProcessingFault e) {
        String message = e.getMessage();
        Throwable cause = e;

        ESBAPIException ex = new ESBAPIException(message, cause);
        return ex;
    }
    
    /**
     * 將 CARException 轉換成 ESBAPIException
     * 
     * @param e 發生 Exception 類別
     * @return CARAPIException
     */
    public CARAPIException getException(CARException e) {
        String message = e.getMessage();
        Throwable cause = e;

        CARAPIException ex = new CARAPIException(message, cause);
        return ex;
    }
    
}
