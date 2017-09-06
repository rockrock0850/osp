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
package com.fet.crm.osp.common.vo.kernel.output;

import com.fet.crm.osp.common.vo.kernel.result.promotiondetail.PromotionDetailRtnVO;

/**
 * 促代相關資訊查詢回傳物件. <br>
 * 
 * @author RichardHuang
 */
public class PromotionDetailOutputVO extends AbstractOutputVO {
    
    private PromotionDetailRtnVO resultBody;

    public PromotionDetailRtnVO getResultBody() {
        return resultBody;
    }

    public void setResultBody(PromotionDetailRtnVO resultBody) {
        this.resultBody = resultBody;
    }
    
}
