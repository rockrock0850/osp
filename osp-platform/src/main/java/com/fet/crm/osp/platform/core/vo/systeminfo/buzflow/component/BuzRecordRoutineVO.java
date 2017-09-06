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
 * 
 * @author LawrenceLai
 */
public class BuzRecordRoutineVO extends AbstractOspBaseVO {
	
	private String contentId;
	private String itemId;
	private int sortSequence;
	private String itemType;
	private String itemName;
	private String orderMId;
	private String itemValue;
	private String unit;

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

	public int getSortSequence() {
		return sortSequence;
	}

	public void setSortSequence(int sortSequence) {
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

	public String getOrderMId() {
		return orderMId;
	}

	public void setOrderMId(String orderMId) {
		this.orderMId = orderMId;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
