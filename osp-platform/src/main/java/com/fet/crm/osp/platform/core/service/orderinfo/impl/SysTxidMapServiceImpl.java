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

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.db.warehouse.SysTxidMapWareHouse;
import com.fet.crm.osp.platform.core.pojo.SysTxidMapPOJO;
import com.fet.crm.osp.platform.core.service.orderinfo.ISysTxidMapService;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderProcessVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.SysTxidMapVO;

/**
 * [] 服務實作
 * 
 * @author Adam Yeh
 */
@Service
public class SysTxidMapServiceImpl implements ISysTxidMapService {

	@Autowired
	private SysTxidMapWareHouse sysTxidMapWarehouse;
	
	@Override
	public boolean createSysTxidMap(OrderProcessVO orderProcessVO) {
		if (orderProcessVO != null) {
			String orderMId = orderProcessVO.getOrderMId();
			List<String> ospKeyList = orderProcessVO.getOspKeyList();
			
			if (CollectionUtils.isNotEmpty(ospKeyList)) {
				for (String ospKey : ospKeyList) {
					SysTxidMapPOJO pojo = new SysTxidMapPOJO();
					pojo.setOrderMId(orderMId);
					pojo.setOspKey(ospKey);
					pojo.setCreateDate(new Date());
					pojo = sysTxidMapWarehouse.save(pojo);
					
					SysTxidMapVO vo = new SysTxidMapVO();
			        BeanUtils.copyProperties(pojo, vo);
				}
			}
		}
		
		return true;
	}
	
}
