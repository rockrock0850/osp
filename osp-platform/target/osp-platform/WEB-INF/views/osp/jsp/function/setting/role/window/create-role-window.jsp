<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN CREATE MODAL -->
<div id="createModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">新增角色</h4>
			</div>
			<div class="modal-body">
				<form id='createForm'>
					<div class="row">
						<label class="col-md-3">角色名稱:</label>
						<div class="col-md-6">
							<input type="text" class="form-control" name='roleName'>
						</div>
					</div>
					<div class="row">
						<label class="col-md-3">角色類別:</label>
						<div class="col-md-6">
							<select class="form-control" name='roleType'>
								<option value=''>請選擇</option>
							</select>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button id='confirmButton' class="btn btn-primary" data-dismiss="modal">確定</button>
				<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- END CREATE MODAL -->