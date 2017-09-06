require(['flip'], 
	function () {
		
		// 登出
		$('#logoutLink').unbind('click').on('click', function() {

			var settings = {
					httpMethod : 'GET',
					action : contextPath + '/session/excute-logout.action'
			}
				
			$.formSubmit(settings);
		});
	}
);
