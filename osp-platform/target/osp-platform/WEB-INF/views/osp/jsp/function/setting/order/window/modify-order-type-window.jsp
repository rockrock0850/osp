<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN MODIFY MODAL -->
<div id="modifyModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">編輯案件類型</h4>
			</div>
			<div>
				<input type="hidden" id="hiddenBeforeCreateDate">
			</div>
			<div class="modal-body">
				<form id='modifyForm'>
					<div class="form-group row">
						<br> 
						<label class="col-md-4" align="right" style="left: 8px;">案件類別名稱 :&nbsp;&nbsp;</label> 
						<label id='orderTypeName' class="col-md-3"></label>
						<br> 
						<label class="col-md-4" align="right">*有效案件處理時間(秒) :</label>
						<div class="col-md-4">
							<input type="number" class="form-control" name='successSec'>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-4" align="right">*無效案件處理時間(秒) :</label>
						<div class="col-md-4">
							<input type="number" class="form-control" name='failSec'>
						</div>
					</div>
					
					<div class="form-group row">
					   <div class="col-md-4" style="float:left;padding-left : 67px;">
						   <label align="right">*KPI設定_日期類別:</label>
					   </div>
						<div class="col-md-4">
							<select class="form-control" id="kpiDayType" name='kpiDayType'>
                                <option value=''>請選擇</option>
                            </select>
						</div>
					</div>
					
					<div class="form-group row">
					   <div class="col-md-4" style="float:left;padding-left : 74px;">
					       <input type="checkbox" name="chkCreateDate" value="Y">
						   <label align="right">判斷進件時間 :</label>
					   </div>
						<div class="col-md-4">
							<select class="form-control" id="beforeCreateDate" name='beforeCreateDate'>
                                <option value=''>請選擇</option>
                            </select>
						</div>
					</div>
					
					<div class="form-group row">
					   <div class="col-md-4" style="float:left;padding-left : 51px;">
					       <input type="checkbox" name="isRegularTime" value="Y">
						   <label align="right">固定處理時間(分) :</label>
					   </div>
						<div class="col-md-4">
							<input type="number" class="form-control" name='regularTime'>
						</div>
					</div>
					
					<div class="form-group row">
                       <div class="col-md-4" style="float:left;padding-left : 60px;">
                           <label align="right">*KPI設定_起算時間:</label>
                       </div>
                        <div class="col-md-4">
                            <select class="form-control" name='startCountTime'>
                                <option value=''>請選擇</option>
                            </select>
                        </div>
                    </div>
                    
					<div class="form-group row">
						<label class="col-md-4" align="right">逾期時間(分) :</label>
						<div class="col-md-4">
							<input type="number" class="form-control" name='overtime'>
						</div>
						<span class="col-md-4" style="color: gray">(以預計完成時間回推)</span>
					</div>
					<br>
					<div class="form-group row">
						<label class="col-md-4" align="right">急件數大於 :</label>
						<div class="col-md-4">
							<input type="number" class="form-control" name='criticalCounts' required="required">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-4" align="right">逾件數大於 :</label>
						<div class="col-md-4">
							<input type="number" class="form-control" name='overtimeCounts' required="required">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-4" align="right">Email寄送位置:</label>
						<div class="col-md-4">
							<input type="text" class="form-control" name='email'>
						</div>
					</div>
				</form>
				<div class="modal-footer">
					<button id='confirmButton' class="btn btn-primary" data-dismiss="modal" aria-hidden="true">確定</button>
					<button id='cancelButton' class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- END MODIFY MODAL -->