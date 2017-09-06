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

package com.fet.crm.osp.platform.core.service.systeminfo;

import java.util.List;

import com.fet.crm.osp.platform.core.vo.orderinfo.CreationManageVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.MemberInfoVO;

/**
 * [人力資源管理] 服務介面
 * 
 * @author LawrenceLai
 */
public interface IHRManageService {
	
	/**
	 * 查詢 7331部門人員 資訊<br>
	 * 
	 * @return List<HRMInfoVO>
	 */
	List<MemberInfoVO> queryMemberInfoBy7331Team();

	/**
	 * 查詢 7331部門人員 資訊(排除已設定者)<br>
	 * 
	 * @param roleId
	 * @return List<HRMInfoVO>
	 */
	List<MemberInfoVO> queryMemberInfoBy7331TeamWithoutBeSettedRoleMember(String roleId);

	/**
	 * 依據 員工編號userId 查詢當日班別
	 * 
	 * @param userId
	 * @return
	 */
	String queryShiftTypeByUserIdAndSysDate(String userId);

	/**
	 * 依據 員工編號userId 查詢員工資訊
	 * 
	 * @author Adam
	 * @create date: May 4, 2017
	 *
	 * @param userId
	 * @return
	 */
	CreationManageVO queryCreationManageByUserId(String userId);
	
	/**
	 * 依據 主管員工編號userId 查詢該組員員工資訊
	 * 
	 * @param userId
	 * @return
	 */
	List<MemberInfoVO> queryMemberInfoByLeaderEmpNo(String userId);
	
	/**
	 * 依據 員工編號userId 查詢該員工員工資訊
	 * 
	 * @param userId
	 * @return
	 */
	MemberInfoVO queryMemberInfoByUserId(String userId);
	
}