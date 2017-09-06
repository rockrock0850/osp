<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- Import File Begin -->
<div class="row ">
	<div class="col-md-12">
		<div class="portlet">
			<div class="portlet-title">
                <div class="caption">匯入</div>
        	</div>
        	<div class="portlet-body form">
        		<form role="form" id="importForm" class="form-horizontal">
        			<div class="form-body">
        				<div class="form-group">
        					<label class="col-md-1">班表年月:</label>
							<div class="col-md-2">
								<select name="month" style='width: 100%'></select>
							</div>
							<div class="col-md-2">
								<select name="year" style='width: 100%'></select>
							</div>
        				</div>
	        			<div class="form-group">
	        				<!-- UPLOAD FILE COMPONENT STATRT -->
							<%@include file="/WEB-INF/views/osp/jsp/common/component/file/file-upload-single.jsp"%>
							<!-- UPLOAD FILE COMPONENT END -->
	        			</div>
	        			<div class="form-group">
	        				<div class="form-actions right">
								<button id='importButton' type="button" class="btn btn-primary">匯入</button>
							</div>
						</div>        				
        			</div>
        		</form>
        	</div>
		</div>
	</div>  
</div>
<!-- Import File End -->
<!-- Export File Begin -->
<div class="row ">
	<div class="col-md-12">
		<div class="portlet">
			<div class="portlet-title">
                <div class="caption">匯出</div>
        	</div>
        	<div class="portlet-body form">
        		<form role="form" id="exportSelect" class="form-horizontal">
        			<div class="form-body">
        				<div class="form-group">
        					<label class="col-md-1">班表年月:</label>
							<div class="col-md-2">
								<select name="month" style='width: 100%'></select>
							</div>
							<div class="col-md-2">
								<select name="year" style='width: 100%'></select>
							</div>
        				</div>
	        			<div class="form-group">
	        				<div class="form-actions right">
								<a id='downloadButton' href="#" style="margin-left: 10px;">
									<i class="fa fa-2x fa-download" aria-hidden="true"></i>
								</a>
							</div>
						</div>
        			</div>
        		</form>
        	</div>
		</div>
	</div>  
</div>
<!-- Export File End -->

<script>
	require([ '${pageContext.request.contextPath}/resources/app/module/setting/shift/shift-content-main.js' ])
</script>