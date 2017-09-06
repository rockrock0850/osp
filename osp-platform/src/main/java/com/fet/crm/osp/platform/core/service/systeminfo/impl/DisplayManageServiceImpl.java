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

package com.fet.crm.osp.platform.core.service.systeminfo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.common.Constant;
import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.db.warehouse.FlowSourceMapWarehouse;
import com.fet.crm.osp.platform.core.db.warehouse.OptionsReferenceWarehouse;
import com.fet.crm.osp.platform.core.db.warehouse.OrderStatusSettingWarehouse;
import com.fet.crm.osp.platform.core.db.warehouse.OrderTypeSettingWarehouse;
import com.fet.crm.osp.platform.core.db.warehouse.ProdTypeMapWarehouse;
import com.fet.crm.osp.platform.core.db.warehouse.SkillWarehouse;
import com.fet.crm.osp.platform.core.db.warehouse.SysFlowReasonMapWareHouse;
import com.fet.crm.osp.platform.core.db.warehouse.SysOptionsComboWareHouse;
import com.fet.crm.osp.platform.core.pojo.OptionsReferencePOJO;
import com.fet.crm.osp.platform.core.pojo.OrderStatusSettingPOJO;
import com.fet.crm.osp.platform.core.pojo.ProdTypeMapPOJO;
import com.fet.crm.osp.platform.core.pojo.SkillPOJO;
import com.fet.crm.osp.platform.core.pojo.SysFlowReasonMapPOJO;
import com.fet.crm.osp.platform.core.pojo.SysFlowSourcePOJO;
import com.fet.crm.osp.platform.core.pojo.SysOptionsComboPOJO;
import com.fet.crm.osp.platform.core.pojo.SysOrderTypeSettingPOJO;
import com.fet.crm.osp.platform.core.service.systeminfo.IDisplayManageService;
import com.fet.crm.osp.platform.core.vo.systeminfo.component.DisplayVO;

/**
 * 「系統內存外顯資訊」實作類別
 * 
 * @author LawrenceLai, Adam Yeh
 */
@Service
public class DisplayManageServiceImpl implements IDisplayManageService {

	@Autowired
	private SysOptionsComboWareHouse sysOptionsComboWareHouse;
    @Autowired
    private OptionsReferenceWarehouse optionsReferenceWarehouse;
    @Autowired
    private ProdTypeMapWarehouse prodTypeMapWarehouse;
    @Autowired
    private OrderTypeSettingWarehouse orderTypeSettingWarehouse;
    @Autowired
    private OrderStatusSettingWarehouse orderStatusSettingWarehouse;
    @Autowired
    private SkillWarehouse skillWarehouse;
    @Autowired
    private SysFlowReasonMapWareHouse sysFlowReasonMapWareHouse;
    @Autowired
    private FlowSourceMapWarehouse flowSourceMapWarehouse;
    @Autowired
    private JdbcDAO jdbcDao;
    
	@Override
	public List<DisplayVO> queryCustTypeDisplay() {
        //JPA 以 IN 作為查詢條件式.必須放入Collection
        List<String> queryCondition = new ArrayList<>();
        queryCondition.add("CUST_TYPE");
        
        List<OptionsReferencePOJO> dataList = optionsReferenceWarehouse.findByOptionsTypeList(queryCondition);

        if (CollectionUtils.isNotEmpty(dataList)) {
            List<DisplayVO> displayList = new ArrayList<>();

            for (OptionsReferencePOJO pojo : dataList) {
                String value = pojo.getOptionsValue(); // 內存值
                String text = pojo.getOptionsText(); // 外顯值

                DisplayVO vo = new DisplayVO(value, text);

                displayList.add(vo);
            }

            return displayList;
        }

        return Collections.emptyList();
	}

	@Override
	public List<DisplayVO> queryOptionComboTypeDisplay(String type) {
        //JPA 以 IN 作為查詢條件式.必須放入Collection
        List<String> queryCondition = new ArrayList<>();
        queryCondition.add(type);
        
        List<SysOptionsComboPOJO> dataList = sysOptionsComboWareHouse.findByComboType(type);

        if (CollectionUtils.isNotEmpty(dataList)) {
            List<DisplayVO> displayList = new ArrayList<>();

            for (SysOptionsComboPOJO pojo : dataList) {
                String value = pojo.getComboValue(); // 內存值
                String text = pojo.getComboText(); // 外顯值

                DisplayVO vo = new DisplayVO(value, text);

                displayList.add(vo);
            }

            return displayList;
        }

        return Collections.emptyList();
	}

	@Override
	public List<DisplayVO> queryCreationSourceSystemDisplay() {
        //JPA 以 IN 作為查詢條件式.必須放入Collection
        List<String> queryCondition = new ArrayList<>();
        queryCondition.add("MAN_SOURCE_ID");        
        
        List<OptionsReferencePOJO> dataList = optionsReferenceWarehouse.findByOptionsTypeList(queryCondition);

        if (CollectionUtils.isNotEmpty(dataList)) {
            List<DisplayVO> displayList = new ArrayList<>();

            for (OptionsReferencePOJO pojo : dataList) {
                String value = pojo.getOptionsValue(); // 內存值
                String text = pojo.getOptionsText(); // 外顯值

                DisplayVO vo = new DisplayVO(value, text);

                displayList.add(vo);
            }

            return displayList;
        }

        return Collections.emptyList();
	}

    @Override
    public List<DisplayVO> querySourceSystemDisplay() {
        //JPA 以 IN 作為查詢條件式.必須放入Collection
        List<String> queryCondition = new ArrayList<>();
        queryCondition.add("SYS_SOURCE_ID");
        queryCondition.add("MAN_SOURCE_ID");        
        
        List<OptionsReferencePOJO> dataList = optionsReferenceWarehouse.findByOptionsTypeList(queryCondition);

        if (CollectionUtils.isNotEmpty(dataList)) {
            List<DisplayVO> displayList = new ArrayList<>();

            for (OptionsReferencePOJO pojo : dataList) {
                String value = pojo.getOptionsValue(); // 內存值
                String text = pojo.getOptionsText(); // 外顯值

                DisplayVO vo = new DisplayVO(value, text);

                displayList.add(vo);
            }

            return displayList;
        }

        return Collections.emptyList();
    }

    @Override
    public List<DisplayVO> queryProductTypeDisplay() {
        List<ProdTypeMapPOJO> dataList = prodTypeMapWarehouse.findAll();

        if (CollectionUtils.isNotEmpty(dataList)) {
            List<DisplayVO> displayList = new ArrayList<>();

            for (ProdTypeMapPOJO pojo : dataList) {
                String value = pojo.getSourceProdTypeId(); // 內存值
                String text = pojo.getSourceProdTypeName(); // 外顯值

                DisplayVO vo = new DisplayVO(value, text);

                displayList.add(vo);
            }

            return displayList;
        }

        return Collections.emptyList();
    }

    @Override
    public List<DisplayVO> queryOrderTypeDisplay() {
        List<SysOrderTypeSettingPOJO> dataList = orderTypeSettingWarehouse.findAll();

        if (CollectionUtils.isNotEmpty(dataList)) {
            List<DisplayVO> displayList = new ArrayList<>();

            for (SysOrderTypeSettingPOJO pojo : dataList) {
                String value = pojo.getOrderTypeId(); // 內存值
                String text = pojo.getOrderTypeName(); // 外顯值

                DisplayVO vo = new DisplayVO(value, text);

                displayList.add(vo);
            }

            return displayList;
        }

        return Collections.emptyList();
    }
    
	@Override
	public List<DisplayVO> queryOrderTypeDisplayBySourceSysId(String sourceSysId) {
		String sqlText = ResourceFileUtil.SQL.getResource("order", "QUERY_ORDER_TYPE_DISPLAY_BY_SOURCE_SYS_ID");
		Map<String, Object> params = new HashMap<>();
		params.put("SOURCE_SYS_ID", sourceSysId);
		
		List<SysFlowSourcePOJO> dataList = jdbcDao.queryForList(sqlText, params, SysFlowSourcePOJO.class);

        if (CollectionUtils.isNotEmpty(dataList)) {
            List<DisplayVO> displayList = new ArrayList<>();

            for (SysFlowSourcePOJO pojo : dataList) {
                String value = pojo.getOrderTypeId(); // 內存值
                String text = pojo.getOrderTypeName(); // 外顯值

                DisplayVO vo = new DisplayVO(value, text);

                displayList.add(vo);
            }

            return displayList;
        }

        return Collections.emptyList();
	}

    @Override
    public List<DisplayVO> queryOperateTypeDisplay() {
        List<OptionsReferencePOJO> dataList = optionsReferenceWarehouse.findByOptionsType("OPERATE_TYPE");

        if (CollectionUtils.isNotEmpty(dataList)) {
            List<DisplayVO> displayList = new ArrayList<>();

            for (OptionsReferencePOJO pojo : dataList) {
                String value = pojo.getOptionsValue(); // 內存值
                String text = pojo.getOptionsText(); // 外顯值

                DisplayVO vo = new DisplayVO(value, text);

                displayList.add(vo);
            }

            return displayList;
        }

        return Collections.emptyList();
    }

    @Override
    public List<DisplayVO> queryAllOrderStatusDisplay() {
        List<OrderStatusSettingPOJO> dataList = orderStatusSettingWarehouse.findAll();

        if (CollectionUtils.isNotEmpty(dataList)) {
            List<DisplayVO> displayList = new ArrayList<>();

            for (OrderStatusSettingPOJO pojo : dataList) {
                String value = pojo.getStatusId(); // 內存值
                String text = pojo.getStatusName(); // 外顯值

                DisplayVO vo = new DisplayVO(value, text);

                displayList.add(vo);
            }

            return displayList;
        }

        return Collections.emptyList();
    }
    
    @Override
	public List<DisplayVO> queryInvalidOrderStatusDisplay() {
    	List<DisplayVO> datalist = queryAllOrderStatusDisplay();
    	
		if (CollectionUtils.isNotEmpty(datalist)) {
			List<DisplayVO> displayList = new ArrayList<>();

			for (DisplayVO vo : datalist) {
				String value = vo.getValue();

				// 過濾有效件, 無效件, 處理中案件狀態
				if (!Constant.ORDER_STATUS_VALID.equals(value) && !Constant.ORDER_STATUS_INVALID.equals(value)
						&& !Constant.ORDER_STATUS_IN_PROCESS.equals(value)) {
					displayList.add(vo);
				}
			}

			return displayList;
		}
    	
    	return Collections.emptyList();
	}

    @Override
    public List<DisplayVO> querySkillDisplay() {
        List<SkillPOJO> dataList = skillWarehouse.findAll();

        if (CollectionUtils.isNotEmpty(dataList)) {
            List<DisplayVO> displayList = new ArrayList<>();

            for (SkillPOJO pojo : dataList) {
                String value = pojo.getSkillId(); // 內存值
                String text = pojo.getSkillName(); // 外顯值

                DisplayVO vo = new DisplayVO(value, text);

                displayList.add(vo);
            }

            return displayList;
        }

        return Collections.emptyList();
    }

    @Override
    public List<DisplayVO> queryRoleTypeDisplay() {
        String sqlText = ResourceFileUtil.SQL_SYSTEM.getResource("role", "QUERY_ROLE_TYPE");

        List<Map<String, Object>> dataList = jdbcDao.queryForList(sqlText);
        
        List<DisplayVO> displayList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(dataList)) {
            for(Map<String, Object> map : dataList) {
                String text = MapUtils.getString(map, "OPTIONS_TEXT");
                String value = MapUtils.getString(map, "OPTIONS_VALUE");
                
                DisplayVO vo = new DisplayVO(value, text);
                
                displayList.add(vo);
            }
            
            return displayList;
        }

        return Collections.emptyList();
    }

	@Override
	public List<DisplayVO> queryProcessReasonDisplay(String flowId, String orderStatus) {
        List<SysFlowReasonMapPOJO> dataList = sysFlowReasonMapWareHouse.findByFlowIdAndOrderStatusAndReasonType(flowId, orderStatus, "RS");

        if (CollectionUtils.isNotEmpty(dataList)) {
            List<DisplayVO> displayList = new ArrayList<>();

            for (SysFlowReasonMapPOJO pojo : dataList) {
                String value = pojo.getReasonId(); // 內存值
                String text = pojo.getReasonName(); // 外顯值

                DisplayVO vo = new DisplayVO(value, text);

                displayList.add(vo);
            }

            return displayList;
        }

        return Collections.emptyList();
	}

	@Override
	public List<DisplayVO> queryProcessResultDisplay(String flowId, String orderStatus) {
        List<SysFlowReasonMapPOJO> dataList = sysFlowReasonMapWareHouse.findByFlowIdAndOrderStatusAndReasonType(flowId, orderStatus, "PR");

        if (CollectionUtils.isNotEmpty(dataList)) {
            List<DisplayVO> displayList = new ArrayList<>();

            for (SysFlowReasonMapPOJO pojo : dataList) {
                String value = pojo.getReasonId(); // 內存值
                String text = pojo.getReasonName(); // 外顯值

                DisplayVO vo = new DisplayVO(value, text);

                displayList.add(vo);
            }

            return displayList;
        }

        return Collections.emptyList();
	}

    @Override
    public List<DisplayVO> queryOptionKpiDate() {
        List<OptionsReferencePOJO> dataList = optionsReferenceWarehouse.findByOptionsType("DAY_TYPE");

        if (CollectionUtils.isNotEmpty(dataList)) {
            List<DisplayVO> displayList = new ArrayList<>();

            for (OptionsReferencePOJO pojo : dataList) {
                String value = pojo.getOptionsValue(); // 內存值
                String text = pojo.getOptionsText(); // 外顯值

                DisplayVO vo = new DisplayVO(value, text);

                displayList.add(vo);
            }

            return displayList;
        }

        return Collections.emptyList();
    }

    @Override
    public List<DisplayVO> queryOptionBeforeCreateDate(String dayTimeType) {
        List<OptionsReferencePOJO> dataList = optionsReferenceWarehouse.findByOptionsType(dayTimeType);

        if (CollectionUtils.isNotEmpty(dataList)) {
            List<DisplayVO> displayList = new ArrayList<>();

            for (OptionsReferencePOJO pojo : dataList) {
                String value = pojo.getOptionsValue(); // 內存值
                String text = pojo.getOptionsText(); // 外顯值

                DisplayVO vo = new DisplayVO(value, text);

                displayList.add(vo);
            }

            return displayList;
        }

        return Collections.emptyList();
    }

    @Override
    public List<DisplayVO> queryOptionStartCountTime() {
        List<OptionsReferencePOJO> dataList = optionsReferenceWarehouse.findByOptionsType("START_COUNT_TIME");

        if (CollectionUtils.isNotEmpty(dataList)) {
            List<DisplayVO> displayList = new ArrayList<>();

            for (OptionsReferencePOJO pojo : dataList) {
                String value = pojo.getOptionsValue(); // 內存值
                String text = pojo.getOptionsText(); // 外顯值

                DisplayVO vo = new DisplayVO(value, text);

                displayList.add(vo);
            }

            return displayList;
        }

        return Collections.emptyList();
    }

}
