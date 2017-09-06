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

import com.fet.crm.osp.common.vo.kernel.result.CacheSubscriberInfoRtnVO;

/**
 * 查詢 Cache Subscriber Info 資訊回傳物件. <br>
 * 
 * @author RichardHuang
 */
public class CacheSubscriberInfoOutputVO extends AbstractOutputVO {

	private CacheSubscriberInfoRtnVO resultBody;

	public CacheSubscriberInfoRtnVO getResultBody() {
		return resultBody;
	}

	public void setResultBody(CacheSubscriberInfoRtnVO resultBody) {
		this.resultBody = resultBody;
	}
	
}
