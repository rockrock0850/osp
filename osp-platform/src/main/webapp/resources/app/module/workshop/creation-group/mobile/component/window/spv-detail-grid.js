require(['service', 'header-remind-new-dispatch-service', 'cookie', 
         'jquery.dataTables', 'DT_bootstrap'], 
	function () {
		document.title = 'OSP | SPV Order Detail';
	}
);

(function() {
	// 注意: 如果定義的module跟require在同一支檔案下的話, 則不用特別在require config裡面註冊名稱及檔案路徑
	define('service', ['flow-main-service'], 
		function() {
			
			// 初始化DataTable
			var initTableContent = function(data) {
				var $table = $('table#gridTable');
				
				$table.DataTable({
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
						{targets: [0], title: 'ACCOUNT_ID', data: 'accountId'},
						{targets: [1], title: 'MSISDN', data: 'msisdn'},
						{targets: [2], title: 'CYCLE_CODE', data: 'cycleCode'},
						{targets: [3], title: 'CANCEL_REASON', data: 'cancelReason'},
						{targets: [4], title: 'OPID', data: 'opId'},
						{targets: [5], title: 'CHANNEL', data: 'channel'},
						{targets: [6], title: 'SERVICE_NAME', data: 'serviceName'},
						{targets: [7], title: 'OFFER_ID', data: 'offerId'},
						{targets: [8], title: 'OFFER_NAME', data: 'offerName'},
						{targets: [9], title: 'TASK_STATUS', data: 'taskStatus'},
						{targets: [10], title: 'STATUS_TIME', data: 'statusTime'},
						{targets: [11], title: 'REQUEST_EFFECTIVE_DATE', data: 'requestEffectiveDate'},
						{targets: [12], title: 'REQUEST_EXPIRATION_DATE', data: 'requestExpirationDate'},
						{targets: [13], title: 'TASK_CREATION_DATE', data: 'taskCreationDate'},
						{targets: [14], title: 'TASK_SEQ_NO', data: 'taskSeqNo'},
						{targets: [15], title: 'TASK_ID', data: 'taskId'}
					]
				});
			};
			
			initTableContent(aaData);
		}
	);
})()