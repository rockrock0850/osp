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

package com.fet.crm.osp.platform.core.service.orderinfo;

import java.util.List;
import java.util.Map;

import com.fet.crm.osp.platform.core.pojo.SysOptionsComboPOJO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderAssignVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderDetailSpvVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderInfoVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderOperateRecordsVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderProcessVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.RecordContentVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.OrderInfoCVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.TodoOrderCVO;
import com.fet.crm.osp.platform.webapp.vo.NotifyOtherSalesVO;

/**
 * [訂單管理] 服務介面
 * 
 * @author LawrenceLai, Adam Yeh
 */
public interface IOrderManageService {
	
	/**
	 * 查詢 訂單資訊<br>
	 * 取得條件：
	 * 
	 * @param qrderInfoCVO
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryAssignOrderInfo(OrderInfoCVO orderInfoCVO);
	
	/**
	 * 查詢 訂單資訊<br>
	 * 取得條件：orderMId
	 * 
	 * @param orderMId
	 * @return OrderInfoVO
	 */
	OrderInfoVO queryOrderInfoByOrderMId(String orderMId);
	
	/**
	 * 建立 訂單資訊<br>
	 * 
	 * @param orderInfoVO
	 * @return boolean
	 */
	boolean createOrder(OrderInfoVO orderInfoVO);
	
	/**
	 * 修改 訂單狀態<br>
	 * 
	 * @param status
	 * @param userId
	 * @return boolean
	 */
	OrderProcessVO updateOrderStatus(OrderProcessVO vo, String status);

	/**
	 * 修改 訂單處理資訊<br>
	 * 
	 * @author Adam
	 * @create date: Apr 25, 2017
	 *
	 * @param vo
	 */
	boolean updateOrderProcessInfo(OrderProcessVO vo);
	
	/**
	 * 執行 案件指派<br>
	 * 
	 * @param assignVO
	 * @return boolean
	 */
	boolean excuteOrderAssign(List<OrderAssignVO> assignList);
	
	/**
	 *  查詢 我的待處理 訂單資訊<br>
	 * 
	 * @param todoOrderCVO
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryPersonalTodoOrderInfo(TodoOrderCVO todoOrderCVO);
	
	/**
	 *  查詢 個人案件清單 資訊<br>
	 * 
	 * @param userId
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryOrderSimpleInfoByUserId(String userId);
	
	/**
	 *  查詢 我的待處理 急件件數 資訊<br>
	 * 
	 * @param todoOrderCVO
	 * @return int
	 */
	int queryPersonalCriticalOrderCount(TodoOrderCVO todoOrderCVO);
	
	/**
	 * 查詢 我的待處理 逾期件件數 資訊<br>
	 * 
	 * @param todoOrderCVO
	 * @return int
	 */
	int queryPersonalOverdueOrderCount(TodoOrderCVO todoOrderCVO);
	
	/**
	 * 新增 訂單操作歷程<br>
	 * 
	 * @param operateRecordsVO
	 * @return boolean
	 */
	boolean createOperateRecord(OrderOperateRecordsVO operateRecordsVO);
	
	/**
	 * 批次 新增 訂單操作歷程<br>
	 * 
	 * @param voList
	 * @return boolean
	 */
	boolean createOperateRecordInBatch(List<OrderOperateRecordsVO> recordList);
	
	/**
	 * 新增 工單內容記錄<br>
	 * 
	 * @param vo
	 * @return
	 */
	boolean createRecordContent(String orderMId, List<RecordContentVO> recordList);
	
	/**
	 * CONT0022_資料輸入 ( 參考文件: 客製頁簽說明.docx )<br>
	 * 
	 * @param status
	 * @param userId
	 * @return boolean
	 */
	boolean updateOrderMainInfo(OrderInfoVO infoVO);
	
	/**
	 * 修改 是否通知業務<br>
	 * 
	 * @param isNoticeSales
	 * @return boolean
	 */
	boolean updateNoticeSales(String isNoticeSales);
	
    /**
	 * 新增[暫離訊息]<br>
	 * 
	 * @param orderMId
	 * @param msgContent
	 * @param userId
	 * @return boolean
	 */
	boolean createOrderMessage(OrderProcessVO vo);
	
	/**
	 * 查詢[暫離訊息]<br>
	 * 查詢條件 : orderMId
	 * 
	 * @param orderMId
	 * @return String
	 */
	String queryOrderMessage(String orderMId);

	/**
	 * 查詢通知它業者的案件資訊<br>
	 * 
	 * @author Adam
	 * @create date: May 10, 2017
	 *
	 * @param vo
	 * @return
	 */
	List<NotifyOtherSalesVO> queryNotifyOtherSalesOrderList(NotifyOtherSalesVO vo);

	/**
	 * 取得通知它業者的副本欄位之內容
	 * 
	 * @author Adam
	 * @create date: May 11, 2017
	 *
	 * @return
	 */
	List<SysOptionsComboPOJO> queryComboContent();

	/**
	 * 寄送E-Mail
	 * 
	 * @author Adam
	 * @create date: May 11, 2017
	 *
	 * @param vo
	 * @return
	 */
	boolean createNotifyOtherSales(NotifyOtherSalesVO vo);

    /**
	 * 批次建立 訂單資訊<br>
	 * 
	 * @param orderInfoVO
	 * @return
	 */
	boolean batchCreateOrder(List<OrderInfoVO> voList);

    /**
	 * 建立案件產能歸屬人員編號<br>
	 * 
	 * @param orderInfoVO
	 * @return
	 */
	boolean createOrderKPIOwner(OrderProcessVO orderProcessVO);
	
	/**
	 * 根據[ORDER_M_ID] </BR>
	 * 取得[POOL_KEY]
	 * 
	 * @param OrderMId
	 * @return
	 */
	String queryPoolKeyByOrderMId(String OrderMId);
	
	/**
	 * 根據[POOL_KEY] </BR>
	 * 查詢SPV案件詳細資訊
	 * 
	 * @param poolKey
	 * @return List
	 */
	List<OrderDetailSpvVO> queryOrderDetailSpvByPoolKey(String poolKey);
	
	/**
	 * 依據員工編號與案件類別ID，驗證是否有案件處理權限
	 * 
	 * @param userId
	 * @param orderTypeId
	 * @return
	 */
	boolean orderHandlingAuthority(String userId, String orderTypeId);

	/**
	 * 更新ORDER_MAIN_OSP, 就算欄位有null也一併更新
	 * 
	 * @author Adam
	 * @create date: May 19, 2017
	 *
	 * @param infoVO
	 */
	int saveOrderMainOspForIgnoreNullValue(OrderInfoVO infoVO);
	
}
