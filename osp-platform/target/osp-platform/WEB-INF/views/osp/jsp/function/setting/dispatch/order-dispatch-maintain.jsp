<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN PAGE CONTAINER -->
<br>
<!-- BEGIN FORM -->
<form id='searchForm'>
	<div class="form-group row">
		<div class='col-md-2'>
			<label>暫停時間:</label>
		</div>
		<!-- BEGIN DATE TIME RANGE PICKER 1 -->
		<div class='col-md-10 order_dispatch_open_datetime'>
			<div class="col-md-3 osp_datetime_1 order_dispatch_open_datetime_1">
				<div class="input-group date date-datetimepicker">
					<input class="form-control" type="text" name="pauseStartTime">
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
			<div class="col-md-3 osp_datetime_2 order_dispatch_open_datetime_2">
				<div class="input-group date date-datetimepicker">
					<input class="form-control" type="text" name='pauseEndTime'>
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
</form>
<!-- END FORM -->

<div class="row">
	<div align="right">
		<input id='searchButton' type="button" class="btn btn-default" value="查詢">
	</div>
</div>
<hr>

<!-- BEGIN SEARCH RESULT -->
<input id="stopAssignButton" type="button" class="btn btn-default" value="暫停分派">
<div id='searchResult' style='display: none'>
	<table id="dataTable" class="table table-striped table-bordered table-hover">
        <thead>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<!-- END PAGE CONTAINER -->

<%@include file="/WEB-INF/views/osp/jsp/function/setting/dispatch/window/order-dispatch-window.jsp"%>

<script>
	require(['${pageContext.request.contextPath}/resources/app/module/setting/dispatch/order-dispatch-maintain-main.js']);
</script>