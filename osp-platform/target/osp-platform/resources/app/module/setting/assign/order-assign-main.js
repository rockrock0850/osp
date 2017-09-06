require(['osp-ui-app', 'order-assign-service', 'moment', 'flip', 
	     'common-util', 'cookie', 'form2object'], 
	function (uiApp, service, moment) {
		document.title = 'OSP | 案件指派維護作業';
		service.init();
		
		var lastYear = moment().subtract(1, 'year').format('YYYY-MM-DD hh:mm');
		$.initDateTimePicker(null, lastYear); //初始化date time picker
		
		$('button#searchButton').on('click', function() {
			service.getOrderAssignInfo();
		});
		
		$("button#assignButton").on('click', function() {
			service.assignMemeberWindow();
		})
	}
);

(function() {
	define('order-assign-service', 
			['flip', 'htmlComponet', 'moment', 'osp-util', 'DT_bootstrap', 'jquery.dataTables', 
			 'datetimepicker', 'small-modal'], 
		 function() {
			var init = function() {
				setFormPositionEvent();
				$.setDateTimePickerDefaultValue();
				$.getOptionOrderType($('select[name=orderTypeId]'));
				$.getOptionSourceSystem($('select[name=sourceSysId]'));
				$.getOptionProductType($('select[name=sourceProdTypeId]'));
				$.getOptionOperaterType($('select[name=operateType]'));
				$.getOptionInvalidOrderStatus($('select[name=orderStatus]'));
				afterModalClosed();
			}
			
			// 查詢案件指派資訊 
			var getOrderAssignInfo = function() {
				if(!$.dateValidator($('input[name=sourceCreateTimeBegin]'), $('input[name=sourceCreateTimeEnd]'))) {
					return;
				}
				if(!$.dateValidator($('input[name=expectProcessTimeBegin]'), $('input[name=expectProcessTimeEnd]'))) {
					return;
				}
				
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/setting/assign/get-order-assign-info.action', 
					dataType: 'html',
					dataProvider: function() {
						var formData = window.form2object("searchForm");
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
				    }, 
				    success: function(data) {
						$.destoryDataTable($('table#dataTable'));
				    	initOrderDataTable(data);
				    	handleDisplaySearchResult(data);
						$.setCheckBoxAllCheckedEvent($('table#dataTable #checkAll'));
				    }
				    
				}
				$.formAjax(settings);
			}
			
			// 「指派人員名單」開窗頁面 
			var assignMemeberWindow = function() {
				if($.hasChecked($('table#dataTable').find('input:checkbox'), '012')) {
					var checkedList = getCheckedList($('table#dataTable'));
					var settings = {
						httpMethod: 'POST', 
						URL: contextPath + '/setting/assign/assign-memeber-window.action', 
						dataType: 'html', 
					    success: function(data) {
							$.destoryDataTable($('table#assignDataTable'));
				    		initAssignDataTable(data);
				    		
				    		// 「查詢案件指派資訊」頁面  
				    		var assignMemberList;
				    		$(document).on('change','table#assignDataTable :input[name=radio]', function() {
				    			assignMemberList = getAssignMemberList($(this), checkedList);
					    	});
				    		
				    		var $modal = $('div#assignModal');
							var $modalConfirm = $modal.find('button#confirmButton');

							// 注意: 在對元件綁定事件之前, 最好先執行unbind( 解除綁定 )語法, 避免元件被重複觸發之問題 
							$modalConfirm.unbind('click').on('click', function() {
					    		if (!assignMemberList) {
					    			$.warningMsg('032');
					    			
					    			return;
					    		}
					    		
								var settings = {
									httpMethod: 'POST', 
									URL: contextPath + '/setting/assign/add-order-assign.action', 
									dataType: 'html',
									dataProvider: function() {
										return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(assignMemberList));
								    }, 
								    success: function(data) {
								    	if(data) {
									    	$('button#searchButton').trigger('click');
									    	$modal.modal('hide');
									    	$.successMsg('001');
											
									    	return;
								    	}
								    	$.warningMsg('001');
								    }
								}
								$.formAjax(settings);
							});
							$modal.modal('show');
					    }
					}
					$.formAjax(settings);
				}
			}
		
			/* Inner Method
			================================================================================================================= */
			var getAssignMemberList = function($this, list) {
	    		var assignMemberList = [];
	    		var $tr = $this.closest('tr');
	    		var empNo = $tr.find('td.empNo').text();
	    		var empName = $tr.find('td.empName').text();
	    		
	    		if (!empNo || !empName) {
	    			return null;
	    		}
	    		
	    		$.each(list, function() {
		    		this.processUserId = empNo;
		    		this.processUserName = empName;
		    		assignMemberList.push(this);
	    		});
	    		
	    		return assignMemberList;
			}
			
			// 注意: 可提出成共用方法, 處理search result div顯示與否 
			var handleDisplaySearchResult = function(data) {
				if(data == '') {
					$('#searchResult').attr('style', 'display: none;');
				}else{
					$('#searchResult').removeAttr('style');
				}
			}

			// 初始化DataTable 
			var initOrderDataTable = function(data) {
				var $dataTable = $('table#dataTable');
				
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
						{orderable: false, targets: [0]}, // 選擇關閉哪個欄位排序功能(  )
						{aTargets: '_all', render: function (data, type, full, mate) {
							data = $.setOrderStatusMark(data);
							data = $.setEmergencyCase(data);
							
							return data;
						}},
						{
							aTargets: [0], 
							data: null,
							title: "<input type='checkbox' id='checkAll'>", 
							className: 'osp_features', 
							defaultContent: "<input type='checkbox'>"
						},    
						{targets: [1], title: '案件狀態', data: 'ORDER_STATUS'}, 
						{targets: [2], title: 'OSP單號', data: 'ORDER_M_ID', className: 'ORDER_M_ID'}, 
						{targets: [3], title: '案件類別 ', data: 'ORDER_TYPE_NAME'}, 
						{targets: [4], title: '進件系統', data: 'SOURCE_SYS_ID'}, 
						{targets: [5], title: '產品類別', data: 'SOURCE_PROD_TYPE_NAME'}, 
						{targets: [6], title: '交易型態', data: 'OPERATE_TYPE'}, 
						{targets: [7], title: '進件時間', data: 'SOURCE_CREATE_TIME'}, 
						{targets: [8], title: '門號/代表號/線路編號', data: 'MSISDN', className: 'MSISDN'}, 
						{targets: [9], title: '用戶名稱', data: 'CUST_NAME'}, 
						{targets: [10], title: '來源單號', data: 'SOURCE_ORDER_ID'}, 
						{targets: [11], title: '母子單', data: 'PARTENT_ORDER_ID'}, 
						{targets: [12], title: '筆數', data: 'COUNTS'}, 
						{targets: [13], title: '預計作業處理時間', data: 'EXPECT_PROCESS_TIME'}, 
						{targets: [14], title: '預計完成時間', data: 'EXPECT_COMPLETE_TIME'}, 
						{targets: [15], title: '客戶指定生效日', data: 'CUST_SPECIFY_DATE'}, 
						{targets: [16], title: '處理人員', data: 'PROCESS_USER_NAME', className: 'PROCESS_USER_NAME'},
						{targets: [17], title: '', data: 'PROCESS_RESULT', className: 'PROCESS_RESULT hide'}, 
						{targets: [18], title: '', data: 'PROCESS_REASON', className: 'PROCESS_REASON hide'}, 
						{targets: [19], title: '', data: 'LIGHT_COLOR', className: 'LOGHT_COLOR hide'}
					]
				});
			}
			
			// 初始化assignDataTable 
			var initAssignDataTable = function(data) {
				var $table = $('table#assignDataTable');
				
				data = $.parseJSON(data);
				$table.DataTable({
					dom: '<"row"lf><"row"tr><"row"ip>',
					paging: true, // 翻頁功能
					lengthChange: true, // 改變每頁顯示數據數量
					pageLength: 100, // 顯示10筆換下一頁
					searching: true, // 過濾功能
					ordering: true, // 排序功能
					info: true, // 頁腳信息
					autoWidth: false, // 自動寬度
					data: data, // 注入資料
					columnDefs: [ // 欄位設定
						{searchable: false, targets: [6]}, // 選擇關閉哪個欄位排序功能(  )
						{
							targets: [0], 
							data: null, 
							className: 'osp_features', 
							defaultContent: "<input name='radio' type='radio'>"
						},  
						{targets: [1], title: '人員代號', data: 'empNo', className: 'empNo'}, 
						{targets: [2], title: 'NT帳號 ', data: 'ntAccount', className: 'ntAccount'}, 
						{targets: [3], title: '中文姓名', data: 'empName', className: 'empName'}, 
						{targets: [4], title: '英文姓名', data: 'engName', className: 'engName'}, 
						{targets: [5], title: '部門', data: 'deptName', className: 'deptName'},
						{targets: [6], title: '', data: 'deptCode', className: 'deptCode hide'}
					]
				});
			}
			
			// 取得data table內所有勾選的checkbox 
			var getCheckedList = function($table) {
				var $checkboxs = $table.find('input:checkbox');
				var checkedArray = [];
				var data = [];
				
				$.each($checkboxs, function() {
					var $this = $(this);
					
					if($this.is(':checked') && !$this.is('#checkAll')) {
						checkedArray.push($this);
					}
				});
				
				$.each(checkedArray, function() {
					var $tr = $(this).closest('tr');
					var order = {};
					
					order.orderMId = $tr.find('td.ORDER_M_ID').text();
					order.processResult = $tr.find('td.PROCESS_RESULT').text();
					order.problemReason = $tr.find('td.PROCESS_REASON').text();
					order.msisdn = $tr.find('td.MSISDN').text();
					data.push(order);
				});
				
				return data;
			}

			// 處理Modal關閉之後需要做的事情 
			var afterModalClosed = function() {
				// 轉派 
				$.afterModalClosed($('div#assignModal'), function() {
					$.destoryDataTable($('table#assignDataTable'));
				});
			}
			
			// 控制左邊menu [ 開闔 ] 的時候內容「表單」的排版
			var setFormPositionEvent = function() {
				$('.page-sidebar').click(function() {
					if(!$('body').hasClass('page-sidebar-closed')) {
						$.setMenuOpen($('form#searchForm'), 'order_assign');
					}else{
						$.setMenuClosed($('form#searchForm'), 'order_assign');
					}
				});
			}
			
			return {
				init: init, 
				
				// 查詢案件指派資訊 
				getOrderAssignInfo: getOrderAssignInfo,
				
				// 「指派人員名單」開窗頁面 
				assignMemeberWindow: assignMemeberWindow
			}
		}
	);
})();