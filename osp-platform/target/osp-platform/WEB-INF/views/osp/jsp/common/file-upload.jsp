<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN UPLOAD-->
<div class="row form-group" style="margin-top: 10px; height: 30px;">
	<label class="col-md-1">檔案名稱:</label>
	<div style="margin-left: 5px; float: left; height: 26px;">
		<form id="fileUploadForm" enctype="multipart/form-data">
			<div class="fileupload fileupload-new" data-provides="fileupload">
				<div class="input-append">
					<div class="uneditable-input" style="width: 330px; height: 26px; pointer-events: none; text-overflow: ellipsis;">
						<span id="fileName"></span>
					</div>
					<span class="btn btn-default btn-file"> 
						<span class="btn-file fileupload-new">選擇檔案</span>  
						<input id="file" type="file" name="file" />
					</span>
					<button id="uploadButton" class="btn btn-primary btn-sm" type="button" style="display: none;">確認上傳</button>
					<a href="#" class="btn btn-danger fileupload-exists" data-dismiss="fileupload" style="float: none; margin-left: -110px; display: none;">×</a>
				</div>
			</div>
		</form>
	</div>
</div>
<div id='fileInformation' style='display: none;'>
	<table class='table'>
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
	<div class="form-actions right">
		<button id='importButton' type="button" class="btn btn-primary">匯入</button>
	</div>
</div>
<!-- END UPLOAD-->