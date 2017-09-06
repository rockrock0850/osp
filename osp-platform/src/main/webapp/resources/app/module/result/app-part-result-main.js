require(['flip', 'angularRoute', 'angularSanitize'], function() {
	require(['thunderUi', 'thunderWizard', 'angularRoute', 
	         'angularSanitize', 'thunder', 'jsonFormatter',
	         'genericDirective', 'genericService'], function() {
			 
			 var $container = $("div#faxServerView");
	
			 angular.bootstrap($container, ['ngRoute', 'ngSanitize', 'thunder', 'thunder.ui', 'thunder.wizard', 
	                               'nspDirective', 'genericServices']);
	});
}); 