(function() {
    'use strict';
    var flipAsset = document.getElementById('flip-require').getAttribute('flip-asset'),
        repository = flipAsset + '/repository';

	// JavaScript Library version
	// thunder core
    var v_jquery = '1.11.1',
        v_jqueryUi = '1.10.3',
        v_jqueryBlockUi = '2.66.0',
        v_bootstrap = '3.2.0',
        v_angular = '1.2.26',
        v_thunder = '1.1.1',
		//thunder.ui
		v_jqueryMultifile = '2.0.3',
		v_select2 = '3.5.1',
		v_jqueryInputmask = '3.1.3',
		v_fancybox = '2.1.5',
		v_gritter = '1.7.4',
		v_datepick = '1.2.0',
		//thunder.socket
		v_socketIO = '1.0.6';
    require.config({
        paths: {
			//thunder core
			jquery: repository + '/jquery/' + v_jquery + '/jquery-1.11.1.min',
			jqueryUi: repository + '/jquery-ui/' + v_jqueryUi + '/jquery-ui.min',
			jqueryBlockui: repository + '/jquery.blockui/' + v_jqueryBlockUi + '/jquery.blockUI',
			bootstrap: repository + '/bootstrap/' + v_bootstrap + '/js/bootstrap.min',
			angular: repository + '/angular/' + v_angular + '/angular.min',
			thunder: repository + '/thunder/' + v_thunder + '/thunder.min',

			//thunder.ui
			jqueryMultifile: repository + '/jquery.multifile/' + v_jqueryMultifile + '/jQuery.MultiFile.min',
			select2: repository + '/select2/' + v_select2 + '/select2.min',
			select2Lg: repository + '/select2/' + v_select2 + '/select2_locale_zh-TW',
			jqueryInputmask: repository + '/jquery.inputmask/' + v_jqueryInputmask + '/jquery.inputmask',
			jqueryInputmaskExtensions: repository + '/jquery.inputmask/' + v_jqueryInputmask + '/jquery.inputmask.extensions',
			jqueryInputmaskDateExtensions: repository + '/jquery.inputmask/' + v_jqueryInputmask + '/jquery.inputmask.date.extensions',
			jqueryInputmaskNumericExtensions: repository + '/jquery.inputmask/' + v_jqueryInputmask + '/jquery.inputmask.numeric.extensions',
			jqueryInputmaskPhoneExtensions: repository + '/jquery.inputmask/' + v_jqueryInputmask + '/jquery.inputmask.phone.extensions',
			jqueryInputmaskRegexExtensions: repository + '/jquery.inputmask/' + v_jqueryInputmask + '/jquery.inputmask.regex.extensions',
			fancybox: repository + '/jquery.fancybox/' + v_fancybox + '/source/jquery.fancybox.pack',
			fancyboxMousewheel: repository + '/jquery.fancybox/' + v_fancybox + '/lib/jquery.mousewheel-3.0.6.pack',
			gritter: repository + '/jquery.gritter/' + v_gritter + '/js/jquery.gritter.min',
			datepicker: repository + '/bootstrap-datepicker/' + v_datepick + '/js/bootstrap-datepicker',
			datepickerLg: repository + '/bootstrap-datepicker/' + v_datepick + '/js/locales/bootstrap-datepicker.zh-TW',
			'thunderUi': repository + '/thunder/' + v_thunder + '/thunder.ui.min',
			
			//thunder.socket
			socketIO: repository + '/socket.io/' + v_socketIO + '/socket.io.min',
			thunderSocket: repository + '/thunder/' + v_thunder + '/thunder.socket',
			
			//others
			ngRoute: repository + '/angular/' + v_angular + '/angular-route.min'
        },
        shim: {
			//thunder core
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
			angular: {
				deps: ['jquery'],
				exports: 'angular'
			},
			thunder: {
				deps: ['jquery', 'angular',
					'jqueryBlockui', 'bootstrap'
				],
				exports: 'thunder'
			},
			//thunder.ui
			jqueryMultifile: {
				deps: ['jquery']
			},
			select2: {
				deps: ['jquery']
			},
			select2Lg: {
				deps: ['select2']
			},
			jqueryInputmask: {
				deps: ['jquery']
			},
			jqueryInputmaskExtensions: {
				deps: ['jqueryInputmask']
			},
			jqueryInputmaskDateExtensions: {
				deps: ['jqueryInputmaskExtensions']
			},
			jqueryInputmaskNumericExtensions: {
				deps: ['jqueryInputmaskExtensions']
			},
			jqueryInputmaskPhoneExtensions: {
				deps: ['jqueryInputmaskExtensions']
			},
			jqueryInputmaskRegexExtensions: {
				deps: ['jqueryInputmaskExtensions']
			},
			fancybox: {
				deps: ['jquery']
			},
			fancyboxMousewheel: {
				deps: ['jquery']
			},
			gritter: {
				deps: ['jquery']
			},
			datepicker: {
				deps: ['bootstrap', 'jquery']
			},
			datepickerLg: {
				deps: ['bootstrap', 'jquery', 'datepicker']
			},
			'thunderUi': {
				deps: ['thunder', 'jqueryMultifile', 'select2', 'select2Lg',
					'jqueryInputmask', 'jqueryInputmaskExtensions', 'jqueryInputmaskDateExtensions',
					'jqueryInputmaskNumericExtensions', 'jqueryInputmaskPhoneExtensions', 'jqueryInputmaskRegexExtensions',
					'fancybox', 'fancyboxMousewheel', 'gritter', 'datepicker', 'datepickerLg'
				],
				exports: 'thunderUi'
			},
			//thunder.socket
			thunderSocket: {
				deps: ['socketIO']
			},
			
			//others
			ngRoute: {
				deps: ['angular']
			}
        }
    });

    define('flip', ['thunder'], function() {});
})();