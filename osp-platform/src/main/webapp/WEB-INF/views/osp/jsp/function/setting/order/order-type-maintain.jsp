<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<br>
<!-- BEGIN FORM -->
<form id='searchForm'>
	<div class='form-group row'>
		<div class='col-md-5 osp_col_1'>
			<div class='col-md-2'>
				<label>案件類別 :</label>
			</div>
			<div class='col-md-5' style='margin-left: -1.5%'>
				<input class="form-control" type="text" name='name' placeholder="請輸入案件類別名稱" value=''>
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
<div id='searchResult' style='display: none'>
	<table id="dataTable" class="table table-striped table-bordered table-hover">
		<thead></thead>
		<tbody></tbody>
	</table>
</div>
<!-- END SEARCH RESULT -->

<%@include file="/WEB-INF/views/osp/jsp/function/setting/order/window/modify-order-type-window.jsp"%>
<%@include file="/WEB-INF/views/osp/jsp/function/setting/order/window/query-source-sys-window.jsp"%>

<script>
	require([ '${pageContext.request.contextPath}/resources/app/module/setting/order/order-type-maintain-main.js' ])
</script>