define('app', function() {
	var app = angular.module('app', ['thunder', 'ngRoute']);

	app.controller('containerCtrl', ['$scope', 'loggingService',
		'controllerName', 'eventBusService',
		function($scope, loggingService, controllerName, eventBusService) {
			// loggingService.info(controllerName, 'says hello');
		}
	]);

	app.config(['loggingServiceProvider', '$sceDelegateProvider',
		function(loggingServiceProvider, $sceDelegateProvider) {

			$sceDelegateProvider.resourceUrlWhitelist([
				// Allow same origin resource loads.
				'self',
				// Allow loading from our assets domain.  Notice the difference between * and **.
				// 'http://*.fareastone.com.tw/**',
				// 'http://*.fetnet.net/**',
				// 'https://*.fetnet.net/**'
			]);


		}
	]);

	return app;
});