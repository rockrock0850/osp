require(['shift-type-service', 'flip', 'common-util', 'datetimepicker', 'cookie', 'form2object'], 
	function (service) {
		document.title = 'OSP | 班別維護作業';
		
		service.init();
		
		$("div .form-group #collectionLeftBtn").on('click', function() {
			service.collectionLeftToRight()
		});
		$("div .form-group #collectionLeft").on('dblclick', function() {
			service.collectionLeftToRight()
		});

		$("div .form-group #collectionRightBtn").on('click', function() {
			service.collectionRightToLeft();
		});
		$("div .form-group #collectionRight").on('dblclick', function() {
			service.collectionRightToLeft();
		});

		$('button#searchButton').on('click', function() {
			service.getShiftTypeInfo();
		});
		
		$('button#createButton').on('click', function() {
			service.createShiftType();
		});
	}
);

(function() {
	define('shift-type-service', 
			['flip', 'osp-ui-app', 'small-modal', 'osp-util', 'DT_bootstrap', 'jquery.dataTables',
			 'datetimepicker'], 
		 function(flip, uiApp) {
			/* 建立select option的"時"單位 */
			var createHour = function($select) {
				for(var i = 0; i <= 24; i++) {
					if(i < 10) {
						$select.append("<option>" + "0" + i + "</option>");				
					} else {
						$select.append("<option>" + i + "</option>");	
					}
				}
			}
	
			/* 建立select option的"分"單位 */
			var createMinute = function($select) {
				for(var i = 0; i <= 60; i++) {
					if(i < 10) {
						$select.append("<option>" + "0" + i + "</option>");				
					} else {
						$select.append("<option>" + i + "</option>");	
					}
				}
			}
			
			/* 初始化SelectSwitcher的選項 */
			var createSelectSwitcherOption = function(data) {
				data = $.parseJSON(data);
				
				var $collectionLeft = $("div#skillModal .modal-body #collectionLeft");
				var $collectionRight = $("div#skillModal .modal-body #collectionRight");
				var has = data.has;
				var no = data.no;

				$.each(no, function() {
					$collectionLeft.append('<option value=' + this.skillId +'>' + this.skillName + '</option>');
				});
				
				$.each(has, function() {
					$collectionRight.append('<option value=' + this.skillId +'>' + this.skillName + '</option>');
				});
			}
			
			/* 初始化DataTable */
			var handleDataTable = function(data) {
				var $dataTable = $('table#dataTable');
				
				data = $.parseJSON(data);
				$dataTable.DataTable({
					dom: '<"row"lf><"row"tr><"row"ip>',
					paging: true, // 翻頁功能
					lengthChange: true, // 改變每頁顯示數據數量
					pageLength: 100, // 顯示10筆換下一頁
					searching: false, // 過濾功能
					ordering: true, // 排序功能
					info: true, // 頁腳信息
					autoWidth: false, // 自動寬度
					data: data, // 注入資料
					columnDefs: [ // 欄位設定
						{orderable: false, targets: []}, // 選擇關閉哪個欄位排序功能(  )
						{targets: [0], title: '班別名稱', data: 'shiftTypeName', className: 'shiftTypeName'}, 
						{targets: [1], title: '上班時間起', data: 'startTime', className: 'startTime'}, 
						{targets: [2], title: '上班時間迄', data: 'endTime', className: 'endTime'}, 
						{targets: [3], title: '休息時間起', data: 'breakStartTime', className: 'breakStartTime'}, 
						{targets: [4], title: '休息時間迄', data: 'breakEndTime', className: 'breakEndTime'}, 
						{
							aTargets: [5], 
							data: null, 
							title: "功能", 
							className: 'osp_features', 
							defaultContent: "<input id='modifyButton' class='btn btn-sm btn-default' type='button' value='編輯'>" +
									"<input id='skillButton' class='btn btn-sm btn-default' type='button' value='Skills'>"
						},
						{targets: [6], title: '', data: 'shiftTypeId', className: 'shiftTypeId hide'}, 
						{targets: [7], title: '', data: 'description', className: 'description hide'}
					]
				});
			}
			
			/* 處理Data table的按鈕事件 */
			var handleTableClickListener = function() {
				var $dataTable = $('table#dataTable');
				
				$dataTable.unbind('click');
				
				/* 班別修改 */
				$dataTable.on('click', '#modifyButton', function() {
					/* 處理click event */
					var $row = $(this).closest('tr');// closest(): 找到最接近的指定元素
					var $shiftTypeId = $row.find('.shiftTypeId');
					var $shiftTypeName = $row.find('.shiftTypeName');
					var $startTime = $row.find('.startTime');
					var $endTime = $row.find('.endTime');
					var $breakStartTime = $row.find('.breakStartTime');
					var $breakEndTime = $row.find('.breakEndTime');
					var $description = $row.find('.description');
					
					if($shiftTypeName) {
						var $modifyModal = $('div#modifyModal');
						var $label = $modifyModal.find('label#shiftTypeName');
						var $select = $modifyModal.find('select#beginWorkHour');
						var $select2 = $modifyModal.find('select#beginWorkMin');
						var $select3 = $modifyModal.find('select#endWorkHour');
						var $select4 = $modifyModal.find('select#endWorkMin');
						var $select5 = $modifyModal.find('select#beginRestHour');
						var $select6 = $modifyModal.find('select#beginRestMin');
						var $select7 = $modifyModal.find('select#endRestHour');
						var $select8 = $modifyModal.find('select#endRestMin');
						var $textArea = $modifyModal.find('textArea');
						
						var time1 = $startTime.text().split(':');
						var time2 = $endTime.text().split(':');
						var time3 = $breakStartTime.text().split(':');
						var time4 = $breakEndTime.text().split(':');

						$label.html($shiftTypeName.text());
						$select.val(time1[0]);
						$select2.val(time1[1]);
						$select3.val(time2[0]);
						$select4.val(time2[1]);
						$select5.val(time3[0]);
						$select6.val(time3[1]);
						$select7.val(time4[0]);
						$select8.val(time4[1]);
						$textArea.val($description.text());
						
						/* 注意:  在對元件綁定事件之前, 最好先執行unbind( 解除綁定 )語法, 避免元件被重複觸發之問題 */
						var $modifyModalConfirm = $modifyModal.find('button#confirmButton');
						$modifyModalConfirm.unbind('click').on('click', function() {
							var settings = {
								httpMethod: 'POST', 
								URL: contextPath + '/setting/shift/modify-shift-type.action', 
								dataType: 'html',
								dataProvider: function() {
									var formData = window.form2object('modifyForm');
									formData.shiftTypeId = $shiftTypeId.text();
									formData.shiftTypeName  = $shiftTypeName.text();
									formData.startTime = formData.beginWorkHour + ':' + formData.beginWorkMin;
									formData.endTime = formData.endWorkHour + ':' + formData.endWorkMin;
									formData.breakStartTime = formData.beginRestHour + ':' + formData.beginRestMin;
									formData.breakEndTime = formData.endRestHour + ':' + formData.endRestMin;
									formData.description = $textArea.val();
									
									return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
							    }, 
							    success: function(data) {
							    	if(data) {
								    	$modifyModal.modal('hide');
								    	$('button#searchButton').trigger('click');
								    	$.successMsg('003');
										
								    	return;
							    	}
							    	$.warningMsg('001');
							    }
							}
							$.formAjax(settings);
						});
						$modifyModal.modal('show');
					}
				});
				
				/* 查詢對應技能關係 */
				$dataTable.on('click', '#skillButton', function() {
					/* 處理click event */
					var $row = $(this).closest('tr');// closest(): 找到最接近的指定元素
					var $shiftTypeId = $row.find('.shiftTypeId');
					
					if($shiftTypeId) {
						var $skillModal = $('div#skillModal');
						var $skillModalConfirm = $skillModal.find('button#confirmButton');

						var settings = {
							httpMethod: 'POST', 
							URL: contextPath + '/setting/shift/get-skill-mapping-info.action',
							dataType: 'html',
							dataProvider: function() {
								var formData = {};
								formData.shiftTypeId = $shiftTypeId.text();
								
								return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
						    }, 
						    success: function(data) {
						    	/* 修改對應技能關係  */
						    	if(data) {
							    	createSelectSwitcherOption(data);
									
									/* 注意:  在對元件綁定事件之前, 最好先執行unbind( 解除綁定 )語法, 避免元件被重複觸發之問題 */
									$skillModalConfirm.unbind('click').on('click', function() {
										var settings = {
											httpMethod: 'POST', 
											URL: contextPath + '/setting/shift/modify-skill-mapping.action', 
											dataType: 'html',
											dataProvider: function() {
												var formData = getSelectedFormData();
												
												$.each(formData.collectionLeft, function() { 
													this.shiftTypeId = $shiftTypeId.text();
												});
												
												$.each(formData.collectionRight, function() {
													this.shiftTypeId = $shiftTypeId.text();
												});
												
												return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
										    }, 
										    success: function(data) {
										    	if(data) {
											    	$skillModal.modal('hide');
											    	$('button#searchButton').trigger('click');
											    	$.successMsg('003');
													
											    	return;
										    	}
										    	$.warningMsg('003');
										    }
										    
										}
										
										$.formAjax(settings);
									});
									$skillModal.modal('show');
						    	}
						    }
						    
						}
						
						$.formAjax(settings);
					}
				});
			}
			
			/* 取得SelectSwitcher所選擇之資料  */
			var getSelectedFormData = function() {
				var $skillModal = $('div#skillModal');
				var $collectionLeftOptions = $skillModal.find('select#collectionLeft').find('option');
				var $collectionRightOptions = $skillModal.find('select#collectionRight').find('option');
				var formData = {};
				var temp = [];
				
				$.each($collectionLeftOptions, function() {
					var $this = $(this);
					var option = {};
					
					option.skillId = $this.val();
//					option.skillName = $this.html();
					temp.push(option);
				});
				formData.collectionLeft = temp;

				temp = [];
				$.each($collectionRightOptions, function() {
					var $this = $(this);
					var option = {};

					option.skillId = $this.val();
//					option.skillName = $this.html();
					temp.push(option);
				});
				formData.collectionRight = temp;
				
				return formData;
			}
			
			/* 注意: 可提出成共用方法, 處理search result div顯示與否 */
			var handleDisplaySearchResult = function(data) {
				if(data == '') {
					$('#searchResult').attr('style', 'display: none;');
				}else{
					$('#searchResult').removeAttr('style');
				}
			}
			
			/* 搜尋所有班別資料 */
			var queryInfo = function() {
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/setting/shift/get-shift-type-info.action', 
					dataType: 'html',
					dataProvider: function() {
						return '';
				    }, 
				    success: function(data) {
				    	$.destoryDataTable($('div#searchResult table'));
				    	handleDataTable(data);
				    	handleTableClickListener();
				    	handleDisplaySearchResult(data);
				    }
				    
				}
				
				$.formAjax(settings);
			}

			/* 處理Modal關閉之後需要做的事情 */
			var afterModalClosed = function() {
				/* 編輯 */
				$.afterModalClosed($('div#modifyModal'), function() {
					$(this).find('input,textArea').val('');
				});

				/* Skill*/
				$.afterModalClosed($('div#skillModal'), function() {
					$(this).find('.modal-body #collectionLeft').empty();
					$(this).find('.modal-body #collectionRight').empty();
				});

				/* 新增 */
				$.afterModalClosed($('div#createModal'), function() {
					$('div#createModal').find('input,textArea').val('');
					$('div#createModal').find('select').val('00');
				});
			}
			
			return {
				init: function() {
					createHour($('div #beginWorkHour'));
					createHour($('div #beginRestHour'));
					createHour($('div #endWorkHour'));
					createHour($('div #endRestHour'));
					createMinute($('div #beginWorkMin'));
					createMinute($('div #beginRestMin'));
					createMinute($('div #endWorkMin'));
					createMinute($('div #endRestMin'));
					queryInfo();
					afterModalClosed();
				},
				
				/* 處理左邊送到右邊之集合 */
				collectionLeftToRight: function() {
					$.initSelectSwitcher('collectionLeft', 'collectionRight');
				},
				
				/* 處理右邊送到左邊之集合 */
				collectionRightToLeft: function() {
					$.initSelectSwitcher('collectionRight', 'collectionLeft');
				},
				
				/* 班別查詢 */
				getShiftTypeInfo: function() {
					var settings = {
						httpMethod: 'POST', 
						URL: contextPath + '/setting/shift/get-shift-type-info.action', 
						dataType: 'html',
						dataProvider: function() {
							var formData = window.form2object("searchForm");
							
							return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
					    }, 
					    success: function(data) {
					    	$.destoryDataTable($('div#searchResult table'));
					    	handleDataTable(data);
					    	handleTableClickListener();
					    	handleDisplaySearchResult(data);
					    }
					    
					}
					
					$.formAjax(settings);
				},
				
				/* 班別新增 */
				createShiftType: function(){
					var $createModal = $('div#createModal');
					var $input = $createModal.find('input');
					var $textArea = $createModal.find('textArea');
					var $createModalConfirm = $createModal.find('button#confirmButton');

					/* 注意:  在對元件綁定事件之前, 最好先執行unbind( 解除綁定 )語法, 避免元件被重複觸發之問題 */
					$createModalConfirm.unbind('click').on('click', function() {
						var formData = window.form2object('createForm');
						
						if (!formData.shiftTypeName) {
							$.warningMsg('033');
							
							return;
						}
						
						formData.shiftTypeId = formData.shiftTypeName;
						formData.startTime = formData.beginWorkHour + ':' + formData.beginWorkMin;
						formData.endTime = formData.endWorkHour + ':' + formData.endWorkMin;
						formData.breakStartTime = formData.beginRestHour + ':' + formData.beginRestMin;
						formData.breakEndTime = formData.endRestHour + ':' + formData.endRestMin;
						formData.description = $textArea.val();
						
						var settings = {
							httpMethod: 'POST', 
							URL: contextPath + '/setting/shift/create-shift-type.action', 
							dataType: 'html',
							dataProvider: function() {
								return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
						    }, 
						    success: function(data) {
						    	if(data) {
							    	$createModal.modal('hide');
							    	$('button#searchButton').trigger('click');
							    	$.successMsg('004');
									
							    	return;
						    	}
						    	$.warningMsg('004');
						    }
						}
						$.formAjax(settings);
					});
					$createModal.modal('show');
				}
			}
		}
	);
})();
