<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp" %>
	
<!-- BEGIN UPLOAD-->
<div class="row form-group" id="uploadContent" style="margin-top: 10px; height: 30px;">
    <label class="col-md-1">檔案:</label>
    <div style="margin-left: 5px; float: left; height: 26px;">
        <form id="fileUploadForm" enctype="multipart/form-data">
            <div class="fileupload fileupload-new" data-provides="fileupload">
                <div class="input-append">
                    <div class="uneditable-input" style="width: 330px; height: 26px; pointer-events: none; text-overflow: ellipsis;">
                        <span id="fileName"></span>
                    </div>
                    <span class="btn btn-default btn-file"> 
                        <span class="btn-file fileupload-new">選擇檔案</span> 
                        <span class="btn-file fileupload-exists">選擇檔案</span> 
                        <input id="file" type="file" name="file"/>
                    </span>
                    <span class="col-md-3 col-md-offset-8" style="padding-left: 6px;">
                        <button id="uploadButton" class="btn btn-primary btn-sm" type="button" style="display:none;">確認上傳</button>
                    </span>
                    <a href="#" class="btn btn-danger fileupload-exists" id="disissFileTag" data-dismiss="fileupload" style="float: none; margin-left: -110px; display: ;">×</a>
                </div>
            </div>
        </form> 
    </div>
</div>

<script>
    require(['${contextPath}/resources/app/module/content/CONT0068-main.js'])
</script>