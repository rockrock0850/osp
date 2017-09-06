<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp" %>

<c:set var="breadcrumbItemLv1" value="${breadcrumbInfo}" />

<!-- BEGIN PAGE ROW -->
<div id="breadcrumb" class="row">
	<div class="col-md-12 col-sm-12" style="margin:-2.9% 0% -0.7% 1.7%;">
		<!-- BEGIN BREADCRUMB
			需要套用舊版的Breadcrumb CSS
			新版沒有Breadcrumb CSS可以用
			可以將舊版的Breadcrumb CSS新增至osp-style.css檔案
			客製化使用( P.S. 樣式名稱需要重新定義 )
		-->
		<ul class="osp_breadcrumb">
			<li><span><a href="${pageContext.request.contextPath}/console-index.action" title="首頁"><i class="icos-home"></i></a></span></li>
			<li breadcrumbId="${breadcrumbItemLv1.menuId}"><span>${breadcrumbItemLv1.menuText}</span></li>
			
			<c:set var="breadcrumbItemLv2List" value="${breadcrumbItemLv1.subMenuList}" />
			
			<c:if test="${not empty breadcrumbItemLv2List && fn:length(breadcrumbItemLv2List) >= 1}">
				<c:set var="breadcrumbItemLv2" value="${breadcrumbItemLv2List[0]}" />
				
				<li><span>${breadcrumbItemLv2.menuText}</span></li>
					
				<c:set var="breadcrumbItemLv3List" value="${breadcrumbItemLv2.subMenuList}" />
				
				<c:if test="${not empty breadcrumbItemLv3List && fn:length(breadcrumbItemLv3List) >= 1}">
					<c:set var="breadcrumbItemLv3" value="${breadcrumbItemLv3List[0]}" />
					
					<li><span>${breadcrumbItemLv3.menuText}</span></li>
							
					<c:set var="breadcrumbItemLv4List" value="${breadcrumbItemLv3.subMenuList}" />
			
					<c:if test="${not empty breadcrumbItemLv4List && fn:length(breadcrumbItemLv4List) >= 1}">
						<c:set var="breadcrumbItemLv4" value="${breadcrumbItemLv4List[0]}" />
						
						<li><span>${breadcrumbItemLv4.menuText}</span></li>
					</c:if>
				</c:if>
			</c:if>
		</ul>
		<!-- END BREADCRUMB -->
	</div>
</div>
<!-- END PAGE ROW -->
