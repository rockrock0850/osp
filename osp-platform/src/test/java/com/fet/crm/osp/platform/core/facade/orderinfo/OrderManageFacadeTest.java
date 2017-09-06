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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.platform.SpringTest;
import com.fet.crm.osp.platform.core.common.Constant;
import com.fet.crm.osp.platform.core.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderAssignVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderDetailSpvVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderImageSettingVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderInfoVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderProcessVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.RecordContentVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.TodoOrderInfoVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.OrderInfoCVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.TodoOrderCVO;
import com.fet.crm.osp.platform.webapp.vo.NotifyOtherSalesVO;

/**
 * [訂單管理] 總體服務窗口 測試單元
 * 
 * @author LawrenceLai
 */
public class OrderManageFacadeTest extends SpringTest {

    @Autowired
    private OrderManageFacade facade;

    @Test
    public void testBatchCreateOrder () {
    	List<OrderInfoVO> voList = new ArrayList<>();
    	for (int i = 0; i < 1; i++) {
	        String orderMId = IdentifierIdUtil.getOspOrderMId();
	        String poolKey = IdentifierIdUtil.getUuid();
	        String sourceOrderId = "0987654321";// From service ticketNo
	        String teamGroup = "1";
	        String sourceSysId = "Email";
	        String sourceProdTypeId = "0987654321";
	        String sourceProdTypeName = "0987654321";
	        String criticalFlag = "N";
	        String sourceCreateTime = "2017-01-01 22:11:33";
	        String ospCreateTime = "2017-01-01 22:11:33";
	        String msisdn = "0987654321";// From service msisdn
	        String counts = "1";
	        String expectProcessTime = "2017-01-01 22:11:33";
	        String expectCompleteTime = "2017-01-01 22:11:33";
	        String custId = "0987654321";// From service custId
	        String processUserId = "0987654321";
	        String processUserName = "0987654321";
	        String orderTypeName = sourceProdTypeName;
	        String orderStatus = "010";
	        String flowId = "0987654321";
	        String isNoticeSales = "N";
	        String custType = "NBT04";
	        String userId = "65196";
	        String orderTypeId = "OSPL4027";
	    	
	        OrderInfoVO vo = new OrderInfoVO();
	        vo.setOrderMId(orderMId);
	        vo.setPoolKey(poolKey);
	        vo.setSourceOrderId(sourceOrderId);
	        vo.setTeamGroup(teamGroup);
	        vo.setSourceSysId(sourceSysId);
	        vo.setSourceProdTypeId(sourceProdTypeId);
	        vo.setSourceProdTypeName(sourceProdTypeName);
	        vo.setCriticalFlag(criticalFlag);
	        vo.setSourceCreateTime(sourceCreateTime);
	        vo.setOspCreateTime(ospCreateTime);
	        vo.setMsisdn(msisdn);
	        vo.setCounts(counts);
	        vo.setExpectProcessTime(expectProcessTime);
	        vo.setExpectCompleteTime(expectCompleteTime);
	        vo.setCustId(custId);
	        vo.setProcessUserId(processUserId);
	        vo.setProcessUserName(processUserName);
	        vo.setOrderTypeId(orderTypeId);
	        vo.setOrderTypeName(orderTypeName);
	        vo.setOrderStatus(orderStatus);
	        vo.setFlowId(flowId);
	        vo.setIsNoticeSales(isNoticeSales);
	        vo.setCreateUser(userId);
	        vo.setUpdateUser(userId);
	        vo.setCustType(custType);
	        voList.add(vo);
    	}
    	boolean result = facade.batchCreateOrder(voList);

        System.out.println("==================================================");
        System.out.println(result);
        System.out.println("==================================================");
    }

    @Test
    public void testUpdateOrderStatus () {
		OrderProcessVO vo = new OrderProcessVO();
		vo.setOrderMId("OSP-20170222-171213-f37dc4");
		vo.setUserId("65196");
		vo.setProcessUserId("65196");
		vo.setProcessUserName("xxxxxxx");
		
		OrderProcessVO result = facade.updateOrderStatus(vo, Constant.ORDER_STATUS_IN_PROCESS);

        System.out.println("==================================================");
        System.out.println(result);
        System.out.println("==================================================");
    }

    @Test
    public void testUpdateOrderCategory() {
        String orderMId = "OSP-20170515-175300-5630bc";
        String userId = "65196";
        String orderTypeId = "OSPL4003";
        String orderTypeName = "統一轉遠傳月租";
        
        OrderProcessVO vo = new OrderProcessVO();
        vo.setOrderMId(orderMId);
        vo.setUserId(userId);
        vo.setOrderTypeId(orderTypeId);
        vo.setOrderTypeName(orderTypeName);
		
		boolean result = facade.updateOrderCategory(vo);

        System.out.println("==================================================");
        System.out.println(result);
        System.out.println("==================================================");
    }

    @Test
    public void testUpdateOrderMainInfo() {
        String orderMId = "OSP-20170222-171213-f37dc4";
        String orderTypeName = "測試測試測試測試";
        String msisdn = "0000000000";
        String ivrCode = "00000";
        String custId = "000";
        String salesId = "0000";
        String promotionId = "00000";
        String updateUser = "65196";
        String counts = "1234";
        
		OrderInfoVO vo = new OrderInfoVO();
		vo.setOrderMId(orderMId);
		vo.setOrderTypeName(orderTypeName);
		vo.setMsisdn(msisdn);
		vo.setIvrCode(ivrCode);
		vo.setCustId(custId);
		vo.setSalesId(salesId);
		vo.setPromotionId(promotionId);
		vo.setUpdateDate(new Date());
		vo.setUpdateUser(updateUser);
		vo.setCounts(counts);
		
		boolean result = facade.updateOrderMainInfo(vo);

        System.out.println("==================================================");
        System.out.println(result);
        System.out.println("==================================================");
    }

    @Test
    public void testGetOrderInfo() {
        String orderMId = "OSP-20170413-100000-ts0137";

        OrderInfoVO vo = facade.getOrderInfo(orderMId);

        System.out.println("==================================================");
        System.out.println(vo);
        System.out.println("==================================================");
    }

    @Test
    public void testGetAssignOrderInfo() {
        String sourceCreateTimeBegin = "2017-02-16 00:00:00";
        String sourceCreateTimeEnd = "";
        String expectProcessTimeBegin = "";
        String expectProcessTimeEnd = "";
        String orderTypeId = "";
        String imgIdApid = "";
        String sourceSysId = "";
        String msisdn = "";
        String custName = "";
        String custId = "";
        String sourceProdTypeId = "";
        String operateType = "";
        String ivrCode = "";
        String salesId = "";
        String processUserName = "";
        String orderStatus = "";

        OrderInfoCVO cvo = new OrderInfoCVO();

        cvo.setSourceCreateTimeBegin(sourceCreateTimeBegin);
        cvo.setSourceCreateTimeEnd(sourceCreateTimeEnd);
        cvo.setExpectProcessTimeBegin(expectProcessTimeBegin);
        cvo.setExpectProcessTimeEnd(expectProcessTimeEnd);
        cvo.setOrderTypeId(orderTypeId);
        cvo.setImgIdApid(imgIdApid);
        cvo.setSourceSysId(sourceSysId);
        cvo.setMsisdn(msisdn);
        cvo.setCustName(custName);
        cvo.setCustId(custId);
        cvo.setSourceProdTypeId(sourceProdTypeId);
        cvo.setOperateType(operateType);
        cvo.setIvrCode(ivrCode);
        cvo.setSalesId(salesId);
        cvo.setProcessUserName(processUserName);
        cvo.setOrderStatus(orderStatus);

        List<Map<String, Object>> dataList = facade.getAssignOrderInfo(cvo);

        System.out.println("==================================================");
        System.out.println(JsonUtil.toJson(dataList));
        System.out.println("==================================================");
    }

    @Test
    public void testCreateOrder() {
        for (int i = 0; i <= 2; i++) {
            String poolKey = IdentifierIdUtil.getUuid();
            String sourceOrderId = IdentifierIdUtil.getUuid();
            String teamGroup = "M";
            String sourceSysId = "FaxServer";
            String sourceProdTypeId = "1";
            String operateType = "1";
            String criticalFlag = "Y";
            String sourceCreateTime = "2017-03-07 16:30:32";
            String ospCreateTime = "2017-03-07 16:30:32";
            String msisdn = "0916000123";
            String custName = "王大明";
            String imgIdApid = IdentifierIdUtil.getUuid();
            String partentOrderId = null;
            String counts = "1";
            String expectProcessTime = "2017-03-07 16:30:32";
            String expectCompleteTime = "2017-03-20 16:30:32";
            String custSpecifyDate = "2017-03-25 16:30:32";
            String custId = "999888666";
            String salesId = "65533";
            String ivrCode = "65533";
            String promotionId = "ZIR000N-N0N4";
            String imgStorePath = null;
            String imgStoreServer = null;
            String cid = IdentifierIdUtil.getUuid();
            String processUserId = "65533";
            String processUserName = "周湯豪";
            String orderTypeId = "OSPL4003";
            String orderStatus = "010";
            String processResult = null;
            String processReason = null;
            String flowId = "FL0001";
            String commment = null;
            String isNoticeSales = "N";
            String createDate = null;
            String createUser = "65533";
            String updateDate = null;
            String updateUser = "65533";

            OrderInfoVO vo = new OrderInfoVO();

            vo.setPoolKey(poolKey);
            vo.setSourceOrderId(sourceOrderId);
            vo.setTeamGroup(teamGroup);
            vo.setSourceSysId(sourceSysId);
            vo.setSourceProdTypeId(sourceProdTypeId);
            vo.setOperateType(operateType);
            vo.setCriticalFlag(criticalFlag);
            vo.setSourceCreateTime(sourceCreateTime);
            vo.setOspCreateTime(ospCreateTime);
            vo.setMsisdn(msisdn);
            vo.setCustName(custName);
            vo.setImgIdApid(imgIdApid);
            vo.setPartentOrderId(partentOrderId);
            vo.setCounts(counts);
            vo.setExpectProcessTime(expectProcessTime);
            vo.setExpectCompleteTime(expectCompleteTime);
            vo.setCustSpecifyDate(custSpecifyDate);
            vo.setCustId(custId);
            vo.setSalesId(salesId);
            vo.setIvrCode(ivrCode);
            vo.setPromotionId(promotionId);
            vo.setImgStorePath(imgStorePath);
            vo.setImgStoreServer(imgStoreServer);
            vo.setCid(cid);
            vo.setProcessUserId(processUserId);
            vo.setProcessUserName(processUserName);
            vo.setOrderTypeId(orderTypeId);
            vo.setOrderStatus(orderStatus);
            vo.setProcessResult(processResult);
            vo.setProcessReason(processReason);
            vo.setFlowId(flowId);
            vo.setCommment(commment);
            vo.setIsNoticeSales(isNoticeSales);
            vo.setCreateUser(createUser);
            vo.setCreateDateTxt(createDate);
            vo.setUpdateUser(updateUser);
            vo.setUpdateDateTxt(updateDate);

            facade.createOrder(vo, "content");
        }
    }

    @Test
    public void testExcuteOrderAssign() {
        String orderMId = "OSP-20170222-171130-0fc0fa";
        String msisdn = "0901222333";
        String processUserId = "62233";
        String processUserName = "蔡依林";
    	String processResult = "";
    	String problemReason = "";
        String updateDate = "2017/03/25";
        String updateUser = "62233";
        String createUser = "51203";

        OrderAssignVO vo = new OrderAssignVO();
        vo.setOrderMId(orderMId);
        vo.setMsisdn(msisdn);
        vo.setProcessUserId(processUserId);
        vo.setProcessUserName(processUserName);
        vo.setProblemReason(problemReason);
        vo.setProcessResult(processResult);
        vo.setUpdateDateTxt(updateDate);
        vo.setUpdateUser(updateUser);
        vo.setCreateUser(createUser);

        List<OrderAssignVO> dataList = new ArrayList<>();

        for (int i = 1; i <= 1; i++) {
            dataList.add(vo);
        }

        facade.excuteOrderAssign(dataList);
    }

    @Test
    public void testGetPersonalTodoOrderInfo() {
        String orderMId = ""; // OSP主要進件單號
        String orderTypeId = ""; // 案件類型代號
        String sourceProdTypeId = ""; // 進件來源產品代號
        String msisdn = ""; // 門號
        String custName = ""; // 客戶名稱
        String sourceOrderId = ""; // 來源系統單號
        String processUserId = "65196"; // 處理人員編號

        TodoOrderCVO cvo = new TodoOrderCVO();
        cvo.setOrderMId(orderMId);
        cvo.setOrderTypeId(orderTypeId);
        cvo.setSourceProdTypeId(sourceProdTypeId);
        cvo.setMsisdn(msisdn);
        cvo.setCustName(custName);
        cvo.setSourceOrderId(sourceOrderId);
        cvo.setProcessUserId(processUserId);

        TodoOrderInfoVO vo = facade.getPersonalTodoOrderInfo(cvo);

        System.out.println("==================================================");
        System.out.println(JsonUtil.toJson(vo));
        System.out.println("==================================================");
    }
    
    @Test
    public void testGetDeptTodoOrderInfo() {
    	String userId = "65196";
        String orderMId = ""; // OSP主要進件單號
        String orderTypeId = ""; // 案件類型代號
        String sourceProdTypeId = ""; // 進件來源產品代號
        String msisdn = ""; // 門號
        String custName = ""; // 客戶名稱
        String sourceOrderId = ""; // 來源系統單號

        TodoOrderCVO cvo = new TodoOrderCVO();
        cvo.setOrderMId(orderMId);
        cvo.setOrderTypeId(orderTypeId);
        cvo.setSourceProdTypeId(sourceProdTypeId);
        cvo.setMsisdn(msisdn);
        cvo.setCustName(custName);
        cvo.setSourceOrderId(sourceOrderId);

        TodoOrderInfoVO vo = facade.getDeptTodoOrderInfo(cvo, userId);

        System.out.println("==================================================");
        System.out.println(JsonUtil.toJson(vo));
        System.out.println("==================================================");
    }

    @Test
    public void testGetOrderSimpleInfoByUserId() {
        String userId = "65533";

        List<Map<String, Object>> dataList = facade.getOrderSimpleInfoByUserId(userId);

        System.out.println("==================================================");
        System.out.println(JsonUtil.toJson(dataList));
        System.out.println("==================================================");
    }

    @Test
    public void testCreateRecordContent() {
        String orderMId = "OSP-20170321-181412-c6bd20";
        String createUser = "65533";

        RecordContentVO vo1 = new RecordContentVO();
        vo1.setContentId("CONT0025A");
        vo1.setItemId("AUTH001");
        vo1.setItemName("超過證號限制");
        vo1.setItemValue("Y");
        vo1.setSortSequence(1);
        vo1.setRemark("test");
        vo1.setCreateUser(createUser);

        RecordContentVO vo2 = new RecordContentVO();
        vo2.setContentId("CONT0025A");
        vo2.setItemId("AUTH002");
        vo2.setItemName("超過總門號數");
        vo2.setItemValue("N");
        vo2.setSortSequence(2);
        vo2.setRemark("test");
        vo2.setCreateUser(createUser);

        List<RecordContentVO> recordList = new ArrayList<>();

        recordList.add(vo1);
        recordList.add(vo2);

        facade.createRecordContent(orderMId, recordList);
    }

    @Test
    public void testInvalidOrder() {
    	OrderProcessVO vo = new OrderProcessVO();
    	vo.setOrderMId("OSP-20170413-100000-ts0001");
    	vo.setUserId("65196");
    	vo.setComment("測試");
    	
        facade.invalidOrder(vo);
    }

    @Test
    public void testTemporarySave() {
    	OrderProcessVO vo = new OrderProcessVO();
    	vo.setOrderMId("OSP-20170505-120902-e6a8f4");
    	vo.setUserId("65196");
    	
        facade.temporarySave(vo);
    }

    @Test
    public void testTemporaryLeave() {
    	OrderProcessVO vo = new OrderProcessVO();
    	vo.setOrderMId("OSP-20170413-100000-ts0081");
    	vo.setUserId("65196");
    	vo.setMsgContent("測試顯示暫離訊息");
    	
        facade.temporaryLeave(vo);
    }

    @Test
    public void testGetOrderMessage() {
    	String msg = facade.getOrderMessage("OSP-20170413-100000-ts0081");

        System.out.println("==================================================");
        System.out.println(msg);
        System.out.println("==================================================");
    }

    @Test
    public void testGetNotifyOtherSalesOrderList() {
    	NotifyOtherSalesVO vo = new NotifyOtherSalesVO();
    	vo.setMsisdn("0911123456");
    	vo.setOrderTypeId("OSPL4003");
    	vo.setProcessUserId("65196");
    	List<NotifyOtherSalesVO> list = facade.getNotifyOtherSalesOrderList(vo);

        System.out.println("==================================================");
        System.out.println(list);
        System.out.println("==================================================");
    }

    @Test
    public void testGetDuplication() {
    	List<NotifyOtherSalesVO> list = facade.getDuplication();

        System.out.println("==================================================");
        System.out.println(list);
        System.out.println("==================================================");
    }

    @Test
    public void testSetNotifyOtherSales() {
    	NotifyOtherSalesVO vo = new NotifyOtherSalesVO();
    	vo.setComboValue("");
    	vo.setComboContent("");
    	vo.setMsisdn("");
    	vo.setCreateUser("65196");
    	vo.setUpdateUser("65196");
    	
    	boolean result = facade.setNotifyOtherSales(vo);

        System.out.println("==================================================");
        System.out.println(result);
        System.out.println("==================================================");
    }
    
    @Test
    public void testQueryOrderDetailSpv() {
    	String orderMid = "OSP-20170413-100000-ts0027";
    	
    	List<OrderDetailSpvVO> dataLs = facade.getOrderDetailSpv(orderMid);
    	
    	if(!CollectionUtils.isEmpty(dataLs)) {
    		System.out.println("==================================================");
    		System.out.println("Result Count : " + dataLs.size());
    		
    		for(OrderDetailSpvVO data : dataLs) {
    			System.out.println("OderDId : " + data.getOrderDId());
    			System.out.println("opId : " + data.getOpId());
    			System.out.println("CancelReason : " + data.getCancelReason());
    		}
    		System.out.println("==================================================");
    	}
    }
    
    @Test
    public void testGetOrderImageSetting() {
    	String orderMId = "OSP-20170413-100000-ts0129";
    	String userId = "65533";
    	String ntAccount = "";
    	
    	OrderImageSettingVO orderImgVO = facade.getOrderImageSetting(orderMId, userId, ntAccount);
    	
    	System.out.println("==================================================");
    	System.out.println("SourceSysId : " + orderImgVO.getSourceSysId());
    	System.out.println("LinkType : " + orderImgVO.getLink());
    	System.out.println("Link : " + orderImgVO.getLink());
    	System.out.println("Browser : " + orderImgVO.getBrowser());
    	System.out.println("==================================================");
    }
    
    @Test
    public void testGetDispatchNotify() {
    	String userId = "65196";
    	
    	boolean isNotify = facade.getDispatchNotify(userId);
    	
    	System.out.println("==================================================");
    	System.out.println("isNotify = " + isNotify);
    	System.out.println("==================================================");
    	
  	}

}
