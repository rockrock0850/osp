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

package com.fet.crm.osp.kernel.mware.server.wrapper.soap;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.apache.commons.collections.MapUtils;

import com.fet.crm.osp.common.code.kernel.ReturnCode;
import com.fet.crm.osp.common.vo.kernel.input.record.BasicRecordVO;
import com.fet.crm.osp.common.vo.kernel.output.BooleanOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.OrderLoadOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.OrderQueryOutputVO;
import com.fet.crm.osp.common.vo.kernel.output.OrderUpdateOutputVO;
import com.fet.crm.osp.common.vo.kernel.process.ResultHeader;
import com.fet.crm.osp.common.vo.kernel.result.OrderLoadRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.OrderQueryRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.OrderUpdateRtnVO;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.core.vo.orderinfo.OrderDetailSpvVO;
import com.fet.crm.osp.kernel.core.vo.orderinfo.OrderMainMetadataVO;
import com.fet.crm.osp.kernel.mware.server.delegate.TicketPoolMainDelegate;
import com.fet.crm.osp.kernel.mware.server.vo.process.ExContext;
import com.fet.crm.osp.kernel.mware.server.vo.process.resp.RespBody;
import com.fet.crm.osp.kernel.mware.server.vo.process.resp.RespHeader;

/**
 * 面向工單池[ORDER_MAIN_METADATA]維護 主要 Webservice. <br>
 * 
 * @author VJChou, RichardHuang
 */
@WebService(
		serviceName = "TicketPoolMainService", 
		name = "TicketPoolMainService", 
		targetNamespace = "ticketpoolmainservice.soap.wrapper.server.mware.kernel.osp.crm.fet.com")
@SOAPBinding(style = Style.RPC)
public class TicketPoolMainSOAPWS extends AbstractBasicSOAPWS {

	/**
	 * 類型: 資料異動. <br>
	 * 將各進件系統抛轉進來的工單再次處理，並寫入TicketPool[ORDER_MAIN_METADATA]. <br>
	 * 
	 * @param basicRecordVO
	 *            基本記錄物件
	 * @param orderMetadataVO
	 *            工單封裝物件
	 * @return OrderLoadOutputVO
	 */
	@WebMethod(operationName = "loadOrder2TicketPoolFromMiddle", exclude = false)
	public OrderLoadOutputVO loadOrder2TicketPoolFromMiddle(
			@WebParam(name = "BasicRecord", header = true) BasicRecordVO basicRecordVO,
			@WebParam(name = "orderMetadataVO") OrderMainMetadataVO orderMetadataVO) {
	    // Request param
		reqBody.setOrderMainMetadata(orderMetadataVO);
		
		setExContext(basicRecordVO);

		ExContext rsContext = TicketPoolMainDelegate.loadOrder2TicketPoolFromMiddle(exContext);

		return getOrderLoadResult(rsContext);
	}

	/**
	 * 類型: 資料異動. <br>
	 * 將SPV抛轉進來的工單明細，寫入TicketPool[ORDER_DETAIL_SPV_MIDDLE]. <br>
	 * 
	 * @param basicRecordVO
	 *            基本記錄物件
	 * @param orderDetailSpvVO
	 *            SPV工單明細封裝物件
	 * @return BooleanOutputVO
	 */
	@WebMethod(operationName = "loadOrderFromSPV", exclude = false)
	public BooleanOutputVO loadOrderFromSPV(
			@WebParam(name = "BasicRecord", header = true) BasicRecordVO basicRecordVO,
			@WebParam(name = "orderDetailSpvVO") OrderDetailSpvVO orderDetailSpvVO) {
		// Request param
		reqBody.setOrderDetailSpv(orderDetailSpvVO);
		
		setExContext(basicRecordVO);

		ExContext rsContext = TicketPoolMainDelegate.loadOrderDetail2TicketPoolFromSPV(exContext);

		return getBooleanProcessResult(rsContext);
	}

	/**
	 * 類型: 查詢 -1. <br>
	 * 根據[來源系統代碼][來源系統原始單號(子母單仍是不同的單號)]，從工單池中取回指定工單資訊(包含狀態). <br>
	 * 
	 * @param basicRecordVO
	 *            基本記錄物件
	 * @param sourceSysId
	 *            來源系統代碼, ex: AIMS
	 * @param sourceOrderId
	 *            來源系統原始單號
	 * @return OrderQueryOutputVO
	 */
	@WebMethod(operationName = "queryOrderFromTicketPoolByIds", exclude = false)
	public OrderQueryOutputVO queryOrderFromTicketPoolByIds(
			@WebParam(name = "BasicRecord", header = true) BasicRecordVO basicRecordVO,
			@WebParam(name = "sourceSysId") String sourceSysId,
			@WebParam(name = "sourceOrderId") String sourceOrderId) {
		// Request param
		reqBody.setSourceSysId(sourceSysId);
		reqBody.setSourceOrderId(sourceOrderId);

		setExContext(basicRecordVO);

		ExContext rsContext = TicketPoolMainDelegate.queryOrderFromTicketPoolByIds(exContext);

		return getOrderQueryResult(rsContext);
	}

	/**
	 * 類型: 查詢 -2. (add at 2017-04-10.) <br>
	 * 根據[PoolKey]，從工單池中取回指定工單資訊(包含狀態). <br>
	 * 
	 * @param basicRecordVO
	 *            基本記錄物件
	 * @param poolKey
	 *            Ticket Pool產生的識別欄位
	 * @return OrderQueryOutputVO
	 */
	@WebMethod(operationName = "queryOrderFromTicketPoolByPoolKey", exclude = false)
	public OrderQueryOutputVO queryOrderFromTicketPoolByPoolKey(
			@WebParam(name = "BasicRecord", header = true) BasicRecordVO basicRecordVO,
			@WebParam(name = "poolKey") String poolKey) {
		// Request param
		reqBody.setPoolKey(poolKey);

		setExContext(basicRecordVO);

		ExContext rsContext = TicketPoolMainDelegate.queryOrderFromTicketPoolByPoolKey(exContext);

		return getOrderQueryResult(rsContext);
	}

	/**
	 * 類型: 資料異動. <br>
	 * 更新工單資訊(狀態/處理人員)至TicketPool[ORDER_MAIN_METADATA]. <br>
	 * 
	 * @param basicRecordVO
	 *            基本記錄物件
	 * @param poolKey
	 *            工單池鍵值
	 * @param status
	 *            工單狀態
	 * @param processUser
	 *            處理人員
	 * @return OrderUpdateOutputVO
	 */
	@WebMethod(operationName = "updateOrderStatus2TicketPool", exclude = false)
	public OrderUpdateOutputVO updateOrderStatus2TicketPool(
			@WebParam(name = "BasicRecord", header = true) BasicRecordVO basicRecordVO,
			@WebParam(name = "poolKey") String poolKey, 
			@WebParam(name = "status") String status,
			@WebParam(name = "processUser") String processUser) {
		// Request param
		reqBody.setPoolKey(poolKey);
		reqBody.setStatus(status);
		reqBody.setProcessUser(processUser);

		setExContext(basicRecordVO);

		ExContext rsContext = TicketPoolMainDelegate.updateOrderStatus2TicketPool(exContext);

		return getOrderUpdateResult(rsContext);
	}

	/**
	 * 類型: 資料異動. <br>
	 * 來源系統AIMS欄位異動[預計作業時間/客戶指定生效日]，更新至[ORDER_MAIN_METADATA]. <br>
	 * OSP狀態處於「未結案」之前才允許更新. <br>
	 * 
	 * @param basicRecordVO
	 *            基本記錄物件
	 * @param sourceOrderId
	 *            來源系統AIMS原始單號
	 * @param expectProcessTime
	 *            預計作業處理時間
	 * @param custSpecifyDate
	 *            客戶指定生效日
	 * @return BooleanOutputVO
	 */
	@WebMethod(operationName = "updateOrderInfo2TicketPoolFromAIMS", exclude = false)
	public BooleanOutputVO updateOrderInfo2TicketPoolFromAIMS(
			@WebParam(name = "BasicRecord", header = true) BasicRecordVO basicRecordVO,
			@WebParam(name = "sourceOrderId") String sourceOrderId,
			@WebParam(name = "expectProcessTime") Date expectProcessTime,
			@WebParam(name = "custSpecifyDate") Date custSpecifyDate) {
		// Request param
		reqBody.setSourceOrderId(sourceOrderId);
		reqBody.setExpectProcessTime(expectProcessTime);
		reqBody.setCustSpecifyDate(custSpecifyDate);

		setExContext(basicRecordVO);

		ExContext rsContext = TicketPoolMainDelegate.updateOrderInfo2TicketPoolFromAIMS(exContext);

		return getBooleanProcessResult(rsContext);
	}

	// ========== 以下為工具程式 ==========

	/**
	 * 工單載入執行結果 處理結果於此統一處理回傳訊息. <br>
	 * 
	 * @param rsContext
	 * @return OrderOutputVO
	 */
	protected OrderLoadOutputVO getOrderLoadResult(ExContext rsContext) {
		// 準備回傳
		OrderLoadOutputVO outputVO = new OrderLoadOutputVO();

		RespHeader respHeader = rsContext.getRespHeader();
		ResultHeader resultHeader = respHeader.getResultHeader();

		if ((ReturnCode.SUCCESS.getCode()).equals(resultHeader.getReturnCode())) {
			RespBody respBody = rsContext.getRespBody();

			OrderLoadRtnVO result = (OrderLoadRtnVO) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);

			outputVO.setResultBody(result);
		}

		outputVO.setResultHeader(resultHeader);

		return outputVO;
	}

	/**
	 * 工單查詢執行結果 處理結果於此統一處理回傳訊息. <br>
	 * 
	 * @param rsContext
	 * @return OrderOutputVO
	 */
	protected OrderQueryOutputVO getOrderQueryResult(ExContext rsContext) {
		// 準備回傳
		OrderQueryOutputVO outputVO = new OrderQueryOutputVO();

		RespHeader respHeader = rsContext.getRespHeader();
		ResultHeader resultHeader = respHeader.getResultHeader();

		if ((ReturnCode.SUCCESS.getCode()).equals(resultHeader.getReturnCode())) {
			RespBody respBody = rsContext.getRespBody();

			OrderQueryRtnVO result = (OrderQueryRtnVO) MapUtils.getObject(respBody.getData(), Constant.PROCESS_RESULT);

			outputVO.setResultBody(result);
		}

		outputVO.setResultHeader(resultHeader);

		return outputVO;
	}

	/**
	 * 工單更新執行結果封裝 處理結果於此統一處理回傳訊息. <br>
	 * 
	 * @param rsContext
	 * @return OrderOutputVO
	 */
	protected OrderUpdateOutputVO getOrderUpdateResult(ExContext rsContext) {
		// 準備回傳
		OrderUpdateOutputVO outputVO = new OrderUpdateOutputVO();

		RespHeader respHeader = rsContext.getRespHeader();
		ResultHeader resultHeader = respHeader.getResultHeader();

		if ((ReturnCode.SUCCESS.getCode()).equals(resultHeader.getReturnCode())) {
			RespBody respBody = rsContext.getRespBody();

			OrderUpdateRtnVO result = (OrderUpdateRtnVO) MapUtils.getObject(respBody.getData(),
					Constant.PROCESS_RESULT);

			outputVO.setResultBody(result);
		}

		outputVO.setResultHeader(resultHeader);

		return outputVO;
	}

}
