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

package com.fet.crm.osp.platform.core.vo.dispatchinfo.condition;

/**
 * [班別類型] 查詢條件封裝物件
 *
 * @author AndrewLee
 */
public class ShiftTypeInfoCVO {

    private String shiftTypeName;
    private String shiftTypeId;

    public String getShiftTypeName() {
        return shiftTypeName;
    }

    public void setShiftTypeName(String shiftTypeName) {
        this.shiftTypeName = shiftTypeName;
    }

    public String getShiftTypeId() {
        return shiftTypeId;
    }

    public void setShiftTypeId(String shiftTypeId) {
        this.shiftTypeId = shiftTypeId;
    }

}
