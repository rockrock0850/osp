/**
 * Copyright (c) 2015 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */

/**
 * 定義JSON 字串的 parameter name
 */
var REQ_PARAM_JSON_DATA = "jsonData";
var REFLASH_TYPE_AJAX = "A";
var REFLASH_TYPE_REFLASH = "R";

var USE_TAB = false; // 是否使用TAB做資料呈現

$(function() 
{
	/**
	 * 字串去除頭尾的空白
	 */
	if (!String.prototype.trim) 
	{
	  String.prototype.trim = function () 
	  {
		  return this.replace(/^\s+|\s+$/g, '');
	  };
	}
	
	/**
	 * 字串不足指定的長度時，自動於左方以指定的字串補足
	 * 
	 * @param padString: 指定的補足字串
	 * @param length: 指定的字串長度
	 */
	String.prototype.leftPad = function (padString, length) 
	{
	    var str = this;
	    while (str.length < length) 
	    {
	    	str = padString + str;
	    }
	    
	    return str;
	};
	
	/**
	 * 字串不足指定的長度時，自動於右方以指定的字串補足
	 * 
	 * @param padString: 指定的補足字串
	 * @param length: 指定的字串長度
	 */
	String.prototype.rightPad = function (padString, length) 
	{
	    var str = this;
	    while (str.length < length) 
	    {
	    	str += padString;
	    }
	    
	    return str;
	};
	
	/**
	 * 將字串加入千分位
	 */
	String.prototype.addComma = function()
	{	
		var n = '';
		for(var i = 0 ; i < this.length ; i++)
		{
			n += this[i];
		}
				
		n = n.trim();
		if (!/^((-*\d+\.?\d*)|(0))$/.test(n))
		{
			var newValue = /^((-*\d+\.?\d*)|(0))$/.exec(n);
			
			if(n.match(/^\./)) // TODO 0.123 -> .123 (先不強制歸零) 
			{ 
				return n;
			}
			else if (newValue != null)
			{
				if (parseFloat(newValue, 10))
				{
					n = newValue;
				}
				else
				{
					n = '0';
				}
			}
			else
			{
				n = '0';
			}
		}
		
		if (parseFloat(n, 10) == 0)
		{
			if (n.match(/\.$/)) // TODO 避免無法輸入小數點  0.
			{ 
				n = '0.';
			}
			else if(n.split('.').length == 2) // 數字含有小數點. ex: 0.0...
			{
				if(n.split('.')[1].indexOf('0')!= -1) // 小數位數含有0
				{
					n = '0.' + n.split('.')[1];
				}
			}
			else 
			{
				n = '0';
			}
		}
		else
		{
			if(n.match(/\.$/)){ // TODO 避免無法輸入小數點  0.
				var tag = n.split('.');
				n = parseFloat(tag[0], 10).toString() + '.';
			}
			else if(n.split('.').length == 2) // 數字含有小數點. ex: 10.0...
			{
				if(n.split('.')[1].indexOf('0')!= -1) // 小數位數含有0
				{
					n = n.split('.')[0] + '.' + n.split('.')[1];
				}
			}
			else{
				n = parseFloat(n, 10).toString();
			}
		}
		
		n += '';
		var arr = n.split('.');
		var re = /(\d{1,3})(?=(\d{3})+$)/g;
		return arr[0].replace(re, '$1,') + (arr.length == 2 ? '.' + arr[1] : '');  
	};
	
	/**
	 * 將字串移除千分位
	 */
	String.prototype.removeComma = function()
	{
		var n = '';
		for(var i = 0 ; i < this.length ; i++)
		{
			n += this[i];
		}
		return n.replace(/[,]+/g, '');
	};
	
	/**
	 * 字串是否為數字
	 */
	String.prototype.isNumeric = function()
	{
		var n = '';
		for(var i = 0 ; i < this.length ; i++)
		{
			n += this[i];
		}
		
		n = n.trim();
		
		return /^((-*\d+)|(0))$/.test(n);
	};
	
});

/**
 * jQuery DataTable MENU縮小時，版面調整大小
 */
function handleDataTableAdjustColumnSizing() 
{	
	var table = $.fn.DataTable.fnTables(true);
	
	if (table.length > 0) 
	{	
		var info = $(table).DataTable().page.info();
		var pageNumber = info.page;
		
		$(table).DataTable().columns.adjust().draw();
		
		if(pageNumber != 0) //  第一頁不做這件事情
		{
			$(table).DataTable().page(pageNumber).draw('page');
		}
	}
}

/**
 * 關閉 jQueryUI - Dialog
 */
function closeDialog(dialogId)
{
	$('#' + dialogId).dialog('close');
}

/**
 * 刪除於記憶體中所有 jQuery DataTable （js）物件
 */
function destroyAllDataTables() 
{
	var table = $.fn.dataTable.fnTables();
	$.each(table, function()
	{	
		$(this).DataTable().destroy();
	})
}

/**
 * 依據畫面點選的功能選項，先
 * 1、整頁畫面更新
 * 2、導入功能頁
 */
function getContentByMenuId(src)
{
	var $target = $(src);
	var reflashType = $target.attr('menu-reflash-type');
	var url = $target.attr('menu-url');
	var menuId = $target.attr('menu-id');
	
	if(reflashType === REFLASH_TYPE_REFLASH)
	{	
		var $form = $('<form />');
		$form.attr
		({
			method : 'POST',
			action : url,
			'class'  : 'hide'
		});
		
		var $param = $('<input />');
		$param.attr
		({
			type : 'hidden',
		    name : 'jsonData',
		    value : JSON.stringify({menuId : menuId})
		});
		
		$param.appendTo($form);
		$form.appendTo($('body:last')).html();
		
		$form.submit();
		
		$('div#waitDialog').waitDialog('與伺服器連線中，請稍後...');
	}
	else 
	{	// 若取不到值則預設為「AJAX」 換頁 
		getContentByAJAX(src);
	}
}

function getContentByAJAX(src)
{	
	var $target = null;
	
	if(typeof src === 'string')
	{
		$target = $('a[menu-id="' + src + '"]');
	}
	else
	{
		$target = $(src);
	}
	
	// 表示已經有點開其功能頁
	var isOpen = $target.attr('isOpen');
	if(isOpen == 'true')
	{
		return;
	}
	
	var url = $target.attr('menu-url');
	var menuId = $target.attr('menu-id');
	var menuNm = $target.attr('menu-name');
	var settings = 
	{
    	URL : url,
    	showOperationMessage: false,
    	dataType : 'html',
    	success: function(data)
    	{	
    		// 需在重新繪制畫面前先刪除
    		destroyAllDataTables();
    		// 將 jQuery DataTable 設定為不可強制換頁
    		$.fn.DataTable.ARAP_TOOLS.setForceChange(false);
    		
    		if(USE_TAB)
			{
    			reflashTabContentDiv(menuId, menuNm, data);
    			$target.attr('isOpen', 'true');
			}
    		else 
			{
    			reflashContentDiv(data);
			}
    	}
	};
	
	$.formAjax(settings);
}

/**
 * 將選單內容以「TAB」方式呈現
 */
function reflashTabContentDiv(menuId, menuNm, data)
{	
	var $divFrame = $('div#iFrame');
	var hasTab = $divFrame.attr('hasTab');
	if(hasTab == 'false')
	{
		var initTab = $('div#tabCntDiv').clone();
		reflashContentDiv(initTab.html());
		$divFrame.attr('hasTab', 'true');
	}
	
	var tabHTML = '<li class=\"active\" tabMenuId=\"' + menuId +'\"><a href=\"#' + menuId + '\" data-toggle=\"tab\">' + menuNm +
				  '<span class=\"ui-icon ui-icon-closethick\" spanMenuId=\"' + menuId 
				  + '\" style=\"position: relative;float:right;margin-top:2px;cursor:pointer;\" onclick="closeTabAndContent(this);"></span></a></li>';
	var tabCntHTML = '<div class=\"tab-pane fade active in\" id=\"' + menuId + '\"></div>';
	
	var $ulTab = $('ul#navTabsUl');
	// 清掉其它
	$ulTab.find('li[class="active"]').removeClass('active');
	$ulTab.append($(tabHTML));
	
	var $tabCntDiv = $('div#tabCntDiv');
	// 拿掉其它 active class 設定
	$tabCntDiv.children('div').removeClass('active');
	$tabCntDiv.append($(tabCntHTML));
	
	reflashContentDivById(menuId, data);	
}

/**
 * 將TAB、TAB_CONTENT資料給清除 
 */
function closeTabAndContent(src)
{
	var $target = $(src);
	showConfirmBootBox(function()
	{
		var menuId = $target.attr('spanMenuId');
		var $navTabs = $('ul#navTabsUl');
		// tab
		$('li[tabMenuId="' + menuId + '"]', $navTabs).remove();
		// content
		$('div#tabCntDiv div#' + menuId).remove();
		
		// 將移除後第一個tab做顯示
		$navTabs.find('li').eq(0).children('a').click();
		
		// 將 menu的isOpen設定為false
		$('ul.page-sidebar-menu a[menu-id="' + menuId + '"]').attr('isOpen', 'false');
		
		
	}, '確認是否關閉');
	
}

/**
 * 將 div id="iFrame" 內容全部更新
 */
function reflashContentDiv(data) 
{
	reflashContentDivById("iFrame", data);
}

/**
 * 將 div 以id為準 內容全部更新
 */
function reflashContentDivById(id, data) 
{
	$('div[id="' + id + '"]').empty().html(data);
}

/**
 * 以 BootBox 方式顯示成功訊息
 */
function showSuccessBootBox(callBack)
{
	showSuccessBootBoxCustom(callBack, "操作成功");
}

/**
 * 以 BootBox 方式顯示成功訊息, 並傳入自訂義訊息
 */
function showSuccessBootBoxCustom(callBack, customMessage)
{
	var $successDiv = $('div[id="successBootBox"]');
	$('span[id="msgSpan"]', $successDiv).empty().html(customMessage);
	
	bootbox.alert({
		backdrop : true,
		closeButton : false,
	    message: $successDiv.html(),
	    buttons: {//更改按鈕名稱"OK"為中文
            ok: {
                label: "確定",
            }
        },
	    callback: callBack || function(){ /* your callback code */ }
	});
}

/**
 * 以 BootBox 方式顯示警示訊息
 */
function showWarringBootBox(warringMsg, callBack)
{
	var $warringDiv = $('div[id="warringBootBox"]');
	$('span[id="msgSpan"]', $warringDiv).empty().html(warringMsg);
	
	bootbox.alert({
		backdrop : true,
		closeButton : false,
	    message: $warringDiv.html(),
	    buttons: {//更改按鈕名稱"OK"為中文
            ok: {
                label: "確定",
            }
        },
	    callback: callBack || function(){ /* your callback code */ }
	});

}

/**
 * 以 BootBox 方式顯示失敗訊息
 */
function showErrorBootBox(errMessage, callBack)
{
	// http://stackoverflow.com/questions/22841625/bootbox-dont-defined-with-requirejs
	require(['bootbox'], function(bootbox)
	{
		$('span[id="msgSpan"]').empty().html(errMessage);
	
		var $failDiv = $('div[id="failBootBox"]');	
		bootbox.alert({
			backdrop : true,
			closeButton : false,
			message: $failDiv.html(),
			buttons: {//更改按鈕名稱"OK"為中文
				ok: {
					label: "確定",
				}
			},
			callback: callBack || function(){ /* your callback code */ }
		});
	});	
}

/**
 * 顯示「請確認是否刪除？」確認方塊
 * 
 * @param confirmFunction
 */
function showRemoveConfirmBootBox(confirmFunction) 
{
	showConfirmBootBox(confirmFunction, "是否刪除？");
}

/**
 * 傳入自訂顯示訊息的確認方塊
 */
function showConfirmBootBox(confirmFunction, message)
{
	$('span[id="msgSpan"]').empty().html(message);

	var $warningDiv = $('div[id="confirmBootBox"]');

	bootbox.confirm($warningDiv.html(), function(result) {
		if (result)
		{
			confirmFunction();
		}
	});
}

/**
 * 傳入自訂顯示訊息、點選「是」callback function
 * 
 * @param yesConfirmFunction
 * @param message
 */
function showConfirmDialogView(yesConfirmFunction, message) 
{
	$('span[id="msgSpan"]').empty().html(message);

	var $warningDiv = $('div[id="confirmBootBox"]');
	
	bootbox.dialog(
	{
		closeButton: false,
		message: $warningDiv.html(),
		title: "",
		buttons: 
		{
			main: 
			{
				label: "確認",
				className: "btn btn-primary",
				callback: function() 
				{
					yesConfirmFunction();
				}
			},
			cancel: 
			{
				label: "取消",
				className: "btn btn-default"
			}	
		}
	});
}

/**
 * 傳入自訂顯示訊息、點選「是」、「否」的callback function
 * 
 * @param yesConfirmFunction
 * @param message
 */
function showConfirmCustomDialogView(yesConfirmFunction, cancelConfirmFunction, message) 
{
	$('span[id="msgSpan"]').empty().html(message);

	var $warningDiv = $('div[id="confirmBootBox"]');
	
	bootbox.dialog(
	{
		message: $warningDiv.html(),
		title: "",
		buttons: 
		{
			main: 
			{
				label: "確認",
				className: "btn btn-primary",
				callback: function() 
				{
					yesConfirmFunction();
				}
			},
			cancel: 
			{
				label: "取消",
				className: "btn btn-default",
				callback: function() 
				{
					cancelConfirmFunction();
				}
			}	
		}
	});
}

/**
 * 左右方塊選單 控制物件
 *
 * @param srcSelId   : 可選擇 select id
 * @param assignSelId: 已選擇 select id
 */
function Selected(srcSelId, assignSelId)
{
	var _inner = 
	{
		srcRoleSelect : $('select#' + srcSelId),
		assignRoleSelect : $('select#' + assignSelId),
		assign : function()
		{
			var assignSt = $('option:selected', this.srcRoleSelect);
			this._processInternal(this.assignRoleSelect, assignSt);
			this.bindOptionEvent();
		},
		assignAll : function()
		{
			var assignSt = $('option', this.srcRoleSelect);
			this._processInternal(this.assignRoleSelect, assignSt);
			this.bindOptionEvent();
		},
		unassign : function()
		{
			var assignSt = $('option:selected', this.assignRoleSelect);
			this._processInternal(this.srcRoleSelect, assignSt);
			this.bindOptionEvent();
		},
		unAssignAll : function()
		{
			var assignSt = $('option', this.assignRoleSelect);
			this._processInternal(this.srcRoleSelect, assignSt);
			this.bindOptionEvent();
		},
		bindOptionEvent : function()
		{
			$('option', this.srcRoleSelect).unbind('dblclick').on('dblclick', function()
			{	
				_inner.assign();
			});
			$('option', this.assignRoleSelect).unbind('dblclick').on('dblclick', function()
			{	
				_inner.unassign();
			});
		},
		_processInternal : function(appendTo, assignSt)
		{
			appendTo.append(assignSt.clone());
			assignSt.remove();
		}
	};
	
	// select 中的 option dbclick時候，做選取或取消
	_inner.bindOptionEvent();
	
	return _inner;
}

/**
 * 左右方塊選單-群組化 控制物件
 *
 * @param srcSelId   : 可選擇 select id
 * @param assignSelId: 已選擇 select id
 */
function GroupSelected(srcSelId, assignSelId)
{	
	var _inner = 
	{
		srcRoleSelect : $('select#' + srcSelId),
		assignRoleSelect : $('select#' + assignSelId),
		assign : function()
		{
			var assignSt = $('option:selected', this.srcRoleSelect);
			this._processInternal(this.assignRoleSelect, this.srcRoleSelect, assignSt);
			// 清除已選擇的對象
			assignSt.remove();
			
			this._clear(this.srcRoleSelect);
			this.bindOptionEvent();
		},
		assignAll : function()
		{
			var assignSt = $('option', this.srcRoleSelect);
			this._processInternal(this.assignRoleSelect, this.srcRoleSelect, assignSt);
			// 清除已選擇的對象
			assignSt.remove();
			
			this._clear(this.srcRoleSelect);
			this.bindOptionEvent();
		},
		unassign : function()
		{
			var assignSt = $('option:selected', this.assignRoleSelect);
			this._processInternal(this.srcRoleSelect, this.assignRoleSelect, assignSt);
			// 清除已選擇的對象
			assignSt.remove();
			
			this._clear(this.assignRoleSelect);
			this.bindOptionEvent();
		},
		unAssignAll : function()
		{
			var assignSt = $('option', this.assignRoleSelect);
			this._processInternal(this.srcRoleSelect, this.assignRoleSelect, assignSt);
			// 清除已選擇的對象
			assignSt.remove();

			this._clear(this.assignRoleSelect);
			this.bindOptionEvent();
		},
		bindOptionEvent : function()
		{
			$('option', this.srcRoleSelect).unbind('dblclick').on('dblclick', function()
			{	
				_inner.assign();
			});
			$('option', this.assignRoleSelect).unbind('dblclick').on('dblclick', function()
			{	
				_inner.unassign();
			});
		},
		_processInternal : function(appendTo, before, assignSt)
		{	
			var assignStCp = assignSt.clone();
			$.each(assignStCp, function()
			{
				var $opt = $(this);
				
				var groupId = $opt.attr('groupId');
				var $optgroup = $('optgroup[groupId="' + groupId + '"]', appendTo);
				if(typeof $optgroup == 'undefined' || $optgroup.length == 0) // 找不到
				{
					$optgroup = $('optgroup[groupId="' + groupId + '"]', before).clone().empty();
					appendTo.append($optgroup);
				}
				
				$optgroup.append($opt);
			});
		},
		_clear : function(clearTarget)
		{	
			$('optgroup', clearTarget).each(function()
			{
				var $optGrp = $(this);
				var $opt = $('option', $optGrp);
				if($opt.length == 0)
				{
					$optGrp.remove();
				}
			});
		}
	};
	
	// select 中的 option dbclick時候，做選取或取消
	_inner.bindOptionEvent();
	
	return _inner;
}

/**
 * 針對數字欄位動態加入千分位
 * 
 * @param input
 */
function dynamicAddComma(input) 
{
	var $input = $(input);
	var insertPos = input.selectionEnd; // 新增字元的位置
	var totalLen = $(input).val().length; // 原本字串長度
	var newPos;
	var newVal = $input.val().removeComma(); // removeComma
	
	$input.val(newVal.addComma()); // addComma
	
	var newLen = $input.val().length; // 新增後字串長度
	
	if(totalLen == newLen) // 判斷input游標位置
	{
		newPos = insertPos;
	}
	else
	{
		if(totalLen > newLen)
		{
			newPos = insertPos - 1;
		}
		else
		{
			newPos = insertPos + 1;
		}
	}
	
	setInputTargetPos(input, newPos);
}

/**
 * 綁定input游標位置
 */
function setSelectionRange(input, selectionStart, selectionEnd) 
{
	  if (input.setSelectionRange) // HTMLInputElement.setSelectionRange
	  {
	    input.focus();
	    input.setSelectionRange(selectionStart, selectionEnd);
	  }
	  else if (input.createTextRange)  // HTMLInputElement.createTextRange
	  {
	    var range = input.createTextRange();
	    range.collapse(true);
	    range.moveEnd('character', selectionEnd);
	    range.moveStart('character', selectionStart);
	    range.select();
	  }
}

/**
 * 設定input游標位置
 */
function setInputTargetPos (input, pos) 
{
	setSelectionRange(input, pos, pos);
}

/**
 * 顯示「文件」頁面
 */
function showDocumentView(documentId) {
	$.formSubmit({
		action : context + '/document/documentdisplay/showDocumentView.do',
		dataProvider : function() {
			var param = {};
			param.documentId = documentId;
			param.prevURL = currentURL;	// request keep param
			   
			return param;
		}
	});
}

/**
 *  返回上一頁(歷史紀錄)全文檢索可用
 */
function backToPrevious() {
	history.back();
}

/**
 * ajax檔案上傳 (暫存至server)
 *
 * 依據傳入參數 將div內所有input file內files 以FormData方式傳送至server暫存 
 * 
 * @param id  : upload Div Id
 * @param removeFileArray  : remove File Name Array
 * 
 * @return object : 文件暫存路徑與檔名
 */
function soaAjaxFileUpload(id, removeFileArray) {
	var fileData = null;
    var uploadData = new FormData();
    var $inputFile = $("#"+ id +"").find('input[type="file"]');

    if ($inputFile.length > 0) {
        for (var i = 0; i < $inputFile.length ; i++) {
            var inputFileList = $inputFile[i].files;

            $.each(inputFileList, function(i, file) {
                var fileName = file.name;

                if (removeFileArray != null) {
	                if ($.inArray(fileName, removeFileArray) == -1) {
	                    uploadData.append('upload', file);
	                }
                } else {
                	uploadData.append('upload', file);
                }
            });
        }

        $.ajax({
            type : 'POST',
            url : context + "/generic/file/fileUpload.do",
            data : uploadData,
            cache : false,
            async: false,
            contentType : false,
            processData : false,
            success : function(data) {
                if (data != "") {
                	fileData = JSON.parse(data);
                }
            }
        });
    }

    return fileData;
}

/**
 * ajax檔案上傳 for BootBox (暫存至server)
 * 由於BootBox內的物件必須在id前面指定class才可以抓到正確的物件 By AndrewLee
 *
 * 依據傳入參數 將div內所有input file內files 以FormData方式傳送至server暫存 
 * 
 * @param id  : upload Div Id
 * @param removeFileArray  : remove File Name Array
 * 
 * @return object : 文件暫存路徑與檔名
 */
function soaAjaxFileUploadInsideBootBox(id, removeFileArray) {
	var fileData = null;
	var uploadData = new FormData();
	//".modal-body"後面的" ".不能刪.刪除的話會抓不到值!
	var $inputFile = $(".modal-body" + " " + "#" + id).find('input[type="file"]');
	
	if ($inputFile.length > 0) {
		for (var i = 0; i < $inputFile.length ; i++) {
			var inputFileList = $inputFile[i].files;
			
			$.each(inputFileList, function(i, file) {
				var fileName = file.name;
				
				if (removeFileArray != null) {
					if ($.inArray(fileName, removeFileArray) == -1) {
						uploadData.append('upload', file);
					}
				} else {
					uploadData.append('upload', file);
				}
			});
		}
		
		$.ajax({
			type : 'POST',
			url : context + "/generic/file/fileUpload.do",
			data : uploadData,
			cache : false,
			async: false,
			contentType : false,
			processData : false,
			success : function(data) {
				if (data != "") {
					fileData = JSON.parse(data);
				}
			}
		});
	}
	
	return fileData;
}
