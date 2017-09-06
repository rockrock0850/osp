<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<div id='notifyOtherSales' style='display: none;'>
	<div class='form-group notify_other_sales_open_row1'>
		<div class='row'>
			<div class='col-md-2 notify_other_sales_col_1'>
				<label>門號:</label>
			</div>
			<div class='col-md-3 notify_other_sales_col_2'>
				<input class="form-control" type="text" name='msisdn'>
			</div>
		</div>
	</div>
	
	<!-- BEGIN Button group -->
	<div class="row">
		<div align="right">
			<button id='searchMsisdnButton' type="button" class="btn btn-default">查詢</button>
		</div>
	</div>
	<!-- END Button group -->
	
	<hr>
	
	<!-- BEGIN Button group -->
	<div class='form-group row'>
		<button id='sendEMailButton' type="button" class="btn btn-default">寄送Mail</button>
	</div>
	<!-- END Button group -->
	
	<div id='searchResult' class='form-group row' style='display: none;'>
		<table id="msisdnTable" class="table table-striped table-bordered table-hover">
			<thead></thead>
			<tbody></tbody>
		</table>
	</div>
</div>