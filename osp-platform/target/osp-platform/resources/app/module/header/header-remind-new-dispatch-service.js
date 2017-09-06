(function() {
	define('header-remind-new-dispatch-service', ['flip'], 
		function($) {
			var remindNewDispatch = function() {
				var settings = {
					httpMethod: 'GET', 
					URL: contextPath + '/workshop/personal/get-remind-flag.action', 
					dataType: 'html',
					showWaitDialog: false,
				    success: function(data) {

				    	if ($.trim(data) == 'true') {
							$.gritter.add({
				                title: '',
				                text: '<center>您有新派件</center>',
				                class_name: 'gritter-light'
				            });
				    	}
				    	
				    }
				}
			
				$.formAjax(settings);
			}
		
			return {
				remindNewDispatch: remindNewDispatch
			}
		}
	);
})()