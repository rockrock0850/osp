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

import com.fet.crm.osp.common.vo.kernel.result.CustInfoForAppPartRtnVO;

/**
 * 查詢 AppPart 啟動所需客資結果回傳物件. <br>
 * 
 * @author RichardHuang
 */
public class CustInfoForAppPartOutputVO extends AbstractOutputVO {
    
    private CustInfoForAppPartRtnVO resultBody; // 回傳內容為新增成功後產生的poolKey

    public CustInfoForAppPartRtnVO getResultBody() {
        return resultBody;
    }

    public void setResultBody(CustInfoForAppPartRtnVO resultBody) {
        this.resultBody = resultBody;
    }

}
