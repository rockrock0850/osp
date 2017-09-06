<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN CREATE MODAL -->
<div id="createModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">新增</h4>
			</div>
			<div class="modal-body">
				<form id='createForm'>
					<div class="row">
						<label class="col-md-2">*班別 :</label>
						<div class="col-md-5" style="right: 29px;">
							<input class="form-control" type="text" name='shiftTypeName' />
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<label>上班時間 :</label> <select id="beginWorkHour" name='beginWorkHour'></select> <select id="beginWorkMin" name='beginWorkMin'></select>
						</div>
						<label class="col-md-1">~</label>
						<div class="col-md-4">
							<select id="endWorkHour" name='endWorkHour'></select> <select id="endWorkMin" name='endWorkMin'></select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<label>休息時間 :</label> <select id="beginRestHour" name='beginRestHour'></select> <select id="beginRestMin" name='beginRestMin'></select>
						</div>
						<label class="col-md-1">~</label>
						<div class="col-md-4">
							<select id="endRestHour" name='endRestHour'></select> <select id="endRestMin" name='endRestMin'></select>
						</div>
					</div>
					<br>
					<div class="row">
						<label class="col-md-2">備註說明:</label>
						<textarea id='textArea' class="col-md-6" style="right: 25px; resize: none;" name='textArea'></textarea>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button id='confirmButton' class="btn btn-primary" id="editDialogSubmitBtn" data-dismiss="modal">確定</button>
				<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- END CREATE MODAL -->