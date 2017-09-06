require(['flip', 'CONT0019-service', 'button'], 
	function (flip, service) {
		service.init();
		
		$('button#cont0019SearchButton').unbind('click').click(function() {
			service.ajaxApnIpResult();
		});
	}
);

(function() {
	define('CONT0019-service', ['flip', 'small-modal'], 
		function() {
			var init = function() {
				getMsisdn();
			}

			var ajaxApnIpResult = function () {
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/flow/content/get-apn-id-result.action',
					dataType: 'html',
					dataProvider: function() {
						var formData = window.form2object('CONT0019Form');
						
						formData.isVolteSub = formData.imsi == 'VOLTE' ? 'Y' : 'N';
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
					},
				    success: function(data) {
				    	if(data) {
				    		var $container = $('div#CONT0019SearchResult');
				    		$container.find('textArea').html(data);
				    		$container.show();
			    		}
				    }
				}
				$.formAjax(settings);
			}
			
			/* Inner Method
			 ========================================= */
			var getMsisdn = function () {
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/flow/content/get-cont-0019-msisdn.action',
					dataType: 'html',
					dataProvider: function() {
						var orderMId = $('div#buzFlow').attr('orderMId');
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(orderMId);
					},
				    success: function(data) {
				    	if(data) {
				    		$('div#CONT0019Form').find('input[name=msisdn]').val(data);
			    		}
				    }
				}
				$.formAjax(settings);
			}
		
			return {
				init: init,
				ajaxApnIpResult: ajaxApnIpResult
			}
		}
	);
})()