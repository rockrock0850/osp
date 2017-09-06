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

package com.fet.crm.osp.common.vo.kernel.output;

import com.fet.crm.osp.common.vo.kernel.process.ResultHeader;

/**
 * 所有處理結果都需要回傳的資料. <br>
 * 
 * @author VJChou
 */
public abstract class AbstractOutputVO {

    private ResultHeader resultHeader;

    public ResultHeader getResultHeader() {
        return resultHeader;
    }

    public void setResultHeader(ResultHeader resultHeader) {
        this.resultHeader = resultHeader;
    }

}
