(function() {
    'use strict';
    var flipAsset = document.getElementById('flip-require').getAttribute('flip-asset'),
        repository = flipAsset + '/repository';

    var v_jquery = '1.11.1',
        v_jqueryUi = '1.10.3',
        v_jqueryBlockUi = '2.66.0',
        v_bootstrap = '3.2.0',
        v_angular = '1.2.26',
        v_thunder = '1.0.9.dev';

    require.config({
        paths: {
            jquery: repository + '/jquery/' + v_jquery + '/jquery.min',
            jqueryUi: repository + '/jquery-ui/' + v_jqueryUi + '/jquery-ui.min',
            jqueryBlockui: repository + '/jquery.blockui/' + v_jqueryBlockUi + '/jquery.blockui',
            bootstrap: repository + '/bootstrap/' + v_bootstrap + '/js/bootstrap.min',
            angular: repository + '/angular/' + v_angular + '/angular',
            thunder: repository + '/thunder/' + v_thunder + '/thunder.min'
        },
        shim: {
            jqueryUi: {
                deps: ['jquery']
            },
            jqueryBlockui: {
                deps: ['jqueryUi']
            },
            bootstrap: {
                deps: ['jquery', 'jqueryUi']
            },
            angular: {
                deps: ['jquery']
            },
            thunder: {
                deps: ['jquery', 'angular', 'jqueryBlockui', 'bootstrap']
            }
        }
    });

    define('flipAsset', [], function() {
        return flipAsset;
    });
    define('flip', ['thunder'], function() {});
})();