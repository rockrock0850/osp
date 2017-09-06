require(['flip', 'service', 'button'], 
	function (flip, service) {
		$('input[type=checkbox]').unbind('change').change(function () {
			var $input = $('#notifySalemanCheckbox'); 
			if($input.attr('disabled')) {
				$input.removeAttr('disabled');
				
				return;
			}
			$input.val('');
			$input.attr('disabled', 'true');
		});
		
		$('button#searchButton').unbind('click').click(function() {
		});
	}
);

(function() {
	define('service', ['flip'], 
		function() {
			var init = function() {}
		
			return {
				init: init
			}
		}
	);
})()