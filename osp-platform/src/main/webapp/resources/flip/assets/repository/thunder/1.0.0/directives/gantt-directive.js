var ganttModule = angular.module('ganttModule', []);
		
ganttModule.directive('gantt', function() {
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