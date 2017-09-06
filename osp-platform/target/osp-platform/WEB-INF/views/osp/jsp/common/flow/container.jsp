<%
/**
 * Copyright (c) 2017 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */
%>

<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<% // 測試使用  %>
<%@ include file="/WEB-INF/views/osp/jsp/common/scripts.jsp" %>

<script type="text/javascript">
	var appPartContents = [ '',
			<c:forEach items="${stepPageLs}" var="stepPage">
				<c:if test="${stepPage.template eq 'apppart.jsp'}">
					,'${stepPage.contentId}'
				</c:if>
			</c:forEach>                   
		];
</script>

<c:forEach items="${stepPageLs}" var="stepPage" varStatus='status'>
	<div class="panel-group osp_accordion" id="accordion">
		<div class="panel">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" 
					   data-parent="#accordion" 
					   href="#${stepPage.stepId}-${status.count}">${stepPage.contentNm}</a>
				</h4>
			</div>
			<div id="${stepPage.stepId}-${status.count}" class="panel-collapse collapse in">
				<div class="panel-body">
					<tiles:insertTemplate template="/WEB-INF/views/osp/jsp/common/flow/template/${stepPage.template}" flush="true">
						<tiles:putAttribute name="URL" value="${stepPage.link}" />
						<tiles:putAttribute name="parameter" value="${stepPage.parameter}" />
						<tiles:putAttribute name="contentId" value="${stepPage.contentId}" />
						<tiles:putAttribute name="stepId" value="${stepPage.stepId}" />
						<tiles:putAttribute name="openBrowser" value="${stepPage.openBrowser}" />
						<tiles:putAttribute name="httpMethod" value="${stepPage.httpMethod}" />
						<tiles:putAttribute name="ospKey" value="${stepPage.ospKey}" />
					</tiles:insertTemplate>
				</div>
			</div>
		</div>
	</div>
</c:forEach>

<script type="text/javascript">
	$.each(appPartContents, function( index, value ) {
		if (value != null && value != '') {
			var $container = $("div#" + value + "_apppart_view");
			
			angular.bootstrap($container, ['ngRoute', 'ngSanitize', 'thunder', 'thunder.ui', 'thunder.wizard', 
			                               'nspDirective', 'genericServices']);
		}
	});
			
	// appPartContents = null;
</script>