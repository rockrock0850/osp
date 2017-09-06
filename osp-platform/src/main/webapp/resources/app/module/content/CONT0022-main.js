require(['flip', 'CONT0022-service', 'button'], 
	function (flip, service) {
		service.init();
		
		$('button#modifyButton').unbind('click').click(function() {
			service.modifyOrderMainInfo();
		});
	}
);

(function() {
	define('CONT0022-service', ['flow-main-service', 'flip', 'small-modal'], 
		function(flowService) {
			var init = function() {
				var callback = function () {
					queryOrderMainInfo();
				}

				$.getOptionCustType($('select[name=custType]'), callback);
			}
			
			var modifyOrderMainInfo = function() {
				var $form = $('form#cont0022Form');
				var msisdn = $form.find('input[name=msisdn]').val();
				var custType = $form.find('select[name=custType]').val();
				var corpPicTaxid = $form.find('input[name=corpPicTaxid]').val();

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
				var formData = window.form2object("cont0022Form");
				formData.orderMId = orderMId;

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
			
			/* Inner Method
			=================================================================================================== */
			// 由DB取回資料
			var queryOrderMainInfo = function() {
				var orderMId = $('div#buzFlow').attr('orderMId');

				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/flow/content/get-order-main-info.action?orderMId=' + orderMId,
					dataType: 'html',
				    success: function(data) {
				    	if(data) {
				    		data = JSON.parse(data);
				    		
							$('#cont0022Form input[name=msisdn]').val(data.msisdn);
							$('#cont0022Form input[name=custId]').val(data.custId);
							$('#cont0022Form input[name=ivrCode]').val(data.ivrCode);
							$('#cont0022Form input[name=salesId]').val(data.salesId);
							$('#cont0022Form input[name=promotionId]').val(data.promotionId);
							$('#cont0022Form select[name=custType]').val(data.custType);
							$('#cont0022Form input[name=corpPicTaxid]').val(data.corpPicTaxid);
				    	}
				    }
				}
				$.formAjax(settings);
			}
		
			return {
				init: init,
				modifyOrderMainInfo: modifyOrderMainInfo
			}
		}
	);
})()