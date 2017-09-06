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

package com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;

/**
 * 流程步籤資訊 封裝物件
 * 
 * @author PaulChen
 */
public class BuzFlowStepVO extends AbstractOspBaseVO {

	private String stepId; // stepId
	private String stepNm; // 標籤名稱
	private String sortSequence; // 排序

	public BuzFlowStepVO() {

	}

	public String getStepNm() {
		return stepNm;
	}

	public void setStepNm(String stepNm) {
		this.stepNm = stepNm;
	}

	public String getSortSequence() {
		return sortSequence;
	}

	public void setSortSequence(String sortSequence) {
		this.sortSequence = sortSequence;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

}
