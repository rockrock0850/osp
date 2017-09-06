<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp" %>

<!-- BEGIN Wait dialog -->
<div id="waitDialog" class='hide' background:#F2F2F2; color=white;" align="center">
	<div style="margin-top:5%">
		<img src="resources/flip/assets/img/loading-circular.gif">
	</div>
</div>
<!-- END Wait dialog -->

<%@include file="/WEB-INF/views/osp/jsp/common/component/modal/small-modal.jsp"%>

<div id="failBootBox" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
   	<div class="alert alert-error" style="margin: 0px; font-size: 14px;">
       	<strong>失敗！</strong><span id="msgSpan"></span>
	</div>
</div>

