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

package com.fet.crm.osp.platform.core.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.vo.dispatchinfo.OrderTypeInfoVO;

/**
 * 計算工單相關日期. <br>
 * 
 * @author VJChou, Adam Yeh
 */
@Component
public class ProcessTimeUtil {
	
	@Autowired
	private CacheReferenceUtil cacheReferenceUtil;
	
	/**
	 * 取得預計完成日. <br>
	 * modify at 2017-06-16 by V.J, Judy
	 * 
	 * @param orderTypeInfo
	 * @param expectProcessTime
	 * @return String
	 */
	public String getExpectCompleteTime(OrderTypeInfoVO orderTypeInfo, String expectProcessTime) {
		// String expectProcessTime, yyyy-mm-dd hh24:mi:ss
		Date date = DateUtil.toDate(expectProcessTime, DateUtil.DATE_PATTERN);
		String time = expectProcessTime.split("\\s+")[1];
		String srcCreateDtStr = DateUtil.fromDate(date); // yyyy-mm-dd
		String srcCreateTimeStr = time.replace(":", ""); // hh24mi 1610
		String kpiDayType = orderTypeInfo.getKpiDayType(); // W_DAY, C_DAY
		String chkCreateDate = orderTypeInfo.getChkCreateDate(); // Y, N

		String beforeCreateDate = null;
		if (orderTypeInfo.getBeforeCreateDate() != null) {
			beforeCreateDate = orderTypeInfo.getBeforeCreateDate().replace(":", ""); // hh24mi
		}
		
		String isRegularTime = orderTypeInfo.getIsRegularTime(); // Y, N
		String regularTime = String.valueOf(orderTypeInfo.getRegularTime()); // 分鐘數
		String startCountTime = orderTypeInfo.getStartCountTime(); // hh24:mi

		// 決定要使用那種日曆
		List<String> dayCalList = cacheReferenceUtil.getCDayCalList(); // 日曆日
		if ("W_DAY".equals(kpiDayType)) {
			dayCalList = cacheReferenceUtil.getWDayCalList(); // 工作日
		}

		// 日期
		String currentDate = srcCreateDtStr;
		String nextDate = getNextDate(dayCalList, srcCreateDtStr, 1);

		// 預計完成日
		String expectCompleteTime = null;

		if ("Y".equals(chkCreateDate)) {
			// 轉24h進制比較
			int srcCreateTimeInt = Integer.parseInt(srcCreateTimeStr);
			int beforeCreateDateInt = Integer.parseInt(beforeCreateDate);
			boolean early = srcCreateTimeInt <= beforeCreateDateInt;

			if ("Y".equals(isRegularTime)) {
				if (early) {
					expectCompleteTime = getTimeCalculation(expectProcessTime, regularTime);
				} else {
					expectCompleteTime = getTimeCalculation(nextDate + " " + startCountTime + ":00", regularTime);
				}
			}
			if ("N".equals(isRegularTime)) {
				if (early) {
					expectCompleteTime = currentDate + " " + "23:59:59";
				} else {
					expectCompleteTime = nextDate + " " + "23:59:59";
				}
			}
		}
		
		if ("N".equals(chkCreateDate) && "Y".equals(isRegularTime)) {
			expectCompleteTime = getTimeCalculation(expectProcessTime, regularTime);
		}

		return expectCompleteTime;
	}

	/**
	 * 計算時間. <br>
	 * 
	 * @param sourceTime
	 * @param diffTime
	 * @return String
	 */
	private String getTimeCalculation(String sourceTime, String diffTime) {
		// org.joda.time.DateTime
		String pattern = "yyyy-MM-dd HH:mm:ss";
		DateTime dateTime = DateTime.parse(sourceTime, DateTimeFormat.forPattern(pattern));

		Date rsDt = dateTime.plusMinutes(Integer.valueOf(diffTime)).toDate(); // 增加分鐘數

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		return sdf.format(rsDt);
	}

	/**
	 * 取得「下一個指定日期」. <br>
	 * 
	 * @param dayCalList
	 * @param targetDt
	 * @param diff
	 * @return String
	 */
	private String getNextDate(List<String> dayCalList, String targetDt, int diff) {
		int index = dayCalList.indexOf(targetDt);

		if (index != -1) {
			return dayCalList.get(index + diff);
		}

		return null;
	}

}
