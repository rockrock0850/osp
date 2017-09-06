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

import java.util.List;

import com.fet.crm.osp.common.vo.kernel.result.SpecialOfferRtnVO;

/**
 * 查詢學生溫暖長青申請方案資訊回傳物件. <br>
 * 
 * @author RichardHuang
 */
public class SpecialOfferOutputVO extends AbstractOutputVO {
    
    private List<SpecialOfferRtnVO> resultBody;

    public List<SpecialOfferRtnVO> getResultBody() {
        return resultBody;
    }

    public void setResultBody(List<SpecialOfferRtnVO> resultBody) {
        this.resultBody = resultBody;
    }
    
}
