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

package com.fet.crm.osp.platform.core.service.dispatchinfo;

import java.util.List;
import java.util.Map;

import com.fet.crm.osp.platform.core.vo.dispatchinfo.FlowSourceMapVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.OrderTypeInfoVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.OrderTypeMaintainVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.condition.OrderTypeInfoCVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderStatusCVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.PersonalOrderOperateRecordCVO;

/**
 * [案件類別管理] 服務介面
 * 
 * @author LawrenceLai
 */
public interface IOrderTypeManageService {
	
	/**
	 *  查詢 案件類別資訊<br>
	 *  
	 * 
	 * @param orderTypeName
	 * @return
	 */
	List<OrderTypeInfoVO> queryOrderTypeInfo(OrderTypeInfoCVO infoCVO);
	
	/**
	 * 修改案件類別設定資訊
	 * 
	 * @param orderTypeInfoVO
	 * @return boolean
	 */
	boolean modifyOrderTypeInfo(OrderTypeMaintainVO maintainVO);
	
	/**
	 * 查詢 案件類別進件來源<br>
	 * 
	 * @param orderTypeId
	 * @return List<FlowSourceMapVO>
	 */
	List<FlowSourceMapVO> queryFlowSourceByorderTypeId(String orderTypeId);
	
	/**
	 * 查詢 案件流程代號<br>
	 * 
	 * @param orderTypeId
	 * @return List<FlowSourceMapVO>
	 */
	FlowSourceMapVO queryFlowIdBySourceSysIdAndSourceProdTypeId(String sourceSysId, String sourceProdTypeId);
	
	/**
     * 透過輸入的查詢條件.查詢案件狀態資訊
     * 
     * @param cvo
     * @return List<Map<String,Object>>
     */
	List<Map<String,Object>> queryOrderStautsInfo(OrderStatusCVO cvo);
	
	/**
	 * 透過條件.查詢個人產能資訊
	 * 
	 * @param cvo
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryPersonalOrderOperateRecordInfo(PersonalOrderOperateRecordCVO cvo);

}
