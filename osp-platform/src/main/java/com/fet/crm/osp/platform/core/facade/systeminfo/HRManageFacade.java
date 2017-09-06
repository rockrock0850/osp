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

package com.fet.crm.osp.platform.core.facade.systeminfo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.platform.core.service.systeminfo.IHRManageService;
import com.fet.crm.osp.platform.core.vo.orderinfo.CreationManageVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.MemberInfoVO;

/**
 * 「人力資源管理」總體服務窗口
 * 
 * @author LawrenceLai
 */
@Service
public class HRManageFacade {
	
	@Autowired
	private IHRManageService hrManageService;
	
	/**
	 * 查詢 7331部門人員 資訊<br>
	 * 
	 * @return List<HRMInfoVO>
	 */
	@Transactional(readOnly = true)
	public List<MemberInfoVO> getMemberInfoBy7331Team() {
		return hrManageService.queryMemberInfoBy7331Team();
	}

	/**
	 * 查詢 7331部門人員 資訊(排除已設定者)<br>
	 * 
	 * @param roleId
	 * @return List<HRMInfoVO>
	 */
	@Transactional(readOnly = true)
	public List<MemberInfoVO> getMemberInfoBy7331TeamWithoutBeSettedRoleMember(String roleId) {
		return hrManageService.queryMemberInfoBy7331TeamWithoutBeSettedRoleMember(roleId);
	}

	/**
	 * 依據 員工編號userId 查詢當日班別
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getShiftTypeByUserIdAndSysDate(String userId) {
		return hrManageService.queryShiftTypeByUserIdAndSysDate(userId);
	}

	/**
	 * 依據 員工編號userId 建檔清單資訊
	 * 
	 * @author Adam
	 * @create date: May 4, 2017
	 *
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public CreationManageVO getCreationManageByUserId(String userId) {
		return hrManageService.queryCreationManageByUserId(userId);
	}

	/**
	 * 依據 主管員工編號userId 查詢該組員員工資訊
	 * 
	 * @param userId
	 * @return List<MemberInfoVO>
	 */
	@Transactional(readOnly = true)
	public List<MemberInfoVO> getMemberInfoByLeaderEmpNo(String userId) {
		List<MemberInfoVO> memberList = hrManageService.queryMemberInfoByLeaderEmpNo(userId);
		
		if (CollectionUtils.isEmpty(memberList)) {
			memberList = new ArrayList<>();
			
			MemberInfoVO memberInfo = hrManageService.queryMemberInfoByUserId(userId);
			
			memberList.add(memberInfo);
		}
		
		return memberList;
	}
	
	/**
	 * 依據 員工編號userId 查詢該員工員工資訊
	 * 
	 * @param userId
	 * @return
	 */
	public MemberInfoVO getMemberInfoByUserId(String userId) {
		return hrManageService.queryMemberInfoByUserId(userId);
	}
	
}