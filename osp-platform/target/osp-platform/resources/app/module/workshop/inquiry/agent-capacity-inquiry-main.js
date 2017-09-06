require([ 'flip', 'capacityService', 'form-components', 'form2object', 'common-util' ], 
	function (flip, capacityService) {
	 	FormComponents.init();
	
		$("button#searchBtn").on('click', function(e) {
			capacityService.inquiry();
		});
		
		$("button#exportBtn").on('click', function(e) {
			capacityService.exportCSV();
		});
		
		// init datepicker sysdate
		$("input[name=startDate]").datepicker("setDate", new Date());
		$("input[name=endDate]").datepicker("setDate", new Date());
		
		// 預設執行查詢
		$("button#searchBtn").click();
	});

(function() {
	define('capacityService', ['flip', 'form2object', 'common-util', 'jquery.dataTables', 'DT_bootstrap', 'osp-util'], function() {
		var inquiry = function() {
			var jsonData = getCapacityInfo();
			
			clearDataGrid();
			
			drawDataGrid(jsonData);
		};
		
		var exportCSV = function() {
			exportCapacityInfo();
		};
		
		var getCapacityInfo = function() {
			var jsonData = null;
			
			var settings = {
				httpMethod: 'POST', 
				async : false,
				dataAreaId : "capacityInquiryForm",
				URL : contextPath + '/workshop/inquiry/get-agent-capacity.action',
				dataType: 'json',
				dataProvider : function(dataAreaId) {
					var params = window.form2object(dataAreaId);
					
					return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(params));
				}, 
				success: function(data) {
					jsonData = data;
				}
			}
				
			$.formAjax(settings);
			
			return jsonData;
		};
		
		var clearDataGrid = function() {
			var $table = $("table[id='gridView']");
			$.destoryDataTable($table);
		}
		
		var drawDataGrid = function(jsonData) {
			$("table[id='gridView']").DataTable({
				dom: '<lf<t>ip>',
				bPaginate: true, 			// 翻頁功能
				bLengthChange: true, 		// 改變每頁顯示數據數量
				bFilter: false, 			// 過濾功能
				iDisplayLength: 100, 		// 顯示10筆換下一頁
				data : jsonData,
				order: [[ 1, "asc" ]],
	 		   	columns: [
	 		            { title: '處理人員', data: 'USER_NAME' },
	 		            { title: '日期', data: 'DT_DATE' },
	 		            { title: '實際完成產能', data: 'REAL_KPI' },
	 		            { title: '個人待處理預計完成產能', data: 'PROCESS_KPI' },
	 		            { title: '前兩者加總產能', data: 'KPI_SUM' },
	 		            { title: '當日已派件的預計處裡產能', data: 'DISPATCH_KPI' }
	 		        ]
			});
		}
		
		var formValidater = function() {
			
		};
		
		var exportCapacityInfo = function() {
			var settings = {
				httpMethod: 'POST', 
				async : false,
				dataAreaId : "capacityInquiryForm",
				URL : contextPath + '/workshop/inquiry/export-agent-capacity.action',
				dataType: 'TEXT',
				dataProvider : function(dataAreaId) {
					var params = window.form2object(dataAreaId);
					
					return REQ_PARAM_JSON_DATA + '=' + encodeURIComponent(JSON.stringify(params));
				}, 
				success: function(data) {
					window.location = contextPath + '/workshop/inquiry/download-agent-capacity.action?fileKey=' + data;
				}
			}
					
			$.formAjax(settings);
		};
	
		return {
			inquiry : inquiry,
			exportCSV : exportCSV
		};
	});
})();