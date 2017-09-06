<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN PAGE CONTAINER -->
<br>
<div id="searchForm">
	<div class="row">
		<div class="col-md-6">
			<label class="col-md-2">處理人員:</label>
			<div class="col-md-4" style="left: 9px;">
				<input type="text" class="form-control" name="userId">
			</div>
		</div>
	</div>

	<div class="row">
		<div class='col-xs-6 osp_col_1'>
			<div class='col-md-2' style="left: 4px;padding-left: 20px;">
				<label>日期:</label>
			</div>
			<div class='col-md-10 osp_datetime' style="padding-left: 10px;">
				<!-- BEGIN DATE TIME RANGE PICKER 1 -->
				<div class="col-md-5 osp_datetime_1">
					<div class="input-group date date-datetimepicker">
						<input class="form-control" type="text" name="queryBeginDate"> <span
							class="input-group-addon input-group-btn">
							<button class="btn btn-default" type="button">
								<i class="fa fa-calendar"></i>
							</button>
						</span>
					</div>
				</div>
				<div class="col-md-1 osp_datetime_separate">
					<span>~</span>
				</div>
				<div class="col-md-5 osp_datetime_2">
					<div class="input-group date date-datetimepicker">
						<input class="form-control" type="text" name="queryEndDate"> <span
							class="input-group-addon input-group-btn">
							<button class="btn btn-default" type="button">
								<i class="fa fa-calendar"></i>
							</button>
						</span>
					</div>
				</div>
				<!-- END DATE TIME RANGE PICKER 1 -->
			</div>
		</div>
	</div>
	<div style="padding-left: 94.3%;">
		<input type="button" id="searchBtn" class="btn btn-default" value="查詢">
	</div>
</div>
<hr>
<!-- Begin main View Table -->
<div style="padding-left: 94.3%;">
	<input type="button" class="btn btn-default" id="exportBtn" value="匯出">
</div>
<div>
	<table class="table table-striped table-bordered table-hover"
		id="tableView">
		<thead></thead>
		<tbody></tbody>
	</table>
</div>

<!-- End main view Table -->
<!-- END PAGE CONTAINER -->

<script>
	require([ '${pageContext.request.contextPath}/resources/app/module/setting/order/service-personal-order-operate-records-main.js' ]);
</script>

