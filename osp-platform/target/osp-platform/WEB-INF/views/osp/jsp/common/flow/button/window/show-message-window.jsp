<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN MODAL -->
<div id="showMessageModal" class="modal fade" data-width="760" tabindex="-1" role="dialog" aria-labelledby="normalModalLabel" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			  <h4 class="modal-title">暫離提醒</h4>
			</div>
			<div class="modal-body"><center><label id='message'></label></center></div>
			<div class="modal-footer">
			  <button class="btn btn-primary"  data-dismiss="modal" aria-hidden="true">確定</button>
			</div>
		</div>
	</div>
</div>
<!-- END MODAL -->