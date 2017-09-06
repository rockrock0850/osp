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
package com.fet.crm.osp.kernel.core.pojo;

/**
 * 未被暫停派件，且目前處於班表工作時間的人員資訊 操作用POJO物件. <br>
 * 
 * @author RichardHuang
 */
public class AvailableEmployeeInfoPOJO {
    
    private String empname; // 人員姓名
    private String orderTypeIdSet; // 「案件類型」集合
    
    /**
     * @return the empname
     */
    public String getEmpname() {
        return empname;
    }
    
    /**
     * @param empname the empname to set
     */
    public void setEmpname(String empname) {
        this.empname = empname;
    }
    
    /**
     * @return the orderTypeIdSet
     */
    public String getOrderTypeIdSet() {
        return orderTypeIdSet;
    }
    
    /**
     * @param orderTypeIdSet the orderTypeIdSet to set
     */
    public void setOrderTypeIdSet(String orderTypeIdSet) {
        this.orderTypeIdSet = orderTypeIdSet;
    }
    
}
