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

package com.fet.crm.osp.kernel.core.service.osp.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.common.util.JsonUtil;
import com.fet.crm.osp.common.util.RestUtil;
import com.fet.crm.osp.common.vo.kernel.result.CustInfoForAppPartRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.SalesInfoVO;
import com.fet.crm.osp.common.vo.kernel.result.agentinfo.AgentInfoRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.agentinfo.GroupDefinitionVO;
import com.fet.crm.osp.common.vo.kernel.result.agentinfo.InternaluseraccountVO;
import com.fet.crm.osp.common.vo.kernel.result.agentinfo.StaffVO;
import com.fet.crm.osp.common.vo.kernel.result.agentinfo.UsersVO;
import com.fet.crm.osp.kernel.core.code.NCPCustDataQueryStatusCode;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.kernel.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.kernel.core.pojo.NCPSystemConfigPOJO;
import com.fet.crm.osp.kernel.core.service.osp.IOSPToolMainService;
import com.fet.crm.osp.kernel.core.service.system.ISysGlobalConfigService;
import com.fet.crm.osp.kernel.mware.exception.DataFormatException;
import com.fet.crm.osp.kernel.mware.exception.HTTPConnectionException;

/**
 * 面向OSP相關服務 實作. <br>
 * 
 * @author VJChou, RichardHuang
 */
@Service
public class OSPToolMainServiceImpl implements IOSPToolMainService {

    @Autowired
    @Qualifier("ospJdbcDAO")
    private JdbcDAO ospJdbcDAO;
    @Autowired
    @Qualifier("nspJdbcDAO")
    private JdbcDAO nspJdbcDAO;
    @Autowired
    @Qualifier("ncpJdbcDAO")
    private JdbcDAO ncpJdbcDAO;
    @Autowired
    private ISysGlobalConfigService sysGlobalConfigService;

    @Override
    public SalesInfoVO querySalesInfo(String empNo) {
        String sqlText = ResourceFileUtil.SQL_SYSTEM.getResource("GET_SALES_INFO");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("empNo", empNo);
        
        List<SalesInfoVO> dataList = ospJdbcDAO.queryForBean(sqlText, params, SalesInfoVO.class);
        
        if (CollectionUtils.isNotEmpty(dataList)) {
            SalesInfoVO salesInfoVO = dataList.get(0);
            return salesInfoVO;
        }
        
        return null;
    }
    
    @Override
    public String getUserIdByIvrCode(String ivrCode) {
        String sqlText = ResourceFileUtil.SQL_ORGANIZATION.getResource("GET_USERID_BY_IVRCODE");
        
        Map<String, Object> params = new HashMap<>();
        params.put("ivrCode", ivrCode);
        
        List<Map<String, Object>> dataList = nspJdbcDAO.queryForList(sqlText, params);
        
        if (CollectionUtils.isNotEmpty(dataList)) {
            Map<String, Object> dataMap = dataList.get(0);
            String userId = MapUtils.getString(dataMap, "USERID");
            return userId;
        }
        
        return null;
    }

    @Override
    public CustInfoForAppPartRtnVO getCustInfoForAppPart(String ownId, String msisdn, String idType, String rocId) {
        String url = sysGlobalConfigService.getSysConfValue(Constant.NCP_QUERY_CUST_INFO_ACTION_URL_CONFIG);
        String requestJson = createQueryCustDataJson(ownId, msisdn, idType, rocId);
        ResponseEntity<String> response = RestUtil.doPostFromJson(url, requestJson);

        HttpStatus responseStatusCode = response.getStatusCode();
        System.out.println("Response statusCode = " + responseStatusCode);
        
        if (!HttpStatus.OK.equals(responseStatusCode)) {
            throw new HTTPConnectionException("無法取得 AppPart 啟動所需客資.");
        }

        String responseBody = response.getBody();
        System.out.println("Response JSON = " + responseBody);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        
        try {
            root = mapper.readTree(responseBody);
            
        } catch (IOException e) {
            throw new DataFormatException("無法解析或處理 NCP 所回傳的 JSON 結果.", e);
        }
        
        JsonNode resultNode = root.get("result");
        JsonNode statusNode = resultNode.get("status");
        String statusValue = statusNode.getTextValue();
        System.out.println("Response status = " + statusValue);
        
        if (StringUtils.equals(statusValue, NCPCustDataQueryStatusCode.SUCCESS.getCode())) {
            JsonNode datasNode = resultNode.get("datas");
            JsonNode idViewInfoDataListNode = datasNode.get("idViewInfoDataList");
            String idViewInfoDataListJson = idViewInfoDataListNode.toString();
            List<CustInfoForAppPartRtnVO> idViewInfoList = JsonUtil.fromJsonToList(idViewInfoDataListJson, CustInfoForAppPartRtnVO.class);
            
            if (CollectionUtils.isNotEmpty(idViewInfoList)) {
                CustInfoForAppPartRtnVO custInfoForAppPartRtnVO = idViewInfoList.get(0);
                System.out.println("Response idViewInfo = " + custInfoForAppPartRtnVO);
                
                return custInfoForAppPartRtnVO;
            }
        }
        
        return null;
    }

    @Override
    public AgentInfoRtnVO getAgentInfo(String accountId) {
        AgentInfoRtnVO agentInfoRtnVO = new AgentInfoRtnVO();
        
        String sqlText = ResourceFileUtil.SQL_ORGANIZATION.getResource("GET_INTERNALUSERACCOUNT_BY_NT_ACCOUNT_ID");
        
        Map<String, Object> params = new HashMap<>();
        params.put("ntAccountId", accountId);
        
        List<InternaluseraccountVO> internaluseraccountList = 
                ncpJdbcDAO.queryForBean(sqlText, params, InternaluseraccountVO.class);
        
        if (CollectionUtils.isNotEmpty(internaluseraccountList)) {
            InternaluseraccountVO internaluseraccountVO = internaluseraccountList.get(0);
            
            if (internaluseraccountVO != null) {
                agentInfoRtnVO.setInternaluseraccountVO(internaluseraccountVO);
                
                sqlText = ResourceFileUtil.SQL_ORGANIZATION.getResource("GET_STAFF_BY_NT_ACCOUNT_ID");
                List<StaffVO> staffList = ncpJdbcDAO.queryForBean(sqlText, params, StaffVO.class);
                
                if (CollectionUtils.isNotEmpty(staffList)) {
                    StaffVO staffVO = staffList.get(0);
                    
                    if (staffVO != null) {
                        agentInfoRtnVO.setStaffVO(staffVO);
                        
                        sqlText = ResourceFileUtil.SQL_ORGANIZATION.getResource("GET_GROUP_DEFINITION_BY_GROUP_NUMBER");
                        
                        params = new HashMap<>();
                        params.put("groupNumber", staffVO.getGroupNumber());
                        
                        List<GroupDefinitionVO> groupDefinitionList = 
                                ncpJdbcDAO.queryForBean(sqlText, params, GroupDefinitionVO.class);
                        
                        if (CollectionUtils.isNotEmpty(groupDefinitionList)) {
                            agentInfoRtnVO.setGroupDefinitionVO(groupDefinitionList.get(0));
                        }
                        
                        sqlText = ResourceFileUtil.SQL_ORGANIZATION.getResource("GET_USERS_BY_ULOGIN_NAME");
                        
                        params = new HashMap<>();
                        params.put("amdocsId", staffVO.getAmdocsId());
                        
                        List<UsersVO> usersList = 
                                ncpJdbcDAO.queryForBean(sqlText, params, UsersVO.class);
                        
                        if (CollectionUtils.isNotEmpty(usersList)) {
                            UsersVO usersVO = usersList.get(0);
                            agentInfoRtnVO.setUsersVO(usersVO);
                            
                            sqlText = ResourceFileUtil.SQL_ORGANIZATION.getResource("GET_CHANNEL_GROUP_ID_BY_USER_ID");
                            
                            params = new HashMap<>();
                            params.put("userEmplId", usersVO.getUserEmplId());
                            
                            String channelGroupId = "00001";
                            StringBuilder builder = new StringBuilder();
                            
                            List<Map<String, Object>> channelGroupIdList = nspJdbcDAO.queryForList(sqlText, params);
                            
                            if (CollectionUtils.isNotEmpty(channelGroupIdList)) {
                                for (Map<String, Object> dataMap : channelGroupIdList) {
                                    String tempChannelGroupId = MapUtils.getString(dataMap, "CHANNEL_GROUP_ID");
                                    
                                    if (StringUtils.isNotBlank(tempChannelGroupId)) {
                                        tempChannelGroupId = StringUtils.substring(tempChannelGroupId, 1, tempChannelGroupId.length() - 1);
                                        
                                        if (StringUtils.isNotBlank(tempChannelGroupId)) {
                                            builder.append(tempChannelGroupId);
                                            builder.append("|");
                                        }
                                    }
                                }
                                
                                String builderStr = builder.toString();
                                
                                if(StringUtils.isNotBlank(builderStr)){
                                    channelGroupId = builderStr.trim();
                                    channelGroupId = StringUtils.substring(channelGroupId, 0, channelGroupId.length() - 1);
                                }
                            }
                            
                            agentInfoRtnVO.setChannelGroupId(channelGroupId);
                        }
                    }
                }
            }
        }
        
        sqlText = ResourceFileUtil.SQL_ORGANIZATION.getResource("GET_SYSTEM_CONFIG_BY_CONFIG_NAME");
        
        String delimiter = "','";
        StringBuffer condition = new StringBuffer("CONFIG_NAME IN ('");
        condition.append(Constant.CACHE_CHANNEL).append(delimiter).append(Constant.CACHE_CHANNEL_POSTPAID);
        condition.append(Constant.CACHE_CHANNEL_ID_POSTPAID).append(delimiter).append(Constant.CACHE_CHANNEL_ID_PASSWORD_POSTPAID);
        condition.append(Constant.CACHE_CHANNEL_ID_PREPAID).append(delimiter).append(Constant.CACHE_CHANNEL_ID_PASSWORD_PREPAID);
        condition.append(Constant.CACHE_CHANNEL_PREPAID).append(delimiter).append(Constant.IVRCODE).append("')");
        
        List<NCPSystemConfigPOJO> ncpSystemConfigList = 
                ncpJdbcDAO.queryForBeanByCondition(sqlText, condition.toString(), NCPSystemConfigPOJO.class);
        
        if (CollectionUtils.isNotEmpty(internaluseraccountList)) {
            for (NCPSystemConfigPOJO ncpSystemConfig : ncpSystemConfigList) {
                String configName = ncpSystemConfig.getConfigName();
                String configValue = ncpSystemConfig.getConfigValue();
                
                if (Constant.CACHE_CHANNEL.equals(configName)) {
                    agentInfoRtnVO.setCacheChannel(configValue);
                    
                } else if (Constant.CACHE_CHANNEL_POSTPAID.equals(configName)) {
                    agentInfoRtnVO.setCacheChannelPostpaid(configValue);
                    
                } else if (Constant.CACHE_CHANNEL_ID_POSTPAID.equals(configName)) {
                    agentInfoRtnVO.setCacheChannelIdPostpaid(configValue);
                    
                } else if (Constant.CACHE_CHANNEL_ID_PASSWORD_POSTPAID.equals(configName)) {
                    agentInfoRtnVO.setCacheChannelIdPasswordPostpaid(configValue);
                    
                } else if (Constant.CACHE_CHANNEL_ID_PREPAID.equals(configName)) {
                    agentInfoRtnVO.setCacheChannelIdPrepaid(configValue);
                    
                } else if (Constant.CACHE_CHANNEL_ID_PASSWORD_PREPAID.equals(configName)) {
                    agentInfoRtnVO.setCacheChannelIdPasswordPrepaid(configValue);
                    
                } else if (Constant.CACHE_CHANNEL_PREPAID.equals(configName)) {
                    agentInfoRtnVO.setCacheChannelPrepaid(configValue);
                    
                } else if (Constant.IVRCODE.equals(configName)) {
                    agentInfoRtnVO.setCacheChannel(configValue);
                }
            }
        }
        
        return agentInfoRtnVO;
    }
    
    @Override
    public boolean updateTxIdByIdentifyId(String ospKey, String txId) {
        String sqlText = ResourceFileUtil.SQL_SYSTEM.getResource("UPDATE_TXID_BY_IDENTIFY_ID");
        
        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put("ospKey", ospKey);
        sqlParams.put("txId", txId);
        
        int affectedNum = ospJdbcDAO.update(sqlText, sqlParams);
        
        return (affectedNum > 0);
    }
    
    // ========= 工具程式 ==========
    
    /*
     * create request parameter of "NSP2/ncpCustDataQuery/queryCustData.action"
     */
    private String createQueryCustDataJson(String ownId, String msisdn, String idType, String rocId) {
        HashMap<String, Object> custDataQuery = new HashMap<String, Object>();
        List<Map<String, Object>> idViewInfoDataList = new ArrayList<Map<String, Object>>();
        Map<String, Object> idViewInfoData = new HashMap<String, Object>();
        List<Map<String, Object>> fetnetInfoDataList = new ArrayList<Map<String, Object>>();
        Map<String, Object> fetnetInfoData = new HashMap<String, Object>();
        List<Map<String, Object>> nonSimInfoDataList = new ArrayList<Map<String, Object>>();
        Map<String, Object> nonSimInfoData = new HashMap<String, Object>();
        
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("custDataQuery", custDataQuery);
        param.put("ownId", ownId);
        
        custDataQuery.put("idViewInfoDataList", idViewInfoDataList);
        custDataQuery.put("fetnetInfoDataList", fetnetInfoDataList);
        custDataQuery.put("nonSimInfoDataList", nonSimInfoDataList);
        custDataQuery.put("deputyIvrcode", null);
        custDataQuery.put("bAllAccount", "");
        custDataQuery.put("bMsisdn", "");
        custDataQuery.put("searchType", "getIdViewInfo");
        
        idViewInfoDataList.add(idViewInfoData);
        idViewInfoData.put("msisdn", msisdn);
        idViewInfoData.put("idType", idType);
        idViewInfoData.put("rocId", rocId);
//        idViewInfoData.put("sim", "");
//        idViewInfoData.put("acctId", "");
        
        fetnetInfoDataList.add(fetnetInfoData);
        fetnetInfoData.put("fetnetId", "");
        
        nonSimInfoDataList.add(nonSimInfoData);
        nonSimInfoData.put("searchPhone", "");
        nonSimInfoData.put("searchEmail", "");
        nonSimInfoData.put("phoneContactType", "");
        
        String requestJson = JsonUtil.toJson(param);
        System.out.println("requestJson = \n" + requestJson);
        
        return requestJson;
    }

}
