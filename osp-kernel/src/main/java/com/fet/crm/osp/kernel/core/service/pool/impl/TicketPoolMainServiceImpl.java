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

package com.fet.crm.osp.kernel.core.service.pool.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.common.util.IdentifierIdUtil;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.kernel.core.db.warehouse.OrderDetailSpvMiddleWarehouse;
import com.fet.crm.osp.kernel.core.db.warehouse.OrderMainMetadataWarehouse;
import com.fet.crm.osp.kernel.core.exception.DataDuplicateKeyException;
import com.fet.crm.osp.kernel.core.pojo.OrderDetailSpvPOJO;
import com.fet.crm.osp.kernel.core.pojo.OrderMainMetadataPOJO;
import com.fet.crm.osp.kernel.core.service.pool.ITicketPoolMainService;
import com.fet.crm.osp.kernel.core.vo.orderinfo.OrderDetailSpvVO;
import com.fet.crm.osp.kernel.core.vo.orderinfo.OrderMainMetadataVO;

/**
 * 面向工單池[ORDER_MAIN_METADATA]維護 主要服務實作. <br>
 * 
 * @author VJChou, RichardHuang
 */
@Service
public class TicketPoolMainServiceImpl implements ITicketPoolMainService {
    
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("poolJdbcDAO")
	private JdbcDAO poolJdbcDAO;

	@Autowired
	private OrderMainMetadataWarehouse orderMainMetadataWarehouse;

	@Autowired
	private OrderDetailSpvMiddleWarehouse orderDetailSpvMiddleWarehouse;

	@Override
	public OrderMainMetadataVO loadOrder2TicketPoolFromMiddle(OrderMainMetadataVO orderMetadataVO) {
		String poolKey = null; // 進入Ticket Pool的識別碼
		String sysUserId = "BUS_SERVICE";
		Date executeDt = new Date();
		String defaultStatus = "000";

		if (orderMetadataVO != null) {
		    poolKey = IdentifierIdUtil.getUuid();
		    String sourceSysId = orderMetadataVO.getSourceSysId();
		    String sourceOrderId = orderMetadataVO.getSourceOrderId();
		    
		    List<OrderMainMetadataPOJO> orderMainMetadataPOJOList = orderMainMetadataWarehouse.findBySourceSysIdAndSourceOrderId(sourceSysId, sourceOrderId);
		    
		    if (CollectionUtils.isNotEmpty(orderMainMetadataPOJOList)) {
		        throw new DataDuplicateKeyException("Duplicate key value violates unique constraint with [ sourceOrderId, sourceSysId ]");
		    }
		    
			String teamGroup = orderMetadataVO.getTeamGroup();
			
			if (StringUtils.isBlank(teamGroup)) {
			    teamGroup = Constant.DEFAULT_TEAM_GROUP;
			}
			
			String sourceProdTypeId = orderMetadataVO.getSourceProdTypeId();
			String operateType = orderMetadataVO.getOperateType();
			String criticalFlag = orderMetadataVO.getCriticalFlag();
			Date sourceCreateTime = orderMetadataVO.getSourceCreateTime();
			String msisdn = orderMetadataVO.getMsisdn();
			String custName = orderMetadataVO.getCustName();
			String imgIdApid = orderMetadataVO.getImgIdApid();
			String partentOrderId = orderMetadataVO.getPartentOrderId();
			BigDecimal counts = orderMetadataVO.getCounts();
			Date expectProcessTime = orderMetadataVO.getExpectProcessTime();
			Date custSpecifyDate = orderMetadataVO.getCustSpecifyDate();
			String custId = orderMetadataVO.getCustId();
			String salesId = orderMetadataVO.getSalesId();
			String ivrCode = orderMetadataVO.getIvrCode();
			String promotionId = orderMetadataVO.getPromotionId();
			String imgStorePath = orderMetadataVO.getImgStorePath();
			String imgStoreServer = orderMetadataVO.getImgStoreServer();
			String cid = orderMetadataVO.getCid();

			OrderMainMetadataPOJO pojo = new OrderMainMetadataPOJO();

			pojo.setPoolKey(poolKey);
			pojo.setSourceOrderId(sourceOrderId);
			pojo.setTeamGroup(teamGroup);
			pojo.setSourceSysId(sourceSysId);
			pojo.setSourceProdTypeId(sourceProdTypeId);
			pojo.setOperateType(operateType);
			pojo.setCriticalFlag(criticalFlag);
			pojo.setSourceCreateTime(sourceCreateTime);
			pojo.setMsisdn(msisdn);
			pojo.setCustName(custName);
			pojo.setImgIdApid(imgIdApid);
			pojo.setPartentOrderId(partentOrderId);
			pojo.setCounts(counts);
			pojo.setExpectProcessTime(expectProcessTime);
			pojo.setCustSpecifyDate(custSpecifyDate);
			pojo.setCustId(custId);
			pojo.setSalesId(salesId);
			pojo.setIvrCode(ivrCode);
			pojo.setPromotionId(promotionId);
			pojo.setImgStorePath(imgStorePath);
			pojo.setImgStoreServer(imgStoreServer);
			pojo.setCid(cid);
			pojo.setStatus(defaultStatus);
			pojo.setProcessUser(null); // 預設 NULL
			pojo.setSysSyncBatchNo(null); // 預設 NULL
			
			// 以下為系統欄位
			pojo.setCreateDate(executeDt);
			pojo.setCreateUser(sysUserId);
			pojo.setUpdateDate(executeDt);
			pojo.setUpdateUser(sysUserId);

			orderMainMetadataWarehouse.save(pojo);
		}

		// 執行成功後，回傳poolKey
		OrderMainMetadataVO orderMainMetadata = queryOrderFromTicketPoolBypoolKey(poolKey);

		return orderMainMetadata;
	}

	@Override
	public boolean loadOrderDetail2TicketPoolFromSPV(OrderDetailSpvVO orderDetailSpvVO) {
	    String sysUserId = "BUS_SERVICE";
        Date executeDt = new Date();
        
	    if (orderDetailSpvVO != null) {
	        String orderDId = IdentifierIdUtil.getUuid();
	        String poolKey = orderDetailSpvVO.getPoolKey();
	        String sourceOrderId = orderDetailSpvVO.getSourceOrderId();
	        String accountId = orderDetailSpvVO.getAccountId();
	        String msisdn = orderDetailSpvVO.getMsisdn();
	        String cycleCode = orderDetailSpvVO.getCycleCode();
	        String cancelReason = orderDetailSpvVO.getCancelReason();
	        String opid = orderDetailSpvVO.getOpid();
	        String channel = orderDetailSpvVO.getChannel();
	        String serviceName = orderDetailSpvVO.getServiceName();
	        String offerId = orderDetailSpvVO.getOfferId();
	        String offerName = orderDetailSpvVO.getOfferName();
	        String taskStatus = orderDetailSpvVO.getTaskStatus();
	        Date statusTime = orderDetailSpvVO.getStatusTime();
	        Date requestEffectiveDate = orderDetailSpvVO.getRequestEffectiveDate();
	        Date requestExpirationDate = orderDetailSpvVO.getRequestExpirationDate();
	        Date taskCreationDate = orderDetailSpvVO.getTaskCreationDate();
	        String taskSeqNo = orderDetailSpvVO.getTaskSeqNo();
	        String taskId = orderDetailSpvVO.getTaskId();
	        
	        OrderDetailSpvPOJO pojo = new OrderDetailSpvPOJO();
	        
	        pojo.setOrderDId(orderDId);
	        pojo.setPoolKey(poolKey);
	        pojo.setSourceOrderId(sourceOrderId);
	        pojo.setAccountId(accountId);
	        pojo.setMsisdn(msisdn);
	        pojo.setCycleCode(cycleCode);
	        pojo.setCancelReason(cancelReason);
	        pojo.setOpid(opid);
	        pojo.setChannel(channel);
	        pojo.setServiceName(serviceName);
	        pojo.setOfferId(offerId);
	        pojo.setOfferName(offerName);
	        pojo.setTaskStatus(taskStatus);
	        pojo.setStatusTime(statusTime);
	        pojo.setRequestEffectiveDate(requestEffectiveDate);
	        pojo.setRequestExpirationDate(requestExpirationDate);
	        pojo.setTaskCreationDate(taskCreationDate);
	        pojo.setTaskSeqNo(taskSeqNo);
	        pojo.setTaskId(taskId);
	        pojo.setCreateDate(executeDt);
	        pojo.setCreateUser(sysUserId);
	        pojo.setUpdateDate(executeDt);
	        pojo.setUpdateUser(sysUserId);
	        
	        orderDetailSpvMiddleWarehouse.save(pojo);
	        
	        return true;
	    }
	    
		return false;
	}

	@Override
	public OrderMainMetadataVO queryOrderFromTicketPoolByIds(String sourceSysId, String sourceOrderId) {
		List<OrderMainMetadataPOJO> dataList = orderMainMetadataWarehouse.findBySourceSysIdAndSourceOrderId(sourceSysId, sourceOrderId);

		if (!CollectionUtils.isEmpty(dataList)) {
			OrderMainMetadataPOJO dataPojo = dataList.get(0);

			OrderMainMetadataVO orderMainMetadataVo = new OrderMainMetadataVO();
			BeanUtils.copyProperties(dataPojo, orderMainMetadataVo);

			return orderMainMetadataVo;
		}

		return null;
	}
	
	@Override
    public OrderMainMetadataVO queryOrderFromTicketPoolByPoolKey(String poolKey) {
        OrderMainMetadataVO orderMainMetadataVo = queryOrderFromTicketPoolBypoolKey(poolKey);

        return orderMainMetadataVo;
    }

	@Override
	public OrderMainMetadataVO updateOrderStatus2TicketPool(String poolKey, String status, String processUser) {
		logger.info("updateOrderStatus2TicketPool [Start]");
		
		boolean success = orderMainMetadataWarehouse.updateOrderStatus2TicketPool(poolKey, status, processUser);
		
		logger.info("TicketPoolMainServiceImpl.updateOrderStatus2TicketPool params = [ poolKey = " + poolKey 
		        + ", status = " + status + ", processUser = " + processUser + " ], success = " + success);
		
		if (success) {
		    // 每執行完資料異動後，將異動後的資料再次查回
	        OrderMainMetadataVO orderMainMetadataVo = queryOrderFromTicketPoolBypoolKey(poolKey);
	        
	        logger.info("updateOrderStatus2TicketPool [End]");
	        return orderMainMetadataVo;
		}

		logger.info("updateOrderStatus2TicketPool [End]");
		return null;
	}

	@Override
	public boolean updateOrderInfo2TicketPoolFromAIMS(String sourceOrderId, Date expectProcessTime, Date custSpecifyDate) {
	    return orderMainMetadataWarehouse.updateOrderInfo2TicketPoolFromAIMS(sourceOrderId, expectProcessTime, custSpecifyDate);
	}
	
    @Override
    public OrderMainMetadataVO syncOrder2TicketPoolFromOSP(OrderMainMetadataVO orderMetadataVO) {
        String poolKey = null; // 進入Ticket Pool的識別碼
        Date executeDt = new Date();

        if (orderMetadataVO != null) {
            poolKey = orderMetadataVO.getPoolKey();
            String sourceSysId = Constant.SYSTEM_ID;
            String sourceOrderId = orderMetadataVO.getSourceOrderId();
            
            List<OrderMainMetadataPOJO> orderMainMetadataPOJOList = orderMainMetadataWarehouse.findBySourceSysIdAndSourceOrderId(sourceSysId, sourceOrderId);
            
            if (CollectionUtils.isNotEmpty(orderMainMetadataPOJOList)) {
                throw new DataDuplicateKeyException("Duplicate key value violates unique constraint with [ sourceOrderId, sourceSysId ]");
            }
            
            String teamGroup = orderMetadataVO.getTeamGroup();
            String status = orderMetadataVO.getStatus();
            String userId = orderMetadataVO.getProcessUser();
            String sourceProdTypeId = orderMetadataVO.getSourceProdTypeId();
            String operateType = orderMetadataVO.getOperateType();
            String criticalFlag = orderMetadataVO.getCriticalFlag();
            Date sourceCreateTime = orderMetadataVO.getSourceCreateTime();
            String msisdn = orderMetadataVO.getMsisdn();
            String custName = orderMetadataVO.getCustName();
            String imgIdApid = orderMetadataVO.getImgIdApid();
            String partentOrderId = orderMetadataVO.getPartentOrderId();
            BigDecimal counts = orderMetadataVO.getCounts();
            Date expectProcessTime = orderMetadataVO.getExpectProcessTime();
            Date custSpecifyDate = orderMetadataVO.getCustSpecifyDate();
            String custId = orderMetadataVO.getCustId();
            String salesId = orderMetadataVO.getSalesId();
            String ivrCode = orderMetadataVO.getIvrCode();
            String promotionId = orderMetadataVO.getPromotionId();
            String imgStorePath = orderMetadataVO.getImgStorePath();
            String imgStoreServer = orderMetadataVO.getImgStoreServer();
            String cid = orderMetadataVO.getCid();

            OrderMainMetadataPOJO pojo = new OrderMainMetadataPOJO();

            pojo.setPoolKey(poolKey);
            pojo.setSourceOrderId(sourceOrderId);
            pojo.setTeamGroup(teamGroup);
            pojo.setSourceSysId(sourceSysId);
            pojo.setSourceProdTypeId(sourceProdTypeId);
            pojo.setOperateType(operateType);
            pojo.setCriticalFlag(criticalFlag);
            pojo.setSourceCreateTime(sourceCreateTime);
            pojo.setMsisdn(msisdn);
            pojo.setCustName(custName);
            pojo.setImgIdApid(imgIdApid);
            pojo.setPartentOrderId(partentOrderId);
            pojo.setCounts(counts);
            pojo.setExpectProcessTime(expectProcessTime);
            pojo.setCustSpecifyDate(custSpecifyDate);
            pojo.setCustId(custId);
            pojo.setSalesId(salesId);
            pojo.setIvrCode(ivrCode);
            pojo.setPromotionId(promotionId);
            pojo.setImgStorePath(imgStorePath);
            pojo.setImgStoreServer(imgStoreServer);
            pojo.setCid(cid);
            pojo.setStatus(status);
            pojo.setProcessUser(userId);
            pojo.setSysSyncBatchNo(null); // 預設 NULL
            
            // 以下為系統欄位
            pojo.setCreateDate(executeDt);
            pojo.setCreateUser(userId);
            pojo.setUpdateDate(executeDt);
            pojo.setUpdateUser(userId);

            orderMainMetadataWarehouse.save(pojo);
        }

        // 執行成功後，回傳poolKey
        OrderMainMetadataVO orderMainMetadata = queryOrderFromTicketPoolBypoolKey(poolKey);

        return orderMainMetadata;
    }
    
    // ======= 工具程式 =======

 	/*
 	 * 根據指定的Pool Key取回工單資料. <br>
 	 * 
 	 * @param poolKey
 	 * @return OrderMainMetadataVO
 	 */
 	private OrderMainMetadataVO queryOrderFromTicketPoolBypoolKey(String poolKey) {
 		OrderMainMetadataPOJO dataPojo = orderMainMetadataWarehouse.findByPoolKey(poolKey);
 		
 		if (dataPojo != null) {
 		    OrderMainMetadataVO orderMainMetadataVo = new OrderMainMetadataVO();
 	        BeanUtils.copyProperties(dataPojo, orderMainMetadataVo);
 	        return orderMainMetadataVo;
 		}
 		
 		return null;
 	}

}
