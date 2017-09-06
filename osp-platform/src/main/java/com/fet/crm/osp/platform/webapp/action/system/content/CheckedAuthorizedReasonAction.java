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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fet.crm.osp.platform.core.facade.systeminfo.BuzFlowManageFacade;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzRecordContentVO;
import com.fet.crm.osp.platform.webapp.action.AbstractOSPAction;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;

/**
 * [CONT0025_勾選授權原因] 前端控制器 ( 參考文件: 客製頁簽說明.docx )<br>
 *
 * @author Adam Yeh
 */
@Controller
@RequestMapping("/flow/content")
public class CheckedAuthorizedReasonAction extends AbstractOSPAction {

	@Autowired
	private BuzFlowManageFacade buzFlowMgrFacade;
	
	@RequestMapping(value = "/cont-0025", method = RequestMethod.POST)
	public String getContent0025(@RequestParam String contentId, @RequestParam String orderMId) {
		List<BuzRecordContentVO> recordContentList = buzFlowMgrFacade.getServiceBuzRecordContent(contentId, orderMId);
		
		HttpRequestHandler.put("recordContentList", recordContentList);
		
		return ForwardUtil.CONT0025.getView();
	}

}
