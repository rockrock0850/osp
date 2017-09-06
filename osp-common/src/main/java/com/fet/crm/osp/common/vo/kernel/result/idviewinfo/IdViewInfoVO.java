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
package com.fet.crm.osp.common.vo.kernel.result.idviewinfo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 查詢歷史客資資訊封裝物件
 * 
 * @author RichardHuang
 */
public class IdViewInfoVO {
	
	private String msisdn; // 電話
	
	private String accountId; // 帳號
	
	private String statusDesc; // 狀態
	
	private Date lastSubStDt; // 永停日
	
	private String subStReasonDscr; // 原因
	
	private Date initActiveDt; // 啟用日
	
	private String lastName; // 姓名
	
	private String productTypeDesc; // 產品別
	
	private BigDecimal arpb; // 均帳

	/**
	 * @return the msisdn
	 */
	public String getMsisdn() {
		return msisdn;
	}

	/**
	 * @param msisdn the msisdn to set
	 */
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the statusDesc
	 */
	public String getStatusDesc() {
		return statusDesc;
	}

	/**
	 * @param statusDesc the statusDesc to set
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	/**
	 * @return the lastSubStDt
	 */
	public Date getLastSubStDt() {
		return lastSubStDt;
	}

	/**
	 * @param lastSubStDt the lastSubStDt to set
	 */
	public void setLastSubStDt(Date lastSubStDt) {
		this.lastSubStDt = lastSubStDt;
	}

	/**
	 * @return the subStReasonDscr
	 */
	public String getSubStReasonDscr() {
		return subStReasonDscr;
	}

	/**
	 * @param subStReasonDscr the subStReasonDscr to set
	 */
	public void setSubStReasonDscr(String subStReasonDscr) {
		this.subStReasonDscr = subStReasonDscr;
	}

	/**
	 * @return the initActiveDt
	 */
	public Date getInitActiveDt() {
		return initActiveDt;
	}

	/**
	 * @param initActiveDt the initActiveDt to set
	 */
	public void setInitActiveDt(Date initActiveDt) {
		this.initActiveDt = initActiveDt;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the arpb
	 */
	public BigDecimal getArpb() {
		return arpb;
	}

	/**
	 * @param arpb the arpb to set
	 */
	public void setArpb(BigDecimal arpb) {
		this.arpb = arpb;
	}

	/**
	 * @return the productTypeDesc
	 */
	public String getProductTypeDesc() {
		return productTypeDesc;
	}

	/**
	 * @param productTypeDesc the productTypeDesc to set
	 */
	public void setProductTypeDesc(String productTypeDesc) {
		this.productTypeDesc = productTypeDesc;
	}
	
}
