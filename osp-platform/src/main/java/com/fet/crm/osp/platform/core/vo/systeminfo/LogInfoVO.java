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

package com.fet.crm.osp.platform.core.vo.systeminfo;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fet.crm.osp.platform.core.common.LogContext;

/**
 * 
 * @author LawrenceLai
 */
public class LogInfoVO {

	private String threadId;
	private String userId;
	private String classify;
	private String operation;
	private Date logDate;
	private String ipAddress;
	private double duration;
	private String requestContent;
	
	public LogInfoVO(LogContext contetx) {
		// 將值複制至 LogVO 中
		BeanUtils.copyProperties(contetx, this);

		// 計算執行時間
		duration = ((double)(contetx.getEndTime() - contetx.getStartTime())) / 1000D;
	}

	public LogInfoVO() {

	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

}
