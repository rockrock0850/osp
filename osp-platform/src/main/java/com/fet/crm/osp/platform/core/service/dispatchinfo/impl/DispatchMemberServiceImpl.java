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

package com.fet.crm.osp.platform.core.service.dispatchinfo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.common.util.DateUtil;
import com.fet.crm.osp.platform.core.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.db.warehouse.DispatchMemberWareHouse;
import com.fet.crm.osp.platform.core.pojo.DispatchMemberConfigPOJO;
import com.fet.crm.osp.platform.core.service.dispatchinfo.IDispatchMemberService;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderDispatchVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.PauseDispatchVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.OrderDispatchCVO;

/**
 * [分派人員維護] 服務界面
 *
 * @author AndrewLee
 */
@Service
public class DispatchMemberServiceImpl implements IDispatchMemberService {

    @Autowired
    private JdbcDAO jdbcDAO;

    @Autowired
    private DispatchMemberWareHouse dispatchMemberWareHouse;

    @Override
    public List<OrderDispatchVO> queryDispatchInfo(OrderDispatchCVO dispatchCVO) {
        List<OrderDispatchVO> rtnLs = new ArrayList<>();

        if (dispatchCVO != null) {
            String queryDateStart = dispatchCVO.getPauseStartTime();
            String queryDateEnd = dispatchCVO.getPauseEndTime();

            String sqlText = ResourceFileUtil.SQL_ORDER.getResource("QUERY_DISPATCH_MEMBER_LIST_BY_DATE");

            Map<String, Object> paramMp = new HashMap<>();
            paramMp.put("PAUSE_START_TIME", queryDateStart);
            paramMp.put("PAUSE_END_TIME", queryDateEnd);

            List<Map<String, Object>> dataLs = jdbcDAO.queryForList(sqlText, paramMp);

            for (Map<String, Object> map : dataLs) {
                OrderDispatchVO orderDispatchVO = new OrderDispatchVO();
                String dispMemberId = MapUtils.getString(map, "DISP_MEMBER_ID");
                String empno = MapUtils.getString(map, "EMPNO");
                String empname = MapUtils.getString(map, "EMPNAME");
                String pauseStartTime = MapUtils.getString(map, "PAUSE_START_TIME");
                String pauseEndTime = MapUtils.getString(map, "PAUSE_END_TIME");
                String active = MapUtils.getString(map, "ACTIVE");

                orderDispatchVO.setDispMemberId(dispMemberId);
                orderDispatchVO.setEmpNo(empno);
                orderDispatchVO.setEmpName(empname);
                orderDispatchVO.setPauseStartTime(pauseStartTime);
                orderDispatchVO.setPauseEndTime(pauseEndTime);
                orderDispatchVO.setActive(active);

                rtnLs.add(orderDispatchVO);
            }

        }

        return rtnLs;
    }

    @Override
    public boolean excutePauseDispatch(List<PauseDispatchVO> pauseDispatchList) {
        if (CollectionUtils.isNotEmpty(pauseDispatchList)) {
            List<DispatchMemberConfigPOJO> dataList = new ArrayList<>();
            
            for (PauseDispatchVO pauseDispatchVO : pauseDispatchList) {
            	String empNo = pauseDispatchVO.getEmpNo();
            	String empName = pauseDispatchVO.getEmpName();
            	String pauseStartTime = pauseDispatchVO.getPauseStartTime();
            	String pauseEndTime = pauseDispatchVO.getPauseEndTime();
            	String createUser = pauseDispatchVO.getCreateUser();
            	String updateUser = pauseDispatchVO.getCreateUser();
            	
            	DispatchMemberConfigPOJO pojo = new DispatchMemberConfigPOJO();

                pojo.setDispMemberId(IdentifierIdUtil.getUuid());
                pojo.setEmpno(empNo);
                pojo.setEmpname(empName);
                pojo.setPauseStartTime(DateUtil.toDate(pauseStartTime, "yyyy-MM-dd HH:mm"));
                pojo.setPauseEndTime(DateUtil.toDate(pauseEndTime, "yyyy-MM-dd HH:mm"));
                pojo.setActive("Y");
                pojo.setCreateUser(createUser);
                pojo.setCreateDate(new Date());
                pojo.setUpdateUser(updateUser);
                pojo.setUpdateDate(new Date());
                
                dataList.add(pojo);

                dispatchMemberWareHouse.saveInBatch(dataList);
            }
        }

        return true;
    }
    
}
