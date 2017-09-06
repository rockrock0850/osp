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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.condition.Condition;
import com.fet.crm.osp.platform.core.db.condition.api.Restrictions;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.db.warehouse.SkillManageWareHouse;
import com.fet.crm.osp.platform.core.pojo.SysSkillMemberRefPOJO;
import com.fet.crm.osp.platform.core.service.dispatchinfo.ISkillManageService;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.SkillInfoVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.SkillMemberRefVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.SkillOrderTypeMapVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.condition.SysSkillMemberRefCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.MemberInfoVO;

/**
 * [人員貼標] 服務界面
 *
 * @author AndrewLee, AllenChen
 */
@Service
public class SkillManageServiceImpl implements ISkillManageService {

    @Autowired
    private JdbcDAO jdbcDAO;

    @Autowired
    private SkillManageWareHouse skillManageWareHouse;

    @Override
    public List<SkillMemberRefVO> queryMemberMappingInfo(SysSkillMemberRefCVO skillMemberRefCVO) {
        List<SkillMemberRefVO> rtnData = new ArrayList<>();
        
        if (skillMemberRefCVO != null) {
            String sqlText = ResourceFileUtil.SQL_SKILL.getResource("QUERY_MEMBER_MAPPING_BY_CONDITION");

            Condition condition = getQueryMemberMappingInfoCondition(skillMemberRefCVO);
            sqlText = condition.getCompleteSQL(sqlText);

            Map<String, Object> params = condition.getParams();

            rtnData = jdbcDAO.queryForBean(sqlText, params, SkillMemberRefVO.class);
        }

        return rtnData;
    }

    @Override
    public List<SkillInfoVO> queryAllSkillInfo() {
        String sqlText = ResourceFileUtil.SQL_SKILL.getResource("QUERY_ALL_SKILL_INFO");

        List<SkillInfoVO> rtnData = jdbcDAO.queryForBean(sqlText, null, SkillInfoVO.class);

        return rtnData;
    }

    @Override
    public boolean excuteStickSkill(List<SkillMemberRefVO> skillMemberRefVoLs) {
        if (CollectionUtils.isNotEmpty(skillMemberRefVoLs)) {

        	/*	先註解 for Bug #112 【人員 SKILL 貼標】新增貼標人員後，會將原已存在的貼標人員刪除
        	// 執行DELETE 將所屬該技能貼標的資料清除掉
            Map<String, Object> params = new HashMap<>();

            String sqlText = ResourceFileUtil.SQL_SKILL.getResource("DELETE_SKILL_MEMBER_REF_BY_SKILL_ID");

            //由於刪除的都是同一個SKILL_ID.所以只取第一筆作為SQL的WHERE 查詢條件
            params.put("SKILL_ID", skillMemberRefVoLs.get(0).getSkillId());

            jdbcDAO.update(sqlText, params);
            */

            List<SysSkillMemberRefPOJO> pojoLs = new ArrayList<>();

            for (SkillMemberRefVO vo : skillMemberRefVoLs) {
                String skillId = vo.getSkillId();
                String skillName = vo.getSkillName();
                String empNo = vo.getEmpNo();
                String empname = vo.getEmpName();
                String createUser = vo.getCreateUser();
                String updateUser = createUser;

                SysSkillMemberRefPOJO pojo = new SysSkillMemberRefPOJO();
                
                pojo.setSkillId(skillId);
                pojo.setSkillName(skillName);
                pojo.setEmpNo(empNo);
                pojo.setEmpname(empname);
                pojo.setCreateUser(createUser);
                pojo.setUpdateUser(updateUser);
                pojo.setCreateDate(new Date());
                pojo.setUpdateDate(new Date());

                pojoLs.add(pojo);
            }
            skillManageWareHouse.batchSave(pojoLs);
        }

        return true;
    }

    @Override
    public List<SkillOrderTypeMapVO> querySkillOrderTypeMap() {
        String sqlText = ResourceFileUtil.SQL_SKILL.getResource("QUERY_ALL_SKILL_ORDER_TYPE");

        List<SkillOrderTypeMapVO> rtnData = jdbcDAO.queryForBean(sqlText, null, SkillOrderTypeMapVO.class);

        return rtnData;
    }
    
    @Override
	public List<MemberInfoVO> queryMemberWithoutSpecificSkill(String skillId) {
    	String sqlText = ResourceFileUtil.SQL_SKILL.getResource("QUERY_MEMBER_WITHOUT_SKILL");
		
    	Map<String, Object> params = new HashMap<>();
    	params.put("SKILL_ID", skillId);
    	
		List<MemberInfoVO> dataList = jdbcDAO.queryForList(sqlText, params, MemberInfoVO.class);
		
		if (!CollectionUtils.isEmpty(dataList)) {
			return dataList;
		}
		
		return Collections.emptyList();
	}

    /**
     * 取得查詢條件
     * 
     * @param cvo
     * @return Condition
     */
    private Condition getQueryMemberMappingInfoCondition(SysSkillMemberRefCVO cvo) {
        String skillId = cvo.getSkillId();
        String empName = cvo.getEmpName();
        String empNo = cvo.getEmpNo();

        Condition condition = new Condition();

        if (StringUtils.isNotBlank(skillId)) {
            condition.and(Restrictions.eq("SKILL_ID", skillId));
        }
        
        if (StringUtils.isNotBlank(empName)) {
            condition.and(Restrictions.like("EMPNAME", empName));
        }
        
        if (StringUtils.isNotBlank(empNo)) {
            condition.and(Restrictions.eq("EMPNO", empNo));
        }

        return condition;
    }

}
