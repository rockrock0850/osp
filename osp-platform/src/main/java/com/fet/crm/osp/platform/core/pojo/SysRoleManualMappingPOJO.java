package com.fet.crm.osp.platform.core.pojo;

import java.util.Date;

/**
 * SysRoleManualMapping POJO
 * 
 * @author AndrewLee
 *
 */
public class SysRoleManualMappingPOJO {

    private String roleId;
    private String empno;
    private String roleName;
    private String empname;
    private String deptcode;
    private String deptchiname;
    private Date createDate;
    private String createUser;
    private Date updateDate;
    private String updateUser;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getDeptchiname() {
        return deptchiname;
    }

    public void setDeptchiname(String deptchiname) {
        this.deptchiname = deptchiname;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

}
