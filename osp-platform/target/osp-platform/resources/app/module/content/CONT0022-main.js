require(['flip', 'CONT0022-service', 'button'], 
	function (flip, service) {
		service.init();
		
		$('button#modifyButton').unbind('click').click(function() {
			service.modifyOrderMainInfo();
		});
	}
);

(function() {
	define('CONT0022-service', ['flip', 'small-modal'], 
		function() {
			var init = function() {
				var callback = function () {
					queryOrderMainInfo();
				}

				$.getOptionCustType($('select[name=custType]'), callback);
			}
			
			var modifyOrderMainInfo = function() {
				var msisdn = $('#modifyForm input[name=msisdn]').val();
				var custType = $('#modifyForm select[name=custType]').val();
				var corpPicTaxid = $('#modifyForm input[name=corpPicTaxid]').val();

				if (msisdn == '' || msisdn == null) {
					$.warningMsg('015');

					return true;
				}

				if (custType == 'NBT05' || custType == 'NBT03') {
					if (corpPicTaxid =='' || corpPicTaxid == null) {
						$.warningMsg('020');

						return true;
					}
				}

				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/flow/content/modify-order-main-info.action', 
					dataType: 'html',
					dataProvider: function() {
						var orderMId = $('div#buzFlow').attr('orderMId');
						var formData = window.form2object("modifyForm");
						formData.orderMId = orderMId;
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
				    }, 
				    success: function(data) {
				    	if(data) {
				    		console.log(data);
				    	}
				    }
				    
				}
				
				$.formAjax(settings);
			}
			
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
					    		
								$('#modifyForm input[name=msisdn]').val(data.msisdn);
								$('#modifyForm input[name=custId]').val(data.custId);
								$('#modifyForm input[name=ivrCode]').val(data.ivrCode);
								$('#modifyForm input[name=salesId]').val(data.salesId);
								$('#modifyForm input[name=promotionId]').val(data.promotionId);
								$('#modifyForm select[name=custType]').val(data.custType);
								$('#modifyForm input[name=corpPicTaxid]').val(data.corpPicTaxid);
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