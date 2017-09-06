require(['flip', 'form2object', 'common-util', 'sessionlogin' ], 
	function () {
		// Detect Pressing Enter
		$(document).keypress(function(event) {
			var keycode = event.keyCode || event.which;
		
			if (keycode == 13) {
				$("button#loginBtn").trigger('click');
			}
		});
	
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