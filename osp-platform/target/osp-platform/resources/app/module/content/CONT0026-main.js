require(['flip', 'CONT0026-service', 'button'], 
	function (flip, service) {
		service.init();
	}
);

(function() {
	define('CONT0026-service', ['flip'], 
		function() {
			var init = function() {
				var orderMId = $('div#buzFlow').attr('orderMId');
				var settings = {
					dataType: 'html',
					httpMethod: 'POST',
					URL: contextPath + '/flow/content/get-assign-level-info.action',
					dataProvider: function() {
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(orderMId);
				    }, 
					success: function(data){
						if(data) {
							var assignLayer = JSON.parse(data);
							var content = '<tr>';
							content += '<td>' + assignLayer.level + '</td>';
							content += '<td>' + assignLayer.empId + '</td>';
							content += '<td>' + assignLayer.name + '</td>';
							$('table').find('tbody').empty().append(content);
						}
					}
				};
				$.formAjax(settings);
			}
			
			return {
				init: init
			}
		}
	);
})()