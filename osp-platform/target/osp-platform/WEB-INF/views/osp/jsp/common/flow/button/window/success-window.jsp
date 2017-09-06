<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN Modal -->
<div id="successModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog" style='width: 500px;'>
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">有效件</h4>
			</div>
			<div class="modal-body">
				<form id='successForm'>
					<table>
						<tr>
							<td>
								<label>處理原因:</label>
							</td>
							<td>
								<select name='reasonId'></select>
							</td>
							<td>
								<label>處理結果:</label>
							</td>
							<td>
								<select name='resultId'></select>
							</td>
						</tr>
						<tr>
							<td valign="top">
								<label>備註:</label>
							</td>
							<td colspan="3">
								<textarea rows="5" cols="50" name='comment'></textarea>
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