(function() {
	$.messageModal = function(title, content, buttons) {
		if(title == 'hide') {
			hide();
			
			return;
		}
		
		show(title, content, buttons);
	}
	
	$.successMsg = function(code) {
		var content = '<center><label>' + $.getSuccessMessage(code) + '</label></center>';
		var buttons = {
			cancelButton: {value: '確認'}
		};
		show('訊息', content, buttons);
	}
	
	$.warningMsg = function(code) {
		var content = '<center><label>' + $.getErrorMessage(code) + '</label></center>';
		var buttons = {
			cancelButton: {value: '確認'}
		};
		show('警告', content, buttons);
	}
	
	/* Inner Method
	==========================================================================================*/
	var show = function(title, content, buttons) {
		$smallModal = $('#smallModal');
		$smallModal.find('.modal-title').html('').html(title);
		$smallModal.find('.modal-body').html('').html(content);

		// 確認按鈕
		$confirmButton = $smallModal.find('button.btn-primary');
		if (buttons.confirmButton) {
			$confirmButton.removeAttr('style');
			$confirmButton.html('').html(buttons.confirmButton.value);
			$confirmButton.unbind('click').on('click', buttons.confirmButton.action);
			$confirmButton.attr("id", buttons.confirmButton.id);
		} else {
			$confirmButton.hide();
		}

		// 取消按鈕
		$cancelButton = $smallModal.find('button.btn-default');
		if (buttons.cancelButton) {
			$cancelButton.removeAttr('style');
			$cancelButton.html('').html(buttons.cancelButton.value);
			$cancelButton.unbind('click').on('click', buttons.cancelButton.action);
		} else {
			$cancelButton.hide();
		}

		$smallModal.modal('show');
	}
	
	var hide = function() {
		$('#smallModal').modal('hide');
	}
})();