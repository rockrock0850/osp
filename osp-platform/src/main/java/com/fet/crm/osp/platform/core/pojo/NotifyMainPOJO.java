package com.fet.crm.osp.platform.core.pojo;

import java.util.Date;

import javax.persistence.Column;

/**
 * SysOptionsCombo POJO
 * 
 * @author Adam Yeh
 *
 */
public class NotifyMainPOJO {

	private String notifyMainId;
	private String notifyType;
	private String notifyFunc;
	private String emailRecipients;
	private String emailCpRecipients;
	private String smsRecipients;
	private String hasSend;
	private String subject;
	private String content;
	private Date sendDate;
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;

	public String getNotifyMainId() {
		return this.notifyMainId;
	}

	public void setNotifyMainId(String notifyMainId) {
		this.notifyMainId = notifyMainId;
	}

	public String getNotifyType() {
		return this.notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getNotifyFunc() {
		return this.notifyFunc;
	}

	public void setNotifyFunc(String notifyFunc) {
		this.notifyFunc = notifyFunc;
	}

	public String getEmailRecipients() {
		return this.emailRecipients;
	}

	public void setEmailRecipients(String emailRecipients) {
		this.emailRecipients = emailRecipients;
	}

	@Column(name = "EMAIL_CP_RECIPIENTS", length = 2000)
	public String getEmailCpRecipients() {
		return this.emailCpRecipients;
	}

	public void setEmailCpRecipients(String emailCpRecipients) {
		this.emailCpRecipients = emailCpRecipients;
	}

	public String getSmsRecipients() {
		return this.smsRecipients;
	}

	public void setSmsRecipients(String smsRecipients) {
		this.smsRecipients = smsRecipients;
	}

	public String getHasSend() {
		return this.hasSend;
	}

	public void setHasSend(String hasSend) {
		this.hasSend = hasSend;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
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