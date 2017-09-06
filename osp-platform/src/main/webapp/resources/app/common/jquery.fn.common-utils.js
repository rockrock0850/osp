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
var REQ_PARAM_JSON_DATA = "jsonData";

(function( $, undefined ) {
	
	// =================================================================
	//                  透過 jQuery Plugin方式將特定function開放使用
	// =================================================================
	
	/**
	 * 依據 url 屬性，取得內容
	 */
	$.fn.getContent = function()
	{
		var orderMId = $('div#buzFlow').attr('orderMId');
		var contentId = $(this).attr('id');
		var url = $(this).attr('url');
		
		if (url)
		{
			var settings =
			{
				dataType : 'html',
				URL : contextPath + url + '?contentId=' + contentId + '&orderMId=' + orderMId,
				success: function(data)
				{
					var $content = $('div#' + contentId);
					$content.empty().html(data);
				}
			};
			
			$.formAjax(settings);
		}
	};
	
	/**
	 * 資料驗証
	 */
	$.fn.validate = function(settings) 
	{
		var $src = $(this);
		installValidation($src, settings || {});
	}
	
	/**
	 * 初使化選單
	 */
	$.fn.initMenu = function()
	{
		initMenu(this);
	};
	
	/**
	 * 初使化類似 Portlet 功能（如：縮放）
	 */
	$.fn.portlet = function(settings)
	{	
		var container = $(this);
		settings = settings || {};
		handlePortletTools(container, settings);
	};
	
	/**
	 * 初使化「返回頁首」功能
	 */
	$.goTop = function()
	{
		initHandleGoTop();
	};
	
	/**
	 * 「Ajxa」將表單送出
	 */
	$.formAjax = function(settings) 
	{
		return formAjax(settings || {});
	};
	
	/**
	 * 「formSubmit」將表單送出
	 */
	$.formSubmit = function(settings) 
	{
		formSubmit(settings || {});
	};
	
	/**
	 * 驗証特定區塊欄位資訊
	 */
	$.validate = function(settings)
	{	
		settings = $.extend({}, formAjaxDefaultSettings, settings || {});
		
		var isPass = validateDataArea(settings);
		if(isPass == false)
		{	
			showValidateFailMessage(settings);
			return false;
		}
	};
	
	/**
	 * 清除驗証失敗特定的CSS設定
	 */
	$.removeValidateFailCssClass = function(settings)
	{
		removeValidateFailCssClass(settings);
	};
	
	/**
	 * 產生並顯示 loading 圖示 dialog
	 */
	$.fn.waitDialog = function(msg)
	{	
		return waitDialog($(this), msg);
	};
	
	/**
	 * 產生操作訊息列
	 */
	$.fn.operationMessage = function(divClass, headMsg, bodyMsg, isAutoClose, settings) 
	{	
		settings = settings || {};
		settings.container = $(this);
		
		operationMessage(divClass, headMsg || '', bodyMsg || '', isAutoClose || true, settings);
	};
	
	/**
	 * 產生成功訊息列
	 */
	$.fn.successMessage = function(headMsg, bodyMsg, settings) 
	{
		settings = settings || {};
		settings.container = $(this);
	
		operationMessage('alert alert-success', headMsg || '', bodyMsg || '', true, settings);
	};
	
	/**
	 * 產生失敗訊息列
	 */
	$.fn.errorMessage = function(headMsg, bodyMsg, settings) 
	{	
		settings = settings || {};
		settings.container = $(this);
		
		operationMessage('alert alert-error', headMsg || '', bodyMsg || '', false, settings);
	};
	
	/**
	 * 產生通知訊息列
	 */
	$.fn.infoMessage = function(headMsg, bodyMsg, settings) 
	{
		settings = settings || {};
		settings.container = $(this);
		
		operationMessage('alert alert-info', headMsg || '', bodyMsg || '', true, settings);
	};
	
	/**
	 * 產生注意訊息列
	 */
	$.fn.alertMessage = function(headMsg, bodyMsg, settings) 
	{
		if (typeof(bodyMsg) == 'object') 
		{
			settings = bodyMsg;
			bodyMsg = '';
		}
		
		settings = settings || {};
		settings.container = $(this);
		
		operationMessage('alert', headMsg || '', bodyMsg || '', true, settings);
	};
	
	/**
	 * 關閉訊息列
	 */
	$.fn.closeMessageBar = function() 
	{
		$(this).hide().html('');
	};
	
	/**
	 * 對checkbox全選/item 做動作處理
	 */
	$.checkboxItem = function(settings) 
	{
		processCheckboxItem(settings || {});
	};
	
    /**
     * Window Open wrapper
     */
    $.openWin = function(settings)
    {
        openWin(settings || {});
    };
    
	// =================================================================
	//                  以下 function 須透過上述方式註冊似其它頁面使用
	// =================================================================
	
    /**
     * 開啟等待 dialog 
     */
    function waitDialog($container, message) {
    	var alert = function() {
        	var title = '努力載入資料中...請稍後';
    		
    		if(message != null && message != 'undefined' && message != ''){
    			title = message;
    		}
    		
    		$container.dialog({
				title: title,
				modal: true,
				minHeight: 96,
				minWidth: 40,
				resizable: false,
				closeOnEscape: false,
				open : function(){
					var $this = $(this);
					var $parent = $this.parent();
					
					$container.removeClass("hide");
					$container.prev(".ui-dialog-titlebar").css("color", "white");
					$container.prev(".ui-dialog-titlebar").css("background", "#01A9DB");
					
					// 移除關閉等待視窗之按鈕
					$parent.find('.ui-dialog-titlebar-close').remove();
					
					// 移除等待視窗的focus效果
					$parent.addClass('focus_off');
					
					// 關掉 Dialog 旁的「X」
					$parent.find('button[class*="ui-button-icon-only"][role="button"]').hide();
				},
				close: function(event, ui){	
					if(isOpen()){
						close();
					}
				}
			});
    	}
    	
    	var isOpen = function() {
    		if($container.hasClass('ui-dialog-content')) {
    			return $container.dialog('isOpen');
			}
    		
    		return false;
    	}
    	
    	var close = function() {
    		if(isOpen()) {
    			$container.dialog('destroy');
    			$container.addClass("hide");
			}
    	}
		
		return {
			alert: alert, 
			
			isOpen: isOpen, 
			
			close: close
		}
    }
    
    /**
     * 初使化選單 
     */
	function initMenu(container) 
	{
		initHandleSidebarMenu(container);
		handleSidebarToggler(container);
	}
    
	/**
	 * 初始化Menu開闔
	 */
	function initHandleSidebarMenu(container) 
	{
		$(container).on('click', 'li > a', function (e) 
		{
            var parent = $(this).parent().parent();
            var the = $(this);
            
            // 主選單開，其他主選單就關
            parent.children('li.open').children('a').children('.arrow').removeClass('open');
            parent.children('li.open').children('.sub-menu').slideUp(200);
            parent.children('li.open').removeClass('open');

            var sub = $(this).next();
            var slideOffeset = -200;
            var slideSpeed = 200;

            if (sub.is(":visible")) 
            {
                $('.arrow', $(this)).removeClass("open");
                $(this).parent().removeClass("open");
                
                sub.slideUp(slideSpeed, function () 
        		{
                    if ($('body').hasClass('page-sidebar-closed') == false) 
                    {
                    	// 2015-09-15 會議中決定關閉
                        // scrollTo(the, slideOffeset);
                    }
                    
                    handleSidebarAndContentHeight();
                });
            } 
            else 
            {
                $('.arrow', $(this)).addClass("open");
                $(this).parent().addClass("open");
                
                sub.slideDown(slideSpeed, function () 
        		{
                    if ($('body').hasClass('page-sidebar-closed') == false) 
                    {
                    	// 2015-09-15 會議中決定關閉
                        // scrollTo(the, slideOffeset);
                    }
                    
                    handleSidebarAndContentHeight();
                });
            }

			// e.preventDefault();
        });
	};
	
	 /**
	 * Portlet 預定設定
	 */
	var portletDefaultSettings = 
	{
		collapse: function(){},
		expand: function(){}
	};
	
	/**
	 * 畫面收合
	 */
	function handlePortletTools(container, settings)
	{	
		settings = $.extend({}, portletDefaultSettings, settings);
		
		var $target = $('.portlet > .portlet-title > .tools > .collapse, .portlet .portlet-title > .tools > .expand', container);
		$target.unbind('click').bind('click', function (e) {
			e.preventDefault();
			
			var el = $(this).closest(".portlet").children(".portlet-body");
			if ($(this).hasClass("collapse")) {
				$(this).removeClass("collapse").addClass("expand");
				el.slideUp(-1);  // 將delay（el.slideUp(400)）設定拿掉，避免畫面異常
				settings.collapse();
			} else {
				$(this).removeClass("expand").addClass("collapse");
				el.slideDown(-1); // 將delay（el.slideUp(400)）設定拿掉，避免畫面異常
				settings.expand();
			}
		});
	};
	
	/**
	 *  Menu選單收合(左右)
	 */
	function handleSidebarToggler(container)
	{
		var viewport = _getViewPort();
		
		// handle sidebar show/hide
		$(container).on('click', '.sidebar-toggler', function (e) 
		{
			var body = $('body');
			var sidebar = $(container);

			if (body.hasClass("page-sidebar-closed")) 
			{
				body.removeClass("page-sidebar-closed");
			} 
			else 
			{
				body.addClass("page-sidebar-closed");
			}
			
			handleSidebarAndContentHeight(); //fix content & sidebar height
			
			handleDataTableAdjustColumnSizing();
		});
	}

	/**
	 * 初始化置頂
	 */
	function initHandleGoTop() 
	{
		$(window).scroll(function() 
		{
            if ($(this).scrollTop() > 10) 
            {
                $('.go-top').fadeIn(200);
            } 
            else 
            {
                $('.go-top').fadeOut(200);
            }
        });
        
		// animate the scroll to top
        $('.go-top').click(function(event) 
		{
            event.preventDefault();
            $('html, body').animate({scrollTop: 0}, 300);
        })
	}
	
	// Set proper height for sidebar and content. The content and sidebar height must be synced always.
    var handleSidebarAndContentHeight = function () 
    {
        var content = $('.page-content');
        var sidebar = $('.page-sidebar');
        var body = $('body');
        var height;
    }
    
    // wrapper function to scroll(focus) to an element
    var scrollTo = function (el, offeset) 
    {
        pos = (el && el.size() > 0) ? el.offset().top : 0;
        $('html,body').animate({
            scrollTop: pos + (offeset ? offeset : 0)
        }, 'slow');
    }
	
	var _getViewPort = function () 
	{
        var e = window, a = 'inner';
        if (!('innerWidth' in window)) 
        {
            a = 'client';
            e = document.documentElement || document.body;
        }
        
        return {
            width: e[a + 'Width'],
            height: e[a + 'Height']
        }
    }
	
    /**
	 * 預定設定
	 */
	var formAjaxDefaultSettings = 
	{
		beforeSend: function(xhr){},
		httpMethod : 'POST',
		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		dataAreaId: null,
		dataType : 'json',
		URL : null,
		async : true,
		success: function(data){},
		fail: function(jqXHR, textStatus, error){},
		showOperationMessage: false,
		showWaitDialog : true,
		dataProvider: serializeData,
		always : function(){},
		returnAjaxObj : false,
		validate : false,
		validateHTML : 'span',
		validateToken : '[class="red"][required]',
		validateTarget : 'targetId',
		validateFailMessageDiv : null,
		validateFailmessage : "請輸入以下必填欄位",
		validateCustom : function(){return true},
		validateFailCallBack : function(){}
	};
	
	function formAjax(settings)
	{
		settings = $.extend({}, formAjaxDefaultSettings, settings);
				
		if(settings.validate && settings.dataAreaId != null) 
		{
			var isPass = validateDataArea(settings);
			if(isPass == false)
			{	
				showValidateFailMessage(settings);
				return false;
			}
		}			
				
		var $waitDialog = null;
		if(settings.showWaitDialog){
			$waitDialog = $('div#waitDialog').waitDialog();
			
			var isOpen = $waitDialog.isOpen();
			if(!isOpen) {
				$waitDialog.alert();
			}
		}
		
		var obj = $.ajax(
		{
			url : settings.URL,
			contentType : settings.contentType,
			beforeSend : settings.beforeSend,
			type: settings.httpMethod,
			dataType: settings.dataType,
			data: settings.dataProvider(settings.dataAreaId),
			async: settings.async,
		}).done(function(data) {
			if(settings.dataType === 'json' && settings.showOperationMessage) 
			{
				showSuccessBootBox();
			}
			
			settings.success(data);
		}).fail(function(jqXHR, textStatus, error) {
			var code = "";
			var messages = "";
			var stack    = "";
			var appendDetailBtn = false;
			var showDetailBtn = false;
			var responseText = jqXHR.responseText;
			
			if(responseText == '' || responseText == null) 
			{
				code = "JS-999";
				messages = "操作失敗  請聯絡系統管理員";
			}
			else 
			{	
				try
				{	
					var responseObj  = $.parseJSON(responseText);
					code      = responseObj['errCode'];
					messages  = responseObj.errMessage || '';
					stack     = responseObj.excStackTrace || '';
					// appendDetailBtn = true;
					showDetailBtn = true;
				} catch (e)
				{	// can't parse from JSON.
					// show default message without stack button.
					code = "AP-999";
					messages = '系統錯誤 請聯絡系統管理員';
					// appendDetailBtn = false;
				}
			}
			
			messages = " " + messages;
			messages += " (錯誤代碼「" + code + "」) ";

			if(appendDetailBtn)
			{ //btn btn-danger
				messages += " &nbsp;&nbsp;&nbsp; <button onclick='$.openWin();' class='btn btn-danger'>詳細資訊</button>";
			}
			// messages += " <span class='hide' id='errorSpan'>" + stack + "</span>";
			
			if (showDetailBtn) {
				messages += " &nbsp;&nbsp;&nbsp; <button id='detailBtn' onclick='$.showStack();' class='btn btn-danger'>詳細資訊</button>";
				messages += "<span id='stack' class='hide'>" + stack + "</span>";
			}

			showErrorBootBox(messages);
			settings.fail(jqXHR, textStatus, error);
			
			// console.log('操作出現錯誤');
		}).complete(function() {
			if(typeof $waitDialog != 'undefined' && $waitDialog != null && settings.showWaitDialog){
				$waitDialog.close();
				$waitDialog = null;
			}
			
			settings.always();
		});
		
		if(settings.returnAjaxObj)
		{
			return obj;
		}
	};
	
	/**
	 * 顯示stack
	 */
    $.showStack = function()
    {
        $('span#stack').attr("class","");
        $('button#detailBtn').hide();
    };
	
	 /**
	 * 預定設定
	 */
	var formSubmitDefaultSettings = {
		httpMethod : 'POST',
		dataAreaId: null,
		action : null,
		dataProvider: serializeData
	};
	
	function formSubmit(settings) {
		settings = $.extend({}, formSubmitDefaultSettings, settings);
		
		var $form = $('<form />');
		$form.attr({
			method : settings.httpMethod,
		    action : settings.action,
		    'class'  : 'hide'
		});

		var data = settings.dataProvider(settings.dataAreaId);

		if (data != "" && data != null && typeof(data) != "undefined") {
			var $param = $('<input />');
			$param.attr({
				type : 'hidden',
			    name : 'jsonData',
			    value : JSON.stringify(data)
			});
			
			$param.appendTo($form);
		}

		$form.appendTo($('body:last')).html();

		$form.submit();

		$('div#waitDialog').waitDialog('與伺服器連線中，請稍後...').alert();
	};
	
	/**
	 * 顯示驗証失敗訊息
	 */
	function showValidateFailMessage(settings)
	{
		// 在該dataAreaId下新增一個DIV，名為： dataAreaId + 'validateFailDv'再顯示訊錯誤息
		var validateFailMessageDiv = settings.validateFailMessageDiv;
		if(validateFailMessageDiv != null)
		{	
			$('#' + validateFailMessageDiv).errorMessage(settings.validateFailmessage);
		}
		
		settings.validateFailCallBack();
	};
	
	/**
	 * 移除檢核失敗的 CSS 設定
	 */
	function removeValidateFailCssClass(settings) 
	{
		var dataAreaId = settings.dataAreaId;		
		$('[class*="validate-fail"]', '#' + dataAreaId).each(function()
		{	
			// 為避免移除自定義的style 屬性
			$(this).removeClass('validate-fail')
				   .css(
				   {
					   border : '',
					   color  : ''
				   });
		});
		
		// 清除錯誤訊息列
		$('button[data-dismiss="alert"]', 'div#' + settings.validateFailMessageDiv).click();
	};
	
	/**
	 * 檢核區塊中是否有必填資訊
	 */
	function validateDataArea(settings)
	{	
	    // 移除檢核失敗的 CSS 設定
		removeValidateFailCssClass(settings);
		
		var dataAreaId = settings.dataAreaId;
		var validateHTML = settings.validateHTML;
		var validateToken = settings.validateToken;
		var validateTarget = settings.validateTarget;
		
		var $dataArea = $('#' + dataAreaId);
		var isPass = true;
		
		$(validateHTML + validateToken, $dataArea).each(function()
		{
			var targetId = $(this).attr(validateTarget);
			var $targetObj = $('#' + targetId, $dataArea);
			var targetValue = $.trim($targetObj.val()); // 避免有空白
			
			if(typeof $targetObj == 'undefined' || targetValue == '' || targetValue == null) 
			{	
				// span 上一層 Label 變紅色
				var $target = $(this);
				var $parent = $(this).parent();
				var tagName = $parent.prop('tagName');
				if(tagName.toLowerCase() == 'label') 
				{
					$target = $parent;
				}
								
				$target.addClass('red validate-fail');
				$targetObj.addClass('validate-fail')
			    .css(
				{
					border : '1px solid red',
					color  : 'red'
				});
				
				isPass = false;
			}
		});
				
		// 呼叫自訂檢核方式。		
		isPass = isPass && settings.validateCustom();   		
				
		return isPass;
	};
	
	/**
	 * 將將定ID區域的INPUT等表單元件組成 JSON格式
	 */
	function form2Json(dataAreaId)
	{
		if (typeof(dataAreaId) === 'undefined' || dataAreaId === null) 
		{
			return "";
		}
		
		var obj = form2object(dataAreaId);
		return REQ_PARAM_JSON_DATA + '=' +  JSON.stringify(obj);
	};
	
	/**
	 * 將特定的 ID 區域的INPUT等表單元件組成如下：
	 * name=Fred&phone=0987654321&address=中文中文中文
	 * 
	 * @param dataAreaId
	 * @returns
	 */
	function serializeData(dataAreaId) 
	{	
		if (typeof(dataAreaId) === 'undefined' || dataAreaId === null) 
		{
			return "";
		}
		
		return $('#' + dataAreaId).serialize();
	};
	
	/**
	 * 操作結果訊息預設參數
	 */
	var operationMessageDefaultSettings = 
	{
		container : 'div[id="operationMessageDiv"]',
		closeTime : 5000
	};
	
	/**
	 * 產生操作結果訊息
	 *
	 *     divClass : 設定DIV樣式的CSS CLASS NAME
	 *     headMsg  : 
	 *     bodyMsg  : 
	 *     isAutoClose : 是否自動關閉
	 */
	function operationMessage(divClass, headMsg, bodyMsg, isAutoClose, settings) 
	{
		settings = $.extend({}, operationMessageDefaultSettings, settings);
		
		var $messageDiv = $('<div/>');
		var $messageHead = $('<strong>');
		var $messageBody = $('<span>');
		var $closeBtn = $('<button>').attr('class', 'close').attr('data-dismiss', 'alert').text('x');
		
		$messageDiv.append($closeBtn).append($messageHead).append($messageBody);
		$(settings.container).show().html('').append($messageDiv); // append message to messageDiv
		
		$messageDiv.attr('class', divClass);
		$messageHead.text(headMsg);
		
		if (typeof(bodyMsg) === 'string')  // 顯示單一訊息
		{
			$messageBody.text(bodyMsg);
		} 
		else // 顯示多筆訊息 
		{	
			var $ul = $('<ul>');
			
			for (var i = 0 ; i < bodyMsg.length ; i++) 
			{
				var $li = $('<li>');
				$li.html(bodyMsg[i]);
				$li.appendTo($ul);
			};
			
			$messageBody.html($ul);
			// isAutoClose = false;
		}	

		$(settings.container).html($messageDiv.parent().html());
		isAutoClose && setTimeout(function(){ closeMessageBar(settings.container); }, settings.closeTime );
	};
	
	/**
	 * 自動關閉操作結果訊息
	 */
	function closeMessageBar(container) 
	{
		$(container).hide(200, function() 
			{
				$(this).html('');
			}
		);
	};
	
	// =================================================================
	//                  Checkbox 相關動作
	// =================================================================
	var checkboxItemDefaultSettings =
	{
		allProp : 'all',
		itemProp : 'item',
		autoSelFlag : 'selAuto'
	};
	
	function processCheckboxItem(settings) 
	{
		settings = $.extend({}, checkboxItemDefaultSettings, settings);
		
		var $allChkBox = $('input[type="checkbox"][' + settings.allProp + ']');
		
		$allChkBox.unbind('click').unbind('click').bind('click', function() 
			{
				$('input[type="checkbox"][' + settings.itemProp + ']').prop('checked', $(this).prop('checked'));
			}
		);
		
		$('input[type="checkbox"][' + settings.itemProp + ']').unbind('change').bind('change', function() 
			{	
				var $unSelItemCount = $('input[type="checkbox"][' + settings.itemProp + ']');
				var allSelItemCount = $unSelItemCount.size();
				var selItemCount = 0;
				
				$unSelItemCount.each(function()
				{
					var $item = $(this);
					var isCheck = $item.prop('checked');
					if (isCheck) 
					{
					   selItemCount++;
					}
				});
				
				$allChkBox.prop('checked', selItemCount == allSelItemCount);
			}
		);	
	};
	
	// =================================================================
	//                資料驗証
	// =================================================================
	var defaultValidateSettings = 
	{
		validate : 'number',
		errorMessage : '請確認填入內容是否正確',
		errorHTML : '<span/>',
		spanErrorClass: 'red',
		appendTo  : 'after',
		validator : 
		{
			number : function(value)
			{
				var pattern = /^\d+$/;
				return pattern.test(value);
			},
			decimal : function(value)
			{
				var pattern = /^\d+[.]?\d*$/;
				return pattern.test(value);
			},
			email : function(value)
			{
				var pattern = /^[a-zA-Z_0-9]+([.][a-zA-Z_0-9]+)*@[a-zA-Z_0-9]+([.][a-zA-Z_0-9]+)*$/;
				return pattern.test(value);
			}
		}
	};

	/**
	 * 資料驗証實作
	 */
	function installValidation($src, settings) 
	{
		settings = $.extend({}, defaultValidateSettings, settings);
		
		var validate = settings.validator[settings.validate];

		var formGroupId = $src.attr('formGroupId');
		// 清空欄位上所有異常狀態
		$('span[formGroupId="' + formGroupId + '"]').text('');
		$('div[formGroupId="' + formGroupId + '"]').removeClass();
		var validateValue = $src.val();
		
		if(validateValue != '')
		{
			var isSuccess = validate(validateValue);

			if(!isSuccess)
			{
				$src.val('');
				
				$('div[formGroupId="' + formGroupId + '"]').addClass('has-error');
				
				var $span = $('span[formGroupId="' + formGroupId + '"]')
				
				// 若原本span不存在，則創造一個span
				if($.type($span) == 'undefined' || $span.length == 0){

					$span = $(settings.errorHTML).attr(
					{
						'formGroupId' : formGroupId,
						'class' : settings.spanErrorClass
					}).html(settings.errorMessage);

					if(settings.appendTo == 'after')
					{
						$src.parents().eq(0).append($span);
					}
					
					if(settings.appendTo == 'before')
					{
						$src.parents().eq(0).prepend($span);
					}
				}
				
				$span.html(settings.errorMessage);
			}	
		}
	};
	
	// =================================================================
	//                Window.open 實作
	// =================================================================
	var defaultWindowOpenSetting = 
	{
		url : null,
		width: '900px',
		height: '850px',
		location : 'no',
		menubar : 'no',
		status: 'no',
		titlebar: 'no',
		toolbar: 'no',
		directLink : false,
		name: ''
	};
	
	function openWin(settings) 
	{
		settings = $.extend({}, defaultWindowOpenSetting, settings);
		var specs = 'location=' + settings.location; 
		specs += ',menubar=' + settings.menubar;
		specs += ',status=' + settings.status;
		specs += ',titlebar=' + settings.titlebar;
		specs += ',toolbar=' + settings.toolbar;
        specs += ',width=' + settings.width;
        specs += ',height=' + settings.height;
        specs += ',resizable=yes'; // for IE
        	
        if(settings.directLink) // 直接開啟連結
    	{
        	window.open(settings.url, settings.name, specs);
        	return;
    	}
        
		try
		{
			var $errorSpan = $('span[id="errorSpan"]');
			var content = $errorSpan.html(); 
			
			var newWin = window.open(settings.url, 'NewWin', specs);
			var newDocu = newWin.document;
			
			newDocu.write('<html>');
			newDocu.write('<body>');
			newDocu.write(content);
			newDocu.write('</body>');
			newDocu.write('</html>');
		} 
		catch (e){/* ignore */}
	};
	
})(jQuery);