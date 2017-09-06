require(['service', 
	     'osp-util', 'common-util', 'cookie', 'form2object', 
         'jquery.dataTables', 'DT_bootstrap', 'flip'], 
	function (service) {
		document.title = 'OSP | 我的待處理';
		service.init();

		// 查詢
		$('button#searchButton').unbind('click').on('click', function() {
			service.setResultTable();
		});
		
		// 取得案件清單
		$('button#getCaseListButton').unbind('click').on('click', function() {
			var settings = {
				httpMethod: 'POST', 
				URL: contextPath + '/workshop/personal/get-oder-user-info-by-user-id.action',
				dataType: 'html',
				dataProvider: function() {}, 
			    success: function(data) {
			    	if(data) {
			    		service.setCaseListContent(data);
		    		}
			    }
			}
			$.formAjax(settings);
		});
		
	}
);

(function() {
	// 注意: 如果定義的module跟require在同一支檔案下的話, 則不用特別在require config裡面註冊名稱及檔案路徑
	define('service', ['flow-main-service', 'flip', 'htmlComponet', 'osp-util'], 
		function(flowMainService) {
			var init = function() {
				$.getOptionOrderType($('select[name=orderTypeId]'));
				$.getOptionProductType($('select[name=sourceProdTypeId]'));
				setFormPositionEvent();
				setResultTable();
				$.isRefreshContent(showCaseContent);// 判斷是否要重新進入案件內容
				afterModalClosed();
			};
			
			// 
			var setCaseListContent = function(data) {
				var $modal = $('div#orderSimpleModal');
	    		var $table = $modal.find('table');
	    		var $tbody = $modal.find('table tbody');
	    		var content;
	    		
	    		data = $.parseJSON(data);
	    		$.each(data, function() {
	    			content += '<tr>';
	    			content += '<td>' + this.ORDER_TYPE_NAME + '</td>';
	    			content += '<td>' + this.ORDER_TYPE_SUM + '</td>';
	    			content += '<td>' + this.CRITICAL_COUNT + '</td>';
	    			content += '<td>' + this.OVERDUE_CUNT + '</td>';
	    			content += '</tr>';
	    		});
    			$tbody.append(content);
    			
    			$table.attr('style', 'border: 1px solid black;');
    			$table.attr('align', 'center');
    			$table.find('th,td').attr('style', 'border: 1px solid black;');
	    		$modal.modal('show');
			}
			
			/* Inner Method
			========================================================================================================================= */
			// 顯示案件內容
			var showCaseContent = function (data, orderMId) {
	    		$('div#info').hide();
	    		$('div#caseContent').empty().html(data).show();
	    		
	    		flowMainService.init();
	    		$.popOrderImageWindow(orderMId);
			}
			
			// 控制左邊menu [ 開闔 ] 的時候內容「表單」的排版
			var setFormPositionEvent = function() {
				$('.page-sidebar').click(function() {
					if(!$('body').hasClass('page-sidebar-closed')) {
						$.setMenuOpen($('form#searchForm'), 'personal_todo');
					}else{
						$.setMenuClosed($('form#searchForm'), 'personal_todo');
					}
				});
			};

			// 設定Data Table內容
			var setResultTable = function() {
				var formData = window.form2object("searchForm");
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/workshop/personal/get-personal-todo-info.action',
					dataType: 'html',
					dataProvider: function() {
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
				    }, 
				    success: function(data) {
				    	if(data) {
				    		data = $.parseJSON(data);
				    		$.destoryDataTable($('div#searchResult').find('table#resultTable'));
				    		initTableContent(data.voList);
				    		setCasesValue(data);
				    		setTableButtonClickEvent();
				    	}
				    }
				}
				$.formAjax(settings);
			};

			// 設定「全部案件」, 「急件等欄位」
			var setCasesValue = function(data) {
				var $sum = $('label#sum');
				var $critical = $('label#critical');
				var $overdue = $('label#overdue');
				
				$sum.html(data.voList.length);
				$critical.html(data.criticalCount);
				$overdue.html(data.overdueCount);
			};

			// 初始化DataTable
			var initTableContent = function(data) {
				var $table = $('table#resultTable');
				
				$table.DataTable({
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
						{
							targets: [0], 
							data: null, 
							className: 'osp_features', 
							defaultContent: "<button id='contentButton'><span class='fa fa-pencil'></span></button>"
						},
						{targets: [1], title: '案件狀態', data: 'ORDER_STATUS', className: 'ORDER_STATUS'}, 
						{targets: [2], title: 'OSP單號', data: 'ORDER_M_ID', className: 'ORDER_M_ID'}, 
						{targets: [3], title: '案件類別 ', data: 'ORDER_TYPE_NAME', className: 'ORDER_TYPE_NAME'}, 
						{targets: [4], title: '進件系統', data: 'SOURCE_SYS_ID', className: 'SOURCE_SYS_ID'}, 
						{targets: [5], title: '產品類別', data: 'SOURCE_PROD_TYPE_NAME', className: 'SOURCE_PROD_TYPE_NAME'}, 
						{targets: [6], title: '交易型態', data: 'OPERATE_TYPE', className: 'OPERATE_TYPE'}, 
						{targets: [7], title: '進件時間', data: 'OSP_CREATE_TIME', className: 'OSP_CREATE_TIME'}, 
						{targets: [8], title: '門號/代表號/線路編號', data: 'MSISDN', className: 'MSISDN'}, 
						{targets: [9], title: '用戶名稱', data: 'CUST_NAME', className: 'CUST_NAME'}, 
						{targets: [10], title: '來源單號', data: 'SOURCE_ORDER_ID', className: 'SOURCE_ORDER_ID'}, 
						{targets: [11], title: '母子單', data: 'PARTENT_ORDER_ID', className: 'PARTENT_ORDER_ID'}, 
						{targets: [12], title: '筆數', data: 'COUNTS', className: 'COUNTS'}, 
						{targets: [13], title: '預計作業處理時間', data: 'EXPECT_PROCESS_TIME', className: 'EXPECT_PROCESS_TIME'}, 
						{targets: [14], title: '預計完成時間', data: 'EXPECT_COMPLETE_TIME', className: 'EXPECT_COMPLETE_TIME'}, 
						{targets: [15], title: '客戶指定生效日', data: 'CUST_SPECIFY_DATE', className: 'CUST_SPECIFY_DATE'}, 
						{targets: [16], title: '處理人員', data: 'PROCESS_USER_NAME', className: 'PROCESS_USER_NAME'},
						{targets: [17], title: '', data: 'FLOW_ID', className: 'FLOW_ID hide'}, 
						{targets: [18], title: '', data: 'ORDER_TYPE_ID', className: 'ORDER_TYPE_ID hide'},
						{targets: [19], title: '', data: 'OVERDUE_FLAG', className: 'OVERDUE_FLAG hide'},
						{targets: [20], title: '', data: 'CRITICAL_FLAG', className: 'CRITICAL_FLAG hide'},
						{targets: [21], title: '', data: 'LIGHT_COLOR', className: 'LIGHT_COLOR hide'}
					]
				});
			};
			
			// 處理Data table的按鈕事件
			var setTableButtonClickEvent = function() {
				var $table = $('table#resultTable');
				
				// 修改案件類別設定
				$table.unbind('click').on('click', '#contentButton', function() {
					// 處理click event
					var $row = $(this).closest('tr');// closest(): 找到最接近的指定元素
					var orderMId = $row.find('.ORDER_M_ID').text();
					
					if(orderMId) {
						ajaxGetBuzOrderFlow(orderMId);
					}
				});
			};
			
			var ajaxGetBuzOrderFlow = function(orderMId) {
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/flow/get-buz-order-flow.action?orderMId=' + orderMId,
					dataType: 'html',
					dataProvider: function() {}, 
				    success: function(data) {
				    	if(data) {
				    		showCaseContent(data, orderMId);
				    	}
				    }
				}
				$.formAjax(settings);
			}

			// 處理Modal關閉之後需要做的事情
			var afterModalClosed = function() {
				// 案件列表
				$.afterModalClosed($('div#orderSimpleModal'), function() {
					$(this).find('table tbody').empty();
				});
			};
			
			return {
				init: init,
				setResultTable: setResultTable,
				setCaseListContent: setCaseListContent
			}
		}
	);
})()

// 顯示訊息
var showMessage = function() {
	$.showMessage();
}

// 有效件
var success = function() {
	$.success();
}

// 無效件
var fail = function() {
	$.fail();
}

// 更改案件類別
var changeOrderType = function() {
	$.changeOrderType();
}

// 開啟影像檔工單
var showSourceDocument = function() {
	$.showSourceDocument();
}

// 暫離
var tempLeave = function() {
	$.tempLeave();
}

// 待系統回覆
var reply = function() {
	$.reply();
}

// 暫存
var tempSave = function() {
	$.tempSave();
}