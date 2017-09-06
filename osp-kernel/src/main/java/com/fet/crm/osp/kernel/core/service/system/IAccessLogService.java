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

package com.fet.crm.osp.kernel.core.service.system;

import com.fet.crm.osp.kernel.core.vo.LogInfoVO;

/**
 * 日誌管理 服務介面
 * 
 * @author PaulChen
 */
public interface IAccessLogService {

    /**
     * 儲存功能執行日誌
     * 
     * @param logInfo
     */
    void createAccessLog(LogInfoVO logInfo);

}
