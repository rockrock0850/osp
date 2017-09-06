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
package com.fet.crm.osp.kernel.mware.client.pojo;

import com.fet.crm.osp.common.vo.kernel.input.param.AbstractESBParamVO;

/**
 * 查詢簽核層級與人員相關資訊的 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 *  
 * @author RichardHuang
 */
public class AuthLevelInfoInputPOJO extends AbstractESBParamVO {
    
	private String salesId; // 業務人員員編
	private String rocId; // 客戶身分證號碼
    private String ivrCode; // 店組代碼
    private String subscriberId;
    private String salesChannelType; // 通路類型
    private String postpaidWirelessId; // 申辦的 Promotion
    private String identificationType; // 身份別
    private String authLevel; // 簽核層級數
    
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
	 * @return the subscrId
	 */
	public String getSubscriberId() {
		return subscriberId;
	}

	/**
	 * @param subscriberId the subscrId to set
	 */
	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
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
	 * @return the salesChannelType
	 */
	public String getSalesChannelType() {
		return salesChannelType;
	}

	/**
	 * @param salesChannelType the salesChannelType to set
	 */
	public void setSalesChannelType(String salesChannelType) {
		this.salesChannelType = salesChannelType;
	}

	/**
	 * @return the postpaidWirelessId
	 */
	public String getPostpaidWirelessId() {
		return postpaidWirelessId;
	}

	/**
	 * @param postpaidWirelessId the postpaidWirelessId to set
	 */
	public void setPostpaidWirelessId(String postpaidWirelessId) {
		this.postpaidWirelessId = postpaidWirelessId;
	}

	/**
	 * @return the identificationType
	 */
	public String getIdentificationType() {
		return identificationType;
	}

	/**
	 * @param identificationType the identificationType to set
	 */
	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}

	/**
	 * @return the authLevel
	 */
	public String getAuthLevel() {
		return authLevel;
	}

	/**
	 * @param authLevel the authLevel to set
	 */
	public void setAuthLevel(String authLevel) {
		this.authLevel = authLevel;
	}

}
