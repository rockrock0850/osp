require(['role-maintain-service', 'flip', 'common-util', 
	     'datetimepicker', 'cookie', 'form2object'], 
	function (service) {
		document.title = 'OSP | 角色維護作業';
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
			service.getRoleInfo();
		});
		
		$("input#createButton").on('click', function() {
			service.createRole();
		})
		
		var $searchButton = $('button#searchButton');
		$searchButton.trigger('click');
	}
);

(function() {
	define('role-maintain-service', 
			[ 'osp-ui-app', 'flip', 'htmlComponet', 'osp-util', 'DT_bootstrap', 'jquery.dataTables', 'datetimepicker', 'small-modal', 'moment'], 
		 function(uiApp) {
			var init = function() {
				$.getOptionRole($('select[name=roleType]'));
				afterModalClosed();
			}
			
			// 處理左邊送到右邊之集合
			var collectionLeftToRight = function() {
				$.initSelectSwitcher('collectionLeft', 'collectionRight');
			}
			
			// 處理右邊送到左邊之集合
			var collectionRightToLeft = function() {
				$.initSelectSwitcher('collectionRight', 'collectionLeft');
			}
			
			// 查詢角色資訊
			var getRoleInfo = function() {
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/setting/role/get-role-info.action', 
					dataType: 'html',
					dataProvider: function() {
						var formData = window.form2object("searchForm");
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
				    }, 
				    success: function(data) {
				    	if(data) {
							$.destoryDataTable($('table#dataTable'));
							handleDataTable(data);
							handleDisplaySearchResult(data);
							setTableButtonClickEvent();
				    	}
				    }
				}
				$.formAjax(settings);
			} 
			
			// 創建角色
			var createRole = function() {
				var $createModal = $('div#createModal');
				var $createModalConfirm = $createModal.find('button#confirmButton');
	
				// 注意: 在對元件綁定事件之前, 最好先執行unbind( 解除綁定 )語法, 避免元件被重複觸發之問題
				$createModalConfirm.unbind('click').on('click', function() {
					submitCreateModal($createModal);
				});
				$createModal.modal('show');
			}
			
			/* Inner Method
			=================================================================================================================================*/
			// 初始化DataTable
			var handleDataTable = function(data) {
				var $dataTable = $('table#dataTable');
				
				data = $.parseJSON(data);
				removeTime(data);
				
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
						{targets: [0], title: '角色名稱', data: 'roleName', className: 'roleName'}, 
						{targets: [1], title: '角色類別', data: 'roleTypeName', className: 'roleTypeName'}, 
						{targets: [2], title: '建立者', data: 'createUser', className: 'createUser'}, 
						{targets: [3], title: '建立日期', data: 'createDate', className: 'createDate'}, 
						{targets: [4], title: '修改者', data: 'updateUser', className: 'updateUser'}, 
						{targets: [5], title: '修改日期', data: 'updateDate', className: 'updateDate'},
						{
							aTargets: [6], 
							data: null, 
							title: "功能", 
							className: 'osp_features', 
							defaultContent: "<input id='modifyButton' class='btn btn-sm btn-default' type='button' value='編輯角色'>" +
									"<input id='setMemberButton' class='btn btn-sm btn-default' type='button' value='設定人員'>" +
									"<input id='setFeature' class='btn btn-sm btn-default' type='button' value='設定功能'>"
						},
						{targets: [7], title: '', data: 'roleId', className: 'roleId hide'},
						{targets: [8], title: '', data: 'roleType', className: 'roleType hide'}
					]
				});
			}
			
			// 移除日期的時間部分資料
			var removeTime = function(data) {
				$.each(data, function() {
					this.createDate = $.formatter(this.createDate, 'YYYY-MM-DD');
					this.updateDate = $.formatter(this.updateDate, 'YYYY-MM-DD');
				});
			}
			
			// 綁定最大張的table的按鈕事件
			var setTableButtonClickEvent = function() {
				var $dataTable = $('table#dataTable');
				
				$dataTable.unbind('click');
				
				// 修改角色
				$dataTable.on('click', '#modifyButton', function() {
					// 處理click event
					var $row = $(this).closest('tr');// closest(): 找到最接近的指定元素
					var roleId = $row.find('.roleId').text();
					var roleName = $row.find('.roleName').text();
					var roleType = $row.find('.roleType').text();
					var roleTypeName = $row.find('.roleTypeName').text();
					
					if(roleId) {
						var $modifyModal = $('div#modifyModal');
						var $modalRoleName = $modifyModal.find('input#roleName');
						var $modalRoleType = $modifyModal.find('label#roleType');
						var $modifyModalConfirm = $modifyModal.find('button#confirmButton');

						$modalRoleName.val(roleName);
						$modalRoleType.html(roleTypeName);
						
						// 注意: 在對元件綁定事件之前, 最好先執行unbind( 解除綁定 )語法, 避免元件被重複觸發之問題
						$modifyModalConfirm.unbind('click').on('click', function() {
							var formData = window.form2object('modifyForm');
							roleName = formData.roleName;
							if (!roleName) {
								var content = '<center><label>' + $.getErrorMessage('031') + '</label></center>';
								var buttons = {
									cancelButton: {
										value: '確認', 
										action: function() {
											$modifyModal.modal('show');
										}
									}
								}
								$.messageModal('警告', content, buttons);
								
								return;
							}
							
							var settings = {
								httpMethod: 'POST', 
								URL: contextPath + '/setting/role/modify-role.action', 
								dataType: 'html',
								dataProvider: function() {
									formData.roleId = roleId;
									formData.roleType = roleType;
									formData.active = 'Y';
									
									return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
							    }, 
							    success: function(data) {
							    	if(data) {
								    	$modifyModal.modal('hide');	
								    	$('button#searchButton').trigger('click');
								    	$.successMsg('003');
										
								    	return;
							    	}
							    	$.warningMsg('003');
							    }
							}
							$.formAjax(settings);
						});
						$modifyModal.modal('show');
					}
				});
				
				// 查詢對應員工帳號關係資訊by id
				$dataTable.on('click', '#setMemberButton', function() {
					// 處理click event
					var $row = $(this).closest('tr');// closest(): 找到最接近的指定元素
					var roleId = $row.find('.roleId').text();
					
					if(roleId) {
						var settings = {
							httpMethod: 'POST', 
							URL: contextPath + '/setting/role/get-manual-mapping-info-by-id.action',
							dataType: 'html',
							dataProvider: function() {
								var formData = {};
								formData.roleId = roleId;

								return  REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
						    }, 
						    success: function(data) {
						    	if(data) {
						    		$.destoryDataTable($('table#setRoleMemberDataTable'));
							    	initSetRoleMemberTable(data);
							    	setSetRoleMemberTableButtonClickEvent();
							    	showSetMemberModal(roleId, $row);
						    	}
						    }
						}
						$.formAjax(settings);
					}
				});
				
				// 查詢系統選單關係 
				$dataTable.on('click', '#setFeature', function() {
					// 處理click event
					var $row = $(this).closest('tr');// closest(): 找到最接近的指定元素
					var roleId = $row.find('.roleId').text();
					
					if(roleId) {
						var settings = {
							httpMethod: 'POST', 
							URL: contextPath + '/setting/role/service-menu-ref.action', 
							dataType: 'html',
							dataProvider: function() {
								var formData = {};
								formData.roleId = roleId;
								
								return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
						    }, 
						    success: function(data) {
						    	// 創建系統選單關係 
						    	if(data) {
							    	createSelectSwitcherOption(data);
							    	showSetFeatureModal(roleId);
						    	}
						    }
						}
						$.formAjax(settings);
					}
				});
			}
			
			var showSetFeatureModal = function(roleId) {
				var $setFeatureModal = $('div#setFeatureModal');
				var $setFeatureModalConfirm = $setFeatureModal.find('button#confirmButton');
				
				// 注意: 在對元件綁定事件之前, 最好先執行unbind( 解除綁定 )語法, 避免元件被重複觸發之問題
				$setFeatureModalConfirm.unbind('click').on('click', function() {
					var settings = {
						httpMethod: 'POST', 
						URL: contextPath + '/setting/role/create-menu-ref.action', 
						dataType: 'html',
						dataProvider: function() {
							var formData = getSelectedFormData();

							$.each(formData.collectionLeft, function() { 
								this.roleId = roleId;
							});
							$.each(formData.collectionRight, function() {
								this.roleId = roleId;
							});

							return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
					    }, 
					    success: function(data) {
					    	if(data) {
						    	$setFeatureModal.modal('hide');
						    	$('button#searchButton').trigger('click');
						    	$.successMsg('007');
								
						    	return;
					    	}
					    	$.warningMsg('007');
					    }
					}
					$.formAjax(settings);
				});
				$setFeatureModal.modal('show');
			}
			
			var showSetMemberModal = function(roleId, $row) {
		    	var $setMemberModal = $('div#setMemberModal');
				var $setMemberSearchButton = $setMemberModal.find('button#setMemberSearchButton');
				var $createMemberButton = $setMemberModal.find('button#createMemberButton');

				// 查詢對應員工帳號關係資訊
				$setMemberSearchButton.unbind('click').on('click', function() {
					var settings = {
						httpMethod: 'POST', 
						URL: contextPath + '/setting/role/get-manual-mapping-info.action', 
						dataType: 'html',
						dataProvider: function() {
							var formData = window.form2object('setMemberSearchForm');
							formData.roleId = roleId;

							return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
					    }, 
					    success: function(data) {
					    	if(data) {
								$.destoryDataTable($('table#setRoleMemberDataTable'));
						    	initSetRoleMemberTable(data);
						    	setSetRoleMemberTableButtonClickEvent();
					    	}
					    }
					}
					$.formAjax(settings);
				});
				
				// 查詢新增角色員工名單 
				$createMemberButton.unbind('click').on('click', function() {
					var settings = {
						httpMethod: 'POST', 
						URL: contextPath + '/setting/role/get-manual-member.action', 
						dataType: 'html',
						dataProvider: function() {
							var formData = {};
							formData.roleId = roleId;

							return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
					    }, 
					    success: function(data) {
					    	if(data) {
								$.destoryDataTable($('div#createMemberModal table'));
								initCreateMemberTable(data);
								$.setCheckBoxAllCheckedEvent($('div#createMemberModal table #checkAll'));
						    	$setMemberModal.modal('hide');
								showCreateMemberModal(roleId, $row, $setMemberModal);
					    	}
					    }
					}
					$.formAjax(settings);
				});
				$setMemberModal.modal('show');
			}
			
			var showCreateMemberModal = function(roleId, $row, $setMemberModal) {
				var roleName = $row.find('.roleName').text();
				var createUser = $row.find('.createUser').text();
				var createDate = $row.find('.createDate').text();
				var updateUser = $row.find('.updateUser').text();
				var updateDate = $row.find('.updateDate').text();
		    	var $createMemberModal = $('div#createMemberModal');
				var $checkbox = $createMemberModal.find('checkbox');
				var $createMemberModalConfirm = $createMemberModal.find('button#confirmButton');
				var $createMemberModalCancel = $createMemberModal.find('button#cancelButton');

				// 注意: 在對元件綁定事件之前, 最好先執行unbind( 解除綁定 )語法, 避免元件被重複觸發之問題
				$createMemberModalConfirm.unbind('click').on('click', function() {
					var settings = {
						httpMethod: 'POST', 
						URL: contextPath + '/setting/role/create-manual-mapping.action', 
						dataType: 'html',
						dataProvider: function() {
				    		var checkedList = getcheckedList($('div#createMemberModal table'));
							$.each(checkedList, function() {
								this.roleId = roleId;
								this.roleName = roleName;
								this.createUser = createUser;
								this.createDate = createDate;
								this.updateUser = updateUser;
								this.updateDate = updateDate;
							});
				    		
							return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(checkedList));
					    }, 
					    success: function(data) {
					    	if(data) {
						    	$('button#searchButton').trigger('click');
					    		$createMemberModal.modal('hide');
								
								var content = '<center><label>' + $.getSuccessMessage('004') + '</label></center>';
								var buttons = {
									cancelButton: {
										value: '確認', 
										action: function() {
											$setMemberModal.find('button#setMemberSearchButton').trigger('click');
											$setMemberModal.modal('show');
											$.messageModal('hide');
										}
									}
								}
					    		$.messageModal('訊息', content, buttons);
								
						    	return;
					    	}
							
							var content = '<center><label>' + $.getErrorMessage('004') + '</label></center>';
							var buttons = {
								cancelButton: {
									value: '確認', 
									action: function() {
										$setMemberModal.modal('show');
										$.samllModal('hide');
									}
								}
							}
				    		$.messageModal('訊息', content, buttons);
					    }
					}
					$.formAjax(settings);
				});
				
				$createMemberModalCancel.unbind('click').click(function() {
					$setMemberModal.modal('show');
				});
				
				$createMemberModal.modal('show');
			}
			
			// 初始化setRoleMemberDataTable
			var initSetRoleMemberTable = function(data) {
				var $dataTable = $('table#setRoleMemberDataTable');
				
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
						{searchable: false, targets: [4,5]}, // 選擇關閉哪個欄位排序功能(  ) 
						{targets: [0], title: '人員代號', data: 'empNo', className: 'empNo'}, 
						{targets: [1], title: '人員姓名', data: 'empName', className: 'empName'}, 
						{targets: [2], title: '單位名稱', data: 'deptName', className: 'deptName'}, 
						{
							aTargets: [3], 
							data: null, 
							title: "功能", 
							className: 'osp_features', 
							defaultContent: "<input id='deleteButton' class='btn btn-sm btn-default' type='button' value='刪除'>"
						},
						{targets: [4], title: '', data: 'roleId', className: 'roleId hide'}, 
						{targets: [5], title: '', data: 'deptCode', className: 'deptCode hide'}
					]
				});
			}
			
			// 處理setRoleMemberDataTable的按鈕事件
			var setSetRoleMemberTableButtonClickEvent = function() {
				var $dataTable = $('table#setRoleMemberDataTable');
				
				$dataTable.unbind('click');

				// 注意: 在對元件綁定事件之前, 最好先執行unbind( 解除綁定 )語法, 避免元件被重複觸發之問題
				$dataTable.unbind('click', '#deleteButton');
				// 刪除員工
				$dataTable.on('click', '#deleteButton', function() {
					// 處理click event
					var $row = $(this).closest('tr');// closest(): 找到最接近的指定元素
					var roleId = $row.find('.roleId').text();
					var empNo = $row.find('.empNo').text();
					
					if(roleId) {
			    		var $modal = $('div#setMemberModal');
			    		$modal.modal('hide');
						
						var content = '<center><label>' + $.getQuestionMessage('001') + '</label></center>';
						var buttons = {
							confirmButton: {
								value: '確認',
								action: function() {
									var settings = {
										httpMethod: 'POST', 
										URL: contextPath + '/setting/role/remove-manual-mapping.action', 
										dataType: 'html',
										dataProvider: function() {
											var formData = {};
											formData.roleId = roleId;
											formData.empNo = empNo;
											
											return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
									    }, 
									    success: function(data) {
									    	if(data) {
									    		$modal.find('button#setMemberSearchButton').trigger('click');
									    		$.messageModal('hide');
									    		$modal.modal('show');
									    	}
									    }
									}
									$.formAjax(settings);
								}
							},
							cancelButton: {
								value: '取消', 
								action: function() {
						    		$modal.modal('show');
								}
							}
						}
						$.messageModal('訊息', content, buttons);
					}
				});
			}
			
			/* 初始化createMemberDataTable
			 * 注意:　以後要善用jquery的selector機制, 
			 * 可減少設定過多的元件id, 降低程式複雜度( 例如第411行, 可不用每個datatable都設定id )
			 */
			var initCreateMemberTable = function(data) {
				var $dataTable = $('div#createMemberModal table');
				
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
							aTargets: [0], 
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

			// 初始化SelectSwitcher的選項
			var createSelectSwitcherOption = function(data) {
				data = $.parseJSON(data);
				
				var $collectionLeft = $("div#setFeatureModal .modal-body #collectionLeft");
				var $collectionRight = $("div#setFeatureModal .modal-body #collectionRight");
				var has = data.has;
				var no = data.no;
	
				$.each(no, function() {
					$collectionLeft.append('<option value=' + this.menuId +'>' + this.menuText + '</option>');
				});
				
				$.each(has, function() {
					$collectionRight.append('<option value=' + this.menuId +'>' + this.menuText + '</option>');
				});
			}
			
			// 注意: 可提出成共用方法, 處理search result div顯示與否
			var handleDisplaySearchResult = function(data) {
				if(data == '') {
					$('#searchResult').attr('style', 'display: none;');
				}else{
					$('#searchResult').removeAttr('style');
				}
			}

			//  取得data table內所有勾選的checkbox
			var getcheckedList = function($table) {
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
					var emp = {};

					emp.deptCode = $tr.find('td.deptCode').text();
					emp.empNo = $tr.find('td.empNo').text();
					emp.ntAccount = $tr.find('td.ntAccount').text();
					emp.empName = $tr.find('td.empName').text();
					emp.engName = $tr.find('td.engName').text();
					emp.deptName = $tr.find('td.deptName').text();
					data.push(emp);
				});
				
				return data;
			}
			
			// 取得SelectSwitcher所選擇之資料 
			var getSelectedFormData = function() {
				var $modal = $('div#setFeatureModal');
				var $collectionLeftOptions = $modal.find('select#collectionLeft').find('option');
				var $collectionRightOptions = $modal.find('select#collectionRight').find('option');
				var formData = {};
				var temp = [];
				
				$.each($collectionLeftOptions, function() {
					var $this = $(this);
					var option = {};
					
					option.menuId = $this.val();
					temp.push(option);
				});
				formData.collectionLeft = temp;

				temp = [];
				$.each($collectionRightOptions, function(idx, selected) {
					var $this = $(this);
					var option = {};
					
					option.menuId = $this.val();
					temp.push(option);
				});
				formData.collectionRight = temp;
				
				return formData;
			}

			// 處理Modal關閉之後需要做的事情
			var afterModalClosed = function() {
				// 新增角色
				$.afterModalClosed($('div#createModal'), function() {
					$(this).find('input,select').val('');
				});

				// 設定成員
				$.afterModalClosed($('div#setMemberModal'), function() {
					//Do something...
				});

				// 新增成員
				$.afterModalClosed($('div#createMemberModal'), function() {
					$(this).find('checkbox').attr('checked', false);
				});

				// 編輯角色
				$.afterModalClosed($('div#modifyModal'), function() {
					$(this).find('input#roleName').val('');
				});

				// 設定功能
				$.afterModalClosed($('div#setFeatureModal'), function() {
					$(this).find('.modal-body #collectionLeft').empty();
					$(this).find('.modal-body #collectionRight').empty();
				});
			}
			
			// 創建角色表單送出
			var submitCreateModal = function($modal) {
				var roleName = $modal.find('input[name=roleName]').val();
				var roleType = $modal.find('select[name=roleType]').val();
				
				if(!roleName || !roleType) {
					var content = '<center><label>' + $.getErrorMessage('009') + '</label></center>';
					var buttons = {
						cancelButton: {
							value: '確認', 
							action: function() {
								$modal.modal('show');
							}
						}
					}
					$.messageModal('警告', content, buttons);
					
					return;
				}
				
				var settings = {
					httpMethod: 'POST', 
					URL: contextPath + '/setting/role/create-role.action', 
					dataType: 'html',
					dataProvider: function() {
						var formData = window.form2object("createForm");
						
						return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(formData));
				    }, 
				    success: function(data) {
				    	if(data) {
				    		$modal.modal('hide');
				    		$.successMsg('004');
					    	$('button#searchButton').trigger('click');
							
					    	return;
				    	}
				    	$.warningMsg('004');
				    }
				}
				$.formAjax(settings);
			}
			
			return {
				init:init,
				
				// 處理左邊送到右邊之集合
				collectionLeftToRight: collectionLeftToRight,
				
				// 處理右邊送到左邊之集合
				collectionRightToLeft: collectionRightToLeft,
				
				// 查詢角色資訊
				getRoleInfo: getRoleInfo, 
				
				// 創建角色
				createRole: createRole
			}
		}
	);
})();
