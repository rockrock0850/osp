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

package com.fet.crm.osp.platform.webapp.action.system;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fet.crm.nsp.generic.model.cache.OwnInfo;
import com.fet.crm.nsp.generic.util.NSPCacheUtil;
import com.fet.crm.osp.platform.core.common.Constant;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.facade.orderinfo.OrderManageFacade;
import com.fet.crm.osp.platform.core.facade.systeminfo.BuzFlowManageFacade;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderImageSettingVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderInfoVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderProcessVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.UserInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.BuzFlowVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzHierarchyEstablishmentVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzStepPageVO;
import com.fet.crm.osp.platform.mware.client.restful.proxy.NCPRESTClientProxy;
import com.fet.crm.osp.platform.mware.client.restful.proxy.OSPKernelRESTClientProxy;
import com.fet.crm.osp.platform.mware.client.vo.AddCIEMasterReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.AddCIEMasterVO;
import com.fet.crm.osp.platform.mware.client.vo.CacheSubscriberInfoVO;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;
import com.fet.crm.osp.platform.webapp.vo.session.SessionVO;

/**
 * 「頁面資訊」 前端控制器
 * 
 * @author PaulChen, Adam Yeh, AllenChen
 */
@Controller
@RequestMapping("/flow")
public class BuzFlowManageAction {

	@Autowired
	private BuzFlowManageFacade buzFlowMgrFacade;
	
	@Autowired
	private OrderManageFacade orderManageFacade;

	@Autowired
	private OSPKernelRESTClientProxy kernelRESTClientProxy;
	
	@Autowired
	private NCPRESTClientProxy ncpRESTClientProxy;
	
	/**
	 * 「特殊案件」
	 * 
	 * @param flowId
	 * @return String
	 */
	@RequestMapping(value = "/get-buz-operate-flow", method = RequestMethod.GET)
	public String getBuzOperateFlow(@RequestParam String flowId) {
		/* 組BuzFlowVO, 讓前端長出Tab
		====================================================================*/
		BuzFlowVO buzFlowVO = buzFlowMgrFacade.getServiceBuzFlow(flowId);
		
		HttpRequestHandler.put("buzFlowVO", buzFlowVO);

		return "tiles.flow.baseContainer";
	}
	
	/**
	 * 「核資, 資料輸入」
	 * 
	 * @param orderMId
	 * @return String
	 */
	@RequestMapping(value = "/re-enetity-buz-order-flow", method = RequestMethod.GET)
	public String reEntryBuzOrderFlow(@RequestParam String orderMId) {
		OrderInfoVO orderInfoVO = orderManageFacade.getOrderInfo(orderMId);
		String orderStatus = orderInfoVO.getOrderStatus();// 注意:　此處需要取得更新前的訂單狀態, 因為要顯示暫離訊息
		
		/* 組BuzFlowVO, 讓前端長出Tab
		====================================================================*/
		String flowId = orderInfoVO.getFlowId();
		String sourceSysId = orderInfoVO.getSourceSysId();
		String orderTypeId = orderInfoVO.getOrderTypeId();
		
		BuzFlowVO buzFlowVO = buzFlowMgrFacade.getServiceBuzFlow(flowId);
        buzFlowVO.setOrderMId(orderMId);
        buzFlowVO.setSourceSysId(sourceSysId);
		buzFlowVO.setOrderTypeId(orderTypeId);
		buzFlowVO.setOrderStatus(orderStatus);
		
		HttpRequestHandler.put("buzFlowVO", buzFlowVO);

		return "tiles.flow.baseContainer";
	}
	
	/**
	 * 取得「作業流程服務頁」
	 * 
	 * @param orderMId
	 * @return String
	 */
	@RequestMapping(value = "/get-buz-order-flow", method = {RequestMethod.POST, RequestMethod.GET})
	public String getBuzOrderFlow(@RequestParam String orderMId) {
		OrderInfoVO orderInfoVO = orderManageFacade.getOrderInfo(orderMId);
		String orderStatus = orderInfoVO.getOrderStatus();// 注意:　此處需要取得更新前的訂單狀態, 因為要顯示暫離訊息
		
		/* 更新訂單狀態
		====================================================================*/
		SessionVO sessionVO = HttpSessionHandler.getSessionInfo();
		String userId = sessionVO.getUserId();
		String userName = sessionVO.getUserInfoVO().getUserNm();
		
		if(StringUtils.isNotEmpty(orderMId)) {
			OrderProcessVO orderProcessVO = new OrderProcessVO();
			
			String processUserId = orderInfoVO.getProcessUserId();
			if (StringUtils.isBlank(processUserId)) {
				orderProcessVO.setDeptFeatrue(true);
			}
			
			orderProcessVO.setOrderMId(orderMId);
			orderProcessVO.setUserId(userId);
			orderProcessVO.setProcessUserId(userId);
			orderProcessVO.setProcessUserName(userName);
			orderManageFacade.updateOrderStatus(orderProcessVO, Constant.ORDER_STATUS_IN_PROCESS);
			
    		addCIEMasterAPI(userId, orderInfoVO);
			updateOrderStatus2TicketPoolAPI(orderInfoVO, orderProcessVO);
		}

		/* 組BuzFlowVO, 讓前端長出Tab
		====================================================================*/
		String flowId = orderInfoVO.getFlowId();
		String sourceSysId = orderInfoVO.getSourceSysId();
		String orderTypeId = orderInfoVO.getOrderTypeId();
		
		BuzFlowVO buzFlowVO = buzFlowMgrFacade.getServiceBuzFlow(flowId);
        buzFlowVO.setOrderMId(orderMId);
        buzFlowVO.setSourceSysId(sourceSysId);
		buzFlowVO.setOrderTypeId(orderTypeId);
		buzFlowVO.setOrderStatus(orderStatus);
		
		HttpRequestHandler.put("buzFlowVO", buzFlowVO);

		return "tiles.flow.baseContainer";
	}

	/**
	 * 取得「流程內容服務頁」
	 * 
	 * @param stepId
	 *            頁籤代碼
	 * @return String
	 */
	@RequestMapping(value = "/getServiceBuzStep", method = RequestMethod.POST)
	public String getServiceBuzStep(@RequestParam String flowId, @RequestParam String orderMId, @RequestParam String stepId) {
		// STEP.1 取得 Session Info
		SessionVO sessionVO = HttpSessionHandler.getSessionInfo();
		UserInfoVO userInfoVO = sessionVO.getUserInfoVO();
		String ntAccount = userInfoVO.getNtAccount();
		String msisdn = "";
		String subscriberId = "";
		String accountId = "";
		
		// STEP.1 取得 訂單資訊
		OrderInfoVO orderInfoVO = orderManageFacade.getOrderInfo(orderMId);
		
		if (orderInfoVO != null) {
			msisdn = orderInfoVO.getMsisdn();
		}
		
		// STEP.3 取得 Subscriber資訊
		CacheSubscriberInfoVO subscriberInfoVO = kernelRESTClientProxy.getCacheSubscriberInfoByMsisdn(msisdn, ntAccount);
		
		// STEP4.Put In Cache For App Part
		if (subscriberInfoVO != null) {
			subscriberId = subscriberInfoVO.getSubscriberId();
			accountId = subscriberInfoVO.getAccountId();
			
			putAppPartCache(subscriberInfoVO);
		}
		
		// STEP.5 取得 流程頁面資訊
		List<BuzStepPageVO> stepPageLs = buzFlowMgrFacade.getServiceBuzStep(flowId, stepId, orderInfoVO, userInfoVO, subscriberId, accountId);
		
		HttpRequestHandler.put("orderMId", orderMId);
		HttpRequestHandler.put("stepPageLs", stepPageLs);
		
		return "tiles.flow.container";
	}

    @RequestMapping(value = "/service-create-ia-level", method = RequestMethod.POST)
    public String getContent0027(@RequestParam String contentId) {
    	String empNo = HttpSessionHandler.getSessionInfo().getUserId();

    	List<BuzHierarchyEstablishmentVO> dataList = buzFlowMgrFacade.getServiceCreateIaLevel(contentId, empNo);

        HttpRequestHandler.put("dataList", dataList);

        return ForwardUtil.CONT0027.getView();
    }

	/**
	 * 案件內容共用按鈕 「暫存」
	 * 
	 * @author Adam
	 * @create date: Mar 31, 2017
	 *
	 * @return
	 */
	@RequestMapping(value = "/service-temp-save", method = {RequestMethod.POST})
	public @ResponseBody boolean serviceTempSave() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();
		
		UserInfoVO userInfoVO = HttpSessionHandler.getSessionInfo().getUserInfoVO();
		String ntAccount = userInfoVO.getNtAccount();
		
		OrderProcessVO vo = JsonUtil.fromJson(requestData, OrderProcessVO.class);
		vo.setUserId(userId);
		
		boolean result = orderManageFacade.temporarySave(vo);
		
    	OrderInfoVO orderInfoVO = orderManageFacade.getOrderInfo(vo.getOrderMId());
		String poolKey = orderInfoVO.getPoolKey();
		String processUser = userId;
		result = kernelRESTClientProxy.updateOrderStatus2TicketPool(poolKey, Constant.ORDER_STATUS_TMP_SAVE, processUser, ntAccount);
		
		return result;
	}

	/**
	 * 案件內容共用按鈕 「暫離」
	 * 
	 * @author Adam
	 * @create date: Mar 31, 2017
	 *
	 * @return
	 */
	@RequestMapping(value = "/service-temp-leave", method = {RequestMethod.POST})
	public @ResponseBody boolean serviceTempLeave() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();
		
		UserInfoVO userInfoVO = HttpSessionHandler.getSessionInfo().getUserInfoVO();
		String ntAccount = userInfoVO.getNtAccount();
		
		OrderProcessVO vo = JsonUtil.fromJson(requestData, OrderProcessVO.class);
		vo.setUserId(userId);
		
		boolean result = orderManageFacade.temporaryLeave(vo);

    	OrderInfoVO orderInfoVO = orderManageFacade.getOrderInfo(vo.getOrderMId());
		String poolKey = orderInfoVO.getPoolKey();
		String processUser = userId;
		result = kernelRESTClientProxy.updateOrderStatus2TicketPool(poolKey, Constant.ORDER_STATUS_TMP_LEAVE, processUser, ntAccount);
		
		return result;
	}
	
	/**
	 * 案件內容共用按鈕 「有效件」
	 * 
	 * @author Adam
	 * @create date: Mar 31, 2017
	 *
	 * @return
	 */
	@RequestMapping(value = "/service-success", method = {RequestMethod.POST})
	public @ResponseBody boolean serviceSuccess() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();
		
		UserInfoVO userInfoVO = HttpSessionHandler.getSessionInfo().getUserInfoVO();
		String ntAccount = userInfoVO.getNtAccount();
		
		OrderProcessVO vo = JsonUtil.fromJson(requestData, OrderProcessVO.class);
		vo.setUserId(userId);
		
    	OrderInfoVO orderInfoVO = orderManageFacade.getOrderInfo(vo.getOrderMId());
		String poolKey = orderInfoVO.getPoolKey();
		String processUser = userId;
		String sourceSysId = orderInfoVO.getSourceSysId();
		String sourceOrderId = orderInfoVO.getSourceOrderId();
		
		if (StringUtils.equalsIgnoreCase(sourceSysId, "ITT")) {
			String comment = kernelRESTClientProxy.getCommentFromITT(sourceOrderId);
			vo.setComment(comment);
		}
		
		boolean result = orderManageFacade.validOrder(vo);

		result = kernelRESTClientProxy.updateOrderStatus2TicketPool(poolKey, Constant.ORDER_STATUS_VALID, processUser, ntAccount);
		
		return result;
	}
	
	/**
	 * 案件內容共用按鈕 「無效件」
	 * 
	 * @author Adam
	 * @create date: Mar 31, 2017
	 *
	 * @return
	 */
	@RequestMapping(value = "/service-fail", method = {RequestMethod.POST})
	public @ResponseBody boolean serviceFail() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();
		
		UserInfoVO userInfoVO = HttpSessionHandler.getSessionInfo().getUserInfoVO();
		String ntAccount = userInfoVO.getNtAccount();
		
		OrderProcessVO vo = JsonUtil.fromJson(requestData, OrderProcessVO.class);
		vo.setUserId(userId);
		
    	OrderInfoVO orderInfoVO = orderManageFacade.getOrderInfo(vo.getOrderMId());
		String poolKey = orderInfoVO.getPoolKey();
		String processUser = userId;
		String sourceSysId = orderInfoVO.getSourceSysId();
		String sourceOrderId = orderInfoVO.getSourceOrderId();
		
		if (StringUtils.equalsIgnoreCase(sourceSysId, "ITT")) {
			String comment = kernelRESTClientProxy.getCommentFromITT(sourceOrderId);
			vo.setComment(comment);
		}
		
		boolean result = orderManageFacade.invalidOrder(vo);
		
		result = kernelRESTClientProxy.updateOrderStatus2TicketPool(poolKey, Constant.ORDER_STATUS_INVALID, processUser, ntAccount);
		
		return result;
	}
	
	/**
	 * 案件內容共用按鈕 「待系統回覆」
	 * 
	 * @author Adam
	 * @create date: Mar 31, 2017
	 *
	 * @return
	 */
	@RequestMapping(value = "/service-reply", method = {RequestMethod.POST})
	public @ResponseBody boolean serviceReply() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();
		
		UserInfoVO userInfoVO = HttpSessionHandler.getSessionInfo().getUserInfoVO();
		String ntAccount = userInfoVO.getNtAccount();
		
		OrderProcessVO vo = JsonUtil.fromJson(requestData, OrderProcessVO.class);
		vo.setUserId(userId);
		
		boolean result = orderManageFacade.pendingSurroundingReply(vo);
		
    	OrderInfoVO orderInfoVO = orderManageFacade.getOrderInfo(vo.getOrderMId());
		String poolKey = orderInfoVO.getPoolKey();
		String processUser = userId;
		result = kernelRESTClientProxy.updateOrderStatus2TicketPool(poolKey, Constant.ORDER_STATUS_PENDING_SURROUNDING_REPLY, processUser, ntAccount);
		
		return result;
	}
	
	/**
	 * 案件內容共用按鈕 「更改案件類別」
	 * 
	 * @author Adam
	 * @create date: Mar 31, 2017
	 *
	 * @return
	 */
	@RequestMapping(value = "/service-change-order-type", method = {RequestMethod.POST})
	public @ResponseBody boolean serviceChangeOrderType() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();
		
		UserInfoVO userInfoVO = HttpSessionHandler.getSessionInfo().getUserInfoVO();
		String ntAccount = userInfoVO.getNtAccount();
		
		OrderProcessVO vo = JsonUtil.fromJson(requestData, OrderProcessVO.class);
		vo.setUserId(userId);
		
		boolean result = orderManageFacade.updateOrderCategory(vo);
		
    	OrderInfoVO orderInfoVO = orderManageFacade.getOrderInfo(vo.getOrderMId());
		String poolKey = orderInfoVO.getPoolKey();
		String processUser = userId;
		kernelRESTClientProxy.updateOrderStatus2TicketPool(poolKey, Constant.ORDER_STATUS_TMP_SAVE, processUser, ntAccount);
		
		return result;
	}
	
	/**
	 * 案件內容共用按鈕 「驚嘆號」
	 * 
	 * @author Adam
	 * @create date: Mar 31, 2017
	 *
	 * @return
	 */
	@RequestMapping(value = "/service-show-message", method = {RequestMethod.POST}, produces = "application/json; charset=utf-8")
	public @ResponseBody String serviceShowMessage() {
		String requestData = HttpRequestHandler.getJsonData();
		OrderProcessVO orderProcessVO = JsonUtil.fromJson(requestData, OrderProcessVO.class);
		String orderMId = orderProcessVO.getOrderMId();
		
		String result = orderManageFacade.getOrderMessage(orderMId);
		
		return result;
	}
	
	/**
	 * 取得案件影像檔開啟資訊
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/get-order-image-url", method = RequestMethod.POST)
	public @ResponseBody String getOrderImageUrl() {
		// STEP.1 取得 Session Info
		SessionVO sessionVO = HttpSessionHandler.getSessionInfo();
	    String userId = sessionVO.getUserId();
	    String ntAccount = sessionVO.getUserInfoVO().getNtAccount();
		
	    // STEP.2 取得 Request Info
		Map<String, Object> requestMap = HttpRequestHandler.getJsonToMap();
		String orderMId = MapUtils.getString(requestMap, "orderMId");
			
		// STEP.3 取得案件影像檔連結資訊
	    OrderImageSettingVO orderImgVO = orderManageFacade.getOrderImageSetting(orderMId, userId, ntAccount);
	    
	    // STEP.4 Response Json Data
	    if(orderImgVO != null) {
	    	return JsonUtil.toJson(orderImgVO);
	    }
		
		return "";
	}
	
	private void addCIEMasterAPI(String userId, OrderInfoVO orderInfoVO) {
		SessionVO sessionVO = HttpSessionHandler.getSessionInfo();
	    String ntAccount = sessionVO.getUserInfoVO().getNtAccount();
		
		String msisdn = orderInfoVO.getMsisdn();
		
    	CacheSubscriberInfoVO cacheSubscriberInfoVO = kernelRESTClientProxy.getCacheSubscriberInfoByMsisdn(msisdn, ntAccount);
		AddCIEMasterVO addCIEMasterVO = new AddCIEMasterVO(userId, cacheSubscriberInfoVO);
		kernelRESTClientProxy.addCIEMaster(addCIEMasterVO, ntAccount);
	}

	private void updateOrderStatus2TicketPoolAPI(OrderInfoVO orderInfoVO, OrderProcessVO orderProcessVO) {
		SessionVO sessionVO = HttpSessionHandler.getSessionInfo();
	    String ntAccount = sessionVO.getUserInfoVO().getNtAccount();
		
		String poolKey = orderInfoVO.getPoolKey();
		String processUser = orderProcessVO.getProcessUserId();
		
		kernelRESTClientProxy.updateOrderStatus2TicketPool(poolKey, Constant.ORDER_STATUS_IN_PROCESS, processUser, ntAccount);
	}
	
	private void putAppPartCache(CacheSubscriberInfoVO subscriberInfoVO) {
		if (subscriberInfoVO != null) {
			SessionVO sessionVO = HttpSessionHandler.getSessionInfo();
			String ntAccount = sessionVO.getUserInfoVO().getNtAccount();
			String ownId = sessionVO.getOwnId();
			
			UserInfoVO userInfoVO = sessionVO.getUserInfoVO();
			String userId = userInfoVO.getUserId();
			
			String partyId = String.valueOf(subscriberInfoVO.getPartyId());
			String subscriberId = subscriberInfoVO.getSubscriberId();
			String msisdn = subscriberInfoVO.getMsisdn();
			
			// Put In Cache For App Part 客戶資訊
			kernelRESTClientProxy.getCustInfoForAppPart(ownId, null, null, msisdn);
			
			// Put In Cache For App Part 處理門號資訊
			ncpRESTClientProxy.saveUsingMsisdnToCache(ownId, partyId, subscriberId, msisdn);
			
			// Put In Cache For App Part Own Info CIE_ID
			AddCIEMasterVO cieMasterParamVO = new AddCIEMasterVO(userId, subscriberInfoVO);
			
			AddCIEMasterReturnVO cieMasterVO = kernelRESTClientProxy.addCIEMaster(cieMasterParamVO, ntAccount);
			
			String cieId = cieMasterVO.getCieId();
			
			OwnInfo ownInfo = NSPCacheUtil.getOwnInfo(ownId);
			ownInfo.setCieId(cieId);
			
			NSPCacheUtil.putOwnInfo(ownId, ownInfo);
		}
	}
	
}