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

/**
 * 取得學生溫暖長青申請方案相關資訊 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 * 
 * @author RichardHuang
 */
public class SpecialOfferParamVO extends AbstractESBParamVO {
    
	private String rocId;

	/**
	 * @return the rocId
	 */
	public String getRocId() {
		return rocId;
	}

	/**
	 * @param rocId the rocId to set
	 */
	public void setRocId(String rocId) {
		this.rocId = rocId;
	}
    
}
