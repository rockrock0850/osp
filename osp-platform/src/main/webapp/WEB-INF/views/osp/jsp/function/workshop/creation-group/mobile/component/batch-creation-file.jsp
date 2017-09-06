<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>
<div id='batchCreationFile' style='display: none;'>
	<!-- BEGIN UPLOAD-->
	<div class="form-group row batch_creation_file_open_row1">
		<div class='col-md-2 batch_creation_file_col_1'>
			<label>檔案名稱:</label>
		</div>
		<div class='col-md-10'>
			<form id="fileUploadForm" enctype="multipart/form-data">
				<div class="fileupload fileupload-new" data-provides="fileupload">
					<div class="input-append batch_creation_file_col_2">
						<div class="uneditable-input">
							<span id='fileName'></span>
						</div>
						<span class="btn btn-default btn-file"> 
							<span class="btn-file fileupload-new ">選擇檔案</span> 
							<input id="file" type="file" name="file" />
						</span>
						<button id="uploadButton" class="btn btn-primary" type="button" style="display: none;">確認上傳</button>
					</div>
				</div>
			</form> 
		</div>
	</div>
	<!-- END UPLOAD-->
</div>