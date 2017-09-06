require(['shift-content-service', 'osp-ui-app', 'flip', 'common-util', 'cookie', 'form2object'], 
	function (service) {
		document.title = 'OSP | 班表匯入作業';
		service.init();
		
		$('a#downloadButton').on('click', function() {
			service.downloadFile();
		});
		
		$("input[name='file']").on('change', function() {
			service.chooseFile();
		});
		
		$("button[id=uploadButton]").click(function() {
			service.uploadFile();
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
		
			// 選擇檔案
			var chooseFile = function() {
				var xlsxType = $.getFileType('xlsxType');
				var xlsType = $.getFileType('xlsType');
				var $fileName = $('form#fileUploadForm').find('span[id=fileName]');
				var $file = $('form#fileUploadForm').find('input#file');
				var $uploadButton = $("form#fileUploadForm").find("button#uploadButton");
				var $chooseButton = $('form#fileUploadForm').find('span[class$=btn-file]');
				
				$fileName.text("");
				try {
					// 注意: 不知道為什麼input一定要加上[0]才能選到檔案清單物件
					var file = $file[0].files[0];
					var name = file.name;
			    	var type = file.type;
			    	
					if(type != xlsType && type != xlsxType) {
						throw $.getErrorMessage('008');
					}
					
					$fileName.text(name);
					$chooseButton.hide();
					$uploadButton.show();
				} catch (error) {
					// 注意: 當選擇檔案時, 若按下取消鍵, name會拋exception, 利用此關係來觸發上傳按鈕的顯示與否 by Andrew
					$chooseButton.show();
					$uploadButton.hide();
					
					var content = '<center><label>' + error + '</label></center>';
					var buttons = {
						cancelButton: {
							value: '確認'
						}
					}
					$.messageModal('警告', content, buttons);
				}
			}
			
			// 上傳檔案
			var uploadFile = function() {
				var files = $('form#fileUploadForm').find('input#file')[0].files;
				var $importSelect = $('div#importSelect');
				var year = 
					$importSelect.find('select#yearSelector option:selected').val();
				var month = 
					$importSelect.find('select#monthSelector option:selected').val();
			    var formData = new FormData();
				
			    $.each(files, function(i, file) {
				    formData.append('file', file);
				    formData.append('jsonData', '{year: "' + year + '", month: "' + month + '"}');
			    });
				
			    if($.isEmptyObject(formData)) {
		        	$.warningMsg('006');
		        	
		        	return;
			    }
			    
			    // 上傳
			    $.ajax({
			        url: contextPath + '/setting/shift/fileUpload.action',
			        type: 'POST',
			        data: formData,
		            cache : false,
		            async: false,
			        contentType: false,
			        processData: false,
			        success: function (data) {
			        	showFileInformation(data);
			        }
			    });
			}
			
			var downloadFile = function() {
				var $exportSelect = $('div#exportSelect');
				var yearSelect = 
					$exportSelect.find('select#yearSelector option:selected').val();
				var monthSelect = 
					$exportSelect.find('select#monthSelector option:selected').val();

				var prepareDownload = {};
				prepareDownload.year = yearSelect;
				prepareDownload.month = monthSelect;
				
				var fileUrl = contextPath + 
				'/setting/shift/export-shift-content.action?jsonData=' +
				encodeURIComponent(JSON.stringify(prepareDownload));
				
				$.fileDownload(fileUrl, {
					 successCallback: function (fileUrl) {
						 resetForm();
				    },
				    failCallback: function (responseHtml, url) {
				    	resetForm();
				    	$.warningMsg('025');
				    }
				});
			}
			
			var importFile = function () {
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/setting/shift/import-shift-content.action', 
					dataType: 'html',
					dataProvider: function() {
						var file = getFile();
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(file));
				    }, 
				    success: function(data) {
				    	if (data) {
				    		$('div#fileInformation').hide();
				    		$.successMsg('006');
				    		resetForm();
				    	}
				    }
				}
				$.formAjax(settings);
			}
		
			/* Inner Method
			=========================================================================================== */
			var getFile = function () {
				var row = $('div#fileInformation table').find('tr')[1];
				var fileName = $(row).find('td.fileName').text();
				var fileLength = $(row).find('td.fileLength').text();
				var fileExtensions = $(row).find('td.fileExtensions').text();
				var filePath = $(row).find('td.filePath').text();
				
				var $importSelect = $('div#importSelect');
				var year = $importSelect.find('select#yearSelector').val();
				var month = $importSelect.find('select#monthSelector').val();
				
				var fileVO = {};
				fileVO.fileName = fileName;
				fileVO.fileLength = fileLength;
				fileVO.fileExtensions = fileExtensions;
				fileVO.filePath = filePath;

				var file = {};
				file.fileVO = fileVO;
				file.year = year;
				file.month = month;
				
				return file;
			}
			
			var showFileInformation = function (data) {
				var year = data.year;
				var month = data.month;
				var fileVO = data.fileVO;
				var $tbody = $('div#fileInformation').find('tbody');
				
				$tbody.empty();
				
				var content = '<tr>';
				content += '<td class="fileName">' + fileVO.fileName + '</td>';
				content += '<td class="fileLength">' + fileVO.fileLength + '</td>';
				content += '<td class="fileExtensions">' + fileVO.fileExtensions + '</td>';
				content += '<td class="hide filePath">' + fileVO.filePath + '</td>';
				content += '</tr>';
				$tbody.append(content);

				$('form#fileUploadForm').find('input#file').val('');
				$('span[id=fileName]').text('');
			    $("button#uploadButton").hide();
				$('span[class$=btn-file]').show();
				$('div#fileInformation').show();
			}
			
			// 全部重置
			var resetForm = function () {
				var date = new Date();

	    		$('div#fileInformation').find('tbody').empty();
				$('select#yearSelector').val(date.getFullYear());
				$('select#monthSelector').val(date.getMonth());
				$('form#fileUploadForm').find('input#file').val('');
				$('span[id=fileName]').text('');
			}
			
			var initYearSelector = function() {
				for (var i = new Date().getFullYear(); i > 1900; i--) {
					$('div#importSelect').find('select#yearSelector').append($('<option />').val(i).html(i));
					$('div#exportSelect').find('select#yearSelector').append($('<option />').val(i).html(i));
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
				
				for (var i = month.length-1; i > -1; i--) {
					$('div#importSelect').find('select#monthSelector').append($('<option />').val(i).html(month[i]));
					$('div#exportSelect').find('select#monthSelector').append($('<option />').val(i).html(month[i]));
				}

				var date = new Date();
				$('div#importSelect').find('select#monthSelector').val(date.getMonth()+1);
				$('div#exportSelect').find('select#monthSelector').val(date.getMonth()+1);
			}
			
			return {
				init: init,
				chooseFile: chooseFile,
				uploadFile: uploadFile,
				downloadFile: downloadFile, 
				importFile: importFile
			}
		}
	);
})();
