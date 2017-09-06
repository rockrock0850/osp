<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN FORM -->
<form id='modifyForm'>
	<table class="table">
		<tr>
			<td><label>門號</label></td>
			<td>:&nbsp;<input type='text' name='msisdn' />
			</td>
			<td><label>ID</label></td>
			<td>:&nbsp;<input type="text" name='custId'>
			</td>
			<td><label>啟用IVR Code</label></td>
			<td>:&nbsp;<input type="text" name='ivrCode'>
			</td>
		</tr>
		<tr>
			<td><label>業務員編</label></td>
			<td>:&nbsp;<input type="text" name='salesId'>
			</td>
			<td><label>促銷代碼</label></td>
			<td>:&nbsp;<input type="text" name='promotionId'>
			</td>
			<td><label>證號類型</label></td>
			<td>:&nbsp;
				<select class="form-control" name='custType' style="width:70%; display: inline-block;">
					<option value=''>請選擇</option>
				</select>
			</td>
			
		</tr>
		<tr>
			<td><label>公司負責人證照號碼</label></td>
			<td>:&nbsp;<input type="text" name='corpPicTaxid'>
			</td>
			<td class="osp_table_td">
				<button id='modifyButton' class="btn btn-primary btn-sm" type="button">儲存</button>
			</td>
			<td></td>
		</tr>
	</table>
</form>
<!-- END FORM -->

<script>
    require(['${contextPath}/resources/app/module/content/CONT0022-main.js'])
</script>