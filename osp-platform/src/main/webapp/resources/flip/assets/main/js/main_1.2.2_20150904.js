(function() {	'use strict';	var flipAsset = document.getElementById('flip-require').getAttribute('flip-asset'),		repository = flipAsset + '/repository';	require.config({
	"paths": {
		"thunder": repository + "/thunder/1.2.2/thunder.min",
		"jquery": repository + "/jquery/1.11.1/jquery-1.11.1.min",
		"jqueryBlockui": repository + "/jquery.blockui/2.66.0/jquery.blockUI",
		"angular": repository + "/angular/1.2.28/angular.min",
		"bootstrap": repository + "/bootstrap/3.2.0/js/bootstrap.min",
		"jqueryUi": repository + "/jquery-ui/1.11.2/jquery-ui-custom.min",
		"jqueryMultifile": repository + "/jquery.multifile/2.0.3/jQuery.MultiFile.min",
		"select2": repository + "/select2/3.5.1/select2.min",
		"select2Lg": repository + "/select2/3.5.1/select2_locale_zh-TW",
		"jqueryInputmask": repository + "/jquery.inputmask/3.1.3/jquery.inputmask.bundle",
		"fancybox": repository + "/jquery.fancybox/2.1.5/source/jquery.fancybox.pack",
		"gritter": repository + "/jquery.gritter/1.7.4/js/jquery.gritter.min",
		"datepicker": repository + "/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker",
		"datepickerLg": repository + "/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-TW",
		"thunderUi": repository + "/thunder/1.2.2/thunder.ui.min",
		"thunderWizard": repository + "/thunder/1.2.2/thunder.wizard.min",
		"socketIO": repository + "/socket.io/1.0.6/socket.io.min",
		"thunderSocket": repository + "/thunder/1.2.2/thunder.socket.min",
		"ngRoute": repository + "/angular/1.2.28/angular-route.min",
		"ngMock": repository + "/angular/1.2.28/angular-mocks",
		"lodash": repository + "/lodash/2.4.1/lodash.compat.min",
		"thunderWt": repository + "/thunder/1.2.2/thunder.wt.min"
	},
	"shim": {
		"thunder": {
			"deps": [
				"jquery",
				"angular",
				"jqueryBlockui",
				"bootstrap",
				"lodash"
			]
		},
		"jquery": {
			"deps": []
		},
		"jqueryBlockui": {
			"deps": [
				"jqueryUi"
			]
		},
		"angular": {
			"deps": [
				"jquery"
			]
		},
		"bootstrap": {
			"deps": [
				"jquery",
				"jqueryUi"
			]
		},
		"jqueryUi": {
			"deps": [
				"jquery"
			]
		},
		"jqueryMultifile": {
			"deps": [
				"jquery"
			]
		},
		"select2": {
			"deps": [
				"jquery"
			]
		},
		"select2Lg": {
			"deps": [
				"select2"
			]
		},
		"jqueryInputmask": {
			"deps": [
				"jquery"
			]
		},
		"fancybox": {
			"deps": [
				"jquery"
			]
		},
		"gritter": {
			"deps": [
				"jquery"
			]
		},
		"datepicker": {
			"deps": [
				"jquery",
				"bootstrap"
			]
		},
		"datepickerLg": {
			"deps": [
				"datepicker"
			]
		},
		"thunderUi": {
			"deps": [
				"thunder",
				"jqueryMultifile",
				"select2",
				"select2Lg",
				"jqueryInputmask",
				"fancybox",
				"gritter",
				"datepicker",
				"datepickerLg"
			]
		},
		"thunderWizard": {
			"deps": [
				"thunder"
			]
		},
		"socketIO": {
			"deps": []
		},
		"thunderSocket": {
			"deps": [
				"thunder",
				"socketIO"
			]
		},
		"ngRoute": {
			"deps": [
				"angular"
			]
		},
		"ngMock": {
			"deps": [
				"angular"
			]
		},
		"lodash": {
			"deps": []
		},
		"thunderWt": {
			"deps": [
				"thunder"
			]
		}
	}
});		define('flip', ['thunder'], function() {});})();