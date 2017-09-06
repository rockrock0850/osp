<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp" %>

<div id="pannelContent" menuId="${menuInfo.menuId}" menuLink="${menuInfo.menuLink}">
</div>

<div id="warningBootBox" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
   	<div class="alert alert-error" style="margin: 0px; font-size: 14px;">
       	<strong>警告！</strong><span id="agentWarningMsg">${AGENT_AUTH_MSG}</span>
	</div>
</div>


<script>
	require(['${pageContext.request.contextPath}/resources/app/module/console/console-main.js']);
</script>