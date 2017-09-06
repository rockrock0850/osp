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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.db.warehouse.HrmMemberWareHouse;
import com.fet.crm.osp.platform.core.db.warehouse.ShiftContentWarehouse;
import com.fet.crm.osp.platform.core.pojo.HrmMemberPOJO;
import com.fet.crm.osp.platform.core.pojo.ShiftContentPOJO;
import com.fet.crm.osp.platform.core.service.systeminfo.IHRManageService;
import com.fet.crm.osp.platform.core.vo.orderinfo.CreationManageVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.MemberInfoVO;

/**
 * 「人力資源管理」實作類別
 * 
 * @author LawrenceLai, Adam Yeh
 */
@Service
public class HRManageServiceImpl implements IHRManageService {
	
	@Autowired
	private JdbcDAO jdbcDAO;
	@Autowired
	private ShiftContentWarehouse shiftContentWarehouse;
	@Autowired
	private HrmMemberWareHouse hrmMemberWareHouse;

	@Override
	public CreationManageVO queryCreationManageByUserId(String userId) {
		HrmMemberPOJO pojo = hrmMemberWareHouse.findByEmpNo(userId);

		if (pojo == null) {
			return new CreationManageVO();
		}
		
		CreationManageVO vo = new CreationManageVO();
		BeanUtils.copyProperties(pojo, vo);
		
		return vo;
	}

	@Override
	public List<MemberInfoVO> queryMemberInfoBy7331Team() {
		String sqlText = ResourceFileUtil.SQL.getResource("system", "hrm", "QUERY_MEMBER_BY_7331_TEAM");
		
		List<MemberInfoVO> dataList = jdbcDAO.queryForList(sqlText, MemberInfoVO.class);
		
		if (!CollectionUtils.isEmpty(dataList)) {
			return dataList;
		}
		
		return Collections.emptyList();
	}

	@Override
	public List<MemberInfoVO> queryMemberInfoBy7331TeamWithoutBeSettedRoleMember(String roleId) {
		String sqlText = ResourceFileUtil.SQL.getResource("system", "hrm", "QUERY_MEMBER_BY_7331_TEAM_WITHOUT_SETTED_ROLE_MEMBER");

		Map<String, Object> params = new HashMap<>();
		params.put("ROLE_ID", roleId);

		List<MemberInfoVO> dataList = jdbcDAO.queryForList(sqlText, params, MemberInfoVO.class);

		if (!CollectionUtils.isEmpty(dataList)) {
			return dataList;
		}

		return Collections.emptyList();
	}

	@Override
	public String queryShiftTypeByUserIdAndSysDate(String userId) {
		if (StringUtils.isNotBlank(userId)) {
			ShiftContentPOJO pojo = shiftContentWarehouse.findByEmpNoAndSysDate(userId);
			
			if (pojo != null) {
				return pojo.getShiftTypeId();
			}
		}
		
		return "";
	}

	@Override
	public List<MemberInfoVO> queryMemberInfoByLeaderEmpNo(String userId) {
		if (StringUtils.isNotBlank(userId)) {
			String sqlText = ResourceFileUtil.SQL.getResource("system", "hrm", "QUERY_MEMBER_BY_LEADER_EMPNO");
			
			Map<String, Object> params = new HashMap<>();
			params.put("USER_ID", userId);
			
			List<MemberInfoVO> dataList = jdbcDAO.queryForList(sqlText, params, MemberInfoVO.class);
			
			if (!CollectionUtils.isEmpty(dataList)) {
				return dataList;
			}
		}
		
		return Collections.emptyList();
	}

	@Override
	public MemberInfoVO queryMemberInfoByUserId(String userId) {
		if (StringUtils.isNotBlank(userId)) {
			String sqlText = ResourceFileUtil.SQL.getResource("system", "hrm", "QUERY_MEMBER_BY_EMP_NO");
			
			Map<String, Object> params = new HashMap<>();
			params.put("USER_ID", userId);
			
			List<MemberInfoVO> dataList = jdbcDAO.queryForList(sqlText, params, MemberInfoVO.class);
			
			if (!CollectionUtils.isEmpty(dataList)) {
				return dataList.get(0);
			}
		}
		
		return new MemberInfoVO();
	}
	
}