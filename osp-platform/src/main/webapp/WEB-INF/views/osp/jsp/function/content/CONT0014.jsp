<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN FORM -->
<div id='CONT0014Form' class='form-group'>
	<div class='row'>
		<div class='col-md-2'>
			<label>促銷代碼:</label>
		</div>
		<div class='col-md-2' style='margin-left: -130px;'>
			<input class='form-control' name='promotionId' />
		</div>
		<div class='col-md-1'>
			<button id='cont0014SearchButton' class="btn btn-primary btn-sm" type="button">查詢</button>
		</div>
	</div>
</div>
<!-- END FORM -->

<hr>

<div id='CONT0014SearchResult' style='display: none;'>
	<p id='attach'></p>
	<label class="osp_table_name">促代</label>
	<table id='promotion' class='table'>
		<thead>
			<tr>
				<th>促銷方案代碼</th>
				<th>促代說明</th>
				<th>促代類型</th>
				<th>促代類別</th>
				<th>起始日</th>
				<th>終止日</th>
				<th>門號類別</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>

	<label class="osp_table_name">折扣項目</label>
	<table id='subPromotion' class='table'>
		<thead>
			<tr>
				<th>折扣名稱</th>
				<th>HappyGo贈點</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<label class="osp_table_name">加值項目</label>
	<table id='vasOffer' class='table'>
		<thead>
			<tr>
				<th>加值名稱</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
</div>

<script>
	require([ '${contextPath}/resources/app/module/content/CONT0014-main.js' ])
</script>