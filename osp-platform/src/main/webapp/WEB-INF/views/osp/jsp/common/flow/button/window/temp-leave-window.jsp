<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN Modal -->
<div id="tempLeaveModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog" style='width: 500px;'>
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">暫離</h4>
			</div>
			<div class="modal-body">
				<form id='tempLeaveForm'>
					<table>
						<tr>
							<td valign="top">
								<label>提醒內容:</label>
							</td>
							<td valign="top">
								<textarea name="msgContent" rows="5" cols="50"></textarea>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="modal-footer">
				<button id='confirmButton' class="btn btn-primary" data-dismiss="modal" aria-hidden="true">確定</button>
				<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- END Modal -->