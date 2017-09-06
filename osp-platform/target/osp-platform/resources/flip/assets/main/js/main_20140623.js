(function() {
	'use strict';
	var flipDomain = document.getElementById('flip-require').getAttribute('flip-domain'),
		itt_rep = flipDomain + '/assets',
		repository = flipDomain + '/repository';

	// JavaScript Library version
	var requireJsTextVersion = "2.0.5",
		angularUiVersion = "0.4.0",
		bootstrapDropDownVersion = "0.0.9",
		thunderVersion = "1.0.1",
		jqueryVersion = "1.8.3",
		jqueryUiVersion = "1.9.2",
		angularVersion = "1.2.19",
		bootstrapVersion = "2.3.1",
		jqueryUniformVersion = "1.7.5",
		jqueryBlockuiVersion = "2.53",
		fancyboxVersion = "2.1.3",
		gritterVersion = "1.7.4",
		tooleBtnVersion = "2.8",
		datepickVersion = "0.0.9",
		timepickVersion = "0.0.9",
		daterangepickVersion = "1.0",
		angularUiBootStrapVersion = "0.2.0",
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
		jqueryCookieVersion = '1.3';

	require.config({
		paths: {
			/*
            //jquery: itt_rep + '/js/jquery/jquery.min',
            jquery: repository + '/jquery/' + jqueryVersion + '/jquery-1.8.3.min',

            //jqueryUi: itt_rep + '/plugins/jquery-slimscroll/jquery-ui-1.9.2.custom.min',
            jqueryUi: repository + '/jquery-ui/' + jqueryUiVersion + '/jquery-ui-1.9.2.custom.min',

            fancybox: itt_rep + 'plugins/fancybox/source/jquery.fancybox.pack', // iframe box, inline box
            gritter: itt_rep + 'plugins/gritter/js/jquery.gritter.min', // gritter
            toggleButton: itt_rep + 'plugins/bootstrap-toggle-buttons/static/js/jquery.toggle.buttons', // toggleButton
            bootstrapDropdown: itt_rep + 'plugins/twitter-bootstrap-hover-dropdown',
            jqueryBlockui: itt_rep + '/plugins/jquery.blockui',
            jqueryUniform: itt_rep + '/plugins/uniform/jquery.uniform.min', // normal checkbox and radio button
            select2: itt_rep + '/plugins/select2/select2.min', //select2
            
            //angular: itt_rep + '/js/angular/angular.min',
            angular: repository + '/angular/' + angularVersion + '/angular.min',

            //angularUi: itt_rep + '/js/angular-ui/build/angular-ui.min',
            angularUi: repository + '/angular-ui/' + angularUiVersion + '/angular-ui.min',

            angularUiBootstrap: itt_rep + '/js/angular-bootstrap/ui-bootstrap-tpls',
            angularUiSelect2: itt_rep + '/js/angular-ui/ui-select2/src/select2',
            
            //bootstrap: itt_rep + '/bootstrap/js/bootstrap.min',
            bootstrap: repository + '/bootstrap/' + bootstrapVersion + '/js/bootstrap.min',
            
            datepick: itt_rep + 'plugins/bootstrap-datepicker/js/bootstrap-datepicker', // datePicker
            timepick: itt_rep + 'plugins/bootstrap-timepicker/js/bootstrap-timepicker', // timePicker
            daterangepick: itt_rep + 'plugins/bootstrap-daterangepicker/daterangepicker', // dateRangePicker
            datee: itt_rep + 'plugins/bootstrap-daterangepicker/date', // for dateRangePicker
            
            //thunder: itt_rep + '/js/thunder/thunder.min',
            thunder: repository + '/thunder/' + thunderVersion + '/thunder',
            
            respond: itt_rep + '/js/respond'*/
			text: repository + '/requirejs-text/' + requireJsTextVersion + '/text',
			angularUi: repository + '/angular-ui/' + angularUiVersion + '/angular-ui.min',
			bootstrapDropdown: repository + '/twitter-bootstrap-hover-dropdown/' + bootstrapDropDownVersion + '/twitter-bootstrap-hover-dropdown',
			thunder: repository + '/thunder/' + thunderVersion + '/thunder',
			jquery: repository + '/jquery/' + jqueryVersion + '/jquery-1.8.3.min',
			jqueryUi: repository + '/jquery-ui/' + jqueryUiVersion + '/jquery-ui-1.9.2.custom.min',
			angular: repository + '/angular/' + angularVersion + '/angular.min',
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
			jqueryCookie: repository + '/jquery.cookie/' + jqueryCookieVersion + '/jquery.cookie'
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
		},
		priority: 'jquery',
	});

	define('flipDomain', [], function(){
		return flipDomain;
	});
	define('flip', ['thunder', 'jqueryUniform', 'angularUiSelect2', 'angularUiBootstrap', 'jqueryCookie'], function() {
		//console.log('test');
		//return this;
	});
}).call(this);