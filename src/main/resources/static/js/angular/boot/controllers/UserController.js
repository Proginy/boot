boot.controller('UserController', function ($scope, UserService) {
	UserService.fetchStories().success(function (users) {
		$scope.users = users;
	})
})