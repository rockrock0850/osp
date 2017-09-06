<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<form id='modifyForm_23'>
	<table class="table">
		<tr>
			<td><label>門號</label></td>
			<td>:&nbsp;<input type='text' name='msisdn' />
			</td>
			<td><label>ID</label></td>
			<td>:&nbsp;<input type="text" name='custId'></td>
			<td><label>啟用IVR Code</label></td>
			<td>:&nbsp;<input type="text" name='ivrCode'></td>
		</tr>
		<tr>
			<td><label>業務員編</label></td>
			<td>:&nbsp;<input type="text" name='salesId'></td>
			<td><label>促銷代碼</label></td>
			<td>:&nbsp;<input type="text" name='promotionId'></td>
			<td><label>證號類型</label></td>
			<td>:&nbsp;
				<select class="form-control" name='custType' style="width:70%; display: inline-block;">
					<option value=''>請選擇</option>
				</select>
			</td>
		</tr>
		<tr>
			<td><label>公司負責人證照號碼</label></td>
			<td>:&nbsp;<input type="text" name='corpPicTaxid'></td>
			<td><label>帳單週期</label></td>
			<td>:&nbsp;<label name='cycleDate'></label></td>
			<td><label>門號類別</label></td>
			<td>:&nbsp;<label name='mobileGeneration'></label></td>
		</tr>
		<tr>
			<td><label>使用者姓名</label></td>
			<td>:&nbsp;<label name='custName'></label></td>
			<td><label>門號(客戶)狀態</label></td>
			<td>:&nbsp;<label name='contractStatusValue'></label></td>
			<td><label>ARPB</label></td>
			<td>:&nbsp;<label name='arpb'></label>
		</tr>
		<tr>
			<td><label>生日</label></td>
			<td>:&nbsp;<label name='birthDate'></label></td>
			<td><label>SIM卡號</label></td>
			<td>:&nbsp;<label name='sim'></label></td>
			<td><label>門號啟用日</label></td>
			<td>:&nbsp;<label name='activeDate'></label></td>
		</tr>
		<tr>
			<td><label>簽核層級</label></td>
			<td>:&nbsp;<label name='authLevel'></label></td>
			<td><label>S1帳號(續約帳號)</label></td>
			<td>:&nbsp;<label name='accountId'></label></td>
			<td><label></label></td>
			<td></td>
		</tr>
	</table>
	<!-- BEGIN FORM -->
	<div class='col-xs-offset-3'>
		<div class='form-group row'>
			<div class='col-xs-12'>
				<div class='col-md-2'>
					<button type="button" class="btn btn-primary btn-sm" id="getSpecialOfferButton">申請學生溫暖長青</button>
				</div>
				<div class='col-md-2'>
					<button type="button" class="btn btn-primary btn-sm" id="eventListBtn">事件列表</button>
				</div>
				<div class='col-md-2' style='margin-left: -7%;'>
					<button type="button" class="btn btn-primary btn-sm" id="futureTaskBtn">Future task</button>
				</div>
				<div class='col-md-2' style='margin-left: -5%;'>
					<button type="button" class="btn btn-primary btn-sm" id="idHistoryBtn">證號歷史查詢</button>
				</div>
				<div class='col-md-2' style='margin-left: -5%;'>
					<button type="button" class="btn btn-primary btn-sm" id="queryButton" >查詢</button>
				</div>
				<div class='col-md-2'>
					<button id="saveButton" type="button" class="btn btn-success">
						<span style="color: black;">儲存</span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- END FORM -->
	<!-- BEGIN 付款人資訊 -->
	<label class="osp_table_title">付款人資訊</label>
	<table class="table">
		<tr>
			<td><label>客戶等級</label></td>
			<td>:&nbsp;<label name='customerScore'></label></td>
			<td><label>證照類別</label></td>
			<td>:&nbsp;<label name='identificationType'></label></td>
			<td><label>用戶姓名</label></td>
			<td>:&nbsp;<label name='payerName'></label></td>
		</tr>
		<tr>
			<td><label>證照號碼</label></td>
			<td>:&nbsp;<label name='identificationNumber'></label></td>
			<td><label>公司名稱</label></td>
			<td>:&nbsp;<label name='companyName'></label></td>
			<td><label>生日</label></td>
			<td>:&nbsp;</td>
		</tr>
		<tr>
			<td><label>國籍</label></td>
			<td>:&nbsp;<label name='citizenshipType'></label></td>
			<td><label>帳單地址</label></td>
			<td>:&nbsp;<label name='billAddress'></label></td>
			<td><label>戶籍地址</label></td>
			<td>:&nbsp;<label name='houseHoldAddress'></label></td>
		</tr>
		<tr>
			<td><label>聯絡電話</label></td>
			<td>:&nbsp;<label name='contactTelList'></label></td>
			<td><label>帳單電子信箱</label></td>
			<td>:&nbsp;<label name='billEmailList'></label></td>
			<td><label>其他電子信箱</label></td>
			<td>:&nbsp;<label name='otherEmailList'></label></td>
		</tr>
		<tr>
			<td><label>遠傳相關優惠簡訊</label></td>
			<td>:&nbsp;<label name='promotionSMSIndicator'></label></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	<!-- END 付款人資訊 -->
	<!-- BEGIN 使用人資訊 -->
	<label class="osp_table_title">使用人資訊</label>
	<table class="table">
		<tr>
			<td><label>姓名</label></td>
			<td>:&nbsp;<label name='userName'></label></td>
			<td><label>第二證照類別</label></td>
			<td>:&nbsp;<label name='secondIdentificationType'></label></td>
			<td><label>第二證照號碼</label></td>
			<td>:&nbsp;<label name='secondIdentificationNumber'></label></td>
		</tr>
		<tr>
			<td><label>個人電子信箱</label></td>
			<td>:&nbsp;<label name='personalEmailList'></label></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	<!-- END 使用人資訊 -->
	
	<!-- BEGIN 帳務資料 -->
	<label class="osp_table_title">帳務資料</label>
	<table class="table">
		<tr>
			<td><label>帳單結帳日</label></td>
			<td>:&nbsp;<label name='cycleDate'></label></td>
			<td><label>付款方式</label></td>
			<td>:&nbsp;<label name='paymentMethod'></label></td>
			<td><label></label></td>
			<td></td>
		</tr>
	</table>
	
	<label class="">帳務詳細資訊</label>
	<table id="billDetailDataTable" class="table table-striped table-bordered table-hover">
		<thead></thead>
		<tbody></tbody>
	</table>
	<!-- END 帳務資料 -->
</form>

<!-- BEGIN FUTURE TASK MODAL -->
<div id="futureTaskModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog" style='width: 60%;'>
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">FutureTask</h4>
			</div>
			<div class="modal-body">
				<div id="futureTaskView">
					<div app-part src="'/CRMCOMMON_EB/app-parts/module-right/ncp_right_fastFlow/unfinished-task-app-part/task.json?linkId=220146'" random="true"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- END FUTURE TASK MODAL -->

<!-- BEGIN 事件列表  MODAL -->
<div id="eventListModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog" style='width: 60%;'>
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">事件列表</h4>
			</div>
			<div class="modal-body">
				<div id="eventListView">
					<div app-part src="'/NCP/app-parts/module-right/ncp_right_fastFlow/ncp_right_fastFlow_serviceRecordModal/ncp_right_fastFlow_serviceRecordModal.json?carouselPage=carouselPage0&custType=FET&from=NSP'" random="true"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- END 事件列表  MODAL -->

<!-- BEGIN 證號歷史查詢 MODAL -->
<div id="idHistoryModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog" style='width: 60%;'>
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">證號歷史查詢</h4>
			</div>
			<div class="modal-body">
				<table id="idHistoryDataTable" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>帳號</th>
							<th>產品別</th>
							<th>姓名</th>
							<th>電話</th>
							<th>啟用日</th>
							<th>永停日</th>
							<th>狀態</th>
							<th>原因</th>
							<th>均帳</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- END 證號歷史查詢 MODAL -->

<!-- BEGIN SPECIAL OFFER MODAL -->
<%@include file="/WEB-INF/views/osp/jsp/function/content/window/query-special-offer-window.jsp"%>
<!-- END SPECIAL OFFER MODAL -->

<script>
	require([ '${contextPath}/resources/app/module/content/CONT0023-main.js' ])
</script>