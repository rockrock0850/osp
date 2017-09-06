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
package com.fet.crm.osp.common.vo.kernel.result.idviewinfo;

import java.util.List;

/**
 * 查詢歷史客資回傳資訊封裝物件
 * 
 * @author RichardHuang
 */
public class IdViewInfoRtnVO {
	
	private int totalCount; // 資料筆數
	
	private List<IdViewInfoVO> idViewInfoList; // 歷史客資清單

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the idViewInfoList
	 */
	public List<IdViewInfoVO> getIdViewInfoList() {
		return idViewInfoList;
	}

	/**
	 * @param idViewInfoList the idViewInfoList to set
	 */
	public void setIdViewInfoList(List<IdViewInfoVO> idViewInfoList) {
		this.idViewInfoList = idViewInfoList;
	}
	
}
