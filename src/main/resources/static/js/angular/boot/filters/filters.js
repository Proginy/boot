'use strict';

boot.filter('boolean', function() {
	return function(input) {
		
		return input != null && (input == '1' || input.toUpperCase() == 'TRUE' || input.toUpperCase() == 'T') ? 'True' : "False";
	};
});