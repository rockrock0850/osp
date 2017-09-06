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
 * SysRoleManualMapping generated by hbm2java
 */
@Entity
@Table(name="SYS_ROLE_MANUAL_MAPPING")
public class SysRoleManualMapping  implements java.io.Serializable {


     private SysRoleManualMappingId id;
     private String roleName;
     private String empname;
     private String deptcode;
     private String deptchiname;
     private Date createDate;
     private String createUser;
     private Date updateDate;
     private String updateUser;

    public SysRoleManualMapping() {
    }

	
    public SysRoleManualMapping(SysRoleManualMappingId id, String roleName, String empname, Date createDate, String createUser) {
        this.id = id;
        this.roleName = roleName;
        this.empname = empname;
        this.createDate = createDate;
        this.createUser = createUser;
    }
    public SysRoleManualMapping(SysRoleManualMappingId id, String roleName, String empname, String deptcode, String deptchiname, Date createDate, String createUser, Date updateDate, String updateUser) {
       this.id = id;
       this.roleName = roleName;
       this.empname = empname;
       this.deptcode = deptcode;
       this.deptchiname = deptchiname;
       this.createDate = createDate;
       this.createUser = createUser;
       this.updateDate = updateDate;
       this.updateUser = updateUser;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="roleId", column=@Column(name="ROLE_ID", nullable=false, length=20) ), 
        @AttributeOverride(name="empno", column=@Column(name="EMPNO", nullable=false, length=20) ) } )
    public SysRoleManualMappingId getId() {
        return this.id;
    }
    
    public void setId(SysRoleManualMappingId id) {
        this.id = id;
    }

    
    @Column(name="ROLE_NAME", nullable=false, length=30)
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    
    @Column(name="EMPNAME", nullable=false, length=40)
    public String getEmpname() {
        return this.empname;
    }
    
    public void setEmpname(String empname) {
        this.empname = empname;
    }

    
    @Column(name="DEPTCODE", length=40)
    public String getDeptcode() {
        return this.deptcode;
    }
    
    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    
    @Column(name="DEPTCHINAME", length=150)
    public String getDeptchiname() {
        return this.deptchiname;
    }
    
    public void setDeptchiname(String deptchiname) {
        this.deptchiname = deptchiname;
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


