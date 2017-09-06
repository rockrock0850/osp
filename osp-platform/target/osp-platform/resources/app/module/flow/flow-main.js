require(['flip', 'flow-main-service'],
    function (flip, service){
		service.init();
	}
);

(function() {
	define('flow-main-service', ['flip', 'osp-ui-app', 'htmlComponet', 'common-util', 'adapterbrowser'], 
		function(flip) {
			flowInit = function() {
				var title = $('div#buzFlow').find('h3').html();
				if (title) {
					document.title = 'OSP | ' + title.substring(6);
				}
				
				var flowId = $("div#buzFlow").attr("flowId");
				var orderMId = $('div#buzFlow').attr('orderMId');
				var sourceSysId = $('div#buzFlow').attr('sourceSysId');
				var stepId = $('div#buzFlow li.active a').attr('id');

				$.setTabSwitchListener();
				setTabClickEvent(flowId, orderMId);
				getStepPage(flowId, orderMId, stepId);
				setSelectContent(flowId, sourceSysId);
			};
			
			/* Inner Method
			================================================================================================================= */
			var setSelectContent = function(flowId, sourceSysId) {
				$.getOrderTypeDisplayBySourceSysId($('select[name=orderTypeId]'), sourceSysId);
				$.getOptionProcessReason($('form#successForm').find('select[name=reasonId]'), flowId, '070');
				$.getOptionProcessResult($('form#successForm').find('select[name=resultId]'), flowId, '070');
				$.getOptionProcessReason($('form#failForm').find('select[name=reasonId]'), flowId, '080');
				$.getOptionProcessResult($('form#failForm').find('select[name=resultId]'), flowId, '080');
			}
			
			var setTabClickEvent = function(flowId, orderMId) {
				$('ul[class="nav nav-tabs"] li').unbind('click').click(function() {
					var $current = $(this);
					var $a = $('a', $current);
					var stepId = $a.attr("id");
	
					getStepPage(flowId, orderMId, stepId);
				});
			};
			
			var getStepPage = function(flowId, orderMId, stepId) {	
				var $src = $('a#' + stepId);
				var isLoad = $src.attr('isLoad');
				
				// 讀取過不再讀取
				if(isLoad == 'true'){
					return;
				}
				if(flowId == undefined || stepId == undefined) {
					return;
				}
				
				var settings = {
					dataType : 'html',
					URL : contextPath + '/flow/getServiceBuzStep.action' + 
						'?flowId=' + flowId + 
						'&stepId=' + stepId +
						'&orderMId=' + orderMId,
					success: function(data){
						$('div#tabContainers div#' + stepId).empty().html(data);
						$('a#' + stepId).attr('isLoad', true);
					}
				};
				$.formAjax(settings);
			};
		
			return {
				init : flowInit
			}
		}
	);
})()