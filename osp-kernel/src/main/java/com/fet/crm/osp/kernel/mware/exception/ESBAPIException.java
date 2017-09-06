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

import com.fet.crm.osp.kernel.mware.exception.code.MwareExceptionCode;

/**
 * ESB 遠端網路服務執行錯誤 例外封裝類別
 * 
 * @author RichardHuang
 */
public class ESBAPIException extends MwareException {

	private static final long serialVersionUID = -3494251710148072294L;
	
	public ESBAPIException(String message) {
        super(message);
        setCode(MwareExceptionCode.ESB_002);
    }

	public ESBAPIException(String message, Throwable cause) {
        super(message, cause);
        setCode(MwareExceptionCode.ESB_002);
    }
	
}
