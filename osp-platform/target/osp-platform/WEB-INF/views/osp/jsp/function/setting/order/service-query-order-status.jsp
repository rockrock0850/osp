<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp"%>

<!-- BEGIN FORM -->
<form id='searchForm'>
    <div class="form-group row">
        <div class='col-md-2'>
            <label style="padding-left: 57px;">進件時間:</label>
        </div>
        <!-- BEGIN DATE TIME RANGE PICKER 1 -->
        <div class='col-md-10 order_assign_open_datetime'>
            <div class="col-md-3 osp_datetime_1 order_assign_open_datetime_1">
                <div class="input-group date date-datetimepicker">
                    <input class="form-control" type="text" name="sourceCreateTimeBegin" id="sourceCreateTimeBegin""">
                    <span class="input-group-addon input-group-btn">
                        <button class="btn btn-default" type="button">
                            <i class="fa fa-calendar"></i>
                        </button>
                    </span>
                </div>
            </div>
            <div class="col-md-2">
                <span>~</span>
            </div>
            <div class="col-md-3 osp_datetime_2 order_assign_open_datetime_2">
                <div class="input-group date date-datetimepicker">
                    <input class="form-control" type="text" name='sourceCreateTimeEnd'>
                    <span class="input-group-addon input-group-btn">
                        <button class="btn btn-default" type="button">
                            <i class="fa fa-calendar"></i>
                        </button>
                    </span>
                </div>
            </div>
        </div>
        <!-- END DATE TIME RANGE PICKER 1 -->
    </div>
    <div class="form-group row">
        <div class='col-md-2'>
            <label style="padding-left: 57px;">結案時間:</label>
        </div>
        <!-- BEGIN DATE TIME RANGE PICKER 1 -->
        <div class='col-md-10 order_assign_open_datetime'>
            <div class="col-md-3 osp_datetime_1 order_assign_open_datetime_1">
                <div class="input-group date date-datetimepicker">
                    <input class="form-control" type="text" name='orderFinishDateBegin'>
                    <span class="input-group-addon input-group-btn">
                        <button class="btn btn-default" type="button">
                            <i class="fa fa-calendar"></i>
                        </button>
                    </span>
                </div>
            </div>
            <div class="col-md-2">
                <span>~</span>
            </div>
            <div class="col-md-3 osp_datetime_2 order_assign_open_datetime_2">
                <div class="input-group date date-datetimepicker">
                    <input class="form-control" type="text" name='orderFinishDateEnd'>
                    <span class="input-group-addon input-group-btn">
                        <button class="btn btn-default" type="button">
                            <i class="fa fa-calendar"></i>
                        </button>
                    </span>
                </div>
            </div>
        </div>
        <!-- END DATE TIME RANGE PICKER 1 -->
    </div>
    <div class="form-group order_assign_open_row1">
        <div class="row">
            <div class='col-md-2 order_assign_col_1'>
                <label>OSP單號:</label>
            </div>
            <div class="col-md-3 order_assign_col_2">
                <input type="text" class="form-control" name='orderMId'>
            </div>
            <div class='col-md-2 order_assign_col_3'>
                <label style="padding-left: 30px;">案件類別:</label>
            </div>
            <div class='col-md-3 order_assign_col_4'>
                <select class="form-control" name='orderTypeId'>
                    <option value=''>請選擇</option>
                </select>
            </div>
        </div>
    </div>
    
    <div class="form-group order_assign_open_row1">
        <div class="row">
            <div class='col-md-2 order_assign_col_1'>
                <label>來源單號:</label>
            </div>
            <div class="col-md-3 order_assign_col_2">
                <input type="text" class="form-control" name='sourceOrderId'>
            </div>
            <div class='col-md-2 order_assign_col_3'>
                <label style="padding-left: 30px;">進件系統:</label>
            </div>
            <div class='col-md-3 order_assign_col_4'>
                <select class="form-control" name='sourceSysId'>
                    <option value=''>請選擇</option>
                </select>
            </div>
        </div>
    </div>
    <div class="form-group order_assign_open_row2">
        <div class="row">
            <div class='col-md-2 order_assign_col_1'>
                <label>用戶名稱:</label>
            </div>
            <div class="col-md-3 order_assign_col_2">
                <input type="text" class="form-control" name='custName'>
            </div>
            <div class='col-md-2 order_assign_col_3'>
                <label>門號/代表號/線路編號:</label>
            </div>
            <div class='col-md-3 order_assign_col_4'>
                <input type="text" class="form-control" name='msisdn'>
            </div>
        </div>
    </div>
    
    <div class="form-group order_assign_open_row3">
        <div class="row">
            <div class='col-md-2 order_assign_col_1'>
                <label>證號/統編:</label>
            </div>
            <div class="col-md-3 order_assign_col_2">
                <input type="text" class="form-control" name='custId'>
            </div>
            <div class='col-md-2 order_assign_col_3'>
                <label>產品類型:</label>
            </div>
            <div class='col-md-3 order_assign_col_4'>
                <select class="form-control" name='sourceProdTypeId'>
                    <option value=''>請選擇</option>
                </select>
            </div>
        </div>
    </div>
    <div class="form-group order_assign_open_row4">
        <div class="row">
            <div class='col-md-2 order_assign_col_1'>
                <label>交易型態:</label>
            </div>
            <div class="col-md-3 order_assign_col_2">
                <select class="form-control" name='operateType'>
                    <option value=''>請選擇</option>
                </select>
            </div>
            <div class='col-md-2 order_assign_col_3'>
                <label>經銷代碼:</label>
            </div>
            <div class='col-md-3 order_assign_col_4'>
                <input type="text" class="form-control" name='ivrCode'>
            </div>
        </div>
    </div>
    <div class="form-group order_assign_open_row5">
        <div class="row">
            <div class='col-md-2 order_assign_col_1'>
                <label>業務員編:</label>
            </div>
            <div class="col-md-3 order_assign_col_2">
                <input type="text" class="form-control" name='salesId'>
            </div>
            <div class='col-md-2 order_assign_col_3'>
                <label>處理人員:</label>
            </div>
            <div class='col-md-3 order_assign_col_4'>
                <input type="text" class="form-control" name='processUserName'>
            </div>
        </div>
    </div>
    <div class="form-group order_assign_open_row6">
        <div class="row">
            <div class='col-md-2 order_assign_col_1'>
                <label style="padding-left: 24px;">案件狀態 :</label>
            </div>
            <div class="col-md-3 order_assign_col_2">
                <select class="form-control" name='orderStatus'>
                    <option value=''>請選擇</option>
                </select>
            </div>
            <div class='col-md-2 order_assign_col_3'>
            </div>
            <div class='col-md-3 order_assign_col_4'>
            </div>
        </div>
    </div>
</form>
<!-- END FORM -->

<div class="row">
    <div align="right">
        <button id='searchButton' class="btn btn-default">查詢</button>
    </div>
</div>
<hr>

<!-- BEGIN SEARCH RESULT -->
<div id='searchResult' style='display: none;'>
    <button class="btn btn-primary" style="float:right;" id="exportBtn">匯出</button>
    <table id="dataTable" class="table table-striped table-bordered table-hover">
        <thead></thead>
        <tbody></tbody>
    </table>
</div>
<!-- END SEARCH RESULT -->

<script>
    require([ '${pageContext.request.contextPath}/resources/app/module/setting/order/service-query-order-main.js' ]);
</script>