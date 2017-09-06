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
 * 取得 AppPart 啟動所需客資 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 * 
 * @author RichardHuang
 */
public class CustInfoForAppPartParamVO {
    
    private String msisdn; // 門號
    private String idType; // 證號類別
    private String rocId; // 證號
    private String ownId; // cache 識別編號
    
    /**
     * 取得「cache 識別編號」
     * 
     * @return the ownId
     */
    public String getOwnId() {
        return ownId;
    }
    
    /**
     * 設定「cache 識別編號」
     * 
     * @param ownId the ownId to set
     */
    public void setOwnId(String ownId) {
        this.ownId = ownId;
    }
    
    /**
     * 取得「門號」
     * 
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }
    
    /**
     * 設定「門號」
     * 
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
    
    /**
     * 取得「證號類別」
     * 
     * @return the idType
     */
    public String getIdType() {
        return idType;
    }
    
    /**
     * 設定「證號類別」
     * 
     * @param idType the idType to set
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }
    
    /**
     * 取得「證號」
     * 
     * @return the rocId
     */
    public String getRocId() {
        return rocId;
    }
    
    /**
     * 設定「證號」
     * 
     * @param rocId the rocId to set
     */
    public void setRocId(String rocId) {
        this.rocId = rocId;
    }

    @Override
    public String toString() {
        return "CustInfoForAppPartParamVO [msisdn=" + msisdn + ", idType=" + idType + ", rocId=" + rocId + ", ownId="
                + ownId + "]";
    }

}
