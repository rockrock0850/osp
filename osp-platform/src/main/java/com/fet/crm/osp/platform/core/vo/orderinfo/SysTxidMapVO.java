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

package com.fet.crm.osp.platform.core.vo.orderinfo;

import java.util.Date;

/**
 * 
 * @author Adam Yeh
 */
public class SysTxidMapVO {

    private String ospKey;
    private String orderMId;
	private String txid;
	private Date createDate;
	private Date updateDate;
	
	public String getOspKey() {
		return ospKey;
	}
	public void setOspKey(String ospKey) {
		this.ospKey = ospKey;
	}
	public String getOrderMId() {
		return orderMId;
	}
	public void setOrderMId(String orderMId) {
		this.orderMId = orderMId;
	}
	public String getTxid() {
		return txid;
	}
	public void setTxid(String txid) {
		this.txid = txid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}

