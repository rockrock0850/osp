<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- 引入template -->
<%@include file="/WEB-INF/views/osp/jsp/function/workshop/creation-group/mobile/component/workshop-template.jsp" %>

<!-- 基本上各個流程首頁唯一的區別就是此處會引入不同的js -->
<script>
	require([ '${contextPath}/resources/app/module/workshop/creation-group/mobile/service-prepaid-nnocw.js' ])
</script>