package com.fet.crm.osp.platform.core.db.model;
// Generated 2017/5/5 �W�� 09:41:50 by Hibernate Tools 4.0.0


import java.sql.Clob;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SysActionLog generated by hbm2java
 */
@Entity
@Table(name="SYS_ACTION_LOG")
public class SysActionLog  implements java.io.Serializable {


     private SysActionLogId id;
     private String classify;
     private String userId;
     private String ipAddress;
     private Clob eventDesc;
     private String operation;
     private Date logDate;
     private String duration;
     private String requestContent;

    public SysActionLog() {
    }

	
    public SysActionLog(SysActionLogId id, Date logDate, String duration) {
        this.id = id;
        this.logDate = logDate;
        this.duration = duration;
    }
    public SysActionLog(SysActionLogId id, String classify, String userId, String ipAddress, Clob eventDesc, String operation, Date logDate, String duration, String requestContent) {
       this.id = id;
       this.classify = classify;
       this.userId = userId;
       this.ipAddress = ipAddress;
       this.eventDesc = eventDesc;
       this.operation = operation;
       this.logDate = logDate;
       this.duration = duration;
       this.requestContent = requestContent;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="requestId", column=@Column(name="REQUEST_ID", nullable=false, length=36) ), 
        @AttributeOverride(name="logId", column=@Column(name="LOG_ID", nullable=false, length=36) ) } )
    public SysActionLogId getId() {
        return this.id;
    }
    
    public void setId(SysActionLogId id) {
        this.id = id;
    }

    
    @Column(name="CLASSIFY", length=100)
    public String getClassify() {
        return this.classify;
    }
    
    public void setClassify(String classify) {
        this.classify = classify;
    }

    
    @Column(name="USER_ID", length=10)
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    @Column(name="IP_ADDRESS", length=100)
    public String getIpAddress() {
        return this.ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    
    @Column(name="EVENT_DESC")
    public Clob getEventDesc() {
        return this.eventDesc;
    }
    
    public void setEventDesc(Clob eventDesc) {
        this.eventDesc = eventDesc;
    }

    
    @Column(name="OPERATION", length=100)
    public String getOperation() {
        return this.operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LOG_DATE", nullable=false, length=7)
    public Date getLogDate() {
        return this.logDate;
    }
    
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    
    @Column(name="DURATION", nullable=false, length=10)
    public String getDuration() {
        return this.duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }

    
    @Column(name="REQUEST_CONTENT", length=1000)
    public String getRequestContent() {
        return this.requestContent;
    }
    
    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }




}


