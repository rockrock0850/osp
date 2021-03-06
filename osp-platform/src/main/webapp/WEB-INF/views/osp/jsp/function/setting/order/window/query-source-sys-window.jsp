<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN SOURCE SYS -->
<div id="sourceSysModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog" style='width: 60%;'>
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">進件來源</h4>
			</div>
			<div class="modal-body">
				<table id="sourceSysDataTable" class="table table-striped table-bordered table-hover">
					<thead></thead>
					<tbody></tbody>
				</table>
				<div class="modal-footer">
					<button id='confirmButton' class="btn btn-default" data-dismiss="modal" aria-hidden="true">確定</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- END SOURCE SYS -->