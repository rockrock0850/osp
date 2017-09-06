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
package com.fet.crm.osp.common.vo.kernel.result.promotiondetail;

/**
 * 折扣項目資訊封裝物件
 * 
 * @author RichardHuang
 */
public class DiscountOfferVO {
	
	private String id;
	
	private String name;
	
	private int happyGo; // Happy Go 贈點

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the happyGo
	 */
	public int getHappyGo() {
		return happyGo;
	}

	/**
	 * @param happyGo the happyGo to set
	 */
	public void setHappyGo(int happyGo) {
		this.happyGo = happyGo;
	}
	
}
