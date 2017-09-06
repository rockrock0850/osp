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

import com.fet.crm.osp.platform.core.vo.orderinfo.OrderBuzContentVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderProcessVO;

/**
 * 
 * @author LawrenceLai, Adam Yeh
 */
public interface IBuzContentService {
	
	/*
	 * 注意: 在新增加的商業邏輯之中, 需要引用到其他content的資料。
	 * 又因無法控制其他content將資料存入資料庫的時間( 導致某content需要某content的時候, 會發生從資料庫拿不到資料的情況, 換句話說每個content之間產生了相依性 ), 
	 * 所以得出解決辦法為, 再把從前端收到的整包content傳給每支Service, 讓Service自己將其他content的資料過濾並使用。
	 * 
	 * Noted by Adam 2017-05-11
	 */
	public boolean createContent(OrderProcessVO vo, Map<String, Object> dataMap, String orderStatus, List<OrderBuzContentVO> buzContentList);
	
}
