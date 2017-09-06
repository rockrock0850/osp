package com.fet.crm.osp.common.vo.kernel.result.checkCustInfo;

import java.util.Date;

/**
 * 帳務詳細資訊封裝物件(用以顯示核資的帳務詳細資訊). <br>
 * 
 * @author AllenChen
 */
public class BillDetailVO {

	private String invoiceType; // 本期帳單類別
	private String totalInvoiceAmount; // 本期電信&小額新增費用
	private Date statementDate; // 本期帳單結算日
	private Date dueDay; // 繳費截止日
	private String subBE; // 帳單類型
	private String totalAmount; // 本期新增費用
	private String invoiceBalance; // 本期未繳餘額
	private String billingInvoiceNumber; // 本期帳單編號

	private String sumAmount; // 總新增費用
	private String sumBalance; // 當期未繳餘額

	private int sortNumber; // 用來排序

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getTotalInvoiceAmount() {
		return totalInvoiceAmount;
	}

	public void setTotalInvoiceAmount(String totalInvoiceAmount) {
		this.totalInvoiceAmount = totalInvoiceAmount;
	}

	public Date getStatementDate() {
		return statementDate;
	}

	public void setStatementDate(Date statementDate) {
		this.statementDate = statementDate;
	}

	public Date getDueDay() {
		return dueDay;
	}

	public void setDueDay(Date dueDay) {
		this.dueDay = dueDay;
	}

	public String getSubBE() {
		return subBE;
	}

	public void setSubBE(String subBE) {
		this.subBE = subBE;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getInvoiceBalance() {
		return invoiceBalance;
	}

	public void setInvoiceBalance(String invoiceBalance) {
		this.invoiceBalance = invoiceBalance;
	}

	public String getBillingInvoiceNumber() {
		return billingInvoiceNumber;
	}

	public void setBillingInvoiceNumber(String billingInvoiceNumber) {
		this.billingInvoiceNumber = billingInvoiceNumber;
	}

	public String getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(String sumAmount) {
		this.sumAmount = sumAmount;
	}

	public String getSumBalance() {
		return sumBalance;
	}

	public void setSumBalance(String sumBalance) {
		this.sumBalance = sumBalance;
	}

	public int getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}

}
