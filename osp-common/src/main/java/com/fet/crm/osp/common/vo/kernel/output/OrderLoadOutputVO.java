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

import com.fet.crm.osp.common.vo.kernel.result.OrderLoadRtnVO;

/**
 * 工單新增結果回傳物件. <br>
 * 
 * @author VJChou
 */
public class OrderLoadOutputVO extends AbstractOutputVO {

	private OrderLoadRtnVO resultBody; // 回傳內容為新增成功後產生的poolKey

	public OrderLoadRtnVO getResultBody() {
		return resultBody;
	}

	public void setResultBody(OrderLoadRtnVO resultBody) {
		this.resultBody = resultBody;
	}

}
