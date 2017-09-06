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
package com.fet.crm.osp.common.vo.kernel.result.agentinfo;

/**
 * 使用者登入資訊封裝物件. <br>
 * 
 * @author RichardHuang
 */
public class AgentInfoRtnVO {
    
    InternaluseraccountVO internaluseraccountVO;
    StaffVO staffVO;
    GroupDefinitionVO groupDefinitionVO;
    UsersVO usersVO;
    String cacheChannel;
    String cacheChannelPostpaid;
    String cacheChannelIdPostpaid;
    String cacheChannelIdPasswordPostpaid;
    String cacheChannelIdPrepaid;
    String cacheChannelIdPasswordPrepaid;
    String cacheChannelPrepaid;
    String ivrcode;
    String channelGroupId;
    
    /**
     * @return the internaluseraccountVO
     */
    public InternaluseraccountVO getInternaluseraccountVO() {
        return internaluseraccountVO;
    }
    
    /**
     * @param internaluseraccountVO the internaluseraccountVO to set
     */
    public void setInternaluseraccountVO(InternaluseraccountVO internaluseraccountVO) {
        this.internaluseraccountVO = internaluseraccountVO;
    }
    
    /**
     * @return the staffVO
     */
    public StaffVO getStaffVO() {
        return staffVO;
    }
    
    /**
     * @param staffVO the staffVO to set
     */
    public void setStaffVO(StaffVO staffVO) {
        this.staffVO = staffVO;
    }
    
    /**
     * @return the groupDefinitionVO
     */
    public GroupDefinitionVO getGroupDefinitionVO() {
        return groupDefinitionVO;
    }
    
    /**
     * @param groupDefinitionVO the groupDefinitionVO to set
     */
    public void setGroupDefinitionVO(GroupDefinitionVO groupDefinitionVO) {
        this.groupDefinitionVO = groupDefinitionVO;
    }
    
    /**
     * @return the usersVO
     */
    public UsersVO getUsersVO() {
        return usersVO;
    }
    
    /**
     * @param usersVO the usersVO to set
     */
    public void setUsersVO(UsersVO usersVO) {
        this.usersVO = usersVO;
    }
    
    /**
     * @return the cacheChannel
     */
    public String getCacheChannel() {
        return cacheChannel;
    }
    
    /**
     * @param cacheChannel the cacheChannel to set
     */
    public void setCacheChannel(String cacheChannel) {
        this.cacheChannel = cacheChannel;
    }
    
    /**
     * @return the cacheChannelPostpaid
     */
    public String getCacheChannelPostpaid() {
        return cacheChannelPostpaid;
    }
    
    /**
     * @param cacheChannelPostpaid the cacheChannelPostpaid to set
     */
    public void setCacheChannelPostpaid(String cacheChannelPostpaid) {
        this.cacheChannelPostpaid = cacheChannelPostpaid;
    }
    
    /**
     * @return the cacheChannelIdPostpaid
     */
    public String getCacheChannelIdPostpaid() {
        return cacheChannelIdPostpaid;
    }
    
    /**
     * @param cacheChannelIdPostpaid the cacheChannelIdPostpaid to set
     */
    public void setCacheChannelIdPostpaid(String cacheChannelIdPostpaid) {
        this.cacheChannelIdPostpaid = cacheChannelIdPostpaid;
    }
    
    /**
     * @return the cacheChannelIdPasswordPostpaid
     */
    public String getCacheChannelIdPasswordPostpaid() {
        return cacheChannelIdPasswordPostpaid;
    }
    
    /**
     * @param cacheChannelIdPasswordPostpaid the cacheChannelIdPasswordPostpaid to set
     */
    public void setCacheChannelIdPasswordPostpaid(String cacheChannelIdPasswordPostpaid) {
        this.cacheChannelIdPasswordPostpaid = cacheChannelIdPasswordPostpaid;
    }
    
    /**
     * @return the cacheChannelIdPrepaid
     */
    public String getCacheChannelIdPrepaid() {
        return cacheChannelIdPrepaid;
    }
    
    /**
     * @param cacheChannelIdPrepaid the cacheChannelIdPrepaid to set
     */
    public void setCacheChannelIdPrepaid(String cacheChannelIdPrepaid) {
        this.cacheChannelIdPrepaid = cacheChannelIdPrepaid;
    }
    
    /**
     * @return the cacheChannelIdPasswordPrepaid
     */
    public String getCacheChannelIdPasswordPrepaid() {
        return cacheChannelIdPasswordPrepaid;
    }
    
    /**
     * @param cacheChannelIdPasswordPrepaid the cacheChannelIdPasswordPrepaid to set
     */
    public void setCacheChannelIdPasswordPrepaid(String cacheChannelIdPasswordPrepaid) {
        this.cacheChannelIdPasswordPrepaid = cacheChannelIdPasswordPrepaid;
    }
    
    /**
     * @return the cacheChannelPrepaid
     */
    public String getCacheChannelPrepaid() {
        return cacheChannelPrepaid;
    }
    
    /**
     * @param cacheChannelPrepaid the cacheChannelPrepaid to set
     */
    public void setCacheChannelPrepaid(String cacheChannelPrepaid) {
        this.cacheChannelPrepaid = cacheChannelPrepaid;
    }
    
    /**
     * @return the ivrcode
     */
    public String getIvrcode() {
        return ivrcode;
    }
    
    /**
     * @param ivrcode the ivrcode to set
     */
    public void setIvrcode(String ivrcode) {
        this.ivrcode = ivrcode;
    }

    /**
     * @return the channelGroupId
     */
    public String getChannelGroupId() {
        return channelGroupId;
    }
    
    /**
     * @param channelGroupId the channelGroupId to set
     */
    public void setChannelGroupId(String channelGroupId) {
        this.channelGroupId = channelGroupId;
    }

    @Override
    public String toString() {
        return "AgentInfoRtnVO [internaluseraccountVO=" + internaluseraccountVO + ", staffVO=" + staffVO
                + ", groupDefinitionVO=" + groupDefinitionVO + ", usersVO=" + usersVO + ", cacheChannel=" + cacheChannel
                + ", cacheChannelPostpaid=" + cacheChannelPostpaid + ", cacheChannelIdPostpaid="
                + cacheChannelIdPostpaid + ", cacheChannelIdPasswordPostpaid=" + cacheChannelIdPasswordPostpaid
                + ", cacheChannelIdPrepaid=" + cacheChannelIdPrepaid + ", cacheChannelIdPasswordPrepaid="
                + cacheChannelIdPasswordPrepaid + ", cacheChannelPrepaid=" + cacheChannelPrepaid + ", ivrcode="
                + ivrcode + ", channelGroupId=" + channelGroupId + "]";
    }
    
}
