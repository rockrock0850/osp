<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<div id='creationFile' style='display: none;'>
	<div class='form-group creation_file_open_row1'>
		<div class='row'>
			<div class='col-md-2 creation_file_col_1'>
				<label>進件來源:</label>
			</div>
			<div class='col-md-3 creation_file_col_2'>
				<!-- BEGIN SELECT -->
				<select class="form-control" name='sourceSysId'></select>
				<!-- END SELECT -->
			</div>
			<div class='col-md-2 creation_file_col_3'>
				<label>門號:</label>
			</div>
			<div class='col-md-5 creation_file_col_4'>
				<div class='col-md-6'>
					<input class="form-control" type="text" name='msisdn'>
				</div>
			</div>
		</div>
	</div>
	<div class='form-group creation_file_open_row2'>
		<div class='row'>
			<div class='col-md-2 creation_file_col_1'>
				<label>用戶名稱:</label>
			</div>
			<div class='col-md-3 creation_file_col_2'>
				<input class="form-control" type="text" name='custName'>
			</div>
			<div class='col-md-2 creation_file_col_3'>
				<label>ID:</label>
			</div>
			<div class='col-md-5 creation_file_col_4'>
				<div class='col-md-6'>
					<input class="form-control" type="text" name='custId'>
				</div>
			</div>
		</div>
	</div>
	<div class='form-group creation_file_open_row3'>
		<div class='row'>
			<div class='col-md-2 creation_file_col_1'>
				<label>證號類型:</label>
			</div>
			<div class='col-md-3 creation_file_col_2'>
				<!-- BEGIN SELECT -->
				<select class="form-control" name='custType'></select>
				<!-- END SELECT -->
			</div>
			<div class='col-md-2 creation_file_col_3'>
				<label>公司負責人證照號碼:</label>
			</div>
			<div class='col-md-5 creation_file_col_4'>
				<div class='col-md-6'>
					<input class="form-control" type="text" name='corpPicTaxId'>
				</div>
			</div>
		</div>
	</div>
	<!-- BEGIN BUTTON GROUP -->
	<table class='pull-right'>
		<tr>
			<td><button id='contentButton' type="button" class="btn btn-default btn-sm">進入案件內容</button></td>
			<td><button id='workFlowButton' type="button" class="btn btn-default btn-sm">返回流程首頁</button></td>
		</tr>
	</table>
	<!-- END BUTTON GROUP -->
</div>