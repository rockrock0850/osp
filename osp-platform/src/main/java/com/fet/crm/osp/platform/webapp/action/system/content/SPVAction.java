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

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.facade.orderinfo.OrderManageFacade;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderDetailSpvVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderInfoVO;
import com.fet.crm.osp.platform.mware.client.restful.proxy.OSPKernelRESTClientProxy;
import com.fet.crm.osp.platform.mware.client.vo.PromotionDetailReturnVO;
import com.fet.crm.osp.platform.webapp.action.AbstractOSPAction;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.logger.WebappLoggerFactory;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;

/**
 * [CONT0014_SPV] 前端控制器 ( 參考文件: 客製頁簽說明.docx )<br>
 *
 * @author Adam Yeh
 */
@Controller
@RequestMapping("/flow/content")
public class SPVAction extends AbstractOSPAction {
	
	private static Logger logger = WebappLoggerFactory.getLogger(SPVAction.class);
	
	@Autowired
	private OrderManageFacade orderManageFacade;
	@Autowired
	private OSPKernelRESTClientProxy proxy;

    @RequestMapping(value = "/cont-0014", method = RequestMethod.POST)
    public String getContent0014() {
        return ForwardUtil.CONT0014.getView();
    }

    @RequestMapping(value = "/get-cont-0014-promotion", method = RequestMethod.POST)
    public @ResponseBody String getContent0019Promotion() {
    	String requseData = HttpRequestHandler.getJsonData();
    	OrderInfoVO orderInfoVO = orderManageFacade.getOrderInfo(requseData);
    	
        return orderInfoVO.getPromotionId();
    }
    
	@RequestMapping(value = "/get-spv-result", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String getSpvResult() {
    	Map<String, Object> requseData = HttpRequestHandler.getJsonToMap();
    	String promotionId = MapUtils.getString(requseData, "promotionId");
    	
    	PromotionDetailReturnVO result = proxy.getPromotionDetail(promotionId);
    	
    	if (result == null) {
    		logger.info("API result: " + result);
    		
    		return "";
    	}
    	
    	String responseData = JsonUtil.toJson(result);
    	
        return responseData;
	}
    
	/**
	 * 取得 ORDER_DETAIL_SPV 資料
	 * 
	 * @param foolId
	 * @return String
	 */
	@RequestMapping(value = "/get-order-detail-spv", method = {RequestMethod.POST, RequestMethod.GET})
	public String getOrderDetailSpvGrid(@RequestParam String orderMId) {
		if(StringUtils.isBlank(orderMId)) {
			return ForwardUtil.SPVGRIDVIEW.getView();
		}
		
		List<OrderDetailSpvVO> orderDetailLs = orderManageFacade.getOrderDetailSpv(orderMId);
		
        String responseData = JsonUtil.toJson(orderDetailLs);
        
        HttpRequestHandler.put("responseData", responseData);
		
        return ForwardUtil.SPVGRIDVIEW.getView();
	}

}
