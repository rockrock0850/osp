/**
 * Copyright (c) 2013 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */
package com.fet.crm.osp.platform.core.db.condition.api;

public enum Order {
	
	DESC("DESC"), ASC("ASC");
	
	private String value;
	
	private Order(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public static Order getOrder(String value) {
		for(Order o : values()) {
			if(o.getValue().equals(value)) {
				return o;
			}
		}
		
		return DESC;
	}
	
}
