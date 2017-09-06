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

import java.util.List;

import com.fet.crm.osp.common.vo.kernel.result.SOATicketDetailRtnVO;

/**
 * 查詢 SOA 進件詳細資訊回傳物件. <br>
 * 
 * @author RichardHuang
 */
public class SOATicketDetailOutputVO extends AbstractOutputVO {

	private List<SOATicketDetailRtnVO> resultBody;

	public List<SOATicketDetailRtnVO> getResultBody() {
		return resultBody;
	}

	public void setResultBody(List<SOATicketDetailRtnVO> resultBody) {
		this.resultBody = resultBody;
	}
	
}
