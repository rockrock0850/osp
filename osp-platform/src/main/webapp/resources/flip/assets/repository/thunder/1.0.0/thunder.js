/*! thunder SVN.13
 * Author: fet
 * Description: fet
 * Date: 2013-10-27 */
(function() {
    define(['versionStr'], function(versionStr) {
        var commonDirectives, appendUrlParams;

        /**
         * append param to url if the oriUrl already content "?" then concat them with "&", otherwise with "?"
         * the param (second one) should be like "v=123"
         * @private
         * @param {string, string}
         * @return string
         */
        appendUrlParams = function(oriUrl, param) {
            if (!oriUrl) {
                return "";
            }
            if (!param) {
                return oriUrl;
            }
            return (oriUrl + ((oriUrl.indexOf("?") > -1) ? "&" : "?") + param);
        };

        // for ie8
        if (!Array.prototype.indexOf) {
            Array.prototype.indexOf = function(elt /*, from*/ ) {
                var len = this.length >>> 0;
                var from = Number(arguments[1]) || 0;
                from = (from < 0) ? Math.ceil(from) : Math.floor(from);
                if (from < 0)
                    from += len;

                for (; from < len; from++) {
                    if (from in this && this[from] === elt) {
                        return from;
                    }
                }
                return -1;
            };
        }

        commonDirectives = angular.module('commonDirectives', []);
        
        commonDirectives.directive('tBlock', function() {
            return {
                restrict: 'CA',
                link: function(scope, element, attributes) {
                    var isBlock;
                    scope.$watch(attributes.tBlock, function(value) {
                        isBlock = value;
                        if (isBlock) {
                            element.block({
                                message: '<div><img id="displayBox" src="assets/img/loading.gif"></div>',
                                css: {
                                    width: '350px',
                                    top: ($(window).height() - 400) / 2 + 'px',
                                    left: ($(window).width() - 400) / 2 + 'px',
                                    right: '10px',
                                    border: 'none',
                                    padding: '5px',
                                    backgroundColor: '#fff',
                                    opacity: 0.6,
                                    color: '#fff'
                                },
                                overlayCSS: {
                                    backgroundColor: '#fff'
                                },
                                fadeIn: 100,
                                fadeOut: 100
                            });
                        } else {
                            element.unblock();
                        }
                    });
                }
            };
        });

        commonDirectives.directive('tAcc', ['$document', '$location', '$window',
            function($document, $location, $window) {
                return {
                    restrict: 'CA',
                    link: function(scope, element, attrs) {
                        element.bind('click', function(event) {
                            var last = jQuery('li.open', element.parent().parent()),
                                sub = element.siblings('.sub');

                            jQuery('.arrow', last).removeClass("open");
                            jQuery('.sub', last).slideUp(200, function() {
                                last.removeClass('active');
                            });

                            if (sub.is(":visible")) {
                                jQuery('.arrow', element).removeClass("open");
                                element.parent().removeClass("open");
                                sub.slideUp(200, function() {
                                    element.parent().removeClass('active');
                                });
                            } else {
                                jQuery('.arrow', element).addClass("open");
                                element.parent().addClass("open");
                                sub.slideDown(200, function() {
                                    element.parent().addClass('active');
                                });
                            }
                        });
                    }
                };
            }
        ]);

        commonDirectives.directive('sortable', function() {
            return {
                restrict: 'C',
                link: function(scope, element, attrs) {
                    element.sortable({
                        connectWith: '.sortable',
                        iframeFix: false,
                        items: 'div.widget',
                        opacity: 0.8,
                        helper: 'original',
                        //revert: true,
                        forceHelperSize: true,
                        placeholder: 'sortable-box-placeholder round-all',
                        forcePlaceholderSize: true,
                        tolerance: 'pointer',

                        handle: '.handle',
                        revert: 100
                    });
                }
            };
        });

        commonDirectives.directive('widget', [
            function() {
                return {
                    restrict: 'C',
                    link: function(scope, element, attrs) {
                        var $tools = element.find('.tools');
                        $tools.find('.icon-remove').bind('click', function() {
                            element.parent().remove();
                        });

                        $tools.find('.icon-chevron-up, .icon-chevron-down').bind('click', function() {
                            var el = element.find('.widget-body');
                            var $this = jQuery(this);
                            if ($this.hasClass('icon-chevron-up')) {
                                $this.removeClass('icon-chevron-up').addClass('icon-chevron-down');
                                el.slideUp(100);
                            } else {
                                $this.removeClass('icon-chevron-down').addClass('icon-chevron-up');
                                el.slideDown(100);
                            }
                        });
                    }
                };
            }
        ]);

        commonDirectives.directive('accordion', [
            function() {
                return {
                    restrict: 'C',
                    link: function(scope, element, attrs) {
                        element.on('show hide', function(event) {
                            $(event.target).siblings(".accordion-heading").find(".accordion-toggle i").toggleClass("icon-chevron-up icon-chevron-down");
                        });
                    }
                };
            }
        ]);
        
        commonDirectives.directive('appPart', [
            '$templateCache', 'httpService', 'appPartService', 'loggingService',
            function($templateCache, httpService, appPartService, loggingService) {
                var params;

                params = function(url) {
                    var decode, match, param, plus, query, urlParams;

                    plus = /\+/g;
                    param = /([^&=]+)=?([^&]*)/g;
                    decode = function(s) {
                        return decodeURIComponent(s.replace(plus, " "));
                    };
                    query = url.split("?")[1];
                    urlParams = {};
                    if (query !== null) {
                        while (match = param.exec(query)) {
                            urlParams[decode(match[1])] = decode(match[2]);
                        }
                    }
                    return urlParams;
                };
                return {
                    restrict: 'A',
                    scope: {
                        src: '=',
                        random: '@'
                    },
                    template: '<div ng-include src="viewUrl"></div>',
                    link: function(scope, element, attributes) {
                        return scope.$watch('src', function(src) {
                            //loggingService.debug('thunder', 'src ' + src);
                            var _src = src,
                                random = (scope.random === "true"),
                                randomValue;
                            if (random) {
                                _src = appendUrlParams(_src, randomValue = "r=" + Math.random());
                            }
                            loggingService.debug('appPart', '_configUrl: ' + _src);
                            return appPartService.load(_src, params(_src), randomValue).then(function(appPart) {
                                var _viewUrl = appendUrlParams(appPart.viewUrl, versionStr);
                                if (random) {
                                    _viewUrl = appendUrlParams(_viewUrl, randomValue);
                                }
                                loggingService.debug('appPart', '_viewUrl: ' + _viewUrl);
                                return httpService.get(_viewUrl).then(function(resp) {
                                    $templateCache.put(_viewUrl, "<div id='" + appPart.name + "' ng-controller='" + appPart.name + "'>" + resp.data + "</div>");
                                    return scope.viewUrl = _viewUrl;
                                });
                            });
                        });
                    }
                };
            }
        ]);

        commonDirectives.directive('cid', [
            function() {
                return {
                    require: 'ngModel',
                    restrict: 'A',
                    link: function(scope, element, attribute, ngModel) {
                        var validate;

                        validate = function(value) {
                            return value.length === 10;
                        };
                        return ngModel.$parsers.unshift(function(value) {
                            ngModel.$setValidity('cid', validate(value));
                            return value;
                        });
                    }
                };
            }
        ]);

        commonDirectives.directive('rocid', [
            function() {
                return {
                    require: 'ngModel',
                    restrict: 'A',
                    link: function(scope, element, attribute, ngModel) {
                        var calculate, mapping, multipliers, validate;

                        mapping = {
                            A: '10',
                            B: '11',
                            C: '12',
                            D: '13',
                            E: '14',
                            F: '15',
                            G: '16',
                            H: '17',
                            I: '34',
                            J: '18',
                            K: '19',
                            L: '20',
                            M: '21',
                            N: '22',
                            O: '35',
                            P: '23',
                            Q: '24',
                            R: '25',
                            S: '26',
                            T: '27',
                            U: '28',
                            V: '29',
                            W: '32',
                            X: '30',
                            Z: '33'
                        };
                        multipliers = [1, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1];
                        calculate = function(value) {
                            var d, i, sum, _i, _len;

                            sum = 0;
                            for (i = _i = 0, _len = value.length; _i < _len; i = ++_i) {
                                d = value[i];
                                sum += parseInt(d) * multipliers[i];
                            }
                            return sum % 10 === 0;
                        };
                        validate = function(value) {
                            return value.length === 10 && mapping[value[0]] && calculate(mapping[value[0]] + value.substring(1));
                        };
                        return ngModel.$parsers.unshift(function(value) {
                            ngModel.$setValidity('rocid', validate(value));
                            return value;
                        });
                    }
                };
            }
        ]);

        commonDirectives.directive('passwd', [
            function() {
                return {
                    require: 'ngModel',
                    restrict: 'A',
                    link: function(scope, element, attributes, ngModel) {
                        var validate;

                        validate = function(value) {
                            return value && value.length >= 5 && (/[A-z]/.test(value) || /\d/.test(value));
                        };
                        return ngModel.$parsers.unshift(function(value) {
                            ngModel.$setValidity('passwd', validate(value));
                            //console.log(validate(value));
                            return value;
                        });
                    }
                };
            }
        ]);

        commonDirectives.directive('regex', [
            function() {
                return {
                    require: 'ngModel',
                    restrict: 'A',
                    link: function(scope, element, attributes, ngModel) {
                        var regex;

                        regex = new RegExp(attributes.regex, attributes.regexFlags || '');
                        return ngModel.$parsers.unshift(function(value) {
                            ngModel.$setValidity('regex', regex.test(value));
                            return value;
                        });
                    }
                };
            }
        ]);

        commonDirectives.directive('validator', [
            function() {
                return {
                    require: 'ngModel',
                    restrict: 'A',
                    link: function(scope, element, attributes, ngModel) {
                        return ngModel.$parsers.unshift(function(value) {
                            var valid;

                            valid = scope[attributes.validator](value);
                            ngModel.$setValidity((attributes != null ? attributes.validatorName : void 0) || 'validator', valid);
                            return value;
                        });
                    }
                };
            }
        ]);

        commonDirectives.directive('formatter', [
            function() {
                return {
                    require: 'ngModel',
                    restrict: 'A',
                    link: function(scope, element, attributes, ngModel) {
                        return ngModel.$formatters.unshift(function(value) {
                            var v;

                            v = scope[attributes.formatter](value);
                            //console.log("formatter returning " + v);
                            return v;
                        });
                    }
                };
            }
        ]);

        var commonServices,
            __hasProp = {}.hasOwnProperty,
            __slice = [].slice;

        commonServices = angular.module('commonServices', []);

        /*
{object} – Newly created cache object with the following set of methods:
{object} info() — Returns id, size, and options of cache.
{void} put({string} key, {*} value) — Puts a new key-value pair into the cache.
{{*}} get({string} key) — Returns cached value for key or undefined for cache miss.
{void} remove({string} key) — Removes a key-value pair from the cache.
{void} removeAll() — Removes all cached values.
{void} destroy() — Removes references to this cache from $cacheFactory.
*/


        commonServices.factory('cacheService', [
            '$cacheFactory',
            function($cacheFactory) {
                return {
                    create: function(id, size) {
                        return $cacheFactory(id, {
                            capacity: size
                        });
                    }
                };
            }
        ]);

        commonServices.factory('taskService', [
            '$timeout',
            function($timeout) {
                var recur;

                recur = function() {
                    var p, _recur;

                    p = void 0;
                    return _recur = function(delay, task) {
                        p = $timeout(task, delay);
                        p.then(function() {
                            return _recur(delay, task);
                        });
                        return function() {
                            return $timeout.cancel(p);
                        };
                    };
                };
                return {
                    recur: recur(),
                    once: function(delay, task) {
                        var p;

                        p = $timeout(task, delay);
                        return function() {
                            return $timeout.cancel(p);
                        };
                    }
                };
            }
        ]);

        commonServices.factory('metricsService', [
            'taskService', 'loggingService',
            function(taskService, loggingService) {
                var counters, meters, p, reportCounter, reportMeter, startTime;

                counters = {};
                meters = {};
                startTime = void 0;
                p = void 0;
                reportCounter = function(id) {
                    return loggingService.info('metrics', "counter " + id + ": " + counters[id]);
                };
                reportMeter = function(id, interval) {
                    var i, intervals, m, perInterval, time, total, _i, _ref;

                    time = new Date().getTime();
                    intervals = parseInt((time - startTime) / 1000 / interval);
                    total = meters[id];
                    perInterval = parseInt(total / intervals);
                    m = [];
                    m.push("meter " + id + ": " + total);
                    for (i = _i = 1, _ref = Math.min(intervals, 5); 1 <= _ref ? _i <= _ref : _i >= _ref; i = 1 <= _ref ? ++_i : --_i) {
                        m.push(" " + (perInterval * i) + "/" + (i * interval) + "s");
                    }
                    return loggingService.info('metrics', m.join());
                };
                return {
                    counter: function(id) {
                        counters[id] = 0;
                        return {
                            inc: function() {
                                return counters[id]++;
                            },
                            dec: function() {
                                return counters[id]--;
                            }
                        };
                    },
                    meter: function(id) {
                        meters[id] = 0;
                        return {
                            mark: function() {
                                return meters[id]++;
                            }
                        };
                    },
                    start: function(interval) {
                        startTime = new Date().getTime();
                        return p = taskService.recur(interval * 1000, function() {
                            var id, _results;

                            for (id in counters) {
                                reportCounter(id);
                            }
                            _results = [];
                            for (id in meters) {
                                _results.push(reportMeter(id, interval));
                            }
                            return _results;
                        });
                    },
                    stop: function() {
                        return p();
                    },
                    reset: function() {
                        counters = {};
                        return meters = {};
                    }
                };
            }
        ]);

        commonServices.factory('circularListService', [
            function() {
                return {
                    create: function(capacity) {
                        var index, list;

                        list = [];
                        index = 0;
                        return {
                            add: function(obj) {
                                list[index] = obj;
                                return index = (index + 1) % capacity;
                            },
                            getAll: function() {
                                var l, _ref, _ref1;

                                l = list.slice(index);
                                [].splice.apply(l, [(_ref = l.length), 9e9].concat(_ref1 = list.slice(0, index))), _ref1;
                                return l;
                            }
                        };
                    }
                };
            }
        ]);

        commonServices.provider('loggingService', [
            function() {
                var appenders, level, logLevel;

                appenders = [
                    (function() {
                        //no console (ie8)
                        if ((typeof console === "undefined") || (typeof console.log === "undefined")) {
                            return function() {};
                        }

                        var getConsoleLog, getChromeColor;
                        getConsoleLog = function(level) {
                            var level_l = level.toLowerCase() === "warning" ? "warn" : level.toLowerCase(),
                                logging = console[level_l] || console.log;

                            return logging;

                        };

                        if ($.browser.chrome === true) {
                            //chrome custmize
                            getChromeColor = function(level) {
                                switch (level) {
                                    case 'DEBUG':
                                        return 'blue';
                                    case 'INFO':
                                        return 'green';
                                    case 'WARNING':
                                        return 'purple';
                                    case 'ERROR':
                                        return 'red';
                                    default:
                                        return 'black';
                                }
                            };
                            return function(level, source, message) {
                                return getConsoleLog(level).call(console, "%c" + level + ": " + source + " -> " + message, "color: " + getChromeColor(level));
                            };
                        }

                        //else
                        return function(level, source, message) {
                            return console.log(level + ": " + source + " -> " + message);

                            //ie don't work
                            //return getConsoleLog(level).call(console, level + ": " + source + " -> " + message);
                        };
                    })()

                ];
                logLevel = 0;
                level = function(l) {
                    switch (l) {
                        case 'DEBUG':
                            return 0;
                        case 'INFO':
                            return 1;
                        case 'WARNING':
                            return 2;
                        case 'ERROR':
                            return 3;
                        default:
                            return 0;
                    }
                };
                return {
                    addAppender: function(appender) {
                        return appenders.push.apply(appenders, appender);
                    },
                    setLevel: function(l) {
                        return logLevel = level(l);
                    },
                    $get: [
                        function() {
                            var log;

                            log = function(l, source, message) {
                                if (level(l) >= logLevel) {
                                    return angular.forEach(appenders, function(appender) {
                                        return appender(l, source, message);
                                    });
                                }
                            };
                            return {
                                debug: function(source, message) {
                                    return log('DEBUG', source, message);
                                },
                                info: function(source, message) {
                                    return log('INFO', source, message);
                                },
                                warning: function(source, message) {
                                    return log('WARNING', source, message);
                                },
                                error: function(source, message) {
                                    return log('ERROR', source, message);
                                }
                            };
                        }
                    ]
                };
            }
        ]);

        commonServices.provider('eventBusService', [
            function() {
                var _maxEvents;

                _maxEvents = 100;
                return {
                    maxEvent: function(n) {
                        return _maxEvents = n;
                    },
                    $get: [
                        '$rootScope', 'circularListService',
                        function($rootScope, circularListService) {
                            var events, subs, unsubscribe;

                            events = circularListService.create(_maxEvents);
                            subs = {};
                            unsubscribe = function(subscriber, name) {
                                if (subs[subscriber] && subs[subscriber][name]) {
                                    subs[subscriber][name]();
                                    delete subs[subscriber][name];
                                }
                                if (subs[subscriber] === {}) {
                                    return delete subs[subscriber];
                                }
                            };
                            return {
                                publish: function(publisher, name, args) {
                                    events.add({
                                        publisher: publisher,
                                        name: name,
                                        args: args
                                    });
                                    return $rootScope.$broadcast(name, args);
                                },
                                subscribe: function(subscriber, name, listener) {
                                    var sub;

                                    sub = subs[subscriber] || {};
                                    if (typeof sub[name] === "function") {
                                        sub[name]();
                                    }
                                    sub[name] = $rootScope.$on(name, listener);
                                    return subs[subscriber] = sub;
                                },
                                unsubscribe: unsubscribe,
                                unsubscribeAll: function(subscriber) {
                                    var n, _ref, _results;

                                    _ref = subs[subscriber];
                                    _results = [];
                                    for (n in _ref) {
                                        if (!__hasProp.call(_ref, n)) continue;
                                        _results.push(unsubscribe(subscriber, n));
                                    }
                                    return _results;
                                },
                                events: function() {
                                    return events.getAll();
                                },
                                subscribers: function() {
                                    var n, s, ss, _ref;

                                    ss = [];
                                    for (s in subs) {
                                        if (!__hasProp.call(subs, s)) continue;
                                        _ref = subs[s];
                                        for (n in _ref) {
                                            if (!__hasProp.call(_ref, n)) continue;
                                            ss.push({
                                                subscriber: s,
                                                name: n
                                            });
                                        }
                                    }
                                    return ss;
                                }
                            };
                        }
                    ]
                };
            }
        ]);

        commonServices.provider('controllerLoader', [
            '$controllerProvider',
            function($controllerProvider) {
                return {
                    $get: [
                        '$q', '$rootScope', '$controller',
                        function($q, $rootScope, $controller) {
                            return {
                                load: function(name, url) {
                                    var defer;

                                    defer = $q.defer();
                                    require([url], function(controller) {
                                        var ctrl, index;

                                        if (angular.isArray(controller)) {
                                            index = controller.indexOf('$scope');
                                            if (index !== -1) {
                                                ctrl = controller[controller.length - 1];
                                                controller[controller.length - 1] = function() {
                                                    var args, scope;

                                                    args = 1 <= arguments.length ? __slice.call(arguments, 0) : [];
                                                    scope = args[index];
                                                    scope._controllerName = name;
                                                    return ctrl.apply(null, args);
                                                };
                                            }
                                        }
                                        $controllerProvider.register(name, controller);
                                        defer.resolve();
                                        return $rootScope.$apply();
                                    });
                                    return defer.promise;
                                }
                            };
                        }
                    ]
                };
            }
        ]);

        commonServices.provider('httpService', [
            function() {
                var config, _timeout;

                _timeout = 60000;
                config = function(method, url, options) {
                    var c;

                    c = {
                        method: method,
                        url: url,
                        timeout: _timeout
                    };
                    return jQuery.extend(c, options);
                };
                return {
                    timeout: function(n) {
                        return _timeout = n;
                    },
                    $get: [
                        '$http',
                        function($http) {
                            return {
                                get: function(url, options) {
                                    if (options == null) {
                                        options = {};
                                    }
                                    return $http(config('GET', url, options));
                                },
                                put: function(url, options) {
                                    if (options == null) {
                                        options = {};
                                    }
                                    return $http(config('PUT', url, options));
                                },
                                post: function(url, options) {
                                    if (options == null) {
                                        options = {};
                                    }
                                    return $http(config('POST', url, options));
                                },
                                jsonp: function(url, options) {
                                    if (options == null) {
                                        options = {};
                                    }
                                    options['params'] = {
                                        'callback': 'JSON_CALLBACK'
                                    };
                                    return $http(config('JSONP', url, options));
                                }
                            };
                        }
                    ]
                };
            }
        ]);

        commonServices.provider('appPartService', [
            '$provide',
            function($provide) {
                var appParts, configs, params;

                appParts = {};
                configs = {};
                params = {};
                $provide.decorator('$controller', [
                    '$delegate',
                    function($delegate) {
                        return function(controller, locals) {
                            if (typeof controller === 'string') {
                                locals.controllerName = controller;
                            }
                            if (configs[controller]) {
                                locals.config = configs[controller];
                            }
                            if (params[controller]) {
                                locals.params = params[controller];
                            }
                            return $delegate(controller, locals);
                        };
                    }
                ]);
                return {
                    $get: [
                        'httpService', 'controllerLoader', 'eventBusService', 'loggingService',
                        function(httpService, controllerLoader, eventBusService, loggingService) {
                            return {
                                load: function(url, param, randomValue, reload) {
                                    if (param == null) {
                                        param = {};
                                    }
                                    if (reload == null) {
                                        reload = false;
                                    }
                                    if (appParts[url] && !reload) {
                                        params[appParts[url].name] = param;
                                        return appParts[url];
                                    } else {
                                        return appParts[url] = httpService.get(url).then(function(resp) {
                                            var _controllerUrl = resp.data.controllerUrl;
                                            if (resp.data.config) {
                                                configs[resp.data.name] = resp.data.config;
                                            }
                                            params[resp.data.name] = param;
                                            _controllerUrl = (randomValue ? appendUrlParams(_controllerUrl, randomValue) : _controllerUrl);
                                            loggingService.debug("appPart", "_controllerUrl: " + _controllerUrl);
                                            return controllerLoader.load(resp.data.name, _controllerUrl).then(function() {
                                                eventBusService.publish('httpService', 'appPart.loaded', resp.data.name);
                                                return resp.data;
                                            });
                                        });
                                    }
                                }
                            };
                        }
                    ]
                };
            }
        ]);

        commonServices.provider('authenticationService', [
            function() {
                var userInfo;

                userInfo = void 0;
                return {
                    $get: [
                        'httpService',
                        function(httpService) {
                            return {
                                register: function(url) {
                                    return httpService.get(url);
                                },
                                login: function(url, id, password, success, failure) {
                                    return httpService.get(url, {
                                        params: {
                                            id: id,
                                            password: password
                                        }
                                    }).then((function(resp) {
                                        userInfo = resp.data.userInfo ? resp.data.userInfo : void 0;
                                        return success(userInfo);
                                    }), (function(resp) {
                                        return failure(resp);
                                    }));
                                },
                                logout: function(url) {
                                    return userInfo = void 0;
                                },
                                userInfo: function() {
                                    return userInfo;
                                }
                            };
                        }
                    ]
                };
            }
        ]);
    });
}).call(this);