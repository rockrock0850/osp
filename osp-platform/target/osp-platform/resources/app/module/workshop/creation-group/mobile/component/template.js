require(['service', 'flip', 'DT_bootstrap', 'jquery.dataTables'], 
	function (service) {
		service.init();
		
		$('button#creationButton').unbind('click').click(function() {
			$('div#info').hide();
			$('div#creationFile').show();
		});
		
		$('button#contentButton').unbind('click').click(function() {
			service.createOrder('content');
		});
		
		$('button#workFlowButton').unbind('click').click(function() {
			service.createOrder('workFlow');
		});
		
		$('button#notifyOtherSalesButton').unbind('click').click(function() {
			service.ajaxGetMsisdnList();
			$('div#info').hide();
			$('div#notifyOtherSales').show();
		});
		
		$("input[name='file']").on('change', function(e) {
			service.chooseFile();
		});
		
		$("#uploadButton").click(function() {
			service.uploadFile();
		});
		
		$('button#batchCreationButton').unbind('click').click(function() {
			$('div#info').hide();
			$('div#batchCreationFile').show();
		});
		
		$('button#searchMsisdnButton').unbind('click').click(function() {
			service.ajaxGetMsisdnList();
		});
		
		$('button#sendEMailButton').unbind('click').click(function() {
			var settings = {
				httpMethod: 'POST', 
				URL: contextPath + '/workshop/creation-group/get-duplication.action',
				dataType: 'html', 
			    success: function(data) {
			    	if(data) {
			    		data = JSON.parse(data);
			    		service.setSendEmailClickEvent(data);
			    	}
			    }
			}
			$.formAjax(settings);
		});
	}
);

(function() {
	define('service', ['jquery', 'flow-main-service', 'htmlComponet', 'small-modal'], 
		function($, flowMainService) {
			var init = function() {
				var jsonData = JSON.parse($("textarea#responseJson").text());
				var title = jsonData.title;
				var orderTypeId = jsonData.orderTypeId;
				
				document.title = 'OSP | ' + title;
				$('div#info').attr('orderTypeId', orderTypeId);
				$.getCreationOptionSourceSystem($('select[name=sourceSysId]'));
				$.getOptionCustType($('select[name=custType]'), function () {
					$('select[name=custType]').val('NBT04');// 預設選擇
				});
				$.getOptionComboType($('select[name=comboValue]'));
				
				var orderTypeVO = jsonData.orderTypeVO;
				
				setCreationButtons(orderTypeVO);
				initResultTable(jsonData);
				setResultTableButtonClickEvent();
				setFormPositionEvent();
				afterModalClosed();
			}

			// 處理Modal關閉之後需要做的事情 
			var afterModalClosed = function() {
				// 寄送EMail
				$.afterModalClosed($('div#sendEmailModal'), function() {
					$('select[name=comboValue]').val('');
					$("table input:checkbox").prop("checked", false);
				});
			}
			
			var ajaxGetMsisdnList = function() {
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/workshop/creation-group/get-msisdn-list.action',
					dataType: 'html',
					dataProvider: function() {
						var formData = window.form2object('notifyOtherSales');
						var orderTypeId = $('div#info').attr('orderTypeId');
						
						var msisdn = formData.msisdn;
						if ($.validateCardNumbers(msisdn, 10)) {
							$.warningMsg('027');
							
							return null;
						}
						
						formData.orderTypeId = orderTypeId;
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
				    }, 
				    success: function(data) {
				    	if(data) {
				    		data = JSON.parse(data);
				    		getNotifyOtherSalesResult(data);
				    	}
				    }
				}
				$.formAjax(settings);
			}

			// 選擇檔案
			var chooseFile = function() {
				// Excel xls & xlsx Type
				var xlsxType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
				var xlsType = "application/vnd.ms-excel";
				var $fileName = $('span#fileName');
				var $input = $('input[name=file]');
				var $uploadButton = $("button#uploadButton");
				var $chooseButton = $('span[class$=btn-file]');
				
				$fileName.text("");
				try {
					// 注意: 不知道為什麼input一定要加上[0]才能選到檔案清單物件
					var name = $input[0].files[0].name;
			    	var type = $input[0].files[0].type;
			    	
					if(type != xlsType && type != xlsxType) {
						throw $.getErrorMessage('008');
					}
					
					$fileName.text(name);
					$chooseButton.hide();
					$uploadButton.show();
				} catch(error) {
					// 注意: 當選擇檔案時, 若按下取消鍵, name會拋exception, 利用此關係來觸發上傳按鈕的顯示與否 by Andrew
					$chooseButton.show();
					$uploadButton.hide();
					
					if (error == "Cannot read property 'name' of undefined") {
						return;
					}
					
					var content = '<center><label>' + error + '</label></center>';
					var buttons = {
						cancelButton: {
							value: '確認'
						}
					}
					$.messageModal('警告', content, buttons);
				}
			}
			
			// 上傳檔案
			var uploadFile = function() {
				var files = $('input[name=file]')[0].files;
				var orderTypeId = $('div#info').attr('orderTypeId');
			    var formData = new FormData();
			    
			    $.each(files, function(i, file) {
				    formData.append('file', file);
			    });
				formData.orderTypeId = orderTypeId;
			    formData.append('jsonData', '{orderTypeId:"' + orderTypeId + '"}');
				
			    if(!$.isEmptyObject(formData)) {
				    $.ajax({
				        url: contextPath + '/workshop/creation-group/batch-creation-file.action',
				        type: 'POST',
				        data: formData,
			            cache: false,
			            async: false,
				        contentType: false,
				        processData: false,
				        success: function () {
				        	$.successMsg('006');
			    			location.reload();
				        }, 
				        error: function(error) {
				        	$.warningMsg('006');
				        }
				    });
			    }else{
		        	$.warningMsg('006');
			    }

			    var $uploadButton = $("button#uploadButton");
				var $chooseButton = $('span[class$=btn-file]');
				var $fileName = $('span#fileName');
				
				$fileName.text("");
	        	$uploadButton.hide();
	        	$chooseButton.show();
			}
			
			var setSendEmailClickEvent = function(duplication) {
				setDuplication(duplication);
				
				var $modal = $('div#sendEmailModal');
	    		var $confirmButton = $modal.find('button#confirmButton');
	    		
	    		$confirmButton.unbind('click').on('click', function() {
	    			var settings = {
	    				httpMethod: 'POST', 
	    				URL: contextPath + '/workshop/creation-group/send-email.action',
	    				dataType: 'html', 
	    				dataProvider: function() {
	    					var formData = window.form2object('sendEmailModal');
	    					formData.msisdnList = getCheckedList($('table#msisdnTable'));
	    					
	    					return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
	    			    }, 
	    			    success: function(data) {
	    			    	if(data) {
	    			    		$.successMsg('009');
	    						$('div#info').show();
	    						$('div#notifyOtherSales').hide();
	    			    		
	    			    		return;
	    			    	}
	    			    	$.warningMsg('021');
	    			    }
	    			}
	    			$.formAjax(settings);
	    		});
				$modal.modal('show');
			}
			
			var setDuplication = function (d) {
				var duplication = '';
				
				$.each(d, function (i) {
					duplication += this.comboContent;
					if (i == d.length-1) {
						return;
					}
					duplication += ',';
				});
				$('textArea[name=comboContent]').val(duplication);
			}
			
			// 建檔頁面的進入案件內容按鈕要做的事
			var createOrder = function(action) {
				if (isBlank()) {
					return;
				}
				
				var formData = getCreationFileData();
				if (!formData) {
					return;
				}
				
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/workshop/creation-group/creation-file.action',
					dataType: 'html',
					dataProvider: function() {
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
				    }, 
				    success: function(data) {
				    	if(data) {
				    		data = JSON.parse(data);
				    		var flowId = data.flowId;
				    		var orderMId = data.orderMId;
				    		var sourceSysId = data.sourceSysId;
				    		var orderTypeId = data.orderTypeId;
				    		
				    		if (action == 'content') {
					    		ajaxGetServiceBuzFlowAction(flowId, orderMId, sourceSysId, orderTypeId);
				    		} else {
				    			location.reload();
				    		}
				    	}
				    }
				}
				$.formAjax(settings);
			}
			
			/* Inner Method
			=================================================================================================================*/
			var getCreationFileData = function () {
				var formData = window.form2object('creationFile');
				var orderTypeId = $('div#info').attr('orderTypeId');
				var msisdn = formData.msisdn;
				var corpPicTaxId = formData.corpPicTaxId;
				
				if ($.validateCardNumbers(msisdn, 10)) {
					$.warningMsg('027');
					
					return null;
				}
				if ($.validateCardNumbers(corpPicTaxId)) {
					$.warningMsg('028');
					
					return null;
				}
				
				formData.orderTypeId = orderTypeId;
				
				return formData;
			}
			
			var setCreationButtons = function (vo) {
				if (vo.btnCreateSingle == 'Y') {
					$('button#creationButton').show();
				}
				
				if (vo.btnCreateBatch == 'Y') {
					$('button#batchCreationButton').show();
				}
				
				if (vo.btnNotice == 'Y') {
					$('button#notifyOtherSalesButton').show();
				}
			}
			
			var getNotifyOtherSalesResult = function (data) {
				$.destoryDataTable($('table#msisdnTable'));
				initMsisdnTable(data);
				$.setCheckBoxAllCheckedEvent($('table#msisdnTable #checkAll'));
				$('div#notifyOtherSales').find('#searchResult').show();
			}
			
			var initMsisdnTable = function (data) {
				var $dataTable = $('table#msisdnTable');
				
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
						{
							targets: [0], 
							data: null, 
							title: "<input type='checkbox' id='checkAll'>", 
							className: 'osp_features', 
							defaultContent: "<input type='checkbox'>"
						},   
						{targets: [1], title: 'OSP單號', data: 'orderMId', className: 'orderMId'}, 
						{targets: [2], title: 'OSP進件時間', data: 'ospCreateTime', className: 'ospCreateTime'}, 
						{targets: [3], title: '門號/代表號/線路編號', data: 'msisdn', className: 'msisdn'},
						{targets: [4], title: '案件狀態', data: 'orderStatusText', className: 'orderStatusText'},  
						{targets: [5], title: '問題原因 ', data: 'processReasonText', className: 'processReasonText'}, 
						{targets: [6], title: '備註', data: 'commment', className: 'commment'},
						{targets: [7], title: '', data: 'orderStatus', className: 'orderStatus hide'} 
					]
				});
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
					var msisdn = {};

					msisdn.orderMId = $tr.find('td.orderMId').text();
					msisdn.msisdn = $tr.find('td.msisdn').text();
					data.push(msisdn);
				});
				
				return data;
			}
			
			// 檢核門號/進件來源是否有輸入
			var isBlank = function () {
				var msisdn = $('div#creationFile').find('input[name=msisdn]').val();
				var sourceSysId = $('select[name=sourceSysId]').val();
				var custType = $('select[name=custType]').val();
				
				if (!msisdn) {
					$.warningMsg('015');
					
					return true;
				}
				if (!sourceSysId) {
					$.warningMsg('016');
					
					return true;
				}
				if (custType == 'NBT05' || custType == 'NBT03') {
					$.warningMsg('020');
					
					return true;
				}
				
				return false;
			}
			
			var ajaxGetServiceBuzFlowAction = function(flowId, orderMId, sourceSysId, orderTypeId) {
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/flow/get-buz-order-flow.action?' + 
						'flowId=' + flowId + 
						'&orderMId=' + orderMId + 
						'&orderTypeId=' + orderTypeId + 
						'&sourceSysId=' + sourceSysId,
					dataType: 'html',
					dataProvider: function() {}, 
				    success: function(data) {
				    	if(data) {
				    		var $creationFile = $('div#creationFile');
				    		var $caseContent = $('div#caseContent');
				    		
				    		$creationFile.hide();
				    		$caseContent.empty().html(data);
				    		flowMainService.init();
				    		$caseContent.show();
				    		$.popOrderImageWindow(orderMId);
				    	}
				    }
				}
				$.formAjax(settings);
			}
			
			var setResultTableButtonClickEvent = function() {
				var $dataTable = $('table#dataTable');
				
				$dataTable.unbind('click');
				
				// 編輯按鈕事件
				$dataTable.on('click', '#editBtn', function() {
					// 處理click event
					var $row = $(this).closest('tr');// closest(): 找到最接近的指定元素
					var orderMId = $row.find(".ORDER_M_ID").text();
					var settings = {
						httpMethod: 'GET', 
						URL: contextPath + '/flow/get-buz-order-flow.action?orderMId=' + orderMId,
						dataType: 'html',
					    success: function(data) {
					    	if(data) {
					    		$('div #info').hide();
					    		$('div #caseContent').empty().html(data).show();
					    		
					    		flowMainService.init();
					    		$.popOrderImageWindow(orderMId);
					    	}
					    }
					}
					$.formAjax(settings);
				});
			}
			
			var initResultTable = function(jsonData) {
				var sum = jsonData.sum;
				var critical = jsonData.critical;
				var overdue = jsonData.overdue;
				var dataList = jsonData.dataLs;
				var $dataTable = $('table#dataTable');

				//將各種案件數量塞進對應的物件
				$("div #sum").text(sum);
				$("div #critical").text(critical);
				$("div #overdue").text(overdue);
				
				$dataTable.DataTable({
					dom: '<"row"lf><"row"tr><"row"ip>',
					paging: true, // 翻頁功能
					lengthChange: true, // 改變每頁顯示數據數量
					pageLength: 100, // 顯示10筆換下一頁
					searching: false, // 過濾功能
					ordering: true, // 排序功能
					info: true, // 頁腳信息
					autoWidth: false, // 自動寬度
					data: dataList, // 注入資料
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
				            title: "功能項", 
				            className: 'osp_features', 
				            defaultContent: "<button class='edit_button' id='editBtn'><span class='fa fa-pencil'></span></button>"
				        },
				        {targets: [1], title: '案件狀態', data: 'ORDER_STATUS'},
					    {targets: [2], title: 'OSP單號', data: 'ORDER_M_ID', className: "ORDER_M_ID"},
					    {targets: [3], title: '案件類別', data: 'ORDER_TYPE_NAME', className: "ORDER_TYPE_NAME"},
					    {targets: [4], title: '進件系統', data: 'SOURCE_SYS_ID'},
					    {targets: [5], title: '產品類別', data: 'SOURCE_PROD_TYPE_NAME'},
					    {targets: [6], title: '交易型態', data: 'OPERATE_TYPE'},
					    {targets: [7], title: '進件時間', data: 'OSP_CREATE_TIME'},
					    {targets: [8], title: '門號/代表號/線路編號', data: 'MSISDN' },
					    {targets: [9], title: '用戶名稱', data: 'CUST_NAME'},
					    {targets: [10], title: '來源單號', data: 'SOURCE_ORDER_ID'},
					    {targets: [11], title: '母子單', data: 'PARTENT_ORDER_IDs', defaultContent: ''},
					    {targets: [12], title: '筆數', data: 'COUNTS'},
					    {targets: [13], title: '預計作業處理時間', data: 'EXPECT_PROCESS_TIME'},
					    {targets: [14], title: '預計完成時間', data: 'EXPECT_COMPLETE_TIME'},
					    {targets: [15], title: '客戶指定生效日', data: 'CUST_SPECIFY_DATE', defaultContent: ''},
					    {targets: [16], title: '處理人員', data: 'PROCESS_USER_ID', defaultContent: ''},
					    {targets: [17], title: '', data: 'FLOW_ID', className: "hide FLOW_ID" },
					    {targets: [18], title: '', data: 'ORDER_TYPE_ID', className: "hide ORDER_TYPE_ID" },
						{targets: [19], title: '', data: 'LIGHT_COLOR', className: 'LIGHT_COLOR hide'}
				    ]
				});
			}
			
			// 控制左邊menu [ 開闔 ] 的時候內容「表單」的排版
			var setFormPositionEvent = function() {
				$('.page-sidebar').click(function() {
					if(!$('body').hasClass('page-sidebar-closed')) {
						$.setMenuOpen($('div#creationFile'), 'creation_file');// creation-file
						$.setMenuOpen($('div#notifyOtherSales'), 'notify_other_sales');// notify-other-sales
						$.setMenuOpen($('div#batchCreationFile'), 'batch_creation_file');// batch-creation-file
					}else{
						$.setMenuClosed($('div#creationFile'), 'creation_file');// creation-file
						$.setMenuClosed($('div#notifyOtherSales'), 'notify_other_sales');// notify-other-sales
						$.setMenuClosed($('div#batchCreationFile'), 'batch_creation_file');// batch-creation-file
					}
				});
			};
			
			return {
				init: init,
				createOrder: createOrder,
				setSendEmailClickEvent: setSendEmailClickEvent,
				chooseFile: chooseFile,
				uploadFile: uploadFile, 
				ajaxGetMsisdnList: ajaxGetMsisdnList
			}
		}
	);
})()

//顯示訊息
var showMessage = function() {
	$.showMessage();
}

//有效件
var success = function() {
	$.success();
}

//無效件
var fail = function() {
	$.fail();
}

//更改案件類別
var changeOrderType = function() {
	$.changeOrderType();
}

//開啟影像檔工單
var showSourceDocument = function() {
	$.showSourceDocument();
}

//暫離
var tempLeave = function() {
	$.tempLeave();
}

//待系統回覆
var reply = function() {
	$.reply();
}

//暫存
var tempSave = function() {
	$.tempSave();
}
