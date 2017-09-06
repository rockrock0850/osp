(function () {
	$.initDateRangePicker = function () {
        if (!$().daterangepicker) {
            return;
        }

        $('#reportrange').daterangepicker({
                opens: 'right',
                startDate: moment().format('YYYY/MM/DD'),
                //endDate: moment(),
                minDate: moment().format('YYYY/MM/DD'),
                 //maxDate: '12/31/2018',
                dateLimit: {
                    days: 90
                },
                showDropdowns: true,
                showWeekNumbers: true,
                timePicker: false,
                timePickerIncrement: true,
                timePicker12Hour: true,
                ranges: {
                    '今日': [moment(), moment().add(1, 'days')],
                    //'昨日': [moment().subtract(1, 'days'), moment()],
                    '最近7日': [moment(), moment().add(6, 'days')],
                    '最近30日': [moment(), moment().add(29, 'days')],
                    '本月': [moment().startOf('month'), moment().endOf('month')]
                },
                buttonClasses: ['btn'],
                applyClass: 'btn-success',
                cancelClass: 'btn-default',
                format: 'YYYY/MM/DD',
                separator: ' 至 ',
                locale: {
                    applyLabel: '套用',
                    cancelLabel: '取消',
                    fromLabel: '開始日期',
                    toLabel: '結束日期',
                    customRangeLabel: '自定區間',
                    daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    firstDay: true
                }
            },
            function (start, end) {
                /*console.log("Callback has been called!");*/
                $('#reportrange span').html(start.format('YYYY/MM/DD') + ' - ' + end.format('YYYY/MM/DD'));
            }
        );
        //Set the initial state of the picker label
        $('#reportrange span').html(moment().format('YYYY/MM/DD') + ' - ' + moment().add(29, 'days').format('YYYY/MM/DD'));
        $.handleInputMasks();
    }
	
	$.handleInputMasks = function () {
		$.extend($.inputmask.defaults, {
			'autounmask': true
		});

		$("#mask_date").inputmask("d/m/y", {
		    autoUnmask: true
		}); //direct mask        
		$("#mask_date1").inputmask("d/m/y", {
			"placeholder": "*"
		}); //change the placeholder
		$("#mask_date2").inputmask("d/m/y", {
			"placeholder": "dd/mm/yyyy"
		}); //multi-char placeholder
		$("#mask_phone").inputmask("mask", {
			"mask": "(99) 9999-9999"
		}); //specifying fn & options
		$("#mask_tin").inputmask({
			"mask": "99-9999999"
		}); //specifying options only
		$("#mask_number").inputmask({
			"mask": "9",
			"repeat": 10,
			"greedy": false
		}); // ~ mask "9" or mask "99" or ... mask "9999999999"
		$("#mask_decimal").inputmask('decimal', {
		    rightAlignNumerics: false
		}); //disables the right alignment of the decimal input
	}
	
	$.initDatePicker = function() {
        if ($().datepicker) {
            $('.date-picker').datepicker({
				format: 'yyyy-mm-dd', // 日期格式
				//startDate: moment().format('YYYY/MM/DD'),  可選起始日期
				// endDate: moment().format('YYYY/MM/DD'), 可選結束日期
                autoclose: true, // 選完日期自動關閉日曆
				todayBtn: true // 顯示[今天]按鈕
            });
        }
	}
	
	$.initDateTimePicker = function($picker, dateBegin, dateEnd) {
		if(!dateBegin) {
			dateBegin = moment().format('YYYY-MM-DD hh:mm');
		}
		if(!dateEnd) {
			dateEnd = null;
		}
		if(!$picker) {
			$picker = $('.date-datetimepicker');
		}
		if($().datetimepicker) {
			$picker.datetimepicker({
				format: 'yyyy-mm-dd hh:ii', // 日期格式
				startDate: dateBegin,//  可選起始日期+時間
				endDate: dateEnd,//可選結束日期+時間
				// pickerPosition: "top-left", 設定視窗彈出位置
                autoclose: true, // 選完日期自動關閉日曆
				todayBtn: true  // 顯示[今天]按鈕
			});
		}
	}
	
	$.initSelectSwitcher = function(collectionFrom, collectionTo) {
		var size = $("div .form-group #" + collectionFrom + " option").size();
		var selected = $("div .form-group #" + collectionFrom + " option:selected").size();

		if (size > 0 && selected > 0) {
			$.each($("div .form-group #" + collectionFrom	+ " option:selected"),
				function(i, target) {
					$(target).appendTo($("div .form-group #" + collectionTo));
				});
		}
	}
	
	$.setTabSwitchListener = function() {
		var $a = $('div#buzFlow .nav.nav-tabs').find('a[id]');//選所有內含id屬性之a元素
		$a.unbind('click').click(function() {
			var $content = $('div#buzFlow .tab-content');
			
			var $contentHide = $content.find('[class~=active]');
			$contentHide.removeClass().addClass('tab-pane fade');

			var id = $(this).attr('id');
			var $contentShow = $content.find('div#' + id);
			$contentShow.removeClass('').addClass('tab-pane fade active in');
		});
	}
	
	/**
	 * 多開一個額外的新視窗
	 * 
	 * left的參數代表.若你要把視窗開到衍生桌面的話.就要設置成超過當前主顯示器的寬度.才可以把視窗開到衍生桌面上
	 * 並且不能用Chrome.只能用IE來操作
	 * 
	 */
	$.openPopUpWindow = function(url, height , width, left) {
		//XXX 第二個參數Title 雖然顯示的不一定真的就是窗口名稱.但去掉這個的話就會變成多開一個新的Tab,而非Window
		window.open(url, 'Title', 'resizable=1, scrollbars=1, fullscreen=0, height=' + height + ', width='+ width +', left= ' + left + ', toolbar=0, menubar=0, status=1');
	}
})();