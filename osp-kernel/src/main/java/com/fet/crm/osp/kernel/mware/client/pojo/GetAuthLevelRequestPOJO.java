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
package com.fet.crm.osp.kernel.mware.client.pojo;

/**
 * @author RichardHuang
 */
public class GetAuthLevelRequestPOJO {

	private ParamAuthInfoPOJO paramAuthInfoPOJO;
	
	private ParamTransInfoPOJO paramTransInfoPOJO;
	
	private String flowType;
	
	private ParamAuthLevelParameterPOJO paramAuthLevelParameterPOJO;

	/**
	 * @return the paramAuthInfoPOJO
	 */
	public ParamAuthInfoPOJO getParamAuthInfoPOJO() {
		return paramAuthInfoPOJO;
	}

	/**
	 * @param paramAuthInfoPOJO the paramAuthInfoPOJO to set
	 */
	public void setParamAuthInfoPOJO(ParamAuthInfoPOJO paramAuthInfoPOJO) {
		this.paramAuthInfoPOJO = paramAuthInfoPOJO;
	}

	/**
	 * @return the paramTransInfoPOJO
	 */
	public ParamTransInfoPOJO getParamTransInfoPOJO() {
		return paramTransInfoPOJO;
	}

	/**
	 * @param paramTransInfoPOJO the paramTransInfoPOJO to set
	 */
	public void setParamTransInfoPOJO(ParamTransInfoPOJO paramTransInfoPOJO) {
		this.paramTransInfoPOJO = paramTransInfoPOJO;
	}

	/**
	 * @return the flowType
	 */
	public String getFlowType() {
		return flowType;
	}

	/**
	 * @param flowType the flowType to set
	 */
	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	/**
	 * @return the paramAuthLevelParameterPOJO
	 */
	public ParamAuthLevelParameterPOJO getParamAuthLevelParameterPOJO() {
		return paramAuthLevelParameterPOJO;
	}

	/**
	 * @param paramAuthLevelParameterPOJO the paramAuthLevelParameterPOJO to set
	 */
	public void setParamAuthLevelParameterPOJO(ParamAuthLevelParameterPOJO paramAuthLevelParameterPOJO) {
		this.paramAuthLevelParameterPOJO = paramAuthLevelParameterPOJO;
	}
	
}
