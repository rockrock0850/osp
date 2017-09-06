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

/**
 * 查詢簽核層級與人員相關資訊的 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 *  
 * @author Adam Yeh
 */
public class AuthLevelInfoVO {
    
    private String empNo; // 員工編號
    private String systemType; // IA or SSI
    private String functionId; // GA 類的 functionId
    private String rocId; // 客戶身分證號碼
    private String salesChannelType; // Retail or VASS
    private String ivrCode; // 店組代碼
    
    /**
     * @return the systemType
     */
    public String getSystemType() {
        return systemType;
    }
    
    /**
     * @param systemType the systemType to set
     */
    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }
    
    /**
     * @return the functionId
     */
    public String getFunctionId() {
        return functionId;
    }
    
    /**
     * @param functionId the functionId to set
     */
    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }
    
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

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
    
}
