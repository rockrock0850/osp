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

package com.fet.crm.osp.platform.core.common;

import com.fet.crm.osp.platform.core.common.util.PropertiesUtil;

/**
 * 系統常數
 * 
 * @author PaulChen
 */
public class Constant {
	
    // System
    public static final String CONFIG_FILE = "platform";
    
    // surrounding system code.
    public static final String SURROUNDING_NSP = "NSP";
    public static final String SURROUNDING_MVC = "MVC";
    
    // 加密方式
    public static final String ENCRYPTION_METHOD_DES = "DES";
    public static final String ENCRYPTION_METHOD_DES_NSP = "DES_NSP";
    
    // is dev mode
    public static final boolean IS_DEV_MODE = PropertiesUtil.getBooleanProp("application.test.devmode");
    
    public static final String APPLICATION_PROP_PREFIX = "${";
    public static final String APPLICATION_PROP_SUFFIX = "}";
    
    public static final String ORDER_STATUS_NON_PROCESS 				= "010";
    public static final String ORDER_STATUS_IN_PROCESS 					= "020";
    public static final String ORDER_STATUS_TMP_LEAVE 					= "030";
    public static final String ORDER_STATUS_PENDING_SURROUNDING_REPLY 	= "040";
    public static final String ORDER_STATUS_TMP_SAVE 					= "050";
    public static final String ORDER_STATUS_SURROUNDING_REPLY 			= "060";
    public static final String ORDER_STATUS_VALID 						= "070";
    public static final String ORDER_STATUS_INVALID 					= "080";
    
}
