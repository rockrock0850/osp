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

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.platform.SpringTest;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.FlowSourceMapVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.OrderTypeInfoVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.OrderTypeMaintainVO;

/**
 * [案件類別管理] 總體服務窗口 測試程式
 *
 * @author AndrewLee
 */
public class OrderTypeManageFacadeTest extends SpringTest {

    @Autowired
    OrderTypeManageFacade facade;

    @Test
    public void testGetOrderTypeInfoByTypeName() {
        String orderTypeName = "月租啟用";

        List<OrderTypeInfoVO> dataList = facade.getOrderTypeInfoByTypeName(orderTypeName);

        System.out.println("==================================================");
        System.out.println(JsonUtil.toJson(dataList));
        System.out.println("==================================================");
    }

    @Test
    public void testGetOrderTypeInfoByTypeId() {
        String orderTypeId = "OSPL4008";

        OrderTypeInfoVO vo = facade.getOrderTypeInfoByTypeId(orderTypeId);

        System.out.println("==================================================");
        System.out.println(vo.toString());
        System.out.println("==================================================");
    }

    @Test
    public void testModifyOrderTypeInfo() throws UnknownHostException {
        String orderTypeId = "OSPL4013";
        String empName = InetAddress.getLocalHost().getHostName();
        BigDecimal successSec = new BigDecimal(50.0);
        BigDecimal failSec = new BigDecimal(50.0);
        BigDecimal regularTime = new BigDecimal(90.0);
        BigDecimal overtime = new BigDecimal(68.0);
        BigDecimal criticalCounts = new BigDecimal(50.0);
        BigDecimal overtimeCounts = new BigDecimal(500.0);

        OrderTypeMaintainVO vo = new OrderTypeMaintainVO();

        vo.setOrderTypeId(orderTypeId);
        vo.setSuccessSec(successSec);
        vo.setFailSec(failSec);
        vo.setRegularTime(regularTime);
        vo.setOvertime(overtime);
        vo.setCriticalCounts(criticalCounts);
        vo.setOvertimeCounts(overtimeCounts);
        vo.setEmail("tw.yahoo.com");
        vo.setUpdateUser(empName);

        facade.modifyOrderTypeInfo(vo);
    }

    @Test
    public void testGetFlowSourceByorderTypeId() {
        String orderTypeId = "OSPL4001";

        List<FlowSourceMapVO> dataList = facade.getFlowSourceByorderTypeId(orderTypeId);

        System.out.println("==================================================");
        System.out.println(JsonUtil.toJson(dataList));
        System.out.println("==================================================");
    }

}
