package com.fet.crm.osp.platform.core.pojo;

import java.util.Date;

import javax.persistence.Column;

/**
 * SysOptionsCombo POJO
 * 
 * @author Adam Yeh
 *
 */
public class SysOptionsComboPOJO {

    private String comboType;
    private String comboValue;
	private String comboText;
	private String comboContent;
	private String description;
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;

	public String getComboText() {
		return this.comboText;
	}

	public void setComboText(String comboText) {
		this.comboText = comboText;
	}

	public String getComboContent() {
		return this.comboContent;
	}

	public void setComboContent(String comboContent) {
		this.comboContent = comboContent;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_USER", nullable = false, length = 20)
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

	public String getComboType() {
		return comboType;
	}

	public void setComboType(String comboType) {
		this.comboType = comboType;
	}

	public String getComboValue() {
		return comboValue;
	}

	public void setComboValue(String comboValue) {
		this.comboValue = comboValue;
	}

}