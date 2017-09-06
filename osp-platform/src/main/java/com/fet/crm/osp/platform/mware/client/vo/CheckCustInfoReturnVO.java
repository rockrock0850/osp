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

package com.fet.crm.osp.platform.mware.client.vo;

import java.util.List;

/**
 * 核資相關資訊封裝物件. <br>
 * 
 * @author AllenChen
 */
public class CheckCustInfoReturnVO {

	private CustInfoForOSPReturnVO custInfoForOSPReturnVO;
	private CustBillingInfoReturnVO custBillingInfoReturnVO;
	private AuthLevelInfoReturnVO authLevelInfoReturnVO;
	private List<BillDetailVO> billDetailVOList;

	public CustInfoForOSPReturnVO getCustInfoForOSPReturnVO() {
		return custInfoForOSPReturnVO;
	}

	public void setCustInfoForOSPReturnVO(CustInfoForOSPReturnVO custInfoForOSPReturnVO) {
		this.custInfoForOSPReturnVO = custInfoForOSPReturnVO;
	}

	public CustBillingInfoReturnVO getCustBillingInfoReturnVO() {
		return custBillingInfoReturnVO;
	}

	public void setCustBillingInfoReturnVO(CustBillingInfoReturnVO custBillingInfoReturnVO) {
		this.custBillingInfoReturnVO = custBillingInfoReturnVO;
	}

	public AuthLevelInfoReturnVO getAuthLevelInfoReturnVO() {
		return authLevelInfoReturnVO;
	}

	public void setAuthLevelInfoReturnVO(AuthLevelInfoReturnVO authLevelInfoReturnVO) {
		this.authLevelInfoReturnVO = authLevelInfoReturnVO;
	}

	public List<BillDetailVO> getBillDetailVOList() {
		return billDetailVOList;
	}

	public void setBillDetailVOList(List<BillDetailVO> billDetailVOList) {
		this.billDetailVOList = billDetailVOList;
	}

}
