<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN ASSIGN MODAL -->
<div id="sendEmailModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog" style='width: 450px;'>
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">通知它業者</h4>
			</div>
			<div class="modal-body">
				<div class='form-group'>
					<div class='row'>
						<div class='col-md-2'>
							<label>通知業者:</label>
						</div>
						<div class='col-md-3'>
							<!-- BEGIN SELECT -->
							<select class="form-control" name='comboValue'></select>
							<!-- END SELECT -->
						</div>
					</div>
				</div>
				<div class='form-group'>
					<div class='row'>
						<div class='col-md-2'>
							<label>副本:</label>
						</div>
						<div class='col-md-5'>
							<div class='col-md-6'>
								<textArea name='comboContent' style="margin: 0px; width: 336px; height: 109px;"></textArea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button id='confirmButton' class="btn btn-primary" data-dismiss="modal" aria-hidden="true">確定</button>
				<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- END ASSIGN MODAL -->