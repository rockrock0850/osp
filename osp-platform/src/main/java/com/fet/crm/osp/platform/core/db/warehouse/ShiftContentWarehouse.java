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
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.platform.core.db.model.SysShiftContent;
import com.fet.crm.osp.platform.core.db.model.SysShiftContentId;
import com.fet.crm.osp.platform.core.db.repository.SysShiftContentRepository;
import com.fet.crm.osp.platform.core.pojo.ShiftContentPOJO;

/**
 * 
 * @author LawrenceLai
 */
@Component
public class ShiftContentWarehouse {

	@Autowired
	private SysShiftContentRepository repository;
	
	public ShiftContentPOJO findByEmpNoAndSysDate(String empNo) {
		ShiftContentPOJO pojo = new ShiftContentPOJO();
		
		if (StringUtils.isNotBlank(empNo)) {
			Calendar sysDate = Calendar.getInstance();
			
			String year = String.valueOf(sysDate.get(Calendar.YEAR));
			String month = String.valueOf(sysDate.get(Calendar.MONTH) + 1);
			String date = String.valueOf(sysDate.get(Calendar.DATE));
			
			SysShiftContent entity = repository.findByEmpNoAndSysDate(empNo, year, month, date);
			
			if (entity != null) {
				SysShiftContentId entityId = entity.getId();
				
				BeanUtils.copyProperties(entityId, pojo);
				BeanUtils.copyProperties(entity, pojo);
			}
		}
		
		return pojo;
	}
	
	public boolean save(List<ShiftContentPOJO> dataList) {
		if (CollectionUtils.isNotEmpty(dataList)) {
			List<SysShiftContent> entityList = new ArrayList<>();
			
			for(ShiftContentPOJO pojo : dataList) {
				SysShiftContent entity = new SysShiftContent();
				SysShiftContentId entityId = new SysShiftContentId();

				BeanUtils.copyProperties(pojo, entityId);
				BeanUtils.copyProperties(pojo, entity);

				entity.setId(entityId);
				entityList.add(entity);
				
				repository.save(entityList);
			}
		}
		
		return true;
	}

	public boolean save(ShiftContentPOJO pojo) {
		SysShiftContent entity = new SysShiftContent();
		SysShiftContentId entityId = new SysShiftContentId();

		BeanUtils.copyProperties(pojo, entityId);
		BeanUtils.copyProperties(pojo, entity);

		entity.setId(entityId);

		repository.save(entity);

		return true;
	}

}
