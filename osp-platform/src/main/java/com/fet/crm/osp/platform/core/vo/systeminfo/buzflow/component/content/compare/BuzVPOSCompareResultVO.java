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

package com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.content.compare;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;

/**
 * [VPOS AIMS] Excel對比資料檢核結果封裝物件
 *
 * @author AndrewLee
 */
public class BuzVPOSCompareResultVO extends AbstractOspBaseVO {

    private String compareId;
    private String VPosAuthCode;
    private String VPosMoney;
    private String VPosNumberPeriods;
    private String VPosRemark;
    private String aimsOrderId;
    private String aimsShippingId;
    private String aimsMsisdn;
    private String aimsActualPayment;
    private String compareResult;
    private String aimsAuthCode;

    public String getCompareId() {
        return compareId;
    }

    public void setCompareId(String compareId) {
        this.compareId = compareId;
    }

    public String getVPosAuthCode() {
        return VPosAuthCode;
    }

    public void setVPosAuthCode(String vPosAuthCode) {
        VPosAuthCode = vPosAuthCode;
    }

    public String getVPosMoney() {
        return VPosMoney;
    }

    public void setVPosMoney(String vPosMoney) {
        VPosMoney = vPosMoney;
    }

    public String getVPosNumberPeriods() {
        return VPosNumberPeriods;
    }

    public void setVPosNumberPeriods(String vPosNumberPeriods) {
        VPosNumberPeriods = vPosNumberPeriods;
    }

    public String getVPosRemark() {
        return VPosRemark;
    }

    public void setVPosRemark(String vPosRemark) {
        VPosRemark = vPosRemark;
    }

    public String getAimsOrderId() {
        return aimsOrderId;
    }

    public void setAimsOrderId(String aimsOrderId) {
        this.aimsOrderId = aimsOrderId;
    }

    public String getAimsShippingId() {
        return aimsShippingId;
    }

    public void setAimsShippingId(String aimsShippingId) {
        this.aimsShippingId = aimsShippingId;
    }

    public String getAimsMsisdn() {
        return aimsMsisdn;
    }

    public void setAimsMsisdn(String aimsMsisdn) {
        this.aimsMsisdn = aimsMsisdn;
    }

    public String getAimsActualPayment() {
        return aimsActualPayment;
    }

    public void setAimsActualPayment(String aimsActualPayment) {
        this.aimsActualPayment = aimsActualPayment;
    }

    public String getCompareResult() {
        return compareResult;
    }

    public void setCompareResult(String compareResult) {
        this.compareResult = compareResult;
    }

    public String getAimsAuthCode() {
        return aimsAuthCode;
    }

    public void setAimsAuthCode(String aimsAuthCode) {
        this.aimsAuthCode = aimsAuthCode;
    }

}
