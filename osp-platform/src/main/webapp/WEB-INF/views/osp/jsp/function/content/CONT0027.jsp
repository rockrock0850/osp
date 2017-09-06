<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<c:forEach items="${dataList}" var="content" varStatus='status'>
	<button type="button" class="btn btn-primary" name="IA_BTN_${content.itemId}" link="${content.reserv2}${content.reserv3}">${content.itemName}</button>
</c:forEach>

<script>
	require([ '${contextPath}/resources/app/module/content/CONT0027-main.js' ])
</script>