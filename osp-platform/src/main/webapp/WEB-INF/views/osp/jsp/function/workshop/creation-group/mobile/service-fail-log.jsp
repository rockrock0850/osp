<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- Fail Log Begin -->
<div class="row ">
	<div class="col-md-12">
		<c:forEach items="${stepPageLs}" var="content" varStatus='status'>
			<div class="portlet">
				<div class="portlet-title">
	                <div class="caption">${content.contentNm}</div>
	        	</div>
	        	<div class="portlet-body form">
	        		<form role="form">
	        			<div class="form-body">
							<div class="form-group">
        						<iframe src="${content.link}${content.parameter}" style="width: 100%; height: 650px;"></iframe>
        					</div>
	        			</div>
	        		</form>
	        	</div>
			</div>
		</c:forEach>
	</div>  
</div>
<!-- Fail Log End -->

<!-- 基本上各個流程首頁唯一的區別就是此處會引入不同的js -->
<script>
	require([ '${contextPath}/resources/app/module/workshop/creation-group/mobile/service-fail-log.js' ])
</script>