<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- Import File -->
<label class="osp_table_name">匯入</label>
<div id='importSelect' class="row form-gruop">
	<label class="col-md-1">班表年月:</label>
	<div class="col-md-2">
		<select id="monthSelector" name="monthSelector" style='width: 100%'></select>
	</div>
	<div class="col-md-2">
		<select id="yearSelector" name="yearSelector" style='width: 100%'></select>
	</div>
</div>
<%@include file="/WEB-INF/views/osp/jsp/common/file-upload.jsp"%>
<!-- Import File -->

<hr>

<!-- Export File -->
<label class="osp_table_name">匯出</label>
<div id='exportSelect' class="row form-gruop">
	<label class="col-md-1">班表年月:</label>
	<div class="col-md-2">
		<select id="monthSelector" name="monthSelector" style='width: 100%'></select>
	</div>
	<div class="col-md-2">
		<select id="yearSelector" name="yearSelector" style='width: 100%'></select>
	</div>
</div>
<div class="form-actions right">
	<a id='downloadButton' href="#" style="margin-left: 10px;">
		<i class="fa fa-2x fa-download" aria-hidden="true"></i>
	</a>
</div>
<!-- Export File -->

<script>
	require([ '${pageContext.request.contextPath}/resources/app/module/setting/shift/shift-content-main.js' ])
</script>