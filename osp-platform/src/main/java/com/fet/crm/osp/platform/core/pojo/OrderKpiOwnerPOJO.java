package com.fet.crm.osp.platform.core.pojo;

import java.util.Date;

/**
 * OrderKpiOwner POJO
 * 
 * @author Adam Yeh
 *
 */
public class OrderKpiOwnerPOJO {

    private String orderMId;
    private String kpiOwnerId;
	private Date createDate;
	private String createUser;

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

	public String getOrderMId() {
		return orderMId;
	}

	public void setOrderMId(String orderMId) {
		this.orderMId = orderMId;
	}

	public String getKpiOwnerId() {
		return kpiOwnerId;
	}

	public void setKpiOwnerId(String kpiOwnerId) {
		this.kpiOwnerId = kpiOwnerId;
	}

}