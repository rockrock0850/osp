package com.fet.crm.osp.platform.core.db.model;
// Generated May 10, 2017 12:40:59 AM by Hibernate Tools 4.0.0


import java.math.BigDecimal;
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
 * HrmCalendar generated by hbm2java
 */
@Entity
@Table(name="HRM_CALENDAR")
public class HrmCalendar  implements java.io.Serializable {


     private HrmCalendarId id;
     private String weekDay;
     private String isHollday;
     private BigDecimal workHr;
     private String remark1;
     private String startWork;
     private String endWork;
     private Date createDate;
     private String createUser;

    public HrmCalendar() {
    }

	
    public HrmCalendar(HrmCalendarId id, String isHollday, BigDecimal workHr, Date createDate, String createUser) {
        this.id = id;
        this.isHollday = isHollday;
        this.workHr = workHr;
        this.createDate = createDate;
        this.createUser = createUser;
    }
    public HrmCalendar(HrmCalendarId id, String weekDay, String isHollday, BigDecimal workHr, String remark1, String startWork, String endWork, Date createDate, String createUser) {
       this.id = id;
       this.weekDay = weekDay;
       this.isHollday = isHollday;
       this.workHr = workHr;
       this.remark1 = remark1;
       this.startWork = startWork;
       this.endWork = endWork;
       this.createDate = createDate;
       this.createUser = createUser;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="dtYear", column=@Column(name="DT_YEAR", nullable=false, length=4) ), 
        @AttributeOverride(name="dtMon", column=@Column(name="DT_MON", nullable=false, length=2) ), 
        @AttributeOverride(name="dtDay", column=@Column(name="DT_DAY", nullable=false, length=2) ) } )
    public HrmCalendarId getId() {
        return this.id;
    }
    
    public void setId(HrmCalendarId id) {
        this.id = id;
    }

    
    @Column(name="WEEK_DAY", length=5)
    public String getWeekDay() {
        return this.weekDay;
    }
    
    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    
    @Column(name="IS_HOLLDAY", nullable=false, length=1)
    public String getIsHollday() {
        return this.isHollday;
    }
    
    public void setIsHollday(String isHollday) {
        this.isHollday = isHollday;
    }

    
    @Column(name="WORK_HR", nullable=false, precision=22, scale=0)
    public BigDecimal getWorkHr() {
        return this.workHr;
    }
    
    public void setWorkHr(BigDecimal workHr) {
        this.workHr = workHr;
    }

    
    @Column(name="REMARK1", length=200)
    public String getRemark1() {
        return this.remark1;
    }
    
    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    
    @Column(name="START_WORK", length=10)
    public String getStartWork() {
        return this.startWork;
    }
    
    public void setStartWork(String startWork) {
        this.startWork = startWork;
    }

    
    @Column(name="END_WORK", length=10)
    public String getEndWork() {
        return this.endWork;
    }
    
    public void setEndWork(String endWork) {
        this.endWork = endWork;
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




}

