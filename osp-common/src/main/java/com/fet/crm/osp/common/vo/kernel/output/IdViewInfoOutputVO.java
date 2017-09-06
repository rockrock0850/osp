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

import com.fet.crm.osp.common.vo.kernel.result.idviewinfo.IdViewInfoRtnVO;

/**
 * 查詢歷史客資資訊回傳物件. <br>
 * 
 * @author RichardHuang
 */
public class IdViewInfoOutputVO extends AbstractOutputVO {
    
    private IdViewInfoRtnVO resultBody;

    public IdViewInfoRtnVO getResultBody() {
        return resultBody;
    }

    public void setResultBody(IdViewInfoRtnVO resultBody) {
        this.resultBody = resultBody;
    }
    
}
