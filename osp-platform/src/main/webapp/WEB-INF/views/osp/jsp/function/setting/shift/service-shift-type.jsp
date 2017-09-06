<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<br>
<!-- BEGIN FORM -->
<form id='searchForm'>
	<div class='form-group row'>
		<div class='col-md-5 osp_col_1'>
			<div class='col-md-2'>
				<label>班別名稱:</label>
			</div>
			<div class='col-md-5' style='margin-left: -1.5%'>
				<input class="form-control" type="text" name='shiftTypeName' placeholder="請輸入班別名稱">
			</div>
		</div>
	</div>
</form>
<!-- END FORM -->

<div class="row">
	<div align="right">
		<button id="searchButton" class="btn btn-default btn-sm">查詢</button>
	</div>
</div>
<hr>

<!-- BEGIN SEARCH RESULT -->
<div id="searchResult" style='display: none'>
	<button id="createButton" type="button" class="btn btn-default" data-toggle="modal">新增</button>
	<table  id="dataTable" class="table table-striped table-bordered table-hover">
		<thead></thead>
		<tbody></tbody>
	</table>
</div>
<!-- END SEARCH RESULT -->

<%@include file="/WEB-INF/views/osp/jsp/function/setting/shift/window/create-shift-type-window.jsp"%>
<%@include file="/WEB-INF/views/osp/jsp/function/setting/shift/window/get-skill-mapping-info-window.jsp"%>
<%@include file="/WEB-INF/views/osp/jsp/function/setting/shift/window/modify-shift-type-window.jsp"%>

<script>
	require([ '${pageContext.request.contextPath}/resources/app/module/setting/shift/shift-type-main.js' ])
</script>
