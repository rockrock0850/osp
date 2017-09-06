package com.fet.crm.osp.platform.core.db.model;
// Generated 2017/2/24 �U�� 01:10:48 by Hibernate Tools 4.0.0


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
 * SysShiftContent generated by hbm2java
 */
@Entity
@Table(name="SYS_SHIFT_CONTENT")
public class SysShiftContent  implements java.io.Serializable {


     private SysShiftContentId id;
     private String dtYear;
     private String dtMonth;
     private String dtDay;
     private String empname;
     private String ntAccount;
     private Date createDate;
     private String createUser;

    public SysShiftContent() {
    }

	
    public SysShiftContent(SysShiftContentId id, Date createDate, String createUser) {
        this.id = id;
        this.createDate = createDate;
        this.createUser = createUser;
    }
    public SysShiftContent(SysShiftContentId id, String dtYear, String dtMonth, String dtDay, String empname, String ntAccount, Date createDate, String createUser) {
       this.id = id;
       this.dtYear = dtYear;
       this.dtMonth = dtMonth;
       this.dtDay = dtDay;
       this.empname = empname;
       this.ntAccount = ntAccount;
       this.createDate = createDate;
       this.createUser = createUser;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="workDate", column=@Column(name="WORK_DATE", nullable=false, length=7) ), 
        @AttributeOverride(name="shiftTypeId", column=@Column(name="SHIFT_TYPE_ID", nullable=false, length=40) ), 
        @AttributeOverride(name="empno", column=@Column(name="EMPNO", nullable=false, length=20) ) } )
    public SysShiftContentId getId() {
        return this.id;
    }
    
    public void setId(SysShiftContentId id) {
        this.id = id;
    }

    
    @Column(name="DT_YEAR", length=4)
    public String getDtYear() {
        return this.dtYear;
    }
    
    public void setDtYear(String dtYear) {
        this.dtYear = dtYear;
    }

    
    @Column(name="DT_MONTH", length=2)
    public String getDtMonth() {
        return this.dtMonth;
    }
    
    public void setDtMonth(String dtMonth) {
        this.dtMonth = dtMonth;
    }

    
    @Column(name="DT_DAY", length=2)
    public String getDtDay() {
        return this.dtDay;
    }
    
    public void setDtDay(String dtDay) {
        this.dtDay = dtDay;
    }

    
    @Column(name="EMPNAME", length=40)
    public String getEmpname() {
        return this.empname;
    }
    
    public void setEmpname(String empname) {
        this.empname = empname;
    }

    
    @Column(name="NT_ACCOUNT", length=40)
    public String getNtAccount() {
        return this.ntAccount;
    }
    
    public void setNtAccount(String ntAccount) {
        this.ntAccount = ntAccount;
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

