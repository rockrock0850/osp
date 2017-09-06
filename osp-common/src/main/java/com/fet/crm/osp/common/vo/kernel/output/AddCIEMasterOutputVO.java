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

package com.fet.crm.osp.common.vo.kernel.output;

import com.fet.crm.osp.common.vo.kernel.result.cie.AddCIEMasterRtnVO;

/**
 * 新增 CIE Master 資料回傳物件. <br>
 * 
 * @author RichardHuang
 */
public class AddCIEMasterOutputVO extends AbstractOutputVO {

	private AddCIEMasterRtnVO resultBody;

	public AddCIEMasterRtnVO getResultBody() {
		return resultBody;
	}

	public void setResultBody(AddCIEMasterRtnVO resultBody) {
		this.resultBody = resultBody;
	}
	
}
