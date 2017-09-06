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

package com.fet.crm.osp.common.util;

import java.util.Date;
import java.util.UUID;

/**
 * 識別碼產生器. <br>
 * 
 * @author VJChou
 */
public class IdentifierIdUtil {

	private static final String OSP = "OSP";
	private static final String minus = "-";

	/**
	 * 產生OSP特有單號[ORDER_M_ID].<br>
	 * e.g. OSP-20170524-154644-6CBA632E3D6C4B7F
	 * 
	 * @return String
	 */
	public final static String getOspOrderMId() {
		StringBuffer idMaker = new StringBuffer();

		idMaker.append(OSP);
		idMaker.append(minus);
		idMaker.append(DateUtil.formatDt(new Date(), "yyyyMMdd-HHmmss"));
		idMaker.append(minus);

		// 配合Oracle語法產生出的UUID格式進行調整
		String seq = getUuid().replace("-", "").substring(0, 16).toUpperCase();

		idMaker.append(seq);

		return idMaker.toString();
	}

	/**
	 * 隨機產生36個字元的UUID. <br>
	 * 
	 * @return String
	 */
	public final static String getUuid() {
		String uuid = (UUID.randomUUID()).toString();

		return uuid;
	}

}
