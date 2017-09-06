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
package com.fet.crm.osp.kernel.mware.client.coes;

import com.fet.crm.osp.common.vo.kernel.result.promotiondetail.PromotionDetailRtnVO;

/**
 * @author RichardHuang
 */
public interface ICARServiceClient {

	/**
	 * 類型: 查詢. <br>
     * 取得 SPV 促代資訊. <br>
	 * 
	 * @param promotionId
	 * @return PromotionDetailRtnVO
	 */
	PromotionDetailRtnVO getPromotionDetail(String promotionId);
	
}
