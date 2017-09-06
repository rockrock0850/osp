<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN FORM -->
<div id='CONT0019Form'>
	<table>
		<tr>
			<td><input name='imsi' type="radio" value='GSM' checked><span>GSM</span></td>
			<td><input name='imsi' type="radio" value='Vozp'><span>Vozp</span></td>
			<td><input name='imsi' type="radio" value='PCRF'><span>PCRF</span></td>
			<td><input name='imsi' type="radio" value='VOLTE'><span>VOLTE</span></td>
		</tr>
	</table>
	<div class="form-group row">
		<label class="col-md-1">門號:</label>
		<div class="col-md-2">
			<input type="text" class="form-control" name='msisdn'>
		</div>
	</div>
	<div class="form-group row">
		<label class="col-md-1">HLR/PCRF</label>
		<div class="col-md-2">
			<select class="form-control" name='action'>
				<option value='HLR_QUERY'>HLR</option>
				<option value='VLR_DISCONNECT'>PCRF</option>
			</select>
		</div>
		<div class="col-md-1" style="margin-left: 87px;">
			<button id='cont0019SearchButton' class="btn btn-sm btn-default">查詢</button>
		</div>
	</div>
</div>
<!-- END FORM -->

<hr>

<div id='CONT0019SearchResult' class="well" style='display: none;'>
	<textArea
		style="margin: 0px; width: 1142px; height: 120px; background: transparent; border: none; resize: none;"></textArea>
</div>

<script>
	require([ '${contextPath}/resources/app/module/content/CONT0019-main.js' ])
</script>