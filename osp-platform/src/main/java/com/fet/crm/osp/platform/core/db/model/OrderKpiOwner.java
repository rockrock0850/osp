package com.fet.crm.osp.platform.core.db.model;
// Generated May 10, 2017 12:50:51 AM by Hibernate Tools 4.0.0


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
 * OrderKpiOwner generated by hbm2java
 */
@Entity
@Table(name="ORDER_KPI_OWNER")
public class OrderKpiOwner  implements java.io.Serializable {


     private OrderKpiOwnerId id;
     private Date createDate;
     private String createUser;

    public OrderKpiOwner() {
    }

	
    public OrderKpiOwner(OrderKpiOwnerId id, Date createDate) {
        this.id = id;
        this.createDate = createDate;
    }
    public OrderKpiOwner(OrderKpiOwnerId id, Date createDate, String createUser) {
       this.id = id;
       this.createDate = createDate;
       this.createUser = createUser;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="orderMId", column=@Column(name="ORDER_M_ID", nullable=false, length=36) ), 
        @AttributeOverride(name="kpiOwnerId", column=@Column(name="KPI_OWNER_ID", nullable=false, length=20) ) } )
    public OrderKpiOwnerId getId() {
        return this.id;
    }
    
    public void setId(OrderKpiOwnerId id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATE_DATE", nullable=false, length=7)
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    
    @Column(name="CREATE_USER", length=20)
    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }




}


