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

package com.fet.crm.osp.kernel.mware.server.vo.process.resp;

import java.util.Map;

/**
 * 請求回覆的處理結果，對應到後端的各種可能回傳，在此採用Map的設計以保持往後擴充的彈性. <br>
 * Key: 回傳結果的索引鍵值. <br>
 * Value: 回傳結果內容. <br>
 * 
 * @author VJChou
 */
public class RespBody {

    private Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
