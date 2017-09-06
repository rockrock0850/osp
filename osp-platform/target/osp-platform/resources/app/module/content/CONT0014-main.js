require(['flip', 'CONT0014-service', 'button'], 
	function (flip, service) {
		service.init();
		
		$('button#cont0014SearchButton').unbind('click').click(function() {
			service.ajaxSpvResult();
		});
	}
);

(function() {
	define('CONT0014-service', ['flip', 'small-modal'], 
		function() {
			var init = function() {
				getPromotionId();
			}

			var ajaxSpvResult = function (promotionId) {
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/flow/content/get-spv-result.action',
					dataType: 'html',
					dataProvider: function() {
						var formData = window.form2object('CONT0014Form');
						
						formData.promotionId = formData.promotionId == '' ? promotionId : formData.promotionId;
						if (formData.promotionId == '') {
							$.warningMsg('023');
							
							return;
						}
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
					},
				    success: function(data) {
				    	if(data) {
				    		var $result = $('div#CONT0014SearchResult');
				    		data = JSON.parse(data);
				    		setAttach($result, data);
				    		setPromotion($result, data);
				    		setRatePlanOffer($result, data.ratePlanOfferList);
				    		setSubPromotioin($result, data.subPromotionList);
				    		setVasOffer($result, data.vasOfferList);
				    		$result.show();
			    		}
				    }
				}
				$.formAjax(settings);
			}
			
			/* Inner Method
			======================================================== */
			var setAttach = function ($container, data) {
	    		$container.find('p#attach').empty().append(data.attach);
			}
			
			var setPromotion = function ($container, data) {
	    		var $promotion = $container.find('table#promotion tbody');
	    		
	    		var content = '<tr>';
	    		content += '<td>' + data.promotionCode + '</td>';
	    		content += '<td>' + data.name + '</td>';
	    		content += '<td>' + data.promotionCategoryName + '</td>';
	    		content += '<td>' + data.promotionType + '</td>';
	    		content += '<td>' + data.startDate + '</td>';
	    		content += '<td>' + data.endDate + '</td>';
	    		content += '<td>' + data.msisdnType + '</td>';
	    		content += '</tr>';
	    		$promotion.empty().append(content);
			}
			
			var setRatePlanOffer = function ($container, list) {
	    		var $ratePlanOffer = $container.find('table#ratePlanOffer tbody');
				var content = '';
				
	    		$.each(list, function () {
		    		content += '<tr>';
		    		content += '<td>' + this.name + '</td>';
		    		content += '<td>' + this.vasOfferType + '</td>';
		    		content += '</tr>';
	    		});
	    		$ratePlanOffer.empty().append(content);
			}
			
			var setSubPromotioin = function ($container, list) {
	    		var $subPromotion = $container.find('table#subPromotion tbody');
	    		var content = '';
	    		
	    		$.each(list, function () {
		    		content += '<tr>';
		    		content += '<td>' + this.promotionCode + '</td>';
		    		content += '<td>' + this.name + '</td>';
		    		content += '<td>' + this.promotionCategoryName + '</td>';
		    		content += '<td>' + this.startDate + '</td>';
		    		content += '<td>' + this.endDate + '</td>';
		    		content += '<td>' + this.msisdnType + '</td>';
		    		content += '</tr>';
	    		});
	    		$subPromotion.empty().append(content);
			}
			
			var setVasOffer = function ($container, list) {
	    		var $vasOffer = $container.find('table#vasOffer tbody');
	    		var content = '';
	    		
	    		$.each(list, function () {
		    		content += '<tr>';
		    		content += '<td>' + this.id + '</td>';
		    		content += '<td>' + this.name + '</td>';
		    		content += '<td>' + this.vasOfferType + '</td>';
		    		content += '</tr>';
	    		});
	    		$vasOffer.empty().append(content);
			}
			
			var getPromotionId = function () {
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/flow/content/get-cont-0014-promotion.action',
					dataType: 'html',
					dataProvider: function() {
						var orderMId = $('div#buzFlow').attr('orderMId');
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(orderMId);
					},
				    success: function(data) {
				    	if(data) {
				    		$('div#CONT0014Form').find('input[name=promotionId]').val(data);
							ajaxSpvResult(data);
			    		}
				    }
				}
				$.formAjax(settings);
			}
			
			return {
				init: init,
				ajaxSpvResult: ajaxSpvResult
			}
		}
	);
})()