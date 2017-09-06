<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- 為測試用的btn  2017-0510 -->
<!-- <a data-toggle="modal" class="btn btn-success" href="http://localhost:8080/osp-platform/session/service-login.action" data-target="#linkContentModal">測試pop up</a> -->

<c:forEach items="${dataList}" var="content" varStatus='status'>

	<a data-toggle="modal" class="btn btn-primary" href="${content.reserv3}" status="${status.count}" data-target="#linkContentModal${status.count}">${content.itemName}</a>
	
</c:forEach>

<%@include file="/WEB-INF/views/osp/jsp/function/content/window/pop-up-link-content.jsp"%>