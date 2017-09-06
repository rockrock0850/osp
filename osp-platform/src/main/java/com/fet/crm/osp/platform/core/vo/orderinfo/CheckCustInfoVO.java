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

/**
 * 
 * @author Lawrence.Lai
 */
public class CheckCustInfoVO {

private String arpb;
	
	private String customerScore;
	
	private String cycleDate;
	
	private String paymentMethod;

	public String getArpb() {
		return arpb;
	}

	public void setArpb(String arpb) {
		this.arpb = arpb;
	}

	public String getCustomerScore() {
		return customerScore;
	}

	public void setCustomerScore(String customerScore) {
		this.customerScore = customerScore;
	}

	public String getCycleDate() {
		return cycleDate;
	}

	public void setCycleDate(String cycleDate) {
		this.cycleDate = cycleDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
}

