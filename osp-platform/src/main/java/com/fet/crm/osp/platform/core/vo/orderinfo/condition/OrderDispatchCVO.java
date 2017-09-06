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

package com.fet.crm.osp.platform.core.vo.orderinfo.condition;

/**
 * [分派人員維護] 查詢條件 資料物件
 * 
 * @author LawrenceLai,AndrewLee
 */
public class OrderDispatchCVO {

    private String pauseStartTime;
    private String pauseEndTime;

    public String getPauseStartTime() {
        return pauseStartTime;
    }

    public void setPauseStartTime(String pauseStartTime) {
        this.pauseStartTime = pauseStartTime;
    }

    public String getPauseEndTime() {
        return pauseEndTime;
    }

    public void setPauseEndTime(String pauseEndTime) {
        this.pauseEndTime = pauseEndTime;
    }

}
