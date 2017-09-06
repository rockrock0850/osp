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

package com.fet.crm.osp.platform.core.service.orderinfo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.common.comparator.TodoSortComparator;
import com.fet.crm.osp.platform.core.common.util.DateUtil;
import com.fet.crm.osp.platform.core.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.condition.Condition;
import com.fet.crm.osp.platform.core.db.condition.api.Restrictions;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.db.warehouse.NotifyDetailWareHouse;
import com.fet.crm.osp.platform.core.db.warehouse.NotifyMainWareHouse;
import com.fet.crm.osp.platform.core.db.warehouse.OrderDetailSpvWareHouse;
import com.fet.crm.osp.platform.core.db.warehouse.OrderMainOspWarehouse;
import com.fet.crm.osp.platform.core.db.warehouse.OrderMessageWarehouse;
import com.fet.crm.osp.platform.core.db.warehouse.OrderOperateRecordsWarehouse;
import com.fet.crm.osp.platform.core.db.warehouse.RecordContentWarehouse;
import com.fet.crm.osp.platform.core.db.warehouse.SysOptionsComboWareHouse;
import com.fet.crm.osp.platform.core.pojo.BuzRecordContentPOJO;
import com.fet.crm.osp.platform.core.pojo.NotifyDetailPOJO;
import com.fet.crm.osp.platform.core.pojo.NotifyMainPOJO;
import com.fet.crm.osp.platform.core.pojo.OrderDetailSpvPOJO;
import com.fet.crm.osp.platform.core.pojo.OrderMainOspPOJO;
import com.fet.crm.osp.platform.core.pojo.OrderMessagePOJO;
import com.fet.crm.osp.platform.core.pojo.OrderOperateRecordsPOJO;
import com.fet.crm.osp.platform.core.pojo.SysOptionsComboPOJO;
import com.fet.crm.osp.platform.core.service.orderinfo.IOrderManageService;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderAssignVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderDetailSpvVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderInfoVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderOperateRecordsVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderProcessVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.RecordContentVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.OrderInfoCVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.TodoOrderCVO;
import com.fet.crm.osp.platform.webapp.vo.NotifyOtherSalesVO;

/**
 * [訂單管理] 服務介面
 * 
 * @author AndrewLee, Adam Yeh, AllenChen
 */
@Service
public class OrderManageServiceImpl implements IOrderManageService {

    @Autowired
    private JdbcDAO jdbcDAO;
    @Autowired
    private OrderMainOspWarehouse orderMainOspWarehouse;
    @Autowired
    private OrderOperateRecordsWarehouse orderOperateRecordsWarehouse;
    @Autowired
    private OrderMessageWarehouse orderMessageWarehouse;
    @Autowired
    private RecordContentWarehouse recordContentWarehouse;
	@Autowired
	private SysOptionsComboWareHouse sysOptionsComboWareHouse;
	@Autowired
	private NotifyMainWareHouse notifyMainWareHouse;
	@Autowired
	private NotifyDetailWareHouse notifyDetailWareHouse;
	@Autowired
	private OrderDetailSpvWareHouse orderDetailSpvWarehouse;
	
    
    @Override
    public List<Map<String, Object>> queryAssignOrderInfo(OrderInfoCVO orderInfoCVO) {
    	if (orderInfoCVO != null) {
    		String sourceCreateTimeBegin 	= orderInfoCVO.getSourceCreateTimeBegin();
            String sourceCreateTimeEnd 		= orderInfoCVO.getSourceCreateTimeEnd();
            String expectProcessTimeBegin   = orderInfoCVO.getExpectProcessTimeBegin();
            String expectProcessTimeEnd		= orderInfoCVO.getExpectProcessTimeEnd();
            String orderTypeId			    = orderInfoCVO.getOrderTypeId();
            String imgIdApid 				= orderInfoCVO.getImgIdApid();
            String sourceSysId 				= orderInfoCVO.getSourceSysId();
            String msisdn 				    = orderInfoCVO.getMsisdn();
            String custId 					= orderInfoCVO.getCustId();
            String custName					= orderInfoCVO.getCustName();
            String sourceProdTypeId		    = orderInfoCVO.getSourceProdTypeId();
            String operateType 				= orderInfoCVO.getOperateType();
            String ivrCode 					= orderInfoCVO.getIvrCode();
            String salesId					= orderInfoCVO.getSalesId();
            String processUserName 			= orderInfoCVO.getProcessUserName();
            String orderStatus 				= orderInfoCVO.getOrderStatus();
            
            String sqltext = ResourceFileUtil.SQL_ORDER.getResource("QUERY_ORDER_INFO_BY_CONDITION");
            Condition condition = new Condition();

            if (StringUtils.isNotBlank(sourceCreateTimeBegin) && StringUtils.isNotBlank(sourceCreateTimeEnd)) {
                StringBuffer sourceCreateTimeSB = new StringBuffer();
                
                sourceCreateTimeSB.append("T1.SOURCE_CREATE_TIME BETWEEN TO_DATE('" )
                    .append(sourceCreateTimeBegin)
                    .append("','yyyy/MM/dd hh24:mi:ss') AND TO_DATE('" )
                    .append(sourceCreateTimeEnd)
                    .append("','yyyy/MM/dd hh24:mi:ss')");
                
                condition.and(Restrictions.sqlRestrictions(sourceCreateTimeSB.toString()));
            } else {
            	if (StringUtils.isNotBlank(sourceCreateTimeBegin)) {
            		condition.and(Restrictions.sqlRestrictions("T1.SOURCE_CREATE_TIME > TO_DATE('" + sourceCreateTimeBegin + "', 'yyyy/MM/dd hh24:mi:ss')"));
            	}
            	if (StringUtils.isNotBlank(sourceCreateTimeEnd)) {
            		condition.and(Restrictions.sqlRestrictions("T1.SOURCE_CREATE_TIME < TO_DATE('" + sourceCreateTimeEnd + "', 'yyyy/MM/dd hh24:mi:ss')"));
            	}
            }

            if (StringUtils.isNotBlank(expectProcessTimeBegin) && StringUtils.isNotBlank(expectProcessTimeEnd)) {
                StringBuffer expectProcessTimeSB = new StringBuffer();
                
                expectProcessTimeSB.append("T1.EXPECT_PROCESS_TIME BETWEEN TO_DATE('" )
                    .append(expectProcessTimeBegin)
                    .append("','yyyy/MM/dd hh24:mi:ss') AND TO_DATE('" )
                    .append(expectProcessTimeEnd)
                    .append("','yyyy/MM/dd hh24:mi:ss')");
                
                condition.and(Restrictions.sqlRestrictions(expectProcessTimeSB.toString()));
            } else {
            	if (StringUtils.isNotBlank(expectProcessTimeBegin)) {
            		condition.and(Restrictions.sqlRestrictions("T1.EXPECT_PROCESS_TIME > TO_DATE('" + expectProcessTimeBegin + "', 'yyyy/MM/dd hh24:mi:ss')"));
            	}
            	if (StringUtils.isNotBlank(expectProcessTimeEnd)) {
            		condition.and(Restrictions.sqlRestrictions("T1.EXPECT_PROCESS_TIME < TO_DATE('" + expectProcessTimeEnd + "', 'yyyy/MM/dd hh24:mi:ss')"));
            	}
            }

            if (StringUtils.isNotBlank(operateType)) {
                condition.and(Restrictions.eq("T1.OPERATE_TYPE", operateType));
            }

            if (StringUtils.isNotBlank(orderStatus)) {
                condition.and(Restrictions.eq("T1.ORDER_STATUS", orderStatus));
            }

            if (StringUtils.isNotBlank(orderTypeId)) {
                condition.and(Restrictions.eq("T1.ORDER_TYPE_ID", orderTypeId));
            }

            if (StringUtils.isNotBlank(imgIdApid)) {
                condition.and(Restrictions.eq("T1.IMG_ID_APID", imgIdApid));
            }

            if (StringUtils.isNotBlank(sourceSysId)) {
                condition.and(Restrictions.eq("T1.SOURCE_SYS_ID", sourceSysId));
            }

            if (StringUtils.isNotBlank(msisdn)) {
                condition.and(Restrictions.eq("T1.MSISDN", msisdn));
            }

            if (StringUtils.isNotBlank(custId)) {
                condition.and(Restrictions.eq("T1.CUST_ID", custId));
            }
            
            if (StringUtils.isNotBlank(custName)) {
                condition.and(Restrictions.eq("T1.CUST_NAME", custName));
            }

            if (StringUtils.isNotBlank(sourceProdTypeId)) {
                condition.and(Restrictions.eq("T1.SOURCE_PROD_TYPE_ID", sourceProdTypeId));
            }

            if (StringUtils.isNotBlank(ivrCode)) {
                condition.and(Restrictions.eq("T1.IVR_CODE", ivrCode));
            }

            if (StringUtils.isNotBlank(salesId)) {
                condition.and(Restrictions.eq("T1.SALES_ID", salesId));
            }

            if (StringUtils.isNotBlank(processUserName)) {
                condition.and(Restrictions.eq("T1.PROCESS_USER_NAME", processUserName));
            }
            
            condition.and(Restrictions.sqlRestrictions("T1.ORDER_STATUS NOT IN ('020', '070', '080')"));
    		
            sqltext = condition.getCompleteSQL(sqltext);
    	    Map<String, Object> params = condition.getParams();

    	    List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqltext, params);
    	    
    	    if (dataList != null && !dataList.isEmpty()) {
    	    	return dataList;
    	    }
    	}
    	
    	return Collections.emptyList();
    }
    
    @Override
	public OrderInfoVO queryOrderInfoByOrderMId(String orderMId) {
    	if (StringUtils.isNotBlank(orderMId)) {
    		OrderMainOspPOJO pojo = orderMainOspWarehouse.findByOrderMId(orderMId);
    		
    		if (pojo != null) {
        		String poolKey				= pojo.getPoolKey();
        		String sourceOrderId 		= pojo.getSourceOrderId();
        		String teamGroup			= pojo.getTeamGroup();
        		String sourceSysId			= pojo.getSourceSysId();
        		String sourceProdTypeId		= pojo.getSourceProdTypeId();
        		String sourceProdTypeName 	= pojo.getSourceProdTypeName();
        		String operateType			= pojo.getOperateType();
        		String criticalFlag			= pojo.getCriticalFlag();
        		Date sourceCreateTime 		= pojo.getSourceCreateTime();
        		Date ospCreateTime 			= pojo.getOspCreateTime();
        		String msisdn 			    = pojo.getMsisdn();
        		String custName 			= pojo.getCustName();
        		String imgIdApid 			= pojo.getImgIdApid();
        		String partentOrderId		= pojo.getPartentOrderId();
        		BigDecimal counts 			= pojo.getCounts();
        		Date expectProcessTime 		= pojo.getExpectProcessTime();
        		Date expectCompleteTime 	= pojo.getExpectCompleteTime();
        		Date custSpecifyDate 		= pojo.getCustSpecifyDate();
        		String custId 				= pojo.getCustId();
        		String salesId 				= pojo.getSalesId();
        		String ivrCode 				= pojo.getIvrCode();
        		String promotionId 			= pojo.getPromotionId();
        		String imgStorePath 		= pojo.getImgStorePath();
        		String imgStoreServer 		= pojo.getImgStoreServer();
        		String cid 					= pojo.getCid();
        		String processUserId 		= pojo.getProcessUserId();
        		String processUserName 		= pojo.getProcessUserName();
        		String orderTypeId 			= pojo.getOrderTypeId();
        		String orderTypeName 		= pojo.getOrderTypeName();
        		String orderStatus 			= pojo.getOrderStatus();
        		String processResult 		= pojo.getProcessResult();
        		String processReason 		= pojo.getProcessReason();
        		String flowId 				= pojo.getFlowId();
        		String commment 			= pojo.getCommment();
        		String isNoticeSales 		= pojo.getIsNoticeSales();
        		Date createDate 			= new Date();
        		String createUser 			= pojo.getCreateUser();
        		Date updateDate 			= new Date();
        		String updateUser 			= pojo.getUpdateUser();
        		String custType				= pojo.getCustType();
        		String corpPicTaxid 		= pojo.getCorpPicTaxid();
        		
        		OrderInfoVO infoVO = new OrderInfoVO();
        		
        		infoVO.setOrderMId(orderMId); 
        		infoVO.setPoolKey(poolKey); 
        		infoVO.setSourceOrderId(sourceOrderId); 
        		infoVO.setTeamGroup(teamGroup); 
        		infoVO.setSourceSysId(sourceSysId); 
        		infoVO.setSourceProdTypeId(sourceProdTypeId); 
        		infoVO.setSourceProdTypeName(sourceProdTypeName); 
        		infoVO.setOperateType(operateType); 
        		infoVO.setCriticalFlag(criticalFlag); 
        		infoVO.setSourceCreateTime(DateUtil.fromDate(sourceCreateTime, DateUtil.DATE_TIME_PATTERN)); 
        		infoVO.setOspCreateTime(DateUtil.fromDate(ospCreateTime, DateUtil.DATE_TIME_PATTERN)); 
        		infoVO.setMsisdn(msisdn);
        		infoVO.setCustName(custName); 
        		infoVO.setImgIdApid(imgIdApid); 
        		infoVO.setPartentOrderId(partentOrderId); 
        		infoVO.setCounts(String.valueOf(counts)); 
        		infoVO.setExpectProcessTime(DateUtil.fromDate(expectProcessTime, DateUtil.DATE_TIME_PATTERN)); 
        		infoVO.setExpectCompleteTime(DateUtil.fromDate(expectCompleteTime, DateUtil.DATE_TIME_PATTERN)); 
        		infoVO.setCustSpecifyDate(DateUtil.fromDate(custSpecifyDate, DateUtil.DATE_TIME_PATTERN)); 
        		infoVO.setCustId(custId); 
        		infoVO.setSalesId(salesId); 
        		infoVO.setIvrCode(ivrCode); 
        		infoVO.setPromotionId(promotionId); 
        		infoVO.setImgStorePath(imgStorePath); 
        		infoVO.setImgStoreServer(imgStoreServer); 
        		infoVO.setCid(cid); 
        		infoVO.setProcessUserId(processUserId); 
        		infoVO.setProcessUserName(processUserName); 
        		infoVO.setOrderTypeId(orderTypeId); 
        		infoVO.setOrderTypeName(orderTypeName); 
        		infoVO.setOrderStatus(orderStatus); 
        		infoVO.setProcessResult(processResult); 
        		infoVO.setProcessReason(processReason); 
        		infoVO.setFlowId(flowId); 
        		infoVO.setCommment(commment); 
        		infoVO.setIsNoticeSales(isNoticeSales);
        		infoVO.setCreateDate(createDate);
        		infoVO.setCreateUser(createUser);
        		infoVO.setUpdateDate(updateDate);
        		infoVO.setUpdateUser(updateUser);
        		infoVO.setCustType(custType);
        		infoVO.setCorpPicTaxid(corpPicTaxid);
        		
        		return infoVO;
    		}
    	}
    	
		return new OrderInfoVO();
	}
    
	@Override
	public boolean createOrder(OrderInfoVO orderInfoVO) {
    	if (orderInfoVO != null) {
    		String orderMId = orderInfoVO.getOrderMId();
    		if (StringUtils.isBlank(orderMId)) {
    	        orderMId = IdentifierIdUtil.getOspOrderMId();
    		}
    		String poolKey				= orderInfoVO.getPoolKey();
    		String sourceOrderId 		= orderInfoVO.getSourceOrderId();
    		String teamGroup			= orderInfoVO.getTeamGroup();
    		String sourceSysId			= orderInfoVO.getSourceSysId();
    		String sourceProdTypeId		= orderInfoVO.getSourceProdTypeId();
    		String sourceProdTypeName 	= orderInfoVO.getSourceProdTypeName();
    		String operateType			= orderInfoVO.getOperateType();
    		String criticalFlag			= orderInfoVO.getCriticalFlag();
    		Date sourceCreateTime 		= DateUtil.toDateTime(orderInfoVO.getSourceCreateTime());
    		Date ospCreateTime 			= DateUtil.toDateTime(orderInfoVO.getOspCreateTime());
    		String msisdn 			    = orderInfoVO.getMsisdn();
    		String custName 			= orderInfoVO.getCustName();
    		String imgIdApid 			= orderInfoVO.getImgIdApid();
    		String partentOrderId		= orderInfoVO.getPartentOrderId();
    		BigDecimal counts 			= BigDecimal.valueOf(Long.valueOf(orderInfoVO.getCounts()));
    		Date expectProcessTime 		= DateUtil.toDateTime(orderInfoVO.getExpectProcessTime());
    		Date expectCompleteTime 	= DateUtil.toDateTime(orderInfoVO.getExpectCompleteTime());
    		Date custSpecifyDate 		= DateUtil.toDateTime(orderInfoVO.getCustSpecifyDate());
    		String custId 				= orderInfoVO.getCustId();
    		String salesId 				= orderInfoVO.getSalesId();
    		String ivrCode 				= orderInfoVO.getIvrCode();
    		String promotionId 			= orderInfoVO.getPromotionId();
    		String imgStorePath 		= orderInfoVO.getImgStorePath();
    		String imgStoreServer 		= orderInfoVO.getImgStoreServer();
    		String cid 					= orderInfoVO.getCid();
    		String processUserId 		= orderInfoVO.getProcessUserId();
    		String processUserName 		= orderInfoVO.getProcessUserName();
    		String orderTypeId 			= orderInfoVO.getOrderTypeId();
    		String orderTypeName 		= orderInfoVO.getOrderTypeName();
    		String orderStatus 			= orderInfoVO.getOrderStatus();
    		String processResult 		= orderInfoVO.getProcessResult();
    		String processReason 		= orderInfoVO.getProcessReason();
    		String flowId 				= orderInfoVO.getFlowId();
    		String commment 			= orderInfoVO.getCommment();
    		String isNoticeSales 		= orderInfoVO.getIsNoticeSales();
    		String custType				= orderInfoVO.getCustType();
    		String corpPicTaxid			= orderInfoVO.getCorpPicTaxid();
    		Date createDate 			= new Date();
    		String createUser 			= orderInfoVO.getCreateUser();
    		Date updateDate 			= new Date();
    		String updateUser 			= orderInfoVO.getUpdateUser();
    		
    		OrderMainOspPOJO pojo = new OrderMainOspPOJO();
    		
    		pojo.setOrderMId( orderMId); 
    		pojo.setPoolKey( poolKey); 
    		pojo.setSourceOrderId( sourceOrderId); 
    		pojo.setTeamGroup( teamGroup); 
    		pojo.setSourceSysId( sourceSysId); 
    		pojo.setSourceProdTypeId( sourceProdTypeId); 
    		pojo.setSourceProdTypeName( sourceProdTypeName); 
    		pojo.setOperateType( operateType); 
    		pojo.setCriticalFlag( criticalFlag); 
    		pojo.setSourceCreateTime( sourceCreateTime); 
    		pojo.setOspCreateTime( ospCreateTime); 
    		pojo.setMsisdn(msisdn);
    		pojo.setCustName( custName); 
    		pojo.setImgIdApid( imgIdApid); 
    		pojo.setPartentOrderId( partentOrderId); 
    		pojo.setCounts( counts); 
    		pojo.setExpectProcessTime( expectProcessTime); 
    		pojo.setExpectCompleteTime( expectCompleteTime); 
    		pojo.setCustSpecifyDate( custSpecifyDate); 
    		pojo.setCustId( custId); 
    		pojo.setSalesId( salesId); 
    		pojo.setIvrCode( ivrCode); 
    		pojo.setPromotionId( promotionId); 
    		pojo.setImgStorePath( imgStorePath); 
    		pojo.setImgStoreServer( imgStoreServer); 
    		pojo.setCid( cid); 
    		pojo.setProcessUserId( processUserId); 
    		pojo.setProcessUserName( processUserName); 
    		pojo.setOrderTypeId( orderTypeId); 
    		pojo.setOrderTypeName( orderTypeName); 
    		pojo.setOrderStatus( orderStatus); 
    		pojo.setProcessResult( processResult); 
    		pojo.setProcessReason( processReason); 
    		pojo.setFlowId( flowId); 
    		pojo.setCommment( commment); 
    		pojo.setIsNoticeSales( isNoticeSales);
    		pojo.setCustType(custType);
    		pojo.setCreateDate(createDate);
    		pojo.setCreateUser(createUser);
    		pojo.setUpdateDate(updateDate);
    		pojo.setUpdateUser(updateUser);
    		pojo.setCorpPicTaxid(corpPicTaxid);
    	
    		orderMainOspWarehouse.save(pojo);
    	}
    	
    	return true;
	}

	@Override
	public boolean batchCreateOrder(List<OrderInfoVO> voList) {
		if (CollectionUtils.isEmpty(voList)) {
			return false;
		}
		
		List<OrderMainOspPOJO> pojoList = new ArrayList<>();
		for (OrderInfoVO orderInfoVO : voList) {
    		String orderMId = orderInfoVO.getOrderMId();
    		if (StringUtils.isBlank(orderMId)) {
    	        orderMId = IdentifierIdUtil.getOspOrderMId();
    		}
    		String poolKey				= orderInfoVO.getPoolKey();
    		String sourceOrderId 		= orderInfoVO.getSourceOrderId();
    		String teamGroup			= orderInfoVO.getTeamGroup();
    		String sourceSysId			= orderInfoVO.getSourceSysId();
    		String sourceProdTypeId		= orderInfoVO.getSourceProdTypeId();
    		String sourceProdTypeName 	= orderInfoVO.getSourceProdTypeName();
    		String operateType			= orderInfoVO.getOperateType();
    		String criticalFlag			= orderInfoVO.getCriticalFlag();
    		Date sourceCreateTime 		= DateUtil.toDateTime(orderInfoVO.getSourceCreateTime());
    		Date ospCreateTime 			= DateUtil.toDateTime(orderInfoVO.getOspCreateTime());
    		String msisdn 			    = orderInfoVO.getMsisdn();
    		String custName 			= orderInfoVO.getCustName();
    		String imgIdApid 			= orderInfoVO.getImgIdApid();
    		String partentOrderId		= orderInfoVO.getPartentOrderId();
    		BigDecimal counts 			= BigDecimal.valueOf(Long.valueOf(orderInfoVO.getCounts()));
    		Date expectProcessTime 		= DateUtil.toDateTime(orderInfoVO.getExpectProcessTime());
    		Date expectCompleteTime 	= DateUtil.toDateTime(orderInfoVO.getExpectCompleteTime());
    		Date custSpecifyDate 		= DateUtil.toDateTime(orderInfoVO.getCustSpecifyDate());
    		String custId 				= orderInfoVO.getCustId();
    		String salesId 				= orderInfoVO.getSalesId();
    		String ivrCode 				= orderInfoVO.getIvrCode();
    		String promotionId 			= orderInfoVO.getPromotionId();
    		String imgStorePath 		= orderInfoVO.getImgStorePath();
    		String imgStoreServer 		= orderInfoVO.getImgStoreServer();
    		String cid 					= orderInfoVO.getCid();
    		String processUserId 		= orderInfoVO.getProcessUserId();
    		String processUserName 		= orderInfoVO.getProcessUserName();
    		String orderTypeId 			= orderInfoVO.getOrderTypeId();
    		String orderTypeName 		= orderInfoVO.getOrderTypeName();
    		String orderStatus 			= orderInfoVO.getOrderStatus();
    		String processResult 		= orderInfoVO.getProcessResult();
    		String processReason 		= orderInfoVO.getProcessReason();
    		String flowId 				= orderInfoVO.getFlowId();
    		String commment 			= orderInfoVO.getCommment();
    		String isNoticeSales 		= orderInfoVO.getIsNoticeSales();
    		String custType				= orderInfoVO.getCustType();
    		String corpPicTaxid			= orderInfoVO.getCorpPicTaxid();
    		Date createDate 			= new Date();
    		String createUser 			= orderInfoVO.getCreateUser();
    		Date updateDate 			= new Date();
    		String updateUser 			= orderInfoVO.getUpdateUser();
    		
    		OrderMainOspPOJO pojo = new OrderMainOspPOJO();
    		pojo.setOrderMId( orderMId); 
    		pojo.setPoolKey( poolKey); 
    		pojo.setSourceOrderId( sourceOrderId); 
    		pojo.setTeamGroup( teamGroup); 
    		pojo.setSourceSysId( sourceSysId); 
    		pojo.setSourceProdTypeId( sourceProdTypeId); 
    		pojo.setSourceProdTypeName( sourceProdTypeName); 
    		pojo.setOperateType( operateType); 
    		pojo.setCriticalFlag( criticalFlag); 
    		pojo.setSourceCreateTime( sourceCreateTime); 
    		pojo.setOspCreateTime( ospCreateTime); 
    		pojo.setMsisdn(msisdn);
    		pojo.setCustName( custName); 
    		pojo.setImgIdApid( imgIdApid); 
    		pojo.setPartentOrderId( partentOrderId); 
    		pojo.setCounts( counts); 
    		pojo.setExpectProcessTime( expectProcessTime); 
    		pojo.setExpectCompleteTime( expectCompleteTime); 
    		pojo.setCustSpecifyDate( custSpecifyDate); 
    		pojo.setCustId( custId); 
    		pojo.setSalesId( salesId); 
    		pojo.setIvrCode( ivrCode); 
    		pojo.setPromotionId( promotionId); 
    		pojo.setImgStorePath( imgStorePath); 
    		pojo.setImgStoreServer( imgStoreServer); 
    		pojo.setCid( cid); 
    		pojo.setProcessUserId( processUserId); 
    		pojo.setProcessUserName( processUserName); 
    		pojo.setOrderTypeId( orderTypeId); 
    		pojo.setOrderTypeName( orderTypeName); 
    		pojo.setOrderStatus( orderStatus); 
    		pojo.setProcessResult( processResult); 
    		pojo.setProcessReason( processReason); 
    		pojo.setFlowId( flowId); 
    		pojo.setCommment( commment); 
    		pojo.setIsNoticeSales( isNoticeSales);
    		pojo.setCustType(custType);
    		pojo.setCreateDate(createDate);
    		pojo.setCreateUser(createUser);
    		pojo.setUpdateDate(updateDate);
    		pojo.setUpdateUser(updateUser);
    		pojo.setCorpPicTaxid(corpPicTaxid);
    		
    		pojoList.add(pojo);
		}
		boolean result = orderMainOspWarehouse.batchSave(pojoList);
    	
    	return result;
	}

	@Override
	public OrderProcessVO updateOrderStatus(OrderProcessVO vo, String status) {
		String orderMId = vo.getOrderMId();
		String userId = vo.getUserId();
		String processUserId = vo.getProcessUserId();
		String processUserName = vo.getProcessUserName();
		boolean deptFeatrue = vo.isDeptFeatrue();
		
		String sqlText = "";
		if (deptFeatrue) {
			sqlText = ResourceFileUtil.SQL_ORDER.getResource("UPDATE_ORDER_STATUS_FOR_DEPT");
		} else {
			sqlText = ResourceFileUtil.SQL_ORDER.getResource("UPDATE_ORDER_STATUS");
		}
		
		Map<String, Object> params = new HashMap<>();
		params.put("ORDER_M_ID", orderMId);
		params.put("PROCESS_USER_ID", processUserId);
		params.put("PROCESS_USER_NAME", processUserName);
		params.put("ORDER_STATUS", status);
		params.put("UPDATE_USER", userId);
		params.put("UPDATE_DATE", new Date());
		
		jdbcDAO.update(sqlText, params);
		
		return vo;
	}
	
    @Override
    public boolean excuteOrderAssign(List<OrderAssignVO> assignList) {
        if (CollectionUtils.isNotEmpty(assignList)) {
            List<OrderMainOspPOJO> dataList = new ArrayList<>();

            for (OrderAssignVO assignVO : assignList) {
                String orderMId = assignVO.getOrderMId(); 				// OSP主要進件單號
                String processUserId = assignVO.getProcessUserId(); 	// 處理人員編號
                String processUserName = assignVO.getProcessUserName(); // 處理人員姓名
                String updateUser = assignVO.getUpdateUser(); 			// 指派人員編號

                OrderMainOspPOJO pojo = new OrderMainOspPOJO();
                
                pojo.setOrderMId(orderMId);
                pojo.setProcessUserId(processUserId);
                pojo.setProcessUserName(processUserName);
                pojo.setUpdateUser(updateUser);
                pojo.setUpdateDate(new Date());

                // 以orderMId查詢 ORDER_STATUS 若為000 改為010，其餘不變 by AllenChen at 2017-0602
                OrderMainOspPOJO queryOrderStatusVo = orderMainOspWarehouse.findByOrderMId(orderMId);
                String orderStatus = queryOrderStatusVo.getOrderStatus();
                
                if("000".equals(orderStatus)) {
                	pojo.setOrderStatus("010");
                }
                
                dataList.add(pojo);   
            }
            
            orderMainOspWarehouse.updateInBatch(dataList);
        }

        return true;
    }

	@Override
	public List<Map<String, Object>> queryPersonalTodoOrderInfo(TodoOrderCVO todoOrderCVO) {
		if (todoOrderCVO != null && StringUtils.isNotBlank(todoOrderCVO.getProcessUserId())) {
			String orderMId				= todoOrderCVO.getOrderMId(); 			// OSP主要進件單號
			String orderTypeId		 	= todoOrderCVO.getOrderTypeId(); 		// 案件類型代號
			String sourceProdTypeId 	= todoOrderCVO.getSourceProdTypeId(); 	// 進件來源產品代號
			String msisdn				= todoOrderCVO.getMsisdn();				// 門號
			String custName				= todoOrderCVO.getCustName(); 			// 客戶名稱
			String sourceOrderId		= todoOrderCVO.getSourceOrderId(); 		// 來源系統單號
			String processUserId		= todoOrderCVO.getProcessUserId();		// 處理人員編號
			
			Condition condition = new Condition();
			
			if (StringUtils.isNotBlank(orderMId)) {
				condition.and(Restrictions.eq("T1.ORDER_M_ID", orderMId));
			}
			if (StringUtils.isNotBlank(orderTypeId)) {
				condition.and(Restrictions.eq("T1.ORDER_TYPE_ID", orderTypeId));
			}
			if (StringUtils.isNotBlank(sourceProdTypeId)) {
				condition.and(Restrictions.eq("T1.SOURCE_PROD_TYPE_ID", sourceProdTypeId));
			}
			if (StringUtils.isNotBlank(msisdn)) {
				condition.and(Restrictions.eq("T1.MSISDN", msisdn));
			}
			if (StringUtils.isNotBlank(custName)) {
				condition.and(Restrictions.eq("T1.CUST_NAME", custName));
			}
			if (StringUtils.isNotBlank(sourceOrderId)) {
				condition.and(Restrictions.eq("T1.SOURCE_ORDER_ID", sourceOrderId));
			}
			if (StringUtils.isNotBlank(processUserId)) {
				condition.and(Restrictions.eq("T1.PROCESS_USER_ID", processUserId));
			}
			
			condition.and(Restrictions.sqlRestrictions("T1.ORDER_STATUS IN (  '010',  '020', '030',  '040',  '050', '060' )"));
			
			String sqltext = ResourceFileUtil.SQL_ORDER.getResource("QUERY_TODO_ORDER_INFO");
			
			sqltext = condition.getCompleteSQL(sqltext);
	    	Map<String, Object> params = condition.getParams();
	    	
	    	List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqltext, params);
	    	
	    	for(Map<String, Object> data : dataList) {
	    		Date cspDate = (Date) data.get("CUST_SPECIFY_DATE");
	    		String cspDateStr = DateUtil.fromDate(cspDate, "yyyy-MM-dd");
	    		
	    		data.put("CUST_SPECIFY_DATE", cspDateStr);
	    	}
	    	
	    	dataList = setNullValueToEmptyString(dataList);
	    	    
	    	if (dataList != null && !dataList.isEmpty()) {
	    		Collections.sort(dataList, new TodoSortComparator());
	    		
	    		return dataList;
	    	}
		}
		
		return Collections.emptyList();
	}

	private List<Map<String, Object>> setNullValueToEmptyString(List<Map<String, Object>> dataList) {
		for (Map<String, Object> map : dataList) {
			for (String key : map.keySet()) {
				Object o = map.get(key);
				
				if (o == null) {
					map.put(key, "");
				}
			}
		}
		
		return dataList;
	}


	@Override
	public List<Map<String, Object>> queryOrderSimpleInfoByUserId(String userId) {
		if (StringUtils.isNotBlank(userId)) {
			String sqltext = ResourceFileUtil.SQL_ORDER.getResource("QUERY_ORDER_SIMPLE_INFO_BY_USER_ID");
			
			Map<String, Object> params = new HashMap<>();
			params.put("PROCESS_USER_ID", userId);
			
			List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqltext, params);
			
			if (dataList != null && !dataList.isEmpty()) {
	    		return dataList;
	    	}
		}
		
		return Collections.emptyList();
	}
	
	@Override
	public int queryPersonalCriticalOrderCount(TodoOrderCVO todoOrderCVO) {
		if (todoOrderCVO != null && StringUtils.isNotBlank(todoOrderCVO.getProcessUserId())) {
			String orderMId				= todoOrderCVO.getOrderMId(); 			// OSP主要進件單號
			String orderTypeId		 	= todoOrderCVO.getOrderTypeId(); 		// 案件類型代號
			String sourceProdTypeId 	= todoOrderCVO.getSourceProdTypeId(); 	// 進件來源產品代號
			String msisdn				= todoOrderCVO.getMsisdn();				// 門號
			String custName				= todoOrderCVO.getCustName(); 			// 客戶名稱
			String sourceOrderId		= todoOrderCVO.getSourceOrderId(); 		// 來源系統單號
			String processUserId		= todoOrderCVO.getProcessUserId();		// 處理人員編號
			
			Condition condition = new Condition();
			
			if (StringUtils.isNotBlank(orderMId)) {
				condition.and(Restrictions.eq("ORDER_M_ID", orderMId));
			}
			if (StringUtils.isNotBlank(orderTypeId)) {
				condition.and(Restrictions.eq("ORDER_TYPE_ID", orderTypeId));
			}
			if (StringUtils.isNotBlank(sourceProdTypeId)) {
				condition.and(Restrictions.eq("SOURCE_PROD_TYPE_ID", sourceProdTypeId));
			}
			if (StringUtils.isNotBlank(msisdn)) {
				condition.and(Restrictions.eq("MSISDN", msisdn));
			}
			if (StringUtils.isNotBlank(custName)) {
				condition.and(Restrictions.eq("CUST_NAME", custName));
			}
			if (StringUtils.isNotBlank(sourceOrderId)) {
				condition.and(Restrictions.eq("SOURCE_ORDER_ID", sourceOrderId));
			}
			if (StringUtils.isNotBlank(processUserId)) {
				condition.and(Restrictions.eq("PROCESS_USER_ID", processUserId));
			}
			
			condition.and(Restrictions.sqlRestrictions("ORDER_STATUS IN (  '010',  '020', '030',  '040',  '050', '060' )"));
			
			String sqltext = ResourceFileUtil.SQL_ORDER.getResource("QUERY_CRITICAL_ORDER_COUNT");
			
			sqltext = condition.getCompleteSQL(sqltext);
			Map<String, Object> params = condition.getParams();
			
			List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqltext, params);
			
			if (CollectionUtils.isNotEmpty(dataList)) {
				BigDecimal count = (BigDecimal) dataList.get(0).get("COUNT");
				
		    	return count.intValue();
			}
		}
		
		return 0;
	}

	@Override
	public int queryPersonalOverdueOrderCount(TodoOrderCVO todoOrderCVO) {
		if (todoOrderCVO != null && StringUtils.isNotBlank(todoOrderCVO.getProcessUserId())) {
			String orderMId				= todoOrderCVO.getOrderMId(); 			// OSP主要進件單號
			String orderTypeId		 	= todoOrderCVO.getOrderTypeId(); 		// 案件類型代號
			String sourceProdTypeId 	= todoOrderCVO.getSourceProdTypeId(); 	// 進件來源產品代號
			String msisdn				= todoOrderCVO.getMsisdn();				// 門號
			String custName				= todoOrderCVO.getCustName(); 			// 客戶名稱
			String sourceOrderId		= todoOrderCVO.getSourceOrderId(); 		// 來源系統單號
			String processUserId		= todoOrderCVO.getProcessUserId();		// 處理人員編號
			
			Condition condition = new Condition();
			
			if (StringUtils.isNotBlank(orderMId)) {
				condition.and(Restrictions.eq("T1.ORDER_M_ID", orderMId));
			}
			if (StringUtils.isNotBlank(orderTypeId)) {
				condition.and(Restrictions.eq("T1.ORDER_TYPE_ID", orderTypeId));
			}
			if (StringUtils.isNotBlank(sourceProdTypeId)) {
				condition.and(Restrictions.eq("T1.SOURCE_PROD_TYPE_ID", sourceProdTypeId));
			}
			if (StringUtils.isNotBlank(msisdn)) {
				condition.and(Restrictions.eq("T1.MSISDN", msisdn));
			}
			if (StringUtils.isNotBlank(custName)) {
				condition.and(Restrictions.eq("T1.CUST_NAME", custName));
			}
			if (StringUtils.isNotBlank(sourceOrderId)) {
				condition.and(Restrictions.eq("T1.SOURCE_ORDER_ID", sourceOrderId));
			}
			if (StringUtils.isNotBlank(processUserId)) {
				condition.and(Restrictions.eq("T1.PROCESS_USER_ID", processUserId));
			}
			
			condition.and(Restrictions.sqlRestrictions("T1.ORDER_STATUS IN (  '010',  '020', '030',  '040',  '050', '060' )"));
			
			String sqltext = ResourceFileUtil.SQL_ORDER.getResource("QUERY_OVERDUE_ORDER_COUNT");
			
			sqltext = condition.getCompleteSQL(sqltext);
			Map<String, Object> params = condition.getParams();
			
			List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqltext, params);
			
			if (CollectionUtils.isNotEmpty(dataList)) {
				BigDecimal count = (BigDecimal) dataList.get(0).get("COUNT");
				
		    	return count.intValue();
			}
		}
		
		return 0;
	}

	@Override
	public boolean createOperateRecord(OrderOperateRecordsVO operateRecordsVO) {
		if (operateRecordsVO != null) {
			String orderOpId = IdentifierIdUtil.getUuid();    
			String orderMId = operateRecordsVO.getOrderMId();     
			String msisdn = operateRecordsVO.getMsisdn();       
			String actionType = operateRecordsVO.getActionType();   
			Date executeTime = new Date();    
			String processResult = operateRecordsVO.getProcessResult();
			String problemReason = operateRecordsVO.getProblemReason();
			Date createDate = new Date();  
			String createUser = operateRecordsVO.getCreateUser();   
			String actionContent = operateRecordsVO.getActionContent();
			
			OrderOperateRecordsPOJO pojo = new OrderOperateRecordsPOJO();
			pojo.setOrderOpId(orderOpId);
			pojo.setOrderMId(orderMId);
			pojo.setMsisdn(msisdn);
			pojo.setActionType(actionType);
			pojo.setExecuteTime(executeTime);
			pojo.setProcessResult(processResult);
			pojo.setProblemReason(problemReason);
			pojo.setCreateDate(createDate);
			pojo.setCreateUser(createUser);
			pojo.setActionContent(actionContent);
			
			orderOperateRecordsWarehouse.save(pojo);
		}
		
		return true;
	}
	
	@Override
	public boolean createOperateRecordInBatch(List<OrderOperateRecordsVO> recordList) {
		if (CollectionUtils.isNotEmpty(recordList)) {
			List<OrderOperateRecordsPOJO> dataList = new ArrayList<>();
			
			for (OrderOperateRecordsVO vo : recordList) {
				if (vo != null) {
					String orderOpId = IdentifierIdUtil.getUuid();    
					String orderMId = vo.getOrderMId();     
					String msisdn = vo.getMsisdn();       
					String actionType = vo.getActionType();   
					Date executeTime = new Date();    
					String processResult = vo.getProcessResult();
					String problemReason = vo.getProblemReason();
					Date createDate = new Date();  
					String createUser = vo.getCreateUser();   
					String actionContent = vo.getActionContent();
					
					OrderOperateRecordsPOJO pojo = new OrderOperateRecordsPOJO();
					pojo.setOrderOpId(orderOpId);
					pojo.setOrderMId(orderMId);
					pojo.setMsisdn(msisdn);
					pojo.setActionType(actionType);
					pojo.setExecuteTime(executeTime);
					pojo.setProcessResult(processResult);
					pojo.setProblemReason(problemReason);
					pojo.setCreateDate(createDate);
					pojo.setCreateUser(createUser);
					pojo.setActionContent(actionContent);
					
					dataList.add(pojo);
				}
			}
			
			orderOperateRecordsWarehouse.save(dataList);
		}
		
		return true;
	}
	
	@Override
	public boolean createRecordContent(String orderMId, List<RecordContentVO> recordList) {
		if (CollectionUtils.isNotEmpty(recordList)) {
			List<BuzRecordContentPOJO> dataList = new ArrayList<>();
			
			for (RecordContentVO vo : recordList) {
				if (vo != null) {
					String contentId = vo.getContentId();
					String itemId = vo.getItemId();   
					String itemName = vo.getItemName(); 
					String itemValue = vo.getItemValue();
					BigDecimal sortSequence = new BigDecimal(vo.getSortSequence());
					String remark = vo.getRemark();   
					Date createDate = new Date();
					String createUser = vo.getCreateUser();
					
					BuzRecordContentPOJO pojo = new BuzRecordContentPOJO();
					pojo.setOrderMId(orderMId);
					pojo.setContentId(contentId);
					pojo.setItemId(itemId);
					pojo.setItemName(itemName);
					pojo.setItemValue(itemValue);
					pojo.setSortSequence(sortSequence);
					pojo.setRemark(remark);
					pojo.setCreateDate(createDate);
					pojo.setCreateUser(createUser);
					
					dataList.add(pojo);
				}
			}
			
			recordContentWarehouse.deleteByOrderMId(orderMId);
			recordContentWarehouse.save(dataList);
		}
		
		return true;
	}

	@Override
	public boolean updateOrderMainInfo(OrderInfoVO vo) {
		String orderMId = vo.getOrderMId();
        String msisdn = vo.getMsisdn();
        String custId = vo.getCustId();
        String ivrCode = vo.getIvrCode();
        String salesId = vo.getSalesId();
        String promotionId = vo.getPromotionId();
        String updateUser = vo.getUpdateUser();
        String custType = vo.getCustType();
        String corpPicTaxid = vo.getCorpPicTaxid();
        String flowId = vo.getFlowId();
        String orderTypeId = vo.getOrderTypeId();
        String orderTypeName = vo.getOrderTypeName();
        String processUserId = vo.getProcessUserId();
        String processUserName = vo.getProcessUserName();

        OrderMainOspPOJO pojo = new OrderMainOspPOJO();
        pojo.setOrderMId(orderMId);
        pojo.setMsisdn(msisdn);
        pojo.setCustId(custId);
        pojo.setIvrCode(ivrCode);
        pojo.setSalesId(salesId);
        pojo.setPromotionId(promotionId);
        pojo.setUpdateDate(new Date());
        pojo.setUpdateUser(updateUser);
        pojo.setCustType(custType);
        pojo.setCorpPicTaxid(corpPicTaxid);
        pojo.setFlowId(flowId);
        pojo.setOrderTypeId(orderTypeId);
        pojo.setOrderTypeName(orderTypeName);
        pojo.setProcessUserId(processUserId);
        pojo.setProcessUserName(processUserName);
		
		orderMainOspWarehouse.update(pojo);
		
		return true;
	}
	
	@Override
	public boolean updateNoticeSales(String isNoticeSales) {
		if (StringUtils.isNotBlank(isNoticeSales)) {
			String sqlText = ResourceFileUtil.SQL_ORDER.getResource("UPDATE_NOTICE_SALES");
			
			Map<String, Object> params = new HashMap<>();
			params.put("IS_NOTICE_SALES", isNoticeSales);
			
			jdbcDAO.update(sqlText, params);
		}
		
		return true;
	}
	
	@Override
	public boolean createOrderMessage(OrderProcessVO vo) {
		if (vo == null) {
			return true;
		}
		
		String orderMId = vo.getOrderMId();
		String msgContent = vo.getMsgContent();
		String userId = vo.getUserId();
		
		OrderMessagePOJO pojo = new OrderMessagePOJO();
		pojo.setOrderMId(orderMId);
		pojo.setMsgContent(msgContent);
		pojo.setCreateDate(new Date());
		pojo.setCreateUser(userId);
		pojo.setUpdateDate(new Date());
		pojo.setUpdateUser(userId);
		
		orderMessageWarehouse.delete(orderMId);
		orderMessageWarehouse.save(pojo);
		
		return true;
	}

	@Override
	public String queryOrderMessage(String orderMId) {
		if (StringUtils.isNotBlank(orderMId)){
			OrderMessagePOJO pojo  = orderMessageWarehouse.findByOrderMId(orderMId);
			
			if (pojo != null) {
				return pojo.getMsgContent();
			}
		}
		
		return "";
	}

	@Override
	public boolean updateOrderProcessInfo(OrderProcessVO vo) {
		if (vo == null) {
			return true;
		}
		
		String orderMId = vo.getOrderMId();
		String comment = vo.getComment();
		String userId = vo.getUserId();
		String processReason = vo.getProcessReason();
		String processResult = vo.getProcessResult();
		
		OrderMainOspPOJO pojo = new OrderMainOspPOJO();
		pojo.setOrderMId(orderMId);
		pojo.setCommment(comment);;
		pojo.setUpdateDate(new Date());
		pojo.setUpdateUser(userId);
		pojo.setProcessReason(processReason);
		pojo.setProcessResult(processResult);
		
		orderMainOspWarehouse.update(pojo);
		
		return true;
	}

	@Override
	public List<NotifyOtherSalesVO> queryNotifyOtherSalesOrderList(NotifyOtherSalesVO vo) {
		if (vo == null) {
			return Collections.emptyList();
		}

		String msisdn = vo.getMsisdn();
		String orderTypeId = vo.getOrderTypeId();
		String processUserId = vo.getProcessUserId();
		
		Condition condition = new Condition();
		if (StringUtils.isNotBlank(msisdn)) {
			condition.and(Restrictions.eq("T1.MSISDN", msisdn));
		}
		if (StringUtils.isNotBlank(orderTypeId)) {
			condition.and(Restrictions.eq("T1.ORDER_TYPE_ID", orderTypeId));
		}
		if (StringUtils.isNotBlank(processUserId)) {
			condition.and(Restrictions.eq("T1.PROCESS_USER_ID", processUserId));
		}
		condition.and(Restrictions.sqlRestrictions("T1.ORDER_STATUS IN (  '070',  '080' )"));
		condition.and(Restrictions.sqlRestrictions("ORDER_M_ID NOT IN(SELECT T4.ORDER_M_ID FROM NOTIFY_DETAIL T4 LEFT JOIN NOTIFY_MAIN T5 ON T4.NOTIFY_MAIN_ID = T5.NOTIFY_MAIN_ID WHERE T5.NOTIFY_FUNC= '1')"));
		
		String sqlText = ResourceFileUtil.SQL_ORDER.getResource("QUERY_NOTIFY_OTHER_SALES_LIST");
		sqlText = condition.getCompleteSQL(sqlText);
    	Map<String, Object> params = condition.getParams();
    	
        List<NotifyOtherSalesVO> list = jdbcDAO.queryForBean(sqlText, params, NotifyOtherSalesVO.class);

		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
        
        // 去除日期/時間尾數 [.0]
        for (NotifyOtherSalesVO notifyOtherSalesVO : list) {
        	String date = notifyOtherSalesVO.getOspCreateTime();
        	date = date.replace(".0", "");
        	notifyOtherSalesVO.setOspCreateTime(date);
		}
		
		return list;
	}

	@Override
	public List<SysOptionsComboPOJO> queryComboContent() {
		return sysOptionsComboWareHouse.findByComboType("MAIL_PORTOUT_CP");
	}

	@Override
	public boolean createNotifyOtherSales(NotifyOtherSalesVO vo) {
		if (vo == null) {
			return false;
		}
		
		String recipients = getRecipients(vo);
		String cpRecipients = vo.getComboContent();
		String subject = getSubject();
		String msisdn = getMsisdn(vo);
		String createUser = vo.getCreateUser();
		String updateUser = vo.getUpdateUser();
		
		NotifyMainPOJO notifyMainPOJO = new NotifyMainPOJO();
		notifyMainPOJO.setNotifyMainId(IdentifierIdUtil.getUuid());
		notifyMainPOJO.setNotifyType("E");
		notifyMainPOJO.setNotifyFunc("1");
		notifyMainPOJO.setEmailRecipients(recipients);
		notifyMainPOJO.setEmailCpRecipients(cpRecipients);
		notifyMainPOJO.setHasSend("N");
		notifyMainPOJO.setSubject(subject);
		notifyMainPOJO.setContent(msisdn);
		notifyMainPOJO.setSendDate(new Date());
		notifyMainPOJO.setCreateDate(new Date());
		notifyMainPOJO.setCreateUser(createUser);
		notifyMainPOJO.setUpdateDate(new Date());
		notifyMainPOJO.setUpdateUser(updateUser);
		
		boolean result = notifyMainWareHouse.save(notifyMainPOJO);
		
		if (result) {
			List<NotifyDetailPOJO> pojoList = new ArrayList<>();
			List<Map<String, Object>> list = vo.getMsisdnList();
			
			for (Map<String, Object> map : list) {
				NotifyDetailPOJO notifyDetailPOJO = new NotifyDetailPOJO();
				notifyDetailPOJO.setNotifyDetailId(IdentifierIdUtil.getUuid());
				notifyDetailPOJO.setNotifyMainId(notifyMainPOJO.getNotifyMainId());
				notifyDetailPOJO.setOrderMId(MapUtils.getString(map, "orderMId"));
				notifyDetailPOJO.setCreateDate(new Date());
				notifyDetailPOJO.setCreateUser(createUser);
				notifyDetailPOJO.setUpdateDate(new Date());
				notifyDetailPOJO.setUpdateUser(updateUser);
				pojoList.add(notifyDetailPOJO);
			}
			
			result = notifyDetailWareHouse.saveInBatch(pojoList);
		}
		
		return result;
	}

	private String getMsisdn(NotifyOtherSalesVO vo) {
		if (vo == null) {
			return "";
		}
		
		List<Map<String, Object>> msisdnList = vo.getMsisdnList();
		StringBuilder msisdn = new StringBuilder();
		
		for (Map<String, Object> map : msisdnList) {
			msisdn.append(MapUtils.getString(map, "msisdn"));
			msisdn.append(",");
		}
		
		return msisdn.toString();
	}

	private String getSubject() {
		String date = DateUtil.fromDate(new Date(), DateUtil.DATE_OF_MONTH_DAYS_SLASH_PATTERN);
		StringBuilder subject = new StringBuilder();

		subject.append(date);
		subject.append("「以下門號為己批核完成」");
		
		return subject.toString();
	}

	private String getRecipients(NotifyOtherSalesVO vo) {
		if (vo == null) {
			return "";
		}
		
		List<SysOptionsComboPOJO> pojos = sysOptionsComboWareHouse.findByComboTypeAndComboValue("MAIL_TELECOM", vo.getComboValue());
		StringBuilder recipients = new StringBuilder();
		
		for (SysOptionsComboPOJO pojo : pojos) {
			String content = pojo.getComboContent();
			
			recipients.append(content);
			recipients.append(",");
		}
		
		return recipients.toString();
	}

	@Override
	public boolean createOrderKPIOwner(OrderProcessVO orderProcessVO) {
		if (orderProcessVO == null) {
			return false;
		}

		String orderMId = orderProcessVO.getOrderMId();
		String userId = orderProcessVO.getUserId();
		String sqlText = ResourceFileUtil.SQL_ORDER.getResource("UPDATE_ORDER_KPI_OWNER");
		
		Map<String, Object> params = new HashMap<>();
		params.put("ORDER_M_ID", orderMId);
		params.put("KPI_OWNER_ID", userId);
		params.put("CREATE_USER", userId);
		params.put("CREATE_DATE", new Date());
		jdbcDAO.update(sqlText, params);
		
		return true;
	}
	
	@Override
	public String queryPoolKeyByOrderMId(String orderMId) {
		if(StringUtils.isEmpty(orderMId)) {
			return "";
		}
		
		return orderMainOspWarehouse.findPoolKeyByOrderMId(orderMId);
	}

	@Override
	public List<OrderDetailSpvVO> queryOrderDetailSpvByPoolKey(String poolKey) {
		if(StringUtils.isEmpty(poolKey)) {
			return Collections.emptyList();
		}
		
		List<OrderDetailSpvPOJO> pojoLs = orderDetailSpvWarehouse.findByPoolKey(poolKey);
		
		List<OrderDetailSpvVO> voLs = new ArrayList<OrderDetailSpvVO>();
		
		if(CollectionUtils.isNotEmpty(pojoLs)) {
			for(OrderDetailSpvPOJO pojo : pojoLs) {
				String orderDId 		   = pojo.getOrderDId();
				String poolkey 			   = pojo.getPoolKey();
				String sourceOrderId 	   = pojo.getSourceOrderId();
				String accountId 		   = pojo.getAccountId();
				String msisdn 			   = pojo.getMsisdn();
				String cycleCode 		   = pojo.getCycleCode();
				String cancelReason 	   = pojo.getCancelReason();
				String opId 			   = pojo.getOpId();
				String channel 			   = pojo.getChannel();
				String serviceName 		   = pojo.getServiceName();
				String offerId 			   = pojo.getOfferId();
				String offerName 		   = pojo.getOfferName();
				String taskStatus 		   = pojo.getTaskStatus();
				Date statusTime 		   = pojo.getStatusTime();
				Date requestEffectiveDate  = pojo.getRequestEffectiveDate();
				Date requestExpirationDate = pojo.getRequestExpirationDate();
				Date taskCreationDate 	   = pojo.getTaskCreationDate();
				String taskSeqNo 		   = pojo.getTaskSeqNo();
				String taskId       	   = pojo.getTaskId();
				String createUser 		   = pojo.getCreateUser();
				Date createDate 		   = pojo.getCreateDate();
				Date updateDate 		   = pojo.getUpdateDate();
				String updateUser 		   = pojo.getUpdateUser();
				
				OrderDetailSpvVO vo = new OrderDetailSpvVO(); 
				
				vo.setOrderDId(orderDId);
				vo.setPoolKey(poolkey);
				vo.setSourceOrderId(sourceOrderId);
				vo.setAccountId(accountId);
				vo.setMsisdn(msisdn);
				vo.setCycleCode(cycleCode);
				vo.setCancelReason(cancelReason);
				vo.setOpId(opId);
				vo.setChannel(channel);
				vo.setServiceName(serviceName);
				vo.setOfferId(offerId);
				vo.setOfferName(offerName);
				vo.setTaskStatus(taskStatus);
				vo.setStatusTime(DateUtil.fromDate(statusTime));
				vo.setRequestEffectiveDate(DateUtil.fromDate(requestEffectiveDate));
				vo.setRequestExpirationDate(DateUtil.fromDate(requestExpirationDate));
				vo.setTaskCreationDate(DateUtil.fromDate(taskCreationDate));
				vo.setTaskSeqNo(taskSeqNo);
				vo.setTaskId(taskId);
				vo.setCreateDate(createDate);
				vo.setCreateUser(createUser);
				vo.setUpdateDate(updateDate);
				vo.setUpdateDateTxt(DateUtil.fromDate(updateDate));
				vo.setUpdateUser(updateUser);
				
				voLs.add(vo);
			}
		}
		
		return voLs;
	}

	@Override
	public boolean orderHandlingAuthority(String userId, String orderTypeId) {
		if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(orderTypeId)) {
			String sqlText = ResourceFileUtil.SQL_ORDER.getResource("ORDER_HANDLING_AUTHORITY");
			
			Map<String, Object> params = new HashMap<>();
			params.put("USER_ID", userId);
			params.put("ORDER_TYPE_ID", orderTypeId);
			
			List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqlText, params);
			
			if (CollectionUtils.isNotEmpty(dataList)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int saveOrderMainOspForIgnoreNullValue(OrderInfoVO vo) {
		if (vo == null) {
			return 0;
		}

		String orderMId = vo.getOrderMId();
		String flowId = vo.getFlowId();
		String orderTypeId = vo.getOrderTypeId();
		String orderTypeName = vo.getOrderTypeName();
		String processUserId = vo.getProcessUserId();
		String processUserName = vo.getProcessUserName();
		String updateUser = vo.getUpdateUser();
		
		String sqlText = ResourceFileUtil.SQL_ORDER.getResource("UPDATE_ORDER_MAIN_OSP_FOR_IGNORE_NULL_VALUE");
		
		Map<String, Object> params = new HashMap<>();
		params.put("ORDER_M_ID", orderMId);
		params.put("FLOW_ID", flowId);
		params.put("ORDER_TYPE_ID", orderTypeId);
		params.put("ORDER_TYPE_NAME", orderTypeName);
		params.put("PROCESS_USER_ID", processUserId);
		params.put("PROCESS_USER_NAME", processUserName);
		params.put("UPDATE_USER", updateUser);
		params.put("UPDATE_DATE", new Date());
		jdbcDAO.update(sqlText, params);
		
		return 1;
	}

}
