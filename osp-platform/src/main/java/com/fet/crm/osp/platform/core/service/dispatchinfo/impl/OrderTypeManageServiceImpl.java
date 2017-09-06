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

package com.fet.crm.osp.platform.core.service.dispatchinfo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.common.util.DateUtil;
import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.condition.Condition;
import com.fet.crm.osp.platform.core.db.condition.api.Restrictions;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.db.warehouse.FlowSourceMapWarehouse;
import com.fet.crm.osp.platform.core.db.warehouse.OrderTypeSettingWarehouse;
import com.fet.crm.osp.platform.core.db.warehouse.ProdTypeMapWarehouse;
import com.fet.crm.osp.platform.core.pojo.SysFlowSourcePOJO;
import com.fet.crm.osp.platform.core.pojo.SysOrderTypeSettingPOJO;
import com.fet.crm.osp.platform.core.service.dispatchinfo.IOrderTypeManageService;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.FlowSourceMapVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.OrderTypeInfoVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.OrderTypeMaintainVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.condition.OrderTypeInfoCVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderStatusCVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.PersonalOrderOperateRecordCVO;

/**
 * [案件類別維護作業] 服務界面
 *
 * @author AndrewLee
 * @author AllenChen at 2017-0508
 * 
 */
@Service
public class OrderTypeManageServiceImpl implements IOrderTypeManageService {

    @Autowired
    private JdbcDAO jdbcDAO;

    @Autowired
    private OrderTypeSettingWarehouse orderTypeSettingwarehouse;

    @Autowired
    private FlowSourceMapWarehouse flowSourceMapWarehouse;

    @Autowired
    private ProdTypeMapWarehouse prodTypeMapWarehouse;

	@Override
	public FlowSourceMapVO queryFlowIdBySourceSysIdAndSourceProdTypeId(String sourceSysId, String sourceProdTypeId) {
		if (StringUtils.isBlank(sourceProdTypeId) && StringUtils.isBlank(sourceSysId)) {
			return new FlowSourceMapVO();
		}
		
		SysFlowSourcePOJO pojo = flowSourceMapWarehouse.findByIdSourceSysIdAndSourceProdTypeId(sourceSysId, sourceProdTypeId);

		if (pojo == null) {
			return new FlowSourceMapVO();
		}
		
		FlowSourceMapVO vo = new FlowSourceMapVO();
		BeanUtils.copyProperties(pojo, vo);
		
		return vo;
	}
    
    @Override
    public List<OrderTypeInfoVO> queryOrderTypeInfo(OrderTypeInfoCVO infoCVO) {
        if (infoCVO != null) {
            String orderTypeId = infoCVO.getOrderTypeId();
            String orderTypeName = infoCVO.getOrderTypeName();

            String sqltext = ResourceFileUtil.SQL.getResource("dispatch", "ORDER_TYPE_INFO_BY_CONDITION");
            Condition condition = new Condition();

            if (StringUtils.isNotBlank(orderTypeId)) {
                condition.and(Restrictions.eq("ORDER_TYPE_ID", orderTypeId));
            }
            if (StringUtils.isNotBlank(orderTypeName)) {
                condition.and(Restrictions.eq("ORDER_TYPE_NAME", orderTypeName));
            }

            sqltext = condition.getCompleteSQL(sqltext);
            Map<String, Object> params = condition.getParams();

            List<OrderTypeInfoVO> dataList = jdbcDAO.queryForList(sqltext, params, OrderTypeInfoVO.class);

            if (dataList != null && !dataList.isEmpty()) {
                return dataList;
            }
        }

        return Collections.emptyList();
    }


    @Override
    public boolean modifyOrderTypeInfo(OrderTypeMaintainVO maintainVO) {
        String orderTypeId = maintainVO.getOrderTypeId();
        BigDecimal successSec = maintainVO.getSuccessSec();
        BigDecimal failSec = maintainVO.getFailSec();
        BigDecimal regularTime = maintainVO.getRegularTime();
        BigDecimal overtime = maintainVO.getOvertime();
        BigDecimal criticalCounts = maintainVO.getCriticalCounts();
        BigDecimal overtimeCounts = maintainVO.getOvertimeCounts();
        String email = maintainVO.getEmail();
        String updateUser = maintainVO.getUpdateUser();
        String kpiDayType = maintainVO.getKpiDayType();
        String chkCreateDate = maintainVO.getChkCreateDate();
        String beforeCreateDate = maintainVO.getBeforeCreateDate();
        String isRegularTime = maintainVO.getIsRegularTime();
        String startCountTime = maintainVO.getStartCountTime();

        SysOrderTypeSettingPOJO pojo = new SysOrderTypeSettingPOJO();

        pojo.setOrderTypeId(orderTypeId);
        pojo.setSuccessSec(successSec);
        pojo.setFailSec(failSec);
        pojo.setRegularTime(regularTime);
        pojo.setOvertime(overtime);
        pojo.setCriticalCounts(criticalCounts);
        pojo.setOvertimeCounts(overtimeCounts);
        pojo.setEmail(email);
        pojo.setUpdateDate(new Date());
        pojo.setUpdateUser(updateUser);
        pojo.setKpiDayType(kpiDayType);
        pojo.setChkCreateDate(chkCreateDate);
        pojo.setBeforeCreateDate(beforeCreateDate);
        pojo.setIsRegularTime(isRegularTime);
        pojo.setStartCountTime(startCountTime);

        orderTypeSettingwarehouse.update(pojo);

        return true;
    }

    @Override
    public List<FlowSourceMapVO> queryFlowSourceByorderTypeId(String orderTypeId) {
        List<SysFlowSourcePOJO> dataLs = flowSourceMapWarehouse.findByOrderTypeId(orderTypeId);
        Map<String, String> dataMap = prodTypeMapWarehouse.getProdTypeMap();

        List<FlowSourceMapVO> rtnLs = new ArrayList<>();

        for (SysFlowSourcePOJO pojo : dataLs) {
            String sourceSysId = pojo.getSourceSysId();
            String flowId = pojo.getFlowId();
            String sourceProdTypeId = pojo.getSourceProdTypeId();
            String sourceProdTypeName = dataMap.get(sourceProdTypeId);
            BigDecimal upperLimitCounts = pojo.getUpperLimitCounts();
            BigDecimal lowerLimitCounts = pojo.getLowerLimitCounts();
            String pojoOrderTypeId = pojo.getOrderTypeId();
            String orderTypeName = pojo.getOrderTypeName();
            String isDefault = pojo.getIsDefault();
            Date createDate = pojo.getCreateDate();
            String createUser = pojo.getCreateUser();
            Date updateDate = pojo.getUpdateDate();
            String updateUser = pojo.getUpdateUser();

            FlowSourceMapVO vo = new FlowSourceMapVO();
            vo.setSourceSysId(sourceSysId);
            vo.setFlowId(flowId);
            vo.setSourceProdTypeId(sourceProdTypeId);
            vo.setSourceProdTypeName(sourceProdTypeName);
            vo.setUpperLimitCounts(upperLimitCounts);
            vo.setLowerLimitCounts(lowerLimitCounts);
            vo.setOrderTypeId(pojoOrderTypeId);
            vo.setOrderTypeName(orderTypeName);
            vo.setIsDefault(isDefault);
            vo.setCreateDateTxt(DateUtil.fromDate(createDate, DateUtil.DATE_TIME_PATTERN));
            vo.setCreateUser(createUser);
            vo.setUpdateDateTxt(DateUtil.fromDate(updateDate, DateUtil.DATE_TIME_PATTERN));
            vo.setUpdateUser(updateUser);

            rtnLs.add(vo);
        }

        return rtnLs;
    }

    @Override
    public List<Map<String, Object>> queryOrderStautsInfo(OrderStatusCVO cvo) {
        if (cvo != null) {
            String sourceCreateTimeBegin = cvo.getSourceCreateTimeBegin();
            String sourceCreateTimeEnd = cvo.getSourceCreateTimeEnd();
            String orderFinishDateBegin = cvo.getOrderFinishDateBegin();
            String orderFinishDateEnd = cvo.getOrderFinishDateEnd();
            String custId = cvo.getCustId();
            String custName = cvo.getCustName();
            String ivrCode = cvo.getIvrCode();
            String msisdn = cvo.getMsisdn();
            String operateType = cvo.getOperateType();
            String orderMId = cvo.getOrderMId();
            String orderStatus = cvo.getOrderStatus();
            String orderTypeId = cvo.getOrderTypeId();
            String processUserName = cvo.getProcessUserName();
            String salesId = cvo.getSalesId();
            String sourceOrderId = cvo.getSourceOrderId();
            String sourceProdTypeId = cvo.getSourceProdTypeId();
            String sourceSysId = cvo.getSourceSysId();

            String sqlText = ResourceFileUtil.SQL.getResource("order", "QUERY_ORDER_STATUS_BY_CONDITION");
            Condition condition = new Condition();

            if (StringUtils.isNotBlank(sourceCreateTimeBegin) && StringUtils.isNotBlank(sourceCreateTimeEnd)) {
                StringBuffer sourceCreateTimeSB = new StringBuffer();

                sourceCreateTimeSB.append("T1.SOURCE_CREATE_TIME BETWEEN TO_DATE('").append(sourceCreateTimeBegin)
                        .append("','yyyy/MM/dd hh24:mi:ss') AND TO_DATE('").append(sourceCreateTimeEnd)
                        .append("','yyyy/MM/dd hh24:mi:ss')");

                condition.and(Restrictions.sqlRestrictions(sourceCreateTimeSB.toString()));
            } else {
                if (StringUtils.isNotBlank(sourceCreateTimeBegin)) {
                    condition.and(Restrictions.sqlRestrictions("T1.SOURCE_CREATE_TIME > TO_DATE('"
                            + sourceCreateTimeBegin + "', 'yyyy/MM/dd hh24:mi:ss')"));
                }
                if (StringUtils.isNotBlank(sourceCreateTimeEnd)) {
                    condition.and(Restrictions.sqlRestrictions(
                            "T1.SOURCE_CREATE_TIME < TO_DATE('" + sourceCreateTimeEnd + "', 'yyyy/MM/dd hh24:mi:ss')"));
                }
            }

            if (StringUtils.isNotBlank(orderFinishDateBegin) && StringUtils.isNotBlank(orderFinishDateEnd)) {
                StringBuffer orderFinishDateSB = new StringBuffer();

                orderFinishDateSB.append("T1.UPDATE_DATE BETWEEN TO_DATE('").append(orderFinishDateBegin)
                        .append("','yyyy/MM/dd hh24:mi:ss') AND TO_DATE('").append(orderFinishDateEnd)
                        .append("','yyyy/MM/dd hh24:mi:ss')");

                condition.and(Restrictions.sqlRestrictions(orderFinishDateSB.toString()));
                condition.and(Restrictions.sqlRestrictions("T1.ORDER_STATUS IN ('080','070')"));
            } else {
                if (StringUtils.isNotBlank(orderFinishDateBegin)) {
                    condition.and(Restrictions.sqlRestrictions("T1.UPDATE_DATE > TO_DATE('" + orderFinishDateBegin + "', 'yyyy/MM/dd hh24:mi:ss')"));
                    condition.and(Restrictions.sqlRestrictions("T1.ORDER_STATUS IN ('080','070')"));
                }
                if (StringUtils.isNotBlank(orderFinishDateEnd)) {
                    condition.and(Restrictions.sqlRestrictions("T1.UPDATE_DATE < TO_DATE('" + orderFinishDateEnd + "', 'yyyy/MM/dd hh24:mi:ss')"));
                    condition.and(Restrictions.sqlRestrictions("T1.ORDER_STATUS IN ('080','070')"));
                }
            }

            if (StringUtils.isNotBlank(custId)) {
                condition.and(Restrictions.eq("T1.CUST_ID", custId));
            }
            if (StringUtils.isNotBlank(custName)) {
                condition.and(Restrictions.eq("T1.CUST_NAME", custName));
            }
            if (StringUtils.isNotBlank(ivrCode)) {
                condition.and(Restrictions.eq("T1.IVR_CODE", ivrCode));
            }
            if (StringUtils.isNotBlank(msisdn)) {
                condition.and(Restrictions.eq("T1.MSISDN", msisdn));
            }
            if (StringUtils.isNotBlank(operateType)) {
                condition.and(Restrictions.eq("T1.OPERATE_TYPE", operateType));
            }
            if (StringUtils.isNotBlank(orderMId)) {
                condition.and(Restrictions.eq("T1.ORDER_M_ID", orderMId));
            }
            if (StringUtils.isNotBlank(orderStatus)) {
                condition.and(Restrictions.eq("T1.ORDER_STATUS", orderStatus));
            }
            if (StringUtils.isNotBlank(orderTypeId)) {
                condition.and(Restrictions.eq("T1.ORDER_TYPE_ID", orderTypeId));
            }
            if (StringUtils.isNotBlank(processUserName)) {
                condition.and(Restrictions.eq("T1.PROCESS_USER_NAME", processUserName));
            }
            if (StringUtils.isNotBlank(salesId)) {
                condition.and(Restrictions.eq("T1.SALES_ID", salesId));
            }
            if (StringUtils.isNotBlank(sourceOrderId)) {
                condition.and(Restrictions.eq("T1.SOURCE_ORDER_ID", sourceOrderId));
            }
            if (StringUtils.isNotBlank(sourceProdTypeId)) {
                condition.and(Restrictions.eq("T1.SOURCE_PROD_TYPE_ID", sourceProdTypeId));
            }
            if (StringUtils.isNotBlank(sourceSysId)) {
                condition.and(Restrictions.eq("T1.SOURCE_SYS_ID", sourceSysId));
            }

            Map<String, Object> params = condition.getParams();
            sqlText = condition.getCompleteSQL(sqlText);

            List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqlText, params);
            
            return dataList;
        }

        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> queryPersonalOrderOperateRecordInfo(PersonalOrderOperateRecordCVO cvo) {
        //TODO sql名稱未填
        String sqlText = ResourceFileUtil.SQL.getResource("order", "");

        Condition condition = new Condition();

        String userId = cvo.getUserId();
        String queryBeginDate = cvo.getQueryBeginDate();
        String queryEndDate = cvo.getQueryEndDate();

        //TODO Column Name 
        if (StringUtils.isNotBlank(userId)) {
            condition.and(Restrictions.eq("T1.xxxxx", userId));
        }

        if (StringUtils.isNotBlank(queryBeginDate) && StringUtils.isNotBlank(queryEndDate)) {
            StringBuffer sourceCreateTimeSB = new StringBuffer();
            //TODO Column Name 
            sourceCreateTimeSB.append("T1.xxxxx BETWEEN TO_DATE('").append(queryBeginDate)
                    .append("','yyyy/MM/dd hh24:mi:ss') AND TO_DATE('").append(queryEndDate)
                    .append("','yyyy/MM/dd hh24:mi:ss')");

            condition.and(Restrictions.sqlRestrictions(sourceCreateTimeSB.toString()));
        } else {
            if (StringUtils.isNotBlank(queryBeginDate)) {
                //TODO Column Name 
                condition.and(Restrictions.sqlRestrictions("T1.xxxxxxx > TO_DATE('" + queryBeginDate + "', 'yyyy/MM/dd hh24:mi:ss')"));
            }
            if (StringUtils.isNotBlank(queryEndDate)) {
                //TODO Column Name 
                condition.and(Restrictions.sqlRestrictions("T1.xxxxxx < TO_DATE('" + queryEndDate + "', 'yyyy/MM/dd hh24:mi:ss')"));
            }
        }

        Map<String, Object> params = condition.getParams();

        List<Map<String, Object>> dataList = jdbcDAO.queryForList(sqlText, params);
        
        //TODO 注意。這裡應該還需要對取得回來的資料做效能計算的處理。

        return dataList;
    }

}
