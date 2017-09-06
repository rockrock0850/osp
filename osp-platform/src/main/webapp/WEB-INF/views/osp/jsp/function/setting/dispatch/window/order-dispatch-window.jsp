<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<style>
  .date-datetimepicker{z-index:1600 !important;}
  .datetimepicker{z-index:1600 !important;}
  .datepicker{z-index:1600 !important;}
  .input-group .form-control{z-index:1600 !important;}
</style>
<!-- BEGIN MODAL -->
<div id="stopAssignModal" class="modal fade" data-width="760" tabindex="-1" role="dialog" aria-labelledby="normalModalLabel" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content" style='width: 700px;'>
			<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			  <h4 class="modal-title" id="normalModalLabel"></h4>
			</div>
			<div class="modal-body">
				<!-- BEGIN DIALOG -->
				<form id='searchFormDialog'>
					<div class="form-group row">
						<div class='col-md-2'>
							<label>暫停時間:</label>
						</div>
						<!-- BEGIN DATE TIME RANGE PICKER 1 -->
						<div class='col-md-10'>
							<div class="col-md-4 osp_datetime_1" style="margin-left: -50px;">
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
							<div class="col-md-4 osp_datetime_2" style="margin-left: -76px;">
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
				<br>
				<table id='stopAssignDataTable' class="table table-striped table-bordered table-hover">
					<thead></thead>
					<tbody></tbody>
				</table>
				<!-- END DIALOG -->
			</div>
			<div class="modal-footer">
			  <button id='confirmButton' class="btn btn-primary">確定</button>
			  <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- END MODAL -->