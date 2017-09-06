require(['upload-service', 'flip', 'cookie', 'form2object', 'common-util', 'osp-ui-app', 'small-modal'], 
	function (uploadService) {
		$("input[name='upload']").on('change', function() {
			uploadService.chooseFile();
		});
		
		$("button[id=uploadButton]").click(function() {
			uploadService.uploadFile();
		});
	}
);

(function() {
	define('upload-service', ['flip', 'small-modal'],
		function() {
			// 選擇檔案
			var chooseFile = function() {
				var file = $("input[name='upload']")[0].files[0];
			
				$("span[name='uploadName']").text("").text(file.name);
				
				$('table#uploadInfoGrid').find('tbody').html("").empty();
				$('div#uploadInformation').addClass("hide");
			
				$("button#uploadButton").show();
			}
			
			// 上傳檔案
			var uploadFile = function() {
				var file = $("input[name='upload']")[0].files[0];
			
			    var formData = new FormData();
				formData.append("file", file);

			    // 上傳
			    $.ajax({
			        url: contextPath + '/file/upload.action',
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
			
			var showFileInformation = function (data) {
				var $infoBody = $('table#uploadInfoGrid').find('tbody');
				$infoBody.html("").empty();
				
				var fileName = data.FILE_NAME;
				var fileSize = data.FILE_SIZE;
				var fileExtension = data.FILE_EXTENSION;
				var filePath = data.FILE_PATH;
				
				var content = '<tr>';
				content += '<td><input type="hidden" name="fileName" value="' + fileName + '">' + fileName + '</td>';
				content += '<td>' + fileSize + '</td>';
				content += '<td><input type="hidden" name="fileExtension" value="' + fileExtension + '">' + fileExtension + '</td>';
				content += '<td class="hide"><input type="hidden" name="filePath" value="' + filePath + '" class="hide">' + filePath + '</td>';
				content += '</tr>';
				
				$infoBody.html(content);

				$('form#fileUploadForm').find('input#file').val('');
				$('span[id=fileName]').text('');
			    $("button#uploadButton").hide();
				$('span[class$=btn-file]').show();
				$('div#uploadInformation').removeClass("hide");
			}
			
			return {
				chooseFile: chooseFile,
				uploadFile: uploadFile
			}
		}
	);
})();
