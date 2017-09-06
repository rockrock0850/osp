require([ 'cont0070-service','osp-ui-app','small-modal' ,'form2object',
          'osp-util','flip', 'common-util', 'cookie','jquery.dataTables','DT_bootstrap'], function(cont0070_Service) {
	
	$("input[name='file']").on('change', function(e) {
		var id = $(this).attr("excelType");

		cont0070_Service.chooseFile(id);
	});
	
	$("div #uploadButton").click(function() {
		cont0070_Service.uploadFile();
	});
	
	$("div #beginCompareBtn").click(function() {
		cont0070_Service.beginCompareExcel();
	});
	
	$("div #queryCompareResultBtn").click(function() {
		cont0070_Service.getCompareResult()
	});
	
	$("#searchResult").on("click" , ".aimsOrderId", function() {
		cont0070_Service.showAimsOrder(this);
	});

});

//------------------------------------------------------------



(function() {
	define('cont0070-service', ['osp-ui-app', 'small-modal'],function() {
	// 檢查是否有上傳過檔案的開關
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
			
			$fileName.text(name);
	
			// 透過尋找兩個檔案的資料是否已經有名字.若有代表有檔案.顯示被隱藏的上傳按鈕
			var aimsFileName = $("div #AIMSfile")[0].files[0].name
			var vposFileName = $("div #VPOSfile")[0].files[0].name
			
			if(vposFileName != null && aimsFileName != null) {
				$uploadButton.show();
			}
			
		} catch(error) {
			/* 注意: 當選擇檔案時, 若按下取消鍵, name會拋exception, 利用此關係來觸發上傳按鈕的顯示與否 by Andrew */
			$uploadButton.hide();
		}
		// End Upload
	}
	
	//宣告兩份檔案的名稱
	var vposFileName = "";
	var aimsFileName = "";
	
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
		        url: contextPath + '/flow/content/upload-vpos-excel.action',
		        type: 'POST',
		        data: formData,
	            cache : false,
	            async: false,
		        contentType: false,
		        processData: false,
		        success: function () {
		        	// 開關.若有上傳過Excel才會為true.並且允許執行後續的核對功能
		        	uploaded = true;

					var content = '<center><label>' + $.getSuccessMessage('006') + '</label></center>';
					var buttons = {
						cancelButton: {
							value: '確認'
						}
					}
		        	$.messageModal('訊息', content, buttons);

					//這兩個值用於後面開始比對後.根據這個名稱來找到對應的檔案
					vposFileName = files[0].files[0].name
					aimsFileName = files[1].files[0].name
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
	
	/**
	 * 開始比對Excel
	 * 
	 */
	var beginCompareExcel = function() {
		// 檢查是否有成功上傳過Excel
		if(!uploaded) {
			var content = '<center><label>注意.若沒上傳過Excel.則無法進行資料比對</label></center>';
			var buttons = {
				cancelButton: {
					value: '確認'
				}
			}
        	$.messageModal('警告', content, buttons);
			
			return false;
		}
		
		var settings = {
				dataType: 'html',
				httpMethod: 'POST',
				URL: contextPath + '/flow/content/begin-vpos-compare.action',
				dataProvider: function() {
					//將上傳Excel後所塞入的fileName.放入params的物件中.準備傳到後端
					var params = {};
					params.vposFileName = vposFileName;
					params.aimsFileName = aimsFileName;
					
					return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(params));
			    }, 
			    success: function () {
					var content = '<center><label>系統開始比對,需要一段時間執行,請稍後才可進行查詢VPOS核帳結果</label></center>';
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
			};
		
		$.formAjax(settings);
	}
	
	var browserHeight = $(window).height();
	var browserWidth = $(window).width();
	var screenWidth = window.screen.availWidth;  
	
	/**
	 * 顯示AIMS單號
	 * FIXME 等需求完善後在修改成共用的開窗function
	 * 
	 */
	var showAimsOrder = function(target) {
		var aimsOrderId = $(target).html();
		console.info(aimsOrderId);
		
		var url = "https://tw.yahoo.com/";//FIXME 
		
		$.openPopUpWindow(url,browserHeight,browserWidth,screenWidth);
	}
	
	/**
	 * 取得比對結果
	 * 
	 */
	var isRepeatCallDataTable = false;// 檢查是否有呼叫過Datatable
	
	var getCompareResult = function() {
		// 如果呼叫過.則先destroy()
		if(isRepeatCallDataTable) {
			$("#searchResultDataTable").DataTable().destroy();
		}
		
		$.ajax({
	        url: contextPath + '/flow/content/get-compare-result.action',
	        type: 'POST',
	        success: function (data) {
	        	isRepeatCallDataTable = true;
	        	
	        	data = $.parseJSON(data);
	        	
	        	$("#searchResultDataTable").DataTable({
					dom: '<"row"lf><"row"tr><"row"ip>',
					paging: true, // 翻頁功能
					lengthChange: true, // 改變每頁顯示數據數量
					pageLength: 10, // 顯示10筆換下一頁
					searching: false, // 過濾功能
					ordering: true, // 排序功能
					info: true, // 頁腳信息
					autoWidth: false, // 自動寬度
					data: data, // 注入資料
					columnDefs: [ // 欄位設定
						{orderable: false, targets: [0]}, // 選擇關閉哪個欄位排序功能(  )
						{targets: [0], title: 'VPOS授權碼 ', data: 'VPosAuthCode'}, 
						{targets: [1], title: 'VPOS備註', data: 'VPosRemark'},
						{targets: [2], title: 'VPOS交易金額', data: 'VPosMoney'},
						{targets: [3], title: 'VPOS分期數', data: 'VPosNumberPeriods',defaultContent : ''},
						{targets: [4], title: 'AIMS單號', data: 'aimsOrderId' ,className :'hover aimsOrderId'},
						{targets: [5], title: 'AIMS門號', data: 'aimsMsisdn'},
						{targets: [6], title: 'AIMS授權碼', data: 'aimsAuthCode'},
						{targets: [7], title: 'AIMS實際付款金額', data: 'aimsActualPayment'},
						{targets: [8], title: '比對結果', data: 'compareResult'}
					]
				});
	        	
	        	//移除掉th的class
				$("th").removeClass("hover aimsOrderId");
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
	        }});
	}
	
	return {
		chooseFile : chooseFile,
		getCompareResult : getCompareResult,
		beginCompareExcel : beginCompareExcel,
		getCompareResult : getCompareResult,
		uploadFile : uploadFile,
		showAimsOrder : showAimsOrder
	}
})})()