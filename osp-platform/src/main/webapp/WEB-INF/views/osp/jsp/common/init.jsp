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

<%@ page pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var = "numberPattern" value="#,##0.###" />
<c:set var = "minAndRatePattern" value="#,##0.#######" />
<c:set var = "moneyPattern" value="#,##0.00" />
 
<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />
<c:set var = "reqParamVO" value = "${requestParamVO}" />