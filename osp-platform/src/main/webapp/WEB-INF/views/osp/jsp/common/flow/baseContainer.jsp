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

<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<% // 測試使用  %>
<%@ include file="/WEB-INF/views/osp/jsp/common/scripts.jsp" %>

<%-- 以下為顯示訊息使用 --%>
<div id="successBootBox" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
	<div class="alert alert-success" style="margin: 0px; font-size: 14px;" id="successMessageDiv">
       	<strong>成功！</strong><span id="msgSpan">操作成功</span> 
	</div>
</div>
<div id="failBootBox" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
   	<div class="alert alert-error" style="margin: 0px; font-size: 14px;">
       	<strong>失敗！</strong><span id="msgSpan"></span>
	</div>
</div>
<div id="warningBootBox" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
   	<div class="alert alert-info" style="margin: 0px; font-size: 14px;">
       	<span id="msgSpan"></span>
	</div>
</div>
<% // 測試使用  %>

<div class="row" id="buzFlow" flowId="${buzFlowVO.flowId}" 
		orderMId='${buzFlowVO.orderMId}' 
		orderTypeId='${buzFlowVO.orderTypeId}' 
		sourceSysId='${buzFlowVO.sourceSysId}'
		orderStatus='${buzFlowVO.orderStatus}'>
	<div style="padding-left: 7px;font-weight: bold;">
		<h3>案件類型: ${buzFlowVO.flowNm}</h3>
	</div>
	
	<div class="col-md-12 col-sm-12">
		<div class="portlet">
			<div class="portlet-body">
				<ul class="nav nav-tabs">
					<c:forEach items="${buzFlowVO.buzFlowStepLs}" var="step" varStatus="status">
						<c:set var='isFirst' value='${status.first}' />
						
						<c:choose>
							<c:when test="${isFirst}">
								<li class="active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						
						<a href="#${step.stepId}" data-toggle="tab" id="${step.stepId}" isLoad="false">${step.stepNm}</a>
						</li>
					</c:forEach>
				</ul>
				<div class="tab-content" id="tabContainers">
					<c:forEach items="${buzFlowVO.buzFlowStepLs}" var="step" varStatus="status">
						<c:if test="${status.first}" var="isFirst">
							<div class="tab-pane fade active in" id="${step.stepId}"></div>
						</c:if>
						<c:if test="${not isFirst }">
							<div class="tab-pane fade" id="${step.stepId}"></div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	
	<%-- 匯入按鈕 --%>
	<%@ include file="button/button.jsp" %>
	
	<!-- 匯入按鈕彈出視窗 -->
	<%@include file="/WEB-INF/views/osp/jsp/common/flow/button/window/temp-leave-window.jsp"%>
	<%@include file="/WEB-INF/views/osp/jsp/common/flow/button/window/fail-window.jsp"%>
	<%@include file="/WEB-INF/views/osp/jsp/common/flow/button/window/success-window.jsp"%>
	<%@include file="/WEB-INF/views/osp/jsp/common/flow/button/window/change-order-type-window.jsp"%>
	<%@include file="/WEB-INF/views/osp/jsp/common/flow/button/window/reply-window.jsp"%>
	<%@include file="/WEB-INF/views/osp/jsp/common/flow/button/window/show-message-window.jsp"%>
</div>

<script type="text/javascript">
	require(['../../module/flow/flow-main']);
</script>
