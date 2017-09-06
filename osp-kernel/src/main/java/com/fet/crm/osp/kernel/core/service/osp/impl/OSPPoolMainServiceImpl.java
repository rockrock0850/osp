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

package com.fet.crm.osp.kernel.core.service.osp.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.fet.crm.osp.common.util.DateUtil;
import com.fet.crm.osp.common.util.IdentifierIdUtil;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.kernel.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.kernel.core.db.warehouse.OrderMainOspWarehouse;
import com.fet.crm.osp.kernel.core.pojo.AvailableEmployeeInfoPOJO;
import com.fet.crm.osp.kernel.core.pojo.CalendarInfoPOJO;
import com.fet.crm.osp.kernel.core.pojo.DispatchOrderPOJO;
import com.fet.crm.osp.kernel.core.pojo.FlowSourceMapInfoPOJO;
import com.fet.crm.osp.kernel.core.pojo.RedispatchOrderPojo;
import com.fet.crm.osp.kernel.core.pojo.TxIdMapPOJO;
import com.fet.crm.osp.kernel.core.service.osp.IOSPPoolMainService;
import com.fet.crm.osp.kernel.core.service.pool.ITicketPoolMainService;
import com.fet.crm.osp.kernel.core.vo.orderinfo.OrderMainMetadataVO;

/**
 * 面向OSP工單[ORDER_MAIN_OSP]維護 主要服務 實作. <br>
 * 
 * @author VJChou, RichardHuang
 */
@Service
public class OSPPoolMainServiceImpl implements IOSPPoolMainService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("ospJdbcDAO")
	private JdbcDAO ospJdbcDAO;
	
	@Autowired
	private ITicketPoolMainService ticketPoolMainService;
	
	@Autowired
	private OrderMainOspWarehouse orderMainOspWarehouse;
	
	@Override
	public List<Map<String, Object>> getDispatchEmpInfo() {
	    String sqlText = ResourceFileUtil.SQL_OSP.getResource("GET_DISPATCH_TASK_INFO");
	
	    List<Map<String, Object>> dataList = ospJdbcDAO.queryForList(sqlText);
	
	    return dataList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int dispatch(String employeeNo, String teamGroup) {
		logger.info("dispatch [Start]");
    	logger.info("params = [ employeeNo = " + employeeNo + ", teamGroup = " + teamGroup);
    	
		// 取得未被暫停派件，且目前處於班表工作時間的人員資訊，包含「姓名」和人員在目前班別中的 SKILL 所對應的「案件類型」集合
	    AvailableEmployeeInfoPOJO employeeInfo = getEmployeeInfo(employeeNo, teamGroup);
	
		// 若處理人員未被暫停派件，且目前處於班表工作時間，則進行派件
		if (employeeInfo != null) {
			String employeeName = employeeInfo.getEmpname();
			String orderTypeIdSet = employeeInfo.getOrderTypeIdSet();
			
			List<DispatchOrderPOJO> dispatchOrderList = getUndispatchedOrderList(teamGroup, orderTypeIdSet);
			
			if (CollectionUtils.isNotEmpty(dispatchOrderList)) {
			    String batchNo = IdentifierIdUtil.getUuid();
			    String status = "010"; // 案件狀態：已分派，未處理
			    
			    for (DispatchOrderPOJO dispatchOrder : dispatchOrderList) {
			        String poolKey = dispatchOrder.getPoolKey();
	                String orderMId = dispatchOrder.getOrderMId();
	                String msisdn = dispatchOrder.getMsisdn();
	                
	                dispatchByOrderMId(orderMId, msisdn, employeeNo, employeeName, batchNo, status);
	                updateOrderStatus2TicketPool(poolKey, status, employeeNo);
			    }
			    
			    logger.info("dispatch [End]");
			    
			    return dispatchOrderList.size();
			}
		}
		
		return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int reDispatch() {
	    List<RedispatchOrderPojo> redispatchOrderList = getRedispatchChildOrderList();
	    
	    if (CollectionUtils.isNotEmpty(redispatchOrderList)) {
	        String batchNo = IdentifierIdUtil.getUuid();
	        String status = "010"; // 案件狀態：已分派，未處理
	        
	        for (RedispatchOrderPojo redispatchOrder : redispatchOrderList) {
	            String poolKey = redispatchOrder.getPoolKey();
	            String processUserId = redispatchOrder.getProcessUserId();
	            String processUserName = redispatchOrder.getProcessUserName();
	            String orderMId = redispatchOrder.getOrderMId();
	            String msisdn = redispatchOrder.getMsisdn();
	            
	            doReDispatchByOrderMId(orderMId, processUserId, processUserName, msisdn, batchNo, status);
	            updateOrderStatus2TicketPool(poolKey, status, processUserId);
	        }
	        
	        return redispatchOrderList.size();
	    }
	    
	    return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateOrderStatus2OSPFromSurrounding(String txId) {
		String sqlText = ResourceFileUtil.SQL_SYSTEM.getResource("UPDATE_TXID_STATUS_BY_TXID");
		
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("txId", txId);
	    
	    int updateTxIdStatusEffCount = ospJdbcDAO.update(sqlText, params);
	    Assert.state(updateTxIdStatusEffCount > 0, 
	    		"Query table [SYS_TXID_MAP] with criteria [txId = '" + txId + "']");
		
	    TxIdMapPOJO txIdMap = getOrderMIdForStatusUpdate(txId);
	    
	    if (txIdMap != null) {
	        sqlText = ResourceFileUtil.SQL_ORDER.getResource("UPDATE_ORDER_STATUS_BY_ORDER_M_ID");
	        
	        String status = Constant.ORDER_STATUS_REPLY; // 系統回覆
	        String orderMId = txIdMap.getOrderMId();
	        String msisdn = txIdMap.getMsisdn();
	
	        params = new HashMap<String, Object>();
	        params.put("orderMId", orderMId);
	        params.put("status", status);
	        
	        int updateOrderStatusEffCount = ospJdbcDAO.update(sqlText, params);
	        Assert.state(updateOrderStatusEffCount > 0, 
	        		"Query table [ORDER_MAIN_OSP] with criteria [orderMId = '" + orderMId + "']");
	        
	        int insertOperateRecordEffCount = insertOrderOperateRecords(orderMId, msisdn, status);
	        Assert.state(insertOperateRecordEffCount > 0
	                , "No data inserted into table [ORDER_OPERATE_RECORDS].");
	    }
	
		return true;
	}

	@Override
	public boolean updateOrderInfo2OSPFromAIMS(String sourceOrderId, Date expectProcessTime, Date custSpecifyDate) {
		String sqlText = ResourceFileUtil.SQL_OSP.getResource("GET_CALENDAR_INFO");
		List<CalendarInfoPOJO> calendarInfoList = ospJdbcDAO.queryForBean(sqlText, CalendarInfoPOJO.class);
		
		if (CollectionUtils.isNotEmpty(calendarInfoList)) {
			List<String> cDayCalList = new ArrayList<String>(); // 日曆日
			List<String> wDayCalList = new ArrayList<String>(); // 工作日
			
			for (CalendarInfoPOJO calendarInfo : calendarInfoList) {
				String dtDateStr = calendarInfo.getDtDateStr();
				String isHollday = calendarInfo.getIsHollday();
				
				cDayCalList.add(dtDateStr);
				
				if ("N".equals(isHollday)) { // 非假日
					wDayCalList.add(dtDateStr);
				}
			}
			
			sqlText = ResourceFileUtil.SQL_OSP.getResource("GET_FLOW_SOURCE_MAP_INFO");
			
			Map<String, Object> params = new HashMap<String, Object>();
	        params.put("sourceOrderId", sourceOrderId);
	        
	        List<FlowSourceMapInfoPOJO> flowSourceMapInfoList = ospJdbcDAO.queryForBean(sqlText, params, FlowSourceMapInfoPOJO.class);
	        
	        if (CollectionUtils.isNotEmpty(flowSourceMapInfoList)) {
	        	FlowSourceMapInfoPOJO flowSourceMapInfo = flowSourceMapInfoList.get(0);
	        	Date expectCompleteTime = getExpectCompleteTime(cDayCalList, wDayCalList, flowSourceMapInfo);
	        	
	        	orderMainOspWarehouse.updateOrderInfo2OSPFromAIMS(
	        			sourceOrderId, expectProcessTime, custSpecifyDate, expectCompleteTime);
	        }
		}
		
		return true;
	}
	
	// =========== 工具程式 ===========
	
	/*
	 * 根據「處理人員編號」, 取得未被暫停派件，且目前處於班表工作時間的人員資訊，<br> 包含「姓名」和人員在目前班別中的 SKILL
	 * 所對應的「案件類型」集合. <br>
	 * 
	 * @param employeeNo - 處理人員編號
	 * @param teamGroup
	 * 
	 * @return AvailableEmployeeInfo - 處理人員的「姓名」和人員在目前班別中的 SKILL 所對應的「案件類型」集合
	 */
	private AvailableEmployeeInfoPOJO getEmployeeInfo(String employeeNo, String teamGroup) {
		String sqlText = ResourceFileUtil.SQL_OSP.getResource("GET_EMPLOYEE_INFO");
	
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("employeeNo", employeeNo);
		params.put("teamGroup", teamGroup);
		
	    List<AvailableEmployeeInfoPOJO> dataList = ospJdbcDAO.queryForBean(sqlText, params, AvailableEmployeeInfoPOJO.class);
	
		if (CollectionUtils.isNotEmpty(dataList)) {
		    AvailableEmployeeInfoPOJO employeeInfo = dataList.get(0);
			return employeeInfo;
		}
	
		return null;
	}

	/*
	 * 根據員工編號，取得尚未分派案件清單
	 * 
	 * @param teamGroup - 人員所屬進件組別
	 * @param orderTypeIdSet - 處理人員可處理的「案件類型」集合, EX: 'OSPL4001',...,'OSPL4009'
	 * 
	 * @return DispatchOrderPOJO
	 */
	private List<DispatchOrderPOJO> getUndispatchedOrderList(String teamGroup, String orderTypeIdSet) {
	    String sqlText = ResourceFileUtil.SQL_OSP.getResource("GET_UNDISPATCHED_ORDER_LIST");
	    sqlText = StringUtils.replace(sqlText, "$P{TEAM_GROUP}", teamGroup);
	    sqlText = StringUtils.replace(sqlText, "$P{ORDER_TYPE_ID}", orderTypeIdSet);
	    
	    List<DispatchOrderPOJO> dataList = ospJdbcDAO.queryForBean(sqlText, DispatchOrderPOJO.class);
	    
	    return dataList;
	}

	/*
	 * 取得母單已派件，但尚未被派件的母子單派件資訊
	 * 
	 * @return List<RedispatchOrderPojo>
	 */
	private List<RedispatchOrderPojo> getRedispatchChildOrderList() {
	    String sqlText = ResourceFileUtil.SQL_OSP.getResource("GET_REDISPATCH_CHILD_ORDER_LIST");
	
	    List<RedispatchOrderPojo> dataList = ospJdbcDAO.queryForBean(sqlText, RedispatchOrderPojo.class);
	    
	    return dataList;
	}

	private void dispatchByOrderMId(String orderMId, String msisdn, String employeeNo, 
	    String employeeName, String batchNo, String status) {
		logger.info("dispatchByOrderMId [Start]");
	    
	    // STEP 1. 派件給「處理人員」
	    int updateOrderMainEffCount = updateOrderByOrderMId(orderMId, employeeNo, employeeName, status);
	    Assert.state(updateOrderMainEffCount > 0, "No data updated from table [ORDER_MAIN_OSP].");
	    
	    // STEP 2. 新增資料至資料表「ORDER_OPERATE_RECORDS」
	    int insertOperateRecordEffCount = insertOrderOperateRecords(orderMId, msisdn, status);
	    Assert.state(insertOperateRecordEffCount == updateOrderMainEffCount, "No data inserted into table [ORDER_OPERATE_RECORDS].");
	    
	    // STEP 3. 新增分派案件至資料表「ORDER_DISPATCH_NOTIFY」
	    int insertDispatchNotifyEffCount = insertOrderDispatchNotify(orderMId, employeeNo, batchNo);
	    Assert.state(insertDispatchNotifyEffCount == insertOperateRecordEffCount, "No data inserted into table [ORDER_DISPATCH_NOTIFY].");
	
	    logger.info("dispatchByOrderMId [End]");
	}

	/*
	 * 將母單已派件，但尚未被派件的子單，補派件給母單的「處理人員」
	 * 
	 * @param orderMId
	 * @param processUserId
	 * @param processUserName
	 * @param msisdn
	 * @param batchNo
	 * @param status
	 */
	private void doReDispatchByOrderMId(String orderMId, String processUserId, String processUserName, 
	        String msisdn, String batchNo, String status) {
	    // STEP 1. 將母單已派件，但尚未被派件的子單，補派件給母單的「處理人員」
	    int updateOrderEffCount = reUpdateOrderByOrderMId(orderMId, processUserId, processUserName, status);
	    Assert.state(updateOrderEffCount > 0, "No data updated from table [ORDER_MAIN_OSP].");
	    
	    // STEP 2. 新增資料至資料表「ORDER_OPERATE_RECORDS」
	    int insertOperateRecordEffCount = insertOrderOperateRecords(orderMId, msisdn, status);
	    Assert.state(insertOperateRecordEffCount == updateOrderEffCount, "No data inserted into table [ORDER_OPERATE_RECORDS].");
	    
	    // STEP 3. 新增分派案件至資料表「ORDER_DISPATCH_NOTIFY」
	    int insertDispatchNotifyEffCount = insertOrderDispatchNotify(orderMId, processUserId, batchNo);
	    Assert.state(insertDispatchNotifyEffCount == insertOperateRecordEffCount, "No data inserted into table [ORDER_DISPATCH_NOTIFY].");
	}

	/*
	 * 根據 txId 分段流程中，他系統已回覆所有處理結果至 OSP 的工單編號
	 * 
	 * @param txId
	 * @return TxIdMapPOJO
	 */
	private TxIdMapPOJO getOrderMIdForStatusUpdate(String txId) {
	    String sqlText = ResourceFileUtil.SQL_ORDER.getResource("GET_ORDER_INFO_LIST_BY_TXID");
	
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("txId", txId);
	    
	    List<TxIdMapPOJO> txIdMapList = ospJdbcDAO.queryForBean(sqlText, params, TxIdMapPOJO.class);
	    
	    Assert.state(CollectionUtils.isNotEmpty(txIdMapList), "No match result found from table [SYS_TXID_MAP]");
	    
	    for (TxIdMapPOJO txIdMap : txIdMapList) {
	        String txidStatus = txIdMap.getTxidStatus();
	        
	        if (!StringUtils.equals(txidStatus, "Y")) {
	            return null;
	        }
	    }
	
	    return txIdMapList.get(0);
	}

	/*
	 * 更新工單資訊(狀態/處理人員)至TicketPool[ORDER_MAIN_METADATA]. <br>
	 * 
	 * @param poolKey - 工單池鍵值
	 * @param status - 工單狀態
	 * @param processUser - 處理人員
	 */
	private void updateOrderStatus2TicketPool(String poolKey, String status, String processUser) {
		logger.info("updateOrderStatus2TicketPool [Start]");
		logger.info("params = [ poolKey = "+poolKey+", status = "+status+", processUser = "+processUser+"]");
		
	    OrderMainMetadataVO result = ticketPoolMainService.updateOrderStatus2TicketPool(poolKey, status, processUser);
	    Assert.state(result != null, "No data updated from ticket pool table [ORDER_MAIN_METADATA].");
	    
	    logger.info("updateOrderStatus2TicketPool [End]");
	}

	/*
	 * 派件給「處理人員」
	 * 
	 * @param orderMId - 主要進件單號
	 * @param employeeNo - 處理人員編號
	 * @param employeeName - 處理人員姓名
	 * @param status - 案件狀態
	 * 
	 * @return int
	 */
	private int updateOrderByOrderMId(String orderMId, String employeeNo, String employeeName, String status) {
		logger.info("updateOrderByOrderMId [Start]");
		logger.info("params [orderMId = "+orderMId+", employeeNo = "+employeeNo+", employeeName = "+employeeName+", status = "+status+"]");
		
	    String sqlText = ResourceFileUtil.SQL_OSP.getResource("DISPATCH_ORDER_BY_ORDER_M_ID");
	
	    Map<String, Object> sqlParams = new HashMap<String, Object>();
	    sqlParams.put("orderMId", orderMId);
	    sqlParams.put("employeeNo", employeeNo);
	    sqlParams.put("employeeName", employeeName);
	    sqlParams.put("status", status);
	    
	    int effCount = ospJdbcDAO.update(sqlText, sqlParams);
	    
	    logger.info("Dispatch order to employeeNo = " + employeeNo + ", count = " + effCount);
	    logger.info("updateOrderByOrderMId [End]");
	    
	    return effCount;
	}

	/*
	 * 將母單已派件，但尚未被派件的子單，補派件給母單的「處理人員」
	 * 
	 * @param orderMId
	 * @param processUserId
	 * @param processUserName
	 * @param status
	 * 
	 * @return int
	 */
	private int reUpdateOrderByOrderMId(String orderMId, String processUserId, String processUserName, String status) {
	    String sqlText = ResourceFileUtil.SQL_OSP.getResource("REDISPATCH_CHILD_ORDER");
	
	    Map<String, Object> sqlParams = new HashMap<String, Object>();
	    sqlParams.put("orderMId", orderMId);
	    sqlParams.put("processUserId", processUserId);
	    sqlParams.put("processUserName", processUserName);
	    sqlParams.put("status", status);
	    
	    int effCount = ospJdbcDAO.update(sqlText, sqlParams);
	    
	    logger.info("reUpdateOrderByOrderMId params = [ orderMId = " + orderMId + ", processUserId = " 
	            + processUserId + ", processUserName = " + processUserName + ", status = " + status + " ], effCount = " + effCount);
	    
	    return effCount;
	}

	/*
	 * 新增分派案件至資料表「ORDER_DISPATCH_NOTIFY」
	 */
	private int insertOrderDispatchNotify(String orderMId, String employeeNo, String batchNo) {
		logger.info("insertOrderDispatchNotify [Start]");
		logger.info("params = [ orderMId = "+orderMId+", employeeNo = "+employeeNo+", batchNo = "+batchNo);
		
	    String sqlText = ResourceFileUtil.SQL_OSP.getResource("INSERT_ORDER_DISPATCH_NOTIFY");
	    
	    Map<String, Object> sqlParams = new HashMap<String, Object>();
	    sqlParams.put("orderMId", orderMId);
	    sqlParams.put("employeeNo", employeeNo);
	    sqlParams.put("batchNo", batchNo);
	    
	    int effCount = ospJdbcDAO.update(sqlText, sqlParams);
	    
	    logger.info("insertOrderDispatchNotify [End]");
	    return effCount;
	}

	/*
	 * 新增資料至資料表「ORDER_OPERATE_RECORDS」
	 * 
	 * @param orderMId
	 * @param msisdn
	 * @param status
	 * 
	 * @return int
	 */
	private int insertOrderOperateRecords(String orderMId, String msisdn, String status) {
		logger.info("insertOrderOperateRecords [Start]");
		logger.info("params [orderMId = "+orderMId+", msisdn = "+msisdn+", status = "+status+"]");
		
	    String sqlText = ResourceFileUtil.SQL_OSP.getResource("INSERT_ORDER_OPERATE_RECORDS");
	
	    Map<String, Object> sqlParams = new HashMap<String, Object>();
	    sqlParams.put("orderOpId", IdentifierIdUtil.getUuid());
	    sqlParams.put("orderMId", orderMId);
	    sqlParams.put("msisdn", msisdn);
	    sqlParams.put("status", status);
	    
	    int effCount = ospJdbcDAO.update(sqlText, sqlParams);
	    
	    logger.info("insertOrderOperateRecords [End]");
	    return effCount;
	}

	/**
	 * 判斷「預計完成日」. <br>
	 * 
	 * @param cDayCalList
	 * @param wDayCalList
	 * @param lowSourceMapInfo
	 * 
	 * @return Date
	 */
	private Date getExpectCompleteTime(
			List<String> cDayCalList, List<String> wDayCalList, FlowSourceMapInfoPOJO lowSourceMapInfo) {
		String expectProcessTime = lowSourceMapInfo.getExpectProcessTime(); // yyyy-mm-dd hh24:mi:ss
		String srcCreateDtStr = lowSourceMapInfo.getSrcCreateDtStr(); // yyyy-mm-dd
		String srcCreateTimeStr = lowSourceMapInfo.getSrcCreateTimeStr(); // hh24mi
		String kpiDayType = lowSourceMapInfo.getKpiDayType(); // W_DAY, C_DAY
		String chkCreateDate = lowSourceMapInfo.getChkCreateDate(); // Y, N
		String beforeCreateDate = lowSourceMapInfo.getBeforeCreateDate(); // hh24mi
		String isRegularTime = lowSourceMapInfo.getIsRegularTime(); // Y, N
		String regularTime = lowSourceMapInfo.getRegularTime(); // 分鐘數
		String startCountTime  = lowSourceMapInfo.getStartCountTime(); // hh24:mi
		
		// 決定要使用那種日曆
		List<String> dayCalList = cDayCalList; // 日曆日
		
		if (StringUtils.equals(kpiDayType, "W_DAY")) {
			dayCalList = wDayCalList; // 工作日
		}

		// 日期
		String currentDate = srcCreateDtStr;
		String nextDate = getNextDate(dayCalList, srcCreateDtStr, 1);

		// 預計完成日
		String expectCompleteTime = null;
		
		if (StringUtils.equals(chkCreateDate, "Y")) {
			// 轉24h進制比較
			int srcCreateTimeInt = Integer.parseInt(srcCreateTimeStr);
			int beforeCreateDateInt = Integer.parseInt(beforeCreateDate);
			boolean early = (srcCreateTimeInt <= beforeCreateDateInt);

			if (StringUtils.equals(isRegularTime, "Y")) {
				if (early) {
					expectCompleteTime = getTimeCalculation(expectProcessTime, regularTime);
					
				} else {
					String sourceTime = StringUtils.join(nextDate, " ", startCountTime, ":00");
					expectCompleteTime = getTimeCalculation(sourceTime, regularTime);
				}
			}
			
			if (StringUtils.equals(isRegularTime, "N")) {
				if (early) {
					expectCompleteTime = StringUtils.join(currentDate, " ", "23:59:59");
					
				} else {
					expectCompleteTime = StringUtils.join(nextDate, " ", "23:59:59");
				}
			}
		}
		
		if (StringUtils.equals(chkCreateDate, "N") 
				&& StringUtils.equals(isRegularTime, "Y")) {
			expectCompleteTime = getTimeCalculation(expectProcessTime, regularTime);
		}
		
		return DateUtil.fromDateTime(expectCompleteTime);
	}
	
	/*
	 * 計算時間. <br>
	 * 
	 * @param sourceTime
	 * @param diffTime
	 * 
	 * @return String
	 */
	private String getTimeCalculation(String sourceTime, String diffTime) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		DateTime dateTime = DateTime.parse(sourceTime, DateTimeFormat.forPattern(pattern));

		Date rsDt = dateTime.plusMinutes(Integer.valueOf(diffTime)).toDate(); // 增加分鐘數

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		return sdf.format(rsDt);
	}
	
	/*
	 * 取得「下一個指定日期」. <br>
	 * 
	 * @param dayCalList
	 * @param targetDt
	 * @param diff
	 * 
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
