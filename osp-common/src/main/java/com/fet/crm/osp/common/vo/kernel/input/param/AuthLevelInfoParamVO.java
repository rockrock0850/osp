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
package com.fet.crm.osp.common.vo.kernel.input.param;

/**
 * 查詢簽核層級與人員相關資訊的 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 *  
 * @author RichardHuang
 */
public class AuthLevelInfoParamVO extends AbstractESBParamVO {
    
	private String ivrCode; // 店組代碼(必要欄位)
	private String orderTypeId; // 案件類型(必要欄位)
	private String salesId; // 業務人員員編(若 ivrCode 長度為 4 碼:直營門市時才為必要欄位)
	private String rocId; // 客戶身分證號碼
    private String subscriberId;
    private String promotionId;
    
	/**
     * @return the rocId
     */
    public String getRocId() {
        return rocId;
    }
    
    /**
     * @param rocId the rocId to set
     */
    public void setRocId(String rocId) {
        this.rocId = rocId;
    }
    
    /**
     * @return the ivrCode
     */
    public String getIvrCode() {
        return ivrCode;
    }
    
    /**
     * @param ivrCode the ivrCode to set
     */
    public void setIvrCode(String ivrCode) {
        this.ivrCode = ivrCode;
    }
    
	/**
	 * @return the salesId
	 */
	public String getSalesId() {
		return salesId;
	}

	/**
	 * @param salesId the salesId to set
	 */
	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	/**
	 * @return the promotionId
	 */
	public String getPromotionId() {
		return promotionId;
	}

	/**
	 * @param promotionId the promotionId to set
	 */
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	/**
	 * @return the orderTypeId
	 */
	public String getOrderTypeId() {
		return orderTypeId;
	}

	/**
	 * @param orderTypeId the orderTypeId to set
	 */
	public void setOrderTypeId(String orderTypeId) {
		this.orderTypeId = orderTypeId;
	}

	/**
	 * @return the subscriberId
	 */
	public String getSubscriberId() {
		return subscriberId;
	}

	/**
	 * @param subscriberId the subscriberId to set
	 */
	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}
    
}
