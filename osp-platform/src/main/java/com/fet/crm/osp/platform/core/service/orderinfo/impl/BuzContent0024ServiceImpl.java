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

package com.fet.crm.osp.platform.core.service.orderinfo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fet.crm.osp.platform.core.common.Constant;
import com.fet.crm.osp.platform.core.common.util.DateUtil;
import com.fet.crm.osp.platform.core.common.util.FreeMarkerUtil;
import com.fet.crm.osp.platform.core.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.db.warehouse.NotifyDetailWareHouse;
import com.fet.crm.osp.platform.core.db.warehouse.NotifyMainWareHouse;
import com.fet.crm.osp.platform.core.db.warehouse.OrderMainOspWarehouse;
import com.fet.crm.osp.platform.core.db.warehouse.OrderStatusSettingWarehouse;
import com.fet.crm.osp.platform.core.db.warehouse.RefStaffWareHouse;
import com.fet.crm.osp.platform.core.db.warehouse.SysOptionsComboWareHouse;
import com.fet.crm.osp.platform.core.db.warehouse.SysReasonWarehouse;
import com.fet.crm.osp.platform.core.pojo.BuzRecordContentPOJO;
import com.fet.crm.osp.platform.core.pojo.NotifyDetailPOJO;
import com.fet.crm.osp.platform.core.pojo.NotifyMainPOJO;
import com.fet.crm.osp.platform.core.pojo.OrderMainOspPOJO;
import com.fet.crm.osp.platform.core.pojo.OrderStatusSettingPOJO;
import com.fet.crm.osp.platform.core.pojo.RefStaffPOJO;
import com.fet.crm.osp.platform.core.pojo.SysOptionsComboPOJO;
import com.fet.crm.osp.platform.core.pojo.SysReasonPOJO;
import com.fet.crm.osp.platform.core.service.orderinfo.IBuzContentService;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderBuzContentVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderProcessVO;
import com.google.gson.JsonObject;

/**
 * 客製頁簽: Email通知業務
 * 
 * @author LawrenceLai, Adam Yeh
 */
@Service
public class BuzContent0024ServiceImpl implements IBuzContentService {

	@Autowired
	private OrderMainOspWarehouse orderMainOspWarehouse;
	@Autowired
	private SysOptionsComboWareHouse sysOptionsComboWareHouse;
	@Autowired
	private NotifyMainWareHouse notifyMainWareHouse;
	@Autowired
	private OrderStatusSettingWarehouse orderStatusSettingWarehouse;
	@Autowired
	private SysReasonWarehouse sysReasonWarehouse;
	@Autowired
	private NotifyDetailWareHouse notifyDetailWareHouse;
	@Autowired
	private RefStaffWareHouse refStaffWareHouse;
	
	@Override
	public boolean createContent(OrderProcessVO vo, Map<String, Object> dataMap, String orderStatus, List<OrderBuzContentVO> buzContentList) {
		String orderMId = vo.getOrderMId();
		String userId = vo.getUserId();
		
		boolean isNotifySales = updateOspMain(orderMId, userId, dataMap);
		if (isNotifySales) {
			NotifyMainPOJO notifyMainPOJO = createNotifyMain(orderMId, userId, orderStatus, buzContentList);
			boolean result = createNotifyDetail(orderMId, notifyMainPOJO);
		}
		
		return true;
	}

	private boolean updateOspMain(String orderMId, String userId, Map<String, Object> dataMap) {
		String isNoticeSales = MapUtils.getString(dataMap, "isNoticeSales");
		String salesId = MapUtils.getString(dataMap, "salesId");
		
		OrderMainOspPOJO pojo = new OrderMainOspPOJO();
		pojo.setOrderMId(orderMId);
		pojo.setIsNoticeSales(StringUtils.equalsIgnoreCase(isNoticeSales, "on") ? "Y" : "N");
		pojo.setSalesId(salesId);
		pojo.setUpdateDate(new Date());
		pojo.setUpdateUser(userId);
		
		orderMainOspWarehouse.update(pojo);
			
		return StringUtils.equalsIgnoreCase(isNoticeSales, "on") ? true : false;
	}
	
	private NotifyMainPOJO createNotifyMain(String orderMId, String userId, String orderStatus, List<OrderBuzContentVO> list) {
		List<RefStaffPOJO> refStaffPOJOs = refStaffWareHouse.findByIdempno(userId);
		String emailRecipients = getEmailRecipients(refStaffPOJOs);
		
		List<SysOptionsComboPOJO> sysOptionsComboPOJOs = sysOptionsComboWareHouse.findByComboType("MAIL_SALES_CP");
		String cpRecipients = getCpRecipients(sysOptionsComboPOJOs);
		
		OrderMainOspPOJO orderMainOspPOJO = orderMainOspWarehouse.findByOrderMId(orderMId);
		
		// 判斷不為[有效件]/[無效件]的話, 就不異動NotifyMain和NotifyDetail資料表
		if (!StringUtils.equals(orderStatus, Constant.ORDER_STATUS_VALID) && 
				!StringUtils.equals(orderStatus, Constant.ORDER_STATUS_INVALID)) {
			return null;
		}

		String content = getContent(orderMainOspPOJO, list);

		// 將content轉成 html
		content = mergeHtml(content);

		NotifyMainPOJO pojo = new NotifyMainPOJO();
		pojo.setNotifyMainId(IdentifierIdUtil.getUuid());
		pojo.setNotifyType("E");
		pojo.setNotifyFunc("2");
		pojo.setEmailRecipients(emailRecipients);
		pojo.setEmailCpRecipients(cpRecipients);
		pojo.setHasSend("N");
		pojo.setSubject("特授件處理結果通知");
		pojo.setContent(content);
		pojo.setCreateDate(new Date());
		pojo.setCreateUser(userId);
		pojo.setUpdateDate(new Date());
		pojo.setUpdateUser(userId);
		
		boolean result = notifyMainWareHouse.save(pojo);
		
		return result == true ? pojo : null;
	}

	private String getContent(OrderMainOspPOJO pojo, List<OrderBuzContentVO> list) {
		if (pojo == null) {
			return "";
		}

		JsonObject content = new JsonObject();
		
		// 組BUZ_RECORD_CONTENT的Email資料
		content = getBuzRecordContentEmailInfo(content, list);

		// 組SYS_ORDER_STATUS_SETTING的Email資料
		content = getSysOrderStatusSettingEmailInfo(content, pojo);

		// 組SYS_RESON的Email資料
		content = getSysResonEmailInfo(content, pojo);
		
		// 組ORDER_MAIN_OSP的Email資料
		content = getOrderMainOspEmailInfo(content, pojo);
		
		return content.toString();
	}

	private JsonObject getSysResonEmailInfo(JsonObject content, OrderMainOspPOJO pojo) {
		String reasonId = pojo.getProcessReason();
		String resultId = pojo.getProcessResult();
		SysReasonPOJO reasonPOJO = sysReasonWarehouse.findByReasonId(reasonId);
		SysReasonPOJO resultPOJO = sysReasonWarehouse.findByReasonId(resultId);
		
		if(reasonPOJO != null && resultPOJO != null) {
			String reasonText = reasonPOJO.getReasonText();
			String resultText = resultPOJO.getReasonText();

			content.addProperty("處理原因", reasonText);
			content.addProperty("處理結果", resultText);
		}
		
		return content;
	}

	private JsonObject getSysOrderStatusSettingEmailInfo(JsonObject content, OrderMainOspPOJO pojo) {
		String statusId = pojo.getOrderStatus();
		OrderStatusSettingPOJO orderStatusSettingPOJO = orderStatusSettingWarehouse.findByStatusId(statusId);
		
		if(orderStatusSettingPOJO != null) {
			String statusName = orderStatusSettingPOJO.getStatusName();
			content.addProperty("案件狀態", statusName);
		}
		
		return content;
	}

	private JsonObject getBuzRecordContentEmailInfo(JsonObject content, List<OrderBuzContentVO> voList) {
		List<BuzRecordContentPOJO> pojoList = getRecordContentList(voList);

		for (int i = 0; i < pojoList.size(); i++) {
			BuzRecordContentPOJO pojo = pojoList.get(i);
			String itemName = pojo.getItemName();
			String itemValue = pojo.getItemValue();
			
			if (StringUtils.equalsIgnoreCase(itemValue, "on")) {
				content.addProperty("授權原因-" + i, itemName);
			}
		}
		
		return content;
	}

	private List<BuzRecordContentPOJO> getRecordContentList(List<OrderBuzContentVO> voList) {
		List<BuzRecordContentPOJO> list = new ArrayList<>();
		
		for (OrderBuzContentVO vo : voList) {
			String contentId = vo.getContentId();
			if (contentId.startsWith("CONT0025")) {
				List<Map<String, Object>> map = getContentList(vo.getContentData());
				for (Map<String, Object> m : map) {
					ObjectMapper mapper = new ObjectMapper();
					BuzRecordContentPOJO buzRecordContentPOJO = mapper.convertValue(m, BuzRecordContentPOJO.class);
					list.add(buzRecordContentPOJO);
				}
			}
		}
		
		return list;
	}

	// 將formData重組成List<Map<String, Object>> 
	private List<Map<String, Object>> getContentList(Map<String, Object> dataMap) {
		if (MapUtils.isNotEmpty(dataMap)) {
			List<Map<String, Object>> itemList = new ArrayList<>();
			
			for (String key : dataMap.keySet()) {
				Map<String, Object> item = new HashMap<>();
				
				if(key.startsWith("id_")) {
					String itemId = MapUtils.getString(dataMap, key);
					
					item.put("itemId", itemId);
					item.put("itemName", MapUtils.getString(dataMap, "name_" + itemId));
					item.put("itemValue", MapUtils.getString(dataMap, "value_" + itemId));
					item.put("remark", MapUtils.getString(dataMap, "remark_" + itemId));
					item.put("orderMId", MapUtils.getString(dataMap, "orderMId"));
					item.put("contentId", MapUtils.getString(dataMap, "contentId"));
					item.put("createUser", MapUtils.getString(dataMap, "userId"));
					itemList.add(item);
				}
			}
			
			return itemList;
		}
		
		return null;
	}

	private JsonObject getOrderMainOspEmailInfo(JsonObject content, OrderMainOspPOJO pojo) {
		Date date = pojo.getOspCreateTime();
		String ospCreateTime = DateUtil.fromDate(date, DateUtil.DATE_TIME_PATTERN);
		String sourceProdTypeName = pojo.getSourceProdTypeName();
		String msisdn = pojo.getMsisdn();
		String comment = pojo.getCommment();
		
		content.addProperty("進件時間", ospCreateTime);
		content.addProperty("產品類別", sourceProdTypeName);
		content.addProperty("門號", msisdn);
		content.addProperty("備註", comment);
		
		return content;
	}

	private String getEmailRecipients(List<RefStaffPOJO> pojoList) {
		if (CollectionUtils.isEmpty(pojoList)) {
			return "";
		}

		StringBuilder email = new StringBuilder();
		for (RefStaffPOJO pojo : pojoList) {
			email.append(pojo.getEmail());
			email.append(",");
		}
		
		return email.toString();
	}

	private String getCpRecipients(List<SysOptionsComboPOJO> pojoList) {
		if (CollectionUtils.isEmpty(pojoList)) {
			return "";
		}

		StringBuilder cp = new StringBuilder();
		for (SysOptionsComboPOJO pojo : pojoList) {
			cp.append(pojo.getComboContent());
			cp.append(", ");
		}
		
		return cp.toString();
	}
	
	private boolean createNotifyDetail(String orderMId, NotifyMainPOJO notifyMainPOJO) {
		if (notifyMainPOJO == null) {
			return false;
		}
		
		NotifyDetailPOJO pojo = new NotifyDetailPOJO();
		String notifyMainId = notifyMainPOJO.getNotifyMainId();
		String userId = notifyMainPOJO.getCreateUser();

		pojo.setNotifyDetailId(IdentifierIdUtil.getUuid());
		pojo.setNotifyMainId(notifyMainId);
		pojo.setOrderMId(orderMId);
		pojo.setCreateDate(new Date());
		pojo.setCreateUser(userId);
		pojo.setUpdateDate(new Date());
		pojo.setUpdateUser(userId);
		
		notifyDetailWareHouse.save(pojo);
		
		return true;
	}
	
	private String mergeHtml(String content) {
		List<Object[]> paramLs = new ArrayList<>();

		// step1 轉json object
		JSONObject jsonObj = new JSONObject(content);

		String createTime = "進件時間";
		String productType = "產品類別";
		String msisdn = "門號";
		String authorizationReason = "";
		String orderStauts = "案件狀態";
		String processResult = "處理結果";
		String processReason = "處理原因";
		String remark = "備註";

		// step2 取出value
		for(Iterator iterator = jsonObj.keySet().iterator(); iterator.hasNext();) {
		    String key = (String) iterator.next();

		    if (key.contains("授權原因")) {
		    	authorizationReason += jsonObj.get(key);
		    	authorizationReason += ";";
		    }
		}

		Object[] param = new Object[] { jsonObj.get(createTime), jsonObj.get(productType), jsonObj.get(msisdn), authorizationReason
				, jsonObj.get(orderStauts), jsonObj.get(processResult), jsonObj.get(processReason), jsonObj.get(remark)};

		paramLs.add(param);

		Map<String, Object> templateParamMP = new HashMap<>();
		templateParamMP.put("gridData", paramLs);

		content = FreeMarkerUtil.merge(templateParamMP, "notify", "mailTemplate");

		return content;
	}
	
}
