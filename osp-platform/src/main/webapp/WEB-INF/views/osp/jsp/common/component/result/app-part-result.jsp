<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN FAX SERVER 案件影像檔 FORM -->
<div class="row">
	<div class="portlet">
		<div class="portlet-body form"> 
			<form class="form-horizontal" role="form">
				<div class="form-body">
					<div class="form-group row">
						<div class="col-md-12"> 
        					<label class="control-label">FAX SERVER 案件影像檔</label>
        				
        					<div id="faxServerView">
								<div app-part src="'/NSP2/app-parts/faxServer/imageProcess/imageProcess-app-part.json?&fJobId=${apId}&account=${ntAccount}'" random="true"></div>
							</div>
						</div>
      				</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- END FAX SERVER 案件影像檔 FORM -->

<script>
	require(['${contextPath}/resources/app/module/result/app-part-result-main.js']);
</script>