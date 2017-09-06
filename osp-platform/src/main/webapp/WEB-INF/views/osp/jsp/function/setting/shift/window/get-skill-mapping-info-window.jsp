<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN SKILL MODAL -->
<div id="skillModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true" data-backdrop="false" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">修改技能</h4>
			</div>
			<div class="modal-body">
				<!-- BEGIN SELECT SWITCHER -->
				<div class="form-group row">
					<div class="col-md-4">
						<select id="collectionLeft" class="form-control" multiple="multiple" style="height: 200px;"></select>
					</div>
					<div class="col-md-2">
						<button id="collectionLeftBtn" class="btn btn-mini" type="button">
							<i class="fa fa-angle-double-right fa-2x"></i>
						</button>
						<br />
						<button id="collectionRightBtn" class="btn btn-mini" type="button">
							<i class="fa fa-angle-double-left fa-2x"></i>
						</button>
					</div>
					<div class="col-md-4">
						<select id="collectionRight" class="form-control" multiple="multiple" name="publishRoles" style="height: 200px;"></select>
					</div>
				</div>
				<!-- END SELECT SWITCHER -->
			</div>
			<div class="modal-footer">
				<button id='confirmButton' class="btn btn-primary">確定</button>
				<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- END SKILL MODAL -->