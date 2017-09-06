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

package com.fet.crm.osp.common.vo.kernel.process.resp;

import com.fet.crm.osp.common.vo.kernel.process.ResultHeader;

/**
 * 回覆請求檔頭，目前是將「處理結果檔頭」直接寫這邊. <br>
 * 往後的擴充性可包含: 處理時間... etc. <br>
 * 
 * @author VJChou
 */
public class RespHeader {

    private ResultHeader resultHeader;
//    private String processTime; // 處理時間

    public ResultHeader getResultHeader() {
        return resultHeader;
    }

    public void setResultHeader(ResultHeader resultHeader) {
        this.resultHeader = resultHeader;
    }

}
