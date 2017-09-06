require([ 'cont0068-service','osp-ui-app','small-modal' ,'form2object',
          'osp-util','flip', 'common-util', 'cookie',], function(cont0068_Service, uiApp) {
	
	$("input[name='file']").on('change', function(e) {
		var id = "uploadContent";

		cont0068_Service.chooseFile(id);
	});
	
	$("div #uploadButton").click(function() {
		cont0068_Service.uploadFile();
	});

});

//----------------------------------------------------------------

(function() {
	define('cont0068-service', [ 'small-modal'],function() {
	
	//檢查是否有上傳過檔案的開關
	var uploaded = false;
	
	/**
	 * 驗證EXCEL
	 */
	var chooseFile =  function(id) {
		/* Excel xls & xlsx Type */
		var xlsxType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		var xlsType = "application/vnd.ms-excel";
		
		var $fileName = $('#' + id + ' ' + 'span#fileName');
		var $input = $('#' + id + ' ' + 'input[type="file"]');
		var $uploadButton = $("button#uploadButton");
		var $chooseButton = $('#' + id + ' ' + 'span[class$=btn-file]');
		
		$fileName.text("");
		try {
			/* 注意: 不知道為什麼input一定要加上[0]才能選到檔案清單物件 */
			var name = $input[0].files[0].name;
	    	var type = $input[0].files[0].type;
	    	
			if(type != xlsType && type != xlsxType) {
				throw $.getErrorMessage(007);
			}
			
			if(name != null) {
				$fileName.text(name);
				$uploadButton.show();
			}
			
		} catch(error) {
			/* 注意: 當選擇檔案時, 若按下取消鍵, name會拋exception, 利用此關係來觸發上傳按鈕的顯示與否 by Andrew */
			$uploadButton.hide();
		}
		// End Upload
	}
	
	
	/* 上傳檔案 */
	var uploadFile = function() {
		var $fileName = $('span#fileName');
		var formData = new FormData();
		var files = $("input[type='file']");// 多個file的陣列
		
		$.each(files,function(i) {
			// 因為每個input file都只會有一個檔案.所以將第i個file中的第一個檔案放入formData
			formData.append("file",files[i].files[0]);
		});
		
	    var $uploadButton = $("button#uploadButton");
		var $chooseButton = $('span[class$=btn-file]');
	    if(!$.isEmptyObject(formData)) {
		    $.ajax({
		        url: contextPath + '/flow/content/upload-ip-config.action',
		        type: 'POST',
		        data: formData,
	            cache : false,
	            async: false,
		        contentType: false,
		        processData: false,
		        success: function () {
		        	//開關.若有上傳過Excel才會為true.並且允許執行後續的核對功能
		        	uploaded = true;
					
					var content = '<center><label>' + $.getSuccessMessage('006') + '</label></center>';
					var buttons = {
						cancelButton: {
							value: '確認'
						}
					}
		        	$.messageModal('訊息', content, buttons);
		        }, 
		        error: function(error) {
		        	console.log(error);
					var content = '<center><label>' + $.getErrorMessage('006') + '</label></center>';
					var buttons = {
						cancelButton: {
							value: '確認'
						}
					}
		        	$.messageModal('警告', content, buttons);
		        }
		    });
	
	    } else {
			var content = '<center><label>' + $.getErrorMessage('006') + '</label></center>';
			var buttons = {
				cancelButton: {
					value: '確認'
				}
			}
        	$.messageModal('警告', content, buttons);
	    }
	    
		$uploadButton.hide();
		$chooseButton.show();
		$fileName.text('');
		files.val('');
	}
	
	return {
		chooseFile : chooseFile,
		uploadFile : uploadFile
	}
})})()