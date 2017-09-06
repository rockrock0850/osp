<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN STICK MODAL -->
<div id="stickModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true" data-backdrop="false">
<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">選擇人員</h4>
		</div>
		<div class="modal-body">
			<div class="row">
				<label class="col-md-2">貼標SKILL:</label>
				<div class="col-md-3" style='margin-top: -0.5%;'>
					<select id='skillSelect' class="form-control"></select>
				</div>
			</div>
			<hr>
			<div>
				<table id="stickDataTable" class="table table-striped table-bordered table-hover">
					<thead>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<div class="modal-footer">
		<button id='confirmButton' class="btn btn-primary" data-dismiss="modal" >確定</button>
		<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
		</div>
	</div>
</div> 
</div>
<!-- END STICK MODAL -->