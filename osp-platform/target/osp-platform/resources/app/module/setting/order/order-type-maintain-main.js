require(['osp-ui-app', 'order-type-maintain-service', 'flip', 'common-util', 
	     'datetimepicker', 'cookie', 'form2object'], 
	function (uiApp, service) {
		document.title = 'OSP | 案件類別維護作業';
		service.init();
		
		$('button#searchButton').on('click', function() {
			service.getOrderTypeInfo();
		});
		
		$("#kpiDayType").change(function() {
			var dayTimeType = $(this).val() +  "_TIME"
			
			console.info(dayTimeType);
			service.changeKpiDateSelector(dayTimeType);
		});

		$('button#searchButton').trigger('click');
	}
);
