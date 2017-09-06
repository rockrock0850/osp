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

<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<table class="pull-right">
<tbody>
	<tr>
		<c:if test="${buzFlowVO.btnTmpSave eq 'Y'}">
			<td>
				<button type="button" class="btn btn-default btn-sm" onclick="tempSave()">暫存</button>
			</td>
		</c:if>
		<c:if test="${buzFlowVO.btnShowMessage eq 'Y'}">
			<td>
				<button type="button" class="btn btn-default btn-sm" onclick="showMessage()"><i class="fa fa-exclamation"></i></button>
			</td>
		</c:if>
		<c:if test="${buzFlowVO.btnSuccess eq 'Y'}">
			<td>
				<button type="button" class="btn btn-default btn-sm" onclick="success()">有效件</button>
			</td>
		</c:if>
		<c:if test="${buzFlowVO.btnFail eq 'Y'}">
			<td>
				<button type="button" class="btn btn-default btn-sm" onclick="fail()">無效件</button>
			</td>
		</c:if>
		<c:if test="${buzFlowVO.btnChangeOrderType eq 'Y'}">
			<td>
				<button type="button" class="btn btn-default btn-sm" onclick="changeOrderType()">更改案件類別</button>
			</td>
		</c:if>
		<c:if test="${buzFlowVO.btnShowSourceDoc eq 'Y'}">
			<td>
				<button type="button" class="btn btn-default btn-sm" onclick="showSourceDocument();">開啟影像檔-工單</button>
			</td>
		</c:if>
		<c:if test="${buzFlowVO.btnTmpLeave eq 'Y'}">
			<td>
				<button type="button" class="btn btn-default btn-sm" onclick='tempLeave()'>暫離</button>
			</td>
		</c:if>
		<c:if test="${buzFlowVO.btnReply eq 'Y'}">
			<td>
				<button type="button" class="btn btn-default btn-sm" onclick='reply()'>待系統回覆</button>
			</td>
		</c:if>
	</tr>
</tbody>
</table>


