<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN SEARCH RESULT -->
<div id='info'>
	<div class='form-group row'>
		<button id='creationButton' type="button" class="btn btn-default" style='display: none;'>
			建檔
		</button>
		<button id='batchCreationButton' type="button" class="btn btn-default" style='display: none;'>
			批次建檔
		</button>
		<button id='notifyOtherSalesButton' type="button" class="btn btn-default" style='display: none;'>
			通知它業者
		</button>
	</div>
	<div class='form-group row'>
		<div class='col-md-4'>
			<div class='col-md-3'>
				<label>全部案件:</label>
			</div>
			<div class='col-md-3' style='margin-left: -5%'>
				<label id='sum'></label>
			</div>
		</div>
		<div class='col-md-4' style='margin-left: -20%;'>
			<div class='col-md-3'>
				<label>急件:</label>
			</div>
			<div class='col-md-3' style='margin-left: -12%'>
				<label id='critical'></label>
			</div>
		</div>
		<div class='col-md-4' style='margin-left: -23%;'>
			<div class='col-md-3'>
				<label>即將逾期件:</label>
			</div>
			<div class='col-md-3' style='margin-left: -1.5%'>
				<label id='overdue'></label>
			</div>
		</div>
	</div>
	<div class='form-group row'>
		<table id="dataTable" class="table table-striped table-bordered table-hover">
			<thead></thead>
			<tbody></tbody>
		</table>
	</div>
    <textarea id="responseJson" style="display: none;">
        ${responseData}
    </textarea>
</div>
<!-- END SEARCH RESULT -->

<!-- BEGIN Content -->
<div id="caseContent"></div>
<!-- END Content -->

<%@include file="/WEB-INF/views/osp/jsp/function/workshop/creation-group/mobile/component/notify-other-sales.jsp"%>
<%@include file="/WEB-INF/views/osp/jsp/function/workshop/creation-group/mobile/component/creation-file.jsp"%>
<%@include file="/WEB-INF/views/osp/jsp/function/workshop/creation-group/mobile/component/batch-creation-file.jsp"%>
<%@include file="/WEB-INF/views/osp/jsp/function/workshop/creation-group/mobile/component/window/send-email-window.jsp"%>

<script>
require([ '${contextPath}/resources/app/module/workshop/creation-group/mobile/component/template.js' ])
</script>