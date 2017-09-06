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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.common.util.DateUtil;
import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.service.orderinfo.IOperateManageService;
import com.fet.crm.osp.platform.core.vo.systeminfo.OperateManageVO;

/**
 * 客製頁簽: 特殊案件
 * 
 * @author LawrenceLai, Adam Yeh
 */
@Service
public class OperateManageServiceImpl implements IOperateManageService {

    @Autowired
    private JdbcDAO jdbcDAO;
	
	@Override
	public boolean createContent(OperateManageVO vo) {
		if (vo == null) {
			return false;
		}
		
		List<Map<String, Object>> contentList = getContentList(vo);
		String sqlText = ResourceFileUtil.SQL_ORDER.getResource("UPDATE_BUZ_RECORD_ROUTINE");
		jdbcDAO.batchUpdate(sqlText, contentList);
		
		return true;
	}
	
	// 將formData重組成List<Map<String, Object>>
	private List<Map<String, Object>> getContentList(OperateManageVO vo) {
		if (vo == null) {
			return Collections.emptyList();
		}
		
		String contentId = vo.getContentId();
		String userId = vo.getUserId();
		Map<String, Object> dataMap = vo.getContentData();
		List<Map<String, Object>> itemList = new ArrayList<>();

		for (String key : dataMap.keySet()) {
			Map<String, Object> item = new HashMap<>();
			
			if(key.startsWith("id_")) {
				String itemId = MapUtils.getString(dataMap, key);
				String sortSequence = MapUtils.getString(dataMap, "sort_" + itemId);
				String itemName = MapUtils.getString(dataMap, "name_" + itemId);
				String itemValue = MapUtils.getString(dataMap, "reserv_" + itemId);
				String createDate = MapUtils.getString(dataMap, "create_date_" + itemId);
				String unit = MapUtils.getString(dataMap, "unit_" + itemId);

				item.put("CONTENT_ID", contentId);
				item.put("ITEM_ID", itemId);
				item.put("ITEM_NAME", itemName);
				item.put("ITEM_VALUE", getItemValue(unit, itemValue));
				item.put("SORT_SEQUENCE", sortSequence);
				item.put("PROCESS_USER_ID", userId);
				item.put("PROCESS_DATE", new Date());
				item.put("CREATE_USER", userId);
				item.put("CREATE_DATE", DateUtil.toDate(createDate, DateUtil.DATE_TIME_PATTERN));
				item.put("UPDATE_USER", userId);
				item.put("UPDATE_DATE", new Date());
				itemList.add(item);
			}
		}
		
		return itemList;
	}

	private Integer getItemValue(String unit, String itemValue) {
		if (StringUtils.isBlank(itemValue)) {
			return null;
		}
		int integer = Integer.valueOf(itemValue);
		
		return StringUtils.equals("分", unit) ? (integer*60) : integer;
	}

}
