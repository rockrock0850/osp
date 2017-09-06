require([ 'flip', 'console-service', 'cookie', 'uiapp', 'form2object', 'common-util', 'gritter', 'bootbox' ], 		
    function(flip, service) {
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
			
			// 檢核agent
			service.agentValidate();
		});
		
		var remindNewDispatch = function() {
			var settings = {
				httpMethod: 'GET', 
				URL: contextPath + '/get-remind-flag.action', 
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

(function() {
	define('console-service', ['flip'], 
		function(flip) {
			var agentValidate = function() {
				var settings = {
					httpMethod: 'GET', 
					URL: contextPath + '/session/agent-validate-warning.action', 
					dataType: 'html',
					showWaitDialog: false,
				    success: function(data) {
				    	if (data) {
				    		data = $.parseJSON(data);
					    	
					    	if (data.message) {
								require(['bootbox'], function(bootbox) {
									$('span[id="agentWarningMsg"]').empty().html(data.message);
										
									var $failDiv = $('div[id="warningBootBox"]');	
										bootbox.alert({
										backdrop : true,
										closeButton : false,
										message: $failDiv.html(),
										buttons: {//更改按鈕名稱"OK"為中文
											ok: {
												label: "確定",
											}
										}
									});
								});
					    	}
				    	}
				    }
				}
	
				$.formAjax(settings);
			}

			return {
				agentValidate: agentValidate
			}
		}
	);
})()