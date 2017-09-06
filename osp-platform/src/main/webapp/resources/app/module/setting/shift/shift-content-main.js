require(['shift-content-service', 'osp-ui-app', 'flip', 'common-util', 'cookie', 'form2object'], 
	function (service) {
		document.title = 'OSP | 班表匯入作業';
		service.init();
		
		$('a#downloadButton').on('click', function() {
			service.downloadFile();
		});
		
		$("button[id=importButton]").click(function() {
			service.importFile();
		});
	}
);

(function() {
	define('shift-content-service', ['flip', 'small-modal', 'osp-util', 'ajaxfiledownload'],
		function() {
			var init = function() {
				initMonthSelector();
				initYearSelector();
			}
			
			var downloadFile = function() {
				var params = window.form2object("exportSelect");

				var fileUrl = contextPath + '/setting/shift/export-shift-content.action?jsonData=' 
					+ encodeURIComponent(JSON.stringify(params));
				
				$.fileDownload(fileUrl, {
					 successCallback: function (fileUrl) {
				    },
				    failCallback: function (responseHtml, url) {
				    	$.warningMsg('025');
				    }
				});
			}
			
			var importFile = function () {
				var settings = {
					httpMethod: 'POST', 
					dataAreaId : "importForm",
					URL: contextPath + '/setting/shift/import-shift-content.action', 
					dataType: 'html',
					dataProvider: function(dataAreaId) {
						var params = window.form2object(dataAreaId);
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(params));
				    }, 
				    success: function(data) {
				    	if ($.trim(data) == 'true') {
				    		$('div#uploadInformation').attr("class", "hide");
				    		$('span[name="uploadName"]').text("");
				    		$.successMsg('006');
				    	} else {
				    		$('div#uploadInformation').attr("class", "hide");
				    		$('span[name="uploadName"]').text("");
				    		$.warningMsg('006');
				    	}
				    }
				}
				$.formAjax(settings);
			}
			
			var initYearSelector = function() {
				for (var i = new Date().getFullYear(); i > 1900; i--) {
					$("select[name='year']").append($('<option />').val(i).html(i));
				}
			}
			
			var initMonthSelector = function() {
				var month = [];
				month[1] = "01";
				month[2] = "02";
				month[3] = "03";
				month[4] = "04";
				month[5] = "05";
				month[6] = "06";
				month[7] = "07";
				month[8] = "08";
				month[9] = "09";
				month[10] = "10";
				month[11] = "11";
				month[12] = "12";
			
				for (var i = month.length-1; i > 0; i--) {
					$("select[name='month']").append($('<option />').val(i).html(month[i]));
				}

				$("select[name='month']").val(new Date().getMonth()+1);
			}
			
			return {
				init: init,
				downloadFile: downloadFile, 
				importFile: importFile
			}
		}
	);
})();
