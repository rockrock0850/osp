require(['flip', 'service', 'button'], 
	function (flip, service) {
		$('button#comfirmButton').unbind('click').click(function() {
			service.submit();
		});

		$('button#cancelButton').unbind('click').click(function() {
			window.location.href = '/osp-platform/console-index.action';
		});
	}
);

(function() {
	define('service', ['flip', 'small-modal'], 
		function(flip) {
			var init = function() {}

			// 送出所有表單陣列
			var submit = function() {
				var formData = validateFormData(window.form2object('CONT0013'));
				if (formData == null) {
					return;
				}
				
				var settings = {
					dataType: 'html',
					httpMethod: 'POST',
					URL: contextPath + '/workshop/special/service-set-special-order-process.action',
					dataProvider: function() {
						var formId = $('form[id=CONT0013]').attr('id');
						var operateMange = {};
						
						operateMange.contentId = formId;
						operateMange.contentData = formData;
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(operateMange));
				    }, 
					success: function(data){
						if(data) {
							var content = '<center><label>' + $.getSuccessMessage('008') + '</label></center>';
							var buttons = {
								cancelButton: {
									value: '確認', 
									action: function() {
										$.messageModal('hide');
										location.reload();
									}
								}
							}
				    		$.messageModal('訊息', content, buttons);
						}
					}
				};
				$.formAjax(settings);
			}
			
			/* Inner Method
			================================================================================================================ */
			var validateFormData = function (data) {
				var flag = false;
				
				$.each(data, function (i) {
					if (i.startsWith('reserv_')) {
						var value = data[i];
						if (!value) {
							$.warningMsg('030');
							flag = true;
						}
						
						if ($.validateCardNumbers(value)) {
							$.warningMsg('029');
							flag = true;
						}
					}
				});
				
				if (flag) {
					return null;
				}
				return data;
			}
		
			return {
				init: init,
				submit: submit
			}
		}
	);
})()