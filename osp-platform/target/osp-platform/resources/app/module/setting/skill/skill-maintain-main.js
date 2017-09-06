require(['skill-maintain-service', 'osp-ui-app', 'flip', 'common-util', 
	     'datetimepicker', 'cookie', 'form2object'], 
	function (service) {
		document.title = 'OSP | 人員技能貼標維護作業';
		service.init();
		
		$('button#searchButton').on('click', function() {
			service.getMemberMappingInfo();
		});
		
		$("button#skillModalButton").on('click', function() {
			service.getOrderTypeMappingInfo();
		})
		
		$('input#stickButton').on('click', function() {
			service.getSkillInfo();
		});
	}
);

(function() {
	define('skill-maintain-service', 
			['flip', 'htmlComponet', 'osp-util', 'DT_bootstrap', 'jquery.dataTables', 
		    'datetimepicker', 'small-modal'], 
		 function() {
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
						{searchable: false, targets: []}, // 選擇關閉哪個欄位排序功能(  )
						{targets: [0], title: '員工編號', data: 'empNo', className: 'empNo'}, 
						{targets: [1], title: '姓名 ', data: 'empName', className: 'empName'}, 
						{targets: [2], title: 'Skill', data: 'skillName', className: 'skillName'},
						{targets: [3], title: '', data: 'skillId', className: 'skillId hide'}
					]
				});
	
				var $searchResult = $('div#searchResult');
				
				/* $=: 尋找xxx屬性內容以xxx結尾之元素 */
				$searchResult.find("[class$=_length] label").addClass("pull-left");// 將dataTable顯示筆數選單靠左
				$searchResult.find('[class$=_filter]').addClass('pull-right');// 將過濾欄位靠右
				$searchResult.find('[class$=_paginate]').attr('align', 'right');// 將dataTable分頁靠右
			}
			
			/* 初始化skill datatable */
			var handleSkillDataTable = function(data) {
				var $dataTable = $('table#skillDataTable');
				
				data = $.parseJSON(data);
				$dataTable.DataTable({
					dom: '<"row"lf><"row"tr><"row"ip>',
					paging: true, // 翻頁功能
					lengthChange: true, // 改變每頁顯示數據數量
					pageLength: 100, // 顯示10筆換下一頁
					searching: true, // 過濾功能
					ordering: true, // 排序功能
					info: true, // 頁腳信息
					autoWidth: false, // 自動寬度
					data: data, // 注入資料
					columnDefs: [ // 欄位設定
						{searchable: false, targets: [2,3]}, // 選擇關閉哪個欄位排序功能(  )  
						{targets: [0], title: 'SKILLS', data: 'skillName', className: 'skillName'}, 
						{targets: [1], title: '案件類別名稱 ', data: 'orderTypeName', className: 'orderTypeName'},
						{targets: [2], title: '', data: 'skillId', className: 'skillId hide'},
						{targets: [3], title: '', data: 'orderTypeId', className: 'orderTypeId hide'}
					]
				});
			}
			
			/* 初始化stick datatable */
			var handleStickDataTable = function(data) {
				var $dataTable = $('table#stickDataTable');
				
				data = $.parseJSON(data);
				$dataTable.DataTable({
					dom: '<"row"lf><"row"tr><"row"ip>',
					paging: true, // 翻頁功能
					lengthChange: true, // 改變每頁顯示數據數量
					pageLength: 100, // 顯示10筆換下一頁
					searching: true, // 過濾功能
					ordering: true, // 排序功能
					info: true, // 頁腳信息
					autoWidth: false, // 自動寬度
					data: data, // 注入資料
					columnDefs: [ // 欄位設定
						{searchable: false, targets: [6]}, // 選擇關閉哪個欄位排序功能(  )
						{
							targets: [0], 
							data: null, 
							title: "<center><input type='checkbox' id='checkAll'></center>", 
							className: 'osp_features', 
							defaultContent: "<input type='checkbox'>"
						},  
						{targets: [1], title: '人員代號', data: 'empNo', className: 'empNo'}, 
						{targets: [2], title: 'NT帳號 ', data: 'ntAccount', className: 'ntAccount'}, 
						{targets: [3], title: '中文姓名', data: 'empName', className: 'empName'}, 
						{targets: [4], title: '英文姓名', data: 'engName', className: 'engName'}, 
						{targets: [5], title: '部門', data: 'deptName', className: 'deptName'},
						{targets: [6], title: '', data: 'deptCode', className: 'deptCode hide'}
					]
				});
			}
			
			/* 注意: 可提出成共用方法, 處理search result div顯示與否 */
			var handleDisplaySearchResult = function(data) {
				if(data == '') {
					$('#searchResult').attr('style', 'display: none;');
				}else{
					$('#searchResult').removeAttr('style');
				}
			}
			
			/* 注意: 可能出成共用方法, 初始化skill列表並增加change事件  */
			var handleStickSelect = function(data) {
	    		var $skillSelect = $('div#stickModal').find('select#skillSelect');
	    		
	    		data = $.parseJSON(data);
	    		$.each(data, function() {
	    			$skillSelect.append('<option value="' + this.skillId + '">' + this.skillName +'</option>');
	    		});
	    		
	    		getSkillMemberWindowContent($skillSelect);
				
				/* 注意:  在對元件綁定事件之前, 最好先執行unbind( 解除綁定 )語法, 避免元件被重複觸發之問題 */
	    		$skillSelect.unbind('change').on('change', function() {
		    		getSkillMemberWindowContent($skillSelect);
	    		});
			}
			
			/* 取得貼標彈出視窗之資料表內容 */
			var getSkillMemberWindowContent = function($skillSelect) {
    			var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/setting/skill/skill-member-window.action', 
					dataType: 'html',
					dataProvider: function() {
						var data = {};
						var $selected = $skillSelect.find('option:selected');
						data.skillId = $selected.val();
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(data));
				    }, 
				    success: function(data) {
				    	$.destoryDataTable($('table#stickDataTable'));
						handleStickDataTable(data);
			    		$.setCheckBoxAllCheckedEvent($('div#stickModal #checkAll'));
				    }
				}
				$.formAjax(settings);
			}

			/* 取得data table內所有勾選的checkbox */
			var getCheckedList = function($table) {
				var $checkboxs = $table.find('input:checkbox');
				var checkedArray = [];
				var data = [];
				
				$.each($checkboxs, function() {
					var $this = $(this);
					
					if($this.is(':checked') && !$this.is('#checkAll')) {
						checkedArray.push($this);
					}
				});
				
				$.each(checkedArray, function() {
					var $tr = $(this).closest('tr');
					var member = {};

					member.empNo = $tr.find('td.empNo').text();
					member.empName = $tr.find('td.empName').text();
					data.push(member);
				});
				
				return data;
			}

			/* 處理Modal關閉之後需要做的事情 */
			var afterModalClosed = function() {
				/* 貼標 */
				$.afterModalClosed($('div#stickModal'), function() {
					$('div#stickModal').find('select#skillSelect').empty();
			    	$.destoryDataTable($('table#stickDataTable'));
				});

				/* 技能對應查詢 */
				$.afterModalClosed($('div#skillModal'), function() {
					$.destoryDataTable($('table#skillDataTable'));
				});
			}
			
			/* 查詢對應人員關係 */
			var getMemberMappingInfo = function() {
				var settings = {
						httpMethod: 'POST', 
						URL: contextPath + '/setting/skill/get-member-mapping-info.action', 
						dataType: 'html',
						dataProvider: function() {
							var formData = window.form2object("searchForm");
							
							return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
					    }, 
					    success: function(data) {
					    	$.destoryDataTable($('table#dataTable'));
					    	handleDataTable(data);
					    	handleDisplaySearchResult(data);
					    }
					    
					}
					
					$.formAjax(settings);
			}
			
			return {
				init: function() {
					$.getOptionSkill($('select[name=skillId]'));
					afterModalClosed();
				}, 

				/* 查詢對應人員關係 */
				getMemberMappingInfo: getMemberMappingInfo,
				
				/* 查詢對應案件類別 */
				getOrderTypeMappingInfo: function() {
					var settings = {
						httpMethod: 'POST', 
						URL: contextPath + '/setting/skill/get-order-type-mapping-info.action', 
						dataType: 'html',
						dataProvider: function() {
							/* 注意: 這裡不用傳熱和東西到server side, 因為是要拿所有資訊 */
							return '';
					    }, 
					    success: function(data) {
					    	if(data) {
					    		handleSkillDataTable(data);
								$('div#skillModal').modal('show');
					    	}
					    }
					}
					$.formAjax(settings);
				}, 
				
				/* 取得技能列表 */
				getSkillInfo: function() {
					var settings = {
						httpMethod: 'POST', 
						URL: contextPath + '/setting/skill/get-skill-info.action',
						dataType: 'html',
						dataProvider: function() {
							/* 注意: 這裡不用傳熱和東西到server side, 因為是要拿所有資訊 */
							return '';
					    }, 
					    success: function(data) {
					    	if(data) {
								var $stickModal = $('div#stickModal');
								var $stickModalConfirm = $stickModal.find('button#confirmButton');
					    		var $skillSelect = $stickModal.find('select#skillSelect');

					    		handleStickSelect(data);
								
								/* 注意:  在對元件綁定事件之前, 最好先執行unbind( 解除綁定 )語法, 避免元件被重複觸發之問題  */
								$stickModalConfirm.unbind('click').on('click', function() {
									if (!$.hasChecked($('table#stickDataTable').find('input:checkbox'), '032')) {
										return;
									}
									
									var settings = {
										httpMethod: 'POST', 
										URL: contextPath + '/setting/skill/stick-skill.action', 
										dataType: 'html',
										dataProvider: function() {
								    		var checkedList = getCheckedList($('table#stickDataTable'));
											var $selected = $skillSelect.find('option:selected');
											
											$.each(checkedList, function() {
												this.skillId = $selected.val();
												this.skillName = $selected.html();
											});
								    		
											return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(checkedList));
									    }, 
									    success: function(data) {
									    	if(data) {
												$stickModal.modal('hide');
												$.successMsg('005');

												getMemberMappingInfo();
												
										    	return;
									    	}
									    	$.warningMsg('005');
									    }
									}
									$.formAjax(settings);
								});
								$stickModal.modal('show');
					    	}
					    }
					}
					$.formAjax(settings);
				}
			}
		}
	);
})();

