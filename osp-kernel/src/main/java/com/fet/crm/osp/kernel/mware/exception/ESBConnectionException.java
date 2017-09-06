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
 * ESB 遠端網路服務連線失敗 例外封裝類別
 * 
 * @author RichardHuang
 */
public class ESBConnectionException extends MwareException {

    private static final long serialVersionUID = -3535103349419309992L;

    public ESBConnectionException(String message, Throwable cause) {
        super(message, cause);
        setCode(MwareExceptionCode.ESB_001);
    }
	
}
