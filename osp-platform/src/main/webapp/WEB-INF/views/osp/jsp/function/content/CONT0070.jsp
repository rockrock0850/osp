<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp" %>

<style>
    .dataTables_length 
    {
        float : right;
    }
</style>
<!-- BEGIN VPOS UPLOAD-->
<div id="uploadContent">
	<div class="row form-group" id="VPOS" style="margin-top: 10px; height: 30px;">
	    <label class="col-md-1">VPOS檔案:</label>
	    <div style="margin-left: 5px; float: left; height: 26px;">
	        <form id="fileUploadForm" enctype="multipart/form-data">
	            <div class="fileupload fileupload-new" data-provides="fileupload">
	                <div class="input-append">
	                    <div class="uneditable-input" style="width: 330px; height: 26px; pointer-events: none; text-overflow: ellipsis;">
	                        <span id="fileName"></span>
	                    </div>
	                    <span class="btn btn-default btn-file"> 
	                        <span class="btn-file fileupload-new ">選擇檔案</span> 
	                        <input id="VPOSfile" type="file" name="file" excelType="VPOS"/>
	                    </span>
	                </div>
	            </div>
	        </form> 
	    </div>
	</div>
	<!-- END UPLOAD-->
	
	<!-- BEGIN UPLOAD-->
	<div class="row form-group" id="AIMS" style="margin-top: 10px; height: 30px;">
	    <label class="col-md-1">AIMS檔案:</label>
	    <div style="margin-left: 5px; float: left; height: 26px;">
	        <form id="fileUploadForm" enctype="multipart/form-data">
	            <div class="fileupload fileupload-new" data-provides="fileupload">
	                <div class="input-append">
	                    <div class="uneditable-input" style="width: 330px; height: 26px; pointer-events: none; text-overflow: ellipsis;">
	                        <span id="fileName"></span>
	                    </div>
	                    <span class="btn btn-default btn-file"> 
	                        <span class="btn-file fileupload-new">選擇檔案</span> 
	                        <input id="AIMSfile" type="file" name="file" excelType="AIMS"/>
	                    </span>
	                    <span class="col-md-3 col-md-offset-8" style="padding-left: 6px;">
	                        <button id="uploadButton" class="btn btn-primary btn-sm" type="button" style="display:none;">確認上傳</button>
	                    </span>
	                </div>
	            </div>
	        </form> 
	    </div>
	</div>
	<div class="row form-group">
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
	</div>
</div>
<!-- END UPLOAD-->
<!-- Begin 比對按鈕 -->
<div class="btn-group" style="float:right;">
    <button type="button" class="btn btn-sm btn-default" id="beginCompareBtn">開始比對</button>
    <button type="button" class="btn btn-sm btn-default" id="queryCompareResultBtn">查詢比對結果</button>
</div>
<!-- End 比對按鈕 -->
<br>
<hr>
<!-- BEGIN SEARCH RESULT -->
<div id='searchResult'>
    <table id="searchResultDataTable" class="table table-striped table-bordered table-hover">
        <thead></thead>
        <tbody></tbody>
    </table>
    
    <input type="hidden" name="ntAccount" value="${ntAccount}" />
</div>
<!-- END SEARCH RESULT -->

<table></table>

<script>
    require(['${contextPath}/resources/app/module/content/CONT0070-main.js'])
</script>