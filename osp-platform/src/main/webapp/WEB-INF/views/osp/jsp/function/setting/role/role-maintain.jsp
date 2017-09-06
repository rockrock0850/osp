<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<br>
<!-- BEGIN FORM -->
<div class='form-group row'>
	<form id='searchForm'>
		<div class='col-md-5 osp_col_1'>
			<div class='col-md-2'>
				<label>角色名稱:</label>
			</div>
			<div class='col-md-5' style='margin-left: -1.5%'>
				<input class="form-control" type="text" name='roleName'>
			</div>
		</div>
		<div class='col-md-5' style='margin-left: -15%;'>
			<div class='col-md-2'>
				<label>員工編號:</label>
			</div>
			<div class='col-md-5' style='margin-left: -1%;'>
				<input class="form-control" type="text" name='empNo'>
			</div>
		</div>
	</form>
</div>
<!-- END FORM -->

<div class="row">
	<div align="right">
	    <button id="searchButton" class="btn btn-default btn-sm">查詢</button>
	</div>
</div>
<hr>

<!-- BEGIN SEARCH RESULT -->
<div id='searchResult' style='display: none'>
	<input id="createButton" type="button" class="btn btn-default" style='margin-bottom: 0.5%;' value="新增">
	<table id="dataTable" class="table table-striped table-bordered table-hover">
		<thead></thead>
		<tbody></tbody>
	</table>
</div>
<!-- END SEARCH RESULT -->

<%@include file="/WEB-INF/views/osp/jsp/function/setting/role/window/create-role-window.jsp"%>
<%@include file="/WEB-INF/views/osp/jsp/function/setting/role/window/modify-role-window.jsp"%>
<%@include file="/WEB-INF/views/osp/jsp/function/setting/role/window/service-menu-ref-window.jsp"%>
<%@include file="/WEB-INF/views/osp/jsp/function/setting/role/window/get-manual-member-window.jsp"%>
<%@include file="/WEB-INF/views/osp/jsp/function/setting/role/window/get-manual-mapping-info-by-id-window.jsp"%>
<script>
	require(['${pageContext.request.contextPath}/resources/app/module/setting/role/role-maintain-main.js'])
</script>