<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN CREATE MEMBER MODAL -->
<div id="createMemberModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">新增成員</h4>
			</div>
			<div class="modal-body">
				<table class="table table-striped table-bordered table-hover">
					<thead></thead>
					<tbody></tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button id='confirmButton' class="btn btn-primary" data-dismiss="modal">確定</button>
				<button id='cancelButton' class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- END CREATE MEMBER MODAL -->