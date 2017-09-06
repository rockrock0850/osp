require(['flip', 'CONT0024-service', 'button'], 
	function (flip, service) {
		service.init();

	}
);

(function() {
	define('CONT0024-service', ['flip'], 
		function() {
			var init = function() {
				// 由DB取回資料
				var orderMId = $('div#buzFlow').attr('orderMId');

				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/flow/content/get-order-main-info.action?orderMId=' + orderMId,
					dataType: 'html',
				    success: function(data) {
				    	if(data) {
				    		data = JSON.parse(data);

				    		var noticeStatus = data.isNoticeSales == 'Y' ? true : false;

				    		$('input[name=isNoticeSales]').prop("checked", noticeStatus);
				    	}
				    }
				}
				$.formAjax(settings);
			}
			
			var getSalesId = function () {
				var salesId = '';
				var ivrCode = $('form#modifyForm').find('input[name=ivrCode]').val();
				var reqData = {
						'ivrCode': ivrCode
				};
				
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/flow/content/get-sales-id.action', 
					dataType: 'html',
					dataProvider: function() {
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(reqData));
				    }, 
				    success: function(data) {
				    	if(data) {
				    		salesId = data;
				    	}
				    }
				}
				$.formAjax(settings);
				
				return salesId;
			}
		
			return {
				init: init,
				getSalesId: getSalesId
			}
		}
	);
})()