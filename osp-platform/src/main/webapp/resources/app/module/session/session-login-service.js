(function () {
	'use strict';
	define('serviceLoginService', function() {
		var initialize = function() {
			// Pass in our Router module and call it's initialize function
			console.log("===============================================================");
			console.log("initialize");
			console.log("===============================================================");
		}
	
		return {
			initialize: initialize
		};
	});
})();
