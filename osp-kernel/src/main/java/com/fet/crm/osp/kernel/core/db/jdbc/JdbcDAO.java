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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.kernel.core.common.LogContext;
import com.fet.crm.osp.kernel.core.common.util.JsonUtil;

/**
 * 透過 Spring JdbcTemplate 實現jdbc封裝，並保留以後的擴充彈性
 * 
 * @author VJChou, RichardHuang
 */
@Transactional
public class JdbcDAO extends NamedParameterJdbcDaoSupport {
	
	// ============================== update
	
	/**
	 * 執行批次作業
	 * 
	 * @param sqlText
	 * @param sqlParam
	 * @return int[]
	 */
	public int[] batchUpdate(String sqlText, List<Map<String, Object>> sqlParam) {
		log(sqlText);
		logParams(sqlParam);
		
		MapSqlParameterSource[] mapParamArr = new MapSqlParameterSource[sqlParam.size()]; 
		for(int i = 0 ; i < sqlParam.size() ; i++) {
			Map<String, ?> m = sqlParam.get(i);
			
			mapParamArr[i] = new MapSqlParameterSource(m);
		}
		
		int[] effCount = getNamedParameterJdbcTemplate().batchUpdate(sqlText, mapParamArr);
		
		return effCount;
	}
	
	/**
	 * 更新資料庫資料
	 *  
	 * @param sqlText
	 * @return int
	 */
	public int update(String sqlText) {
		log(sqlText);
		
		return getJdbcTemplate().update(sqlText);
	}
	
	/**
	 * 更新資料庫資料
	 *  
	 * @param sqlText
	 * @param sqlParams
	 * @return int
	 */
	public int update(String sqlText, Map<String, Object> sqlParams) {
		log(sqlText);
		logParams(sqlParams);
		
		return getNamedParameterJdbcTemplate().update(sqlText, sqlParams);
	}
	
	// ============================== Query 
	
	/**
	 * 根據指定條件查詢並回傳結果
	 * 
	 * @param sqlText
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryForMap(String sqlText) {
		return queryForMap(sqlText, new HashMap<String, Object>());
	}
	
	/**
	 * 根據指定條件查詢並回傳結果
	 * 
	 * @param sqlText
	 * @param params
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryForMap(String sqlText, Map<String, Object> params) {
		log(sqlText);
		logParams(params);
		
		List<Map<String, Object>> dataLs = getNamedParameterJdbcTemplate().queryForList(sqlText, params);
		dataLs = validate(dataLs);
		
		int size = dataLs != null ? dataLs.size() : -1;
		
		if(dataLs != null && dataLs.size() > 1) {
			throw new IncorrectResultSizeDataAccessException(1, size);
		}
		
		if(size == 1) {
			return dataLs.get(0);
		} 
		
		return null;
	}
	
	/**
	 * 查詢並回傳結果
	 * 
	 * @param sqlText
	 * @param clazz
	 * @return List<T>
	 */
	public <T> List<T> queryForBean(String sqlText, Class<T> clazz) {
		log(sqlText);
		
		List<T> dataLs = 
				getNamedParameterJdbcTemplate().query(sqlText, new BeanPropertyRowMapper<T>(clazz));
		
		dataLs = validate(dataLs);
		
		return dataLs;
	}
	
	/**
     * 根據指定條件查詢並回傳結果
     * 
     * @param sqltext
     * @param condition
     * @param clazz
     * @return List<T>
     */
    public <T> List<T> queryForBeanByCondition(String sqlText, String condition, Class<T> clazz) {
        if (sqlText != null && sqlText.length() != 0) {
            if (condition != null && condition.length() != 0) {
                sqlText = sqlText.replace("(1 = 1)", condition);
            }

            log(sqlText);
            
            List<T> dataLs = 
                    getNamedParameterJdbcTemplate().query(sqlText, new BeanPropertyRowMapper<T>(clazz));
            
            dataLs = validate(dataLs);
            
            return dataLs;
        }

        return null;
    }
	
	/**
	 * 根據指定條件查詢並回傳結果
	 * 
	 * @param sqlText
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public <T> List<T> queryForBean(String sqlText, Map<String, Object> params, Class<T> clazz) {
		log(sqlText);
		logParams(params);
		
		List<T> dataLs = 
				getNamedParameterJdbcTemplate().query(sqlText, params, new BeanPropertyRowMapper<T>(clazz));
		
		dataLs = validate(dataLs);
		
		return dataLs;
	}
	
	/**
	 * 根據指定條件查詢並回傳結果
	 * 
	 * @param sqlText
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> queryForList(String sqlText, Map<String, Object> params) {
		List<Map<String, Object>> rtnLs = getNamedParameterJdbcTemplate().queryForList(sqlText, params);
		rtnLs = validate(rtnLs);
		
		if (CollectionUtils.isEmpty(rtnLs)) {
			rtnLs = Collections.emptyList();
		}
		
		return rtnLs;
	}
	
	/**
	 * 根據指定條件查詢並回傳結果
	 * 
	 * @param sqlText
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> queryForList(String sqlText, List<Object> params) {
		return queryForList(sqlText, params.toArray());
	}
	
	/**
	 * 根據指定條件查詢並回傳結果
	 * 
	 * @param sqlText
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> queryForList(String sqlText, Object[] params) {
		log(sqlText);
		logParams(params);
		
		List<Map<String, Object>> rtnLs = getJdbcTemplate().queryForList(sqlText, params);
		rtnLs = validate(rtnLs);
		
		return rtnLs;
	}
	
	/**
	 * 查詢並回傳結果
	 *
	 * @param sqlText
	 * @return List<Map<String, Object>>
	 */
	public <T> List<T> queryForList(String sqlText, Class<T> clazz) {
		log(sqlText);
		
		List<T> rtnLs = getJdbcTemplate().queryForList(sqlText, clazz);
		rtnLs = validate(rtnLs);
		
		if(CollectionUtils.isEmpty(rtnLs)) {
			return Collections.emptyList();
		}
		
		return rtnLs;
	}
	
	/**
	 * 查詢並回傳結果
	 *
	 * @param sqlText
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> queryForList(String sqlText) {
		log(sqlText);
		
		List<Map<String, Object>> rtnLs = getJdbcTemplate().queryForList(sqlText);
		rtnLs = validate(rtnLs);
		
		return rtnLs;
	}
	
	/**
	 * 根據指定條件查詢並回傳結果
	 * 
	 * @param sqltext
	 * @param condition
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> queryForListByCondition(String sqltext, String condition) {
		if (sqltext != null && sqltext.length() != 0) {
			if (condition != null && condition.length() != 0) {
				sqltext = sqltext.replace("(1 = 1)", condition);
			}

			log(sqltext);
			
			List<Map<String, Object>> rtnLs = getJdbcTemplate().queryForList(sqltext);
			rtnLs = validate(rtnLs);
			
			return rtnLs;
		}

		return null;
	}

	// ============================== release resources.
	
	/**
	 * 開啟資料庫連線. (透過DataSource取得DAO的資料庫連線)
	 * 
	 * @return Connection
	 */
	public Connection getNativeConnection() {
		Connection conn = null;

		try {
			conn = getDataSource().getConnection();

			return conn;
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e) {

				private static final long serialVersionUID = 1L;

			};
		}
	}

	/**
	 * 關閉資料庫連線
	 * 
	 * @param conn
	 * @return boolean
	 */
	public boolean closeConnection(Connection conn) {
		try {

			if (conn != null) {
				conn.close();
				return true;
			}
		} catch (SQLException ignore) {
			;
		} finally {
		    ;
		}

		return false;
	}
	
	// ============================== tools method
	
	/**
	 * 對傳入的資料做驗証
	 * 
	 * @param t
	 * @return T
	 */
	private <T> T validate(T t) {
		return t;
	}
	
	/**
	 * 將執行SQL的內容放至 LogContext 物件中
	 * 
	 * @param sqlText
	 */
	private void log(String sqlText) {
		LogContext logCtx = LogContext.getContext();
		logCtx.append("<span style=\"color: #66009D\">" + sqlText + "</span>");
	}
	
	/**
	 * 將執行SQL連結的參數，放至 LogContext 物件中
	 * 
	 * @param args
	 */
	private String paramsPattern = "binding param(%s) value = %s";
	private void logParams(Object[] args) {
		if(args == null || args.length == 0) {
			return;
		}
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < args.length ; i++) {
			if(sb.length() != 0) {
				sb.append(", &nbsp;&nbsp;&nbsp;");
			}
			
			sb.append(String.format(paramsPattern, (i + 1), args[i]));
		}
		
		log(sb.toString());
	}
	
	private void logParams(List<Map<String, Object>> args) {
		for(Map<String, Object> m : args) {
			logParams(m);
		}
	}
	
	/**
	 * 將執行SQL連結的參數，放至 LogContext 物件中
	 * 
	 * @param args
	 */
	private void logParams(Map<String, Object> args) {
		if(args == null || args.isEmpty()) {
			return;
		}
		
		StringBuffer sb = new StringBuffer();
		for(Entry<String, Object> entry : args.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			
			if(sb.length() != 0) {
				sb.append(", &nbsp;&nbsp;&nbsp;");
			}
			
			sb.append(String.format(paramsPattern, key, JsonUtil.toJson(value)));
			
		}
		
		log(sb.toString());
	}

}
