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

<script type="text/javascript">

$(document).ready(function() {	
	var url = '<tiles:getAsString name="URL" /><tiles:getAsString name="parameter" />';
	var openBrowser = '<tiles:getAsString name="openBrowser" />';
	
	if (openBrowser == 'IE') {
		var urlDecodeFlag = "true";
		var singleFlag = "false";
		
		doOpenBrowser(openBrowser, url, urlDecodeFlag, singleFlag);
	} else {
		var settings = {
			url : url,
			directLink : true
		};

		$.openWin(settings);		
	}
});

</script>

