package com.fet.crm.osp.platform.core.pojo;

import java.util.Date;

public class SysFlowReasonMapPOJO {
	
     private String reasonMapId;
     private String flowId;
     private String orderStatus;
     private String reasonId;
     private String reasonName;
     private String reasonType;
     private Date createDate;
     private String createUser;
     private Date updateDate;
     private String updateUser;

    public String getReasonMapId() {
        return this.reasonMapId;
    }
    
    public void setReasonMapId(String reasonMapId) {
        this.reasonMapId = reasonMapId;
    }
    
    public String getFlowId() {
        return this.flowId;
    }
    
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }
    
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getReasonId() {
        return this.reasonId;
    }
    
    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }
    
    public String getReasonName() {
        return this.reasonName;
    }
    
    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    public String getReasonType() {
        return this.reasonType;
    }
    
    public void setReasonType(String reasonType) {
        this.reasonType = reasonType;
    }

    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }
    
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

}


