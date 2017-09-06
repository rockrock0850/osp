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

package com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;

/**
 * BuzHierarchyEstablishmentVO 封裝物件
 * 
 * @author AllenChen
 */
public class BuzHierarchyEstablishmentVO extends AbstractOspBaseVO {

	private String contentId;
	private String itemId;
	private String sortSequence; // 排序
	private String itemType;
	private String itemName;
	private String reserv2;
	private String reserv3;

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getSortSequence() {
		return sortSequence;
	}

	public void setSortSequence(String sortSequence) {
		this.sortSequence = sortSequence;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getReserv2() {
		return reserv2;
	}

	public void setReserv2(String reserv2) {
		this.reserv2 = reserv2;
	}

	public String getReserv3() {
		return reserv3;
	}

	public void setReserv3(String reserv3) {
		this.reserv3 = reserv3;
	}

}
