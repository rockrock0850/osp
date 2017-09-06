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

package com.fet.crm.osp.platform.core.common.comparator;

import java.util.Comparator;

import com.fet.crm.osp.platform.core.vo.systeminfo.menu.AbstractMenuBaseVO;

/**
 * 
 * @author Lawrence.Lai
 */
public class MenuSortComparator implements Comparator<AbstractMenuBaseVO> {

	@Override
	public int compare(AbstractMenuBaseVO item1, AbstractMenuBaseVO item2) {
		int item1Sort = item1.getSortSequence();
		int item2Sort = item2.getSortSequence();

		return (item1Sort - item2Sort);
	}

}

