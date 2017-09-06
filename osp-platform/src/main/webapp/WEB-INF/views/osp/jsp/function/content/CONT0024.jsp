<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN FORM -->
<div class='form-group row'>
	<div class='row'>
		<div class='col-md-2'>
			<label><input type="checkbox" name='isNoticeSales' >通知業務</label>
		</div>
	</div>
</div>
<!-- END FORM -->

<script>
	require([ '${contextPath}/resources/app/module/content/CONT0024-main.js' ])
</script>