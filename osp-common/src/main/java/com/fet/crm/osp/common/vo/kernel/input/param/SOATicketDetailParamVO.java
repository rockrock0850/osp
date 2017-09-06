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
package com.fet.crm.osp.common.vo.kernel.input.param;

import java.util.List;

/**
 * 取得 SOA 進件詳細資訊 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 * 
 * @author RichardHuang
 */
public class SOATicketDetailParamVO extends AbstractESBParamVO {
	
	private List<String> msisdnList; // 門號清單

	/**
	 * @return the msisdnList
	 */
	public List<String> getMsisdnList() {
		return msisdnList;
	}

	/**
	 * @param msisdnList the msisdnList to set
	 */
	public void setMsisdnList(List<String> msisdnList) {
		this.msisdnList = msisdnList;
	}
    
}
