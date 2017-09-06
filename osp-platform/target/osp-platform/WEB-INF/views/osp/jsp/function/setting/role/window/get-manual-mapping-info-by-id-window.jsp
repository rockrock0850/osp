<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN SETMEMBER MODAL -->
<div id="setMemberModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">設定角色成員</h4>
			</div>
			<div class="modal-body">
				<form id='setMemberSearchForm'>
					<div class='col-md-5 osp_col_1'>
						<div class='col-md-3'>
							<label>人員姓名:</label>
						</div>
						<div class='col-md-5' style='margin-left: -1.5%'>
							<input class="form-control" type="text" name='empName'>
						</div>
					</div>
					<div class='col-md-5' style='margin-left: -15%;'>
						<div class='col-md-3'>
							<label>單位名稱:</label>
						</div>
						<div class='col-md-5' style='margin-left: -1%;'>
							<input class="form-control" type="text" name='deptchiName'>
						</div>
					</div>
				</form>

				<div class="row">
					<div align="right">
					    <button id="setMemberSearchButton" class="btn btn-default btn-sm">查詢</button>
					</div>
				</div>
				<hr>
				<button id="createMemberButton" type="button" class="btn btn-default" style='margin-bottom: 0.5%;'>新增</button>
				<table id="setRoleMemberDataTable" class="table table-striped table-bordered table-hover">
					<thead></thead>
					<tbody></tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">確定</button>
			</div>
		</div>
	</div>
</div>
<!-- END SETMEMBER MODAL -->