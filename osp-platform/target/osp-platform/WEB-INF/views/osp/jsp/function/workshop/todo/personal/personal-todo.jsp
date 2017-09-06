<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN INFO -->
<div id='info'>
	<!-- BEGIN FORM -->
	<form id='searchForm'>
		<div class='form-group row personal_todo_open_row1'>
			<div class='col-md-5'>
				<div class='col-md-2 personal_todo_col_1'>
					<label>OSP單號:</label>
				</div>
				<div class='col-md-5 personal_todo_col_2'>
					<input class="form-control" type="text" name='orderMId'>
				</div>
			</div>
			<div class='col-md-7'>
				<div class='col-md-3 personal_todo_col_3'>
					<label>案件類別:</label>
				</div>
				<div class='col-md-7 personal_todo_col_4'>
					<div class='col-md-6 '>
						<!-- BEGIN SELECT -->
						<select class="form-control" name='orderTypeId'>
							<option value=''>請選擇</option>
						</select>
						<!-- END SELECT -->
					</div>
					<div class='col-md-1 personal_todo_0_2'>
						<button id='getCaseListButton' type='button' class="btn btn-default btn-sm">
							<i class="fa fa-file-text"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class='form-group row personal_todo_open_row2'>
			<div class='col-md-5'>
				<div class='col-md-2 personal_todo_col_1'>
					<label>產品類別:</label>
				</div>
				<div class='col-md-5 personal_todo_col_2'>
					<!-- BEGIN SELECT -->
					<select class="form-control" name='sourceProdTypeId'>
						<option value=''>請選擇</option>
					</select>
					<!-- END SELECT -->
				</div>
			</div>
			<div class='col-md-7'>
				<div class='col-md-4 personal_todo_col_3'>
					<label>門號/代表號/線路編號:</label>
				</div>
				<div class='col-md-8 personal_todo_col_4'>
					<div class='col-md-6'>
						<input class="form-control "type="text" name='msisdn'>
					</div>
					<div class='col-md-6'></div>
				</div>
			</div>
		</div>
		<div class='form-group row personal_todo_open_row3'>
			<div class='col-md-5'>
				<div class='col-md-2 personal_todo_col_1'>
					<label>用戶名稱:</label>
				</div>
				<div class='col-md-5 personal_todo_col_2'>
					<input class="form-control "type="text" name='custName'>
				</div>
			</div>
			<div class='col-md-7'>
				<div class='col-md-3 personal_todo_col_3'>
					<label>來源單號:</label>
				</div>
				<div class='col-md-8 personal_todo_col_4'>
					<div class='col-md-6'>
						<input class="form-control "type="text" name='sourceOrderId'>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END FORM -->
	
	<div class="row">
		<div align="right">
			<button id='searchButton' type="button" class="btn btn-primary">查詢</button>
		</div>
	</div>
	
	<hr>
	
	<!-- BEGIN SEARCH RESULT -->
	<div id='searchResult'>
		<div class='form-group row'>
			<div class='col-md-4'>
				<div class='col-md-3'>
					<label>全部案件:</label>
				</div>
				<div class='col-md-3' style='margin-left: -5%'>
					<label id='sum'></label>
				</div>
			</div>
			<div class='col-md-4' style='margin-left: -20%;'>
				<div class='col-md-3'>
					<label>急件:</label>
				</div>
				<div class='col-md-3' style='margin-left: -12%'>
					<label id='critical'></label>
				</div>
			</div>
			<div class='col-md-4' style='margin-left: -23%;'>
				<div class='col-md-3'>
					<label>即將逾期件:</label>
				</div>
				<div class='col-md-3' style='margin-left: -1.5%'>
					<label id='overdue'></label>
				</div>
			</div>
		</div>
		<div class='form-group row'>
			<table id="resultTable" class="table table-striped table-bordered table-hover">
				<thead></thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
	<!-- END SEARCH RESULT -->
</div>
<!-- END INFO -->

<!-- BEGIN CONTENT -->
<div id='caseContent' style='display: none;'></div>
<!-- END CONTENT -->

<!-- BEGIN ORDER DETAIL GRID -->
<div id="gridDiv" style="display: none;">
	<table id="gridTable" class="table table-striped table-bordered table-hover">
		<thead></thead>
		<tbody></tbody>
	</table>
</div>
<!-- END ORDER DETAIL GRID -->

<%@include file="/WEB-INF/views/osp/jsp/function/workshop/todo/personal/window/get-order-simple-info-by-user-id-window.jsp"%>
<script>
	require(['${pageContext.request.contextPath}/resources/app/module/workshop/todo/personal/personal-todo-main.js']);
</script>