<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/scripts.jsp" %>
<style>
	body {
		background : #FFFFFF;
	}
</style>
<!-- BEGIN ORDER DETAIL GRID -->
<div id="gridView">

<table id="gridTable" class="table table-striped table-bordered table-hover">
	<thead></thead>
	<tbody></tbody>
</table>

</div>
<script type="text/javascript">
	var aaData = ${responseData};
	
	require(['${pageContext.request.contextPath}/resources/app/module/workshop/creation-group/mobile/component/window/spv-detail-grid.js']);
</script>

<!-- END ORDER DETAIL GRID -->