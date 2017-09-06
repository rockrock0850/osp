require(['flip', 'adapterbrowser', 'common-util'], 
	function (flip) {
		$('[name^="IA_BTN"]').unbind().bind("click", function() {
			var link = $(this).attr("link");
		
			var urlDecodeFlag = "true";
			var singleFlag = "false";
			var openBrowser = "IE";
			
			doOpenBrowser(openBrowser, link, urlDecodeFlag, singleFlag);
		});
	}
);
