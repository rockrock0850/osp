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

import com.fet.crm.osp.common.vo.kernel.result.OrderUpdateRtnVO;

/**
 * 工單更新結果回傳物件. <br>
 * 
 * @author VJChou
 */
public class OrderUpdateOutputVO extends AbstractOutputVO {

	private OrderUpdateRtnVO resultBody;

	public OrderUpdateRtnVO getResultBody() {
		return resultBody;
	}

	public void setResultBody(OrderUpdateRtnVO resultBody) {
		this.resultBody = resultBody;
	}

}
