require([ 'flip', 'common-util', ], 
		function() {
			console.log("===============================================");
			console.log("載入 menu-main.js");
			console.log("===============================================");
	
			$('[name^="MENU"]').on('click', function(e) {
				var menuLink = $(this).attr("menuLink");
				var menuId = $(this).attr("menuId");
			
				if (menuLink == null || menuLink == "" || menuLink == 'undefined') {
					return;
				}
				
				var settings = {
						httpMethod : 'GET',
						action : menuLink,
						dataProvider : function() {
							var params = {};
							params.menuId = menuId;
							
							return params;
					    }
				}
					
				$.formSubmit(settings);
			});
		});