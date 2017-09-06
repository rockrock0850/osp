(function() {
	var protocol = window.location.protocol;
	var host = location.hostname;
	var port = location.port;
	var hostNameWithPort = protocol + "//" + host + ":" + port;

	var context = hostNameWithPort +"/assets";
	var repository = context + "/repository";

	var versionStr = "1.0.0";

	// JavaScript Library version
	var requireJsTextVersion = "2.0.5";
	var angularUiVersion = "0.4.0";
	var bootstrapDropDownVersion = "0.0.9";
	var thunderVersion = "1.0.0";
    var jqueryVersion = "1.8.3";
	var jqueryUiVersion = "1.9.2";
	var angularVersion = "1.0.5";
	var bootstrapVersion = "2.3.1";
	var jqueryUniformVersion = "1.7.5";
	var jqueryBlockuiVersion = "2.53";
    var fancyboxVersion = "2.1.3";
	var gritterVersion = "1.7.4";
	var tooleBtnVersion = "2.8";
	var datepickVersion = "0.0.9";
	var timepickVersion = "0.0.9";
	var daterangepickVersion = "1.0";
	var angularUiBootStrapVersion = "0.2.0";
	var respondVersion = "1.0.0";
	var ngTableVersion = "0.0.9";
	var bootboxVersion = "3.3.0";
	var boottreeVersion = "0.3";
	var backstretchVersion = "2.0.3";
	var bootstrapfileuploadVersion = "0.0.9";
    var ajaxfileuploadVersion = "1.0.0";
    require.config({
        paths: {
            text: repository + '/requirejs-text/' + requireJsTextVersion + '/text',
            angularUi: repository + '/angular-ui/' + angularUiVersion + '/angular-ui.min',
            bootstrapDropdown: repository + '/twitter-bootstrap-hover-dropdown/' +  bootstrapDropDownVersion + '/twitter-bootstrap-hover-dropdown',
            thunder: repository + '/thunder/' + thunderVersion + '/thunder',
            jquery: repository + '/jquery/' + jqueryVersion + '/jquery-1.8.3.min',
            jqueryUi: repository + '/jquery-ui/' + jqueryUiVersion + '/jquery-ui-1.9.2.custom.min',
            angular: repository + '/angular/' + angularVersion + '/angular.min',
            bootstrap: repository + '/bootstrap/' + bootstrapVersion + '/js/bootstrap.min',
            jqueryUniform: repository + '/uniform/' + jqueryUniformVersion + '/jquery.uniform.min',
            jqueryBlockui: repository + '/jquery.blockui/' + jqueryBlockuiVersion + '/jquery.blockui',
            fancybox: repository + '/jquery.fancybox/' + fancyboxVersion + '/source/jquery.fancybox.pack',
            gritter: repository + '/jquery.gritter/' + gritterVersion + '/js/jquery.gritter.min',
            toggleButton: repository + '/bootstrap-toggle-buttons/'+ tooleBtnVersion + '/static/js/jquery.toggle.buttons',
            datepick: repository + '/bootstrap-datepicker/'+ datepickVersion +'/js/bootstrap-datepicker',
            timepick: repository + '/bootstrap-timepicker/'+ timepickVersion + '/js/bootstrap-timepicker',
            daterangepick: repository + '/bootstrap-daterangepicker/' + daterangepickVersion + '/daterangepicker',
            datee: repository + '/bootstrap-daterangepicker/' + daterangepickVersion + '/date',
            angularUiBootstrap: repository + '/angular-bootstrap/' + angularUiBootStrapVersion + '/ui-bootstrap-tpls',
            respond: repository + '/thunder/' + respondVersion + '/js/respond',
			ngTable: repository + '/angular-ngTable/' + ngTableVersion + '/ng-table',
			bootbox: repository + '/bootbox/' + bootboxVersion + '/bootbox',
			boottree: repository + '/bootstrap-tree/' + boottreeVersion + '/bootstrap-tree/js/bootstrap-tree',
			backstretch : repository + '/jquery.backstretch/' + backstretchVersion + '/jquery.backstretch.min',
			bootstrapfileupload : repository + '/bootstrap-fileupload/' + bootstrapfileuploadVersion + '/bootstrap-fileupload',
			ajaxfileupload : repository + '/ajaxfileupload/' + ajaxfileuploadVersion + '/ajaxfileupload'
        },
        shim: {
            angular: {
                deps: ['jquery'],
                exports: 'angular'
            },
            jqueryUi: {
                deps: ['jquery']
            },
            jqueryBlockui: {
                deps: ['jqueryUi']
            },
            bootstrap: {
                deps: ['jquery', 'jqueryUi'],
                exports: 'bootstrap'
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
            datepick: {
                deps: ['jquery', 'jqueryUi']
            },
            daterangepick: {
                deps: ['jquery', 'datee']
            },
            timepick: {
                deps: ['jquery']
            },
            angularUi: {
                deps: ['angular'],
                exports: 'angularUi'
            },
            angularUiBootstrap: {
                deps: ['angular'],
                exports: 'angularUiBootstrap'
            },
            bootstrapDropdown: {
                deps: ['jquery'],
                exports: 'bootstrapDropdown'
            },
            jqueryUniform: {
                deps: ['jquery'],
                exports: 'jqueryUniform'
            },
			ngTable: {
				deps: ['angular'],
                exports: 'ngTable'
			},
			backstretch: {
				deps: ['jquery'],
                exports: 'backstretch'
			},
			thunder: {
                deps: ['jquery', 'angular', 'datepick',
                       'daterangepick', 'timepick',
                       'jqueryBlockui', 'bootstrap',
                       'fancybox', 'gritter', 'ngTable',
					   'backstretch'
                ],
                exports: 'thunder'
            },
            bootbox: {
                deps: ['jquery'],
                exports: 'bootbox'
            },
			boottree: {
                deps: ['jquery','bootstrap']
            },
            bootstrapfileupload : {
            	deps: ['jquery','bootstrap']
            },
            ajaxfileupload : {
            	deps: ['jquery']
            }
        },
        priority: 'jquery',
        urlArgs: versionStr
    });

    define('versionStr', [], function() {
        return versionStr;
    });

	define('flip', ["text", "angularUi", "bootstrapDropdown",
	                      "thunder",  "jquery",  "jqueryUi", "angular",
						  "bootstrap", "jqueryUniform", "jqueryBlockui",
						  "fancybox", "gritter", "toggleButton", "datepick",
						  "timepick", "daterangepick",  "datee", "angularUiBootstrap",
						  "respond", "bootbox", "boottree", "backstretch", "bootstrapfileupload",
						  "ajaxfileupload"], function() {
        return this;
    });
}).call(this);