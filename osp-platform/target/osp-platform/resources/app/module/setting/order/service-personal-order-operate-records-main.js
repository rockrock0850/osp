require([ 'osp-ui-app', 'personal-order-operate-records-service', 'moment', 'flip', 'common-util',  
          'datetimepicker', 'cookie', 'form2object', 'small-modal'], 
		function(uiApp, service, moment) {
			document.title = 'OSP | 個人產能狀況查詢';
			service.init();
		
			var minDate = moment().subtract(1, 'year').format('YYYY-MM-DD hh:mm');
			var maxDate = moment().subtract(0, 'month').format('YYYY-MM-DD hh:mm');
		
			$.initDateTimePicker(null, minDate, maxDate); // 初始化date time picker
		
			$("div #searchBtn").click(service.getOrderOperateRecordsInfo);
		//	$("div #exportBtn").click(service.exportCSVFile);
		
			// TODO 需要進行一次 Default 查詢
		});

(function() {
	define('personal-order-operate-records-service',
			['jquery', 'htmlComponet', 'osp-util', 'DT_bootstrap', 'jquery.dataTables', 'datepicker', 'datepickerLg', 'datetimepicker', 
			 'bootstrap', 'jqueryUi', 'jqueryMultifile' ],
			function($) {
				// 暫存已經透過後端取得到的清單
				var dataList = null;

				/* 查詢案件類別資訊 */
				var getOrderOperateRecordsInfo = function() {
					if(!$.dateValidator($('input[name=queryBeginDate]'), $('input[name=queryEndDate]'))) {
						return;
					}

					var settings = {
						httpMethod : 'POST',
						URL : contextPath + '/get-personal-order-operate-records-info.action',
						dataType : 'html',
						dataProvider : function() {
							var formData = window.form2object("searchForm");

							return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
						},
						success : function(data) {
							if (data) {
								destoryDataTable($('table#dataTable'));
								handleDataTable(data);
							}
						}
					}
					$.formAjax(settings);
				}
				
				/* 初始化DataTable */
				var handleDataTable = function(data) {
					var $dataTable = $('#dataTable');

					// 將後端取得到的結果放入dataList中
					dataList = data;

					data = $.parseJSON(data);

					$dataTable.DataTable({
						dom: '<"row"lf><"row"tr><"row"ip>',
						paging: true, // 翻頁功能
						lengthChange: true, // 改變每頁顯示數據數量
						pageLength: 100, // 顯示10筆換下一頁
						searching: false, // 過濾功能
						ordering: true, // 排序功能
						info: true, // 頁腳信息
						autoWidth: false, // 自動寬度
						data: data, // 注入資料
						columnDefs: [ // 欄位設定
							{orderable: false, targets: []}, // 選擇關閉哪個欄位排序功能(  )
							{targets : [0],title: '處理人員',data : 'id'},
							{targets : [1],title: '日期',data : 'id'},
							{targets : [2],title: '實際完成產能',data : 'id'},
							{targets : [3],title: '個人待處理預計完成產能',data : 'id'},
							{targets : [4],title: '前二者加總產能',data : 'id'},
							{targets : [5],title: '當日已派件的預計處理',data : 'id'} 
						]
					});
				}

				/*
				 * 注意: 可提出成共用方法, 有時間要把前幾支的destroy方法改成這支 清除data table
				 */
				var destoryDataTable = function($table) {
					var $tbody = $table.find('tbody');

					if ($tbody.has('tr').length) {
						$table.dataTable().fnDestroy();
					}
				}

				// 將查詢結果以CSV檔案的方式匯出
				var exportCSVFile = function() {
					if(!$.dateValidator($('input[name=queryBeginDate]'), $('input[name=queryEndDate]'))) {
						return;
					}
					
					if (dataList == null) {
						alert("必須有查詢結果之後才可匯出成CSV檔");

						return false;
					}

					var settings = {
						httpMethod : 'POST',
						URL : contextPath + '/export-operate-record-csv.action',
						dataType : 'html',
						dataProvider : function() {
							// 注意.dataList為Json String,直接傳入後端即可
							return REQ_PARAM_JSON_DATA + '='
									+ encodeURIComponent(dataList);
						},
						success : function(data) {
							var data = JSON.parse(data);
							var fileName = data.fileName;
							var filePath = data.filePath;

							// AJax Success後.再透過window.location
							// 執行該URL後.才可透過AJAX下載檔案
							window.location = contextPath + '/setting/order/excute-csv-file-download.action?filePath=' + filePath + '&fileName=' + fileName;
						}
					}
					$.formAjax(settings);
				}

				return {
					getOrderOperateRecordsInfo : getOrderOperateRecordsInfo,
					exportCSVFile : exportCSVFile,

					init : function() {
						$.setDateTimePickerDefaultValue();
						$.getOptionOrderType($('select[name=orderTypeId]'));
						$.getOptionSourceSystem($('select[name=sourceSysId]'));
						$.getOptionProductType($('select[name=sourceProdTypeId]'));
						$.getOptionOperaterType($('select[name=operateType]'));
						$.getOptionOrderStatus($('select[name=orderStatus]'));

						// Default 查詢
						//getOrderOperateRecordsInfo();

					}

				}
			});
})();