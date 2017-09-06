<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN MODAL -->
<div id="replyModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="normalModalLabel" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog" style='width: 500px;'>
		<div class="modal-content">
			<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			  <h4 class="modal-title">待系統回覆</h4>
			</div>
			<div class="modal-body">
				<center>請問是否確認?若是上傳CM Batch, 請選擇「暫離」。</center>
			</div>
			<div class="modal-footer">
			  <button id='confirmButton' class="btn btn-primary">確定</button>
			  <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- END MODAL -->