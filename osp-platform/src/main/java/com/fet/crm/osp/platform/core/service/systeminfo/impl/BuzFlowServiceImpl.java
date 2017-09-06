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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.common.Constant;
import com.fet.crm.osp.platform.core.common.cyptogram.DESHandler;
import com.fet.crm.osp.platform.core.common.cyptogram.NSPCyptogramHandler;
import com.fet.crm.osp.platform.core.common.util.DateUtil;
import com.fet.crm.osp.platform.core.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.logger.CoreLoggerFactory;
import com.fet.crm.osp.platform.core.service.systeminfo.IBuzFlowService;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderInfoVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderProcessVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.UserInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.BuzFlowVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzFlowStepVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzHierarchyEstablishmentVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzRecordContentVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzRecordRoutineVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzStepPageVO;

/**
 * 「頁面資訊」實作類別
 * 
 * @author PaulChen, AllenChen, Adam Yeh
 */
@Service
public class BuzFlowServiceImpl implements IBuzFlowService {
	
	private Logger logger = CoreLoggerFactory.getLogger(BuzFlowServiceImpl.class);

	@Autowired
	private JdbcDAO jdbcDAO;

	private Map<String, String> nspLinkMap = new HashMap<>();

	public BuzFlowServiceImpl() {
		// init nspLinkMap values.
		nspLinkMap.put("CONT0032", "199477");
		nspLinkMap.put("CONT0033", "199483");
		nspLinkMap.put("CONT0034", "199481");
		nspLinkMap.put("CONT0035", "199486");
		nspLinkMap.put("CONT0036", "199489");
		nspLinkMap.put("CONT0037", "199493");
		nspLinkMap.put("CONT0038", "199501");
		nspLinkMap.put("CONT0039", "199500");
		nspLinkMap.put("CONT0044", "199531");
		nspLinkMap.put("CONT0045", "199535");
		nspLinkMap.put("CONT0046", "199558");
		nspLinkMap.put("CONT0047", "199559");
		nspLinkMap.put("CONT0048", "199537");
		nspLinkMap.put("CONT0049", "199506");
		nspLinkMap.put("CONT0072", "199538");
	}

	@Override
	public BuzFlowVO getServiceBuzFlow(String flowId) {
		String sqlTxt = ResourceFileUtil.SQL.getResource("system", "pageflow", "GET_FLOW_STEP");

		Map<String, Object> sqlParamMP = new HashMap<>();
		sqlParamMP.put("FLOW_ID", flowId);

		List<Map<String, Object>> dataLs = jdbcDAO.queryForList(sqlTxt, sqlParamMP);

		// 轉為封裝物件
		BuzFlowVO rtnVO = null;
		String flowNm = null;
		String btnTmpSave = null;
		String btnTmpLeave = null;
		String btnSuccess = null;
		String btnFail = null;
		String btnShowMessage = null;
		String btnReply = null;
		String btnShowSourceDoc = null;
		String btnChangeOrderType = null;

		if (CollectionUtils.isEmpty(dataLs) == false) {
			List<BuzFlowStepVO> buzFlowStepLs = new ArrayList<>();
			for (Map<String, Object> mp : dataLs) {
				String stepId = MapUtils.getString(mp, "STEP_ID");
				String stepNm = MapUtils.getString(mp, "STEP_NAME");
				String sortSeq = MapUtils.getString(mp, "SORT_SEQUENCE");

				btnTmpSave = MapUtils.getString(mp, "BTN_TEMP_SAVE");
				btnTmpLeave = MapUtils.getString(mp, "BTN_TEMP_LEAVE");
				btnSuccess = MapUtils.getString(mp, "BTN_SUCCESS");
				btnFail = MapUtils.getString(mp, "BTN_FAIL");
				btnShowMessage = MapUtils.getString(mp, "BTN_SHOW_MESSAGE");
				btnReply = MapUtils.getString(mp, "BTN_SYS_REPLY");
				btnShowSourceDoc = MapUtils.getString(mp, "BTN_SHOW_SOURCE_DOC");
				btnChangeOrderType = MapUtils.getString(mp, "BTN_CHANGE_ORDER_TYPE");
				flowNm = MapUtils.getString(mp, "FLOW_NAME");

				BuzFlowStepVO stepVO = new BuzFlowStepVO();
				stepVO.setStepId(stepId);
				stepVO.setStepNm(stepNm);
				stepVO.setSortSequence(sortSeq);

				buzFlowStepLs.add(stepVO);
			}

			rtnVO = new BuzFlowVO();
			rtnVO.setFlowId(flowId);
			rtnVO.setFlowNm(flowNm);
			rtnVO.setBtnFail(btnFail);
			rtnVO.setBtnReply(btnReply);
			rtnVO.setBtnShowMessage(btnShowMessage);
			rtnVO.setBtnShowSourceDoc(btnShowSourceDoc);
			rtnVO.setBtnSuccess(btnSuccess);
			rtnVO.setBtnTmpLeave(btnTmpLeave);
			rtnVO.setBtnTmpSave(btnTmpSave);
			rtnVO.setBuzFlowStepLs(buzFlowStepLs);
			rtnVO.setBtnChangeOrderType(btnChangeOrderType);
		}

		return rtnVO;
	}

	@Override
	public List<BuzStepPageVO> getServiceBuzStep(String flowId, String stepId, OrderInfoVO orderInfoVO,
			UserInfoVO userInfoVO, String subscriberId, String accountId) {
		String sqlTxt = ResourceFileUtil.SQL.getResource("system", "pageflow", "GET_FLOW_PAGE_STEP");

		Map<String, Object> sqlParamMP = new HashMap<>();
		sqlParamMP.put("FLOW_ID", flowId);
		sqlParamMP.put("STEP_ID", stepId);

		List<Map<String, Object>> dataLs = jdbcDAO.queryForList(sqlTxt, sqlParamMP);
		Map<String, String> matchMap = getMatchMap(orderInfoVO, userInfoVO, subscriberId, accountId);

		List<BuzStepPageVO> rtnLs = new ArrayList<>();
		if (CollectionUtils.isEmpty(dataLs) == false) {
			for (Map<String, Object> dataMap : dataLs) {
				String contentId = MapUtils.getString(dataMap, "CONTENT_ID");
				String contentNm = MapUtils.getString(dataMap, "CONTENT_NAME"); // 區塊名稱
				String sortSequence = MapUtils.getString(dataMap, "SORT_SEQUENCE"); // 排序
				String template = MapUtils.getString(dataMap, "TEMPLATE_TYPE"); // 樣版名稱（INCLUDE_TYPE）
				String link = MapUtils.getString(dataMap, "LINK"); // 連結
				String openBrowser = MapUtils.getString(dataMap, "OPEN_BROWSER"); // 開啟瀏覽器
				String replacement = MapUtils.getString(dataMap, "PARAMETER"); // 取得未置換入真實資料的請求參數
				String httpMethod = MapUtils.getString(dataMap, "HTTP_METHOD");
				String parameter = getPerfectParameterString(replacement, matchMap, dataMap);// 取得含有真實資料的請求參數
				String ospKey = MapUtils.getString(matchMap, "$P{OSPkey}");

				BuzStepPageVO stepPageVO = new BuzStepPageVO();
				stepPageVO.setContentId(contentId);
				stepPageVO.setContentNm(contentNm);
				stepPageVO.setLink(link);
				stepPageVO.setOpenBrowser(openBrowser);
				stepPageVO.setSortSequence(sortSequence);
				stepPageVO.setTemplate(template);
				stepPageVO.setStepId(stepId);
				stepPageVO.setParameter(parameter);
				stepPageVO.setHttpMethod(httpMethod);
				stepPageVO.setOspKey(ospKey);

				rtnLs.add(stepPageVO);
			}
		}

		return rtnLs;
	}

	@Override
	public List<BuzStepPageVO> getServiceBuzStepForFunction(String menuId, UserInfoVO userInfoVO) {
		String sqlTxt = ResourceFileUtil.SQL.getResource("system", "pageflow", "GET_FLOW_PAGE_STEP_FOR_FUNCTION");

		Map<String, Object> sqlParamMP = new HashMap<>();
		sqlParamMP.put("MENU_ID", menuId);

		List<Map<String, Object>> dataLs = jdbcDAO.queryForList(sqlTxt, sqlParamMP);

		Map<String, String> matchMap = getMatchMap(userInfoVO);

		List<BuzStepPageVO> rtnLs = new ArrayList<>();
		if (CollectionUtils.isEmpty(dataLs) == false) {
			for (Map<String, Object> dataMap : dataLs) {
				String contentId = MapUtils.getString(dataMap, "CONTENT_ID");
				String contentNm = MapUtils.getString(dataMap, "CONTENT_NAME"); // 區塊名稱
				String template = MapUtils.getString(dataMap, "TEMPLATE_TYPE"); // 樣版名稱（INCLUDE_TYPE）
				String link = MapUtils.getString(dataMap, "LINK"); // 連結
				String openBrowser = MapUtils.getString(dataMap, "OPEN_BROWSER"); // 開啟瀏覽器
				String replacement = MapUtils.getString(dataMap, "PARAMETER"); // 取得未置換入真實資料的請求參數
				String httpMethod = MapUtils.getString(dataMap, "HTTP_METHOD");
				String parameter = getPerfectParameterString(replacement, matchMap, dataMap);// 取得含有真實資料的請求參數
				String ospKey = MapUtils.getString(matchMap, "$P{OSPkey}");

				BuzStepPageVO stepPageVO = new BuzStepPageVO();
				stepPageVO.setContentId(contentId);
				stepPageVO.setContentNm(contentNm);
				stepPageVO.setLink(link);
				stepPageVO.setOpenBrowser(openBrowser);
				stepPageVO.setTemplate(template);
				stepPageVO.setParameter(parameter);
				stepPageVO.setHttpMethod(httpMethod);
				stepPageVO.setOspKey(ospKey);

				rtnLs.add(stepPageVO);
			}
		}

		return rtnLs;
	}

	// 取得 參數識別符號/真實參數資料 的Map物件
	private Map<String, String> getMatchMap(OrderInfoVO orderInfoVO, UserInfoVO userInfoVO, String subscriberId, String accountId) {
		if (userInfoVO == null || orderInfoVO == null) {
			return Collections.emptyMap();
		}
		
		String ospKey = IdentifierIdUtil.getUuid();

		Map<String, String> map = new HashMap<>();
		
		// User Information
		map.put("$P{NTAccount}", userInfoVO.getNtAccount());
		map.put("$P{EMPNO}", userInfoVO.getUserId());
		
		// Subscriber Information
		map.put("$P{subscrId}", subscriberId);
		map.put("$P{account_id}", accountId);
		
		// Order Information
		map.put("$P{CUST_ID}", orderInfoVO.getCustId());
		map.put("$P{CUST_TYPE}", orderInfoVO.getCustType());
		map.put("$P{MSISDN}", orderInfoVO.getMsisdn());
		map.put("$P{PromotionID}", orderInfoVO.getPromotionId());
		map.put("$P{ivrcode}", orderInfoVO.getIvrCode());
		map.put("$P{SOURCE_ORDER_ID}", orderInfoVO.getSourceOrderId());
		map.put("$P{CORP_PIC_TAXID}", orderInfoVO.getCorpPicTaxid());
		map.put("$P{IMG_ID_APID}", orderInfoVO.getImgIdApid());
		map.put("$P{CID}", orderInfoVO.getCid());
		
		// System Information
		map.put("$P{SYSDATE}", DateUtil.fromDate(new Date()));
		map.put("$P{TimeSeed}", DateUtil.getMillisTime());
		map.put("$P{OSPkey}", ospKey);

		return map;
	}

	// 取得 參數識別符號/真實參數資料 的Map物件
	private Map<String, String> getMatchMap(UserInfoVO userInfoVO) {
		String ospKey = IdentifierIdUtil.getUuid();
		
		Map<String, String> map = new HashMap<>();

		// User Information
		map.put("$P{NTAccount}", userInfoVO.getNtAccount());
		map.put("$P{EMPNO}", userInfoVO.getUserId());

		// System Information
		map.put("$P{SYSDATE}", DateUtil.fromDate(new Date()));
		map.put("$P{TimeSeed}", DateUtil.getMillisTime());
		map.put("$P{OSPkey}", ospKey);

		return map;
	}

	@Override
	public List<BuzRecordContentVO> getServiceBuzRecordContent(String contentId, String orderMId) {
		String sqlTxt = ResourceFileUtil.SQL.getResource("system", "pageflow", "GET_STEP_RECORD_CONTENT");

		Map<String, Object> params = new HashMap<>();
		params.put("CONTENT_ID", contentId);
		params.put("ORDER_M_ID", orderMId);

		List<BuzRecordContentVO> dataList = jdbcDAO.queryForList(sqlTxt, params, BuzRecordContentVO.class);

		if (CollectionUtils.isNotEmpty(dataList)) {
			return dataList;
		}

		return Collections.emptyList();
	}

	@Override
	public List<BuzRecordRoutineVO> getServiceBuzRecordRoutine(String contentId, String userId) {
		String sqlTxt = ResourceFileUtil.SQL.getResource("system", "pageflow", "GET_STEP_RECORD_ROUTINE");

		Map<String, Object> params = new HashMap<>();
		params.put("CONTENT_ID", contentId);
		params.put("PROCESS_USER_ID", userId);

		List<BuzRecordRoutineVO> dataList = jdbcDAO.queryForList(sqlTxt, params, BuzRecordRoutineVO.class);

		if (CollectionUtils.isNotEmpty(dataList)) {
			return dataList;
		}

		return Collections.emptyList();
	}

	@Override
	public List<BuzHierarchyEstablishmentVO> getServiceCreateIaLevel(String contentId, String empNo) {
		String sqlTxt = ResourceFileUtil.SQL.getResource("system", "pageflow", "GET_HIERARCHY_ESTABLISHMENT_INFO");

		Map<String, Object> params = new HashMap<>();
		params.put("CONTENT_ID", contentId);

		List<BuzHierarchyEstablishmentVO> dataList = jdbcDAO.queryForList(sqlTxt, params,
				BuzHierarchyEstablishmentVO.class);

		if (CollectionUtils.isNotEmpty(dataList)) {
			replacePara(dataList, empNo);

			return dataList;
		}

		return Collections.emptyList();
	}

	@Override
	public String getFlowId(OrderProcessVO orderProcessVO, OrderInfoVO infoVO) {
		if (orderProcessVO == null || infoVO == null) {
			return "";
		}

		String sqlTxt = ResourceFileUtil.SQL.getResource("system", "pageflow",
				"GET_FLOW_ID_BY_ORDER_TYPE_ID_AND_SOURCE_SYS_ID");
		String orderTypeId = orderProcessVO.getOrderTypeId();
		String sourceSysId = infoVO.getSourceSysId();

		Map<String, Object> params = new HashMap<>();
		params.put("ORDER_TYPE_ID", orderTypeId);
		params.put("SOURCE_SYS_ID", sourceSysId);

		Map<String, Object> map = jdbcDAO.queryForMap(sqlTxt, params);

		if (MapUtils.isEmpty(map)) {
			return "";
		}

		String flowId = MapUtils.getString(map, "FLOW_ID", "");

		return flowId;
	}

	// 將識別符號替換成ParameterVO內的真實資料
	private String getPerfectParameterString(String text, Map<String, String> paramMap, Map<String, Object> dataMap) {
		String surrounding = MapUtils.getString(dataMap, "SURROUNDING", "");

		if (MapUtils.isEmpty(paramMap) || StringUtils.isBlank(text)) {
			return text;
		}

		String actureURL = null;
		switch (surrounding) {
			case Constant.SURROUNDING_NSP :
				actureURL = getNSPUrlLink(paramMap, dataMap);
	
				break;
			case  Constant.SURROUNDING_MVC :
				actureURL = getMCVUrlLink(paramMap, dataMap);
				
				break;
			default:
				actureURL = replaceStr(text, paramMap, dataMap);
	
				break;
		}

		actureURL = encrypt(actureURL, dataMap);
		return actureURL;
	}

	/**
	 * 取得 NSP 連線URL
	 * 
	 * @param paramMap
	 *            參數集合物件
	 * @param dataMap
	 *            資料庫資訊集合物件
	 * @return String
	 */
	private String getNSPUrlLink(Map<String, String> paramMap, Map<String, Object> dataMap) {
		String ospKey = MapUtils.getString(dataMap, "OSPkey");
		String contentId = MapUtils.getString(dataMap, "CONTENT_ID");
		String token = "jv498Gcdze";
		String date = DateUtil.fromDate(new Date(), "yyyyMMdd");
		String ivrcode = null;
		String account = MapUtils.getString(paramMap, "$P{EMPNO}");
		String rocIdType = null;
		String rocId = MapUtils.getString(paramMap, "$P{CUST_ID}");
		String corpPicTaxId = null;
		String msisdn = MapUtils.getString(paramMap, "$P{MSISDN}");
		String ntAccount = MapUtils.getString(paramMap, "$P{NTAccount}");
		String linkId = MapUtils.getString(nspLinkMap, contentId);
		String system = "BSOM";
		// String imgId = MapUtils.getString(paramMap, "$P{IMG_ID_APID}");

		NSPOpenLinkVO nspLnkVO = new NSPOpenLinkVO();
		nspLnkVO.key = ospKey;
		nspLnkVO.token = token;
		nspLnkVO.date = date;
		nspLnkVO.ivrcode = ivrcode;
		nspLnkVO.account = account;
		nspLnkVO.rocIdType = rocIdType;
		nspLnkVO.rocId = rocId;
		nspLnkVO.corpPicTaxId = corpPicTaxId;
		nspLnkVO.msisdn = msisdn;
		nspLnkVO.ntAccount = ntAccount;
		nspLnkVO.linkId = linkId;
		nspLnkVO.system = system;
		nspLnkVO.funcPara = null;

		return JsonUtil.toJsonWithoutBuilder(nspLnkVO);
	}
	
	private String getMCVUrlLink(Map<String, String> paramMap, Map<String, Object> dataMap) {
		String urlTemplate = MapUtils.getString(dataMap, "PARAMETER");
		String cid = MapUtils.getString(paramMap, "$P{CID}", "8888");
		
		String url = urlTemplate.replace("$P{CID}", cid);
		
		return url;
	}

	/**
	 * 依據資料庫設定，判斷是否需要做加密。 若否則直接回傳連結
	 * 
	 * @param link
	 *            明碼連結
	 * @param dataMp
	 *            資料庫資訊集合物件
	 * @return
	 */
	private String encrypt(String link, Map<String, Object> dataMp) {
		String method = MapUtils.getString(dataMp, "ENCRYPTION_METHOD");
		String key = MapUtils.getString(dataMp, "ENCRYPTION_KEY");
		// String surrounding = MapUtils.getString(dataMp, "SURROUNDING");
		
		// 若加密方式為空時，則直接回傳連結
		if (StringUtils.isBlank(method)) {
			return link;
		}
		
		String encryptLink;

		try {
			switch (method) {
			case Constant.ENCRYPTION_METHOD_DES:
				encryptLink = DESHandler.getEncrypt(link, key);
				
				logger.info(" ==================== desCyptoHandler ===================== ");
				logger.info(encryptLink);
				logger.info(" ==================== desCyptoHandler ===================== ");

				return encryptLink;
			case Constant.ENCRYPTION_METHOD_DES_NSP:

				NSPCyptogramHandler nspCyptoHandler = new NSPCyptogramHandler(key);
				encryptLink = nspCyptoHandler.desEncrypt(link);

				logger.info(" ==================== nspCyptoHandler ===================== ");
				logger.info(encryptLink);
				logger.info(" ==================== nspCyptoHandler ===================== ");

				return encryptLink;
			default:
				// nop...
			}
		} catch (Exception e) {
			logger.error(" cyptogram handler cause error: ", e);
		}

		return link;
	}
	
	/**
	 * 將參數置換成實際的數值
	 * 
	 * @param template
	 *            樣版
	 * @param values
	 *            參數名稱及參數值對映物件
	 * @param dataMp
	 *            資料庫資訊集合物件
	 * @return String
	 */
	private String replaceStr(String template, Map<String, String> values, Map<String, Object> dataMp) {
		StringBuilder sb = new StringBuilder(template);

		for (Entry<String, String> entry : values.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			int start = sb.indexOf(key);
			while (start != -1) {
				int end = key.length() + start;
				if (value == null) {
					value = "";
				}

				sb.replace(start, end, value);

				// 再次搜找是否有相同的token
				start = sb.indexOf(key);
			}
		}

		return sb.toString();
	}
	
	private void replacePara(List<BuzHierarchyEstablishmentVO> dataList, String empNo) {
		String TimeSeed = String.valueOf(new Date().getTime());

		for (BuzHierarchyEstablishmentVO vo : dataList) {
			String itemId = vo.getItemId();
			String params = vo.getReserv3();

			params = params.replace("$P{EMPNO}", empNo);
			params = params.replace("$P{TimeSeed}", TimeSeed);
			
			if ("LEV005".equals(itemId)) {
				try {
					String encryptUrl =  DESHandler.getEncrypt(params, "eP9Mz7sq");
					params = encryptUrl;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			vo.setReserv3(params);
		}
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