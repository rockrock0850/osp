package com.fet.crm.osp.platform.core.db.model;
// Generated 2017/3/6 下午 02:26:19 by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SysShiftAttachment generated by hbm2java
 */
@Entity
@Table(name="SYS_SHIFT_ATTACHMENT")
public class SysShiftAttachment  implements java.io.Serializable {

     private String fileName;
     private byte[] content;
     private Date createDate;
     private String createUser;
     private String dtYear;
     private String dtMonth;
     private String fileExtensions;

    public SysShiftAttachment() {
    }

	
    public SysShiftAttachment(String fileName, Date createDate, String createUser, String dtYear, String dtMonth, String fileExtensions) {
        this.fileName = fileName;
        this.createDate = createDate;
        this.createUser = createUser;
        this.dtYear = dtYear;
        this.dtMonth = dtMonth;
        this.fileExtensions = fileExtensions;
    }
    public SysShiftAttachment(String fileName, byte[] content, Date createDate, String createUser, String dtYear, String dtMonth, String fileExtensions) {
       this.fileName = fileName;
       this.content = content;
       this.createDate = createDate;
       this.createUser = createUser;
       this.dtYear = dtYear;
       this.dtMonth = dtMonth;
       this.fileExtensions = fileExtensions;
    }
   
     @Id 

    
    @Column(name="FILE_NAME", unique=true, nullable=false, length=100)
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
    @Column(name="CONTENT")
    public byte[] getContent() {
        return this.content;
    }
    
    public void setContent(byte[] content) {
        this.content = content;
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

    
    @Column(name="DT_YEAR", nullable=false, length=4)
    public String getDtYear() {
        return this.dtYear;
    }
    
    public void setDtYear(String dtYear) {
        this.dtYear = dtYear;
    }

    
    @Column(name="DT_MONTH", nullable=false, length=2)
    public String getDtMonth() {
        return this.dtMonth;
    }
    
    public void setDtMonth(String dtMonth) {
        this.dtMonth = dtMonth;
    }

    
    @Column(name="FILE_EXTENSIONS", nullable=false, length=5)
    public String getFileExtensions() {
        return this.fileExtensions;
    }
    
    public void setFileExtensions(String fileExtensions) {
        this.fileExtensions = fileExtensions;
    }

}


