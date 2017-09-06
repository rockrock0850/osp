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

package com.fet.crm.osp.kernel.core.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.kernel.core.common.util.DateUtil;
import com.fet.crm.osp.kernel.core.service.osp.IOSPPoolMainService;
import com.fet.crm.osp.kernel.core.task.component.DispatcherExecutor;

/**
 * 依據每日班別產生對映執行派件（dispatcher） 產生器
 * 
 * @author PaulChen, RichardHuang
 */
@Component
@Configurable
@EnableScheduling
public class ShiftDispatcherTaskManager {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TaskScheduler taskScheduler;
	@Autowired
	private ApplicationContext context;
	@Autowired
	private IOSPPoolMainService ospPoolMainService;
	
	private List<DispatcherExecutor> scheduleTaskList = new ArrayList<DispatcherExecutor>();
	
	/**
	 *	依據各班別上/下班時間，產生對映的自動派件類別(task)
	 */
	public void initDailyShiftDispatcherTask() {
		// 1. 取得每日班別
		// 2. 依據各班別產生派件執行器
		//    2-1. 先執行「cleanOutOfTimeTask(true);」 強制全部清除
		logger.info("initDailyShiftDispatcherTask [START]");
		
		cleanShiftDispatcherTask(); // 強制清除所有自動派件執行任務
		
		List<Map<String, Object>> dispatchEmpInfoList = ospPoolMainService.getDispatchEmpInfo();
		
		for(Map<String, Object> dispatchEmpInfo : dispatchEmpInfoList) {
			String empNo = MapUtils.getString(dispatchEmpInfo, "EMPNO");
			String startTime = MapUtils.getString(dispatchEmpInfo, "START_TIME");
			String endTime = MapUtils.getString(dispatchEmpInfo, "END_TIME");
			String teamGroup = MapUtils.getString(dispatchEmpInfo, "TEAM_GROUP");
			String cycleMinutes = MapUtils.getString(dispatchEmpInfo, "CYCLE_MINUTES"); // 分鐘數
			String lastDispatchMinusMinutes = MapUtils.getString(dispatchEmpInfo, "LAST_DISPATCH_MINUS_MINUTES"); // 分鐘數
			
			long period = Integer.valueOf(cycleMinutes) * 60 * 1000L; // 毫秒數
			int minusMinutes = -1 * Integer.valueOf(lastDispatchMinusMinutes);
			
			Date startDate = parse(startTime);
			Date endDate = parse(endTime);
			
			Calendar lastDispatchDate = Calendar.getInstance();
			lastDispatchDate.setTime(endDate);
			lastDispatchDate.add(Calendar.MINUTE, minusMinutes);
			
			DispatcherExecutor executor = context.getBean(DispatcherExecutor.class);
			ScheduledFuture<?> scheduledFuture = taskScheduler.scheduleAtFixedRate(executor, startDate, period);
			executor.setScheduleFeture(scheduledFuture);
			executor.setEmpNo(empNo);
            executor.setEndDate(lastDispatchDate.getTime());
            executor.setStartDate(startDate);
            executor.setTeamGroup(teamGroup);
			
			scheduleTaskList.add(executor);
			
			logger.info("Add dispatcherExecutor to scheduleTask List : DispatcherExecutor = [ empNo = " + empNo 
			        + ", startDate = " + startDate + ", endDate = " + endDate + ", teamGroup = " + teamGroup);
		}
		
		logger.info("initDailyShiftDispatcherTask [END]");
		
	}
	
	/**
	 * 清除不在執行時間內的任務
	 * 
	 * 預設於每日 08 ~ 23 點每 60 秒執行一次
	 */
	@Scheduled(cron = "0/60 * 08-23 * * *")
	public void cleanOutOfTimeTask() {
		logger.info("cleanOutOfTimeTask [START]");
		cleanOutOfTimeTask(false);
		logger.info("cleanOutOfTimeTask [END]");
	}
	
	/**
	 * 強制清除所有自動派件執行任務
	 */
	public void cleanShiftDispatcherTask() {
	    logger.info("cleanShiftDispatcherTask [START]");
        cleanOutOfTimeTask(true);
        logger.info("cleanShiftDispatcherTask [END]");
	}
	
	// ==============================================================
	//     tools method
	// ==============================================================
	
	/**
	 * 將字串日期轉為物件
	 * 
	 * @param dtStr
	 * @return
	 */
	private Date parse(String dtStr) {
		String now = DateUtil.formatDt(new Date(), DateUtil.DATE_PATTERN);
		
		String pattern = StringUtils.join(DateUtil.DATE_PATTERN, " ", "HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		String fullDtStr = StringUtils.join(now, " ", dtStr);
		
		try {
			return sdf.parse(fullDtStr);
		} catch (ParseException e) {
		}
		
		return null;
	}
	
	/**
	 * 清除已超過時間區間的任務
	 * 
	 * @param force
	 * 			是否強制清除
	 */
	private void cleanOutOfTimeTask(boolean force) {
		for(int i = 0 ; i < scheduleTaskList.size() ; i++) {
			DispatcherExecutor task = scheduleTaskList.get(i);
			
			boolean isTimeToStop = task.isTimeToStop();
			if (isTimeToStop || force) {
				boolean isCancel = task.cancel();
				
				// 取消成功後，再將此參照移除
				if(isCancel || force) {
					scheduleTaskList.remove(i--);
				}
			}
		}
	}
	
}
