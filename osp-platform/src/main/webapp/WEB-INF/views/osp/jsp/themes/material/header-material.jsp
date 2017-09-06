<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp" %>

<div id="header" class="header navbar navbar-inverse navbar-fixed-top"> 
	<!-- BEGIN TOP NAVIGATION BAR -->
    <div class="header-inner"> 
    	<!-- BEGIN RESPONSIVE MENU TOGGLER --> 
      	<a id="toggle" class="navbar-toggle" href="#"><i class="fa fa-reorder"></i></a> 
      	<!-- END RESPONSIVE MENU TOGGLER -->
      	 
      	<!-- BEGIN LOGO --> 
      	<a class="navbar-brand" href="${contextPath}/console-index.action" > <img src="${contextPath}/resources/flip/assets/img/logo.png" alt="logo" /> </a> 
      	<!-- END LOGO -->
      	 
      	<span class="app-name">OSP</span> 
      	
     	<!-- BEGIN TOP NAVIGATION MENU -->
      	<ul class="nav navbar-nav pull-right">
	        <!-- BEGIN USER LOGIN DROPDOWN -->
			<li class="dropdown user"> <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> <span class="username">Hi~ ${sessionInfo.userInfoVO.userNm}!</span> <i class="fa fa-angle-down"></i> </a>
				<ul class="dropdown-menu">
					<li> <a href="#"><i class="fa fa-user"></i>姓名: ${sessionInfo.userInfoVO.userNm}</a> </li>
					<c:forEach var="role" items="${sessionInfo.userInfoVO.roleList}" varStatus="status">
						<li> <a href="#"><i class="fa fa-asterisk"></i>角色: ${role.roleTypeName}</a> </li>
					</c:forEach>
					<li> <a href="#"><i class="fa fa-asterisk"></i>班別: ${sessionInfo.userInfoVO.shiftType}</a> </li>
					<li> <a href="#"><i class="fa fa-clock-o"></i>登入時間: ${sessionInfo.loginDateTime} </a> </li>
					<li class="divider"></li>
					<li> <a href="#" id="logoutLink"><i class="fa fa-key"></i> 系統登出</a> </li>
				</ul>
			</li>
	        <!-- END USER LOGIN DROPDOWN -->
		</ul>
      	<!-- END TOP NAVIGATION MENU --> 
    </div>
   	<!-- END TOP NAVIGATION BAR --> 
</div>

<script>
	require(['../../module/session/session-logout-main']);
</script>