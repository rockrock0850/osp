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

import java.util.Date;
import java.util.List;

import com.fet.crm.osp.common.vo.kernel.result.cie.CIEDetailVO;

/**
 * 新增 CIE Master 資料的 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 *  
 * @author Adam Yeh
 */
public class AddCIEMasterReturnVO {
    
    private String cieId;
    private String potentialContactIndicator;
    private String inOutIndicator;
    private String channelType;
    private String channelValue;
    private String fetContactMethodType;
    private String fetContactMethodValue;
    private String createdAgent;
    private String createdSystem;
    private Date startDate;
    private Date endDate;
    private Date createDate;
    private Date cieMasterLastUpdateDate;
    private String cieMasterLastUpdateUser;
    private String cieMasterLastUpdateTxId;
    private List<CIEDetailVO> cieDetailVOList;
    
	public String getCieId() {
		return cieId;
	}
	public void setCieId(String cieId) {
		this.cieId = cieId;
	}
	public String getPotentialContactIndicator() {
		return potentialContactIndicator;
	}
	public void setPotentialContactIndicator(String potentialContactIndicator) {
		this.potentialContactIndicator = potentialContactIndicator;
	}
	public String getInOutIndicator() {
		return inOutIndicator;
	}
	public void setInOutIndicator(String inOutIndicator) {
		this.inOutIndicator = inOutIndicator;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getChannelValue() {
		return channelValue;
	}
	public void setChannelValue(String channelValue) {
		this.channelValue = channelValue;
	}
	public String getFetContactMethodType() {
		return fetContactMethodType;
	}
	public void setFetContactMethodType(String fetContactMethodType) {
		this.fetContactMethodType = fetContactMethodType;
	}
	public String getFetContactMethodValue() {
		return fetContactMethodValue;
	}
	public void setFetContactMethodValue(String fetContactMethodValue) {
		this.fetContactMethodValue = fetContactMethodValue;
	}
	public String getCreatedAgent() {
		return createdAgent;
	}
	public void setCreatedAgent(String createdAgent) {
		this.createdAgent = createdAgent;
	}
	public String getCreatedSystem() {
		return createdSystem;
	}
	public void setCreatedSystem(String createdSystem) {
		this.createdSystem = createdSystem;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getCieMasterLastUpdateDate() {
		return cieMasterLastUpdateDate;
	}
	public void setCieMasterLastUpdateDate(Date cieMasterLastUpdateDate) {
		this.cieMasterLastUpdateDate = cieMasterLastUpdateDate;
	}
	public String getCieMasterLastUpdateUser() {
		return cieMasterLastUpdateUser;
	}
	public void setCieMasterLastUpdateUser(String cieMasterLastUpdateUser) {
		this.cieMasterLastUpdateUser = cieMasterLastUpdateUser;
	}
	public String getCieMasterLastUpdateTxId() {
		return cieMasterLastUpdateTxId;
	}
	public void setCieMasterLastUpdateTxId(String cieMasterLastUpdateTxId) {
		this.cieMasterLastUpdateTxId = cieMasterLastUpdateTxId;
	}
	public List<CIEDetailVO> getCieDetailVOList() {
		return cieDetailVOList;
	}
	public void setCieDetailVOList(List<CIEDetailVO> cieDetailVOList) {
		this.cieDetailVOList = cieDetailVOList;
	}
    
}
