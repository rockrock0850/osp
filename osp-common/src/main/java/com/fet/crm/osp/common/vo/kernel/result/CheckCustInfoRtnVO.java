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

import java.util.List;

import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustBillingInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustInfoForOSPRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.BillDetailVO;

/**
 * 核資相關資訊封裝物件. <br>
 * 
 * @author AllenChen
 */
public class CheckCustInfoRtnVO {

	private CustInfoForOSPRtnVO custInfoForOSPRtnVO;
	private CustBillingInfoRtnVO custBillingInfoRtnVO;
	private AuthLevelInfoRtnVO authLevelInfoRtnVO;
	private List<BillDetailVO> billDetailVOList;

	public CustInfoForOSPRtnVO getCustInfoForOSPRtnVO() {
		return custInfoForOSPRtnVO;
	}

	public void setCustInfoForOSPRtnVO(CustInfoForOSPRtnVO custInfoForOSPRtnVO) {
		this.custInfoForOSPRtnVO = custInfoForOSPRtnVO;
	}

	public CustBillingInfoRtnVO getCustBillingInfoRtnVO() {
		return custBillingInfoRtnVO;
	}

	public void setCustBillingInfoRtnVO(CustBillingInfoRtnVO custBillingInfoRtnVO) {
		this.custBillingInfoRtnVO = custBillingInfoRtnVO;
	}

	public AuthLevelInfoRtnVO getAuthLevelInfoRtnVO() {
		return authLevelInfoRtnVO;
	}

	public void setAuthLevelInfoRtnVO(AuthLevelInfoRtnVO authLevelInfoRtnVO) {
		this.authLevelInfoRtnVO = authLevelInfoRtnVO;
	}

	public List<BillDetailVO> getBillDetailVOList() {
		return billDetailVOList;
	}

	public void setBillDetailVOList(List<BillDetailVO> billDetailVOList) {
		this.billDetailVOList = billDetailVOList;
	}

}
