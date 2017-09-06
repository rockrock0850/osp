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

package com.fet.crm.osp.kernel.mware.server.vo.process.req;

import java.util.Date;
import java.util.List;

import com.fet.crm.osp.kernel.core.vo.orderinfo.OrderDetailSpvVO;
import com.fet.crm.osp.kernel.core.vo.orderinfo.OrderMainMetadataVO;

/**
 * 請求檔內容，需要傳入後端進行操作用的資料(ex: 交易/查詢條件)，都需要註冊在此. <br>
 * 
 * @author VJChou, RichardHuang
 */
public class ReqBody {

    private String empNo; // 員工編號
    private String teamGroup; // 員工所屬進件組別

    // OSPPoolMainService
    private String sourceOrderId; // 來源系統AIMS原始單號
    private Date expectProcessTime; // 預計作業處理時間
    private Date custSpecifyDate; // 客戶指定生效日
    private String txId; // 交易代碼
    private OrderDetailSpvVO orderDetailSpv; // SPV進件工單明細封裝資料

    // OSPToolMainService
    private String ospKey;
    private String accountId;
    private String subscriberId;
    private String businessEntityNumber = "110154"; // F1帳戶類型 (F1/F3: 110154, F2: 110155)
    private String msisdn; // 門號
    private List<String> msisdnList; // 門號清單
    private String idType; // 證號類別
    private String rocId; // 證號
    private String ownId;
    private String systemType;
    private String functionId;
    private String salesChannelType;
    private String ivrCode;
    private String promotionId;
    private String paymentCategory;
    private String accountName;
    private String isVolteSub;
    private String subType;
    private String contractComponentId;
    private String partyId;
    private String serviceProvider;
    private String generationCode;
    private String orderTypeId; // 案件類型
    private String identificationType; // 身份別: 個人 / 公司
    private String salesId; // 業務人員員編(直營)
    private String userId; // 使用者 NT Account
    
    // TicketPoolMainService
    private String poolKey; // 工單池所產生的鍵值
    private String status; // 工單狀態
    private String processUser; // 處理人員
    private String sourceSysId; // 進件系統代號

    private OrderMainMetadataVO orderMainMetadata; // 進件工單封裝資料

    /**
     * 取得員工編號
     * 
     * @return
     */
    public String getEmpNo() {
        return empNo;
    }

    /**
     * 設定員工編號
     * 
     * @param empNo
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    /**
     * 取得員工所屬進件組別
     * 
     * @return the teamGroup
     */
    public String getTeamGroup() {
        return teamGroup;
    }

    /**
     * 設定員工所屬進件組別
     * 
     * @param teamGroup
     *            the teamGroup to set
     */
    public void setTeamGroup(String teamGroup) {
        this.teamGroup = teamGroup;
    }

    public String getSourceOrderId() {
        return sourceOrderId;
    }

    public void setSourceOrderId(String sourceOrderId) {
        this.sourceOrderId = sourceOrderId;
    }

    public Date getExpectProcessTime() {
        return expectProcessTime;
    }

    public void setExpectProcessTime(Date expectProcessTime) {
        this.expectProcessTime = expectProcessTime;
    }

    public Date getCustSpecifyDate() {
        return custSpecifyDate;
    }

    public void setCustSpecifyDate(Date custSpecifyDate) {
        this.custSpecifyDate = custSpecifyDate;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getOspKey() {
        return ospKey;
    }

    public void setOspKey(String ospKey) {
        this.ospKey = ospKey;
    }

    public String getPoolKey() {
        return poolKey;
    }

    public void setPoolKey(String poolKey) {
        this.poolKey = poolKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessUser() {
        return processUser;
    }

    public void setProcessUser(String processUser) {
        this.processUser = processUser;
    }

    public OrderMainMetadataVO getOrderMainMetadata() {
        return orderMainMetadata;
    }

    public void setOrderMainMetadata(OrderMainMetadataVO orderMainMetadata) {
        this.orderMainMetadata = orderMainMetadata;
    }

    public String getSourceSysId() {
        return sourceSysId;
    }

    public void setSourceSysId(String sourceSysId) {
        this.sourceSysId = sourceSysId;
    }

    public OrderDetailSpvVO getOrderDetailSpv() {
        return orderDetailSpv;
    }

    public void setOrderDetailSpv(OrderDetailSpvVO orderDetailSpv) {
        this.orderDetailSpv = orderDetailSpv;
    }
    
    public String getBusinessEntityNumber() {
        return businessEntityNumber;
    }
    
    public void setBusinessEntityNumber(String businessEntityNumber) {
        this.businessEntityNumber = businessEntityNumber;
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
    
    /**
     * 取得 ownId
     * 
     * @return the ownId
     */
    public String getOwnId() {
        return ownId;
    }
    
    /**
     * 設定 ownId
     * 
     * @param ownId the ownId to set
     */
    public void setOwnId(String ownId) {
        this.ownId = ownId;
    }

    /**
     * @return the msisdnList
     */
    public List<String> getMsisdnList() {
        return msisdnList;
    }
    
    /**
     * @param msisdnList the msisdnList to set
     */
    public void setMsisdnList(List<String> msisdnList) {
        this.msisdnList = msisdnList;
    }
    
    /**
     * @return the accountId
     */
    public String getAccountId() {
        return accountId;
    }
    
    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
     * @return the paymentCategory
     */
    public String getPaymentCategory() {
        return paymentCategory;
    }

    /**
     * @param paymentCategory the paymentCategory to set
     */
    public void setPaymentCategory(String paymentCategory) {
        this.paymentCategory = paymentCategory;
    }
    
    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    /**
     * @return the isVolteSub
     */
    public String getIsVolteSub() {
        return isVolteSub;
    }
    
    /**
     * @param isVolteSub the isVolteSub to set
     */
    public void setIsVolteSub(String isVolteSub) {
        this.isVolteSub = isVolteSub;
    }

    /**
     * @return the subType
     */
    public String getSubType() {
        return subType;
    }

    /**
     * @param subType the subType to set
     */
    public void setSubType(String subType) {
        this.subType = subType;
    }
    
    /**
     * @return the contractComponentId
     */
    public String getContractComponentId() {
        return contractComponentId;
    }

    /**
     * @param contractComponentId the contractComponentId to set
     */
    public void setContractComponentId(String contractComponentId) {
        this.contractComponentId = contractComponentId;
    }
    
    /**
     * @return the partyId
     */
    public String getPartyId() {
        return partyId;
    }

    /**
     * @param partyId the partyId to set
     */
    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    /**
     * @return the serviceProvider
     */
    public String getServiceProvider() {
        return serviceProvider;
    }
    
    /**
     * @param serviceProvider the serviceProvider to set
     */
    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    /**
     * @return the generationCode
     */
    public String getGenerationCode() {
        return generationCode;
    }

    /**
     * @param generationCode the generationCode to set
     */
    public void setGenerationCode(String generationCode) {
        this.generationCode = generationCode;
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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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

}
