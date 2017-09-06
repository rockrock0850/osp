<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>

<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="MobileOptimized" content="320">
<meta content="" name="description"/>
<meta content="" name="author"/>

<%@ include file="/WEB-INF/views/osp/jsp/common/scripts.jsp"%>

</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="page-header-fixed">
<!-- BEGIN WRAPPER -->
<div class="wrapper row-off-canvas"> 
	<!-- BEGIN HEADER -->
	<tiles:insertAttribute name="header" />
	<!-- END HEADER --> 
	
	<!-- BEGIN PAGE CONTAINER -->
	<div id="content" class="page-container"> 
		<!-- BEGIN PAGE SIDEBAR -->
		<tiles:insertAttribute name="menu" />
		<!-- END PAGE SIDEBAR --> 
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content-wrapper"> 
			<div class="page-content"> 
				<div class='osp_gap'></div>
				<tiles:insertAttribute name="breadcrumb" />
				<tiles:insertAttribute name="content" />
			</div>
		</div>
		<!-- END PAGE CONTENT -->
	</div> 
	<!-- END PAGE CONTAINER --> 
	
	<!-- BEGIN Component -->
	<tiles:insertAttribute name="component" />
	<!-- END Component --> 
	
	<!-- BEGIN FOOTER -->
	<tiles:insertAttribute name="footer" />
	<!-- END FOOTER --> 
</div>
<!-- END WRAPPER --> 
</body>
<!-- END BODY -->

</html>