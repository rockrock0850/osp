(function () {
	// 暫存 
	$.tempSave = function() {
		var settings = {
			dataType: 'html',
			httpMethod: 'POST',
			URL: contextPath + '/flow/service-temp-save.action',
			dataProvider: function() {
				var orderMId = $('div#buzFlow').attr('orderMId');
				var formDataList = getCustomContentList();
				var orderProcess = {};
				
				orderProcess.orderMId = orderMId;
				orderProcess.content = formDataList;
				
				return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(orderProcess));
		    }, 
			success: function(data){
				if(data) {
					showWorkFlow();
				}
			}
		};
		$.formAjax(settings);
	}

	// 暫離
	$.tempLeave = function() {
		showModal($('div#tempLeaveModal'), function() {
			var settings = {
				dataType: 'html',
				httpMethod: 'POST',
				URL: contextPath + '/flow/service-temp-leave.action',
				dataProvider: function() {
					var orderMId = $('div#buzFlow').attr('orderMId');
					var formDataList = getCustomContentList();
					var buttonFormData = window.form2object('tempLeaveForm');
					var orderProcess = {};
					
					orderProcess.orderMId = orderMId;
					orderProcess.msgContent = buttonFormData.msgContent;
					orderProcess.content = formDataList;
					
					return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(orderProcess));
			    }, 
				success: function(data){
					if(data) {
						showWorkFlow();
					}
				}
			};
			$.formAjax(settings);
		});
	}

	// 有效件
	$.success = function() {
		// 判斷有無勾選  通知業務checkbox
		var isNoticeSalesFlag = $('input[type=checkbox][name=isNoticeSales]').prop("checked");

		// 有勾選，再檢查有無salesId
		if (isNoticeSalesFlag) {
			var hasSalesId = checkHasSalesId();

			if(hasSalesId){
				var sourceSysId = $('div#buzFlow').attr('sourceSysId');
				if(isNeedWindow(sourceSysId)) {
					showModal($('div#successModal'), function() {
						ajaxServiceSuccessAction();
					});
				}else{
					ajaxServiceSuccessAction();
				}
			}
		} else {
			var sourceSysId = $('div#buzFlow').attr('sourceSysId');
			if(isNeedWindow(sourceSysId)) {
				showModal($('div#successModal'), function() {
					ajaxServiceSuccessAction();
				});
			}else{
				ajaxServiceSuccessAction();
			}
		}
	}

	// 無效件 
	$.fail = function() {
		// 判斷有無勾選  通知業務checkbox
		var isNoticeSalesFlag = $('input[type=checkbox][name=isNoticeSales]').prop("checked");

		// 有勾選，再檢查有無salesId
		if (isNoticeSalesFlag) {
			var hasSalesId = checkHasSalesId();

			if(hasSalesId){
				var sourceSysId = $('div#buzFlow').attr('sourceSysId');
				if(isNeedWindow(sourceSysId)) {
					showModal($('div#failModal'), function() {
						ajaxServiceFailAction();
					});
				}else{
					ajaxServiceFailAction();
				}
			}
		} else {
			var sourceSysId = $('div#buzFlow').attr('sourceSysId');
			if(isNeedWindow(sourceSysId)) {
				showModal($('div#failModal'), function() {
					ajaxServiceFailAction();
				});
			}else{
				ajaxServiceFailAction();
			}
		}		
	}

	// 待系統回復
	$.reply = function() {
		showModal($('div#replyModal'), function() {
			var settings = {
				dataType: 'html',
				httpMethod: 'POST',
				URL: contextPath + '/flow/service-reply.action',
				dataProvider: function() {
					var orderProcess = {};
					var orderMId = $('div#buzFlow').attr('orderMId');
					var iframeObject = $("iframe[name='iframe']");
					var formDataList = getCustomContentList();
					
					var ospKeyArray = new Array;
					
					$.each(iframeObject, function(index, iframe) {
						var ospKey = $(this).attr('ospKey');
						
						ospKeyArray.push(ospKey);
					});
					
					orderProcess.orderMId = orderMId;
					orderProcess.ospKeyList = ospKeyArray;
					orderProcess.content = formDataList;
					
					return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(orderProcess));
			    }, 
				success: function(data){
					if(data) {
						showWorkFlow();
					}
				}
			};
			$.formAjax(settings);
		});
	}

	// 更改案件類別
	$.changeOrderType = function() {
		showModal($('div#changeOrderTypeModal'), function() {
			var buttonFormData = window.form2object('changeOrderTypeForm');
			var orderTypeId = buttonFormData.orderTypeId;
			if (!orderTypeId) {
				$.warningMsg('024');
				
				return;
			}
			
			var settings = {
				dataType: 'html',
				httpMethod: 'POST',
				URL: contextPath + '/flow/service-change-order-type.action',
				dataProvider: function() {
					var orderMId = $('div#buzFlow').attr('orderMId');
					var formDataList = getCustomContentList();
					var orderTypeName = $('div#changeOrderTypeModal').find('[name=orderTypeId] option:selected').text();
					var orderProcess = {};
					
					orderProcess.orderMId = orderMId;
					orderProcess.orderTypeId = orderTypeId;
					orderProcess.orderTypeName = orderTypeName;
					orderProcess.content = formDataList;
					
					return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(orderProcess));
			    }, 
				success: function(data){
					if(data == 'true') {
						showCaseContent();
					} else {
						showWorkFlow();
					}
				}
			};
			$.formAjax(settings);
		});
	}

	// 顯示訊息
	$.showMessage = function() {
		var settings = {
			dataType: 'html',
			httpMethod: 'POST',
			URL: contextPath + '/flow/service-show-message.action',
			dataProvider: function() {
				var orderMId = $('div#buzFlow').attr('orderMId');
				var orderProcess = {};
				
				orderProcess.orderMId = orderMId;
				
				return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(orderProcess));
		    }, 
			success: function(message){
				showMessageModal(message);
			}
		};
		$.formAjax(settings);
	}

	// 開啟影像檔工單
	$.showSourceDocument = function() {
		var orderMId = $('div#buzFlow').attr('orderMId');
		
		$.popOrderImageWindow(orderMId);
	}
	
	/* Inner Method
	=================================================================================================================== */
	var ajaxServiceSuccessAction = function () {
		var settings = {
			dataType: 'html',
			httpMethod: 'POST',
			URL: contextPath + '/flow/service-success.action',
			dataProvider: function() {
				var orderMId = $('div#buzFlow').attr('orderMId');
				var formDataList = getCustomContentList();
				var buttonFormData = window.form2object('successForm');
				var orderProcess = {};
				
				orderProcess.orderMId = orderMId;
				orderProcess.processReason = buttonFormData.reasonId;
				orderProcess.processResult = buttonFormData.resultId;
				orderProcess.comment = buttonFormData.comment;
				orderProcess.content = formDataList;
				
				return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(orderProcess));
		    }, 
			success: function(data){
				if(data) {
					showWorkFlow();
				}
			}
		};
		$.formAjax(settings);
	}
	
	var showMessageModal = function(message) {
		var $modal = $('div#showMessageModal');
		var $confirmButton = $modal.find('button#confirmButton');
		var $message = $modal.find('label#message');
		
		$message.html(message);
		$confirmButton.unbind('click').click(function() {
			$modal.modal('close');
		});
		$modal.modal('show');
	}
	
	var ajaxServiceFailAction = function() {
		var settings = {
			dataType: 'html',
			httpMethod: 'POST',
			URL: contextPath + '/flow/service-fail.action',
			dataProvider: function() {
				var orderMId = $('div#buzFlow').attr('orderMId');
				var formDataList = getCustomContentList();
				var buttonFormData = window.form2object('failForm');
				var orderProcess = {};
				
				orderProcess.orderMId = orderMId;
				orderProcess.processReason = buttonFormData.reasonId;
				orderProcess.processResult = buttonFormData.resultId;
				orderProcess.comment = buttonFormData.comment;
				orderProcess.content = formDataList;
				
				return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(orderProcess));
		    }, 
			success: function(data){
				if(data) {
					showWorkFlow();
				}
			}
		};
		$.formAjax(settings);
	}
	
	// 判斷無效件按鈕按下之後是否需要彈出訊息視窗
	var isNeedWindow = function(sourceSysId) {
		var matchs = {
			'AIMS': function() {
				return false;
			},
			'ITT': function() {
				return false;
			}
		};
		
		try {
			return matchs[sourceSysId]();
		} catch (e) {
			return true;
		}
	}
	
	// 處理Modal關閉之後需要做的事情
	var afterModalClosed = function() {
		// tempLeaveModal
		$.afterModalClosed($('div#tempLeaveModal'), function() {
			$('textArea').html('');
		});

		// successModal
		$.afterModalClosed($('div#successModal'), function() {
			$('textArea').html('');
		});

		// failModal
		$.afterModalClosed($('div#failModal'), function() {
			$('textArea').html('');
		});

		// changeOrderTypeModal
		$.afterModalClosed($('div#changeOrderTypeModal'), function() {
		});
	}
	
	// 顯示指定Modal
	var showModal = function($modal, afterClick) {
		var $confirmButton = $modal.find('button#confirmButton');
		
		$confirmButton.unbind('click').click(function() {
			afterClick();
		});
		$modal.modal('show');
	}
	
	// 取得所有表單內容 
	var getCustomContentList = function() {
		var $forms = $('div[id^=CONT]');
		var formList = [];
		
		$.each($forms, function(i, form) {
			var content = {};
			var formId = $(form).attr('id');
			var formData = window.form2object(formId);

			formData.contentId = formId;
			content.contentId = formId;
			content.contentData = formData;
			formList.push(content);
		});
		
		return formList;
	}
	
	// 顯示案件流程頁面
	var showWorkFlow = function() {
		var menuId = $('div#buzFlow').attr('orderTypeId');
		var flowData = '{menuId:' + menuId + '}';
		
		window.location.href = contextPath + '/console-panel.action?jsonData=' + encodeURIComponent(flowData);
	}
	
	// 顯示案件內容
	var showCaseContent = function() {
		var orderMId = $('div#buzFlow').attr('orderMId');
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/flow/get-buz-order-flow.action?orderMId=' + orderMId,
			dataType: 'html',
		    success: function(data) {
		    	if(data) {
		    		$('div #info').hide();
		    		$('div #caseContent').empty().html(data).show();
		    		
		    		$.flowInit();
		    		$.popOrderImageWindow(orderMId);
		    	}
		    }
		}
		$.formAjax(settings);
	}
	
	// 查詢有無業務員編
	var checkHasSalesId = function () {
		var result = false;
		var orderMId = $('div#buzFlow').attr('orderMId');
		var settings = {
				httpMethod: 'POST', 
				URL: contextPath + '/flow/content/get-order-main-info.action?orderMId=' + orderMId,
				dataType: 'html',
				async: false,
			    success: function(data) {
			    	if(data) {
			    		data = JSON.parse(data);

			    		var salesId = data.salesId;

			    		if (!salesId) {
			    			$.warningMsg('038');

			    			result = false;
			    		} else {
			    			result = true;
			    		}
			    	}
			    }
			}
			$.formAjax(settings);
		
		return result;
	}
	
	var orderStatus = $('div#buzFlow').attr('orderStatus');
	if (orderStatus == '030') {
		$.showMessage();
	}
})();