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

package com.fet.crm.osp.platform.core.service.systeminfo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.common.comparator.MenuSortComparator;
import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.db.warehouse.MenuWarehouse;
import com.fet.crm.osp.platform.core.pojo.MenuPOJO;
import com.fet.crm.osp.platform.core.service.systeminfo.IMenuService;
import com.fet.crm.osp.platform.core.vo.systeminfo.MenuInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.menu.MenuContentVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.menu.MenuItemVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.menu.TabItemVO;

/**
 * 「OSP選單」實作類別
 * 
 * @author LawrenceLai
 */
@Service
public class MenuServiceImpl implements IMenuService {
	
	@Autowired
	private MenuWarehouse menuWarehouse;
	
	@Autowired
	private JdbcDAO jdbcDAO;
	
	private Map<String, MenuItemVO> refrenceMenuMap;

	@Override
	public MenuInfoVO queryMenuInfoById(String menuId) {
		MenuInfoVO vo = new MenuInfoVO();
		
		if (StringUtils.isNotBlank(menuId)) {
			MenuPOJO pojo = menuWarehouse.findById(menuId);
			
			String skillId 			= pojo.getSkillId();			
			BigDecimal sortSequence = pojo.getSortSequence();
			String menuText 		= pojo.getMenuText();		
			String menuType 		= pojo.getMenuType();		
			String menuOpenType 	= pojo.getMenuOpenType();	
			String menuLev 			= pojo.getMenuLev();			
			String menuPartent 		= pojo.getMenuPartent();		
			String menuLink 		= pojo.getMenuLink();		
			String active 			= pojo.getActive();			
			String description 		= pojo.getDescription();		
			
			vo.setMenuId(menuId);
			vo.setSkillId(skillId);
			vo.setSortSequence(sortSequence);
			vo.setMenuText(menuText);
			vo.setMenuType(menuType);
			vo.setMenuOpenType(menuOpenType);
			vo.setMenuLev(menuLev);
			vo.setMenuPartent(menuPartent);
			vo.setMenuLink(menuLink);
			vo.setActive(active);
			vo.setDescription(description);
		}
		
		return vo;
	}
	
	@Override
	public MenuContentVO getMenuContentByUserId(String userId, String ntAccount) {
		String sqlText = ResourceFileUtil.SQL.getResource("system", "menu", "QUERY_AUTHORIZATION_MENU");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("USER_ID", userId);
		
		List<MenuItemVO> dataList = jdbcDAO.queryForList(sqlText, params, MenuItemVO.class);
		
		if (CollectionUtils.isNotEmpty(dataList)) {
			return toHierarchyMenu(dataList, ntAccount);
		}
		
		return new MenuContentVO();
	}
	
	@Override
	public Map<String, MenuItemVO> getBreadcrumbInfo() {
		String sqlText = ResourceFileUtil.SQL.getResource("system", "menu", "QUERY_BREADCRUMB_NODE");
		
		List<Map<String, Object>> nodeList = jdbcDAO.queryForList(sqlText);
		
		if (CollectionUtils.isNotEmpty(nodeList)) {
			Map<String, MenuItemVO> crumbMap = new HashMap<>();
			
			refrenceMenuMap = getRefrenceMenuMap();
			
			for (Map<String, Object> nodeMap : nodeList) {
				String nodeId = MapUtils.getString(nodeMap, "MENU_ID");
				MenuItemVO nodeItem = new MenuItemVO();
				
				BeanUtils.copyProperties(refrenceMenuMap.get(nodeId), nodeItem);
				
				MenuItemVO breadcrumbItem = toHierarchyBreadcrumb(nodeItem, refrenceMenuMap);
				
				crumbMap.put(nodeId, breadcrumbItem);
			}
			
			return crumbMap;
		}
		
		return Collections.emptyMap();
	}
	
	private Map<String, MenuItemVO> getRefrenceMenuMap() {
		List<MenuPOJO> dataList = menuWarehouse.findAll();
		
		if (CollectionUtils.isNotEmpty(dataList)) {
			Map<String, MenuItemVO> refrenceMap = new HashMap<>();
			
			for (MenuPOJO pojo : dataList) {
				String menuId = pojo.getMenuId(); 			
				String skillId = pojo.getSkillId();			
				int sortSequence = pojo.getSortSequence().intValue(); 		
				String menuText = pojo.getMenuText(); 		
				String menuType = pojo.getMenuType(); 		
				String menuOpenType = pojo.getMenuOpenType(); 	
				String menuLev = pojo.getMenuLev(); 		
				String menuPartent = pojo.getMenuPartent(); 	
				String menuLink = pojo.getMenuLink();
				String menuIcon = pojo.getMenuIcon();
				
				MenuItemVO vo = new MenuItemVO();
				
				vo.setMenuId(menuId);
				vo.setSkillId(skillId);
				vo.setSortSequence(sortSequence);
				vo.setMenuText(menuText);
				vo.setMenuType(menuType);
				vo.setMenuOpenType(menuOpenType);
				vo.setMenuLev(menuLev);
				vo.setMenuPartent(menuPartent);
				vo.setMenuLink(menuLink);
				vo.setMenuIcon(menuIcon);
				
				refrenceMap.put(menuId, vo);
			}
			
			return refrenceMap;
		}
		
		return Collections.emptyMap();
	}

	private MenuContentVO toHierarchyMenu(List<MenuItemVO> authorizationMenus, String ntAccount) {
		refrenceMenuMap = getRefrenceMenuMap();
		
		List<MenuItemVO> menuList = new ArrayList<>();
		
		for (MenuItemVO authMenu : authorizationMenus) {
			String authId = authMenu.getMenuId();
			MenuItemVO authMenuItem = refrenceMenuMap.get(authId);
			
			menuList = findOutUpLev(authMenuItem, menuList);
			menuList = findOutDownLev(authMenuItem, menuList);
		}
		
		MenuContentVO menuContentVO = new MenuContentVO();
		
		List<TabItemVO> tabList = new ArrayList<>();
		
		for (MenuItemVO menuItem : menuList) {
			buildAuthLink(menuItem, ntAccount);
			
			String menuLev = menuItem.getMenuLev(); 	
				
			if (StringUtils.isBlank(menuLev)) {
				String tabId = menuItem.getMenuId(); 		
				int sortSequence = menuItem.getSortSequence(); 	
				String tabText = menuItem.getMenuText(); 	
				
				TabItemVO tabItem = new TabItemVO();
						
				tabItem.setTabId(tabId);
				tabItem.setTabText(tabText);
				tabItem.setSortSequence(sortSequence);
				tabItem.setMenuList(recyclingSubMenu(tabId, menuList));
						
				tabList.add(tabItem);
			}
		}
		
		menuContentVO.setTabList(tabList);
		
		return menuContentVO;
	}
	
	private List<MenuItemVO> findOutUpLev(MenuItemVO menuItem, List<MenuItemVO> dependencyList) {
		if (CollectionUtils.isEmpty(dependencyList)) {
			dependencyList = new ArrayList<>();
		}
		
		String menuId = menuItem.getMenuId();
		String parentId = menuItem.getMenuPartent();
		
		for (MenuItemVO dependencyItem : dependencyList) {
			String dependencyMenuId = dependencyItem.getMenuId();
			
			if (menuId.equals(dependencyMenuId)) {
				return dependencyList;
			} 
		}
		
		dependencyList.add(menuItem);
		
		if (StringUtils.isBlank(parentId)) {
			return dependencyList;
		}
		
		MenuItemVO parentItem = new MenuItemVO();
		BeanUtils.copyProperties(refrenceMenuMap.get(parentId), parentItem);
		
		return findOutUpLev(parentItem, dependencyList);
	}
	
	private List<MenuItemVO> findOutDownLev(MenuItemVO menuItem, List<MenuItemVO> dependencyList) {
		String menuId = menuItem.getMenuId();
		String menuLev = menuItem.getMenuLev();
		
		if (Integer.valueOf(menuLev) <= 2) {
			return dependencyList;
		}
		
		if (CollectionUtils.isEmpty(dependencyList)) {
			dependencyList = new ArrayList<>();
		}
		
		for (String menuKey : refrenceMenuMap.keySet()) {
			MenuItemVO childItem = refrenceMenuMap.get(menuKey);
			String parentId = childItem.getMenuPartent();
			
			if (menuId.equals(parentId)) {
				dependencyList.add(childItem);
				
				findOutDownLev(childItem, dependencyList);
			}
		}
		
		return dependencyList;
	}
	
	private List<MenuItemVO> recyclingSubMenu(String targetId, List<MenuItemVO> menuList) {
		List<MenuItemVO> subMenuList = null;
		
		for (MenuItemVO menuItem : menuList) {
			String partentId = menuItem.getMenuPartent();
			String menuId = menuItem.getMenuId();
			
			if (targetId.equals(partentId)) {
				if (CollectionUtils.isEmpty(subMenuList)) {
					subMenuList = new ArrayList<>();
				}
				
				menuItem.setSubMenuList(recyclingSubMenu(menuId, menuList));
				
				subMenuList.add(menuItem);
			}
		}
		

        if (CollectionUtils.isNotEmpty(subMenuList)) {
            Collections.sort(subMenuList, new MenuSortComparator());
        }
		
		return subMenuList;
	}
	
	private MenuItemVO toHierarchyBreadcrumb(MenuItemVO nodeItem, Map<String, MenuItemVO> refrenceMenuMap) {
		String menuLev = nodeItem.getMenuLev();
		
		if (StringUtils.isBlank(menuLev) || Integer.valueOf(menuLev) <= 1) {
			return nodeItem;
		}
		
		String partentId = nodeItem.getMenuPartent();
		
		MenuItemVO refrenceItem = refrenceMenuMap.get(partentId);
		
		if (refrenceItem == null) {
			return nodeItem;
		}
		
		MenuItemVO parentItem = new MenuItemVO();
				
		BeanUtils.copyProperties(refrenceItem, parentItem); 
			
		List<MenuItemVO> subItemlist = new ArrayList<>();				
				
		subItemlist.add(nodeItem);
				
		parentItem.setSubMenuList(subItemlist);
				
		return toHierarchyBreadcrumb(parentItem, refrenceMenuMap);
	}
	
	private void buildAuthLink(MenuItemVO menuItemVO, String ntAccount) {
		if (menuItemVO != null) {
			String link = menuItemVO.getMenuLink();
			
			if (StringUtils.isNotBlank(link)) {
				String authlink = link.replace("$P{NTAccount}", ntAccount);
				menuItemVO.setMenuLink(authlink);
			}
		}
	}
	
}
