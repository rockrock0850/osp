(function() {
	'use strict';

	if (window && !window.defaulContentPath) {
		window.defaulContentPath = "http://localhost:8080/osp-platform";
	}

	var rootContext = "http://localhost:8080/osp-platform";
	var flipAsset = document.getElementById('flip-require').getAttribute('flip-asset'),
		// when using requirejs to load javascript, recommended to append this to
		// the url args to avoid cache
		versionStr = 'v=alpha0.0',
		sysContext = '/osp-platform',
		// framework front-end log configuration
		logConfig = {
			/* required, default 'unknown' */
			systemName: 'osp-platform',

			/* required, default '/unknown' */
			systemContextPath: '/osp-platform'
				/* default '' */
				//, logAggregatorHost : ''

			/* get client info when method get, default '/ClientLogServlet' */
			,
//			logInfoAction: '//flip-test.fareastone.com.tw/CLA-ODC/ClientInfoServlet'

			/* write to server when method post, default '/ClientLogServlet' */
//			, logReceiverAction: 'http://flip-test.fareastone.com.tw/CLA-ODC/CLA.png'
//			, logReceiverAction: '//ncpuat.fareastone.com.tw/NCP/ClientLogServlet'

			/* console, default 'OFF', 'DEBUG' */
//			, consoleAppenderStatus: 'ON'
//			, logLevel: 'DEBUG'
			// showAnaLog: 'OFF'

			/* write to server, default 'ON', 'DEBUG' */
//			, logCenterAppenderStatus: 'ON'
//			, logAnalyzeLevel : 'DEBUG'

			/* the buffer size, default 100 */
//			, logDumpSize: 100
		};

	//setting baseUrl, flip path, urlArgs to requirejs
	require.config({
		baseUrl: '',
		paths: {			
			flip: flipAsset + '/main/js/main_docs',
			angularRoute: flipAsset + '/repository/angular/1.2.28/angular-route.min',
			angularSanitize: flipAsset + '/repository/angular/1.2.28/angular-sanitize.min',
			slimscroll: flipAsset + '/repository/jquery-slimscroll/1.0.6/jquery.slimscroll.min',
			app: rootContext + '/resources/app/main/js/app',
			uiapp: rootContext + '/resources/app/common/ui-app',
			cookie: flipAsset + '/repository/jquery.cookie/1.3/jquery.cookie'
		},
		shim: {
			angular: {
				deps: ['jquery'],
				exports: 'angular'
			},
			angularRoute: {
				deps: ['flip']
			},
			angularSanitize: {
				deps: ['flip']
			},
			slimscroll: {
				deps: ['flip']
			},
			uiapp: {
				deps: ['flip', 'slimscroll'],
				exports: 'uiapp'
			},
			cookie: {
				deps: ['flip'],
				exports: 'cookie'
			}
		},
		urlArgs: versionStr
	});

	//how to pass these configurations to thunder
	define('initConfig', [], function() {
		return {
			'logConfig': logConfig,
			'versionStr': versionStr,
			'flipAsset': flipAsset
		};
	});	

	//nested require, first one only require 'flip'
	require(['flip', 'angularRoute', 'angularSanitize'], function() {
		//and other dependencies put here
		require(['app', 'thunderUi', 'thunderWizard', 'slimscroll', 'uiapp', 'cookie'], function(app, thunderUi, thunderWizard, slimscroll, uiapp, cookie) {			
			angular.bootstrap(document, ['app']);					
		});
	});
})();