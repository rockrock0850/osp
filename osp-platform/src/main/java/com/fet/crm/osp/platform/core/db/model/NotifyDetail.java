package com.fet.crm.osp.platform.core.db.model;
// Generated 2017/4/19 �W�� 01:21:01 by Hibernate Tools 4.0.0


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NotifyDetail generated by hbm2java
 */
@Entity
@Table(name="NOTIFY_DETAIL")
public class NotifyDetail  implements java.io.Serializable {


     private String notifyDetailId;
     private String notifyMainId;
     private String orderMId;
     private Date createDate;
     private String createUser;
     private Date updateDate;
     private String updateUser;

    public NotifyDetail() {
    }

	
    public NotifyDetail(String notifyDetailId, String notifyMainId, String orderMId, Date createDate, String createUser) {
        this.notifyDetailId = notifyDetailId;
        this.notifyMainId = notifyMainId;
        this.orderMId = orderMId;
        this.createDate = createDate;
        this.createUser = createUser;
    }
    public NotifyDetail(String notifyDetailId, String notifyMainId, String orderMId, Date createDate, String createUser, Date updateDate, String updateUser) {
       this.notifyDetailId = notifyDetailId;
       this.notifyMainId = notifyMainId;
       this.orderMId = orderMId;
       this.createDate = createDate;
       this.createUser = createUser;
       this.updateDate = updateDate;
       this.updateUser = updateUser;
    }
   
     @Id 

    
    @Column(name="NOTIFY_DETAIL_ID", unique=true, nullable=false, length=36)
    public String getNotifyDetailId() {
        return this.notifyDetailId;
    }
    
    public void setNotifyDetailId(String notifyDetailId) {
        this.notifyDetailId = notifyDetailId;
    }

    
    @Column(name="NOTIFY_MAIN_ID", nullable=false, length=36)
    public String getNotifyMainId() {
        return this.notifyMainId;
    }
    
    public void setNotifyMainId(String notifyMainId) {
        this.notifyMainId = notifyMainId;
    }

    
    @Column(name="ORDER_M_ID", nullable=false, length=36)
    public String getOrderMId() {
        return this.orderMId;
    }
    
    public void setOrderMId(String orderMId) {
        this.orderMId = orderMId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATE_DATE", nullable=false, length=7)
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    
    @Column(name="CREATE_USER", nullable=false, length=20)
    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UPDATE_DATE", length=7)
    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    
    @Column(name="UPDATE_USER", length=20)
    public String getUpdateUser() {
        return this.updateUser;
    }
    
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }




}


