<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<br>
<!-- BEGIN FORM -->
<div class='form-group row'>
	<form id='searchForm'>
		<div class='col-md-3'>
			<div class='col-md-3'>
				<label>員編 :</label>
			</div>
			<div class='col-md-5' style='margin-left: -6.5%'>
				<input class="form-control" type="text" name='empNo'>
			</div>
		</div>
		<div class='col-md-3' style="margin-left: -10%;">
			<div class='col-md-3'>
				<label>姓名 :</label>
			</div>
			<div class='col-md-5' style='margin-left: -5.5%'>
				<input class="form-control" type="text" name='empName'>
			</div>
		</div>
		<div class='col-md-5' style="margin-left: -10%;">
			<div class='col-md-2'>
				<label>SKILL:</label>
			</div>
			<div class='col-md-9' style='margin-left: -6%; margin-top: -0.5%;'>
				<div class='col-md-6'>
					<!-- BEGIN SELECT -->
					 <select class="form-control" name='skillId'>
						<option value=''>請選擇</option>
					</select>
					<!-- END SELECT -->
				</div>
			</div>
		</div>
	</form>
</div>
<!-- END FORM -->

<div class="row">
	<div align="right">
	    <button id="searchButton" class="btn btn-default btn-sm">查詢</button>
	    <button id="skillModalButton" class="btn btn-default fa fa-th-list fa-2x hover btn-sm"></button>
	</div>
</div>
<hr>

<!-- BEGIN SEARCH RESULT -->
<input id="stickButton" type="button" class="btn btn-default" style='margin-bottom: 0.5%;' value="貼標">
<div id='searchResult' style='display: none'>
	<table id="dataTable" class="table table-striped table-bordered table-hover">
		<thead></thead>
		<tbody></tbody>
	</table>
</div>
<!-- END SEARCH RESULT -->

<%@include file="/WEB-INF/views/osp/jsp/function/setting/skill/window/skill-member-window.jsp"%>
<%@include file="/WEB-INF/views/osp/jsp/function/setting/skill/window/stick-skill-window.jsp"%>

<script>
	require(['${pageContext.request.contextPath}/resources/app/module/setting/skill/skill-maintain-main.js'])
</script>
