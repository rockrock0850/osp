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

package com.fet.crm.osp.platform.core.db.warehouse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.db.model.HrmMember;
import com.fet.crm.osp.platform.core.db.model.HrmMemberId;
import com.fet.crm.osp.platform.core.db.repository.HrmMemberRepository;
import com.fet.crm.osp.platform.core.pojo.HrmMemberPOJO;

/**
 * 
 * @author Adam Yeh
 */
@Component
public class HrmMemberWareHouse {

	@Autowired
	private HrmMemberRepository repository;
	
	public HrmMemberPOJO findByEmpNo(String empNo) {
		if (StringUtils.isBlank(empNo)) {
			return new HrmMemberPOJO();
		}

		HrmMember entity = repository.findByIdEmpno(empNo);

		if (entity == null) {
			return new HrmMemberPOJO();
		}
		
		HrmMemberPOJO pojo = new HrmMemberPOJO();
		
		HrmMemberId id = entity.getId();
		
		BeanUtils.copyProperties(id, pojo);
		BeanUtils.copyProperties(entity, pojo);
		
		return pojo;
	}
	
}
