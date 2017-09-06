package com.fet.crm.osp.platform.core.db.model;
// Generated 2017/3/23 下午 02:35:57 by Hibernate Tools 4.0.0


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SysReason generated by hbm2java
 */
@Entity
@Table(name="SYS_REASON")
public class SysReason  implements java.io.Serializable {


     private String reasonId;
     private String reasonText;
     private String description;
     private Date createDate;
     private String createUser;
     private Date updateDate;
     private String updateUser;

    public SysReason() {
    }

	
    public SysReason(String reasonId, String reasonText, Date createDate, String createUser) {
        this.reasonId = reasonId;
        this.reasonText = reasonText;
        this.createDate = createDate;
        this.createUser = createUser;
    }
    public SysReason(String reasonId, String reasonText, String description, Date createDate, String createUser, Date updateDate, String updateUser) {
       this.reasonId = reasonId;
       this.reasonText = reasonText;
       this.description = description;
       this.createDate = createDate;
       this.createUser = createUser;
       this.updateDate = updateDate;
       this.updateUser = updateUser;
    }
   
     @Id 

    
    @Column(name="REASON_ID", unique=true, nullable=false, length=20)
    public String getReasonId() {
        return this.reasonId;
    }
    
    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    
    @Column(name="REASON_TEXT", nullable=false, length=100)
    public String getReasonText() {
        return this.reasonText;
    }
    
    public void setReasonText(String reasonText) {
        this.reasonText = reasonText;
    }

    
    @Column(name="DESCRIPTION", length=500)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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


