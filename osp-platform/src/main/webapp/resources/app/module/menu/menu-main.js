require([ 'menu-service', 'flip', 'adapterbrowser', 'common-util', ], 
		function(menu) {
			console.log("===============================================");
			console.log("載入 menu-main.js");
			console.log("===============================================");
	
			$(document).ready(function() {
				menu.locate();
			});
			
			$('[name^="MENU"]').on('click', function(e) {
				var menuLink = $(this).attr("menuLink");
				var menuId = $(this).attr("menuId");
				var linkType = $(this).attr("linkType");
			
				if (menuLink == null || menuLink == "" || menuLink == 'undefined') {
					return;
				}
					
				if (linkType == "POP") {
					var urlDecodeFlag = "true";
					var singleFlag = "false";
					var openBrowser = "IE";
					
					doOpenBrowser(openBrowser, menuLink, urlDecodeFlag, singleFlag);
				} else {
					var settings = {
						httpMethod : 'GET',
						action : contextPath + "/console-panel.action",
						dataProvider : function() {
							var params = {};
								params.menuId = menuId;
								
								return params;
						}
					}
					
					$.formSubmit(settings);
				}
			});
		});

(function() {
	define('menu-service', [ 'flip' ], function() {
		var locate = function() {
			var $tabContainer = $("div.sidebar-tabs");
			var $tabNav = $tabContainer.find("ul.nav");
			var $tabContent = $tabContainer.find("div.tab-content");
			var breadcrumbId = $("ul.osp_breadcrumb li:nth-child(2)").attr("breadcrumbId");
			
			if (breadcrumbId) {
				var $contentLi = $tabContent.find("li[menuId='" + breadcrumbId + "']");
				var $tabPane = $contentLi.parents("div.tab-pane");
				var tabIndex = $tabContent.find("div").index($tabPane);
				var $navLi = $tabNav.find("li").eq(tabIndex);
				
				$contentLi.addClass("active");
				$tabPane.addClass("active");
				$navLi.addClass("active");
			} else {
				$tabNav.find("li").eq(0).addClass("active");
				$tabContent.find("div.tab-content").eq(0).addClass("active");
			}
		}
		
		return {
			locate : locate
		};
	});
})();