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

package com.fet.crm.osp.kernel.mware.server.logger;

import com.fet.generic.logger.ILogger;
import com.fet.generic.logger.LoggerFactory;

/**
 * Mware Server Logger物件控制器. <br>
 * 
 * @author VJChou
 */
public class MwareServerLoggerFactory {

    private static ILogger serverLogger;

    /**
     * 
     * @return Logger
     */
    public static final ILogger getLogger() {
        if (serverLogger == null) {
            serverLogger = LoggerFactory.getLogger(MwareServerLoggerFactory.class);
        }

        return serverLogger;
    }

}
