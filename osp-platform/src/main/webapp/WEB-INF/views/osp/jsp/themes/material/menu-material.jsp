<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp" %>

<c:set var="menuContent" value="${menuContent}" />
<c:set var="tabList" value="${menuContent.tabList}" />

<!-- BEGIN SIDEBAR NAV -->
<div class="sidebar-off-canvas">
	<div class="page-sidebar"> 
		<!--BEGIN TABS-->
        <div class="sidebar-tabs tabbable">
            <ul class="nav nav-tabs">
            	<c:forEach items="${tabList}" var="tabItem" varStatus="status">
					<li>
                		<a href="#tab_${tabItem.tabId}" data-toggle="tab">${tabItem.tabText}</a>
                	</li>
                </c:forEach>
            </ul>
            <div class="tab-content">
            	<c:forEach items="${tabList}" var="tabItem" varStatus="status">
					<div class="tab-pane" id="tab_${tabItem.tabId}"> 
						<!-- BEGIN SIDEBAR MENU -->
                    	<ul class="page-sidebar-menu">
                    		<c:set var="menuLv1List" value="${tabItem.menuList}" />
                    		
                    		<c:forEach items="${menuLv1List}" var="menuItemLv1" varStatus="status">
                    			<li class="start" menuId="${menuItemLv1.menuId}">
                    				<a href="javascript:;">
                    					<i class="${menuItemLv1.menuIcon}"></i>
                    					<span class="title">${menuItemLv1.menuText}</span>
                    					<span class="arrow"></span>
                    				</a>
                    				<ul class="sub-menu">
                    					<c:set var="menuLv2List" value="${menuItemLv1.subMenuList}" />
                    					
                    					<c:forEach items="${menuLv2List}" var="menuItemLv2">
                    						<c:if test="${empty menuItemLv2.subMenuList}">
                    							 <li>
											</c:if>
                    						<c:if test="${not empty menuItemLv2.subMenuList}">
                    							 <li class="menu-slider-horizontal">
											</c:if>
                    					
                    							<a href="javascript:void(0)" name="MENU_${menuItemLv2.menuId}" menuId="${menuItemLv2.menuId}" menuLink="${menuItemLv2.menuLink}" linkType="${menuItemLv2.menuOpenType}"
                    							>${menuItemLv2.menuText}</a>
                    								
                    							<c:set var="menuLv3List" value="${menuItemLv2.subMenuList}" />
                    								
                    							<c:if test="${not empty menuLv3List}">
                    								<ul class="menu-slider-horizontal-handle">
	                    								<c:forEach items="${menuLv3List}" var="menuItemLv3">
	                    									<c:set var="menuLv4List" value="${menuItemLv3.subMenuList}" />
	                    								
			                    							<li class="sub-menu-slider-horizontal">
			                    								<a href="javascript:void(0)"
			                    									<c:if test="${not empty menuLv4List && fn:length(menuLv4List) > 1}">
			                    										name="MENU_${menuItemLv3.menuId}" menuId="${menuItemLv3.menuId}"
			                    									</c:if>
			                    									<c:if test="${not empty menuLv4List && fn:length(menuLv4List) <= 1}">
			                    										name="MENU_${menuLv4List[0].menuId}" menuId="${menuLv4List[0].menuId}" menuLink="${menuLv4List[0].menuLink}" linkType="${menuLv4List[0].menuOpenType}"
			                    									</c:if>
                    											>${menuItemLv3.menuText}</a>
			                    								
			                    								<c:if test="${not empty menuLv4List && fn:length(menuLv4List) > 1}">
			                    									<ul class="sub-menu-slider-horizontal-handle">
				                    									<c:forEach items="${menuLv4List}" var="menuItemLv4">
				                    											<li>
				                    												<a href="javascript:void(0)" name="MENU_${menuItemLv4.menuId}" menuId="${menuItemLv4.menuId}" menuLink="${menuItemLv4.menuLink}" linkType="${menuItemLv4.menuOpenType}"
                    																>${menuItemLv4.menuText}</a>
				                    											</li>
				                    									</c:forEach>
			                    									</ul>
			                    								</c:if>
			                    							</li>
                    									</c:forEach>
                    								</ul>
                    							</c:if>
                    						</li>
                    					</c:forEach>
                    				</ul>
                    			</li>
                    		</c:forEach>
						</ul>
                    	<!-- END SIDEBAR MENU --> 
					 </div>
            	</c:forEach>
            </div>
        </div>
        <!--END TABS-->
        <div class="sidebar-toggler-wrapper"> <a href="javascript:;" class="sidebar-toggler"><span class="title">收闔選單 </span></a> </div>
    </div>
</div>
<!--END SIDEBAR NAV-->

<script>
	require(['../../module/menu/menu-main']);
</script>