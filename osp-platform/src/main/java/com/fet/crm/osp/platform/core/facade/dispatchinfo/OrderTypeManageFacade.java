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

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fet.crm.osp.platform.core.service.dispatchinfo.IOrderTypeManageService;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.FlowSourceMapVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.OrderTypeInfoVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.OrderTypeMaintainVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.condition.OrderTypeInfoCVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderStatusCVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.PersonalOrderOperateRecordCVO;

/**
 * [案件類別管理] 總體服務窗口
 * 
 * @author LawrenceLai,AndrewLee
 */
@Service
public class OrderTypeManageFacade {

    @Autowired
    private IOrderTypeManageService orderTypeManageService;

    /**
     * 取得流程代號
     * 
     * @author Adam
     * @create date: May 4, 2017
     *
     * @param sourceSysId
     * @param sourceProdTypeId
     * @return
     */
    @Transactional(readOnly = true)
	public String getFlowIdBySourceSysIdAndSourceProdTypeId(String sourceSysId, String sourceProdTypeId) {
		if (StringUtils.isBlank(sourceProdTypeId) && StringUtils.isBlank(sourceSysId)) {
			return "";
		}
		
		FlowSourceMapVO vo = orderTypeManageService.queryFlowIdBySourceSysIdAndSourceProdTypeId(sourceSysId, sourceProdTypeId);

		if (vo == null) {
			return "";
		}
		
        return vo.getFlowId();
    }

    /**
     * 查詢 案件類別資訊<br>
     * 取得條件：案件類別名稱
     * 
     * @param orderTypeName
     * @return List<OrderTypeInfoVO>
     */
    @Transactional(readOnly = true)
    public List<OrderTypeInfoVO> getOrderTypeInfoByTypeName(String orderTypeName) {
    	OrderTypeInfoCVO infoCVO = new OrderTypeInfoCVO();
    	
    	infoCVO.setOrderTypeName(orderTypeName);
    	
        return orderTypeManageService.queryOrderTypeInfo(infoCVO);
    }

    /**
     * 查詢 案件類別資訊<br>
     * 取得條件：案件類別ID
     * 
     * @param orderTypeId
     * @return OrderTypeInfoVO
     */
    @Transactional(readOnly = true)
    public OrderTypeInfoVO getOrderTypeInfoByTypeId(String orderTypeId) {
    	OrderTypeInfoCVO infoCVO = new OrderTypeInfoCVO();
    	
    	infoCVO.setOrderTypeId(orderTypeId);
    	
    	List<OrderTypeInfoVO> dataList = orderTypeManageService.queryOrderTypeInfo(infoCVO);
    	
    	if (!CollectionUtils.isEmpty(dataList)) {
    		return dataList.get(0);
    	}
    	
        return new OrderTypeInfoVO();
    }

    /**
     * 修改案件類別設定資訊
     * 
     * @param orderTypeInfoVO
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean modifyOrderTypeInfo(OrderTypeMaintainVO maintainVO) {
        return orderTypeManageService.modifyOrderTypeInfo(maintainVO);
    }

    /**
     * 查詢 案件類別進件來源<br>
     * 
     * @param orderTypeId
     * @return List<FlowSourceMapVO>
     */
    @Transactional(readOnly = true)
    public List<FlowSourceMapVO> getFlowSourceByorderTypeId(String orderTypeId) {
        return orderTypeManageService.queryFlowSourceByorderTypeId(orderTypeId);
    }
    
    /**
     * 透過輸入的查詢條件.查詢案件狀態資訊
     * 
     * @param cvo
     * @return List<Map<String,Object>>
     */
    @Transactional(readOnly = true)
    public List<Map<String,Object>> getOrderStatusInfo(OrderStatusCVO cvo) {
        return orderTypeManageService.queryOrderStautsInfo(cvo);
    }
    
    /**
     * 透過條件 查詢個人產能資訊
     * 
     * @param cvo
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getPersonalOrderOperateRecordInfo(PersonalOrderOperateRecordCVO cvo) {
        return orderTypeManageService.queryPersonalOrderOperateRecordInfo(cvo);
    }

}
