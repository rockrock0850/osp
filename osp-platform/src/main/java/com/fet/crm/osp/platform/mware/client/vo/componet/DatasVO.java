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

package com.fet.crm.osp.platform.mware.client.vo.componet;

import java.util.List;

/**
 * 
 * @author LawrenceLai
 */
public class DatasVO {

	List confirmMsgs;
	String errorMsg;
	List promptMsgs;

	public List getConfirmMsgs() {
		return confirmMsgs;
	}

	public void setConfirmMsgs(List confirmMsgs) {
		this.confirmMsgs = confirmMsgs;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List getPromptMsgs() {
		return promptMsgs;
	}

	public void setPromptMsgs(List promptMsgs) {
		this.promptMsgs = promptMsgs;
	}

}
