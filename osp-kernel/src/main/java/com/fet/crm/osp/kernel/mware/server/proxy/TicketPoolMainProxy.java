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

package com.fet.crm.osp.kernel.mware.server.proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.fet.crm.osp.common.code.kernel.ReturnCode;
import com.fet.crm.osp.common.vo.kernel.result.OrderLoadRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.OrderQueryRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.OrderUpdateRtnVO;
import com.fet.crm.osp.kernel.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.kernel.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.kernel.core.exception.DataDuplicateKeyException;
import com.fet.crm.osp.kernel.core.service.osp.IOSPPoolMainService;
import com.fet.crm.osp.kernel.core.service.pool.ITicketPoolMainService;
import com.fet.crm.osp.kernel.core.vo.orderinfo.OrderDetailSpvVO;
import com.fet.crm.osp.kernel.core.vo.orderinfo.OrderMainMetadataVO;
import com.fet.crm.osp.kernel.mware.client.ticketpool.ITicketPoolServiceClient;
import com.fet.crm.osp.kernel.mware.exception.ESBAPIException;
import com.fet.crm.osp.kernel.mware.exception.ESBConnectionException;
import com.fet.crm.osp.kernel.mware.server.vo.process.ExContext;
import com.fet.crm.osp.kernel.mware.server.vo.process.req.ReqBody;

/**
 * 面向工單池[ORDER_MAIN_METADATA]維護 服務代理窗口. <br>
 * 作用類似以往開發時的「Facade」對象，用來將服務統籌/服務縫合/資料加工/資料處理. <br>
 * 放在這個位置，在於部分服務的方式為轉介其他Webservices，故不適合在core內部進行呼叫. <br>
 * 
 * @author VJChou, Richard
 */
@Service
public class TicketPoolMainProxy extends AbstractProxy {

	@Autowired
	private ITicketPoolMainService ticketPoolMainService;
	@Autowired
	private IOSPPoolMainService ospPoolMainService;
	@Autowired
	private ITicketPoolServiceClient ticketPoolServiceClient;
	
	@Autowired
    @Qualifier("ospJdbcDAO")
    private JdbcDAO ospJdbcDAO;

	/**
	 * 類型: 資料異動. <br>
	 * 將各進件系統抛轉進來的工單再次處理，並寫入TicketPool[ORDER_MAIN_METADATA]. <br>
	 * 
	 * @param exContext
	 * @return ExContext
	 */
	public ExContext loadOrder2TicketPoolFromMiddle(ExContext exContext) {
		// 取出需要往後傳的參數
		ReqBody reqBody = exContext.getReqBody();
		OrderMainMetadataVO orderMetadataVO = reqBody.getOrderMainMetadata();
		
		try {
		    // 驗證輸入參數 : orderMetadataVO 不允許 null
	        Assert.notNull(orderMetadataVO, "OrderMainMetadata is required; it must not be null");
	        
			OrderMainMetadataVO result = ticketPoolMainService.loadOrder2TicketPoolFromMiddle(orderMetadataVO);
			String poolKey = result.getPoolKey();
			
			OrderLoadRtnVO returnVO = new OrderLoadRtnVO();
			returnVO.setPoolKey(poolKey);
			
			setSuccessResult(returnVO);

		} catch (IllegalArgumentException ex) {
		    setFailResult(ReturnCode.PARAM_ERROR, ex);
		    
		} catch (DataDuplicateKeyException ex) {
			setFailResult(ReturnCode.DATA_DUPLICATE_KEY_ERROR, ex);
			
		} catch (Exception ex) {
			setFailResult(ReturnCode.ERROR, ex);
		}

		return getExContext();
	}

	/**
	 * 類型: 資料異動. <br>
	 * 將SPV抛轉進來的工單明細，寫入TicketPool[ORDER_DETAIL_SPV_MIDDLE]. <br>
	 * 
	 * @param exContext
	 * @return ExContext
	 */
	public ExContext loadOrderDetail2TicketPoolFromSPV(ExContext exContext) {
		// 取出需要往後傳的參數
		ReqBody reqBody = exContext.getReqBody();

		OrderDetailSpvVO orderDetailSpvVO = reqBody.getOrderDetailSpv();

		try {
			boolean result = ticketPoolMainService.loadOrderDetail2TicketPoolFromSPV(orderDetailSpvVO);
			
			setSuccessResult(result);

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			setFailResult(ReturnCode.ERROR, ex);
		}

		return getExContext();
	}

	/**
	 * 類型: 查詢 -1. <br>
	 * 根據[來源系統代碼][來源系統原始單號(子母單仍是不同的單號)]，從工單池中取回指定工單資訊(包含狀態). <br>
	 * 
	 * @param exContext
	 * @return ExContext
	 */
	public ExContext queryOrderFromTicketPoolByIds(ExContext exContext) {
		// 取出需要往後傳的參數
		ReqBody reqBody = exContext.getReqBody();

		String sourceSysId = reqBody.getSourceSysId();
		String sourceOrderId = reqBody.getSourceOrderId();

		try {
			OrderMainMetadataVO result = ticketPoolMainService.queryOrderFromTicketPoolByIds(sourceSysId, sourceOrderId);

			if (result != null) {
			    OrderQueryRtnVO returnVO = new OrderQueryRtnVO();
	            BeanUtils.copyProperties(result, returnVO);
	            
	            setSuccessResult(returnVO);
	            
			} else {
			    setNoDataResult();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			setFailResult(ReturnCode.ERROR, ex);
		}

		return getExContext();
	}

	/**
	 * 類型: 查詢 -2. <br>
	 * 根據[PoolKey]，從工單池中取回指定工單資訊(包含狀態). <br>
	 * 
	 * @param exContext
	 * @return ExContext
	 */
	public ExContext queryOrderFromTicketPoolByPoolKey(ExContext exContext) {
		// 取出需要往後傳的參數
		ReqBody reqBody = exContext.getReqBody();

		String poolKey = reqBody.getPoolKey();

		try {
			OrderMainMetadataVO result = ticketPoolMainService.queryOrderFromTicketPoolByPoolKey(poolKey);
			
			if (result != null) {
			    OrderQueryRtnVO returnVO = new OrderQueryRtnVO();
	            BeanUtils.copyProperties(result, returnVO);
	            
	            setSuccessResult(returnVO);
                
            } else {
                setNoDataResult();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            setFailResult(ReturnCode.ERROR, ex);
        }

        return getExContext();
	}

	/**
	 * 類型: 資料異動. <br>
	 * 更新工單資訊(狀態/處理人員)至TicketPool[ORDER_MAIN_METADATA]. <br>
	 * 
	 * @param exContext
	 * @return ExContext
	 */
	public ExContext updateOrderStatus2TicketPool(ExContext exContext) {
		// 取出需要往後傳的參數
		ReqBody reqBody = exContext.getReqBody();

		String poolKey = reqBody.getPoolKey();
		String status = reqBody.getStatus();
		String processUser = reqBody.getProcessUser();

		try {
			OrderMainMetadataVO result = ticketPoolMainService.updateOrderStatus2TicketPool(poolKey, status, processUser);

			if (result != null) {
			    String sourceSysId = result.getSourceSysId();
	            String sourceOrderId = result.getSourceOrderId();
	            String processUserEmpNo = result.getProcessUser();
	            String ntAccount = convertEmpNoToNtAccount(processUserEmpNo);

	            OrderUpdateRtnVO returnVO = new OrderUpdateRtnVO();

	            returnVO.setPoolKey(poolKey);
	            returnVO.setSourceSysId(sourceSysId);
	            returnVO.setSourceOrderId(sourceOrderId);
	            returnVO.setNtAccount(ntAccount);

	            setSuccessResult(returnVO);
	            
			} else {
			    setNoDataResult();
			}

		} catch (Exception ex) {
			setFailResult(ReturnCode.ERROR, ex);
		}

		return getExContext();
	}
	
	/**
	 * 類型: 資料異動. <br>
	 * 更新 OSP 工單資訊(狀態/處理人員)至TicketPool[ORDER_MAIN_METADATA]. <br>
	 * 
	 * @param exContext
	 * @return ExContext
	 */
	public ExContext updateOrderStatus2TicketPoolFromOSP(ExContext exContext) {
		// 取出需要往後傳的參數
		ReqBody reqBody = exContext.getReqBody();

		String userId = reqBody.getUserId();
		String poolKey = reqBody.getPoolKey();
		String status = reqBody.getStatus();
		String processUser = reqBody.getProcessUser();
		
		try {
			String ntAccount = convertEmpNoToNtAccount(processUser);
			
			Assert.hasText(userId, "userId must not be null, empty, or blank");
			Assert.hasText(poolKey, "poolKey must not be null, empty, or blank");
			Assert.hasText(status, "status must not be null, empty, or blank");
			Assert.hasText(processUser, "processUser must not be null, empty, or blank");
			
			if (StringUtils.isNotBlank(ntAccount)) {
				ticketPoolServiceClient.updateTicketStatus(userId, poolKey, status, processUser, ntAccount);

				OrderUpdateRtnVO returnVO = new OrderUpdateRtnVO();
	            returnVO.setPoolKey(poolKey);
	            returnVO.setNtAccount(ntAccount);

	            setSuccessResult(returnVO);
	            
			} else {
			    setNoDataResult("The NT Account of process user [" + processUser + "] not exist.");
			}
			
		} catch (IllegalArgumentException ex) {
		    setFailResult(ReturnCode.PARAM_ERROR, ex);
		    
		} catch (ESBConnectionException ex) {
		    setFailResult(ReturnCode.ESB_CONNECTION_ERROR, ex);
		    
		} catch (ESBAPIException ex) {
		    setFailResult(ReturnCode.ESB_API_ERROR, ex);
		    
		} catch (Exception ex) {
			setFailResult(ReturnCode.ERROR, ex);
		}

		return getExContext();
	}

	/**
	 * 類型: 資料異動. <br>
	 * 來源系統AIMS欄位異動[預計作業時間/客戶指定生效日]，更新至[ORDER_MAIN_METADATA]. <br>
	 * OSP狀態處於「未結案」之前才允許更新. <br>
	 * 
	 * @param exContext
	 * @return ExContext
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ExContext updateOrderInfo2TicketPoolFromAIMS(ExContext exContext) {
		// 取出需要往後傳的參數
		ReqBody reqBody = exContext.getReqBody();

		String sourceOrderId = reqBody.getSourceOrderId();
		Date expectProcessTime = reqBody.getExpectProcessTime();
		Date custSpecifyDate = reqBody.getCustSpecifyDate();
		
		try {
            Assert.hasText(sourceOrderId, "sourceOrderId must not be null, empty, or blank");
            Assert.notNull(expectProcessTime, "expectProcessTime must not be null");
            Assert.notNull(custSpecifyDate, "custSpecifyDate must not be null");
            
            boolean success = ticketPoolMainService.updateOrderInfo2TicketPoolFromAIMS(sourceOrderId, expectProcessTime, custSpecifyDate);

			if (success) {
				ospPoolMainService.updateOrderInfo2OSPFromAIMS(sourceOrderId, expectProcessTime, custSpecifyDate);
			    setSuccessResult(success);
	            
			} else {
			    setNoDataResult();
			}

		} catch (IllegalArgumentException ex) {
			setFailResult(ReturnCode.PARAM_ERROR, ex);
			
		} catch (Exception ex) {
			setFailResult(ReturnCode.ERROR, ex);
		}

		return getExContext();
	}
	
	/**
     * 類型: 資料異動. <br>
     * 將OSP系統人工建檔抛轉進來的工單再次處理，並寫入TicketPool[ORDER_MAIN_METADATA]. <br>
     * 
     * @param exContext
     * @return ExContext
     */
    public ExContext syncOrder2TicketPoolFromOSP(ExContext exContext) {
        // 取出需要往後傳的參數
        ReqBody reqBody = exContext.getReqBody();
        OrderMainMetadataVO orderMetadataVO = reqBody.getOrderMainMetadata();
        
        try {
            // 驗證輸入參數 : orderMetadataVO 不允許 null
            Assert.notNull(orderMetadataVO, "OrderMainMetadata is required; it must not be null");
            
            String poolKey = orderMetadataVO.getPoolKey();
            String sourceSysId = orderMetadataVO.getSourceSysId();
            String sourceOrderId = orderMetadataVO.getSourceOrderId();
            String msisdn = orderMetadataVO.getMsisdn();
            
            Assert.hasText(poolKey, "poolKey must not be null, empty, or blank");
            Assert.hasText(sourceSysId, "sourceSysId must not be null, empty, or blank");
            Assert.hasText(sourceOrderId, "sourceOrderId must not be null, empty, or blank");
            Assert.hasText(msisdn, "msisdn must not be null, empty, or blank");
            
            OrderMainMetadataVO result = ticketPoolMainService.syncOrder2TicketPoolFromOSP(orderMetadataVO);
            
            // 驗證回傳結果 : 新增資料成功後查詢結果不允許為空白或 null
            Assert.state(result != null && StringUtils.isNotBlank(result.getPoolKey()));

            String poolKeyResult = result.getPoolKey();
            
            OrderLoadRtnVO returnVO = new OrderLoadRtnVO();
            returnVO.setPoolKey(poolKeyResult);
            
            setSuccessResult(returnVO);

        } catch (IllegalArgumentException ex) {
            setFailResult(ReturnCode.PARAM_ERROR, ex);
            
        } catch (DataDuplicateKeyException ex) {
            setFailResult(ReturnCode.DATA_DUPLICATE_KEY_ERROR, ex);
            
        } catch (Exception ex) {
            setFailResult(ReturnCode.ERROR, ex);
        }

        return getExContext();
    }
	
	// ========== 以下為工具程式 ==========

	/**
	 * 將使用者empno轉換成NT帳號. <br>
	 * 
	 * @return String
	 */
	private String convertEmpNoToNtAccount(String empNo) {
	    String sqlText = ResourceFileUtil.SQL_ORGANIZATION.getResource("GET_NT_ACCOUNT_BY_EMPNO");
	    
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("empNo", empNo);
	    
        List<Map<String, Object>> dataList = ospJdbcDAO.queryForList(sqlText, params);
        
        if (CollectionUtils.isNotEmpty(dataList)) {
            String ntAccount = MapUtils.getString(dataList.get(0), "ALIASNAME");
            
            return ntAccount; 
        }
        
		return null;
	}

}
