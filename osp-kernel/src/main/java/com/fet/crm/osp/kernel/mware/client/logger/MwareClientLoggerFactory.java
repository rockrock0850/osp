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

package com.fet.crm.osp.kernel.mware.client.logger;

import com.fet.generic.logger.ILogger;
import com.fet.generic.logger.LoggerFactory;

/**
 * Mware Clent Logger物件控制器. <br>
 * 
 * @author VJChou
 */
public class MwareClientLoggerFactory {

    private static ILogger clientLogger;

    /**
     * 
     * @return Logger
     */
    public static final ILogger getLogger() {
        if (clientLogger == null) {
            clientLogger = LoggerFactory.getLogger(MwareClientLoggerFactory.class);
        }

        return clientLogger;
    }

}
