require([ 'flip', 'cookie', 'uiapp', 'form2object', 'common-util', 'gritter' ], 		
    function() {
		$(document).ready(function() {
			console.log("===============================================");
			console.log("載入 console-main.js");
			console.log("===============================================");
			
			App.init(); // init layout and core plugins
			
			var menuLink = $("div#pannelContent").attr("menuLink");
			var menuId = $("div#pannelContent").attr("menuId");
			
			var settings = {
					httpMethod: 'GET', 
					URL: contextPath + menuLink, 
					dataType: 'html',
					dataProvider: function() {
						var params = {};
						params.menuId = menuId;
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(params));
				    },
				    success: function(data) {
				    	$("div#pannelContent").empty().html(data);
				    }
				}
				
			$.formAjax(settings);
			
			// 新進件通知
			var timeInterval = 60000;
			
			window.setInterval(function() {
				remindNewDispatch();
			}, timeInterval);
			
		});
		
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
		
	}
);
