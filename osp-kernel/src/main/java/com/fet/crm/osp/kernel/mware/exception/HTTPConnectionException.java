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
 * HTTP 請求失敗 例外封裝類別
 * 
 * @author RichardHuang
 */
public class HTTPConnectionException extends MwareException {

    private static final long serialVersionUID = 2545142150646366404L;
    
    public HTTPConnectionException(String message) {
        super(message);
        setCode(MwareExceptionCode.HTTP_001);
    }

    public HTTPConnectionException(String message, Throwable cause) {
        super(message, cause);
        setCode(MwareExceptionCode.HTTP_001);
    }
	
}
