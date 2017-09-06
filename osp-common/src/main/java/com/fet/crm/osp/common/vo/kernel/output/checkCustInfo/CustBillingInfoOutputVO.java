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

package com.fet.crm.osp.common.vo.kernel.output.checkCustInfo;

import com.fet.crm.osp.common.vo.kernel.output.AbstractOutputVO;
import com.fet.crm.osp.common.vo.kernel.result.checkCustInfo.CustBillingInfoRtnVO;

/**
 * 查詢 Billing 相關資訊回傳物件. <br>
 * 
 * @author RichardHuang
 */
public class CustBillingInfoOutputVO extends AbstractOutputVO {

	private CustBillingInfoRtnVO resultBody;

	public CustBillingInfoRtnVO getResultBody() {
		return resultBody;
	}

	public void setResultBody(CustBillingInfoRtnVO resultBody) {
		this.resultBody = resultBody;
	}
	
}
