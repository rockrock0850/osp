(function() {
	define('order-type-maintain-service', 
			['flip','htmlComponet', 'osp-util', 'DT_bootstrap', 
		     'jquery.dataTables', 'datetimepicker', 'small-modal'], 
		 function(flip) {
			// 初始化DataTable 
			var handleDataTable = function(data) {
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
						{targets: [0], title: '案件類別', data: 'orderTypeName', className: 'orderTypeName'}, 
						{targets: [1], title: '有效件處理時間(秒)', data: 'successSec', className: 'successSec'}, 
						{targets: [2], title: '無效件處理時間(秒)', data: 'failSec', className: 'failSec'}, 
						{targets: [3], title: 'KPI設定_日期類別', data: 'kpiDayTypeText', className: 'kpiDayTypeText'}, 
						{targets: [4], title: '判斷進件時間', data: 'chkCreateDate', className: 'chkCreateDate'}, 
						{targets: [5], title: '進件時間', data: 'beforeCreateDate', className: 'beforeCreateDate'}, 
						{targets: [6], title: '固定處理時間', data: 'isRegularTime', className: 'isRegularTime'}, 
						{targets: [7], title: '處理時間(分)', data: 'regularTime', className: 'regularTime',defaultContent : ''}, 
						{targets: [8], title: 'KPI起算時間', data: 'startCountTime', className: 'startCountTime'}, 
						{targets: [9], title: '逾期時間(分)', data: 'overtime', className: 'overtime'},
						{
							targets: [10], 
							data: null, 
							title: "功能", 
							className: 'osp_features', 
							defaultContent: "<input id='modifyButton' class='btn btn-sm btn-default' type='button' value='編輯'>" +
									"<input id='sourceButton' class='btn btn-sm btn-default' type='button' value='進件來源'>"
						},
						{targets: [11], title: '', data: 'orderTypeId', className: 'orderTypeId hide'},
						{targets: [12], title: '', data: 'criticalCounts', className: 'criticalCounts hide'}, 
						{targets: [13], title: '', data: 'overtimeCounts', className: 'overtimeCounts hide'}, 
						{targets: [14], title: '', data: 'email', className: 'email hide'},  
						{targets: [15], title: '', data: 'kpiDayType', className: 'kpiDayType hide'}
					]
				});
			}

			// 處理Data table的按鈕事件 
			var handleTableClickListener = function() {
				var $dataTable = $('table#dataTable');
				
				$dataTable.unbind('click');
				
				// 修改案件類別設定 
				$dataTable.on('click', '#modifyButton', function() {
					// 處理click event 
					var $row = $(this).closest('tr');// closest(): 找到最接近的指定元素
					var $orderTypeId = $row.find('.orderTypeId');
					var $orderTypeName = $row.find('.orderTypeName');
					var $successSec = $row.find('.successSec');
					var $failSec = $row.find('.failSec');
					var $regularTime = $row.find('.regularTime');
					var $overtime = $row.find('.overtime');
					var $criticalCounts = $row.find('.criticalCounts');
					var $overtimeCounts = $row.find('.overtimeCounts');
					var $email = $row.find('.email');
					var $kpiDayType = $row.find('.kpiDayType');
					var $beforeCreateDate = $row.find('.beforeCreateDate');
					var $startCountTime = $row.find('.startCountTime');
					var $chkCreateDate = $row.find('.chkCreateDate');
					var $isRegularTime = $row.find('.isRegularTime');
					
					if($orderTypeId) {
						// 根據 $kpiDayType 的類型判斷該
						var dayTimeType = $kpiDayType.text() + "_TIME";
						
						// 初始化Selector
						$.getOptionBeforeCreateDate($('select[name=beforeCreateDate]'),dayTimeType,setSelectorDefaultValue);
						
						var $modifyModal = $('div#modifyModal');
						var $label = $modifyModal.find('label#orderTypeName');
						var $successSecInput = $modifyModal.find('input[name=successSec]');
						var $failSecInput = $modifyModal.find('input[name=failSec]');
						var $overtimeInput = $modifyModal.find('input[name=overtime]');
						var $criticalCountsInput = $modifyModal.find('input[name=criticalCounts]');
						var $overtimeCountsInput = $modifyModal.find('input[name=overtimeCounts]');
						var $emailInput = $modifyModal.find('input[name=email]');
						var $kpiDayTypeSelector = $modifyModal.find('select[name=kpiDayType]');
						var $chkCreateDateCheckBox = $modifyModal.find('input[name=chkCreateDate]');
						var $beforeCreateDateSelector = $modifyModal.find('select[name=beforeCreateDate]');
						var $regularTimeInput = $modifyModal.find('input[name=regularTime]');
						var $isRegularTimeCheckBox = $modifyModal.find('input[name=isRegularTime]');
						var $startCountTimeSelector = $modifyModal.find('select[name=startCountTime]');
						var $modifyModalConfirm = $modifyModal.find('button#confirmButton');
						var $hiddenKpiDayType = $("#hiddenKpiDayType");
						var $hiddenBeforeCreateDate = $("#hiddenBeforeCreateDate");
						var $hiddenStartCountTime = $("#hiddenStartCountTime");
						
						if($chkCreateDate.text() == "Y") {
							$chkCreateDateCheckBox.prop("checked",true);
						}
						if($isRegularTime.text() == "Y") {
							$isRegularTimeCheckBox.prop("checked",true);
						}
						
						$label.html($orderTypeName.text());
						$successSecInput.val($successSec.text());
						$failSecInput.val($failSec.text());
						$regularTimeInput.val($regularTime.text());
						$overtimeInput.val($overtime.text());
						$criticalCountsInput.val($criticalCounts.text());
						$overtimeCountsInput.val($overtimeCounts.text());
						$emailInput.val($email.text());
						$kpiDayTypeSelector.val($kpiDayType.text());
						$startCountTimeSelector.val($startCountTime.text());
						$hiddenBeforeCreateDate.val($beforeCreateDate.text());
						
						// 注意: 在對元件綁定事件之前, 最好先執行unbind( 解除綁定 )語法, 避免元件被重複觸發之問題 
						$modifyModalConfirm.unbind('click').on('click', function() {
							
							// 1. 有效件處理時間 (秒), 不可空白
							if($successSecInput.val() == ''|| $successSecInput.val() == null) {
								$.warningMsg('017');
								
								return false;
							}

							// 2. 無效件處理時間 (秒), 不可空白
							if($failSecInput.val() == ''|| $failSecInput.val() == null) {
								$.warningMsg('018');
								
								return false;
							}
							
							// 5. 未檢核「判斷進件時間」、「固定處理時間」至少需勾選一項
							if(!$chkCreateDateCheckBox.prop("checked") && !$isRegularTimeCheckBox.prop("checked")) {
								$.warningMsg('019');
								
								return false;
							}
							
							// 判斷是否有勾選
							if($chkCreateDateCheckBox.prop("checked")) {
								if($beforeCreateDateSelector.val() == '' || $beforeCreateDateSelector.val() == null) {
									$.warningMsg('013');
									
									return false;
								}
							}
							
							// 判斷是否有勾選
							if($isRegularTimeCheckBox.prop("checked")) {
								if($regularTimeInput.val() == '' || $regularTimeInput.val() == null ) {
									$.warningMsg('014');
									
									return false;
								}
							}

							var settings = {
								httpMethod: 'POST', 
								URL: contextPath + '/setting/order/modify-order-type.action',
								dataType: 'html',
								dataProvider: function() {
									var formData = window.form2object('modifyForm');
									formData.orderTypeId = $orderTypeId.text();
									
									return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
							    }, 
							    success: function(data) {
							    	if(data) {
								    	$modifyModal.modal('hide');
								    	$('button#searchButton').trigger('click');
								    	$.successMsg('003');
										
								    	return;
							    	}
							    	$.warningMsg('003');
							    }
							}
							$.formAjax(settings);
						});
						$modifyModal.modal('show');
					}
				});
				
				// 查詢進件來源 
				$dataTable.on('click', '#sourceButton', function() {
					// 處理click event 
					var $row = $(this).closest('tr');// closest(): 找到最接近的指定元素
					var $orderTypeId = $row.find('.orderTypeId');
					
					if($orderTypeId) {
						var $sourceSysModal = $('div#sourceSysModal');

						var settings = {
							httpMethod: 'POST', 
							URL: contextPath + '/setting/order/query-source-sys.action',
							dataType: 'html',
							dataProvider: function() {
								var formData = {};

								formData.orderTypeId = $orderTypeId.text();
								
								return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
						    }, 
						    success: function(data) {
						    	// 查詢進件來源  
						    	if(data) {
						    		$.destoryDataTable($('table#sourceSysDataTable'));
						    		handleSourceSysDataTable(data);
									$sourceSysModal.modal('show');
						    	}
						    }
						    
						}
						
						$.formAjax(settings);
					}
				});
			}
			
			// 注意: 可提出成共用方法, 處理search result div顯示與否 
			var handleDisplaySearchResult = function(data) {
				if(data == '') {
					$('#searchResult').attr('style', 'display: none;');
				}else{
					$('#searchResult').removeAttr('style');
				}
			}

			// 初始化sourceSysDataTable 
			var handleSourceSysDataTable = function(data) {
				var $dataTable = $('table#sourceSysDataTable');
				
				data = $.parseJSON(data);
				
				$.each(data, function() {
					var upperLimitCounts = this.upperLimitCounts;
					var lowerLimitCounts = this.lowerLimitCounts;
					
					this.count = upperLimitCounts + ' ~ ' + lowerLimitCounts;
				});
				
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
						{targets: [0], title: '進件來源', data: 'sourceSysId'}, 
						{targets: [1], title: '進件類別 ', data: 'sourceProdTypeName'}, 
						{targets: [2], title: '線數 (起)', data: 'upperLimitCounts'},
						{targets: [3], title: '線數 (迄)', data: 'lowerLimitCounts'}
					]
				});
			}

			// 處理Modal關閉之後需要做的事情 
			var afterModalClosed = function() {
				$.afterModalClosed($('div#modifyModal'), function() {
					//Do something...
				});
			}
			
			/* 設置Selector 的初始值(因為值會變動。所以額外寫function出來應對) only for #beforeCreateDate */
			var setSelectorDefaultValue = function() {
				var hiddenBeforeCreateDate = $("#hiddenBeforeCreateDate").val();
				$("#beforeCreateDate").val(hiddenBeforeCreateDate);
			};
			
			/*Selector 的change 事件   only for #beforeCreateDate */
			var changeKpiDateSelector = function(dayTimeType) {
				$.getOptionBeforeCreateDate($('select[name=beforeCreateDate]'),dayTimeType,setSelectorDefaultValue);
			}
		
		return {
			init: function() {
				afterModalClosed();
			}, 
			changeKpiDateSelector : changeKpiDateSelector,
			
			// 查詢案件類別資訊 
			getOrderTypeInfo: function() {
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/setting/order/get-order-type-info.action',
					dataType: 'html',
					dataProvider: function() {
						var formData = window.form2object("searchForm");
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
				    }, 
				    success: function(data) {
				    	if(data) {
				    		$.destoryDataTable($('table#dataTable'));
				    		handleDataTable(data);
				    		handleDisplaySearchResult(data);
				    		handleTableClickListener();
				    	}
						$.getOptionKpiDateResule($('select[name=kpiDayType]'));
						$.getOptionStartCountTime($('select[name=startCountTime]'));
				    }
				}
				$.formAjax(settings);
			}
		}
	});
})();