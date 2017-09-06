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

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fet.crm.osp.platform.mware.client.restful.proxy.OSPKernelRESTClientProxy;
import com.fet.crm.osp.platform.webapp.action.AbstractOSPAction;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;

/**
 * [CONT0024_Email通知業務] 前端控制器 ( 參考文件: 客製頁簽說明.docx )<br>
 *
 * @author Adam Yeh
 */
@Controller
@RequestMapping("/flow/content")
public class EmailNotifySalesAction extends AbstractOSPAction {

	@Autowired
	private OSPKernelRESTClientProxy proxy;
	

    @RequestMapping(value = "/cont-0024", method = RequestMethod.POST)
    public String getContent0024() {
        return ForwardUtil.CONT0024.getView();
    }

    @RequestMapping(value = "/get-sales-id", method = RequestMethod.POST)
    public @ResponseBody String getSalesId() {
    	Map<String, Object> requestMap = HttpRequestHandler.getJsonToMap();
    	String ivrCode = MapUtils.getString(requestMap, "ivrCode");
    	
    	String salesId = proxy.getUserIdByIvrCode(ivrCode);
    	
        return salesId;
    }

}
