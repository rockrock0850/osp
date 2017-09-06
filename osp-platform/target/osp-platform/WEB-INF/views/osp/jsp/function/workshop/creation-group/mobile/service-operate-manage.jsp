<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />

<!-- BEGIN FORM -->
<form id='CONT0013'>
	<div class='form-group row'>
		<div class='col-md-2'>
			<label>日期: ${today}</label>
		</div>
	</div>
	<c:forEach items="${recordContentList}" var="content" varStatus='status'>
		<c:if test="${status.count%2 != 0}" >
			<div class='form-group row'>
		</c:if>
		
				<input class='hide' type="text" name='id_${content.itemId}' value='${content.itemId}'>
				<input class='hide' type="text" name='sort_${content.itemId}' value='${content.sortSequence}'>
				<input class='hide' type="text" name='unit_${content.itemId}' value='${content.unit}'>
				<div class='col-md-2'>
					<label>${content.itemName}:</label>
					<input class='hide' type="text" name='name_${content.itemId}' value='${content.itemName}'>
				</div>
				<div class='col-md-3'>
				
					<c:choose>
						<c:when test="${content.unit == '分'}">
							<fmt:parseNumber  parseLocale="#" integerOnly="true" value="${content.itemValue/60}" var="result" />
							<input class="form-control" type="number" name='reserv_${content.itemId}' value='${result}'>
						</c:when>
						<c:otherwise>
							<input class="form-control" type="text" name='reserv_${content.itemId}' value='${content.itemValue}'>
						</c:otherwise>
					</c:choose>
					
				</div>
				<div class='col-md-1'>
					<label>${content.unit}</label>
				</div>
				<input class='hide' type="text" name='create_date_${content.itemId}' value='${content.createDate}'>
			
		<c:if test="${status.count%2 == 0 || status.last}" >
			</div>
		</c:if>
	</c:forEach>
</form>
<!-- END FORM -->

<hr>
<div class="row">
	<div align="right">
		<button id='comfirmButton' class="btn btn-primary">確定</button>
		<button id='cancelButton' class="btn btn-default">取消</button>
	</div>
</div>

<script>
    require(['${contextPath}/resources/app/module/workshop/creation-group/mobile/service-operate-manage.js'])
</script>