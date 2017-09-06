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

package com.fet.crm.osp.kernel.core.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Core Logger物件控制器. <br>
 * <br>
 * 
 * [說明]: 【非 - Web 類型】的 logging 資訊, 應該要透過此 Logger Factory 取得 logger 做 log資訊的動作.
 * 
 * @author VJChou
 */
public class CoreLoggerFactory {

    private static Logger coreLogger;

    /**
     * 
     * @return Logger
     */
    public static final Logger getLogger() {
        if (coreLogger == null) {
            coreLogger = LoggerFactory.getLogger(CoreLoggerFactory.class);
        }

        return coreLogger;
    }
    
}
