/**
 * Copyright (c) 2017 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */

package com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;

/**
 * 頁面內容訊資 封裝物件
 * 
 * @author PaulChen
 */
public class BuzStepPageVO extends AbstractOspBaseVO {

	private String stepId; // step id
	private String contentId;
	private String contentNm; // 區塊名稱
	private String sortSequence; // 排序
	private String template; // 樣版名稱（INCLUDE_TYPE）
	private String link; // 連結
	private String openBrowser; // 開啟瀏覽器
	private String parameter;
	private String httpMethod;
	private String encriptionMethod;
	private String encriptionKey;
	private String ospKey;
	

	public BuzStepPageVO() {

	}

	public String getContentNm() {
		return contentNm;
	}

	public void setContentNm(String contentNm) {
		this.contentNm = contentNm;
	}

	public String getSortSequence() {
		return sortSequence;
	}

	public void setSortSequence(String sortSequence) {
		this.sortSequence = sortSequence;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getOpenBrowser() {
		return openBrowser;
	}

	public void setOpenBrowser(String openBrowser) {
		this.openBrowser = openBrowser;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getEncriptionKey() {
		return encriptionKey;
	}

	public void setEncriptionKey(String encriptionKey) {
		this.encriptionKey = encriptionKey;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getEncriptionMethod() {
		return encriptionMethod;
	}

	public void setEncriptionMethod(String encriptionMethod) {
		this.encriptionMethod = encriptionMethod;
	}

	public String getOspKey() {
		return ospKey;
	}

	public void setOspKey(String ospKey) {
		this.ospKey = ospKey;
	}

}
