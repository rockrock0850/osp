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
<div class="page-content fullwidth" style='margin-left: 0px !important;'> 
<tiles:insertAttribute name="content" />
</div>
</html>