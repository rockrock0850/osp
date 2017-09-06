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

package com.fet.crm.osp.platform.core.facade.systeminfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.platform.core.service.systeminfo.IBuzVPosCompareService;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.content.compare.BuzVPOSCompareResultVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.content.compare.BuzVPOSCompareVO;

/**
 * [VPOS AIMS 資料對比] 服務界面
 *
 * @author AndrewLee
 */
@Service
public class BuzVPosCompareFacade {
    
    @Autowired
    private IBuzVPosCompareService compareService;
    
    @Autowired
    private ThreadPoolTaskExecutor executor;

    /**
     * 呼叫Thread去執行Excel檢核
     * 
     * @param buzVPOSCompareVO
     * @return boolean
     */
    public boolean doExcelCompare(BuzVPOSCompareVO buzVPOSCompareVO) {
        executor.execute(new Runner(buzVPOSCompareVO));
        
        return true;
    }

    /**
     * 取得檢核結果
     * 
     * @return List
     */
    @Transactional(readOnly = true)
    public List<BuzVPOSCompareResultVO> getCompareResult() {
        return compareService.queryCompareResult();
    }
    

    /**
     * 執行Thread,檢核Excel檔案.之後在把結果Insert至DB
     * 
     * @param buzVPOSCompareVO
     */
    @Transactional(propagation = Propagation.REQUIRED)
    private void doExcelCompareByThread(BuzVPOSCompareVO buzVPOSCompareVO) {
        compareService.doCompareExcel(buzVPOSCompareVO);
    }
    
    
    /**
     * 執行緒 內部類別
     * 
     * @author AndrewLee
     *
     */
    private class Runner implements Runnable {
        
        private BuzVPOSCompareVO buzVPOSCompareVO;
        
         Runner(BuzVPOSCompareVO buzVPOSCompareVO) {
             this.buzVPOSCompareVO = buzVPOSCompareVO;
        }

        @Override
        public void run() {
            BuzVPosCompareFacade.this.doExcelCompareByThread(buzVPOSCompareVO);
        }
        
    }
    
}
