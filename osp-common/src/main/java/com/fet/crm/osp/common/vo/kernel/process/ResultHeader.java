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

package com.fet.crm.osp.common.vo.kernel.process;

import java.util.Date;

/**
 * 處理結果回傳的標準檔頭，包括處理結果代碼, 處理結果代碼所對應的訊息. <br>
 * 
 * @author VJChou
 */
public class ResultHeader {

    private String returnCode; // 處理結果代碼
    private String returnMessage; // 處理結果代碼所對應的訊息
    private Date executeDt; // = new Date(); // 回傳執行時間 [最後離開Service的時間]

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Date getExecuteDt() {
        executeDt = new Date(); // add at 2017-03-23
        return executeDt;
    }

    public void setExecuteDt(Date executeDt) {
        this.executeDt = executeDt;
    }
    
}
