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

package com.fet.crm.osp.common.vo.kernel.result;

/**
 * 工單執行新增後所回傳的封裝物件. <br>
 * 
 * @author VJChou
 */
public class OrderLoadRtnVO {
	private String poolKey;
    
    /**
     * @return the poolKey
     */
    public String getPoolKey() {
        return poolKey;
    }
    
    /**
     * @param poolKey the poolKey to set
     */
    public void setPoolKey(String poolKey) {
        this.poolKey = poolKey;
    }
}
