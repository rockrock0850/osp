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

package com.fet.crm.osp.platform.core.facade.orderinfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.platform.core.common.Constant;
import com.fet.crm.osp.platform.core.pojo.SysOptionsComboPOJO;
import com.fet.crm.osp.platform.core.service.orderinfo.IBuzContentService;
import com.fet.crm.osp.platform.core.service.orderinfo.IOrderManageService;
import com.fet.crm.osp.platform.core.service.orderinfo.IOrderReadService;
import com.fet.crm.osp.platform.core.service.orderinfo.IOrderReferenceService;
import com.fet.crm.osp.platform.core.service.orderinfo.ISysTxidMapService;
import com.fet.crm.osp.platform.core.service.systeminfo.IBuzFlowService;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderAssignVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderBuzContentVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderDetailSpvVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderImageSettingVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderInfoVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderOperateRecordsVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderProcessVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.RecordContentVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.TodoOrderInfoVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.OrderInfoCVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.TodoOrderCVO;
import com.fet.crm.osp.platform.webapp.vo.NotifyOtherSalesVO;

/**
 * [訂單管理] 總體服務窗口
 * 
 * @author LawrenceLai, AndrewLee, Adam Yeh
 */
@Service
public class OrderManageFacade {
	
	@Autowired
	private ApplicationContext appContext;
    @Autowired
    private IOrderManageService orderManageService;
    @Autowired
    private IOrderReadService orderReadService; 
    @Autowired
    private IOrderReferenceService orderReferenceService;
    @Autowired
    private IBuzFlowService buzFlowService;
    @Autowired
    private ISysTxidMapService sysTxidMapService;
    
    private static final Map<String, String> serviceMap;
	
	static {
		serviceMap = new HashMap<String, String>();
		serviceMap.put("CONT0024", "buzContent0024ServiceImpl");
		serviceMap.put("CONT0025A", "buzContent0025ServiceImpl");
		serviceMap.put("CONT0025B", "buzContent0025ServiceImpl");
		serviceMap.put("CONT0025C", "buzContent0025ServiceImpl");
		serviceMap.put("CONT0025D", "buzContent0025ServiceImpl");
		serviceMap.put("CONT0025E", "buzContent0025ServiceImpl");
	}

    /**
	 * 建立 訂單資訊<br>
	 * 
	 * @param orderInfoVO
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean createOrder(OrderInfoVO orderInfoVO, String action) {
		if (orderInfoVO == null || StringUtils.isBlank(action)) {
			return false;
		}
		
		orderManageService.createOrder(orderInfoVO);

    	orderManageService.createOperateRecord(new OrderOperateRecordsVO(orderInfoVO, "U01", "010"));
		if (StringUtils.equals(action, "content")) {
	    	orderManageService.createOperateRecord(new OrderOperateRecordsVO(orderInfoVO, "U01", "020"));
		}
		
	    return true;
	}

	/**
	 * 批次建立 訂單資訊<br>
	 * 
	 * @author Adam
	 * @create date: May 12, 2017
	 *
	 * @param vo
	 * @param msisdnList
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean batchCreateOrder(List<OrderInfoVO> voList) {
		if (CollectionUtils.isEmpty(voList)) {
			return false;
		}
		
		orderManageService.batchCreateOrder(voList);
		
		for (OrderInfoVO orderInfoVO : voList) {
	    	orderManageService.createOperateRecord(new OrderOperateRecordsVO(orderInfoVO, "U01", "010"));
		}
    	
	    return true;
	}

	/**
	 * 新增 工單內容記錄<br>
	 * 
	 * @param vo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean createRecordContent(String orderMId, List<RecordContentVO> recordList) {
		return orderManageService.createRecordContent(orderMId, recordList);
	}

	/**
     * 根據orderMId查詢當筆訂單資訊
     * 
     * @author Adam
     * @create date: Apr 21, 2017
     *
     * @param orderMId
     * @return
     */
    @Transactional(readOnly = true)
    public OrderInfoVO getOrderInfo(String orderMId) {
        return orderManageService.queryOrderInfoByOrderMId(orderMId);
    }

    /**
	 * 查詢通知它業者的案件資訊<br>
	 * 
	 * @param NotifyOtherSalesVO
	 * @return List<NotifyOtherSalesVO>
	 */
	@Transactional(readOnly = true)
	public List<NotifyOtherSalesVO> getNotifyOtherSalesOrderList(NotifyOtherSalesVO vo) {
	    return orderManageService.queryNotifyOtherSalesOrderList(vo);
	}

	/**
     * 查詢案件指派維護作業功能的案件資訊<br>
     * 
     * @param orderInfoCVO
     * @return List<Map<String, Object>>
     */
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getAssignOrderInfo(OrderInfoCVO orderInfoCVO) {
        List<Map<String, Object>> rtnData = orderManageService.queryAssignOrderInfo(orderInfoCVO);
        formatOrderInfoVOCustSpecifyDate(rtnData);

        return rtnData;
    }

    /**
	 *  查詢 我的待處理 訂單資訊<br>
	 * 
	 * @param todoOrderCVO
	 * @return List<Map<String, Object>>
	 */
	public TodoOrderInfoVO getPersonalTodoOrderInfo(TodoOrderCVO todoOrderCVO) {
		List<Map<String, Object>> todoList = orderManageService.queryPersonalTodoOrderInfo(todoOrderCVO);
		int criticalOrderCount = orderManageService.queryPersonalCriticalOrderCount(todoOrderCVO);
		int overdueOrderCount = orderManageService.queryPersonalOverdueOrderCount(todoOrderCVO);
		
		TodoOrderInfoVO todoOrderInfoVO = new TodoOrderInfoVO();
		
		todoOrderInfoVO.setTodoList(todoList);
		todoOrderInfoVO.setCriticalOrderCount(criticalOrderCount);
		todoOrderInfoVO.setOverdueOrderCount(overdueOrderCount);
		 
		return todoOrderInfoVO;
	}

	/**
	 *  查詢 部門待處理 訂單資訊<br>
	 * 
	 * @param todoOrderCVO
	 * @return List<Map<String, Object>>
	 */
	@Transactional(readOnly = true)
	public TodoOrderInfoVO getDeptTodoOrderInfo(TodoOrderCVO todoOrderCVO, String userId) {
		List<Map<String, Object>> todoList = orderReadService.queryDeptTodoOrderInfo(todoOrderCVO, userId);
		int criticalOrderCount = orderReadService.queryDeptCriticalOrderCount(todoOrderCVO, userId);
		int overdueOrderCount = orderReadService.queryDeptOverdueOrderCount(todoOrderCVO, userId);
		
		TodoOrderInfoVO todoOrderInfoVO = new TodoOrderInfoVO();
		
		todoOrderInfoVO.setTodoList(todoList);
		todoOrderInfoVO.setCriticalOrderCount(criticalOrderCount);
		todoOrderInfoVO.setOverdueOrderCount(overdueOrderCount);
		 
		return todoOrderInfoVO;
	}

	/**
	 *  查詢 個人案件清單 資訊<br>
	 * 
	 * @param userId
	 * @return List<Map<String, Object>>
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getOrderSimpleInfoByUserId(String userId) {
		return orderManageService.queryOrderSimpleInfoByUserId(userId);
	}

	/**
	 * 寄送E-Mail
	 * 
	 * @author Adam
	 * @create date: May 11, 2017
	 *
	 * @param vo
	 * @return
	 */
	public boolean setNotifyOtherSales(NotifyOtherSalesVO vo) {
		boolean result = orderManageService.createNotifyOtherSales(vo);
		
		return result;
	}

	/**
	 * CONT0022_資料輸入 ( 參考文件: 客製頁簽說明.docx )<br>
	 * 
	 * @param OrderMainInfoVO
	 * @return boolean
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateOrderMainInfo(OrderInfoVO vo) {
	    return orderManageService.updateOrderMainInfo(vo);
	}

	/**
	 * 修改 訂單狀態<br>
	 * 
	 * @param status
	 * @param userId
	 * @return boolean
	 */
    public OrderProcessVO updateOrderStatus(OrderProcessVO orderProcessVO, String status) {
    	// STEP.1 更新訂單狀態
    	orderManageService.updateOrderStatus(orderProcessVO, status);
    	
    	// STEP.1-1  取回訂單資料
    	OrderInfoVO infoVO = orderManageService.queryOrderInfoByOrderMId(orderProcessVO.getOrderMId());
    	
    	// STEP.2 新增訂單操作歷程
    	orderManageService.createOperateRecord(new OrderOperateRecordsVO(infoVO, "U01", status, orderProcessVO));
    	
    	return orderProcessVO;
    }

    /**
     * 執行 轉派 1.orderMId 2.processUserId 3.processUserName 4.updateUser
     * 5.updateDate
     * 
     * @param assignVOLs
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean excuteOrderAssign(List<OrderAssignVO> assignList) {
    	if (CollectionUtils.isNotEmpty(assignList)) {
    		List<OrderOperateRecordsVO> recordList = new ArrayList<>();
    		
    		for (OrderAssignVO assignVO : assignList) {
    			recordList.add(new OrderOperateRecordsVO(assignVO));
    		}
    		
    		orderManageService.excuteOrderAssign(assignList);
           	orderManageService.createOperateRecordInBatch(recordList);
    	}
    	
        return true;
    }
    
    /* 案件共用按鈕
     ======================================================================================================================== */
    
    public List<NotifyOtherSalesVO> getDuplication() {
		List<SysOptionsComboPOJO> list = orderManageService.queryComboContent();
		List<NotifyOtherSalesVO> voList = new ArrayList<>();
		
		for (SysOptionsComboPOJO pojo : list) {
			NotifyOtherSalesVO vo = new NotifyOtherSalesVO();
			
			BeanUtils.copyProperties(pojo, vo);
			voList.add(vo);
		} 
		 
		return voList;
	}

	/**
     * 訂單處理 人工暫存 [ORDER_STATUS : 050]
     * 
     * @param orderMId
     * @param userId
     * @param contentParams
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean temporarySave(OrderProcessVO orderProcessVO) {
    	// STEP.1更新訂單狀態 人工暫存 [ORDER_STATUS : 050]
    	orderManageService.updateOrderStatus(orderProcessVO, Constant.ORDER_STATUS_TMP_SAVE);
    	
    	// STEP.1-1 取回訂單資料
    	OrderInfoVO infoVO = orderManageService.queryOrderInfoByOrderMId(orderProcessVO.getOrderMId());
    	
    	// STEP.2-1 Email通知業務
    	// STEP.2-2 更新勾選授權原因
    	createContent(orderProcessVO, Constant.ORDER_STATUS_TMP_SAVE);
    	
    	// STEP.3 新增訂單操作歷程
    	orderManageService.createOperateRecord(new OrderOperateRecordsVO(infoVO, "U01", Constant.ORDER_STATUS_TMP_SAVE, orderProcessVO));

    	// STEP.4-1 新增資料表：ORDER_KPI_OWNER
    	orderManageService.createOrderKPIOwner(orderProcessVO);
    	
    	return true;
    }
    
    /**
     * 訂單處理 人工暫離 [ORDER_STATUS : 030]
     * 
     * @param orderMId
     * @param userId
     * @param contentParams
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean temporaryLeave(OrderProcessVO orderProcessVO) {
    	// STEP.1更新訂單狀態 人工暫離 [ORDER_STATUS : 030]
    	orderManageService.updateOrderStatus(orderProcessVO, Constant.ORDER_STATUS_TMP_LEAVE);
    	
    	// STEP.1-1 取回訂單資料
    	OrderInfoVO infoVO = orderManageService.queryOrderInfoByOrderMId(orderProcessVO.getOrderMId());
    	
    	// STEP.2-1 Email通知業務
    	// STEP.2-2 更新勾選授權原因
    	createContent(orderProcessVO, Constant.ORDER_STATUS_TMP_LEAVE);
    	
    	// STEP.3 新增訂單操作歷程
    	orderManageService.createOperateRecord(new OrderOperateRecordsVO(infoVO, "U01", Constant.ORDER_STATUS_TMP_LEAVE, orderProcessVO));
    	
    	// STEP.4 新增暫離訊息
    	orderManageService.createOrderMessage(orderProcessVO);

    	// STEP.5 新增資料表：ORDER_KPI_OWNER
    	orderManageService.createOrderKPIOwner(orderProcessVO);
    	
    	return true;
    }
    
    /**
     * 訂單處理 有效件 [ORDER_STATUS : 070]
     * 
     * @param orderMId
     * @param userId
     * @param contentParams
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean validOrder(OrderProcessVO orderProcessVO) {
    	// STEP.1更新訂單狀態 有效件 [ORDER_STATUS : 070]
    	orderManageService.updateOrderStatus(orderProcessVO, Constant.ORDER_STATUS_VALID);
    	orderManageService.updateOrderProcessInfo(orderProcessVO);
    	
    	// STEP.1-1 取回訂單資料
    	OrderInfoVO infoVO = orderManageService.queryOrderInfoByOrderMId(orderProcessVO.getOrderMId());
    	
    	// STEP.2-1 Email通知業務
    	// STEP.2-2 更新勾選授權原因
    	createContent(orderProcessVO, Constant.ORDER_STATUS_VALID);
    	
    	// STEP.3 新增訂單操作歷程
    	orderManageService.createOperateRecord(new OrderOperateRecordsVO(infoVO, "U01", Constant.ORDER_STATUS_VALID, orderProcessVO));

    	// STEP.4 新增資料表：ORDER_KPI_OWNER
    	orderManageService.createOrderKPIOwner(orderProcessVO);
    	
    	return true;
    }
    
    /**
     * 訂單處理 無效件 [ORDER_STATUS : 080]
     * 
     * @param orderMId
     * @param userId
     * @param contentParams
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean invalidOrder(OrderProcessVO orderProcessVO) {
    	// STEP.1更新訂單狀態 無效件 [ORDER_STATUS : 080]
    	orderManageService.updateOrderStatus(orderProcessVO, Constant.ORDER_STATUS_INVALID);
    	orderManageService.updateOrderProcessInfo(orderProcessVO);
    	
    	// STEP.1-1 取回訂單資料
    	OrderInfoVO infoVO = orderManageService.queryOrderInfoByOrderMId(orderProcessVO.getOrderMId());
    	 
    	// STEP.2-1 Email通知業務
    	// STEP.2-2 更新勾選授權原因
    	createContent(orderProcessVO, Constant.ORDER_STATUS_INVALID);
    	
    	// STEP.3 新增訂單操作歷程
    	orderManageService.createOperateRecord(new OrderOperateRecordsVO(infoVO, "U01", Constant.ORDER_STATUS_INVALID, orderProcessVO));

    	// STEP.4 新增資料表：ORDER_KPI_OWNER
    	orderManageService.createOrderKPIOwner(orderProcessVO);
    	
    	return true;
    }
    
    /**
     * 訂單處理 更改案件類別
     * 
     * @param orderMId
     * @param userId
     * @param contentParams
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateOrderCategory(OrderProcessVO orderProcessVO) {
    	// STEP.1-1 Email通知業務
    	// STEP.1-2 更新勾選授權原因
    	createContent(orderProcessVO, "");
    	
    	// STEP.2 更改案件類別
    	String userId = orderProcessVO.getUserId();
    	String orderTypeId = orderProcessVO.getOrderTypeId();
    	String orderTypeName = orderProcessVO.getOrderTypeName();
    	OrderInfoVO infoVO = orderManageService.queryOrderInfoByOrderMId(orderProcessVO.getOrderMId());
    	String flowId = buzFlowService.getFlowId(orderProcessVO, infoVO);
    	boolean result = orderManageService.orderHandlingAuthority(userId, orderTypeId);
    	
    	infoVO.setFlowId(flowId);
    	infoVO.setOrderTypeId(orderTypeId);
    	infoVO.setOrderTypeName(orderTypeName);
    	infoVO.setUpdateUser(userId);
    	if (!result) {
    		infoVO.setProcessUserId(null);
    		infoVO.setProcessUserName(null);
    		updateOrderStatus(orderProcessVO, "000");
        	orderManageService.createOperateRecord(new OrderOperateRecordsVO(infoVO, "U01", "000", orderProcessVO));
    	}
    	orderManageService.saveOrderMainOspForIgnoreNullValue(infoVO);
    	
    	// STEP.3 新增訂單操作歷程
    	orderManageService.createOperateRecord(new OrderOperateRecordsVO(infoVO, "U02", Constant.ORDER_STATUS_TMP_SAVE, orderProcessVO));

    	// STEP.4 新增資料表：ORDER_KPI_OWNER
    	orderManageService.createOrderKPIOwner(orderProcessVO);
    	
    	return result;
    }
    
    /**
     * 訂單處理 待系統回覆 系統暫存 [ORDER_STATUS : 040]
     * 
     * @param orderMId
     * @param userId
     * @param contentParams
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean pendingSurroundingReply(OrderProcessVO orderProcessVO) {
    	// STEP.1更新訂單狀態 無效件 [ORDER_STATUS : 040]
    	orderManageService.updateOrderStatus(orderProcessVO, Constant.ORDER_STATUS_PENDING_SURROUNDING_REPLY);
    	
    	// STEP.1-1 取回訂單資料
    	OrderInfoVO infoVO = orderManageService.queryOrderInfoByOrderMId(orderProcessVO.getOrderMId());
    	
    	// STEP.1-2 更新SYS_TXID_MAP
        sysTxidMapService.createSysTxidMap(orderProcessVO);	
    	
    	// STEP.2-1 Email通知業務
    	// STEP.2-2 更新勾選授權原因
    	createContent(orderProcessVO, Constant.ORDER_STATUS_PENDING_SURROUNDING_REPLY);
    	
    	// STEP.3 新增訂單操作歷程
    	orderManageService.createOperateRecord(new OrderOperateRecordsVO(infoVO, "U01", Constant.ORDER_STATUS_PENDING_SURROUNDING_REPLY, orderProcessVO));

    	// STEP.4 新增資料表：ORDER_KPI_OWNER
    	orderManageService.createOrderKPIOwner(orderProcessVO);
    	
    	return true;
    }
    
    /**
     * 根據[orderMId] </BR>
     * 帶回poolKey取得SPV案件詳細資訊
     * 
     * @param poolKey
     * @return List
     */
    @Transactional(readOnly = true)
    public List<OrderDetailSpvVO> getOrderDetailSpv(String OrderMId) {
    	String poolKey = orderManageService.queryPoolKeyByOrderMId(OrderMId);
    	
    	if(StringUtils.isNotEmpty(poolKey)) {
    		return orderManageService.queryOrderDetailSpvByPoolKey(poolKey);
    	}
    	
		return Collections.emptyList();
    }
    
    /**
     * 查詢 Message Content訊息<br>
     * 
     * @param orderMId
     * @return String
     */
    @Transactional(readOnly = true)
    public String getOrderMessage(String orderMId) {
    	return orderManageService.queryOrderMessage(orderMId);
    }
    
    /**
	 * 查詢案件影像檔開啟資訊
	 * 
	 * @param orderMId
	 * @param userId
	 * @return OrderImageSettingVO
	 */
    @Transactional(readOnly = true)
   	public OrderImageSettingVO getOrderImageSetting(String orderMId, String userId, String ntAccount) {
       	return orderReferenceService.queryOrderImageSetting(orderMId, userId, ntAccount);
   	}
       
	/**
	 * 查詢是否有新進待處理案件，並於前端顯示新進件通知
	 * 
	 * @param userId
	 * @return boolean
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean getDispatchNotify(String userId) {
		return orderReferenceService.dispatchNotify(userId);
	}
       
   	private boolean createContent(OrderProcessVO orderProcessVO, String orderStatus) {
		if (orderProcessVO == null) {
			return true;
		}
		
    	String orderMId = orderProcessVO.getOrderMId();
    	List<OrderBuzContentVO> buzContentList = orderProcessVO.getContent();
		if(CollectionUtils.isNotEmpty(buzContentList)) {
			for (OrderBuzContentVO vo : buzContentList) {
				IBuzContentService buzContentService = generateService(orderMId, vo.getContentId());
				
				if (buzContentService != null) {
					buzContentService.createContent(orderProcessVO, vo.getContentData(), orderStatus, buzContentList);
				}
			}
		}
		
		return true;
	}
	
	private IBuzContentService generateService(String orderMId, String contentId) {
		if(serviceMap.containsKey(contentId)) {
			String beanName = serviceMap.get(contentId);
			
			return (IBuzContentService) appContext.getBean(beanName);
		}
		
		return null;
	}
    
    /**
     * 判斷OrderInfoVO中的客戶指定日期是否為空.若空直接給空字串
     * 
     * @param dataMp
     */
    private void formatOrderInfoVOCustSpecifyDate(List<Map<String, Object>> dataMp) {
        if (CollectionUtils.isNotEmpty(dataMp)) {
            for (Map<String, Object> map : dataMp) {
                String custSpecifyDate = MapUtils.getString(map, "CUST_SPECIFY_DATE");

                //塞入空字串.防止前端DataTable出錯
                if (custSpecifyDate == null) {
                    map.put("CUST_SPECIFY_DATE", "");
                }
            }
        }
    }

}
