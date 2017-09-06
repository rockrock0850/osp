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

package com.fet.crm.osp.kernel.core.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fet.crm.osp.common.util.IdentifierIdUtil;

/**
 * 日誌 共用元件  <br />
 * 
 * 使用於： <br />
 * <ul>
 * <li>1、BusinessLayerAspect</li>
 * <li>2、ThrowsAspect</li>
 * <li>3、SqlLogAppender</li>
 * <li>4、AccessLogInterceptor</li>
 * <li>5、JdbcDAO</li>
 * </ul>
 * 
 * @author PaulChen
 */
public class LogContext {

	private static LogContextThreadLocal context = new LogContextThreadLocal();

	private String classify; // category
	private String operation;
	private String ip;
	private String userId;
	private Date eventDt;
	private String threadId;
	private List<String> logMessage;

	private long startTime;
	private long endTime;

	private LogContext() {
		;
	}

	public static LogContext getContext() {
		LogContext logCtx = context.get();

		return logCtx;
	}

	/**
	 * 於目前執行緒中加入日誌訊息
	 * 
	 * @param logMessage
	 */
	public void append(String logMessage) {
		if (StringUtils.isNotBlank(logMessage)) {
			this.logMessage.add(logMessage);
		}
	}

	/**
	 * 清除 LogContext 物件
	 */
	public void remove() {
		context.remove();
	}

	// =========================== getter / setter =============================
	public String getThreadId() {
		return this.threadId;
	}

	public List<String> getLogMessage() {
		return this.logMessage;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
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

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	// ==============================override =================================
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	// =========================================================================
	private static class LogContextThreadLocal extends ThreadLocal<LogContext> {

		@Override
		protected LogContext initialValue() {
			LogContext logCtx = new LogContext();
			logCtx.threadId = IdentifierIdUtil.getUuid();
			logCtx.logMessage = new ArrayList<String>();

			return logCtx;
		}
	}

}
