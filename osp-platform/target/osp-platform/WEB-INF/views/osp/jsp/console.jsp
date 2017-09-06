<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp" %>

<div id="pannelContent" menuId="${menuInfo.menuId}" menuLink="${menuInfo.menuLink}">
</div>

<script>
	require(['${pageContext.request.contextPath}/resources/app/module/console/console-main.js']);
</script>