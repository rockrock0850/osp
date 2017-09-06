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
package com.fet.crm.osp.kernel.core.task.component;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.kernel.core.service.osp.IOSPPoolMainService;

/**
 * 班別派件 執行類別
 * 
 * @author PaulChen
 */
@Component
@Scope("prototype")
public class DispatcherExecutor implements Runnable {

	private String empNo; // 員工編號
	private Date startDt; // 班表開始時間
	private Date endDt; // 班表結束時間
	private String teamGroup; // 群組別

	private ScheduledFuture<?> scheduleFeture;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IOSPPoolMainService ospPoolMainService;

	@Override
	public void run() {
		boolean isExecute = isExecute();

		if (isExecute) {
			executeInternal();
		}
	}

	/**
	 * 執行派件任務（依據不同班別類型）
	 */
	private void executeInternal() {
	    logger.info("執行派件任務 [Start]");
	    
		int dispatchCnt = ospPoolMainService.dispatch(empNo, teamGroup);
		int reDispatchCnt = ospPoolMainService.reDispatch();
		
		logger.info("派件給「處理人員」: empNo = " + empNo + ", 案件筆數 = " + dispatchCnt);
		logger.info("將母單已派件，但尚未被派件的子單，補派件給母單的「處理人員」: 案件筆數 = " + reDispatchCnt);
		logger.info("執行派件任務 [End]");
	}

	/**
	 * 判斷目前時間是否為上（startDt） / 下（endDt）班時間
	 * 
	 * @return boolean
	 */
	private boolean isExecute() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime start = LocalDateTime.fromDateFields(startDt);
		LocalDateTime end = LocalDateTime.fromDateFields(endDt);

		// 目前時間在開始時間之後及目前時間在結束時間之前
		return now.isAfter(start) && now.isBefore(end);
	}

	// ===============================================
	// 開放給 ShiftDispatcherTask 使用的 method.
	// ===============================================

	/**
	 * 判斷是否取消排程
	 * 
	 * @return boolean
	 */
	public boolean isTimeToStop() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime end = LocalDateTime.fromDateFields(endDt);

		// 目前時間在結束時間之後。
		return now.isAfter(end);
	}

	/**
	 * 停止執行
	 * 
	 * @return boolean
	 */
	public boolean cancel() {
		if (scheduleFeture != null && scheduleFeture.isCancelled() == false) {
			logger.info(" prepared to cancel with empNo = " + empNo);

			return scheduleFeture.cancel(false);
		}

		return false;
	}

	// ===============================================
	// getter / setter
	// ===============================================
	public void setStartDate(Date startDt) {
		this.startDt = startDt;
	}

	public void setEndDate(Date endDt) {
		this.endDt = endDt;
	}

	public void setScheduleFeture(ScheduledFuture<?> scheduleFeture) {
		this.scheduleFeture = scheduleFeture;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public void setTeamGroup(String teamGroup) {
		this.teamGroup = teamGroup;
	}

	// ===============================================
	// override
	// ===============================================
	@Override
	public int hashCode() {
		int hashCode = HashCodeBuilder.reflectionHashCode(this, false);
		return hashCode;
	}

}
