/**
 * 
 */
package com.fet.crm.osp.kernel.core.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.kernel.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.kernel.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.kernel.core.service.system.ISysGlobalConfigService;

/**
 * 系統全域參數 服務實作類別. <br>
 * 
 * @author RichardHuang
 */
@Service
public class SysGlobalConfigServiceImpl implements ISysGlobalConfigService {
	
	@Autowired
    @Qualifier("ospJdbcDAO")
    private JdbcDAO ospJdbcDAO;

	@Override
	public String getSysConfValue(String confId) {
		String sqlText = ResourceFileUtil.SQL_SYSTEM.getResource("GET_SYS_CONFIG_BY_CONF_ID");
		
		Map<String, Object> params = new HashMap<>();
        params.put("CONF_ID", confId);
        
        List<Map<String, Object>> dataList = ospJdbcDAO.queryForList(sqlText, params);
		
		if (CollectionUtils.isNotEmpty(dataList)) {
		    Map<String, Object> dataMap = dataList.get(0);
		    return MapUtils.getString(dataMap, "CONF_VALUE");
		}
		
		return null;
	}

}
