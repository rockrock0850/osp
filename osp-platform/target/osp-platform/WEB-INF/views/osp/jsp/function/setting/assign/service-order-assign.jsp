<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN FORM -->
<form id='searchForm'>
	<div class="form-group row">
		<div class='col-md-2'>
			<label>進件來源進件時間:</label>
		</div>
		<!-- BEGIN DATE TIME RANGE PICKER 1 -->
		<div class='col-md-10 order_assign_open_datetime'>
			<div class="col-md-3 osp_datetime_1 order_assign_open_datetime_1">
				<div class="input-group date date-datetimepicker">
					<input class="form-control" type="text" name="sourceCreateTimeBegin">
					<span class="input-group-addon input-group-btn">
						<button class="btn btn-default" type="button">
							<i class="fa fa-calendar"></i>
						</button>
					</span>
				</div>
			</div>
			<div class="col-md-2">
				<span>~</span>
			</div>
			<div class="col-md-3 osp_datetime_2 order_assign_open_datetime_2">
				<div class="input-group date date-datetimepicker">
					<input class="form-control" type="text" name='sourceCreateTimeEnd'>
					<span class="input-group-addon input-group-btn">
						<button class="btn btn-default" type="button">
							<i class="fa fa-calendar"></i>
						</button>
					</span>
				</div>
			</div>
		</div>
		<!-- END DATE TIME RANGE PICKER 1 -->
	</div>
	<div class="form-group row">
		<div class='col-md-2'>
			<label>預計作業處理時間:</label>
		</div>
		<!-- BEGIN DATE TIME RANGE PICKER 1 -->
		<div class='col-md-10 order_assign_open_datetime'>
			<div class="col-md-3 osp_datetime_1 order_assign_open_datetime_1">
				<div class="input-group date date-datetimepicker">
					<input class="form-control" type="text" name='expectProcessTimeBegin'>
					<span class="input-group-addon input-group-btn">
						<button class="btn btn-default" type="button">
							<i class="fa fa-calendar"></i>
						</button>
					</span>
				</div>
			</div>
			<div class="col-md-2">
				<span>~</span>
			</div>
			<div class="col-md-3 osp_datetime_2 order_assign_open_datetime_2">
				<div class="input-group date date-datetimepicker">
					<input class="form-control" type="text" name='expectProcessTimeEnd'>
					<span class="input-group-addon input-group-btn">
						<button class="btn btn-default" type="button">
							<i class="fa fa-calendar"></i>
						</button>
					</span>
				</div>
			</div>
		</div>
		<!-- END DATE TIME RANGE PICKER 1 -->
	</div>
	<div class="form-group order_assign_open_row1">
		<div class="row">
			<div class='col-md-2 order_assign_col_1'>
				<label>案件類別:</label>
			</div>
			<div class="col-md-3 order_assign_col_2">
				<select class="form-control" name='orderTypeId'>
					<option value=''>請選擇</option>
				</select>
			</div>
			<div class='col-md-2 order_assign_col_3'>
				<label>影像代號/PAID:</label>
			</div>
			<div class='col-md-3 order_assign_col_4'>
				<input type="text" class="form-control" name='imgIdApid'>
			</div>
		</div>
	</div>
	<div class="form-group order_assign_open_row2">
		<div class="row">
			<div class='col-md-2 order_assign_col_1'>
				<label>進件系統:</label>
			</div>
			<div class="col-md-3 order_assign_col_2">
				<select class="form-control" name='sourceSysId'>
					<option value=''>請選擇</option>
				</select>
			</div>
			<div class='col-md-2 order_assign_col_3'>
				<label>門號/代表號/線路編號:</label>
			</div>
			<div class='col-md-3 order_assign_col_4'>
				<input type="text" class="form-control" name='msisdn'>
			</div>
		</div>
	</div>
	<div class="form-group order_assign_open_row3">
		<div class="row">
			<div class='col-md-2 order_assign_col_1'>
				<label>用戶名稱:</label>
			</div>
			<div class="col-md-3 order_assign_col_2">
				<input type="text" class="form-control" name='custName'>
			</div>
			<div class='col-md-2 order_assign_col_3'>
				<label>證號/統編:</label>
			</div>
			<div class='col-md-3 order_assign_col_4'>
				<input type="text" class="form-control" name='custId'>
			</div>
		</div>
	</div>
	<div class="form-group order_assign_open_row4">
		<div class="row">
			<div class='col-md-2 order_assign_col_1'>
				<label>產品類型:</label>
			</div>
			<div class="col-md-3 order_assign_col_2">
				<select class="form-control" name='sourceProdTypeId'>
					<option value=''>請選擇</option>
				</select>
			</div>
			<div class='col-md-2 order_assign_col_3'>
				<label>交易型態:</label>
			</div>
			<div class='col-md-3 order_assign_col_4'>
				<select class="form-control" name='operateType'>
					<option value=''>請選擇</option>
				</select>
			</div>
		</div>
	</div>
	<div class="form-group order_assign_open_row5">
		<div class="row">
			<div class='col-md-2 order_assign_col_1'>
				<label>經銷代碼:</label>
			</div>
			<div class="col-md-3 order_assign_col_2">
				<input type="text" class="form-control" name='ivrCode'>
			</div>
			<div class='col-md-2 order_assign_col_3'>
				<label>業務代碼:</label>
			</div>
			<div class='col-md-3 order_assign_col_4'>
				<input type="text" class="form-control" name='salesId'>
			</div>
		</div>
	</div>
	<div class="form-group order_assign_open_row6">
		<div class="row">
			<div class='col-md-2 order_assign_col_1'>
				<label>處理人員名稱:</label>
			</div>
			<div class="col-md-3 order_assign_col_2">
				<input type="text" class="form-control" name='processUserName'>
			</div>
			<div class='col-md-2 order_assign_col_3'>
				<label>案件狀態 :</label>
			</div>
			<div class='col-md-3 order_assign_col_4'>
				<select class="form-control" name='orderStatus'>
					<option value=''>請選擇</option>
				</select>
			</div>
		</div>
	</div>
</form>
<!-- END FORM -->

<div class="row">
	<div align="right">
		<button id='searchButton' class="btn btn-default">查詢</button>
	</div>
</div>
<hr>

<!-- BEGIN SEARCH RESULT -->
<div id='searchResult' style='display: none'>
	<button id="assignButton" class="btn btn-default">轉派</button>
	<table id="dataTable" class="table table-striped table-bordered table-hover">
		<thead></thead>
		<tbody></tbody>
	</table>
</div>
<!-- END SEARCH RESULT -->

<%@include file="/WEB-INF/views/osp/jsp/function/setting/assign/window/assign-memeber-window.jsp"%>

<script>
	require([ '${pageContext.request.contextPath}/resources/app/module/setting/assign/order-assign-main.js' ]);
</script>