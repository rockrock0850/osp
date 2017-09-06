package com.fet.crm.osp.platform.core.pojo;

import java.util.Date;

/**
 * NotifyDetail POJO
 * 
 * @author Adam Yeh
 *
 */
public class NotifyDetailPOJO {

	private String notifyDetailId;
	private String notifyMainId;
	private String orderMId;
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;

	public String getNotifyDetailId() {
		return this.notifyDetailId;
	}

	public void setNotifyDetailId(String notifyDetailId) {
		this.notifyDetailId = notifyDetailId;
	}

	public String getNotifyMainId() {
		return this.notifyMainId;
	}

	public void setNotifyMainId(String notifyMainId) {
		this.notifyMainId = notifyMainId;
	}

	public String getOrderMId() {
		return this.orderMId;
	}

	public void setOrderMId(String orderMId) {
		this.orderMId = orderMId;
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

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}