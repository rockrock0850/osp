<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp" %>

<!-- 查詢 下拉選單  內存外顯值資訊 -->

<c:forEach items='${responseData}' var='option'>
	<option value='${option.value}'>${option.text}</option>
</c:forEach>