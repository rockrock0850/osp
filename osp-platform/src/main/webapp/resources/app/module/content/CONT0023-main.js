require(['flip', 'CONT0023-service', 'button'], 
	function (flip, service) {
		service.init();
		
		// 申請學生溫暖長青
		$('button#getSpecialOfferButton').unbind('click').click(function() {
			service.getSpecialOfferInfo();
		});
		
	   	// Future Task
		$("button#futureTaskBtn").unbind().bind("click", function() {
			$('div#futureTaskModal').modal('show');
		});
		
		// 事件列表
		$("button#eventListBtn").unbind().bind("click", function() {
			$('div#eventListModal').modal('show');
		});
		
		// 證號歷史查詢
		$("button#idHistoryBtn").unbind().bind("click", function() {
			service.getIdHistoryInfo();
		});
		
		// 查詢
		$('button#queryButton').unbind('click').click(function() {
			service.checkCustInfo();
		});
		
		// 儲存
		$('button#saveButton').unbind('click').click(function() {
			service.saveOrderMainInfo();
		});
	}
);

(function() {
	define('CONT0023-service', ['flip', 'small-modal'], 
		function() {
			var $modifyForm = $('#modifyForm_23');

			var init = function() {
				var callback = function () {
					queryOrderMainInfo();

					// 由bus取回
					queryBusCustInfo();
				}

				$.getOptionCustType($modifyForm.find('select[name=custType]'), callback);
				
				// init apppart view
				var $futureTaskContainer = $("div#futureTaskView");
				var $eventListContainer = $("div#eventListView");
				
				angular.bootstrap($futureTaskContainer, ['ngRoute', 'ngSanitize', 'thunder', 'thunder.ui', 'thunder.wizard', 
				                               'nspDirective', 'genericServices']);
				
				angular.bootstrap($eventListContainer, ['ngRoute', 'ngSanitize', 'thunder', 'thunder.ui', 'thunder.wizard', 
				                               'nspDirective', 'genericServices']);
				
			}

			var checkCustInfo = function() {
				var msisdn = $modifyForm.find('input[name=msisdn]').val();
				var custType = $modifyForm.find('select[name=custType]').val();
				var corpPicTaxid = $modifyForm.find('input[name=corpPicTaxid]').val();

				if (!msisdn) {
					$.warningMsg('015');

					return true;
				}

				if (!$.validateMsisdn(msisdn)) {
					$.warningMsg('027');
					
					return true;
				}

				if ((custType == 'NBT05' || custType == 'NBT03') && !corpPicTaxid) {
					$.warningMsg('020');

					return true;
				}

				// 重新查詢核資資料 
				queryBusCustInfo();
			}

			// 申請學生溫暖長青
			var getSpecialOfferInfo = function() {

				var rocId = $modifyForm.find('input[name=custId]').val();
				var $specialOfferModal = $('div#specialOfferModal');

				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/flow/content/get-special-offer-info.action?rocId=' + rocId,
					dataType: 'html',
				    success: function(data) {
				    	if(data) {
				    		$.destoryDataTable($('table#specialOfferDataTable'));
				    		handleSpecialOfferDataTable(data);
				    		$specialOfferModal.modal('show');
				    	}
				    }
				}
				
				$.formAjax(settings);
			}

			var saveOrderMainInfo = function() {
				var msisdn = $modifyForm.find('input[name=msisdn]').val();
				var custId = $modifyForm.find('input[name=custId]').val();
				var ivrCode = $modifyForm.find('input[name=ivrCode]').val();
				var salesId = $modifyForm.find('input[name=salesId]').val();
				var promotionId = $modifyForm.find('input[name=promotionId]').val();
				var custName = $modifyForm.find('label[name=custName]').text();
				var custType = $modifyForm.find('select[name=custType]').val();
				var corpPicTaxid = $modifyForm.find('input[name=corpPicTaxid]').val();
				
				if (!msisdn) {
					$.warningMsg('015');

					return true;
				}

				if (!$.validateMsisdn(msisdn)) {
					$.warningMsg('027');
					
					return true;
				}

				if ((custType == 'NBT05' || custType == 'NBT03') && !corpPicTaxid) {
					$.warningMsg('020');

					return true;
				}

				var orderMId = $('div#buzFlow').attr('orderMId');
				var formData = {};
				formData.orderMId = orderMId;
				formData.msisdn = msisdn;
				formData.custId = custId;
				formData.ivrCode = ivrCode;
				formData.salesId = salesId;
				formData.promotionId = promotionId;
				formData.custName = custName;
				formData.custType = custType;
				formData.corpPicTaxid = corpPicTaxid;

				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/flow/content/modify-order-main-info.action', 
					dataType: 'html',
					dataProvider: function() {
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
				    }, 
				    success: function(data) {
				    	if(data) {
				    		// 注意: 只用ajax刷頁面重載, 按鈕事件不會重新綁定
				    		window.location = window.location.href + "#refresh:" + orderMId;
				    		window.location.reload();
				    	}
				    }
				    
				}
				
				$.formAjax(settings);
			}

			// 證號歷史查詢
			var getIdHistoryInfo = function() {
				var rocId = $modifyForm.find('input[name=custId]').val();
				var $idHistoryModal = $('div#idHistoryModal');

				var settings = {
					httpMethod: 'POST',
					URL: contextPath + '/flow/content/get-id-view-info.action?rocId=' + rocId,
					dataType: 'html',
				    success: function(data) {
				    	if(data) {
				    		$.destoryDataTable($('table#idHistoryDataTable'));
				    		handleIdHistoryDataTable(data);
				    		$idHistoryModal.modal('show');
				    	}
				    }
				}
				
				$.formAjax(settings);
			}

			/* BEGIN Private Method */
			
			// 由DB取回資料
			var queryOrderMainInfo = function() {
				var orderMId = $('div#buzFlow').attr('orderMId');

				var settings = {
						httpMethod: 'POST', 
						URL: contextPath + '/flow/content/get-order-main-info.action?orderMId=' + orderMId,
						dataType: 'html',
						async: false,
					    success: function(data) {
					    	if(data) {
					    		data = JSON.parse(data);
					    		
					    		$modifyForm.find('label[name=custName]').text(data.custName);
					    		$modifyForm.find('input[name=msisdn]').val(data.msisdn);
					    		$modifyForm.find('input[name=custId]').val(data.custId);
					    		$modifyForm.find('input[name=ivrCode]').val(data.ivrCode);
					    		$modifyForm.find('input[name=salesId]').val(data.salesId);
					    		$modifyForm.find('input[name=promotionId]').val(data.promotionId);
					    		$modifyForm.find('select[name=custType]').val(data.custType);
					    		$modifyForm.find('input[name=corpPicTaxid]').val(data.corpPicTaxid);
					    	}
					    }
				}

				$.formAjax(settings);
			}

			// 核資
			var queryBusCustInfo = function() {
				var msisdn = $modifyForm.find('input[name=msisdn]').val();
				var rocId = $modifyForm.find('input[name=custId]').val();

				var settings = {
						httpMethod: 'POST', 
						URL: contextPath + '/flow/content/get-bus-cust-info.action?msisdn=' + msisdn + '&rocId=' + rocId,
						dataType: 'html',
					    success: function(data) {

					    	clearLastBusCustInfo();

					    	if(data) {
					    		data = JSON.parse(data);

					    		var basicInfo = data.custInfoForOSPReturnVO;
					    		var billInfo = data.custBillingInfoReturnVO;
					    		var authLevelInfo = data.authLevelInfoReturnVO;
					    		var authLevel;
					    		var billDetailList = data.billDetailVOList;

					    		if (basicInfo) {
						    		$modifyForm.find('label[name=mobileGeneration]').text(basicInfo.mobileGeneration); //門號類別
						    		$modifyForm.find('label[name=contractStatusValue]').text(basicInfo.contractStatusValue); //門號(客戶)狀態
						    		$modifyForm.find('label[name=birthDate]').text(basicInfo.birthDate); //生日
						    		$modifyForm.find('label[name=sim]').text(basicInfo.sim); //sim
						    		$modifyForm.find('label[name=activeDate]').text(basicInfo.activeDate); //門號啟用日
						    		$modifyForm.find('label[name=accountId]').text(basicInfo.accountId); //S1帳號(續約帳號)

						    		// 付款人資訊
						    		$modifyForm.find('label[name=identificationType]').text(basicInfo.identificationType); //證照類別
						    		$modifyForm.find('label[name=payerName]').text(basicInfo.payerName); //用戶姓名
						    		$modifyForm.find('label[name=identificationNumber]').text(basicInfo.identificationNumber); //證照號碼
						    		$modifyForm.find('label[name=companyName]').text(basicInfo.companyName); //公司名稱
						    		$modifyForm.find('label[name=citizenshipType]').text(basicInfo.citizenshipType); //國籍
						    		$modifyForm.find('label[name=billAddress]').text(basicInfo.billAddress); //帳單地址
						    		$modifyForm.find('label[name=houseHoldAddress]').text(basicInfo.houseHoldAddress); //戶籍地址
						    		$modifyForm.find('label[name=contactTelList]').text(basicInfo.contactTelList); //聯絡電話
						    		$modifyForm.find('label[name=billEmailList]').text(basicInfo.billEmailList); //帳單電子信箱
						    		$modifyForm.find('label[name=otherEmailList]').text(basicInfo.otherEmailList); //其他電子信箱
						    		$modifyForm.find('label[name=promotionSMSIndicator]').text(basicInfo.promotionSMSIndicator); //遠傳相關優惠簡訊

						    		// 使用人資訊
						    		$modifyForm.find('label[name=userName]').text(basicInfo.userName); //姓名
						    		$modifyForm.find('label[name=secondIdentificationType]').text(basicInfo.secondIdentificationType); //第二證照類別
						    		$modifyForm.find('label[name=secondIdentificationNumber]').text(basicInfo.secondIdentificationNumber); //第二證照號碼
						    		$modifyForm.find('label[name=personalEmailList]').text(basicInfo.personalEmailList); //個人電子信箱
					    		}

					    		if (billInfo) {
						    		$modifyForm.find('label[name=arpb]').text(billInfo.arpb); //arpb

						    		// 付款人資訊
						    		$modifyForm.find('label[name=customerScore]').text(billInfo.customerScore); //VIP客戶等級

						    		// 帳務資訊
						    		$modifyForm.find('label[name=cycleDate]').text(billInfo.cycleDate); //帳單結帳日
						    		$modifyForm.find('label[name=paymentMethod]').text(billInfo.paymentMethod); //付款方式
					    		}

					    		if (authLevelInfo) {
					    			authLevel = authLevelInfo.level + "-" + authLevelInfo.empId + "-" + authLevelInfo.name;
					    			
					    			$modifyForm.find('label[name=authLevel]').text(authLevel); //簽核層級
					    		}

					    		if (billDetailList) {
					    			// 帳務詳細資料table
						    		$.destoryDataTable($('table#billDetailDataTable'));
						    		handleBillDetailDataTable(billDetailList);
					    		}

					    	}
					    }
				}
					
				$.formAjax(settings);
			}
			
			
			// 查詢前，先清除前次資料
			var clearLastBusCustInfo = function() {
	    		$modifyForm.find('label[name=mobileGeneration]').text(''); //門號類別
	    		$modifyForm.find('label[name=contractStatusValue]').text(''); //門號(客戶)狀態
	    		$modifyForm.find('label[name=birthDate]').text(''); //生日
	    		$modifyForm.find('label[name=sim]').text(''); //sim
	    		$modifyForm.find('label[name=activeDate]').text(''); //門號啟用日
	    		$modifyForm.find('label[name=accountId]').text(''); //S1帳號(續約帳號)

	    		// 付款人資訊
	    		$modifyForm.find('label[name=identificationType]').text(''); //證照類別
	    		$modifyForm.find('label[name=payerName]').text(''); //用戶姓名
	    		$modifyForm.find('label[name=identificationNumber]').text(''); //證照號碼
	    		$modifyForm.find('label[name=companyName]').text(''); //公司名稱
	    		$modifyForm.find('label[name=citizenshipType]').text(''); //國籍
	    		$modifyForm.find('label[name=billAddress]').text(''); //帳單地址
	    		$modifyForm.find('label[name=houseHoldAddress]').text(''); //戶籍地址
	    		$modifyForm.find('label[name=contactTelList]').text(''); //聯絡電話
	    		$modifyForm.find('label[name=billEmailList]').text(''); //帳單電子信箱
	    		$modifyForm.find('label[name=otherEmailList]').text(''); //其他電子信箱
	    		$modifyForm.find('label[name=promotionSMSIndicator]').text(''); //遠傳相關優惠簡訊

	    		// 使用人資訊
	    		$modifyForm.find('label[name=userName]').text(''); //姓名
	    		$modifyForm.find('label[name=secondIdentificationType]').text(''); //第二證照類別
	    		$modifyForm.find('label[name=secondIdentificationNumber]').text(''); //第二證照號碼
	    		$modifyForm.find('label[name=personalEmailList]').text(''); //個人電子信箱
	    		
	    		$modifyForm.find('label[name=arpb]').text(''); //arpb

	    		// 付款人資訊
	    		$modifyForm.find('label[name=customerScore]').text(''); //VIP客戶等級

	    		// 帳務資訊
	    		$modifyForm.find('label[name=cycleDate]').text(''); //帳單結帳日
	    		$modifyForm.find('label[name=paymentMethod]').text(''); //付款方式
	    		
	    		$modifyForm.find('label[name=authLevel]').text(''); //簽核層級
	    		
	    		
	    		if ($.fn.DataTable.isDataTable('table#billDetailDataTable') ){
	    			$('table#billDetailDataTable').DataTable().destroy();
	    		}

	    		$('table#billDetailDataTable tbody').empty();
			}

			// 初始化detailDetailDataTable
			var handleBillDetailDataTable = function(data) {
				var $dataTable = $('table#billDetailDataTable');

				$dataTable.DataTable({
					dom: '<"row"lf><"row"tr><"row"ip>',
					paging: false, // 翻頁功能
					lengthChange: false, // 改變每頁顯示數據數量
					pageLength: 100, // 顯示10筆換下一頁
					searching: false, // 過濾功能
					ordering: false, // 排序功能
					info: false, // 頁腳信息
					autoWidth: false, // 自動寬度
					data: data, // 注入資料
					columnDefs: [ // 欄位設定
						{orderable: false, targets: []}, // 選擇關閉哪個欄位排序功能(  )
						{width: '10%', targets: [0], title: '結帳日期', data: 'statementDate'}, 
						{width: '10%', targets: [1], title: '繳費截止', data: 'dueDay'}, 
						{width: '10%', targets: [2], title: '帳單類型', data: 'subBE'},
						{width: '10%', targets: [3], title: '帳單類別', data: 'invoiceType'}, 
						{width: '10%', targets: [4], title: '新增費用', data: 'totalAmount'},
						{width: '10%', targets: [5], title: '總新增費用', data: 'sumAmount'},// 自己算
						{width: '10%', targets: [6], title: '帳單餘額', data: 'invoiceBalance'},
						{width: '10%', targets: [7], title: '當期未繳餘額', data: 'sumBalance'},// 自己算
						{width: '20%', targets: [8], title: '帳單編號', data: 'billingInvoiceNumber'}
					]
				});
			}			

			// 初始化specialOfferDataTable
			var handleSpecialOfferDataTable = function(data) {
				var $dataTable = $('table#specialOfferDataTable');

				data = $.parseJSON(data);

				$dataTable.DataTable({
					dom: '<"row"lf><"row"tr><"row"ip>',
					paging: false, // 翻頁功能
					lengthChange: false, // 改變每頁顯示數據數量
					pageLength: 100, // 顯示10筆換下一頁
					searching: false, // 過濾功能
					ordering: true, // 排序功能
					info: false, // 頁腳信息
					autoWidth: false, // 自動寬度
					data: data, // 注入資料
					columnDefs: [ // 欄位設定
						{orderable: false, targets: [0]}, // 選擇關閉哪個欄位排序功能(  )
						{width: '15%', targets: [0], title: '門號', data: 'msisdn'}, 
						{width: '55%', targets: [1], title: '折扣名稱', data: 'offerName'}, 
						{width: '15%', targets: [2], title: '開始日期', data: 'startDate'},
						{width: '15%', targets: [3], title: '結束日期', data: 'endDate'}
					]
				});

			}

			// 初始化idHistoryDataTable
			var handleIdHistoryDataTable = function(data) {
				var $dataTable = $('table#idHistoryDataTable');

				data = $.parseJSON(data);

				$dataTable.DataTable({
					dom: '<"row"lf><"row"tr><"row"ip>',
					paging: true, // 翻頁功能
					lengthChange: false, // 改變每頁顯示數據數量
					pageLength: 100, // 顯示10筆換下一頁
					searching: false, // 過濾功能
					ordering: true, // 排序功能
					info: false, // 頁腳信息
					autoWidth: false, // 自動寬度
					data: data, // 注入資料
					columnDefs: [ // 欄位設定
						{orderable: false, targets: [0]}, // 選擇關閉哪個欄位排序功能(  )
						{width: '10%', targets: [0], title: '帳號', data: 'accountId'}, 
						{width: '10%', targets: [1], title: '產品別', data: 'productTypeDesc'}, 
						{width: '10%', targets: [2], title: '姓名', data: 'lastName'},
						{width: '10%', targets: [3], title: '電話', data: 'msisdn', className: 'blue'}, 
						{width: '10%', targets: [4], title: '啟用日', data: 'initActiveDt'},
						{width: '10%', targets: [5], title: '永停日', data: 'lastSubStDt'}, 
						{width: '10%', targets: [6], title: '狀態', data: 'statusDesc'},
						{width: '25%', targets: [7], title: '原因', data: 'subStReasonDscr'}, 
						{width: '5%', targets: [8], title: '均帳', data: 'arpb'}
					]
				});
				
				//移除掉th的class
				$("#idHistoryDataTable th").removeClass("blue");

			}
			
			/* END Private Method */
			
			return {
				init: init,
				checkCustInfo: checkCustInfo,
				getSpecialOfferInfo: getSpecialOfferInfo,
				saveOrderMainInfo: saveOrderMainInfo,
				getIdHistoryInfo: getIdHistoryInfo
			}
		}
	);
})()