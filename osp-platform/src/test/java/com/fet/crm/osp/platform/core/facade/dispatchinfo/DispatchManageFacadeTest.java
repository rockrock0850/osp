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

package com.fet.crm.osp.platform.core.facade.dispatchinfo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.platform.SpringTest;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderDispatchVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.PauseDispatchVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.OrderDispatchCVO;

/**
 * [分派管理] 總體服務窗口 測試程式
 *
 * @author AndrewLee
 */
public class DispatchManageFacadeTest extends SpringTest {

    @Autowired
    private DispatchManageFacade facade;
    
    @Test
    public void testQueryDispatchInfoTest() {
        String pauseStartTime = "2016-01-01 01:01";
        String pauseEndTime = "2019-01-01 01:01";
        
        OrderDispatchCVO cvo = new OrderDispatchCVO();
        
        cvo.setPauseStartTime(pauseStartTime);
        cvo.setPauseEndTime(pauseEndTime);
        
        List<OrderDispatchVO> dataList = facade.getDispatchInfo(cvo);
        
        System.out.println("==================================================");
	    System.out.println(JsonUtil.toJson(dataList));
	    System.out.println("==================================================");
    }

    @Test
    public void testExcutePauseDispatchTest() throws UnknownHostException {
        String empNo = "86148";
        String empName = "施*妤";
        String pauseStartTime = "2016-01-01 01:01";
        String pauseEndTime = "2016-01-01 01:01";
        String createUser = "75944";
        String updateUser = "75944";
    	
        PauseDispatchVO vo = new PauseDispatchVO();
        
        vo.setEmpNo(empNo);
        vo.setEmpName(empName);
        vo.setPauseStartTime(pauseStartTime);
        vo.setPauseEndTime(pauseEndTime);
        vo.setCreateUser(createUser);
        vo.setUpdateUser(updateUser);
        
        List<PauseDispatchVO> dataList = new ArrayList<>();
        dataList.add(vo);
        
        facade.excutePauseDispatch(dataList);
    }
    
}
