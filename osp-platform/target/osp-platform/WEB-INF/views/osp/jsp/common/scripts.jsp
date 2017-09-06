<%
/**
 * Copyright (c) 2015 Far Eastone Telecommunications Co., Ltd.
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

<%@ page import="com.fet.generic.env.ApEnv"%>

<%
    String flipAssetUrl = ApEnv.get("flip.asset.url");
%> 

<%-- Image --%>
<link rel="shortcut icon" href="${contextPath}/resources/flip/assets/img/favicon.ico" type="image/gif" />
<link rel="apple-touch-icon" href="${contextPath}/resources/flip/assets/img/apple-touch-icon.png" />
	
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/flip/assets/font/font-awesome/4.2.0/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/app/main/css/main.css"/>

<script data-main="${contextPath}/resources/app/main/js/main"  
        id="flip-require" 
        flip-asset="/flip-assets"
        nsp-library="/NSP2"
        src="${contextPath}/resources/flip/assets/repository/requirejs/2.1.5/require.js">
    // [DEV]  flip-asset="/flip-assets"   
    // [SIT]  flip-asset="//10.64.70.24:8003/OSP/flip-asset"
</script>

<script type="text/javascript">
	var contextPath = '${contextPath}';
</script>