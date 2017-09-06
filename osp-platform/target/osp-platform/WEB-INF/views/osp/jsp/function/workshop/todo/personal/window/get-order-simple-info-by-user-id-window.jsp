<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN ORDER SIMPLE MODAL -->
<div id="orderSimpleModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog" style='width: 50%;'>
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">案件清單</h4>
			</div>
			<div class="modal-body">
				<table>
					<thead>
						<tr>
							<th>案件類型</th>
							<th>全部</th>
							<th>急件</th>
							<th>即將逾期件</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button id='confirmButton' class="btn btn-default" data-dismiss="modal" aria-hidden="true">確定</button>
			</div>
		</div>
	</div>
</div>
<!-- END ORDER SIMPLE MODAL -->