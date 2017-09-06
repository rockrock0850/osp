require(['flip', 'form2object', 'common-util', 'sessionlogin' ], 
	function () {
		$("button#loginBtn").on('click', function(e) {
			var settings = {
					httpMethod : 'POST',
					dataAreaId : "loginform",
					action : contextPath + '/session/excute-login.action',
					dataProvider : function(dataAreaId) {
						var params = window.form2object(dataAreaId);
						
						return params;
				    }
			}
				
			$.formSubmit(settings);
		});
	});