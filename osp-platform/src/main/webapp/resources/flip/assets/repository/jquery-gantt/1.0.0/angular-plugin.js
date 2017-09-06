(function() {
	var host = location.hostname;
	var port = location.port;
	var hostNameWithPort = "http://" + host + ":" + port;
	
	var context = hostNameWithPort +"/assets";
	var repository = context + "/repository";
	
	var ganttVersion = "1.0.0";
	
	
    require.config({
        paths: {
            ganttJquery: repository + '/jquery-gantt/' + ganttVersion + '/jquery.fn.gantt'
        },
        shim: {
            ganttJquery: {
                deps: ['jquery', 'angular']
            }
        }
    });
 
    define('ganttDirective', ['angular', 'ganttJquery'], function(angular) {
		var gantt = angular.module('flip.module.gantt', []);
		
		gantt.directive('gantt', function() {
			return function($scope, element, attrs) {
				var scaleAttr = element.attr('scale');
				if(typeof(scaleAttr) == 'undefined' || !scaleAttr) {
					scaleAttr = 'days';
				}
				
				element.gantt({
					source: $scope[attrs.ngModel],
					scale : scaleAttr
			   });
		   }
		});	
				
        return this;
    });
}).call(this);