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
package com.fet.crm.osp.platform.mware.client.vo;

import java.util.List;

/**
 * 新增 CIE Master 資料的 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 * 
 * @author Adam Yeh, AllenChen
 */
public class PromotionDetailReturnVO {

	private String attach; // 附件內容
	private String promotionCode; // 促銷方案代碼
	private String name; // 促代說明
	private String promotionCategoryName; // 促代類型
	private String promotionType; // 促代類別
	private String startDate; // 起始日
	private String endDate; // 終止日
	private String msisdnType; // 門號類別
	private List<DiscountOfferVO> discountOfferList; // 折扣項目清單
	private List<VasOfferVO> vasOfferList; // 加值項目清單

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPromotionCategoryName() {
		return promotionCategoryName;
	}

	public void setPromotionCategoryName(String promotionCategoryName) {
		this.promotionCategoryName = promotionCategoryName;
	}

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMsisdnType() {
		return msisdnType;
	}

	public void setMsisdnType(String msisdnType) {
		this.msisdnType = msisdnType;
	}

	public List<VasOfferVO> getVasOfferList() {
		return vasOfferList;
	}

	public void setVasOfferList(List<VasOfferVO> vasOfferList) {
		this.vasOfferList = vasOfferList;
	}

	public List<DiscountOfferVO> getDiscountOfferList() {
		return discountOfferList;
	}

	public void setDiscountOfferList(List<DiscountOfferVO> discountOfferList) {
		this.discountOfferList = discountOfferList;
	}

}
