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

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.common.util.DateUtil;
import com.fet.crm.osp.platform.core.common.cyptogram.DESHandler;
import com.fet.crm.osp.platform.core.common.cyptogram.NSPCyptogramHandler;
import com.fet.crm.osp.platform.core.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.db.warehouse.OrderDispatchNotifyWarehouse;
import com.fet.crm.osp.platform.core.logger.CoreLoggerFactory;
import com.fet.crm.osp.platform.core.pojo.OrderDispatchNotifyPOJO;
import com.fet.crm.osp.platform.core.service.orderinfo.IOrderReferenceService;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderImageSettingVO;

/**
 * [訂單管理輔助資訊] 服務實作
 * 
 * @author LawrenceLai
 */
@Service
public class OrderReferenceServiceImpl implements IOrderReferenceService {

	@Autowired
	private OrderDispatchNotifyWarehouse dispatchNotifyWarehouse;
	@Autowired
	private JdbcDAO jdbcDAO;
	
	private Logger logger = CoreLoggerFactory.getLogger(OrderReferenceServiceImpl.class);
	
	@Override
	public OrderImageSettingVO queryOrderImageSetting(String orderMId, String userId, String ntAccount) {
		if (StringUtils.isNotBlank(orderMId)) {
			String sqlText = ResourceFileUtil.SQL.getResource("order", "QUERY_ORDER_IMAGE_SETTING");
			
			Map<String, Object> params = new HashMap<>();
			params.put("ORDER_M_ID", orderMId);
			
			Map<String, Object> dataMap = jdbcDAO.queryForMap(sqlText, params);
			
			if (dataMap != null && !dataMap.isEmpty()) {
				String sourceSysId =  MapUtils.getString(dataMap, "SOURCE_SYS_ID");
				String linkType = MapUtils.getString(dataMap, "LINK_TYPE");
				String browser =  MapUtils.getString(dataMap, "BROWSER");
				String link = buildUpLink(dataMap, userId, ntAccount);
				
				OrderImageSettingVO settingVO = new OrderImageSettingVO();
				settingVO.setSourceSysId(sourceSysId);
				settingVO.setLinkType(linkType);
				settingVO.setBrowser(browser);
				settingVO.setLink(link);
				
				return settingVO;
			}
		}
		
		return new OrderImageSettingVO();
	}
	
	@Override
	public boolean dispatchNotify(String userId) {
		if (StringUtils.isNotBlank(userId)) {
			List<OrderDispatchNotifyPOJO> dataList = dispatchNotifyWarehouse.findByProcessUserId(userId);
			
			if (!CollectionUtils.isEmpty(dataList)) {
				Set<String> batchNoSet = new HashSet<>();
				
				try {
					for (OrderDispatchNotifyPOJO dispatchNotifyPOJO : dataList) {
						String batchNo = dispatchNotifyPOJO.getBatchNo();
						
						if (!batchNoSet.contains(batchNo)) {
							dispatchNotifyWarehouse.deleteDispatchNotifyByBatchNoAndProcessUserId(batchNo, userId);

							batchNoSet.add(batchNo);
						}
					}
							
					return true;
				} finally {
					batchNoSet.clear();
					batchNoSet = null;
				}
			}
		}
		
		return false;
	}

	private String buildUpLink(Map<String, Object> dataMap, String userId, String ntAccount) {
		String link = null;
		
		if (dataMap != null && !dataMap.isEmpty()) {
			String linkType = MapUtils.getString(dataMap, "LINK_TYPE", "");
			
			switch (linkType) {
				case "URL":
					link = urlBuilder(dataMap, userId, ntAccount);
					
					break;
				case "FTP":
					link = urlBuilder(dataMap, userId, ntAccount);
					
					break;
				case "WIZZARD":
					link = nspWizzardBuilder(dataMap, userId, ntAccount);
					
					break;
				default:
					link = "";
					
					break;
			}
		}
		
		return link;
	}
	
	private static String urlBuilder(Map<String, Object> dataMap, String userId, String ntAccount) {
		try {
			String link = MapUtils.getString(dataMap, "LINK");
			String parameter = MapUtils.getString(dataMap, "PARAMETER");
			String orderMId = MapUtils.getString(dataMap, "ORDER_M_ID");
			String custId = MapUtils.getString(dataMap, "CUST_ID");
			String imgIdApig = MapUtils.getString(dataMap, "IMG_ID_APID");
			String imgStorePath = MapUtils.getString(dataMap, "IMG_STORE_PATH");
			String srcOrderId = MapUtils.getString(dataMap, "SOURCE_ORDER_ID");
			String encryptionMethod = MapUtils.getString(dataMap, "ENCRYPTION_METHOD");
			String encryptionKey = MapUtils.getString(dataMap, "ENCRYPTION_KEY");
			
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("$P{ORDER_M_ID}", orderMId);
			paramMap.put("$P{EMPNO}", userId);
			paramMap.put("$P{CUST_ID}", custId);
			paramMap.put("$P{NTAccount}", ntAccount);
			paramMap.put("$P{key}", IdentifierIdUtil.getUuid());
			paramMap.put("$P{SYSDATE}", DateUtil.formatDt(new Date(), "YYYYMMDD"));
			paramMap.put("$P{IMG_ID_APID}",imgIdApig);
			paramMap.put("$P{IMG_STORE_PATH}",imgStorePath);
			paramMap.put("$P{SOURCE_ORDER_ID}",srcOrderId);
			
			for (String key : paramMap.keySet()) {
				String value = MapUtils.getString(paramMap, key, "");
				parameter = parameter.replace(key, value);
			}
			
			if (StringUtils.isNotBlank(encryptionMethod)) {
				if ("DES".equals(encryptionMethod)) {
					parameter = DESHandler.getEncrypt(parameter, encryptionKey);
				}
			}
			
			return link + parameter;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}

	private String nspWizzardBuilder(Map<String, Object> dataMap, String userId, String ntAccount) {
		try {
			String link = MapUtils.getString(dataMap, "LINK");
			String token = "jv498Gcdze";
			String date = DateUtil.formatDt(new Date(), "yyyyMMdd");
			String ivrcode = null;
			String rocIdType = null;
			String rocId = MapUtils.getString(dataMap, "$P{CUST_ID}");
			String corpPicTaxId = null;
			String msisdn = MapUtils.getString(dataMap, "$P{MSISDN}");
			String system = "BSOM";
			String imgIdApig = MapUtils.getString(dataMap, "IMG_ID_APID");
			String encryptionKey = MapUtils.getString(dataMap, "ENCRYPTION_KEY");
			
			Map<String, String> funcPara = new HashMap<>();
			funcPara.put("fJobId", imgIdApig);
			funcPara.put("account", ntAccount);
	
			NSPOpenLinkVO nspLnkVO = new NSPOpenLinkVO();
			nspLnkVO.token = token;
			nspLnkVO.date = date;
			nspLnkVO.ivrcode = ivrcode;
			nspLnkVO.account = userId;
			nspLnkVO.rocIdType = rocIdType;
			nspLnkVO.rocId = rocId;
			nspLnkVO.corpPicTaxId = corpPicTaxId;
			nspLnkVO.msisdn = msisdn;
			nspLnkVO.ntAccount = ntAccount;
			nspLnkVO.linkId = "199536";
			nspLnkVO.system = system;
			nspLnkVO.funcPara = funcPara;
	
			String jsonParam = JsonUtil.toJsonWithoutBuilder(nspLnkVO);
			
			NSPCyptogramHandler nspCyptoHandler = new NSPCyptogramHandler(encryptionKey);
			String encryptLink = nspCyptoHandler.desEncrypt(jsonParam);
			
			return link + encryptLink;
		} catch (Exception e) {
			logger.error(" NSP cyptogram handler cause error: ", e);
		}
		
		return "";
	}
	
	/**
	 * NSP 開啟連結資訊 封裝物件
	 * 
	 * @author PaulChen
	 */
	@SuppressWarnings("unused")
	private class NSPOpenLinkVO {

		String key; // BSOM 回寫 Key 值
		String token; // 兩邊需相同的的驗證 ， BSOM 請填jv498Gcdze
		String date; // 日期 YYYYMMDD
		String ivrcode; // 店組代碼
		String account; // 登入帳號
		String rocIdType; // 證照類型
		String rocId; // 證照號碼 / 統編
		String corpPicTaxId; // 公司負責人證照號碼
		String msisdn; // 行動電話 / 代表號
		String ntAccount;
		String linkId; // 功能別 ID
		String system; // 請帶BSOM
		Map<String, String> funcPara; // 功能參數

		NSPOpenLinkVO() {

		}

	}
	
}
