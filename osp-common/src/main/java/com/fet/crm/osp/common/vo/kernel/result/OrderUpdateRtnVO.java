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
 * 工單執行更新後所回傳的封裝物件. <br>
 * 
 * @author VJChou, RichardHuang
 */
public class OrderUpdateRtnVO {
    
    private String poolKey;
	private String sourceSysId;
	private String sourceOrderId;
	private String ntAccount;
	
	public String getPoolKey() {
        return poolKey;
    }
    
    public void setPoolKey(String poolKey) {
        this.poolKey = poolKey;
    }

	public String getSourceSysId() {
		return sourceSysId;
	}

	public void setSourceSysId(String sourceSysId) {
		this.sourceSysId = sourceSysId;
	}

	public String getSourceOrderId() {
		return sourceOrderId;
	}

	public void setSourceOrderId(String sourceOrderId) {
		this.sourceOrderId = sourceOrderId;
	}

	public String getNtAccount() {
		return ntAccount;
	}

	public void setNtAccount(String ntAccount) {
		this.ntAccount = ntAccount;
	}

}
