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

package com.fet.crm.osp.platform.core.service.systeminfo;

import java.util.List;

import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.content.compare.BuzVPOSCompareResultVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.content.compare.BuzVPOSCompareVO;


/**
 *
 * @author AndrewLee
 */
public interface IBuzVPosCompareService {
    
    /**
     * 傳入檔案.執行對比,完成後將結果Insert至DB
     * 
     * @param buzVPOSCompareVO
     * @return boolean
     */
    boolean doCompareExcel(BuzVPOSCompareVO buzVPOSCompareVO);

    /**
     * 取得檢核結果
     * 
     * @return List
     */
    List<BuzVPOSCompareResultVO> queryCompareResult();
}
