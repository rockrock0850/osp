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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.db.warehouse.RecordContentWarehouse;
import com.fet.crm.osp.platform.core.pojo.BuzRecordContentPOJO;
import com.fet.crm.osp.platform.core.service.orderinfo.IBuzContentService;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderBuzContentVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderProcessVO;

/**
 * 客製頁簽: 勾選授權原因(A~E)
 * 
 * @author LawrenceLai, Adam Yeh
 */
@Service
public class BuzContent0025ServiceImpl implements IBuzContentService {
	
	@Autowired
	private RecordContentWarehouse recordContentWarehouse;

	@Override
	public boolean createContent(OrderProcessVO vo, Map<String, Object> dataMap, String orderStatus, List<OrderBuzContentVO> buzContentList) {
		List<Map<String, Object>> itemList = getContentList(dataMap);
		String userId = vo.getUserId();
		String orderMId = vo.getOrderMId();
		
		if (CollectionUtils.isNotEmpty(itemList)) {
			List<BuzRecordContentPOJO> dataList = new ArrayList<>();
			String contentId = "";
			int i = 1;
			
			for (Map<String, Object> item : itemList) {
				contentId = MapUtils.getString(item, "contentId");
				String itemId = MapUtils.getString(item, "itemId");  
				String itemName = MapUtils.getString(item, "itemName");
				String itemValue = MapUtils.getString(item, "itemValue");
				BigDecimal sortSequence = new BigDecimal(i++);
				String remark = MapUtils.getString(item, "remark");  
				Date createDate = new Date();
				
				BuzRecordContentPOJO pojo = new BuzRecordContentPOJO();
				pojo.setOrderMId(orderMId);
				pojo.setContentId(contentId);
				pojo.setItemId(itemId);
				pojo.setItemName(itemName);
				pojo.setItemValue(StringUtils.equalsIgnoreCase(itemValue, "on") ? "Y" : "N");
				pojo.setSortSequence(sortSequence);
				pojo.setRemark(remark);
				pojo.setUpdateDate(createDate);
				pojo.setUpdateUser(userId);
				pojo.setCreateDate(createDate);
				pojo.setCreateUser(userId);
				
				dataList.add(pojo);
			}
			
			recordContentWarehouse.deleteByOrderMId(orderMId);
			recordContentWarehouse.save(dataList);
		}
		
		return true;
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
	
}
