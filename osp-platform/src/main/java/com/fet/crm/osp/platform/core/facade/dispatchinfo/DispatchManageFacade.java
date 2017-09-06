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

package com.fet.crm.osp.platform.core.facade.dispatchinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fet.crm.osp.platform.core.service.dispatchinfo.IDispatchMemberService;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderDispatchVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.PauseDispatchVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.OrderDispatchCVO;

/**
 * [分派管理] 總體服務窗口
 * 
 * @author LawrenceLai,AndrewLee
 */
@Service
public class DispatchManageFacade {

    @Autowired
    private IDispatchMemberService dispatchMemberservice;

    /**
     * 查詢 分派資訊<br>
     * 1.pauseStartTime
     * 2.pauseEndTime
     * 
     * @param dispatchCVO
     * @return OrderDispatchVO
     */
    @Transactional(readOnly = true)
    public List<OrderDispatchVO> getDispatchInfo(OrderDispatchCVO dispatchCVO) {
        return dispatchMemberservice.queryDispatchInfo(dispatchCVO);
    }

    /**
     * 執行 暫停案件指派<br>
     * 1.dispMemberId
     * 2.empno
     * 3.pauseStartTime
     * 4.pauseEndTime
     * 5.active
     * 
     * @param assignVOLs
     * @return boolean
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean excutePauseDispatch(List<PauseDispatchVO> pauseDispatchVOLs) {
        return dispatchMemberservice.excutePauseDispatch(pauseDispatchVOLs);
    }

}
