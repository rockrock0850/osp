<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp" %>

<!-- BEGIN FORM -->
<!-- 注意: 此頁沒有JS檔案, 直接由serviceBuzRecordContent傳入元件資料並用JSTL的方式產出頁面內容( 20170504 ) -->
<c:forEach items="${recordContentList}" var="content" varStatus='status'>
	<c:if test="${status.count%2 != 0}" >
		<div class='form-group row'>
	</c:if>
	
			<input class='hide' type="text" name='id_${content.itemId}' value='${content.itemId}'>
			<div class='col-md-3'>
			
				<c:choose>
				    <c:when test="${content.itemValue == 'Y'}">
						<input type='checkbox' name='value_${content.itemId}' checked>
				    </c:when>
				    <c:otherwise>
						<input type='checkbox' name='value_${content.itemId}'>
				    </c:otherwise>
				</c:choose>
				
				<label>${content.itemName}</label>
				<input class='hide' type="text" name='name_${content.itemId}' value='${content.itemName}''>
				<div style="margin-top: -10px;">${content.itemRemark}</div>
			</div>
			<div class='col-md-3'>
				<input class="form-control" type="text" name='remark_${content.itemId}' value='${content.remark}'>
			</div>
		
	<c:if test="${status.count%2 == 0 || status.last}" >
		</div>
	</c:if>
</c:forEach>
<!-- END FORM -->