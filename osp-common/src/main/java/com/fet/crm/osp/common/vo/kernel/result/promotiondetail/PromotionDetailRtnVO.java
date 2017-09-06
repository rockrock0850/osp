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

import java.util.List;

/**
 * 促銷方案合約資訊查詢的回傳封裝物件
 * 
 * @author RichardHuang
 */
public class PromotionDetailRtnVO {
    
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
    
    /**
     * @return the attach
     */
    public String getAttach() {
        return attach;
    }
    
    /**
     * @param attach the attach to set
     */
    public void setAttach(String attach) {
        this.attach = attach;
    }
    
    /**
     * @return the promotionCode
     */
    public String getPromotionCode() {
        return promotionCode;
    }
    
    /**
     * @param promotionCode the promotionCode to set
     */
    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
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
     * @return the promotionCategoryName
     */
    public String getPromotionCategoryName() {
        return promotionCategoryName;
    }
    
    /**
     * @param promotionCategoryName the promotionCategoryName to set
     */
    public void setPromotionCategoryName(String promotionCategoryName) {
        this.promotionCategoryName = promotionCategoryName;
    }
    
    /**
     * @return the promotionType
     */
    public String getPromotionType() {
        return promotionType;
    }
    
    /**
     * @param promotionType the promotionType to set
     */
    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }
    
    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }
    
    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }
    
    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    /**
     * @return the msisdnType
     */
    public String getMsisdnType() {
        return msisdnType;
    }
    
    /**
     * @param msisdnType the msisdnType to set
     */
    public void setMsisdnType(String msisdnType) {
        this.msisdnType = msisdnType;
    }
    
    /**
     * @return the vasOfferList
     */
    public List<VasOfferVO> getVasOfferList() {
        return vasOfferList;
    }
    
    /**
     * @param vasOfferList the vasOfferList to set
     */
    public void setVasOfferList(List<VasOfferVO> vasOfferList) {
        this.vasOfferList = vasOfferList;
    }

	/**
	 * @return the discountOfferList
	 */
	public List<DiscountOfferVO> getDiscountOfferList() {
		return discountOfferList;
	}

	/**
	 * @param discountOfferList the discountOfferList to set
	 */
	public void setDiscountOfferList(List<DiscountOfferVO> discountOfferList) {
		this.discountOfferList = discountOfferList;
	}
    
}
