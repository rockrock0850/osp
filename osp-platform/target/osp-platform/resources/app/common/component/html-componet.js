/**
 * author: Lawrence, Adam Yeh, AndrewLee
 */

(function () {
	// 查詢 通知業者 內存外顯值資訊
	$.getOptionComboType = function($select) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-option-combo-type.action', 
			dataType: 'html',
			dataProvider: function() {}, 
		    success: function(data) {
		    	$select.empty().append('<option value="">請選擇</option>');
		    	$select.append(data);
		    }
		}
		$.formAjax(settings);
	}
	
	// 查詢 證號類型 內存外顯值資訊
	$.getOptionCustType = function($select, callback) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-option-cust-type.action', 
			dataType: 'html',
			dataProvider: function() {}, 
		    success: function(data) {
		    	$select.empty().append('<option value="">請選擇</option>');
		    	$select.append(data);
		    	callback();
		    }
		}
		$.formAjax(settings);
	}
	
	// 查詢 建檔頁面的進件系統 內存外顯值資訊
	$.getCreationOptionSourceSystem = function($select) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-creation-option-source-system.action', 
			dataType: 'html',
			dataProvider: function() {}, 
		    success: function(data) {
		    	$select.empty().append('<option value="">請選擇</option>');
		    	$select.append(data);
		    }
		}
		$.formAjax(settings);
	}
	
	// 查詢 進件系統 內存外顯值資訊
	$.getOptionSourceSystem = function($select) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-option-source-system.action', 
			dataType: 'html',
			dataProvider: function() {}, 
		    success: function(data) {
		    	$select.empty().append('<option value="">請選擇</option>');
		    	$select.append(data);
		    }
		}
		$.formAjax(settings);
	}

	// 查詢 產品類別 內存外顯值資訊
	$.getOptionProductType = function($select) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-option-product-type.action', 
			dataType: 'html',
			dataProvider: function() {}, 
		    success: function(data) {
		    	$select.empty().append('<option value="">請選擇</option>');
		    	$select.append(data);
		    }
		}
		$.formAjax(settings);
	}

	// 查詢 案件類別 內存外顯值資訊
	$.getOptionOrderType = function($select) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-option-order-type.action', 
			dataType: 'html',
			dataProvider: function() {}, 
		    success: function(data) {
		    	$select.empty().append('<option value="">請選擇</option>');
		    	$select.append(data);
		    }
		}
		$.formAjax(settings);
	}

	// 查詢共用按鈕 [ 更改案件類別 ] 的案件類別內存外顯值資訊
	$.getOrderTypeDisplayBySourceSysId = function($select, sourceSysId) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-option-order-type-by-source-sys-id.action?sourceSysId=' + sourceSysId, 
			dataType: 'html',
			dataProvider: function() {}, 
		    success: function(data) {
		    	$select.empty().append('<option value="">請選擇</option>');
		    	$select.append(data);
		    }
		}
		$.formAjax(settings);
	}

	// 查詢 交易類型 內存外顯值資訊
	$.getOptionOperaterType = function($select) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-option-operater-type.action', 
			dataType: 'html',
			dataProvider: function() {}, 
		    success: function(data) {
		    	$select.empty().append('<option value="">請選擇</option>');
		    	$select.append(data);
		    }
		}
		$.formAjax(settings);
	}
	
	// 查詢 案件狀態 內存外顯值資訊
	$.getOptionOrderStatus = function($select) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-option-order-status.action', 
			dataType: 'html',
			dataProvider: function() {}, 
		    success: function(data) {
		    	$select.empty().append('<option value="">請選擇</option>');
		    	$select.append(data);
		    }
		}
		$.formAjax(settings);
	}
	
	// 查詢  未結案類別 案件狀態 內存外顯值資訊
	$.getOptionInvalidOrderStatus = function($select) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-invalid-option-order-status.action', 
			dataType: 'html',
			dataProvider: function() {}, 
		    success: function(data) {
		    	$select.empty().append('<option value="">請選擇</option>');
		    	$select.append(data);
		    }
		}
		$.formAjax(settings);
	}

	// 查詢 技能 內存外顯值資訊
	$.getOptionSkill = function($select) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-option-skill.action', 
			dataType: 'html',
			dataProvider: function() {}, 
		    success: function(data) {
		    	$select.empty().append('<option value="">請選擇</option>');
		    	$select.append(data);
		    }
		}
		$.formAjax(settings);
	}

	// 查詢 角色 內存外顯值資訊
	$.getOptionRole = function($select) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-option-role.action', 
			dataType: 'html',
			dataProvider: function() {}, 
		    success: function(data) {
		    	$select.empty().append('<option value="">請選擇</option>');
		    	$select.append(data);
		    }
		}
		$.formAjax(settings);
	}

	// 查詢 處理原因 內存外顯值資訊
	$.getOptionProcessReason = function($select, flowId, orderStatus) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-option-process-reason.action?flowId=' + flowId + '&orderStatus=' + orderStatus,
			dataType: 'html',
			dataProvider: function() {}, 
		    success: function(data) {
		    	$select.empty().append('<option value="">請選擇</option>');
		    	$select.append(data);
		    }
		}
		$.formAjax(settings);
	}

	// 查詢 處理結果 內存外顯值資訊
	$.getOptionProcessResult = function($select, flowId, orderStatus) {// TODO
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-option-process-result.action?flowId=' + flowId + '&orderStatus=' + orderStatus,
			dataType: 'html',
			dataProvider: function() {}, 
		    success: function(data) {
		    	$select.empty().append('<option value="">請選擇</option>');
		    	$select.append(data);
		    }
		}
		$.formAjax(settings);
	}
	
	//查詢 判斷進件時間 內存外顯值資訊
	$.getOptionKpiDateResule = function($select) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-option-kpi-date.action',
			dataType: 'html',
			dataProvider: function() {}, 
			success: function(data) {
				$select.empty();
				$select.append(data);
			}
		}
		$.formAjax(settings);
	}
	
	// 
	$.getOptionBeforeCreateDate = function($select,dayTimeType,callback) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-option-before-create-date.action?dayTimeType=' + dayTimeType,
			dataType: 'html',
			dataProvider: function() {}, 
			success: function(data) {
				$select.empty();
				$select.append("<option value=''>請選擇</option>").append(data);
				
				callback();
			}
		}
		$.formAjax(settings);
	}
	
	// 
	$.getOptionStartCountTime = function($select, callback) {
		var settings = {
			httpMethod: 'GET', 
			URL: contextPath + '/componet/html/get-option-start-count-time.action',
			dataType: 'html',
			dataProvider: function() {}, 
			success: function(data) {
				$select.empty();
				$select.append(data);
			}
		}
		$.formAjax(settings);
	}
})();