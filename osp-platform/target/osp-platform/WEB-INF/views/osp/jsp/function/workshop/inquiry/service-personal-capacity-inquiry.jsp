<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<c:set var = "memberList" value = "${memberList}" />

<!-- BEGIN FORM -->
<div class="row">
	<div class="portlet">
		<div class="portlet-body form"> 
			<form id='capacityInquiryForm' class="form-horizontal" role="form">
				<div class="form-body">
					<div class="form-group row">
						<div class="col-md-6"> 
        				<label class="control-label">帳號名稱</label>
	        			<select id="empNoSelect" name="empNo" class="form-control placeholder-no-fix">
	        				<c:if test="${fn:length(memberList) > 1}">
	        					<option value="">全部</option>
	        				</c:if>
	        			
	        				<c:forEach items="${memberList}" var="memberItem">
	        					<option value="${memberItem.empNo}">${memberItem.empName}</option>
	        				</c:forEach>
	        			</select>
						</div>
      				</div>
  					<div class="form-group row">
						<label class="control-label">區間日期選擇器</label>
						<div class="input-group input-large date-picker input-daterange" data-date-format="yyyy-mm-dd">
							<input type="text" class="form-control" name="startDate">
							<span class="input-group-addon"> 至 </span>
							<input type="text" class="form-control" name="endDate">
						</div>
					</div>
				</div>
				<div class="form-actions right">
					<button id='searchBtn' type="button" class="btn btn-primary">查詢</button>
					<button id='exportBtn' type="button" class="btn btn-primary">匯出</button>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- END FORM -->
	
<!-- BEGIN SEARCH RESULT -->
<div class="row">
	<div class="portlet">
		<div class="portlet-body form"> 
			<form class="form-horizontal form-bordered">
				<div class="form-body">
					<div class="form-group">
						<table id="gridView" class="table" cellpadding="0" cellspacing="0" border="0" width="100%">
						</table>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- END SEARCH RESULT -->

<script>
	require(['${contextPath}/resources/app/module/workshop/inquiry/agent-capacity-inquiry-main.js' ]);
</script>