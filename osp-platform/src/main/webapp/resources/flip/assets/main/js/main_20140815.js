(function() {
	'use strict';
	var flipDomain = document.getElementById('flip-require').getAttribute('flip-domain'),
		repository = flipDomain + '/repository';

	// JavaScript Library version
	var requireJsTextVersion = "2.0.5",
		angularUiVersion = "0.4.0",
		bootstrapDropDownVersion = "0.0.9",
		thunderVersion = "1.0.1",
		jqueryVersion = "1.8.3",
		jqueryUiVersion = "1.9.2",
		angularVersion = "1.2.20",
		bootstrapVersion = "2.3.1",
		jqueryUniformVersion = "1.7.5",
		jqueryBlockuiVersion = "2.53",
		fancyboxVersion = "2.1.3",
		gritterVersion = "1.7.4",
		tooleBtnVersion = "2.8",
		datepickVersion = "0.0.9",
		timepickVersion = "0.0.9",
		daterangepickVersion = "1.0",
		angularUiBootStrapVersion = "0.6.0",
		respondVersion = "1.0.0",
		ngTableVersion = "0.0.9",
		bootboxVersion = "3.3.0",
		boottreeVersion = "0.3",
		backstretchVersion = "2.0.3",
		bootstrapfileuploadVersion = "0.0.9",
		ajaxfileuploadVersion = "1.0.0",
		jquerySidrVersion = "1.1.1",
		select2Version = '3.4.2',
		angularUiSelect2Version = '0.0.9',
		jqueryCookieVersion = '1.3',
		socketIOVersion = '1.0.6',
		jqueryGanttVersion = '1.0.0',
		ganttDirectiveVersion = '1.0.0',
		ngGridVersion = '2.0.11',
		dynamicFormsVersion = '0.0.2',
		jqueryDataTablesVersion = '1.9.4',
		DTBootstrapVersion = '1.9.4',
		angularStrapVersion = '0.7.7',
		requireConfig = {
			paths: {
				text: repository + '/requirejs-text/' + requireJsTextVersion + '/text',
				angularUi: repository + '/angular-ui/' + angularUiVersion + '/angular-ui.min',
				bootstrapDropdown: repository + '/twitter-bootstrap-hover-dropdown/' + bootstrapDropDownVersion + '/twitter-bootstrap-hover-dropdown',
				thunder: repository + '/thunder/' + thunderVersion + '/thunder.min',
				jquery: repository + '/jquery/' + jqueryVersion + '/jquery-1.8.3.min',
				jqueryUi: repository + '/jquery-ui/' + jqueryUiVersion + '/jquery-ui-1.9.2.custom.min',
				angular: repository + '/angular/' + angularVersion + '/angular.min',
				angularRoute: repository + '/angular/' + angularVersion + '/angular-route.min',
				bootstrap: repository + '/bootstrap/' + bootstrapVersion + '/js/bootstrap.min',
				jqueryUniform: repository + '/uniform/' + jqueryUniformVersion + '/jquery.uniform.min',
				jqueryBlockui: repository + '/jquery.blockui/' + jqueryBlockuiVersion + '/jquery.blockui',
				fancybox: repository + '/jquery.fancybox/' + fancyboxVersion + '/source/jquery.fancybox.pack',
				gritter: repository + '/jquery.gritter/' + gritterVersion + '/js/jquery.gritter.min',
				toggleButton: repository + '/bootstrap-toggle-buttons/' + tooleBtnVersion + '/static/js/jquery.toggle.buttons',
				datepick: repository + '/bootstrap-datepicker/' + datepickVersion + '/js/bootstrap-datepicker',
				timepick: repository + '/bootstrap-timepicker/' + timepickVersion + '/js/bootstrap-timepicker',
				daterangepick: repository + '/bootstrap-daterangepicker/' + daterangepickVersion + '/daterangepicker',
				datee: repository + '/bootstrap-daterangepicker/' + daterangepickVersion + '/date',
				angularUiBootstrap: repository + '/angular-bootstrap/' + angularUiBootStrapVersion + '/ui-bootstrap-tpls',
				respond: repository + '/thunder/' + respondVersion + '/js/respond',
				ngTable: repository + '/angular-ngTable/' + ngTableVersion + '/ng-table',
				bootbox: repository + '/bootbox/' + bootboxVersion + '/bootbox',
				boottree: repository + '/bootstrap-tree/' + boottreeVersion + '/bootstrap-tree/js/bootstrap-tree',
				backstretch: repository + '/jquery.backstretch/' + backstretchVersion + '/jquery.backstretch.min',
				bootstrapfileupload: repository + '/bootstrap-fileupload/' + bootstrapfileuploadVersion + '/bootstrap-fileupload',
				ajaxfileupload: repository + '/ajaxfileupload/' + ajaxfileuploadVersion + '/ajaxfileupload',
				jquerySidr: repository + '/jquery.sidr.min/' + jquerySidrVersion + '/jquery.sidr.min',
				select2: repository + '/select2/' + select2Version + '/select2.min',
				angularUiSelect2: repository + '/ui-select2/' + angularUiSelect2Version + '/select2',
				jqueryCookie: repository + '/jquery.cookie/' + jqueryCookieVersion + '/jquery.cookie',
				socketIO: repository + '/socket.io/' + socketIOVersion + '/socket.io.min',
				jqueryGantt: repository + '/jquery-gantt/' + jqueryGanttVersion + '/jquery.fn.gantt',
				ganttDirective: repository + '/jquery-gantt/' + ganttDirectiveVersion + '/angular-plugin',
				ngGrid: repository + '/ng-grid/' + ngGridVersion + '/ng-grid.min',
				dynamicForms: repository + '/dynamic-forms/' + dynamicFormsVersion + '/dynamic-forms',
				jqueryDataTables: repository + '/jquery.dataTables/' + jqueryDataTablesVersion + '/jquery.dataTables',
				DTBootstrap: repository + '/jquery.dataTables/' + DTBootstrapVersion + '/DT_bootstrap',
				angularStrap: repository + '/angularStrap/' + angularStrapVersion + '/angular-strap.min'
			},
			shim: {
				angular: {
					deps: ['jquery'],
					exports: 'angular'
				},
				angularUi: {
					deps: ['angular'],
					exports: 'angularUi'
				},
				angularUiBootstrap: {
					deps: ['angular', 'jquery']
				},
				angularRoute: {
					deps: ['angular']
				},
				jqueryUi: {
					deps: ['jquery']
				},
				jqueryUniform: {
					deps: ['jquery']
				},
				select2: {
					deps: ['jquery']
				},
				jqueryCookie: {
					deps: ['jquery']
				},
				angularUiSelect2: {
					deps: ['angular', 'select2']
				},
				bootstrap: {
					deps: ['jquery', 'jqueryUi'],
					exports: 'bootstrap'
				},
				datepick: {
					deps: ['jqueryUi']
				},
				daterangepick: {
					deps: ['jquery', 'datee']
				},
				timepick: {
					deps: ['jquery']
				},
				jqueryBlockui: {
					deps: ['jqueryUi']
				},
				fancybox: {
					deps: ['jquery']
				},
				gritter: {
					deps: ['jquery']
				},
				toggleButton: {
					deps: ['jquery']
				},
				bootstrapDropdown: {
					deps: ['jquery'],
					exports: 'bootstrapDropdown'
				},
				thunder: {
					deps: ['jquery', 'angular',
						'jqueryBlockui', 'bootstrap'
					],
					exports: 'thunder'
				},
				socketIO: {
					exports: 'socketIO'
				},
				jqueryGantt: {
					deps: ['jquery'],
				},
				ganttDirective: {
					deps: ['jqueryGantt']
				},
				ngGrid: {
					deps: ['jquery', 'angular']
				},
				dynamicForms: {
					deps: ['angular']
				},
				jqueryDataTables: {
					deps: ['jquery']
				},
				DTBootstrap: {
					deps: ['jqueryDataTables']
				},
				angularStrap: {
					deps: ['bootstrap', 'datepick']
				}
			},
			priority: 'jquery',
		};

	require.config(requireConfig);

	define('flipDomain', [], function() {
		return flipDomain;
	});
	define('flip', ['thunder'], function() {
		var v = null;
		if(jQuery.browser.msie && '789'.indexOf (v = jQuery.browser.version.substr(0, 1)) !== -1 ) {
			jQuery('html').addClass('ie' + v);
		}
	});
}).call(this);