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

package com.fet.crm.osp.platform.core.facade.orderinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.platform.core.service.orderinfo.IOperateManageService;
import com.fet.crm.osp.platform.core.vo.systeminfo.OperateManageVO;

/**
 * 
 * 
 * @author Adam
 */
@Service
public class OperateManageFacade {
	
	@Autowired
	private IOperateManageService specialOrderProcessService;
	
	/**
	 * 輸入特殊案件內容
	 * 
	 * @author Adam
	 * @create date: Apr 27, 2017
	 *
	 * @param vo
	 * @return
	 */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean createOperateContent(OperateManageVO vo) {
        return specialOrderProcessService.createContent(vo);
    }

}