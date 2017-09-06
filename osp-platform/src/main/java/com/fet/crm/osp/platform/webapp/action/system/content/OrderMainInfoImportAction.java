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

package com.fet.crm.osp.platform.webapp.action.system.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.facade.orderinfo.OrderManageFacade;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderInfoVO;
import com.fet.crm.osp.platform.webapp.action.AbstractOSPAction;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;

/**
 * [CONT0022_資料輸入] 前端控制器 ( 參考文件: 客製頁簽說明.docx )<br>
 *
 * @author Adam Yeh, AllenChen
 */
@Controller
@RequestMapping("/flow/content")
public class OrderMainInfoImportAction extends AbstractOSPAction {
	
	@Autowired
	private OrderManageFacade orderManageFacade;

    @RequestMapping(value = "/cont-0022", method = RequestMethod.POST)
    public String getContent0022() {
        return ForwardUtil.CONT0022.getView();
    }

    @RequestMapping(value = "/modify-order-main-info", method = RequestMethod.POST)
    public @ResponseBody boolean modifyOrderMainInfo() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();
		
		OrderInfoVO vo = JsonUtil.fromJson(requestData, OrderInfoVO.class);
		vo.setUpdateUser(userId);
    	boolean result = orderManageFacade.updateOrderMainInfo(vo);

        return result;
    }

    @RequestMapping(value = "/get-order-main-info", method = RequestMethod.POST)
    public String getOrderMainInfo(@RequestParam String orderMId) {

    	OrderInfoVO orderInfoVO = orderManageFacade.getOrderInfo(orderMId);

		String responseData = JsonUtil.toJson(orderInfoVO);
		HttpRequestHandler.put("responseData", responseData);

		return ForwardUtil.JSON.getView();
    }

}
