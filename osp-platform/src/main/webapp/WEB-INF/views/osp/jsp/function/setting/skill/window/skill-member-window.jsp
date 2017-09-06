<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN SKILL MODAL -->
<div id="skillModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true" data-backdrop="false">
<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">對應案件類別</h4>
		</div>
		<div class="modal-body">
		    <table  id="skillDataTable" class="table table-striped table-bordered table-hover">
		        <thead>
		        </thead>
		        <tbody>
		        </tbody>
		    </table>
		</div>
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">確定</button>
		</div>
	</div>
	</div> 
</div>
<!-- END SKILL MODAL -->