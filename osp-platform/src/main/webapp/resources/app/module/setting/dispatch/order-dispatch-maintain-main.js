require(['order-dispatch-maintain-service', 'osp-ui-app', 'flip', 'common-util', 
	    'datetimepicker', 'cookie', 'form2object', 'moment'], 
	function (service) {
		document.title = 'OSP | 暫停人員分派維護作業';
		
		var $picker1 = $('form#searchForm').find('.date-datetimepicker');
		var $picker2 = $('form#searchFormDialog').find('.date-datetimepicker');
		var lastYear = moment().subtract(1, 'year').format('YYYY-MM-DD hh:mm');
		
		$.initDateTimePicker($picker1, lastYear); // 初始化date time picker
		$.initDateTimePicker($picker2); // 初始化date time picker
		service.init();
		
		$("input#searchButton").on('click', function() {
			service.getOrderDispatchInfo();
		});
		
		$('input#stopAssignButton').on('click', function() {
			service.orderDispatchWindow();
		});

		$('input#searchButton').trigger('click');
	}
);

(function() {
	define('order-dispatch-maintain-service', 
			['flip', 'osp-util', 'DT_bootstrap', 'jquery.dataTables', 'datetimepicker', 'small-modal'], 
	 	function() {
			var init = function() {
				setFormPositionEvent();
				$.setDateTimePickerDefaultValue();
				afterModalClosed();
			}
			
			// 查詢暫停人員分派維護作業
			var getOrderDispatchInfo = function() {
				if(!$.dateValidator($('input[name=pauseStartTime]'), $('input[name=pauseEndTime]'))) {
					return;
				}
				
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/setting/dispatch/get-order-dispatch-info.action', 
					dataType: 'html',
					dataProvider: function() {
						var formData = window.form2object("searchForm");
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
				    }, 
				    success: function(data) {
				    	$.destoryDataTable($('table#dataTable'));
				    	initDataTable(data);
				    	handleDisplaySearchResult(data);
				    }
				}
				$.formAjax(settings);
			}
			
			// 「查詢暫停人員分派維護作業」開窗頁面 
			var orderDispatchWindow = function() {
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/setting/dispatch/order-dispatch-window.action', 
					dataType: 'html',
					dataProvider: function() {}, 
				    success: function(data) {
			    		initStopAssignTable(data);
			    		$.setCheckBoxAllCheckedEvent($('div#stopAssignModal #checkAll'));
			    		openOrderDispatchWindow();
				    }
				}
				$.formAjax(settings);
			}
		
			/* Inner Method
			================================================================================= */
			var openOrderDispatchWindow = function() {
	    		// 「查詢暫停人員分派維護作業」頁面
	    		var $modal = $('div#stopAssignModal');
	    		var $confirmButton = $modal.find('button#confirmButton');

				// 注意: 在對元件綁定事件之前, 最好先執行unbind( 解除綁定 )語法, 避免元件被重複觸發之問題
	    		$confirmButton.unbind('click').on('click', function() {
	    			// 檢核起迄時間不得錯置
					if($.dateValidatorWithoutRange($('div#stopAssignModal input[name=pauseStartTime]'), $('div#stopAssignModal input[name=pauseEndTime]'))) {
			    		if($.hasChecked($('div#stopAssignModal table').find('input:checkbox'), '032') && 
			    				hasTypeDate($('div#stopAssignModal input[name=pauseStartTime]'))) {
			    			sendCheckedMembers($modal);
			    		}
					}	
	    		});
	    		$modal.modal('show');
			}

			// 注意: 可提出成共用方法, 驗證是否已經輸入某個日期欄位
			var hasTypeDate = function($input) {
				if($input.val() == '') {
					$.warningMsg('010');
					
					return false;
				}
				
				return true
			}
			
			var sendCheckedMembers = function($modal) {
	    		var checkedList = getCheckedList($('table#stopAssignDataTable'));
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/setting/dispatch/add-order-dispatch.action', 
					dataType: 'html',
					dataProvider: function() {
						var formData = window.form2object("searchFormDialog");
						
						$.each(checkedList, function() {
							this.pauseStartTime = formData.pauseStartTime;
							this.pauseEndTime = formData.pauseEndTime;
						});
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(checkedList));
				    }, 
				    success: function(data) {
				    	if(data) {
					    	$modal.modal('hide');
					    	$.successMsg('002');
					    	
					    	return;
				    	}
				    	$.warningMsg('002');
				    }
				}
				$.formAjax(settings);
			}
			
			// 初始化DataTable
			var initDataTable = function(data) {
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
						{orderable: false, targets: []}, // 選擇關閉哪個欄位排序功能(  ) 
						{targets: [0], title: '人員姓名', data: 'empName', className: 'empName'}, 
						{targets: [1], title: '暫停分派時間起 ', data: 'pauseStartTime', className: 'pauseStartTime'}, 
						{targets: [2], title: '暫停分派時間迄', data: 'pauseEndTime', className: 'pauseEndTime'},
						{targets: [3], title: '', data: 'empNo', className: 'empNo hide'}
					]
				});
			}
	
			// 初始化stopAssignDataTable
			var initStopAssignTable = function(data) {
				var $dataTable = $('table#stopAssignDataTable');
				
				data = $.parseJSON(data);
				$dataTable.DataTable({
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
						{orderable: false, targets: [0]}, // 選擇關閉哪個欄位排序功能(  )
						{
							aTargets: [0], 
							data: null, 
							title: "<center><input type='checkbox' id='checkAll'></center>", 
							className: 'osp_features', 
							defaultContent: "<input type='checkbox'>"
						}, 
						{targets: [1], title: '', data: 'deptCode', className: 'deptCode hide'}, 
						{targets: [2], title: '人員代號', data: 'empNo', className: 'empNo'}, 
						{targets: [3], title: 'NT帳號 ', data: 'ntAccount', className: 'ntAccount'}, 
						{targets: [4], title: '中文姓名', data: 'empName', className: 'empName'}, 
						{targets: [5], title: '英文姓名', data: 'engName', className: 'engName'}, 
						{targets: [6], title: '部門', data: 'deptName', className: 'deptName'}
					]
				});
				
				var $searchResult = $('div#stopAssignModal');
				
				// $=: 尋找xxx屬性內容以xxx結尾之元素
				$searchResult.find("[class$=_length] label").addClass("pull-left");// 將dataTable顯示筆數選單靠左
				$searchResult.find('[class$=_paginate]').attr('align', 'right');// 將dataTable分頁靠右
			}
			
			// 注意: 可提出成共用方法, 處理search result div顯示與否
			var handleDisplaySearchResult = function(data) {
				if(data == '') {
					$('#searchResult').attr('style', 'display: none;');
				}else{
					$('#searchResult').removeAttr('style');
				}
			}
	
			//  取得data table內所有勾選的checkbox
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
					var emp = {};
					
					emp.empNo = $tr.find('td.empNo').text();
					emp.empName = $tr.find('td.empName').text();
					data.push(emp);
				});
				
				return data;
			}

			// 處理Modal關閉之後需要做的事情
			var afterModalClosed = function() {
				$.afterModalClosed($('div#stopAssignModal'), function() {
			    	var $input = $('div#stopAssignModal').find('.row input');
			    	$.destoryDataTable($('div#stopAssignModal table'));
			    	$input.val('');
				});
			}

			// 控制左邊menu [ 開闔 ] 的時候內容「表單」的排版
			var setFormPositionEvent = function() {
				$('.page-sidebar').click(function() {
					if(!$('body').hasClass('page-sidebar-closed')) {
						$.setMenuOpen($('form#searchForm'), 'order_dispatch');
					}else{
						$.setMenuClosed($('form#searchForm'), 'order_dispatch');
					}
				});
			}
			
			return {
				init: init, 
				
				getOrderDispatchInfo: getOrderDispatchInfo, 
				
				orderDispatchWindow: orderDispatchWindow
			}
		}
	);
})();
