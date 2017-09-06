<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN UPLOAD-->
<div>
	<label class="col-md-1">檔案名稱:</label>
	<div class="fileupload col-md-5" data-provides="fileupload">
		<div class="uneditable-input span4">
			<i class="fa fa-file-o fileupload-exists"></i>
			<span name="uploadName"></span>
		</div>
		<span class="btn btn-file"> 
			<span class="fileupload">選擇檔案</span>
			<input type="file" class="btn-file" id="upload" name="upload" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" />
		</span>
	</div>

	<button type="button" id="uploadButton" class="btn btn-primary btn-sm" style="display:none">確認上傳</button>
</div>
<div id='uploadInformation' class="hide">
	<table id="uploadInfoGrid" class='table'>
		<thead>
			<tr>
				<th>檔案名稱</th>
				<th>檔案大小</th>
				<th>檔案類型</th>
				<th class='hide'>Path</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
</div>
<!-- END UPLOAD-->

<script>
	require([ '${pageContext.request.contextPath}/resources/app/common/component/file/file-upload-single-main.js' ])
</script>