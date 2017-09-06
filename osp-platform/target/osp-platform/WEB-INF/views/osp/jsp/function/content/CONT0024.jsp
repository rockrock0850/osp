<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN FORM -->
<div class='form-group row'>
	<div class='row'>
		<div class='col-md-2'>
			<label><input type="checkbox" name='isNoticeSales'>通知業務</label>
		</div>
		<div class='col-md-2' style='margin-left: -120px'>
			<input id='notifySalemanCheckbox' class="form-control" type="text" name='salesId' placeholder='請輸入業務人員員編' disabled />
		</div>
	</div>
</div>
<!-- END FORM -->

<script>
	require([ '${contextPath}/resources/app/module/content/CONT0024-main.js' ])
</script>