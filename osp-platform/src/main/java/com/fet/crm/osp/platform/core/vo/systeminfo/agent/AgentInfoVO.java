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

package com.fet.crm.osp.platform.core.vo.systeminfo.agent;

/**
 * 
 * @author LawrenceLai
 */
public class AgentInfoVO {

	private InternaluseraccountVO internaluseraccountVO;
	private StaffVO staffVO;
	private GroupDefinitionVO groupDefinitionVO;
	private UsersVO usersVO;
	private String cacheChannel;
	private String cacheChannelPostpaid;
	private String cacheChannelIdPostpaid;
	private String cacheChannelIdPasswordPostpaid;
	private String cacheChannelIdPrepaid;
	private String cacheChannelIdPasswordPrepaid;
	private String cacheChannelPrepaid;
	private String ivrcode;
	private String channelGroupId;

	public InternaluseraccountVO getInternaluseraccountVO() {
		return internaluseraccountVO;
	}

	public void setInternaluseraccountVO(InternaluseraccountVO internaluseraccountVO) {
		this.internaluseraccountVO = internaluseraccountVO;
	}

	public StaffVO getStaffVO() {
		return staffVO;
	}

	public void setStaffVO(StaffVO staffVO) {
		this.staffVO = staffVO;
	}

	public GroupDefinitionVO getGroupDefinitionVO() {
		return groupDefinitionVO;
	}

	public void setGroupDefinitionVO(GroupDefinitionVO groupDefinitionVO) {
		this.groupDefinitionVO = groupDefinitionVO;
	}

	public UsersVO getUsersVO() {
		return usersVO;
	}

	public void setUsersVO(UsersVO usersVO) {
		this.usersVO = usersVO;
	}

	public String getCacheChannel() {
		return cacheChannel;
	}

	public void setCacheChannel(String cacheChannel) {
		this.cacheChannel = cacheChannel;
	}

	public String getCacheChannelPostpaid() {
		return cacheChannelPostpaid;
	}

	public void setCacheChannelPostpaid(String cacheChannelPostpaid) {
		this.cacheChannelPostpaid = cacheChannelPostpaid;
	}

	public String getCacheChannelIdPostpaid() {
		return cacheChannelIdPostpaid;
	}

	public void setCacheChannelIdPostpaid(String cacheChannelIdPostpaid) {
		this.cacheChannelIdPostpaid = cacheChannelIdPostpaid;
	}

	public String getCacheChannelIdPasswordPostpaid() {
		return cacheChannelIdPasswordPostpaid;
	}

	public void setCacheChannelIdPasswordPostpaid(String cacheChannelIdPasswordPostpaid) {
		this.cacheChannelIdPasswordPostpaid = cacheChannelIdPasswordPostpaid;
	}

	public String getCacheChannelIdPrepaid() {
		return cacheChannelIdPrepaid;
	}

	public void setCacheChannelIdPrepaid(String cacheChannelIdPrepaid) {
		this.cacheChannelIdPrepaid = cacheChannelIdPrepaid;
	}

	public String getCacheChannelIdPasswordPrepaid() {
		return cacheChannelIdPasswordPrepaid;
	}

	public void setCacheChannelIdPasswordPrepaid(String cacheChannelIdPasswordPrepaid) {
		this.cacheChannelIdPasswordPrepaid = cacheChannelIdPasswordPrepaid;
	}

	public String getCacheChannelPrepaid() {
		return cacheChannelPrepaid;
	}

	public void setCacheChannelPrepaid(String cacheChannelPrepaid) {
		this.cacheChannelPrepaid = cacheChannelPrepaid;
	}

	public String getIvrcode() {
		return ivrcode;
	}

	public void setIvrcode(String ivrcode) {
		this.ivrcode = ivrcode;
	}

	public String getChannelGroupId() {
		return channelGroupId;
	}

	public void setChannelGroupId(String channelGroupId) {
		this.channelGroupId = channelGroupId;
	}

}
