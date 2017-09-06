package com.fet.crm.osp.platform.core.pojo;

import java.util.Date;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;
import com.fet.crm.osp.platform.core.db.model.RefStaffId;

/**
 * SysOptionsCombo POJO
 * 
 * @author Adam Yeh
 *
 */
public class RefStaffPOJO extends AbstractOspBaseVO{

    private RefStaffId id;
    private String empname;
    private String engname;
    private String aliasname;
    private String email;
    private String headEmpno;
    private String headEmpname;
    private String headEmail;
    private Date createDate;
    private String createUser;

   public RefStaffId getId() {
       return this.id;
   }
   
   public void setId(RefStaffId id) {
       this.id = id;
   }

   public String getEmpname() {
       return this.empname;
   }
   
   public void setEmpname(String empname) {
       this.empname = empname;
   }

   public String getEngname() {
       return this.engname;
   }
   
   public void setEngname(String engname) {
       this.engname = engname;
   }

   public String getAliasname() {
       return this.aliasname;
   }
   
   public void setAliasname(String aliasname) {
       this.aliasname = aliasname;
   }

   public String getEmail() {
       return this.email;
   }
   
   public void setEmail(String email) {
       this.email = email;
   }

   public String getHeadEmpno() {
       return this.headEmpno;
   }
   
   public void setHeadEmpno(String headEmpno) {
       this.headEmpno = headEmpno;
   }

   public String getHeadEmpname() {
       return this.headEmpname;
   }
   
   public void setHeadEmpname(String headEmpname) {
       this.headEmpname = headEmpname;
   }

   public String getHeadEmail() {
       return this.headEmail;
   }
   
   public void setHeadEmail(String headEmail) {
       this.headEmail = headEmail;
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

}