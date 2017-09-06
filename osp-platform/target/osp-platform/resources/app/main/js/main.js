(function() {
	
	'use strict';
	
	var rootPath = contextPath;
	
	var flipAsset = document.getElementById('flip-require').getAttribute('flip-asset'),
	    nsp       = document.getElementById('flip-require').getAttribute('nsp-library'),
    // when using requirejs to load javascript, recommended to append this to
    // the url args to avoid cache
    versionStr = 'v=alpha0.0',
    sysContext = '/NSP2',
    // framework front-end log configuration
    logConfig = {
        /* required, default 'unknown' */
        systemName : 'nsp2',
        
        /* required, default '/unknown' */
        systemContextPath : sysContext
        /* default '' */
        //, logAggregatorHost : ''
        
        /* get client info when method get, default '/ClientLogServlet' */
        //, logInfoAction : '/ClientLogServlet'
        
        /* write to server when method post, default '/ClientLogServlet' */
        //, logReceiverAction : '/ClientLogServlet'
        
        /* console, default 'OFF', 'DEBUG' */
        //, consoleAppenderStatus : 'ON'
        //, logLevel : 'DEBUG'
        
        /* write to server, default 'ON', 'DEBUG' */
        //, logCenterAppenderStatus : 'ON'
        //, logAnalyzeLevel : 'DEBUG'
        
        /* the buffer size, default 100 */
        //, logDumpSize : 10
    }, ssoConfig = {
        /* default 'OFF' */
        //ssoStatus : 'ON',
        
        /* required, default '/unknown' */
        systemContextPath : sysContext
        
        /* default '' */
        //, ssoHost : '',
        
        /* loop to check session expiration time, default '/FETSSO/validTimeCounter.action' */
        //, validateTimeAction: '/FETSSO/validTimeCounter.action'
        
        /* to extend session expiration time, default '/FETSSO/UpdateTimeCounter.action' */
        //, updateTimeAction: '/FETSSO/UpdateTimeCounter.action'
        
        /* retrieve user profile, default '/FETSSO/retvUserProfile.action' */
        //, userProfileAction: '/FETSSO/retvUserProfile.action'
    };
	
	
	require.config({
		"paths": {			
			// NSP main
			"flip": flipAsset + "/main/js/main_docs",
			"angularRoute" : flipAsset + '/repository/angular/1.2.28/angular-route.min',
			"angularSanitize" : flipAsset + '/repository/angular/1.2.28/angular-sanitize.min',
			"genericService" : nsp + '/app/service/generic-service',
			"genericDirective" : nsp + '/app/directive/generic-directive',
			"jsonFormatter" : nsp + "/system-asset/repository/json-formatter/0.3.1/json-formatter.min",
			
			// jQuery
			"cookie": rootPath + "/resources/flip/assets/repository/jquery.cookie/1.3/jquery.cookie", // n
			"slimscroll": rootPath + '/resources/flip/assets/repository/jquery-slimscroll/1.0.6/jquery.slimscroll.min', // n
			"jquery.dataTables": rootPath + "/resources/flip/assets/repository/data-tables-bs4/1.10.12/jquery.dataTables.min", // y
			"DT_bootstrap": rootPath + "/resources/flip/assets/repository/data-tables-bs4/1.10.12/DT_bootstrap", // n
			"jqueryUiDialog": rootPath + "/resources/flip/assets/repository/jquery-ui-dialog/1.11.4/jquery-ui.11.14.dialog",
			"gritter": rootPath + "/resources/flip/assets/repository/jquery.gritter/1.7.4/js/jquery.gritter",
			
			// bootstrap
			"bootbox": rootPath + "/resources/flip/assets/repository/bootbox/4.4.0/bootbox.min", // n
			"daterangepicker": rootPath + "/resources/flip/assets/repository/bootstrap-daterangepicker-bs3/1.2.0/daterangepicker", // n
			"datetimepicker": rootPath + '/resources/flip/assets/repository/bootstrap-datetimepicker/2.0.0/js/bootstrap-datetimepicker', // y
			"moment": rootPath + "/resources/flip/assets/repository/bootstrap-daterangepicker-bs3/1.2.0/moment", // Y
			
			"ajaxfileupload" : rootPath + "/resources/flip/assets/repository/ajaxfileupload/1.0.0/ajaxfileupload", // N
			
			"adapterbrowser" : rootPath + "/resources/app/common/adapter/adapterbrowser",
			
			/*Dialog js */
			"small-modal" : rootPath + "/resources/app/common/component/modal/small-modal",

			/* OPS library */ 
			"uiapp": rootPath + "/resources/app/common/ui-app",
			"button": rootPath + "//resources/app/common/component/flow/button",
			"common-util2": rootPath + "/resources/app/common/common-utils",
			"common-util": rootPath + "/resources/app/common/jquery.fn.common-utils",
			"osp-util": rootPath + "/resources/app/common/osp-util",
			"osp-ui-app": rootPath + "/resources/app/common/osp-ui-app",
			"form-components": rootPath + "/resources/app/common/form-components",
			
			"form2object": rootPath + "/resources/flip/assets/repository/form2object/20100909/form2object",
			"htmlComponet": rootPath + "/resources/app/common/component/html-componet",
			
			/* Ajax 檔案下載 */
			"ajaxfiledownload": rootPath + "/resources/app/common/jquery.fn.filedownload",
			
			/* 測試資料 */
			'table-data': rootPath + "/resources/app/common/table-data",
			
			/* 載入各項功能事件  */
			'psersonal-todo-service': rootPath + "/resources/app/module/workshop/todo/personal/personal-todo-main", 
			"flow-main-service": rootPath + "/resources/app/module/flow/flow-main",
			"sessionlogin": rootPath + "/resources/app/module/session/session-login-service",
			'order-assign-service': rootPath + "/resources/app/module/setting/assign/order-assign-main", 
			'order-dispatch-maintain-service': rootPath + "/resources/app/module/setting/dispatch/order-dispatch-maintain-main",
			'shift-content-service': rootPath + "/resources/app/module/setting/shift/shift-content-main",  
			'shift-type-service': rootPath + "/resources/app/module/setting/shift/shift-type-main", 
			'skill-maintain-service': rootPath + "/resources/app/module/setting/skill/skill-maintain-main",
			'order-type-maintain-service': rootPath + "/resources/app/module/setting/order/order-type-maintain-service",
			'role-maintain-service': rootPath + "/resources/app/module/setting/role/role-maintain-main",  
			'workflow-service': rootPath + "/resources/app/module/work-flow/workflow-service", 
			'building-file-service': rootPath + "/resources/app/module/work-flow/building-file-operation/building-file-service", 
			'query-order-status-service': rootPath + "/resources/app/module/setting/order/service-query-order-main", 
			'personal-order-operate-records-service': rootPath + "/resources/app/module/setting/order/service-personal-order-operate-records-main", 
			'header-remind-new-dispatch-service': rootPath + "/resources/app/module/header/header-remind-new-dispatch-service",
			
			/* Content Service */
			'CONT0013-service': rootPath + "/resources/app/module/content/CONT0013-main",  
			'CONT0014-service': rootPath + "/resources/app/module/content/CONT0014-main",   
			'CONT0019-service': rootPath + "/resources/app/module/content/CONT0019-main",    
			'CONT0022-service': rootPath + "/resources/app/module/content/CONT0022-main",   
			'CONT0023-service': rootPath + "/resources/app/module/content/CONT0023-main",  
			'cont0068-service' : rootPath + "/resources/app/module/content/CONT0068-main",
			'cont0069-service' : rootPath + "/resources/app/module/content/CONT0069-main",
			'cont0070-service' : rootPath + "/resources/app/module/content/CONT0070-main"
		},
		"shim": {
			// NSP
			angular : {
				deps : [ 'flip' ],
				exports : 'angular'
			},
			angularRoute : {
				deps : ['flip'],
				exports: 'angularRoute'
			},
			angularSanitize : {
				deps : ['flip'],
				exports: 'angularSanitize'
			},
			"genericService" : {
				depts: ['flip'],
				exports: "genericService"
			},
			"genericDirective" : {
				depts: ['flip'],
				exports: "genericDirective"
			},
			"jsonFormatter" : {
				depts: ['flip'],
				exports: "jsonFormatter"
			},
			
			"jquery.dataTables": {
				"deps": ["flip"],
		         exports: "jquery.dataTables"
			},
			"DT_bootstrap": {
				"deps": ['jquery.dataTables'],
				exports: "DT_bootstrap"
			},
			"cookie": {
				"deps": ["flip"],
				exports: "cookie"
			},
			"slimscroll":{
				"deps": ["flip"],
				exports: "slimscroll"
			},
			"jqueryUiDialog": {
				"deps": ["flip"],
				exports: "jqueryUiDialog"
			},"gritter": {
				"deps": [ "flip"],
				exports: "gritter"
			},
			
			// bootstrap
			"bootbox": {
				"deps": ["flip"],
				exports: "bootbox"
			},
			"datetimepicker": {
				"deps": ["flip"],
				exports: "datetimepicker"
			},
			"moment": {
				exports: "moment"
			},
			"daterangepicker": {
				"deps": ["flip", "moment"],
				exports: "daterangepicker"
			},
			"ajaxfileupload": {
				"deps": ["flip"],
				exports: "ajaxfileupload"
			},
			"adapterbrowser": {
				"deps": ["flip"],
				exports: "adapterbrowser"
			},
			
			"form2object": {
				exports: "form2object"
			},
			
			"ajaxfiledownlaod": {
				exports: "ajaxfiledownlaod"
			},

			// OSP 
			"uiapp": {
				"deps":["flip"],
				exports: "uiapp"
			},
			"osp-ui-app": {
				"deps":["flip", "osp-util"],
				exports: "osp-ui-app"
			},
			"form-components": {
				"deps":["flip", "datepicker"],
				exports: "form-components"
			},
			"common-util2": {
				"deps": ["flip", "bootbox"],
				exports: "common-util2"
			},
			"common-util": {
				"deps": [ "flip", "jqueryUiDialog", "common-util2"],
				exports: "common-util"
			}			
		},
		deps: [ flipAsset + "/main/js/main_docs.js" ],
		waitSeconds: 15
	});
	
	//how to pass these configurations to thunder
	define('initConfig', [], function() {
		return {
			'logConfig' : logConfig,
			'versionStr' : versionStr,
			'flipAsset': flipAsset,
            'ssoConfig': ssoConfig
		};
	});
	
	require(['flip', 'angularRoute', 'angularSanitize'], function() {
		console.log("===============================================");
		console.log("載入 main.js");
		console.log("===============================================");
		
		// Detect Pressing Enter
		$(document).keypress(function(event) {
			var keycode = event.keyCode || event.which;
			
		    if (keycode == 13) {
		        return;
		    }
		});
		
		require(['thunderUi', 'thunderWizard', 'angularRoute', 
		         'angularSanitize', 'thunder', 'jsonFormatter',
		         'genericDirective', 'genericService'], function() {
		});
	}); 
	
})();