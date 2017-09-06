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

package com.fet.crm.osp.kernel.core.common;

/**
 * 系統 - 「功能[類]常數」. <br>
 * <br>
 * 說明: Functional Level的常數. 該常數的「影響範圍」為 特定系統「功能」作用域.
 * 
 * @author VJChou, RichardHuang
 */
public class Constant {

    public static final String SYSTEM_ID = "OSP"; // OSP 系統代號
    public static final String PROCESS_RESULT = "result"; // 處理結果
    public static final String NCP_QUERY_CUST_INFO_ACTION_URL_CONFIG = "NCP_QUERY_CUST_INFO_ACTION_URL"; // CONF_ID for NCP queryCustData Action URL
    public static final String DEFAULT_TEAM_GROUP = "M"; // 「進件組別」預設值 : M
    
    public static final String CACHE_CHANNEL = "CACHE_CHANNEL";
    public static final String CACHE_CHANNEL_POSTPAID = "CACHE_CHANNEL_POSTPAID";
    public static final String CACHE_CHANNEL_ID_POSTPAID = "CACHE_CHANNEL_ID_POSTPAID";
    public static final String CACHE_CHANNEL_ID_PASSWORD_POSTPAID = "CACHE_CHANNEL_ID_PASSWORD_POSTPAID";
    public static final String CACHE_CHANNEL_ID_PREPAID = "CACHE_CHANNEL_ID_PREPAID";
    public static final String CACHE_CHANNEL_ID_PASSWORD_PREPAID = "CACHE_CHANNEL_ID_PASSWORD_PREPAID";
    public static final String CACHE_CHANNEL_PREPAID = "CACHE_CHANNEL_PREPAID";
    public static final String IVRCODE = "IVRCODE";
    public static final String RETAIL_CHANNEL_TYPE = "Retail";
    public static final String VASS_CHANNEL_TYPE = "VASS";
    public static final String ESB_API_PASSWORD = "ESB_API_PASSWORD";
    public static final String ESB_API_USER_ID = "ESB_API_USER_ID";
    public static final int BUSINESS_ENTITY_NUMBER = 110154;
    
    public static final String ORDER_STATUS_REPLY = "060"; // 系統回覆
    public static final String GET_PROMOTION_DETAILS = "GET_PROMOTION_DETAILS"; // CAR Service Command 參數:取得促代詳細資訊
    public final static String CAR_SERVCIE_RESULT_CONTRACT_LIST = "ContractList"; 
}
