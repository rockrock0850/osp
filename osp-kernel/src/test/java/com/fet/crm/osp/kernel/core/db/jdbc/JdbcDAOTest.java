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

package com.fet.crm.osp.kernel.core.db.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fet.crm.osp.kernel.SpringTest;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.core.common.util.ResourceFileUtil;

/**
 * 測試DAO物件運行類，也可在此測試所需資料邏輯.<br>
 * 
 * @author VJChou
 */
public class JdbcDAOTest extends SpringTest {

    @Autowired
    @Qualifier("ospJdbcDAO")
    private JdbcDAO ospJdbcDAO;
    
    @Test
    public void testQueryForListString() {
        String sqltext = ResourceFileUtil.SQL_ORDER.getResource("GET_ORDER_MAIN_OSP");
        
        List<Map<String, Object>> dataList = ospJdbcDAO.queryForList(sqltext);
        
        if (dataList != null && !dataList.isEmpty()) {
            System.out.println(dataList.size());
        }
    }
    
    @Test
    public void testGetSysConfValue() {
        String sqlText = ResourceFileUtil.SQL_SYSTEM.getResource("GET_SYS_CONFIG_BY_CONF_ID");
        
        Map<String, Object> params = new HashMap<>();
        params.put("CONF_ID", Constant.NCP_QUERY_CUST_INFO_ACTION_URL_CONFIG);
        
        List<Map<String, Object>> dataList = ospJdbcDAO.queryForList(sqlText, params);
        
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (Map<String, Object> dataMap : dataList) {
                String value = MapUtils.getString(dataMap, "CONF_VALUE");
                System.out.println("value = " + value);
            }
        }
    }

}
