require(['osp-ui-app', 'query-order-status-service', 'moment', 'flip', 
	    'common-util', 'datetimepicker', 'cookie', 'form2object'], 
	function (uiApp, service, moment) {
		document.title = 'OSP | 案件狀態查詢';
		service.init();
		
		var minDate = moment().subtract(1, 'year').format('YYYY-MM-DD hh:mm');
		var maxDate = moment().subtract(0, 'month').format('YYYY-MM-DD hh:mm');
		$.initDateTimePicker(null, minDate,maxDate); //初始化date time picker
		
		$("div #searchButton").click(service.getOrderTypeInfo);
		
		$("div #exportBtn").click(service.exportCSVFile);
		
		$("#sourceCreateTimeBegin").on("change",function() {
		})
	}
);

(function() {
	define('query-order-status-service', 
			['jquery','htmlComponet', 'small-modal', 'osp-util', 'DT_bootstrap', 'jquery.dataTables', 'datepicker', 
			 'datepickerLg', 'datetimepicker', 'bootstrap', 'jqueryUi', 'jqueryMultifile'], 
		 function($) {
		
		//暫存已經透過後端取得到的清單
		var dataList = null;
		
		/* 查詢案件類別資訊 */
		var getOrderTypeInfo = function() {
			if(!$.dateValidator($('input[name=sourceCreateTimeBegin]'), $('input[name=sourceCreateTimeEnd]'))) {
				return;
			}
			
			var settings = {
				httpMethod: 'POST', 
				URL: contextPath + '/setting/order/get-order-status-info.action',
				dataType: 'html',
				dataProvider: function() {
					var formData = window.form2object("searchForm");
					
					return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
			    }, 
			    success: function(data) {
			    	if(data) {
			    		$.destoryDataTable($('table#dataTable'));
			    		handleDataTable(data);
			    		$('div#searchResult').show();
			    	}
			    }
			    
			}
			
			$.formAjax(settings);
		}
			/* 初始化DataTable */
			var handleDataTable = function(data) {
				var $dataTable = $('#dataTable');
				
				//將後端取得到的結果放入dataList中
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
						{targets: '_all', render: function (data, type, full, mate) {
							data = $.setOrderStatusMark(data);
							data = $.setEmergencyCase(data);
							
							return data;
						}},
						{targets: [0], title: '案件狀態', data: 'ORDER_STATUS'}, 
						{targets: [1], title: '來源進件時間', data: 'SOURCE_CREATE_TIME'},
						{targets: [2], title: 'OSP單號', data: 'ORDER_M_ID'}, 
						{targets: [3], title: '案件類別', data: 'ORDER_TYPE_NAME'},  
						{targets: [4], title: '來源單號', data: 'SOURCE_ORDER_ID'}, 
						{targets: [5], title: '筆數', data: 'COUNTS'}, 
						{targets: [6], title: '母子單', data: 'PARTENT_ORDER_ID',defaultContent : '' }, 
						{targets: [7], title: '進件系統', data: 'SOURCE_SYS_ID',defaultContent : '' }, 
						{targets: [8], title: '產品類別', data: 'SOURCE_PROD_TYPE_NAME',defaultContent : '' },
						{targets: [9], title: '門號/代表號/線路編號', data: 'MSISDN',defaultContent : '' },
						{targets: [10], title: '用戶名稱', data: 'CUST_NAME',defaultContent : '' },
						{targets: [11], title: '證號/統編', data: 'CUST_ID',defaultContent : '' },
						{targets: [12], title: '交易型態', data: 'OPERATE_TYPE',defaultContent : '' },
						{targets: [13], title: '經銷代碼', data: 'IVR_CODE',defaultContent : '' },
						{targets: [14], title: '業務員編', data: 'SALES_ID'},
						{targets: [15], title: '處理人員', data: 'PROCESS_USER_ID',defaultContent : '' },
						{targets: [16], title: '處理原因', data: 'PROCESS_REASON',defaultContent : '' },
						{targets: [17], title: '備註', data: 'COMMMENT',defaultContent : '' },
						{targets: [18], title: '客戶指定生效日', data: 'CUST_SPECIFY_DATE',defaultContent : '' },
						{targets: [19], title: '授權原因', data: 'ITEM_NAME',defaultContent : '' },
						{targets: [20], title: '促銷代號', data: 'PROMOTION_ID',defaultContent : '' },
						{targets: [21], title: '結案日期', data: 'UPDATE_DATE',defaultContent : '' }
					]
				});
			}
			
			//將查詢結果以CSV檔案的方式匯出
			var exportCSVFile = function() {
					if(dataList == null) {
						alert("必須有查詢結果之後才可匯出成CSV檔");

						return false;
					}
					
					var settings = {
						httpMethod: 'POST', 
						URL: contextPath + '/setting/order//export-csv-file.action',
						dataType: 'html',
						dataProvider: function() {
							//注意.dataList為Json String,直接傳入後端即可
							return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(dataList);
					    }, 
					    success: function(data) {
					    	var data = JSON.parse(data);
					    	var fileName = data.fileName;
					    	var filePath = data.filePath;

					    	//AJax Success後.再透過window.location 執行該URL後.才可透過AJAX下載檔案
					    	window.location = contextPath + '/setting/order/excute-csv-file-download.action?filePath=' + filePath + '&fileName=' + fileName;
					    }
					}
					
					$.formAjax(settings);
			}


		return {
			getOrderTypeInfo : getOrderTypeInfo,
			exportCSVFile : exportCSVFile,
			
			init: function() {
				$.setDateTimePickerDefaultValue();
				$.getOptionOrderType($('select[name=orderTypeId]'));
				$.getOptionSourceSystem($('select[name=sourceSysId]'));
				$.getOptionProductType($('select[name=sourceProdTypeId]'));
				$.getOptionOperaterType($('select[name=operateType]'));
				$.getOptionOrderStatus($('select[name=orderStatus]'));
				
			}

		}
	});
})();