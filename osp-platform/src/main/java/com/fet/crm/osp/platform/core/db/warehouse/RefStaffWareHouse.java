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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.db.model.RefStaff;
import com.fet.crm.osp.platform.core.db.model.RefStaffId;
import com.fet.crm.osp.platform.core.db.repository.RefStaffRepository;
import com.fet.crm.osp.platform.core.pojo.RefStaffPOJO;

/**
 * NotifyMainWareHouse 倉庫
 *
 * @author Adam Yeh
 */
@Component
public class RefStaffWareHouse {

    @Autowired
    private RefStaffRepository repository;

	public List<RefStaffPOJO> findByIdempno(String userId) {
		if (StringUtils.isBlank(userId)) {
			return Collections.emptyList();
		}
		
		List<RefStaff> refStaffs = repository.findByIdEmpno(userId);

		if (refStaffs == null) {
			return Collections.emptyList();
		}

		List<RefStaffPOJO> pojoList = new ArrayList<>();
		for (RefStaff refStaff : refStaffs) {
			RefStaffPOJO pojo = new RefStaffPOJO();
			RefStaffId id =  refStaff.getId();
			
			BeanUtils.copyProperties(id, pojo);
			BeanUtils.copyProperties(refStaff, pojo);
			pojoList.add(pojo);
		}
		
		return pojoList;
	}
    
}
