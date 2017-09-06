/**
 * Copyright (c) 2015 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */

package com.fet.crm.osp.kernel.core.vo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;
import com.fet.crm.osp.kernel.core.common.LogContext;

/**
 * 日誌處理 包覆類別
 * 
 * @author PaulChen
 */
public class LogInfoVO extends AbstractOspBaseVO {

	private String classify; // category
	private String operation;
	private String ip;
	private String userId;
	private Date eventDt;
	private String threadId;
	private List<String> logMessage;
	private double duration;

	public LogInfoVO(LogContext src) {
		// 將值複制至 LogVO 中
		BeanUtils.copyProperties(src, this);

		// 計算執行時間
		duration = ((double)(src.getEndTime() - src.getStartTime())) / 1000D;
	}

	public LogInfoVO() {

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getEventDt() {
		return eventDt;
	}

	public void setEventDt(Date eventDt) {
		this.eventDt = eventDt;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public List<String> getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(List<String> logMessage) {
		this.logMessage = logMessage;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

}
