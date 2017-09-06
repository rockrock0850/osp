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

package com.fet.crm.osp.platform.webapp.action.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fet.crm.nsp.generic.model.cache.Agent;
import com.fet.crm.nsp.generic.model.cache.BackupModeFlag;
import com.fet.crm.nsp.generic.model.cache.GlobalSessionKeys;
import com.fet.crm.nsp.generic.model.cache.OwnInfo;
import com.fet.crm.nsp.generic.model.cache.agent.Role;
import com.fet.crm.nsp.generic.model.cache.defined.CurrUsingInfo;
import com.fet.crm.nsp.generic.util.IDUtil;
import com.fet.crm.nsp.generic.util.NSPCacheUtil;
import com.fet.crm.osp.common.util.DateUtil;
import com.fet.crm.osp.platform.core.common.Constant;
import com.fet.crm.osp.platform.core.common.ldap.FetLdapUtil;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.facade.systeminfo.HRManageFacade;
import com.fet.crm.osp.platform.core.facade.systeminfo.RolePermissionsFacade;
import com.fet.crm.osp.platform.core.vo.systeminfo.MemberInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.RoleInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.UserInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.agent.AgentInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.agent.GroupDefinitionVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.agent.InternaluseraccountVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.agent.StaffVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.agent.UsersVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.condition.RoleInfoCVO;
import com.fet.crm.osp.platform.mware.client.restful.proxy.OSPKernelRESTClientProxy;
import com.fet.crm.osp.platform.webapp.action.AbstractOSPAction;
import com.fet.crm.osp.platform.webapp.common.APPPartContext;
import com.fet.crm.osp.platform.webapp.common.util.ScopeUtil;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.logger.WebappLoggerFactory;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;
import com.fet.crm.osp.platform.webapp.vo.session.SessionVO;

/**
 * 
 * @author LawrenceLai
 */
@Controller
@RequestMapping("/session")
public class SessionAction extends AbstractOSPAction {
	
	private static Logger logger = WebappLoggerFactory.getLogger(SessionAction.class);

    @Autowired
    private RolePermissionsFacade rolePermissionsFacade;

	@Autowired 
	private HRManageFacade hRManageFacade;
	
	@Autowired
	private OSPKernelRESTClientProxy ospKernelRESTClientProxy;

	@RequestMapping(value="/service-login", method = RequestMethod.GET)
	public String serviceLogin() {
		HttpRequestHandler.put("IS_DEV_MODE", Constant.IS_DEV_MODE);
		
		return "tiles.session.login";
	}
	
	@RequestMapping(value="/excute-login", method = RequestMethod.POST)
	public String excuteLogin() {
	    Map<String, Object> paramMap = HttpRequestHandler.getJsonToMap();
		String ntAccount = MapUtils.getString(paramMap, "ntaccount", "");
		String password = MapUtils.getString(paramMap, "password", "");
	    String loginTime = DateUtil.formatDt(new Date(), "yyyy-MM-dd HH:mm:ss");

		HttpSessionHandler.put("IS_FIRST_LOGIN", null);

		// 「測試模式」：暫且將Account直接當UserId
        if (Constant.IS_DEV_MODE) {
        	String empNo = MapUtils.getString(paramMap, "empNo", "");
         
            UserInfoVO mockUserVo = new UserInfoVO();
            mockUserVo.setUserId(empNo);
         	mockUserVo.setNtAccount(ntAccount);

         	rollingUserInfoVO(mockUserVo);
         	
         	SessionVO sVO = new SessionVO();
            sVO.setAuthPass(true);
            sVO.setUserId(empNo);
         	sVO.setUserInfoVO(mockUserVo);
            sVO.setLoginDateTime(loginTime);

            HttpSessionHandler.putSessionInfo(sVO);
            
        	initAppartAgent(ntAccount);

            return "redirect:/console-index.action";
        }

		UserInfoVO userVo = FetLdapUtil.getAuthUser(ntAccount, password);
		boolean isPass = userVo.isPass();
		String userId = userVo.getUserId();
		
		 // LDAP：檢核失敗
        if (!isPass || StringUtils.isBlank(userId)) {
        	return "redirect:/session/service-login.action";
        }

        SessionVO sVO = new SessionVO();
        sVO.setAuthPass(isPass);
        sVO.setUserId(userId);
        sVO.setUserInfoVO(userVo);
        sVO.setLoginDateTime(loginTime);

        HttpSessionHandler.putSessionInfo(sVO);
        
    	initAppartAgent(ntAccount);

		return "redirect:/console-index.action";
	}

	@RequestMapping(value="/excute-logout", method = RequestMethod.GET)
	public String excuteLogout() {
		HttpSessionHandler.destory();

		return "redirect:/session/service-login.action";
	}

	private void rollingUserInfoVO(UserInfoVO userInfoVO) {
		if (userInfoVO != null) {
			String userId = userInfoVO.getUserId();
			
			RoleInfoCVO roleInfoCVO = new RoleInfoCVO();
			roleInfoCVO.setEmpNo(userId);
			
			MemberInfoVO memberVO = hRManageFacade.getMemberInfoByUserId(userId);
			String userNm = memberVO.getEmpName();
			String shiftType = hRManageFacade.getShiftTypeByUserIdAndSysDate(userId);;			
			List<RoleInfoVO> roleList = rolePermissionsFacade.queryRoleInfo(roleInfoCVO); 

			userInfoVO.setUserNm(userNm);
			userInfoVO.setShiftType(shiftType);
			userInfoVO.setRoleList(roleList);
		}
	}
	
	private void initAppartAgent(String ntAccount) {
		AgentInfoVO agentInfoVO = ospKernelRESTClientProxy.getAgentInfo(ntAccount);
		
		HttpSessionHandler.put("AGENT_INFO", agentInfoVO);
		
		if (agentInfoVO != null) {
			String ownId = IDUtil.genBPID();
			
			// Agent Info
			String agentId = null;
			String userId = null;
			String name = null;
			String englishName = null;
			String password = null;
			String email = null;
			String status = null;
			String isPasswordLocked = null;
			String position = null;
			String agentcode = null;
			
			// Staff Info
			String ntdomainAccountId = null;
			String staffAreaId = null;
			String staffId = null;
			
			// Group Definition
			String groupNumber = null;
			String groupName = null;
			String groupStatus = null;
			String groupDesc = null;
			
			// Channel Info
			String orderCartId 					= IDUtil.genBPID();
			String channelGroupId 				= APPPartContext.CHANNELGROUPID;	
			String channelIdPasswordPostpaid 	= APPPartContext.AMDOCS_ID_PASSWORD_POSTPAID; 	
			String channelIdPasswordPrepaid 	= APPPartContext.AMDOCS_ID_PASSWORD_POSTPAID;		
			String channelIdPostpaid 			= APPPartContext.AMDOCS_ID_POSTPAID;                 
			String channelIdPrepaid 			= APPPartContext.AMDOCS_ID_POSTPAID;                  
			String channelPostpaid 				= APPPartContext.AMDOCS_ID_POSTPAID;                    
			String channelPrepaid 				= APPPartContext.AMDOCS_ID_POSTPAID;                      
			String rsIaChannel 					= APPPartContext.CHANNEL_NCP;
			String rsIaModule 					= APPPartContext.CHANNEL_NCP;
			String needHintChangePw				= APPPartContext.NEED_HINT_CHANGE_PW;
			
			Date sysDate = new Date();
			
			InternaluseraccountVO internaluseraccountVO = agentInfoVO.getInternaluseraccountVO();
			StaffVO staffVO = agentInfoVO.getStaffVO();
			GroupDefinitionVO groupDefinitionVO =  agentInfoVO.getGroupDefinitionVO();
			
			channelGroupId = agentInfoVO.getChannelGroupId();
//			channelIdPasswordPostpaid = agentInfoVO.getCacheChannelIdPasswordPostpaid();
//			channelIdPasswordPrepaid = agentInfoVO.getCacheChannelIdPasswordPrepaid();
//			channelIdPostpaid = agentInfoVO.getCacheChannelIdPostpaid();
//			channelIdPrepaid = agentInfoVO.getCacheChannelIdPrepaid();
//			channelPostpaid = agentInfoVO.getCacheChannelPostpaid();
//			channelPrepaid = agentInfoVO.getCacheChannelPrepaid();
			
			if (internaluseraccountVO != null) {
				userId = internaluseraccountVO.getUserid();
				name = internaluseraccountVO.getName();
				englishName = internaluseraccountVO.getEnglishname();
				password = internaluseraccountVO.getPassword();
				email = internaluseraccountVO.getEmail();
				status = internaluseraccountVO.getStatus();
				isPasswordLocked = internaluseraccountVO.getIsPasswordLocked();
				position = internaluseraccountVO.getPosition();
				agentcode = internaluseraccountVO.getAgentcode();
				
				agentId = userId + "-" + IDUtil.genBPID();
			}
			
			if (staffVO != null) {
				ntdomainAccountId = staffVO.getNtdomainAccountId();
				staffAreaId = staffVO.getAreaId();
				staffId = String.valueOf(staffVO.getStaffId());
			}
			
			if (groupDefinitionVO != null) {
				groupNumber = String.valueOf(groupDefinitionVO.getGroupNumber());
				groupName = groupDefinitionVO.getGroupName();
				groupStatus = String.valueOf(groupDefinitionVO.getStatus());
				groupDesc = groupDefinitionVO.getGroupDesc();
			}
			
			OwnInfo ownInfo = new OwnInfo();
			ownInfo.setAgentId(agentId);
			ownInfo.setOrderCartId(orderCartId);
			
		    CurrUsingInfo usingInfo = new CurrUsingInfo();
	        usingInfo.setChannel(APPPartContext.CHANNEL_NCP);
	        
			GlobalSessionKeys globalSessionKeys = new GlobalSessionKeys();
			globalSessionKeys.setAccount(userId);
			globalSessionKeys.setAmdocsIdPostpaid(APPPartContext.AMDOCS_ID_POSTPAID);
			globalSessionKeys.setAmdocsIdPasswordPostpaid(APPPartContext.AMDOCS_ID_PASSWORD_POSTPAID);
			globalSessionKeys.setChannelGroupId(channelGroupId);
			globalSessionKeys.setChannelIdPasswordPostpaid(channelIdPasswordPostpaid);
			globalSessionKeys.setChannelIdPasswordPrepaid(channelIdPasswordPrepaid);
			globalSessionKeys.setChannelIdPostpaid(channelIdPostpaid);
			globalSessionKeys.setChannelIdPrepaid(channelIdPrepaid);
			globalSessionKeys.setChannelPostpaid(channelPostpaid);
			globalSessionKeys.setChannelPrepaid(channelPrepaid);
			globalSessionKeys.setEmpno(userId);
			globalSessionKeys.setExIvrcode(userId);
			globalSessionKeys.setGroupNumber(groupNumber);
			
			globalSessionKeys.setIvrcode(APPPartContext.AGENT_IVRCODE);
			globalSessionKeys.setPassword(password);
			globalSessionKeys.setRsIaChannel(rsIaChannel);
			globalSessionKeys.setRsIaModule(rsIaModule);
			globalSessionKeys.setSoftphoneAreaId(staffAreaId);
			globalSessionKeys.setStaffId(staffId);
			globalSessionKeys.setuEmplId(userId);
			globalSessionKeys.setUserId(userId);
			globalSessionKeys.setNtAccount(ntAccount);

			Agent agent = new Agent();
			agent.setGlobalSessionKeys(globalSessionKeys);
			agent.setAccount(userId);
			agent.setChannel(APPPartContext.CHANNEL_NCP);
			agent.setEmail(email);
			agent.setEnglishName(englishName);
			agent.setIsPasswordLocked(isPasswordLocked);
			agent.setIvrCode(APPPartContext.AGENT_IVRCODE);
			agent.setLoginTime(sysDate);
			agent.setName(name);
			agent.setNeedHintChangePw(needHintChangePw);
			agent.setPassword(password);
			agent.setRoleIdStr(groupNumber);
			agent.setStatus(status);
			agent.setUserAccount(userId);

			List<Role> roleArray = new ArrayList<Role>();
			roleArray.add(new Role(groupNumber, "", groupName));

			agent.setRole(roleArray);
			
			BackupModeFlag flag = new BackupModeFlag();
			flag.setBackupModeFlag(APPPartContext.BACKUP_MODE_FLAG);

			NSPCacheUtil.putCurrUsingInfo(orderCartId, usingInfo);
			NSPCacheUtil.putOwnInfo(ownId, ownInfo);
			NSPCacheUtil.putAgent(agentId, agent);
			NSPCacheUtil.putBackupModeFlag(flag);

			Cookie cookie_ncp = new Cookie("OWN_ID", ownId);
			cookie_ncp.setPath("/");
			cookie_ncp.setSecure(false);
			
			Cookie cookie_eb = new Cookie("tgt", agentId);
			cookie_eb.setPath("/");
			cookie_eb.setSecure(false);
			
			HttpServletResponse response = ScopeUtil.getHttpResponse();
			response.addCookie(cookie_ncp);
			response.addCookie(cookie_eb);

			HttpSession session = ScopeUtil.getHttpSession();
			session.setAttribute("sessionGroupId", agent.getGlobalSessionKeys().getGroupNumber());
			
			HttpSessionHandler.getSessionInfo().setOwnId(ownId);
		}
	}
	
	@RequestMapping(value="/agent-validate-warning", method = RequestMethod.GET)
	public String agentValidateWarning() {
		Map<String, Object> dataMap = new HashMap<>();

		// 若是null 表示第一次登入
		if (HttpSessionHandler.get("IS_FIRST_LOGIN") == null) {
			AgentInfoVO agentInfoVO = (AgentInfoVO) HttpSessionHandler.get("AGENT_INFO");

			StringBuffer agentAuthMessageSB = null;
			String breakLine = "<br>";

			if (agentInfoVO == null) {
				agentAuthMessageSB = new StringBuffer();
				agentAuthMessageSB.append("無法取得Agent操作權限!" + breakLine);
			}

			if (agentInfoVO != null) {
				agentAuthMessageSB = new StringBuffer();

				InternaluseraccountVO useraccountVO = agentInfoVO.getInternaluseraccountVO();
				StaffVO staffVO = agentInfoVO.getStaffVO();
				UsersVO usersVO = agentInfoVO.getUsersVO();
				GroupDefinitionVO groupDefinitionVO = agentInfoVO.getGroupDefinitionVO();

				if (useraccountVO == null) {
					agentAuthMessageSB.append("無法取得Account資訊!" + breakLine);
				}
				if (staffVO == null) {
					agentAuthMessageSB.append("無法取得Staff資訊!" + breakLine);
				}
				if (usersVO == null) {
					agentAuthMessageSB.append("無法取得User資訊!" + breakLine);
				}
				if (groupDefinitionVO == null) {
					agentAuthMessageSB.append("無法取得Group Definition資訊!" + breakLine);
				}
			} 

			if (agentAuthMessageSB != null) {
				String message = agentAuthMessageSB.toString();

				dataMap.put("message", message);

				logger.info(message);
			}

			// 設為不是第一次登入
			HttpSessionHandler.put("IS_FIRST_LOGIN", false);
		}

		String responseData = JsonUtil.toJson(dataMap);

		HttpRequestHandler.put("responseData", responseData);

		return ForwardUtil.JSON.getView();
	}

}