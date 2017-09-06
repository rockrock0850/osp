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

import com.fet.crm.osp.platform.mware.client.vo.componet.DatasVO;
import com.fet.crm.osp.platform.mware.client.vo.componet.MessageVO;

/**
 * 
 * @author LawrenceLai
 */
public class NCPRESTReturnVO {

	private MessageVO message;
	private DatasVO datas;
	private String status;

	public MessageVO getMessage() {
		return message;
	}

	public void setMessage(MessageVO message) {
		this.message = message;
	}

	public DatasVO getDatas() {
		return datas;
	}

	public void setDatas(DatasVO datas) {
		this.datas = datas;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
