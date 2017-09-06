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

package com.fet.crm.osp.platform.webapp.action.workshop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fet.crm.osp.common.vo.kernel.result.SOATicketDetailRtnVO;
import com.fet.crm.osp.platform.core.common.util.DateUtil;
import com.fet.crm.osp.platform.core.common.util.ExcelParseUtil;
import com.fet.crm.osp.platform.core.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.common.util.ProcessTimeUtil;
import com.fet.crm.osp.platform.core.facade.dispatchinfo.OrderTypeManageFacade;
import com.fet.crm.osp.platform.core.facade.orderinfo.OrderManageFacade;
import com.fet.crm.osp.platform.core.facade.systeminfo.BuzFlowManageFacade;
import com.fet.crm.osp.platform.core.facade.systeminfo.HRManageFacade;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.OrderTypeInfoVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.CreationManageVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderInfoVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.TodoOrderInfoVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.TodoOrderCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.UserInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzRecordRoutineVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzStepPageVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.component.FileVO;
import com.fet.crm.osp.platform.mware.client.restful.proxy.OSPKernelRESTClientProxy;
import com.fet.crm.osp.platform.mware.client.vo.AddCIEMasterVO;
import com.fet.crm.osp.platform.mware.client.vo.CacheSubscriberInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.OrderLoadReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.OrderLoadVO;
import com.fet.crm.osp.platform.webapp.action.AbstractOSPAction;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.logger.WebappLoggerFactory;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;
import com.fet.crm.osp.platform.webapp.vo.NotifyOtherSalesVO;
import com.fet.crm.osp.platform.webapp.vo.session.SessionVO;

/**
 * [流程首頁] 共用控制器
 *
 * @author AndrewLee, Adam Yeh
 */
@Controller
@RequestMapping("/workshop/creation-group")
public class MobileGroupAction extends AbstractOSPAction{

    private static Logger logger = WebappLoggerFactory.getLogger(MobileGroupAction.class);

	@Autowired
	private BuzFlowManageFacade buzFlowMgrFacade;
    @Autowired
    private OrderManageFacade orderManageFacade;
    @Autowired
    private OrderTypeManageFacade orderTypeManageFacade;
    @Autowired
    private HRManageFacade hrManageFacade;
	@Autowired
	private OSPKernelRESTClientProxy proxy;
	@Autowired
	private ProcessTimeUtil processTimeUtil;

	/**
	 * [CONT0013_特殊案件] ( 參考文件: 客製頁簽說明.docx )<br>
	 *
	 * @author Adam Yeh
	 */
	@RequestMapping(value = "/cont-0013", method = RequestMethod.POST)
	public String getContent0013(@RequestParam String contentId) {
		String userId = HttpSessionHandler.getSessionInfo().getUserId();
		List<BuzRecordRoutineVO> recordContentList = buzFlowMgrFacade.getServiceBuzRecordRoutine(contentId, userId);
		
		HttpRequestHandler.put("recordContentList", recordContentList);
		
		return ForwardUtil.CONT0013.getView();
	}
	
	/**
	 * 「批次建檔 」
	 * 
	 * @return String
	 */
	@RequestMapping(value="/batch-creation-file", method = RequestMethod.POST)
	public @ResponseBody String batchCreationFile(@RequestParam("file") MultipartFile file) {
    	SessionVO sessionVO = HttpSessionHandler.getSessionInfo();
      	String ntAccount = sessionVO.getUserInfoVO().getNtAccount();
    	String userId = sessionVO.getUserId();
    	String userName = sessionVO.getUserInfoVO().getUserNm();
    	String now = DateUtil.fromDate(new Date(), DateUtil.DATE_TIME_PATTERN);
    	CreationManageVO creationManageVO = hrManageFacade.getCreationManageByUserId(userId);
    	
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();
    	String orderTypeId = MapUtils.getString(requestData, "orderTypeId");
    	OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);

		FileVO fileVO = toFileVO(file);
		List<Map<String, Object>> msisdnList = ExcelParseUtil.fromExcel(fileVO.getFile());
		
		List<String> msisdns = getMsisdnStringList(msisdnList);
		List<SOATicketDetailRtnVO> soaTicketDetailRtnVOs = proxy.getSOATicketDetail(msisdns, ntAccount);
		if (CollectionUtils.isEmpty(soaTicketDetailRtnVOs)) {
			logger.info("Ticket detail is empty.");
			
			return "";
		}
    	
    	List<OrderInfoVO> voList = new ArrayList<>();
    	for (SOATicketDetailRtnVO soaTicketDetailRtnVO : soaTicketDetailRtnVOs) {
            String orderMId = IdentifierIdUtil.getOspOrderMId();
            String poolKey = IdentifierIdUtil.getUuid();
            String sourceOrderId = soaTicketDetailRtnVO.getTicketNo();// From service ticketNo
            String teamGroup = creationManageVO.getTeamGroup();
            String sourceSysId = "Email";
            String sourceProdTypeId = orderTypeId;
            String sourceProdTypeName = orderTypeInfoVO.getOrderTypeName();
            String criticalFlag = "N";
            String sourceCreateTime = now;
            String ospCreateTime = now;
            String msisdn = soaTicketDetailRtnVO.getMsisdn();// From service msisdn
            String counts = "1";
            String expectProcessTime = now;
            String expectCompleteTime = processTimeUtil.getExpectCompleteTime(orderTypeInfoVO, sourceCreateTime);
            String custId = soaTicketDetailRtnVO.getCustID();// From service custId
            String processUserId = userId;
            String processUserName = userName;
            String orderTypeName = sourceProdTypeName;
            String orderStatus = "010";
            String flowId = orderTypeManageFacade.getFlowIdBySourceSysIdAndSourceProdTypeId(sourceSysId, sourceProdTypeId);
            String isNoticeSales = "N";
            String custType = "NBT04";
        	
            OrderInfoVO vo = new OrderInfoVO();
            vo.setOrderMId(orderMId);
            vo.setPoolKey(poolKey);
            vo.setSourceOrderId(sourceOrderId);
            vo.setTeamGroup(teamGroup);
            vo.setSourceSysId(sourceSysId);
            vo.setSourceProdTypeId(sourceProdTypeId);
            vo.setSourceProdTypeName(sourceProdTypeName);
            vo.setCriticalFlag(criticalFlag);
            vo.setSourceCreateTime(sourceCreateTime);
            vo.setOspCreateTime(ospCreateTime);
            vo.setMsisdn(msisdn);
            vo.setCounts(counts);
            vo.setExpectProcessTime(expectProcessTime);
            vo.setExpectCompleteTime(expectCompleteTime);
            vo.setCustId(custId);
            vo.setProcessUserId(processUserId);
            vo.setProcessUserName(processUserName);
            vo.setOrderTypeId(orderTypeId);
            vo.setOrderTypeName(orderTypeName);
            vo.setOrderStatus(orderStatus);
            vo.setFlowId(flowId);
            vo.setIsNoticeSales(isNoticeSales);
            vo.setCreateUser(userId);
            vo.setUpdateUser(userId);
            vo.setCustType(custType);
            voList.add(vo);
		}
    	boolean result = orderManageFacade.batchCreateOrder(voList);

    	String response = "";
    	if (result) {
    		List<String> poolKeys = syncOrder2TicketPoolAPI(voList);
    		
    		if (CollectionUtils.isEmpty(poolKeys)) {
    			logger.info("Error batch files: " + JsonUtil.toJson(voList));
    		}
    		
    		Map<String , Object> responseMap = new HashMap<>();
    		responseMap.put("poolKeys", poolKeys);
    		response = JsonUtil.toJson(responseMap);	
    	}
		
        return response;
	}
	
	private List<String> syncOrder2TicketPoolAPI(List<OrderInfoVO> voList) {
		if (CollectionUtils.isEmpty(voList)) {
			return Collections.emptyList();
		}
		
		List<String> poolKeys = new ArrayList<>();
		for (OrderInfoVO vo : voList) {
			String poolKey = vo.getPoolKey();
			String sourceOrderId = vo.getSourceOrderId();
			String teamGroup = vo.getTeamGroup();
			String sourceSysId = vo.getSourceSysId();
			String criticalFlag = vo.getCriticalFlag();
			String sourceCreateTime = vo.getSourceCreateTime();
			String counts = vo.getCounts();
			String expectProcessTime = vo.getExpectProcessTime();
			String status = vo.getOrderStatus();
			String processUser = vo.getProcessUserId();
			String msisdn = vo.getMsisdn();
			String custName = vo.getCustName();
			String custId = vo.getCustId();

			OrderLoadVO params = new OrderLoadVO();
			params.setPoolKey(poolKey);
			params.setSourceOrderId(sourceOrderId);
			params.setTeamGroup(teamGroup);
			params.setSourceSysId(sourceSysId);
			params.setCriticalFlag(criticalFlag);
			params.setSourceCreateTime(DateUtil.toDateTime(sourceCreateTime));
			params.setCounts(new BigDecimal(counts));
			params.setExpectProcessTime(DateUtil.toDateTime(expectProcessTime));
			params.setStatus(status);
			params.setProcessUser(processUser);
			params.setMsisdn(msisdn);
			params.setCustName(custName);
			params.setCustId(custId);
			
			OrderLoadReturnVO result = proxy.syncOrder2TicketPoolFromOSP(params);
			
			poolKey = result.getPoolKey();
			poolKeys.add(poolKey);
		}
		
		return poolKeys;
	}

	private List<String> getMsisdnStringList(List<Map<String, Object>> msisdnList) {
		if (CollectionUtils.isEmpty(msisdnList)) {
			return Collections.emptyList();
		}
    	
    	List<String> list = new ArrayList<>();
    	for (Map<String, Object> map : msisdnList) {
    		list.add(MapUtils.getString(map, "MSISDN"));
    	}
    	
    	return list;
	}

	/**
     * 寄送E-Mail
     * 
     * @return String
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/send-email", method = RequestMethod.POST)
    public @ResponseBody boolean sendEmail() {
    	Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();
    	String userId = HttpSessionHandler.getSessionInfo().getUserId();
    	
    	NotifyOtherSalesVO vo = new NotifyOtherSalesVO();
    	vo.setComboValue(MapUtils.getString(requestData, "comboValue"));
    	vo.setComboContent(MapUtils.getString(requestData, "comboContent"));
    	vo.setMsisdnList((List<Map<String, Object>>) MapUtils.getObject(requestData, "msisdnList"));
    	vo.setCreateUser(userId);
    	vo.setUpdateUser(userId);
    	
    	boolean result = orderManageFacade.setNotifyOtherSales(vo);
    	
        return result;
    }
    
    /**
     * 取得通知它業者的副本欄位的資料
     * 
     * @return String
     */
    @RequestMapping(value = "/get-duplication", method = RequestMethod.POST)
    public @ResponseBody List<NotifyOtherSalesVO> getDuplication() {
        return orderManageFacade.getDuplication();
    }
    
    /**
     * 通知它業者
     * 
     * @return String
     */
    @RequestMapping(value = "/get-msisdn-list", method = RequestMethod.POST)
    public @ResponseBody List<NotifyOtherSalesVO> getMsisdnList() {
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();
        String msisdn = MapUtils.getString(requestData, "msisdn");
        String orderTypeId = MapUtils.getString(requestData, "orderTypeId");
    	String userId = HttpSessionHandler.getSessionInfo().getUserId();
    	
    	NotifyOtherSalesVO vo = new NotifyOtherSalesVO();
    	vo.setMsisdn(msisdn);
    	vo.setOrderTypeId(orderTypeId);
    	vo.setProcessUserId(userId);
    	
    	List<NotifyOtherSalesVO> list = orderManageFacade.getNotifyOtherSalesOrderList(vo);
    	
        return list;
    }
    
    /**
     * 建檔
     * 
     * @return String
     */
    @RequestMapping(value = "/creation-file", method = RequestMethod.POST)
    public @ResponseBody String creationFile() {
    	SessionVO sessionVO = HttpSessionHandler.getSessionInfo();
      	String ntAccount = sessionVO.getUserInfoVO().getNtAccount();
    	String userId = sessionVO.getUserId();
    	String userName = sessionVO.getUserInfoVO().getUserNm();
    	String now = DateUtil.fromDate(new Date(), DateUtil.DATE_TIME_PATTERN);
    	CreationManageVO creationManageVO = hrManageFacade.getCreationManageByUserId(userId);
    	
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();
    	String orderTypeId = MapUtils.getString(requestData, "orderTypeId");
    	String action = MapUtils.getString(requestData, "action");
    	OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
    	
        String orderMId = IdentifierIdUtil.getOspOrderMId();
        String poolKey = IdentifierIdUtil.getUuid();
        String sourceOrderId = orderMId;
        String teamGroup = creationManageVO.getTeamGroup();
        String sourceSysId = MapUtils.getString(requestData, "sourceSysId");
        String sourceProdTypeId = orderTypeId;
        String sourceProdTypeName = orderTypeInfoVO.getOrderTypeName();
        String criticalFlag = "N";
        String sourceCreateTime = now;
        String ospCreateTime = now;
        String msisdn = MapUtils.getString(requestData, "msisdn");
        String custName = MapUtils.getString(requestData, "custName");
        String counts = "1";
        String expectProcessTime = now;
        // modify at 2017-06-16 by V.J, Judy
//        String expectCompleteTime = processTimeUtil.getExpectCompleteTime(orderTypeInfoVO, sourceCreateTime);
        String expectCompleteTime = processTimeUtil.getExpectCompleteTime(orderTypeInfoVO, expectProcessTime);
        String custId = MapUtils.getString(requestData, "custId");
        String processUserId = userId;
        String processUserName = userName;
        String orderTypeName = sourceProdTypeName;
        String orderStatus = "010";
        String flowId = orderTypeManageFacade.getFlowIdBySourceSysIdAndSourceProdTypeId(sourceSysId, sourceProdTypeId);
        String isNoticeSales = "N";
        String custType = MapUtils.getString(requestData, "custType");
        String corpPicTaxid = MapUtils.getString(requestData, "corpPicTaxId");
    	
        OrderInfoVO vo = new OrderInfoVO();
        vo.setOrderMId(orderMId);
        vo.setPoolKey(poolKey);
        vo.setSourceOrderId(sourceOrderId);
        vo.setTeamGroup(teamGroup);
        vo.setSourceSysId(sourceSysId);
        vo.setSourceProdTypeId(sourceProdTypeId);
        vo.setSourceProdTypeName(sourceProdTypeName);
        vo.setCriticalFlag(criticalFlag);
        vo.setSourceCreateTime(sourceCreateTime);
        vo.setOspCreateTime(ospCreateTime);
        vo.setMsisdn(msisdn);
        vo.setCustName(custName);
        vo.setCounts(counts);
        vo.setExpectProcessTime(expectProcessTime);
        vo.setExpectCompleteTime(expectCompleteTime);
        vo.setCustId(custId);
        vo.setProcessUserId(processUserId);
        vo.setProcessUserName(processUserName);
        vo.setOrderTypeId(orderTypeId);
        vo.setOrderTypeName(orderTypeName);
        vo.setOrderStatus(orderStatus);
        vo.setFlowId(flowId);
        vo.setIsNoticeSales(isNoticeSales);
        vo.setCreateUser(userId);
        vo.setUpdateUser(userId);
        vo.setCustType(custType);
        vo.setCorpPicTaxid(corpPicTaxid);
    	boolean result = orderManageFacade.createOrder(vo, action);
    	
    	String response = "";
    	if (result) {
        	CacheSubscriberInfoVO cacheSubscriberInfoVO = proxy.getCacheSubscriberInfoByMsisdn(msisdn, ntAccount);
    		AddCIEMasterVO addCIEMasterVO = new AddCIEMasterVO(userId, cacheSubscriberInfoVO);
    		proxy.addCIEMaster(addCIEMasterVO, ntAccount);
    		poolKey = syncOrder2TicketPoolAPI(vo);
    		
    		if (StringUtils.isEmpty(poolKey)) {
    			logger.info("Error files in pool: " + JsonUtil.toJson(vo));
    		}
    		
    		Map<String , Object> responseMap = new HashMap<>();
    		responseMap.put("flowId", flowId);
    		responseMap.put("orderMId", orderMId);
    		responseMap.put("sourceSysId", sourceSysId);
    		responseMap.put("orderTypeId", orderTypeId);
    		responseMap.put("poolKey", poolKey);
    		response = JsonUtil.toJson(responseMap);
    	}
    	
        return response;
    }

	private String syncOrder2TicketPoolAPI(OrderInfoVO vo) {
		if (vo == null) {
			return null;
		}
		
		String poolKey = vo.getPoolKey();
		String sourceOrderId = vo.getSourceOrderId();
		String teamGroup = vo.getTeamGroup();
		String sourceSysId = vo.getSourceSysId();
		String criticalFlag = vo.getCriticalFlag();
		String sourceCreateTime = vo.getSourceCreateTime();
		String counts = vo.getCounts();
		String expectProcessTime = vo.getExpectProcessTime();
		String status = vo.getOrderStatus();
		String processUser = vo.getProcessUserId();
		String msisdn = vo.getMsisdn();
		String custName = vo.getCustName();
		String custId = vo.getCustId();

		OrderLoadVO params = new OrderLoadVO();
		params.setPoolKey(poolKey);
		params.setSourceOrderId(sourceOrderId);
		params.setTeamGroup(teamGroup);
		params.setSourceSysId(sourceSysId);
		params.setCriticalFlag(criticalFlag);
		params.setSourceCreateTime(DateUtil.toDateTime(sourceCreateTime));
		params.setCounts(new BigDecimal(counts));
		params.setExpectProcessTime(DateUtil.toDateTime(expectProcessTime));
		params.setStatus(status);
		params.setProcessUser(processUser);
		params.setMsisdn(msisdn);
		params.setCustName(custName);
		params.setCustId(custId);
		
		OrderLoadReturnVO result = proxy.syncOrder2TicketPoolFromOSP(params);
		
		poolKey = result.getPoolKey();
		
		return poolKey;
	}

	/**
     * 月租啟用 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-postpaid-nn", method = RequestMethod.GET)
    public String servicePostpaidNN() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();
        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);
        
        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);

        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4001.getView();
    }

    /**
     * 月租攜碼 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-postpaid-np", method = RequestMethod.GET)
    public String servicePostpaidNP() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4002.getView();
    }

    /**
     * 統一轉遠傳月租 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-king-to-fet-post", method = RequestMethod.GET)
    public String serviceKingToFETPost() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4003.getView();
    }

    /**
     * 統一轉遠傳預付 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-king-to-fet-pre", method = RequestMethod.GET)
    public String serviceKingToFETPre() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4004.getView();
    }

    /**
     * 內轉月租 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-internal-np-post", method = RequestMethod.GET)
    public String serviceInternalNPPost() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4005.getView();
    }

    /**
     * 內轉預付 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-internal-np-pre", method = RequestMethod.GET)
    public String serviceInternalNPPre() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4006.getView();
    }

    /**
     * 永停復機 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-p26-activation", method = RequestMethod.GET)
    public String serviceP26Activation() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4007.getView();
    }

    /**
     * 變更結帳日 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-change-cycle-date", method = RequestMethod.GET)
    public String serviceChangeCycleDate() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4008.getView();
    }

    /**
     * 門號保留合約遞延90天 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-contract-postpone", method = RequestMethod.GET)
    public String serviceContractPostpone() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4009.getView();
    }

    /**
     * 提前解約修正合約日期 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-change-contact-date", method = RequestMethod.GET)
    public String serviceChangeContactDate() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4010.getView();
    }

    /**
     * 折扣安裝(future task安裝) 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-add-future-task", method = RequestMethod.GET)
    public String serviceAddFutureTask() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4011.getView();
    }

    /**
     * 學生溫暖 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-special-offer", method = RequestMethod.GET)
    public String serviceSpecialOffer() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);
        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4012.getView();
    }

    /**
     * 特授月租啟用 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-special-post-paid-nn", method = RequestMethod.GET)
    public String serviceSpecialPostpaidNN() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);
        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4013.getView();
    }

    /**
     * 特授月租攜碼 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-special-post-paid-np", method = RequestMethod.GET)
    public String serviceSpecialPostpaidNP() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4014.getView();
    }

    /**
     * 月租續約 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-loyalty", method = RequestMethod.GET)
    public String serviceLoyalty() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4015.getView();
    }

    /**
     * 月租續約(純附約) 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-loyalty-vas", method = RequestMethod.GET)
    public String serviceLoyaltyVAS() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4016.getView();
    }

    /**
     * 降轉啟用(4轉3)含加回原3G折扣 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-4g-to-3g", method = RequestMethod.GET)
    public String service4GTo3G() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();
        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4017.getView();
    }

    /**
     * 一退一租 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-market-move", method = RequestMethod.GET)
    public String serviceMarketMove() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4018.getView();
    }

    /**
     * 特殊退租 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-suspend", method = RequestMethod.GET)
    public String serviceSuspend() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4019.getView();
    }

    /**
     * 變更促代 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-change-promotion", method = RequestMethod.GET)
    public String serviceChangePromotion() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4020.getView();
    }

    /**
     * 預付啟用 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-prepaid-nn-activation", method = RequestMethod.GET)
    public String servicePrepaidNNActivation() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);
        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4021.getView();
    }

    /**
     * 預付攜碼 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-prepaid-np-activation", method = RequestMethod.GET)
    public String servicePrepaidNPActivation() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4022.getView();
    }

    /**
     * 預付轉讓 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-prepaid-transfer-activation", method = RequestMethod.GET)
    public String servicePrepaidTransferActivation() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4023.getView();
    }

    /**
     * 預付啟用OCW 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-prepaid-nn-ocw", method = RequestMethod.GET)
    public String servicePrepaidNNOCW() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);
        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4024.getView();
    }

    /**
     * 大量開卡 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-prepaid-mass", method = RequestMethod.GET)
    public String servicePrepaidMass() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4025.getView();
    }

    /**
     * 移入協商 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-port-in", method = RequestMethod.GET)
    public String servicePortIn() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4026.getView();
    }

    /**
     * 移出作業(特授移出/移出協商) 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-port-out-assist", method = RequestMethod.GET)
    public String servicePortOutAssist() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4027.getView();
    }

    /**
     * 移出作業(特授移出/移出批核) 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-port-out-approve", method = RequestMethod.GET)
    public String servicePortOutApprove() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4028.getView();
    }

    /**
     * E-月租啟用 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-postpaid-nn", method = RequestMethod.GET)
    public String serviceEBUPostpaidNN() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4029.getView();
    }

    /**
     * E-月租攜碼 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-postpaid-np", method = RequestMethod.GET)
    public String serviceEBUPostpaidNP() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4030.getView();
    }

    /**
     * E-批次啟用 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-batch-nn", method = RequestMethod.GET)
    public String serviceEBUBatchNN() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4031.getView();
    }

    /**
     * E-批次攜碼 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-batch-np", method = RequestMethod.GET)
    public String serviceEBUBatchNP() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4032.getView();
    }

    /**
     * E-內轉月租 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-internal-np-post", method = RequestMethod.GET)
    public String serviceEBUInternalNPPost() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);
        
        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4033.getView();
    }

    /**
     * E-月租續約 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-loyalty", method = RequestMethod.GET)
    public String serviceEBULoyalty() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4034.getView();
    }

    /**
     * E-月租續約(純附約) 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-loyalty-vas", method = RequestMethod.GET)
    public String serviceEBULoyaltyVAS() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4035.getView();
    }

    /**
     * E-變更促代 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-change-promotion", method = RequestMethod.GET)
    public String serviceEBUChangePromotion() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4036.getView();
    }

    /**
     * E-一退一租流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-market-move", method = RequestMethod.GET)
    public String serviceEBUMarketMove() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4037.getView();
    }

    /**
     * E-公私分帳(異動) 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-m34", method = RequestMethod.GET)
    public String serviceEBUM34() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4038.getView();
    }

    /**
     * E-MVPN(新增/異動) 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-mvpn", method = RequestMethod.GET)
    public String serviceEBUMVPN() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4039.getView();
    }

    /**
     * E-服務異動 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-service-change", method = RequestMethod.GET)
    public String serviceEBUServiceChange() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);
        
        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4040.getView();
    }

    /**
     * E-停機 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-suspend", method = RequestMethod.GET)
    public String serviceEBUSuspend() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4041.getView();
    }

    /**
     * E-變更結帳日 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-change-cycle-date", method = RequestMethod.GET)
    public String serviceEBUChangeCycleDate() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4042.getView();
    }

    /**
     * E-批次異動(CM 批次異動) 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-cm-batch", method = RequestMethod.GET)
    public String serviceEBUCMBatch() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4043.getView();
    }

    /**
     * E-批次異動(Y槽) 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-batch-y", method = RequestMethod.GET)
    public String serviceEBUBatchY() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4044.getView();
    }

    /**
     * E-批次異動(MVPN) 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-batch-mvpn", method = RequestMethod.GET)
    public String serviceEBUBatchMVPN() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4045.getView();
    }

    /**
     * E-批次續約 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-batch-loyalty", method = RequestMethod.GET)
    public String serviceEBUBatchLoyalty() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4046.getView();
    }

    /**
     * E-批次內轉啟用 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-batch-internal-np-post", method = RequestMethod.GET)
    public String serviceEBUBatchInternalNPPost() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4047.getView();
    }

    /**
     * E-其他作業 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-others", method = RequestMethod.GET)
    public String serviceEBUOthers() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4048.getView();
    }

    /**
     * 異動工單 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-change-tt", method = RequestMethod.GET)
    public String serviceChangeTT() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4049.getView();
    }

    /**
     * 啟用工單 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-activation-tt", method = RequestMethod.GET)
    public String serviceActivationTT() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4050.getView();
    }

    /**
     * E-工單 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-tt", method = RequestMethod.GET)
    public String serviceEBUTT() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4051.getView();
    }

    /**
     * E-協派單 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-support-tt", method = RequestMethod.GET)
    public String serviceEBUSupportTT() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4052.getView();
    }

    /**
     * E-VPOS核帳 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-ebu-vpos", method = RequestMethod.GET)
    public String serviceEBUVPOS() {
    	SessionVO sVO = HttpSessionHandler.getSessionInfo();
    	String userId = sVO.getUserId();
    	
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId.相當於OrderTypeID
        String orderTypeId = MapUtils.getString(requestData, "menuId");

        // 建立查詢封裝物件
        TodoOrderCVO cvo = new TodoOrderCVO();

        cvo.setOrderTypeId(orderTypeId);
        cvo.setProcessUserId(userId);

        OrderTypeInfoVO orderTypeInfoVO = orderTypeManageFacade.getOrderTypeInfoByTypeId(orderTypeId);
        TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(cvo);
    	List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
        // 取得title
        String title = getPageTitleName(todoList);
        
        // 將處理好的資料統一放入map中
        Map<String, Object> dataMp = new HashMap<>();
        dataMp.put("orderTypeVO", orderTypeInfoVO);
        dataMp.put("orderTypeId", orderTypeId);
        dataMp.put("dataLs", todoList);
        dataMp.put("sum", todoList.size());
        dataMp.put("critical", criticalOrderCount);
        dataMp.put("overdue", overdueOrderCount);
        dataMp.put("title", title);

        String responseData = JsonUtil.toJson(dataMp);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.OSPLV4053.getView();
    }

    /**
     * Fail Log處理 流程首頁
     * 
     * @return String
     */
    @RequestMapping(value = "/service-fail-log", method = RequestMethod.GET)
    public String serviceFailLog() {
        // 從前端取得JSON轉化過後的Map
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        // 在從中取得menuId
        String menuId = MapUtils.getString(requestData, "menuId");

    	UserInfoVO userInfoVO = HttpSessionHandler.getSessionInfo().getUserInfoVO();
		List<BuzStepPageVO> stepPageLs = buzFlowMgrFacade.getServiceBuzStepForFunction(menuId, userInfoVO);

		HttpRequestHandler.put("stepPageLs", stepPageLs);

        return ForwardUtil.OSPLV4054.getView();
    }
    
    private String getPageTitleName( List<Map<String, Object>> dataList) {
    	 if (CollectionUtils.isNotEmpty(dataList)) {
    		 return MapUtils.getString(dataList.get(0), "ORDER_TYPE_NAME");
         }
    	 
    	 return "";
    }
    
}
